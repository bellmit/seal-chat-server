package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum TRoleCodes {

    SYSTEM_ADMIN("SYSTEM_ADMIN","系统管理"),
    COMPANY_ADMIN("COMPANY_ADMIN","公司(厂)负责人"),
    XS("XS","销售员"),
    XS_ADMIN("XS_ADMIN","销售负责人"),
    PROD("PROD","生产员"),
    PROD_ADMIN("PROD_ADMIN","生产负责人"),
    ACCOUNTING_CUS("ACCOUNTING_CUS","会计(客户)"),
    ACCOUNTING_SUPPLIER("ACCOUNTING_SUPPLIER","会计(供应商)"),
    STORE_ADMIN("STORE_ADMIN","仓库管理员"),
    ZJY("ZJY","质检员")
    ;

    private String type;

    private String desc;

    TRoleCodes(String type, String desc){
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
        for(TRoleCodes item : TRoleCodes.values()) {
            dataList.add(item.getType());
        }
    }

}
