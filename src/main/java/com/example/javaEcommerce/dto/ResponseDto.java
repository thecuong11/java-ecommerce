package com.example.javaEcommerce.dto;

import com.example.javaEcommerce.common.Constant;
import com.example.javaEcommerce.enums.MessageEnum;
import lombok.Data;

@Data
public class ResponseDto<T> {
    private String message;
    private String code;
    private String status;
    private T data;

    public ResponseDto() {}

    public ResponseDto(T data) {
        MessageEnum msg = MessageEnum.SUCCESS;
        this.status = Constant.SUCCESS;
        this.message = msg.getMessage();
        this.code = msg.getCode();
        this.data = data;
    }
}
