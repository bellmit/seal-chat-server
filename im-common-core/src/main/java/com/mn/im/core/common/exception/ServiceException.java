
package com.mn.im.core.common.exception;

/**
 * @author qiaomengnan
 * @ClassName: LmsServiceException
 * @Description: 逻辑层异常
 * @date 2018/1/7
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 5439915454935047937L;

    public ServiceException(String msg){
        super(msg);
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
