package com.mn.im.core.common.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mn.im.core.base.BaseVo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qiaohao
 * @ClassName: PageQuery
 * @Description: 分页查询对象抽象类
 * @date: 2017/8/30 19:55
 */
@Data
public class PageQuery<T> extends BaseVo<T> implements Page, Serializable {

    private static final long serialVersionUID = 1L;

    public PageQuery() {

    }

    public PageQuery(Page page){
        this.currentPage = page.getCurrentPage();
        this.pageSize = page.getPageSize();
    }

    public PageQuery(Integer currentPage, Integer pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageQuery(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @Fields : 当前页数
     */
    private Integer currentPage = 1;


    /**
     * @Fields : 每页显示条数
     */
    private Integer pageSize = 10;

    /**
     * @Fields  : datatable插件参数
     */
    private Integer length ;

    /**
     * @Fields  : datatable参数
     */
    private Integer draw ;

    @JsonIgnore
    @JSONField(serialize = false)
    public  PageQuery getPageQuery(){
        return this;
    }

    /**
     * @Fields  :  是否分页 null、0:分页 , 1:不分页
     * @author qiaomengnan
     */
    private String pageFlag;

    /** 
     * @Fields  :  导出excel最大条数
     * @author qiaomengnan
     */ 
    private Integer excelDataMax;

    /**
     * @Fields  :  是否需要合计
     * @author qiaomengnan
     */
    private String totalFlag;

    /**
     * @Fields  : 返回最大行数
     * @author qiaomengnan
     */
    private Integer topLimit;

}