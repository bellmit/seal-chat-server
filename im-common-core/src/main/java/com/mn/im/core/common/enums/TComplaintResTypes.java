package com.mn.im.core.common.enums;

/**
 * @author qiaomengnan
 * @ClassName: TComplaintResTypes
 * @Description:
 * @date 2020-02-04
 */
public enum TComplaintResTypes {


    UNQUALIFIED("0","不合格"),
    QUALIFIED("1","合格");

    private String type;

    private String desc;

    TComplaintResTypes(String type, String desc){
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
