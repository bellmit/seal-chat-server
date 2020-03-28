package com.mn.im.core.common.enums.common;

/**
 * @author wangxue
 * @ClassName: EnableFlagEnums
 * @Description: 枚举 常量
 * @date 2018/3/27
 */
public enum EnableFlagEnums {

    ENABLE("0","启用"),
    DISABLE("1","禁用");

    private String type;

    private String desc;

    EnableFlagEnums(String type, String desc){
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
