package com.mn.im.core.common.annotation;

import com.mn.im.core.common.extend.aop.service.ValidDataExistConditionService;
import com.mn.im.core.common.extend.aop.service.ValidDataExistService;

import java.lang.annotation.*;

/**
 * @author qiaomengnan
 * @ClassName: ValidDataExist
 * @Description: 验证数据是否存在
 * @date 2019/12/14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(ValidDataExists.class)
public @interface ValidDataExist {

    /**
     * @Fields  : id
     * @author qiaomengnan
     */
    String value() default  "";

    Class valueType() default String.class;

    String errMsg() default "该CODE信息不存在";

    String setData() default "";

    String getData() default "";

    Class dataType() default String.class;

    String existData() default "";

    boolean currentUserData() default false;

    /**
     * @Fields  : 根据id查询数据的实现类
     * @author qiaomengnan
     */
    Class<? extends ValidDataExistService> service() default ValidDataExistService.class;

    /**
     * @Fields  : 自定义条件service验证
     * @author qiaomengnan
     */
    Class<? extends ValidDataExistConditionService> conditionService() default ValidDataExistConditionService.class;

}
