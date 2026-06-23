package com.likelion.likelionassignmentcrud.menu.api.dto.response;

import lombok.Builder;
import java.util.List;

@Builder
public record MenuListResponseDto(
        List<MenuInfoResponseDto> menus
) {
    public static MenuListResponseDto from(List<MenuInfoResponseDto> menus) {
        return MenuListResponseDto.builder()
                .menus(menus)
                .build();
    }
}