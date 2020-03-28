package com.mn.im.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: TComplaintHandlerStatus
 * @Description:
 * @date 2020-02-04
 */
public enum  TComplaintHandlerStatus {

    PROCESS("1","处理中"),
    COM_HAND_ONE("2","合格正常发货"),
    COM_HAND_TWO("3","不合格协商发货"),
    COM_HAND_THREE("4","不合格退货"),
    COM_HAND_FOUR("5","不合格报废");

    private String type;

    private String desc;

    TComplaintHandlerStatus(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static final Map<String,String> dataMap = new HashMap<>();

    static {
        for(TConsultProDetailStatus item : TConsultProDetailStatus.values()) {
            dataMap.put(item.getType(),item.getDesc());
        }
    }

}
