package com.likelion.likelionassignmentcrud.common.exception;

import com.likelion.likelionassignmentcrud.common.response.code.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;
    private final String customMessage; // 추가적인 상세 메시지를 담을 필드

    // 생성자: 기본 에러 메시지 활용
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.customMessage = errorCode.getMessage();
    }

    // 생성자: 기본 에러 메시지 뒤에 커스텀 메시지(예: ID값)를 붙임
    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(errorCode.getMessage() + customMessage);
        this.errorCode = errorCode;
        this.customMessage = errorCode.getMessage() + customMessage;
    }
}