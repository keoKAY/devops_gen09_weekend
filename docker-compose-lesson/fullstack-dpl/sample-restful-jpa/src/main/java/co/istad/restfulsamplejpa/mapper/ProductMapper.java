package co.istad.restfulsamplejpa.mapper;

import co.istad.restfulsamplejpa.dto.ProductRequest;
import co.istad.restfulsamplejpa.dto.ProductResponse;
import co.istad.restfulsamplejpa.model.Product;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResponse mapToProductResponse(Product product);
    Product mapRequestToProduct(ProductRequest productRequest);

    @AfterMapping
    default void setId(@MappingTarget Product product){
         if(product.getId()==null){
             product.setId(0L);
         }

    }
}
