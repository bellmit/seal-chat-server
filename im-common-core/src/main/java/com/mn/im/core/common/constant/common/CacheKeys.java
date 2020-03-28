package com.mn.im.core.common.constant.common;

/**
 * @author qiaomengnan
 * @ClassName: CacheableKeys
 * @Description:
 * @date 2019-12-21
 */
public class CacheKeys {

    // ------------------------ 用户相关BEGIN ------------------------

    /**
     * @Fields  : 根据用户名查询用户 (用户表变动时,清空缓存)
     * @author qiaomengnan
     */
    public static final String findUserByUserName = "findUserByUserName";

    /**
     * @Fields  : 查询销售负责人 (用户表变动时,清空缓存)
     * @author qiaomengnan
     */
    public static final String findXsAdmin = "findXsAdmin";

    /**
     * @Fields  : 用户详情 (用户表变动时,清空缓存)
     * @author qiaomengnan
     */
    public static final String detailTUser = "detailTUser";

    // ------------------------ 用户相关END ------------------------


    // ------------------------ 菜单相关BEGIN ------------------------



    // ------------------------ 菜单相关END ------------------------

}
