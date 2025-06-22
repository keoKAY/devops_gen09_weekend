package co.istad.restfulsamplejpa.adviser;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestControllerAdviser {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HashMap<String,Object> handleNoSuchElementException(NoSuchElementException ex ){
        return new HashMap<>(){{
            put("message","There is no element !!! ");
            put("status",HttpStatus.NOT_FOUND.value());
            put("payload",null);
        }};
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HashMap<String, Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
       return new HashMap<String,Object>(){{
           put("errors",exception.getBindingResult().getFieldErrors().stream()
                   .map(err -> new HashMap<String, Object>(){{
                       put(err.getField(), err.getDefaultMessage());
                   }}).toList());
       }};
    }
}
