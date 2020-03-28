package com.mn.im.core.common.extend.aop.service;

import com.mn.im.core.common.annotation.ValidDataExist;
import com.mn.im.core.common.annotation.ValidDataExists;
import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.extend.common.util.SpringUtils;
import com.mn.im.core.common.utils.ExpressionParserUtils;
import com.mn.im.core.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author qiaomengnan
 * @ClassName: ValidDataExistAop
 * @Description: 判断某个数据是否存在
 * @date 2019/12/18
 */
@Slf4j
@Component
@Aspect
public class ValidDataExistAop {

    @Pointcut("@annotation(com.mn.im.core.common.annotation.ValidDataExists)")
    public void aspect() { }

    @Pointcut("@annotation(com.mn.im.core.common.annotation.ValidDataExist)")
    public void aspect2() { }

    @Around("aspect() && @annotation(validDataExists)")
    public Object around(ProceedingJoinPoint joinPoint,
            ValidDataExists validDataExists) throws Throwable {
        for (ValidDataExist data : validDataExists.value()) {
            init(joinPoint,data);
        }
        return joinPoint.proceed();
    }

    @Around("aspect2() && @annotation(validDataExist)")
    public Object around2(ProceedingJoinPoint joinPoint,
            ValidDataExist validDataExist) throws Throwable {
        init(joinPoint,validDataExist);
        return joinPoint.proceed();
    }

    private void init(ProceedingJoinPoint joinPoint , ValidDataExist validDataExist) {
        boolean validFlag = true;
        if(validDataExist.conditionService() != ValidDataExistConditionService.class) {
            validFlag = SpringUtils.getBean(validDataExist.conditionService()).validDataExistConditionCheck(joinPoint.getArgs());
        }
        if(validFlag) {
            String value = validDataExist.value();
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            Object[] args = joinPoint.getArgs();
            AopUtils.validParams(value,parameterNames,args);
            ExpressionParserUtils.BuildExpData buildExpData = ExpressionParserUtils.buildExpData(value,parameterNames,args);
            Object valueData = ExpressionParserUtils.getValue(buildExpData.getExp(), buildExpData.getObjKey(), buildExpData.getObjValue(), validDataExist.valueType());
            ValidDataExistService validDataExistService = SpringUtils.getBean(validDataExist.service());
            Object existData = validDataExistService.detailById(valueData);
            if (existData != null) {
                // 反光设置参数
                if (StringUtils.isNotTrimBlank(validDataExist.getData()) && StringUtils
                        .isNotTrimBlank(validDataExist.setData())) {
                    Object getDataValue = ExpressionParserUtils
                            .getValue("getData." + validDataExist.getData(), "getData", existData,
                                    validDataExist.dataType());
                    ExpressionParserUtils.setValue("setData." + validDataExist.setData(), "setData", buildExpData.getObjValue(), getDataValue);
                }
                if(StringUtils.isNotTrimBlank(validDataExist.existData())) {
                    ExpressionParserUtils.setValue("setData." + validDataExist.existData(), "setData", buildExpData.getObjValue(), existData);
                }
            } else {
                if(validDataExist.currentUserData()) {

                } else {
                    throw new ServiceException(validDataExist.errMsg());
                }
            }
        }
    }

}
