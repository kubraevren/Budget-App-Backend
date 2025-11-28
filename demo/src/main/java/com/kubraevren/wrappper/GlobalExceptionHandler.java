package com.kubraevren.wrappper; // Kendi paket isminle aynı olduğundan emin ol!

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {//hatayı yakalarr

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ApiResponse<?>> handleBaseException(BaseException ex) {//ex=hata
        ResponseMessage responseMessage = ex.getResponseMessage();

        // ApiResponse.failure metodu: (String error, String message) bekliyor.
        // responseMessage.name() -> Örn: "USER_NOT_FOUND" (error kodu gibi düşün)
        // ex.getMessage() -> Örn: "Kullanıcı Bulunamadı."
        return ResponseEntity
                .status(responseMessage.getHttpStatus())// 404 mü 500 mü?
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
        ResponseMessage responseMessage = ResponseMessage.INTERNAL_ERROR;

        return ResponseEntity
                .status(responseMessage.getHttpStatus())
                .body(ApiResponse.failure(responseMessage.name(), ex.getMessage()));
    }
}