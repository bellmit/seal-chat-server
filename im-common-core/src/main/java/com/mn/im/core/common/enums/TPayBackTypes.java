package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TPayBackTypes
 * @Description:
 * @date 2020-01-05
 */
public enum TPayBackTypes {

    CUS("99","客户"),
    SUPPLIER("1","供应商"),
    CONVERTER("2","加工商"),
    LOGISTICS("3","物流");

    private String type;

    private String desc;

    TPayBackTypes(String type, String desc){
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
        for(TPayBackTypes item : TPayBackTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
