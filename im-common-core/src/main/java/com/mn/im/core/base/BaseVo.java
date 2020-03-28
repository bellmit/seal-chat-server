package com.mn.im.core.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mn.im.core.common.annotation.SQLLike;
import com.mn.im.core.common.utils.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: BaseVo
 * @Description:
 * @date 2018/2/5
 */
@Slf4j
@Data
public abstract class BaseVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final List<String> columns = new ArrayList<>();

    static {
        columns.add("createTime");
        columns.add("creator");
        columns.add("updateTime");
        columns.add("updater");
    }

    @JsonFormat(pattern = DateUtils.formatStr_yyyyMMddHHmmssSSS)
    protected Date createTime;

    @JsonIgnore
    protected String creator;

    @JsonFormat(pattern = DateUtils.formatStr_yyyyMMddHHmmssSSS)
    protected Date updateTime;

    @JsonIgnore
    protected String updater;

    @JsonIgnore
    protected Integer delFlag;

    @JsonIgnore
    @JSONField(serialize = false)
    public T getEntity(){
        try{
            Class clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            return EntityUtils.getEntity(this,(T)clazz.newInstance());
        }catch (IllegalAccessException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }catch (InstantiationException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public T getBasicsEntity(){
        try{
            T oldRes = this.getEntity();
            Class clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            T newRes = (T)clazz.newInstance();
            //field 此处赋值没有调用 get set 方法，直接对属性做操作，请注意！
            Map<String, Field> fieldMap = ReflectUtils.getMapField(clazz);
            for(Field field : fieldMap.values()) {
                if(field.getAnnotation(Id.class) != null || columns.contains(field.getName())) {
                    field.set(newRes,field.get(oldRes));
                }
            }
            return newRes;
        }catch (IllegalAccessException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }catch (InstantiationException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    @JSONField(serialize = false)
    public void initSQLVo(){
        try{
            //field 此处赋值没有调用 get set 方法，直接对属性做操作，请注意！
            Map<String, Field> fieldMap = ReflectUtils.getMapField(this.getClass());
            for(Field field : fieldMap.values()) {
                if(field.getType() ==  String.class) {
                    Object value = field.get(this);
                    if(StringUtils.isTrimBlank(value)) {
                        field.set(this,null);
                    } else {
                        if(field.getAnnotation(SQLLike.class) != null) {
                            field.set(this, SqlUtil.likePattern(value));
                        }
                    }
                }
            }
        }catch (IllegalAccessException ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @Fields  : 用于保存上一次数据库的更新时间,在更新时做排他处理
     * @author qiaomengnan
     */
    @JsonIgnore
    protected Date updateLastTime;

}
