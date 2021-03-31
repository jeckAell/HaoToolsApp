package com.example.jecktao.baseConfig;

import lombok.Data;

/**
 * 自定义的异常类
 */
@Data
public class DefinitionException extends RuntimeException{
    protected String errorCode;
    protected String errorMsg;

    public DefinitionException() {

    }

    public DefinitionException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
