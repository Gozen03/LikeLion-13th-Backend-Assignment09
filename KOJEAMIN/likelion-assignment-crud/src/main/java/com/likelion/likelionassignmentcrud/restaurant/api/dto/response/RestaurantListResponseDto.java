package com.likelion.likelionassignmentcrud.restaurant.api.dto.response;

import lombok.Builder;
import java.util.List;

@Builder
public record RestaurantListResponseDto(
        List<RestaurantInfoResponseDto> restaurants
) {
    public static RestaurantListResponseDto from(List<RestaurantInfoResponseDto> restaurants) {
        return RestaurantListResponseDto.builder()
                .restaurants(restaurants)
                .build();
    }
}