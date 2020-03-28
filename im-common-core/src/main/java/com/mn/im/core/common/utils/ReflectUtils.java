package com.mn.im.core.common.utils;

import com.mn.im.core.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: ReflectUtils
 * @Description: 反射工具类
 * @date 2018/5/23
 */
@Slf4j
public class ReflectUtils {

    /**
     * @Title:  
     * @Description:   暂时用于SQL拦截,但是一个类中不能有多个同名的方法
     * @param resClazz
     * @param classMethod
     * @return 
     * @throws 
     * @author qiaomengnan 
     * @date 2019/12/17 11:39:06
     */
    public static <T extends Annotation> T[] classMethodAnnotation(Class<T> resClazz, String classMethod) {
        try {
            String className = classMethod.substring(0, classMethod.lastIndexOf("."));
            String methodName = classMethod.substring(classMethod.lastIndexOf(".") + 1).replace("_COUNT","");
            Class clazz = Class.forName(className);
            Method method = null;
            for (Method data : clazz.getDeclaredMethods()) {
                if (data.getName().equals(methodName)) {
                    if(ArrayUtils.isNotNullAndLengthNotZero(data.getParameterTypes())) {
                        // 父类方法不算
                        if(data.getParameterTypes()[0] == PageQuery.class) {
                            continue;
                        }
                    }
                    if(method != null) {
                        throw new ServiceException("该类有多个重名方法,请处理");
                    } else {
                        method = data;    
                    }
                }
            }
            if (method != null) {
                return method.getAnnotationsByType(resClazz);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @param field
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static String getMethodGetName(Field field){
        field.setAccessible(true);
        return "get" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @param field
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static String getMethodSetName(Field field){
        field.setAccessible(true);
        return "set" + field.getName().substring(0,1).toUpperCase() + field.getName().substring(1);
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @param field
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static Method getMethodGet(Field field, Class clazz) throws NoSuchMethodException {
        return clazz.getMethod(getMethodGetName(field));
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @param field
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static Method getMethodSet(Field field, Class clazz) throws NoSuchMethodException {
        return clazz.getMethod(getMethodSetName(field),field.getType());
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static Field getField(String fieldName , Class clazz) throws NoSuchFieldException {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static Object getFieldValue(String fieldName, Object param, Class clazz) throws NoSuchFieldException,
            NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field field = getField(fieldName,clazz);
        Method method = getMethodGet(field,clazz);
        return method.invoke(param);
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static Object getFieldValue(String fieldName, Object data) {
       try {
           Class clazz = data.getClass();
           Field field = getField(fieldName, clazz);
           Method method = getMethodGet(field, clazz);
           return method.invoke(data);
       }catch (Exception ex){
           log.error(ex.getMessage());
           ex.printStackTrace();
           return null;
       }
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    public static <T> T setObjectValue(T data, String key, String value){
        try {
            Class clazz = data.getClass();
            Field field = ReflectUtils.getField(key, data.getClass());
            Method method = ReflectUtils.getMethodSet(field, clazz);
            method.invoke(data, getValue(field,value));
            return data;
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @Title:
     * @Description:   获取get方法
     * @param field
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:00:43
     */
    private static Object getValue(Field field, String value){
        try {
            if(StringUtils.isTrimBlank(value))
                return null;
            if (field.getType() == Integer.class)
                return Integer.valueOf(value);
            else if (field.getType() == Double.class)
                return Double.valueOf(value);
            else if (field.getType() == Float.class)
                return Float.valueOf(value);
            else if (field.getType() == BigDecimal.class)
                return new BigDecimal(value);
            else if (field.getType() == String.class)
                return value;
            else
                throw new ServiceException(field.getDeclaringClass() + "值类型还未定义");
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            throw new ServiceException("值转换失败");
        }
    }

    /**
     * @Title:
     * @Description:   保存并返回实体列
     * @param entityClass
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/02/26 10:41:25
     */
    public static Map<String, Field> getMapField(Class<?> entityClass){
        Map<String, Field> fieldMap = new HashMap<>();
        for(Field field : entityClass.getDeclaredFields()){
            field.setAccessible(true);
            fieldMap.put(field.getName(),field);
        }

        Class superClass = entityClass.getSuperclass();
        while(superClass != null){
            for(Field field : superClass.getDeclaredFields()){
                field.setAccessible(true);
                fieldMap.put(field.getName(),field);
            }
            superClass = superClass.getSuperclass();
        }
        return fieldMap;
    }

}
