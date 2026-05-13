package com.scalers.productserviceebatch.controllers;


import com.scalers.productserviceebatch.models.Product;
import com.scalers.productserviceebatch.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        // In a real application, you would fetch the product from a database
        // Here, we are just returning a dummy product for demonstration
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
//        product.setId(id);
//        product.setName("Sample Product");
//        product.setPrice(99.99);
//        product.setDescription("This is a sample product.");
//        product.setImage("https://example.com/product-image.jpg");
//        return product;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        // In a real application, you would fetch all products from a database
        // Here, we are just returning a dummy message for demonstration
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); //@98123
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // In a real application, you would save the product to a database
        // Here, we are just returning the received product for demonstration
        return new Product();
    }

    @PatchMapping("{id}")
    public Product updateProduct(@PathVariable long id, @RequestBody Product product) {
        // In a real application, you would update the product in a database
        // Here, we are just returning the received product for demonstration
        return new Product();
    }

    @PutMapping("{id}")
    public Product replaceProduct(@PathVariable long id, @RequestBody Product product) {
        // In a real application, you would replace the product in a database
        // Here, we are just returning the received product for demonstration
        return productService.replaceProduct(id, product);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable long id) {
        // In a real application, you would delete the product from a database
        // Here, we are just printing a message for demonstration
        System.out.println("Product with id " + id + " deleted.");
    }
}
