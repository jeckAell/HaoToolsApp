package com.example.jecktao.utils;

import com.example.jecktao.baseConfig.DefinitionException;

/**
 * 自定义异常工具方法
 */
public class JeckTaoExecption {

    public static DefinitionException throwExecption(String errorCode, String errorMsg) {
        throw new DefinitionException(errorCode, errorMsg);
    }
}
