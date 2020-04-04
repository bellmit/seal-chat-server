package com.mn.im.core.base;

import com.mn.im.core.common.exception.ServiceException;
import com.mn.im.core.common.utils.ArrayUtils;
import com.mn.im.core.common.utils.PageInfoExtend;
import com.mn.im.core.common.utils.PageQuery;
import com.mn.im.core.common.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: BaseService
 * @Description:
 * @date 2019-12-22
 */
@Slf4j
public abstract class BaseService<T extends AbstractBaseRepository, K extends BaseEntity<K>, V extends PageQuery<K>> {

    @Autowired
    protected T baseRepository;

    @Autowired
    protected RedisService redisService;

    /**
     * @Title:
     * @Description:   分页查询
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/04 09:46:56
     */
    public PageInfoExtend<V> findVosByPage(V v) {
        v.initSQLVo();
        return this.baseRepository.selectListVoByPage("selectVos",v);
    }

    /**
     * @Title:
     * @Description:   查询第一条数据
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/15 10:41:54
     */
    public V findFirstVo(V v) {
        v.initSQLVo();
        List<V> vs = this.baseRepository.getBaseDao().selectVos(v);
        return ArrayUtils.getFirst(vs);
    }

    /**
     * @Title:  
     * @Description:   保存
     * @param v
     * @return 
     * @throws 
     * @author qiaomengnan 
     * @date 2020/01/04 09:55:50
     */
    public int save(V v) {
        return this.baseRepository.insertData(v.getEntity());
    }

    /**
     * @Title:
     * @Description:   保存
     * @param k
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/04 09:55:50
     */
    public int save(K k) {
        return this.baseRepository.insertData(k);
    }

    /**
     * @Title:
     * @Description:   修改
     * @param v
     * @return 
     * @throws 
     * @author qiaomengnan 
     * @date 2020/01/04 09:56:07
     */
    public int modify(V v) {
        return this.baseRepository.updateByPrimaryKeySelectiveData(v.getEntity());
    }

    /**
     * @Title:
     * @Description:   修改
     * @param k
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/04 09:56:07
     */
    public int modify(K k) {
        return this.baseRepository.updateByPrimaryKeySelectiveData(k);
    }
    
    /**
     * @Title:
     * @Description:   删除
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/04 09:56:59
     */
    public int delete(V v) {
        return this.baseRepository.deleteData(v.getEntity());
    }

    /**
     * @Title:
     * @Description:   详情
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/01/04 09:57:14
     */
    public V detail(V v) {
        List<V> vs = this.baseRepository.getBaseDao().selectVos(v);
        return ArrayUtils.getFirst(vs);
    }

    /**
     * @Title:
     * @Description:   批量删除
     * @param dataList
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/22 04:22:46
     */
    @Transactional
    public int batchDelete(List<V> dataList) {
        if(ArrayUtils.isNotNullAndLengthNotZero(dataList)) {
            List ids = new ArrayList();
            BaseEntity baseEntity = null;
            for (V v : dataList) {
                baseEntity = v.getEntity();
                List<EntityColumn> pkColumnList = new ArrayList<>(EntityHelper.getPKColumns(baseEntity.getClass()));
                if(pkColumnList == null || pkColumnList.size() != 1) {
                    throw new ServiceException("ID信息不合法");
                }
                ids.add(ReflectUtils.getFieldValue(pkColumnList.get(0).getProperty(),baseEntity));
            }
            try {
                return baseRepository.deleteDataByIds(ids,baseEntity.getClass().newInstance());
            } catch (Exception ex) {
                ex.printStackTrace();
                log.error(ex.getMessage());
            }
        }
        return 0;
    }

}
