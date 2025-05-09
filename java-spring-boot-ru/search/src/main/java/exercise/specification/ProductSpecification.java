package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN

@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt())
                .and(withPriceLt(params.getPriceLt())
                .and(withRatingGt(params.getRatingGt())))
                .and(withTitleCont(params.getTitleCont()))
                );
    }
    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null
                ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }


    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) -> price == null
                ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
    }

    private Specification<Product> withPriceLt(Integer price) {
        return (root, query, cb) -> price == null
                ? cb.conjunction() : cb.lessThan(root.get("price"), price);
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, cb) -> rating == null
                 ? cb.conjunction() : cb.greaterThan(root.get("rating"), rating);
    }

    private Specification<Product> withTitleCont(String name) {
        return (root, query, cb) -> name == null
                ? cb.conjunction() // : cb.like(root.get("title"), "%" + name + "%");
                : cb.like(cb.lower(root.get("title")), "%" + name + "%");
    }
}

//     private String titleCont;
//    private Long categoryId;
//    private Integer priceLt;
//    private Integer priceGt;
//    private Double ratingGt;

// END
