package com.likelion.likelionassignmentcrud.menu.application;

import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.common.response.code.ErrorCode;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.domain.Menu;
import com.likelion.likelionassignmentcrud.menu.domain.repository.MenuRepository;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    // 메뉴 저장 (음식점 존재 여부 예외 처리 추가)
    @Transactional
    public void menuSave(MenuSaveRequestDto menuSaveRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(menuSaveRequestDto.restaurantId())
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage() + menuSaveRequestDto.restaurantId()
                ));

        Menu menu = Menu.builder()
                .restaurant(restaurant)
                .name(menuSaveRequestDto.name())
                .price(menuSaveRequestDto.price())
                .build();

        menuRepository.save(menu);
    }

    // 메뉴 전체 조회
    public MenuListResponseDto menuFindAll() {
        List<Menu> menus = menuRepository.findAll();
        List<MenuInfoResponseDto> menuInfoResponseDtoList = menus.stream()
                .map(MenuInfoResponseDto::from)
                .collect(Collectors.toList());
        return MenuListResponseDto.from(menuInfoResponseDtoList);
    }

    // 음식점별 메뉴 조회 (음식점 존재 여부 예외 처리 추가)
    public MenuListResponseDto menuFindAllByRestaurant(Long restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage() + restaurantId
                ));

        List<Menu> menus = menuRepository.findByRestaurant_RestaurantId(restaurantId);
        List<MenuInfoResponseDto> menuInfoResponseDtoList = menus.stream()
                .map(MenuInfoResponseDto::from)
                .collect(Collectors.toList());
        return MenuListResponseDto.from(menuInfoResponseDtoList);
    }

    // 단일 메뉴 상세 조회 (예외 처리 적용)
    public MenuInfoResponseDto menuFindOne(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION,
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION.getMessage() + menuId
                ));

        return MenuInfoResponseDto.from(menu);
    }

    // 메뉴 정보 수정 (예외 처리 적용)
    @Transactional
    public void menuUpdate(Long menuId, MenuUpdateRequestDto menuUpdateRequestDto) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION,
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION.getMessage() + menuId
                ));

        // Record DTO를 활용한 업데이트
        menu.update(menuUpdateRequestDto);
    }

    // 메뉴 삭제 (예외 처리 적용)
    @Transactional
    public void menuDelete(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION,
                        ErrorCode.MENU_NOT_FOUND_EXCEPTION.getMessage() + menuId
                ));

        menuRepository.delete(menu);
    }
}