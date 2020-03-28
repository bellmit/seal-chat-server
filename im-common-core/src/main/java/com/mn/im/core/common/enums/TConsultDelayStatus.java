package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: TConsultDelayStatus
 * @Description: 订单拖延状态
 * @date 2020-03-10
 */
public enum TConsultDelayStatus {

    NORMAL("0","正常"),
    WARNING("1","预警"),
    DELAY("2","已拖延");

    private String type;

    private String desc;

    TConsultDelayStatus(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

}
