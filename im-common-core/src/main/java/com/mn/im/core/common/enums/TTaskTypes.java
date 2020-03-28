package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TTaskTypes
 * @Description:
 * @date 2020-01-16
 */
public enum TTaskTypes {

    REMARK("0","备忘录"),
    SERVICE("1","业务分配");

    private String type;

    private String desc;

    TTaskTypes(String type, String desc){
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
        for(TTaskTypes item : TTaskTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
