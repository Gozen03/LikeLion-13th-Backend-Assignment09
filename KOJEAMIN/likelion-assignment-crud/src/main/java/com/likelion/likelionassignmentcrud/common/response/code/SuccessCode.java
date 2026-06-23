package com.likelion.likelionassignmentcrud.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum SuccessCode {

    /**
     * 200 OK (성공)
     */
    GET_SUCCESS(HttpStatus.OK, "성공적으로 조회했습니다."),

    // Restaurant 관련 성공 코드
    RESTAURANT_UPDATE_SUCCESS(HttpStatus.OK, "음식점 정보가 성공적으로 수정되었습니다."),
    RESTAURANT_DELETE_SUCCESS(HttpStatus.OK, "음식점이 성공적으로 삭제되었습니다."),

    // Menu 관련 성공 코드 (추후 사용)
    MENU_UPDATE_SUCCESS(HttpStatus.OK, "메뉴 정보가 성공적으로 수정되었습니다."),
    MENU_DELETE_SUCCESS(HttpStatus.OK, "메뉴가 성공적으로 삭제되었습니다."),

    /**
     * 201 CREATED (생성 성공)
     */
    // Restaurant 관련 생성 코드
    RESTAURANT_SAVE_SUCCESS(HttpStatus.CREATED, "음식점이 성공적으로 등록되었습니다."),

    // Menu 관련 생성 코드 (추후 사용)
    MENU_SAVE_SUCCESS(HttpStatus.CREATED, "메뉴가 성공적으로 등록되었습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}