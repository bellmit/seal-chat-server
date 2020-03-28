package com.mn.im.core.common.extend.config;

import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.utils.StringUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: WebServiceNames
 * @Description:
 * @date 2018/3/23
 */
@ConfigurationProperties(prefix = "lms.feigns.serverNames")
@Component
@Data
public class WebServiceNames {

    private static WebServiceNames webServiceNames = null;

    private static final String HTTP = "http://";

    public WebServiceNames(){
        webServiceNames = this;
    }

    private String lmsAgent;

    private String lmsOauth2;

    private String lmsSystem;

    private String lmsActiviti;

    private String lmsPostBiz;

    private String lmsPreBiz;

    /**
     * @Fields  : 名称map集合,以下标做标识
     * @author qiaomengnan
     */
    private Map<Integer, String> nameMaps = null;

    private static String getUrlPath(String serviceName, String path){
        if(StringUtils.isNotTrimBlank(path) && !path.startsWith("/"))
            path = "/" + path;
        return HTTP + serviceName + path;
    }

    /**
     * @Title:
     * @Description: 通过服务标识和路径 拼出一个完成的url
     * @param:  serviceFlag 服务标识
     * @param:  path 路径
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/4/19 0019 17:01
     */
    public static String getUrlPath(Integer serviceFlag, String path){
        if(serviceFlag == null)
            throw new ServiceException("服务标识不能为空");
        if(StringUtils.isTrimBlank(path))
            throw new ServiceException("服务数据路径不能为空");
        String serviceName = webServiceNames.getNameMaps().get(serviceFlag);
        if(StringUtils.isTrimBlank(serviceName))
            throw new ServiceException("服务不存在");
        return webServiceNames.getUrlPath(serviceName,path);
    }

    public static String getLmsAgentUrl(String path){
        return getUrlPath(webServiceNames.getLmsAgent(),path);
    }

    public static String getLmsOauth2Url(String path){
        return getUrlPath(webServiceNames.getLmsOauth2(),path);
    }

    public static String getLmsSystemUrl(String path){
        return getUrlPath(webServiceNames.getLmsSystem(),path);
    }

    public static String getLmsActivitiUrl(String path){
        return getUrlPath(webServiceNames.getLmsActiviti(),path);
    }

    public static String getLmsPostBizUrl(String path){
        return getUrlPath(webServiceNames.getLmsPostBiz(),path);
    }

    public static String getLmsPreBizUrl(String path){
        return getUrlPath(webServiceNames.getLmsPreBiz(),path);
    }

    public static boolean isLmsAgent(String requestUrl){
        if(StringUtils.isNotTrimBlank(requestUrl) && requestUrl.contains(webServiceNames.lmsAgent))
            return true;
        return false;
    }

}
