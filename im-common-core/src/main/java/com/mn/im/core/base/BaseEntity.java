package com.mn.im.core.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mn.im.core.common.utils.DateUtils;
import com.mn.im.core.common.utils.IdGenerator;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qiaomengnan
 * @ClassName: BaseEntity
 * @Description: entity基类 所有的业务类 需要继承
 * @date 2018/1/7
 */
@Data
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * @Fields  : ID
     * @author qiaomengnan
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = IdGenerator.ID_GENERATOR)
    protected String id;

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

    /**
     * @Fields  : 用于保存上一次数据库的更新时间,在更新时做排他处理
     * @author qiaomengnan
     */
    @JsonIgnore
    @Transient
    protected Date updateLastTime;

}
