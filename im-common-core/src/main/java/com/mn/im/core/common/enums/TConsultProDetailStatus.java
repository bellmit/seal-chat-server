package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: TConsultProDetailStatus
 * @Description: 订单/产品状态
 * @date 2020-01-25
 */
public enum TConsultProDetailStatus {

    UNREAD("0","1000","待处理"),
    SUPPLIER("1","1001","采购完成"),
    CONVERTER("2","1002","加工完成"),
    PROD_COMPLETE("3","1003","生产完成"),
    COMPLAINT_SUCCESS("4","1004","质检合格"),
    COMPLAINT_ERROR("5","1005","质检不合格"),
    STORE("6","1006","已入库"),
    PROD_AUDIT_SUCCESS("7","1007","生产入账成功"),
    PROD_AUDIT_ERROR("8","1008","生产入账失败"),
    SEND("9","1009","已发货"),
    SEND_AUDIT_SUCCESS("10","1010","发货入账成功"),
    SEND_AUDIT_ERROR("11","1011","发货入账失败"),
    COMPLETE("12","1012","产品已完成");

    private String type;

    private String code;

    private String desc;

    TConsultProDetailStatus(String type , String code , String desc){
        this.type = type;
        this.code = code;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static final List<String> dataList = new ArrayList<>();

    public static final Map<String,TConsultProDetailStatus> dataMap = new HashMap<>();

    static {
        for(TConsultProDetailStatus item : TConsultProDetailStatus.values()) {
            dataList.add(item.getType());
            dataMap.put(item.getType(),item);
        }
    }

}
