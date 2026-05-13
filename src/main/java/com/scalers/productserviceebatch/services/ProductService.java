package com.scalers.productserviceebatch.services;


import com.scalers.productserviceebatch.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(long id, Product product);
    Product replaceProduct(long id, Product product);
    void deleteProduct(long id);
}