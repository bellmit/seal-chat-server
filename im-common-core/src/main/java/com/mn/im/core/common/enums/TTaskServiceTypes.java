package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TTaskServiceTypes
 * @Description: 任务类型
 * @date 2019/12/11
 */
public enum TTaskServiceTypes {

    REMARK("0","备忘录"),
    OFFER("1","询盘报价任务"),
    PLAN("2","工作计划"),
    ORDER_AUDIT("3","订单审核"),
    ORDER_HANDLER("4","订单生产"),
    ORDER_PLAN("5","生产计划")
    ;

    private String type;

    private String desc;

    TTaskServiceTypes(String type, String desc){
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
        for(TTaskServiceTypes item : TTaskServiceTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
