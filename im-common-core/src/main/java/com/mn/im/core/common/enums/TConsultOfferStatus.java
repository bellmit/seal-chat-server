package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TConsultOfferStatus
 * @Description:
 * @date 2019-12-11
 */
public enum TConsultOfferStatus {

    OFFER_SALE_ADMIN("0","负责人处理"),
    OFFER_PROD_PRICE("1","跟单员处理"),
    OFFER_SALE_ADMIN_PRICE("2","负责人二次处理"),
    OFFER_SALE_PRICE("3","销售处理"),
    OFFER_END("9","完成");

    private String type;

    private String desc;

    TConsultOfferStatus(String type, String desc){
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
        for(TConsultOfferStatus item : TConsultOfferStatus.values()) {
            dataList.add(item.getType());
        }
    }


}
