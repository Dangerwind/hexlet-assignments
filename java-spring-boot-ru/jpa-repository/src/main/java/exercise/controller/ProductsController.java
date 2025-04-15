package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN

    @GetMapping(path = "")
    public List<Product> getAllProductsBetween(@RequestParam(required = false) Double min,
                                               @RequestParam(required = false) Double max) {
       // System.out.println("!!!!!!  min=" + min + ", max=" + max);

        var mySort = Sort.by(Sort.Order.asc("price"));

        if (min != null && max != null) {
            return productRepository.findByPriceBetween(min, max, mySort);
        }
        if (min != null) {
            return productRepository.findByPriceLessThan(max, mySort);
        }
        if (max != null) {
            return productRepository.findByPriceGreaterThan(min, mySort);
        }

        return productRepository.findAll(mySort);
    }


    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
