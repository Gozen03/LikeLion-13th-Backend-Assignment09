package com.likelion.likelionassignmentcrud.restaurant.application;

import com.likelion.likelionassignmentcrud.common.exception.BusinessException;
import com.likelion.likelionassignmentcrud.common.response.code.ErrorCode;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.KakaoPlaceResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.domain.Restaurant;
import com.likelion.likelionassignmentcrud.restaurant.domain.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final KakaoLocalClient kakaoLocalClient; // 필드 선언을 Repository 옆으로 모아줌

    // 음식점 정보 저장
    @Transactional
    public void restaurantSave(RestaurantSaveRequestDto restaurantSaveRequestDto) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantSaveRequestDto.name())
                .category(restaurantSaveRequestDto.category())
                .build();
        restaurantRepository.save(restaurant);
    }

    // 음식점 모두 조회 (페이지네이션 적용)
    public Page<RestaurantInfoResponseDto> restaurantFindAll(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);
        // Page 객체 내부의 요소들을 DTO로 변환
        return restaurants.map(RestaurantInfoResponseDto::from);
    }

    // 단일 음식점 조회 (예외 처리 적용)
    public RestaurantInfoResponseDto restaurantFindOne(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage() + restaurantId
                ));

        return RestaurantInfoResponseDto.from(restaurant);
    }

    // 음식점 정보 수정 (예외 처리 적용)
    @Transactional
    public void restaurantUpdate(Long restaurantId, RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage() + restaurantId
                ));
        restaurant.update(restaurantUpdateRequestDto);
    }

    // 음식점 정보 삭제 (예외 처리 적용)
    @Transactional
    public void restaurantDelete(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new BusinessException(
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION,
                        ErrorCode.RESTAURANT_NOT_FOUND_EXCEPTION.getMessage() + restaurantId
                ));
        restaurantRepository.delete(restaurant);
    }

    public KakaoPlaceResponseDto searchExternalRestaurants(String keyword) {
        return kakaoLocalClient.searchRestaurant(keyword);
    }
}