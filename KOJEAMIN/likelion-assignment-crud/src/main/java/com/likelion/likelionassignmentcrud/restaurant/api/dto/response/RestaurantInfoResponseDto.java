package com.likelion.likelionassignmentcrud.restaurant.api.dto.response;

import com.likelion.likelionassignmentcrud.restaurant.domain.Category;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import lombok.Builder;

@Builder
public record RestaurantInfoResponseDto(
        Long restaurantId,
        String name,
        Category category
) {
    public static RestaurantInfoResponseDto from(Restaurant restaurant) {
        return RestaurantInfoResponseDto.builder()
                .restaurantId(restaurant.getRestaurantId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .build();
    }
}