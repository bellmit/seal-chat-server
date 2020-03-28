package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TThirdTypes
 * @Description: 第三方类型
 * @date 2020-01-29
 */
public enum TThirdTypes {

    SUPPLIER("0","供应商"),
    CONVERTER("1","加工商"),
    LOGISTIC("2","物流"),
    CONSUMABLE("3","耗材"),
    OTHER("9","其他");

    private String type;

    private String desc;

    TThirdTypes(String type, String desc){
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
        for(TThirdTypes item : TThirdTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
