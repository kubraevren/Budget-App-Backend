package com.kubraevren.wrappper;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseMessage {

    // --- GENEL BAŞARI ---
    SUCCESS(HttpStatus.OK, "Başarılı."),

    // --- AUTH (GİRİŞ/KAYIT) ---
    LOGIN_SUCCESS(HttpStatus.OK, "Giriş başarılı."),
    USER_CREATED(HttpStatus.CREATED, "Kullanıcı başarıyla kayıt edildi."), // <-- Bunu geri ekledim!
    TOKEN_REFRESHED(HttpStatus.OK, "Token yenilendi."),

    // --- EXPENSE (HARCAMA/GELİR) ---
    EXPENSE_CREATED(HttpStatus.CREATED, "Kayıt başarıyla eklendi."),
    EXPENSE_UPDATED(HttpStatus.OK, "Kayıt güncellendi."),
    EXPENSE_DELETED(HttpStatus.OK, "Kayıt silindi."),

    // --- HATALAR (FAILURE) ---
    EMAIL_OR_PASSWORD_INVALID(HttpStatus.UNAUTHORIZED, "E-mail veya Şifre Yanlış."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "Token süresi dolmuş."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "Token geçersiz."),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "Bu işlem için yetkiniz yok."),

    USER_ALREADY_REGISTERED(HttpStatus.CONFLICT, "Kullanıcı zaten kayıtlı."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "Kullanıcı Bulunamadı."),
    EXPENSE_NOT_FOUND(HttpStatus.NOT_FOUND, "Harcama/Gelir kaydı bulunamadı."),

    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Bir Sorun Oluştu. Lütfen daha sonra tekrar deneyiniz.");

    private final HttpStatus httpStatus;
    private final String message;

    ResponseMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}