package com.mn.im.core.common.utils;

import com.mn.im.core.base.BaseVo;
import lombok.Data;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author qiaomengnan
 * @ClassName: ExpressionParserUtils
 * @Description: 解析器
 * @date 2019/12/18
 */
public class ExpressionParserUtils {

    /**
     * @Title:
     * @Description:   获取string类型值
     * @param exp
     * @param objKey
     * @param objValue
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/18 08:50:49
     */
    public static String getStringValue(String exp, String objKey, Object objValue) {
        return getValue(exp, objKey, objValue, String.class);
    }

    /**
     * @Title:
     * @Description:   获取object类型值
     * @param exp
     * @param objKey
     * @param objValue
     * @param clazz
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/18 08:50:52
     */
    public static <T> T getValue(String exp, String objKey, Object objValue, Class<T> clazz) {
        BuildData buildData = buildExpression(exp, objKey, objValue);
        return buildData.getValue(clazz);
    }

    /**
     * @Title:
     * @Description:   设置值
     * @param exp
     * @param objKey
     * @param objValue
     * @param data
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/18 09:28:30
     */
    public static void setValue(String exp, String objKey, Object objValue, Object data) {
        BuildData buildData = buildExpression(exp, objKey, objValue);
        buildData.setValue(data);
    }

    /**
     * @Title:
     * @Description:   构建解析器
     * @param exp
     * @param objKey
     * @param objValue
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/18 09:28:38
     */
    public static BuildData buildExpression(String exp, String objKey, Object objValue) {
        exp = "#{#" + exp + "}";
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(objKey, objValue);
        Expression expression = parser.parseExpression(exp, new TemplateParserContext());
        return new BuildData(parser,context,expression);
    }

    /**
     * @author qiaomengnan
     * @ClassName: ExpressionParserUtils
     * @Description: 内部解析类
     * @date 2019-12-18
     */

    @Data
    static class BuildData {

        ExpressionParser parser;

        EvaluationContext context;

        Expression expression;

        public BuildData(ExpressionParser parser, EvaluationContext context, Expression expression) {
            this.parser = parser;
            this.context = context;
            this.expression = expression;
        }

        public void setValue(Object data) {
            this.getExpression().setValue(this.getContext(),data);
        }

        public <T> T getValue(Class<T> clazz) {
            return this.getExpression().getValue(this.getContext(),clazz);
        }

    }

    public static BuildExpData buildExpData (String value, String[] parameterNames , Object[] args) {
        String parameterName = value.indexOf(".") == -1 ? value : value.split("\\.")[0];
        String exp;
        String objKey;
        int i = 0;
        for (String name : parameterNames) {
            if (parameterName.equals(name))
                break;
            else {
                if((i + 1) < parameterNames.length)
                    i++;
            }
        }
        if (args[i] instanceof BaseVo && value.indexOf(".") == -1) {
            objKey = parameterNames[i];
            exp = parameterNames[i] + "." + value;
        } else {
            objKey = value.split("\\.")[0];
            exp = value;
        }
        return new BuildExpData(exp,objKey,args[i]);
    }


    @Data
    public static class BuildExpData {

        String exp;

        String objKey;

        Object objValue;

        public BuildExpData(String exp , String objKey,  Object objValue) {
            this.exp = exp;
            this.objKey = objKey;
            this.objValue = objValue;
        }

    }


}
