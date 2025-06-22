package co.istad.restfulsamplejpa.service;

import co.istad.restfulsamplejpa.dto.ProductRequest;
import co.istad.restfulsamplejpa.dto.ProductResponse;
import co.istad.restfulsamplejpa.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProduct();
    ProductResponse createProduct(ProductRequest product);
    ProductResponse updateProduct(Long id, ProductRequest product);
    ProductResponse deleteProduct(Long id);
}
