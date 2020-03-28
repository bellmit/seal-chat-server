package com.mn.im.core.common.utils;

import com.mn.im.core.common.exception.ServiceException;

/**
 * @author qiaomengnan
 * @ClassName: IntegerUtils
 * @Description: integer工具类
 * @date 2018/4/14
 */
public class IntegerUtils {

    /**
     * @Title:
     * @Description: 对象转换成Integer类型
     * @param:
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/4/14 13:50
     */
    public static Integer getValue(Object val){
        if(val == null)
            return null;
        return Integer.valueOf(val.toString());
    }

    /**
     * @Title:
     * @Description: 对象转换成int类型
     * @param:
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/4/14 13:50
     */
    public static int getIntValue(Object val){
        if(val == null)
            throw new ServiceException("对象为空,不能转换为int");
        return Integer.valueOf(val.toString()).intValue();
    }

    /**
     * @Title:
     * @Description:   返回value 没有则是default
     * @param val
     * @param defaultValue
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/14 11:46:44
     */
    public static int getValue(Object val, int defaultValue) {
        Integer res = getValue(val);
        return res == null ? defaultValue : res;
    }


    /**
     * @Title:
     * @Description: 判断一个值是否包含在一个数组中
     * @param: arrs
     * @param: val
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/4/20  14:34
     */
    public static boolean containValue(Integer[] arrs, Integer val){
        if(ArrayUtils.isNotNullAndLengthNotZero(arrs)){
            for(Integer tmp :arrs){
                if(tmp.equals(val))
                    return true;
            }
        }
        return false;
    }

    /**
     * @Title:
     * @Description: 判断一个值是否包含在一个数组中
     * @param: arrs
     * @param: val
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/4/20  14:34
     */
    public static boolean containValue(int [] arrs,int val){
        if(arrs != null && arrs.length > 0){
            for(int tmp :arrs){
                if(tmp == val)
                    return true;
            }
        }
        return false;
    }

    /**
     * 判断int list是否为空或者长度为0
     * @param list
     * @return
     */
    public static boolean isNullOrLengthZero(int [] list){
        return list == null || list.length == 0;
    }

    /**
     * 判断int list是否不为空并且长度不为0
     * @param list
     * @return
     */
    public static boolean isNotNullAndNotLengthZero(int [] list){
        return !isNullOrLengthZero(list);
    }

    /**
     * @Title:
     * @Description: 是否相等
     * @param val1 val2
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/12/12 05:56:42
     */
    public static boolean equals(Integer val1, Integer val2){
        if(val1 == null || val2 == null)
            return val1 == val2;
        else
            return val1.equals(val2);
    }

    /**
     * @Title:
     * @Description:   是否不相等
     * @param val1 val2
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/12/12 05:55:35
     */
    public static boolean notEquals(Integer val1, Integer val2){
        return !equals(val1,val2);
    }

}
