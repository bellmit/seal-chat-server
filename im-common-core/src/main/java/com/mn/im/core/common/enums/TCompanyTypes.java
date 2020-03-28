package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: TCompanyTypes
 * @Description: 公司类型
 * @date 2019/12/18
 */
public enum TCompanyTypes {

    COMPANY("0","公司"),
    FACTORY("1","工厂");

    private String type;

    private String desc;

    TCompanyTypes(String type, String desc){
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
