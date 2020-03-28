package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TDepartmentTypes
 * @Description:
 * @date 2020-01-11
 */
public enum TDepartmentTypes {

    XS("0","销售部"),
    PROD("1","生产部"),
    ZJ("2","质检部"),
    CW("3","财务部"),
    GL("4","管理部"),
    SYS_ADMIN("5","系统管理部"),
    STORE("6","仓储部");

    private String type;

    private String desc;

    TDepartmentTypes(String type, String desc){
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
        for(TDepartmentTypes item : TDepartmentTypes.values()) {
            dataList.add(item.getType());
        }
    }

}
