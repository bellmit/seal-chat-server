package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum TTaskStatus {

    UNREAD("0","待处理"),
    PROCESSING("1","处理中"),
    COMPLETE("9","处理完成");

    private String type;

    private String desc;

    TTaskStatus(String type, String desc){
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
        for(TTaskStatus item : TTaskStatus.values()) {
            dataList.add(item.getType());
        }
    }

}
