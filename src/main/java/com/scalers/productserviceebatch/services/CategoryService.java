package com.scalers.productserviceebatch.services;

import java.util.List;
import java.util.NoSuchElementException;
import com.scalers.productserviceebatch.models.Category;
import com.scalers.productserviceebatch.models.Product;

public interface CategoryService {
    Category createCategory(String categoryTitle);
    List<Product> getProductsInCategory(String category) throws NoSuchElementException;
    List<Category> getAllCategories() throws NullPointerException;
}
