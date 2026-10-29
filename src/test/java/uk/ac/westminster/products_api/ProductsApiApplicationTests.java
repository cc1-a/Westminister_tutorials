package uk.ac.westminster.products_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductsApiApplicationTests {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @BeforeEach
    void setUp() {
        productService.getAllProducts().clear();
    }

    @Test
    void contextLoads() {
        assertNotNull(productService);
        assertNotNull(productController);
    }

    @Test
    void testServiceAddAndGetAll() {
        assertEquals(0, productService.getAllProducts().size());

        Product p1 = productService.addProduct(new Product("Laptop", 899.99));
        assertNotNull(p1.getId());
        assertEquals("Laptop", p1.getName());
        assertEquals(899.99, p1.getPrice());

        Product p2 = productService.addProduct(new Product("Mouse", 24.99));
        assertNotNull(p2.getId());
        assertEquals(p1.getId() + 1, p2.getId());

        List<Product> all = productService.getAllProducts();
        assertEquals(2, all.size());
    }

    @Test
    void testServiceGetById() {
        Product p = productService.addProduct(new Product("Keyboard", 49.99));
        Optional<Product> found = productService.getProductById(p.getId());
        assertTrue(found.isPresent());
        assertEquals("Keyboard", found.get().getName());

        Optional<Product> notFound = productService.getProductById(999999L);
        assertFalse(notFound.isPresent());
    }

    @Test
    void testServiceDelete() {
        Product p1 = productService.addProduct(new Product("Screen", 199.99));
        Product p2 = productService.addProduct(new Product("Stand", 39.99));

        boolean deleted = productService.deleteProduct(p1.getId());
        assertTrue(deleted);
        assertEquals(1, productService.getAllProducts().size());
        assertEquals("Stand", productService.getAllProducts().get(0).getName());

        boolean deletedAgain = productService.deleteProduct(p1.getId());
        assertFalse(deletedAgain);
    }

    @Test
    void testServiceFindByName() {
        productService.addProduct(new Product("Mechanical Keyboard", 89.99));
        productService.addProduct(new Product("Wireless Mouse", 24.99));
        productService.addProduct(new Product("Ergonomic Keyboard", 119.99));

        List<Product> keyMatches = productService.findByName("key");
        assertEquals(2, keyMatches.size());

        List<Product> mouseMatches = productService.findByName("MOUSE");
        assertEquals(1, mouseMatches.size());
        assertEquals("Wireless Mouse", mouseMatches.get(0).getName());

        List<Product> noMatches = productService.findByName("Monitor");
        assertEquals(0, noMatches.size());
    }

    @Test
    void testControllerEndpoints() {
        // GET /products initially empty
        assertEquals(0, productController.getAllProducts().size());

        // POST /products
        Product created = productController.addProduct(new Product("Headphones", 59.99));
        assertNotNull(created.getId());
        assertEquals("Headphones", created.getName());

        // GET /products/{id}
        Product retrieved = productController.getProductById(created.getId());
        assertNotNull(retrieved);
        assertEquals("Headphones", retrieved.getName());

        // GET /products/{id} not found returns null (status 200 with empty body in Spring)
        assertNull(productController.getProductById(999999L));

        // GET /products/search
        List<Product> searched = productController.searchByName("head");
        assertEquals(1, searched.size());
        assertEquals("Headphones", searched.get(0).getName());

        // DELETE /products/{id}
        productController.deleteProduct(created.getId());
        assertEquals(0, productController.getAllProducts().size());
    }
}
