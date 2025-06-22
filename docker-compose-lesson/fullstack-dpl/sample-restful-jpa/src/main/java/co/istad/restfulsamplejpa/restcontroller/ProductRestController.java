package co.istad.restfulsamplejpa.restcontroller;


import co.istad.restfulsamplejpa.dto.ProductRequest;
import co.istad.restfulsamplejpa.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
@Slf4j
@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductService productService;
    private HashMap<String, Object> generateResponse(Object data, String message, int statusCode) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("payload", data);
        response.put("status", statusCode);
        return response;
    }

    @GetMapping
    public HashMap<String, Object> getAllProduct() {
        return generateResponse(
                productService.getAllProduct(),
                "Successfully Retrieved the data ! ",
                HttpStatus.OK.value());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String, Object> createProduct(
            @Valid @RequestBody ProductRequest request) {
//        log.info("This is the info logsss : "+request);
        return generateResponse(
                productService.createProduct(request),
                "Successfully Create Product!"
                , HttpStatus.CREATED.value()
        );
    }


    @PutMapping("/{id}")
    public HashMap<String,Object> updateProduct(@PathVariable Long id,@RequestBody ProductRequest request){
        return generateResponse(productService.updateProduct(id,request),
                "Update Successfully!",
                HttpStatus.OK.value()
                );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HashMap<String,Object> deleteProduct(@PathVariable Long id){
        return generateResponse(
                productService.deleteProduct(id),
                "Deleted Successfully!! "
                ,HttpStatus.NO_CONTENT.value()
        );
    }



}
