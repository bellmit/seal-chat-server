package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TUserWageTypes
 * @Description: 工资类型
 * @date 2020-01-30
 */
public enum TUserWageTypes {

    BASIC("0","基本工资"),
    ROYALTY("1","提成工资");

    private String type;

    private String desc;

    TUserWageTypes(String type, String desc){
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
        for(TUserWageTypes item : TUserWageTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
