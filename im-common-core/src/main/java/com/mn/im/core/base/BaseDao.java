package com.mn.im.core.base;

import com.mn.im.core.common.utils.PageQuery;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * Created by qiaohao on 2017/8/23.
 */
public interface BaseDao<T extends BaseEntity , V extends PageQuery> extends Mapper<T>, MySqlMapper<T> {

    List<V> selectVos(@Param("params") V v);

}
