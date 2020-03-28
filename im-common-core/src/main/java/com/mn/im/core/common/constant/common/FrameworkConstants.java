package com.mn.im.core.common.constant.common;

/**
 * @author qiaomengnan
 * @ClassName: Constants
 * @Description: 框架常量配置
 * @date 2018/1/7
 */
public class FrameworkConstants {

    public static final String COMPONENT_SCAN = "com.qmn.erp.steels.admin";

    public static final String ENABLE_FEIGN_CLIENTS = "com.qmn.erp.steels.admin";

    public static final String MAPPER_SCAN =  "com.qmn.erp.steels.admin.data.dao";

    public static final String AOP_REPOSITORY_INSERT = "execution(* com.qmn.erp.steels.admin.data..*.insert*Data(..))";

    public static final String AOP_REPOSITORY_UPDATE = "execution(* com.qmn.erp.steels.admin.data..*.update*Data(..))";

    public static final String AOP_REPOSITORY_DELETE = "execution(* com.qmn.erp.steels.admin.data..*.delete*Data*(..))";

    public static final String AOP_REPOSITORY_INSERT_LIST = "execution(* com.qmn.erp.steels.admin.data..*.insert*DataList(..))";

    public static final String AOP_REPOSITORY_UPDATE_LIST = "execution(* com.qmn.erp.steels.admin.data..*.update*DataList(..))";

    public static final String AOP_REPOSITORY_DELETE_LIST = "execution(* com.qmn.erp.steels.admin.data..*.delete*DataList(..))";

}
