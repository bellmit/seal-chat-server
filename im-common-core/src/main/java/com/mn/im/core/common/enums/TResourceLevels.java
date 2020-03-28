package com.mn.im.core.common.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: TResourceLevels
 * @Description:
 * @date 2019-12-01
 */
public enum TResourceLevels {

    ONE("1","一级菜单"),
    TWO("2","二级菜单"),
    THREE("3","三级菜单"),
    INTERFACE("4","接口资源");

    private String type;

    private String desc;

    TResourceLevels(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static final List<String> resLevels = new ArrayList<>();

    static {
       for(TResourceLevels item : TResourceLevels.values()) {
           resLevels.add(item.getType());
       }
    }

}
