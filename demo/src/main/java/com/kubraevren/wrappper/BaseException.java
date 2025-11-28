package com.kubraevren.wrappper;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {//FIRLATILAN HATA
    private final ResponseMessage responseMessage;

    public BaseException(ResponseMessage responseMessage) {
        super(responseMessage.getMessage());
        this.responseMessage = responseMessage;
    }
}
