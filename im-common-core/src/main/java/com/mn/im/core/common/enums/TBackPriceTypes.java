package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TBackPriceTypes
 * @Description:
 * @date 2020-01-05
 */
public enum TBackPriceTypes {

    COLLECT("0","收款"),
    PAY("1","付款");

    private String type;

    private String desc;

    TBackPriceTypes(String type, String desc){
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
        for(TBackPriceTypes item : TBackPriceTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
