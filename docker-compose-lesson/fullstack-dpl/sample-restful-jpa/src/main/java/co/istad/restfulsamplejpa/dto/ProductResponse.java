package co.istad.restfulsamplejpa.dto;


import lombok.Builder;

@Builder
public record ProductResponse(Long id , String title , String description , String imageUrl, float price ) {
}
