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

        Product p1 = productService.addProduct(new DigitalProduct("Laptop", 899.99, "/dl/laptop", 1.5));
        assertNotNull(p1.getId());
        assertEquals("Laptop", p1.getName());
        assertEquals(899.99, p1.getPrice());

        Product p2 = productService.addProduct(new PhysicalProduct("Mouse", 24.99, 0.2, 5.0));
        assertNotNull(p2.getId());
        assertEquals(p1.getId() + 1, p2.getId());

        List<Product> all = productService.getAllProducts();
        assertEquals(2, all.size());
    }

    @Test
    void testServiceGetById() {
        Product p = productService.addProduct(new DigitalProduct("Keyboard", 49.99, "/dl/keyboard", 2.0));
        Optional<Product> found = productService.getProductById(p.getId());
        assertTrue(found.isPresent());
        assertEquals("Keyboard", found.get().getName());

        Optional<Product> notFound = productService.getProductById(999999L);
        assertFalse(notFound.isPresent());
    }

    @Test
    void testServiceDelete() {
        Product p1 = productService.addProduct(new DigitalProduct("Screen", 199.99, "/dl/screen", 5.0));
        Product p2 = productService.addProduct(new PhysicalProduct("Stand", 39.99, 1.5, 8.0));

        boolean deleted = productService.deleteProduct(p1.getId());
        assertTrue(deleted);
        assertEquals(1, productService.getAllProducts().size());
        assertEquals("Stand", productService.getAllProducts().get(0).getName());

        boolean deletedAgain = productService.deleteProduct(p1.getId());
        assertFalse(deletedAgain);
    }

    @Test
    void testServiceFindByName() {
        productService.addProduct(new DigitalProduct("Mechanical Keyboard", 89.99, "/dl/mech", 1.0));
        productService.addProduct(new PhysicalProduct("Wireless Mouse", 24.99, 0.2, 4.0));
        productService.addProduct(new DigitalProduct("Ergonomic Keyboard", 119.99, "/dl/ergo", 1.2));

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
        assertEquals(0, productController.getAllProducts().size());

        Product created = productController.addProduct(new DigitalProduct("Headphones", 59.99, "/dl/headphones", 0.5));
        assertNotNull(created.getId());
        assertEquals("Headphones", created.getName());

        Product retrieved = productController.getProductById(created.getId());
        assertNotNull(retrieved);
        assertEquals("Headphones", retrieved.getName());

        assertNull(productController.getProductById(999999L));

        List<Product> searched = productController.searchByName("head");
        assertEquals(1, searched.size());
        assertEquals("Headphones", searched.get(0).getName());

        productController.deleteProduct(created.getId());
        assertEquals(0, productController.getAllProducts().size());
    }

    @Test
    void testPolymorphismAndDiscount() {
        DigitalProduct dp = new DigitalProduct("E-Book", 100.0, "/dl/book", 2.5);
        PhysicalProduct pp = new PhysicalProduct("Book", 100.0, 1.0, 5.0);

        productService.addProduct(dp);
        productService.addProduct(pp);

        List<String> summaries = productController.getProductSummaries();
        assertEquals(2, summaries.size());

        dp.applyDiscount(0.10);
        assertEquals(90.0, dp.getDiscountedPrice(), 0.001);

        pp.applyDiscount(0.50);
        assertEquals(80.0, pp.getDiscountedPrice(), 0.001);
    }
}
