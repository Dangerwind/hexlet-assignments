package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN

    List<Product> findByPriceBetween(Double min, Double max, Sort sort);
    List<Product> findByPriceGreaterThan(Double max, Sort sort);
    List<Product> findByPriceLessThan(Double min, Sort sort);
    // END
}
