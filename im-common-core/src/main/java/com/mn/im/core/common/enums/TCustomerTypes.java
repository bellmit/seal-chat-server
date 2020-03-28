package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum TCustomerTypes {

    A("0","A类客户(自己开发)"),
    B("1","B类客户(分配客户)");

    private String type;

    private String desc;

    TCustomerTypes(String type, String desc){
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
        for(TCustomerTypes item : TCustomerTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
