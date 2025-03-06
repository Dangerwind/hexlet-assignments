package exercise.repository;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import exercise.model.Product;

import java.sql.SQLException;
import java.sql.Statement;

public class ProductsRepository extends BaseRepository {

    // BEGIN
        public static void save(Product prod) throws SQLException {
            String sql = "INSERT INTO products (title, price) VALUES (?, ?)";
            try (var conn = dataSource.getConnection();
                 var preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, prod.getTitle());
                preparedStatement.setInt(2, prod.getPrice());
                preparedStatement.executeUpdate();
                var generatedKeys = preparedStatement.getGeneratedKeys();
                // Устанавливаем ID в сохраненную сущность
                if (generatedKeys.next()) {
                    prod.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("DB have not returned an id after saving an entity");
                }
            }
        }

        public static Optional<Product> find(Long id) throws SQLException {
            var sql = "SELECT * FROM products WHERE id = ?";
            try (var conn = dataSource.getConnection();
                 var stmt = conn.prepareStatement(sql)) {
                stmt.setLong(1, id);
                var resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    var title = resultSet.getString("title");
                    var price = resultSet.getInt("price");
                    var prd = new Product(title, price);
                    prd.setId(id);
                    return Optional.of(prd);
                }
                return Optional.empty();
            }
        }

        public static List<Product> getEntities() throws SQLException {
            var sql = "SELECT * FROM products";
            try (var conn = dataSource.getConnection();
                 var stmt = conn.prepareStatement(sql)) {
                var resultSet = stmt.executeQuery();
                var result = new ArrayList<Product>();
                while (resultSet.next()) {
                    var id = resultSet.getLong("id");
                    var title = resultSet.getString("title");
                    var price = resultSet.getInt("price");
                    var prd = new Product(title, price);
                    prd.setId(id);
                    result.add(prd);
                }
                return result;
            }
        }

    // END
}
