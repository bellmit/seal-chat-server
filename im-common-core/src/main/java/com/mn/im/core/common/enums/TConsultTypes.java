package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TConsultTypes
 * @Description: 询盘类型
 * @date 2020-01-13
 */
public enum TConsultTypes {

    CONSULT("0","询盘记录"),
    ORDER("1","订单记录");

    private String type;

    private String desc;

    TConsultTypes(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }


    public static final List<String> dataList = new ArrayList<>();

    static {
        for(TConsultTypes item : TConsultTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
