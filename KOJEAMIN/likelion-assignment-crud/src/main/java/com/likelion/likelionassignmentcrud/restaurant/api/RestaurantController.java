package com.likelion.likelionassignmentcrud.restaurant.api;

import com.likelion.likelionassignmentcrud.common.response.code.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantSaveRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.request.RestaurantUpdateRequestDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.KakaoPlaceResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.api.dto.response.RestaurantInfoResponseDto;
import com.likelion.likelionassignmentcrud.restaurant.application.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurant")
@Tag(name = "음식점 API", description = "음식점을 관리하는 API")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 음식점 저장 (유효성 검사 및 공통 응답 적용)
    @PostMapping()
    @Operation(summary = "음식점 등록", description = "새로운 음식점을 등록합니다.")
    public ApiResTemplate<Void> restaurantSave(@RequestBody @Valid RestaurantSaveRequestDto restaurantSaveRequestDto) {
        restaurantService.restaurantSave(restaurantSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_SAVE_SUCCESS);
    }

    // 음식점 전체 조회 (페이지네이션 및 공통 응답 적용)
    @GetMapping("/all")
    @Operation(summary = "음식점 전체 조회", description = "모든 음식점 목록을 페이징하여 조회합니다.")
    public ApiResTemplate<Page<RestaurantInfoResponseDto>> restaurantFindAll(
            @ParameterObject
            @PageableDefault(
                    size = 10,
                    sort = "restaurantId",
                    direction = Sort.Direction.ASC
            ) Pageable pageable
    ) {
        Page<RestaurantInfoResponseDto> restaurants = restaurantService.restaurantFindAll(pageable);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurants);
    }

    // 음식점 ID로 상세 조회 (공통 응답 적용)
    @GetMapping("/{restaurantId}")
    @Operation(summary = "음식점 상세 조회", description = "음식점 ID로 특정 음식점을 조회합니다.")
    public ApiResTemplate<RestaurantInfoResponseDto> restaurantFindOne(@PathVariable("restaurantId") Long restaurantId) {
        RestaurantInfoResponseDto restaurantInfoResponseDto = restaurantService.restaurantFindOne(restaurantId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, restaurantInfoResponseDto);
    }

    // 음식점 정보 수정 (유효성 검사 및 공통 응답 적용)
    @PatchMapping("/{restaurantId}")
    @Operation(summary = "음식점 정보 수정", description = "음식점 정보를 업데이트합니다.")
    public ApiResTemplate<Void> restaurantUpdate(@PathVariable("restaurantId") Long restaurantId,
                                                 @RequestBody @Valid RestaurantUpdateRequestDto restaurantUpdateRequestDto) {
        restaurantService.restaurantUpdate(restaurantId, restaurantUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_UPDATE_SUCCESS);
    }

    // 음식점 삭제 (공통 응답 적용)
    @DeleteMapping("/{restaurantId}")
    @Operation(summary = "음식점 삭제", description = "음식점 정보를 삭제합니다.")
    public ApiResTemplate<Void> restaurantDelete(@PathVariable("restaurantId") Long restaurantId) {
        restaurantService.restaurantDelete(restaurantId);
        return ApiResTemplate.successWithNoContent(SuccessCode.RESTAURANT_DELETE_SUCCESS);
    }

    @GetMapping("/search-external")
    public ResponseEntity<KakaoPlaceResponseDto> searchExternal(@RequestParam String keyword) {
        KakaoPlaceResponseDto response = restaurantService.searchExternalRestaurants(keyword);
        return ResponseEntity.ok(response);
    }
}