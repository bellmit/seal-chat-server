package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TConsultPoolStatus
 * @Description: 询盘池状态
 * @date 2019/12/19
 */
public enum TConsultPoolStatus {

    NORMAL("0","正常"),
    POOL("1","已入询盘池"),
    COMPLETE("9","已被认领");

    private String type;

    private String desc;

    TConsultPoolStatus(String type, String desc){
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
        for(TConsultPoolStatus item : TConsultPoolStatus.values()) {
            dataList.add(item.getType());
        }
    }

}
