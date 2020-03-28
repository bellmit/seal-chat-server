package com.mn.im.core.common.extend.mybatis.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author qiaomengnan
 * @ClassName: ASQLInterceptor
 * @Description: sql拦截器
 * @date 2019/12/13
 */
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SQLInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        buildSQL(invocation);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        String dialect = properties.getProperty("dialect");
        log.info("mybatis intercept dialect:{}", dialect);
    }

    /**
     * @Title:
     * @Description:   重构SQL,缺陷: SQL分析不太友好,SQL必须有where关键字
     * @param invocation
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 11:49:34
     */
    private void buildSQL(Invocation invocation) {
//        TUserVo tUserVo = UserInfoUtils.getUserDetail();
//        if(tUserVo == null)
//            return;
//        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
//        //获取sql
//        String boundSql = String.valueOf(metaStatementHandler.getValue("delegate.boundSql.sql"));
//        ASQLInterceptor[] aSqlInterceptors = ReflectUtils.classMethodAnnotation(ASQLInterceptor.class, String.valueOf(metaStatementHandler.getValue("delegate.mappedStatement.id")));
//        if(aSqlInterceptors != null) {
//            // 循环读取方法头上注解
//            for(ASQLInterceptor aSqlInterceptor : aSqlInterceptors) {
//                if (aSqlInterceptor != null) {
//                    // 是否可以进入拼接查询
//                    boolean getInto = true;
//                    TRoleCodes[] tRoleCodes = aSqlInterceptor.roleCode();
//                    if(tRoleCodes != null && tRoleCodes.length > 0) {
//                        getInto = false;
//                        for(TRoleCodes data : tRoleCodes) {
//                            if(data.getType().equals(tUserVo.getUserRoleCode())) {
//                                getInto = true;
//                                break;
//                            }
//                        }
//                    }
//                    // 进入拼接查询
//                    if(getInto) {
//                        String[] res = new String[2];
//                        int index;
//                        if((index = boundSql.lastIndexOf("where")) == -1) {
//                            index = boundSql.lastIndexOf("WHERE");
//                        }
//                        if(index == -1) {
//                            throw new ServiceException("SQL中没有where关键字");
//                        }
//                        res[0] = boundSql.substring(0,index);
//                        res[1] = boundSql.substring(index);
//                        StringBuilder sb = new StringBuilder(boundSql);
//                        sb.insert(index,buildInterceptorSql(aSqlInterceptor.left(), tUserVo));
//                        sb.insert(index + 5, " " + buildInterceptorSql(aSqlInterceptor.value(), tUserVo) + (res[1].indexOf("0") != -1 ? " AND " : ""));
//                        boundSql = sb.toString();
//                        //boundSql = res[0] + buildInterceptorSql(aSqlInterceptor.left(), tUserVo) + res[1] + " and " + buildInterceptorSql(aSqlInterceptor.value(), tUserVo);
//                    }
//                }
//            }
//            metaStatementHandler.setValue("delegate.boundSql.sql", boundSql);
//        }
    }

//    private String buildInterceptorSql(String sql, TUserVo tUserVo) {
//        if (StringUtils.isNotTrimBlank(sql)) {
//            sql = sql.replace(ParameterFlag.CURRENT_USER_ID, "'" + tUserVo.getUserId() + "'" );
//            sql = sql.replace(ParameterFlag.CURRENT_USER_COMPANY_CODE, "'" + tUserVo.getUserCompanyCode() + "'" );
//            sql = sql.replace(ParameterFlag.CURRENT_USER_DEP_CODE, "'" + tUserVo.getUserDepCode() + "'" );
//            sql = sql.replace(ParameterFlag.CURRENT_USER_COMPANY_ID, "'" + tUserVo.getUserCompanyId() + "'" );
//            sql = sql.replace(ParameterFlag.T_DEPARTMENT_TYPES_XS, "'" + TDepartmentTypes.XS.getType() + "'" );
//            sql = sql.replace(ParameterFlag.T_DEPARTMENT_TYPES_PROD, "'" + TDepartmentTypes.PROD.getType() + "'" );
//            sql = sql.replace(ParameterFlag.T_ROLE_CODES_XS, "'" + TRoleCodes.XS.getType() + "'" );
//            sql = sql.replace(ParameterFlag.T_ROLE_CODES_PROD, "'" + TRoleCodes.PROD.getType() + "'" );
//            sql = sql.replace(ParameterFlag.CURRENT_USER_DEP_ID, "'" + tUserVo.getUserDepId() + "'" );
//        }
//        return sql;
//    }
//
//    public static class ParameterFlag {
//
//        public static final String CURRENT_USER_ID = "#{currentUserId}";
//
//        public static final String CURRENT_USER_COMPANY_CODE = "#{currentUserCompanyCode}";
//
//        public static final String CURRENT_USER_DEP_CODE = "#{currentUserDepCode}";
//
//        public static final String CURRENT_USER_COMPANY_ID = "#{currentUserCompanyId}";
//
//        public static final String CURRENT_USER_DEP_ID = "#{currentUserDepId}";
//
//        public static final String T_DEPARTMENT_TYPES_XS = "#{TDepartmentTypes.XS}";
//
//        public static final String T_DEPARTMENT_TYPES_PROD = "#{TDepartmentTypes.PROD}";
//
//        public static final String T_ROLE_CODES_XS = "#{TRoleCodes.XS}";
//
//        public static final String T_ROLE_CODES_PROD = "#{TRoleCodes.PROD}";
//
//    }

}
