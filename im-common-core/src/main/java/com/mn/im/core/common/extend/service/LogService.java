package com.mn.im.core.common.extend.service;

import com.mn.im.core.common.extend.vo.RequestParamsLog;

/**
 * @author qiaomengnan
 * @ClassName: LogService
 * @Description: 日志记录service
 * @date 2018/3/8
 */
public interface LogService {

    /**
     * @Title:
     * @Description:   请求日志保存
     * @param requestParamsLog
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/22 03:12:28
     */
    void saveLog(RequestParamsLog requestParamsLog);

}
