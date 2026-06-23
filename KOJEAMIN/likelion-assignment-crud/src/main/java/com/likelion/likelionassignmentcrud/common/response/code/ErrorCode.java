package com.likelion.likelionassignmentcrud.common.response.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    /**
     * 404 NOT FOUND (찾을 수 없음)
     */
    RESTAURANT_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 음식점이 없습니다. restaurantId = "),
    MENU_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 메뉴가 없습니다. menuId = "),

    /**
     * 400 BAD REQUEST (잘못된 요청)
     */
    VALIDATION_EXCEPTION(HttpStatus.BAD_REQUEST, "유효성 검사에 실패하였습니다 - "),

    /**
     * 500 INTERNAL SERVER ERROR (내부 서버 에러)
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버 에러가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String message;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}