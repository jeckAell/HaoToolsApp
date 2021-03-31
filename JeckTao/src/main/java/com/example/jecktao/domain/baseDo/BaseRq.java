package com.example.jecktao.domain.baseDo;

import lombok.Data;

@Data
public class BaseRq<T> {
    private String code;
    private String message;
    private T data;

    public BaseRq() {
        this.code = "0";
        this.message = "成功";
        this.data = null;
    }
}
