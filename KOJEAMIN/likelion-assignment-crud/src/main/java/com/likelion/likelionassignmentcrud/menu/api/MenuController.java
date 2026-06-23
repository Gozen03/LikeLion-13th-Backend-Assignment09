package com.likelion.likelionassignmentcrud.menu.api;

import com.likelion.likelionassignmentcrud.common.response.code.SuccessCode;
import com.likelion.likelionassignmentcrud.common.template.ApiResTemplate;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuSaveRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.request.MenuUpdateRequestDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuInfoResponseDto;
import com.likelion.likelionassignmentcrud.menu.api.dto.response.MenuListResponseDto;
import com.likelion.likelionassignmentcrud.menu.application.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menu")
@Tag(name = "메뉴 API", description = "메뉴를 관리하는 API")
public class MenuController {

    private final MenuService menuService;

    // 메뉴 저장 (유효성 검사 및 공통 응답 적용)
    @PostMapping()
    @Operation(summary = "메뉴 등록", description = "특정 음식점에 새로운 메뉴를 등록합니다.")
    public ApiResTemplate<Void> menuSave(@RequestBody @Valid MenuSaveRequestDto menuSaveRequestDto) {
        menuService.menuSave(menuSaveRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MENU_SAVE_SUCCESS);
    }

    // 메뉴 전체 조회 (페이징 미적용, 공통 응답 적용)
    @GetMapping("/all")
    @Operation(summary = "메뉴 전체 조회", description = "모든 메뉴 목록을 조회합니다.")
    public ApiResTemplate<MenuListResponseDto> menuFindAll() {
        MenuListResponseDto menuListResponseDto = menuService.menuFindAll();
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuListResponseDto);
    }

    // 특정 음식점별 메뉴 조회
    @GetMapping("/restaurant/{restaurantId}")
    @Operation(summary = "음식점별 메뉴 조회", description = "특정 음식점(ID)에 속한 모든 메뉴 목록을 조회합니다.")
    public ApiResTemplate<MenuListResponseDto> menuFindAllByRestaurant(@PathVariable("restaurantId") Long restaurantId) {

        MenuListResponseDto menuListResponseDto = menuService.menuFindAllByRestaurant(restaurantId);

        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuListResponseDto);
    }

    // 메뉴 ID로 상세 조회 (공통 응답 적용)
    @GetMapping("/{menuId}")
    @Operation(summary = "메뉴 상세 조회", description = "메뉴 ID로 특정 메뉴를 조회합니다.")
    public ApiResTemplate<MenuInfoResponseDto> menuFindOne(@PathVariable("menuId") Long menuId) {
        MenuInfoResponseDto menuInfoResponseDto = menuService.menuFindOne(menuId);
        return ApiResTemplate.successResponse(SuccessCode.GET_SUCCESS, menuInfoResponseDto);
    }

    // 메뉴 정보 수정 (유효성 검사 및 공통 응답 적용)
    @PatchMapping("/{menuId}")
    @Operation(summary = "메뉴 정보 수정", description = "메뉴 정보를 업데이트합니다.")
    public ApiResTemplate<Void> menuUpdate(@PathVariable("menuId") Long menuId,
                                           @RequestBody @Valid MenuUpdateRequestDto menuUpdateRequestDto) {
        menuService.menuUpdate(menuId, menuUpdateRequestDto);
        return ApiResTemplate.successWithNoContent(SuccessCode.MENU_UPDATE_SUCCESS);
    }

    // 메뉴 삭제 (공통 응답 적용)
    @DeleteMapping("/{menuId}")
    @Operation(summary = "메뉴 삭제", description = "메뉴 정보를 삭제합니다.")
    public ApiResTemplate<Void> menuDelete(@PathVariable("menuId") Long menuId) {
        menuService.menuDelete(menuId);
        return ApiResTemplate.successWithNoContent(SuccessCode.MENU_DELETE_SUCCESS);
    }
}