package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TDelayOrderFlag
 * @Description:
 * @date 2020-01-30
 */
public enum TDelayOrderFlag {

    SOON("0","即将延期"),
    ALREADY("1","已经延期");

    private String type;

    private String desc;

    TDelayOrderFlag(String type, String desc){
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
        for(TDelayOrderFlag item : TDelayOrderFlag.values()) {
            dataList.add(item.getType());
        }
    }

}
