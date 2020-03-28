package com.mn.im.core.common.extend.common.util;

import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.extend.response.ResponseEnums;
import com.mn.im.core.common.extend.response.ResponseFailEnums;
import com.mn.im.core.common.extend.response.RestResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by qiaohao on 2017/10/23.
 */
public class ResponseEntityUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseEntityUtils.class);


	/**
	 * @Title: getRestResponseData
	 * @Description:调用微服务获取返回对象
	 * @param responseEntity
	 * @return T
	 * @throws ServiceException
	 * @author qiaohao
	 * @date 2017/11/13 10:27:14
	 */
	public static <T> T getRestResponseData(ResponseEntity<RestResponse<T>> responseEntity) throws ServiceException {
		if (responseEntity == null) {
			throw new ServiceException("RPC调用异常,未获取响应对象");
		}
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			throw new ServiceException(responseEntity.getStatusCode().value() + "");
		}
		if (!StringUtils.equals(responseEntity.getBody().getCode(), ResponseEnums.SUCCESS.getCode())) {
			String errorMsg = responseEntity.getBody().getMessage();
			if(StringUtils.equals(responseEntity.getBody().getCode(), ResponseFailEnums.BIZ_CHECK_ERROR.getCode())){
				throw new ServiceException(errorMsg);
			}
			if (StringUtils.isNotBlank(errorMsg)) {
				LOGGER.error("the rpc error is {}", errorMsg);
				throw new ServiceException(responseEntity.getBody().getCode());
			}
			throw new ServiceException(responseEntity.getBody().getCode());
		}
		return responseEntity.getBody().getData();
	}

}
