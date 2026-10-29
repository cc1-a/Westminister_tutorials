package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // In-memory list simulating a database
    private List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);  // Assign current id, then increment
        products.add(product);
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId() != null && p.getId().equals(id))
                .findFirst();
    }

    public boolean deleteProduct(Long id) {
        return products.removeIf(p -> p.getId() != null && p.getId().equals(id));
    }

    public List<Product> findByName(String name) {
        if (name == null) {
            return new ArrayList<>();
        }
        return products.stream()
                .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
