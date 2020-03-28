package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: TCompanyLevels
 * @Description: 公司级别
 * @date 2019/12/18
 */
public enum TCompanyLevels {


    HEAD_COMPANY("0","总公司"),
    BRANCH_COMPANY("1","分公司");

    public String type;

    public String desc;

    TCompanyLevels(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static class Types {

        public static final String HEAD_COMPANY = "0";

    }

}
