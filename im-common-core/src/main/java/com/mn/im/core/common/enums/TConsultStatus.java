package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TConsultStatus
 * @Description: 询盘状态
 * @date 2019/12/19
 */
public enum TConsultStatus {

    STAY("0","待处理"),
    OFFER_ING("1","报价中"),
    OFFER_COMPLETE("2","报价完成"),
    COMPLETE("9","询盘完成");

    private String type;

    private String desc;

    TConsultStatus(String type, String desc){
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
        for(TConsultStatus item : TConsultStatus.values()) {
            dataList.add(item.getType());
        }
    }

}
