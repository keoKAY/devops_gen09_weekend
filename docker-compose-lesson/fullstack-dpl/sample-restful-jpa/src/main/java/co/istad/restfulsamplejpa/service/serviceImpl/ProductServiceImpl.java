package co.istad.restfulsamplejpa.service.serviceImpl;

import co.istad.restfulsamplejpa.dto.ProductRequest;
import co.istad.restfulsamplejpa.dto.ProductResponse;
import co.istad.restfulsamplejpa.mapper.ProductMapper;
import co.istad.restfulsamplejpa.model.Product;
import co.istad.restfulsamplejpa.repository.ProductRepository;
import co.istad.restfulsamplejpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // this is the constructor injection
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(product->productMapper.mapToProductResponse(product))
                .toList();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        return productMapper.mapToProductResponse(
                productRepository.save(
                        productMapper.mapRequestToProduct(request)
                ));
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest product) {
        var updatedProduct = productRepository.findById(id)
                .stream().findFirst()
                .orElseThrow();
        updatedProduct=productMapper.mapRequestToProduct(product);
        updatedProduct.setId(id);
//        System.out.println("Updated  Product is : "+ updatedProduct)
        return productMapper.mapToProductResponse(productRepository.save(updatedProduct));
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
// find product by id , if product doesn't exist ,
// we throw not found exception
        var deletedProduct= productRepository.findById(id)
                        .stream().findFirst()
                        .orElseThrow();
        productRepository.deleteById(id);
        return productMapper.mapToProductResponse(deletedProduct);
    }
}
