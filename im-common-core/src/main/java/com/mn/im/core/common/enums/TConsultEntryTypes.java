package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: TConsultEntryType
 * @Description:
 * @date 2020-01-15
 */
public enum TConsultEntryTypes {

    CONSULT("0","询盘"),
    ORDER("1","订单");

    private String type;

    private String desc;

    TConsultEntryTypes(String type, String desc){
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
