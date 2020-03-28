package com.mn.im.core.common.annotation;

import com.mn.im.core.common.extend.aop.service.ValidCodeRepeatConditionService;
import com.mn.im.core.common.extend.aop.service.ValidCodeRepeatService;

import java.lang.annotation.*;

@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidCodeRepeat {

    /**
     * @Fields  : id
     * @author qiaomengnan
     */
    String id() default  "";

    /**
     * @Fields  : code
     * @author qiaomengnan
     */
    String code() default "";

    /**
     * @Fields  : errMsg
     * @author qiaomengnan
     */
    String errMsg() default "";

    /**
     * @Fields  : 根据id查询数据的实现类
     * @author qiaomengnan
     */
    Class<? extends ValidCodeRepeatService> service() default ValidCodeRepeatService.class;

    /**
     * @Fields  : 自定义条件service验证
     * @author qiaomengnan
     */
    Class<? extends ValidCodeRepeatConditionService> conditionService() default ValidCodeRepeatConditionService.class;

}
