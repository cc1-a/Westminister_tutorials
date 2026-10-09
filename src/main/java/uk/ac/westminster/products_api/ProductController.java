package uk.ac.westminster.products_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/product")
public class ProductController {
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product p = new Product(id, "sdf", 12.2);
        return p;
    }


}
