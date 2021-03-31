package com.example.jecktao.baseConfig;

import com.example.jecktao.domain.baseDo.BaseRq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class MycontrollerActive {

    private static final Logger logger = LoggerFactory.getLogger(MycontrollerActive.class);

    /**
     * 统一处理系统异常的类
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseRq<Object> sysErrorHandler(Exception ex) {
        BaseRq<Object> rq = new BaseRq<Object>();
        rq.setCode("-1");
        rq.setMessage("系统异常");
        rq.setData(null);
        logger.error(ex.getMessage());
        return rq;
    }

    @ResponseBody
    @ExceptionHandler(value = DefinitionException.class)
    public BaseRq<Object> myErrorHandler(DefinitionException dex) {
        BaseRq<Object> rq = new BaseRq<Object>();
        rq.setData(null);
        rq.setCode(dex.getErrorCode());
        rq.setMessage(dex.getErrorMsg());
        logger.info(dex.errorCode + ":" + dex.getErrorMsg());
        return rq;
    }
}
