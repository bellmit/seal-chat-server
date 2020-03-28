package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: AppUserTypes
 * @Description: 用户类型
 * @date 2019/3/5 0005
 */
public enum  AppUserTypes {

    TOURIST("0","游客"),
    STUDENT("1","学员");

    private String type;

    private String desc;

    AppUserTypes(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
