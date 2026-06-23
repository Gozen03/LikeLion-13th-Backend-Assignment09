package com.likelion.likelionassignmentcrud.restaurant.api.dto.request;

import com.likelion.likelionassignmentcrud.restaurant.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RestaurantSaveRequestDto(

        @NotBlank(message = "음식점 이름을 필수로 입력해야 합니다.")
        String name,

        @NotNull(message = "카테고리는 필수 입력입니다.")
        Category category
) {
}