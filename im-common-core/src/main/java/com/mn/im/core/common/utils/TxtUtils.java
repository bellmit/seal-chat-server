package com.mn.im.core.common.utils;

/**
 * @author qiaomengnan
 * @ClassName: CommonFileConstants
 * @Description: txt工具类
 * @date 2018/5/15
 */
public class TxtUtils {

    /**
     * @Fields  : txt后缀名
     * @author qiaomengnan
     */
    public static final String TXT_SUFFIX = ".txt";

    /**
     * @Title:
     * @Description:  返回txt文件名称
     * @param name
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/05/15 10:20:09
     */
    public static String getTxtName(String name){
        return name + TXT_SUFFIX;
    }

}
