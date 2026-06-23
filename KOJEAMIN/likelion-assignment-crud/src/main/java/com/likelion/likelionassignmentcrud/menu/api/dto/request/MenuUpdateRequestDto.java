package com.likelion.likelionassignmentcrud.menu.api.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record MenuUpdateRequestDto(

        @NotBlank(message = "수정할 메뉴 이름을 필수로 입력해야 합니다.")
        String name,

        @Min(value = 0, message = "수정할 가격은 0원 이상이어야 합니다.")
        int price
) {
}