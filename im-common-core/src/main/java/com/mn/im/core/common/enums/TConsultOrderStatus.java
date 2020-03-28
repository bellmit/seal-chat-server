package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TConsultOrderStatus
 * @Description: 订单状态
 * @date 2020-01-28
 */
public enum TConsultOrderStatus {

    STORAGE("0","暂存"),
    PROCESS("1","处理中"),
    DELAY("2","拖延交货期"),
    AUDIT_ING("3","提交审核中"),
    AUDIT_SUCCESS("4","审核通过"),
    AUDIT_REFUSE("5","审核拒绝"),
    COMPLETE("9","已完成");

    private String type;

    private String desc;

    TConsultOrderStatus(String type, String desc){
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
        for(TConsultOrderStatus item : TConsultOrderStatus.values()) {
            dataList.add(item.getType());
        }
    }

}
