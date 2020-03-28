package com.mn.im.core.base;

import com.mn.im.core.common.extend.response.ResponseEnums;
import com.mn.im.core.common.extend.response.RestResponse;
import com.mn.im.core.common.extend.response.RestResponseGenerator;
import com.mn.im.core.common.utils.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * @author qiaomengnan
 * @ClassName: BaseController
 * @Description:
 * @date 2019-12-22
 */
public abstract class BaseController<T extends BaseService, K extends BaseEntity , V extends PageQuery> {

    @Autowired
    protected T baseService;

    /**
     * @Title:
     * @Description:   分页查询
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/11/28 09:59:18
     */
    @RequestMapping(value = "findVosByPage" ,method = RequestMethod.POST)
    public ResponseEntity<RestResponse> findVosByPage(@RequestBody V v){
        return new ResponseEntity<RestResponse>(
                RestResponseGenerator.genSuccessResponse(this.baseService.findVosByPage(v)),
                HttpStatus.OK);
    }


    /**
     * @Title:
     * @Description: 保存
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/09 05:42:12
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> save(@Valid @RequestBody V v){
        this.baseService.save(v);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }

    /**
     * @Title:
     * @Description: 修改客户
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/09 05:42:12
     */
    @RequestMapping(value = "modify",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> modify(@Valid @RequestBody V v){
        this.baseService.modify(v);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }

    /**
     * @Title:
     * @Description: 删除
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/09 05:42:12
     */
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> delete(@Valid @RequestBody V v){
        this.baseService.delete(v);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(ResponseEnums.SUCCESS.getMark()), HttpStatus.OK);
    }

    /**
     * @Title:
     * @Description: 客户详情
     * @param v
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/09 05:42:12
     */
    @RequestMapping(value = "detail",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> detail(@Valid @RequestBody V v){
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(
                this.baseService.detail(v)
        ), HttpStatus.OK);
    }


    /**
     * @Title:
     * @Description:   批量删除
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/18 10:50:45
     */
    @RequestMapping(value = "batchDelete",method = RequestMethod.POST)
    public ResponseEntity<RestResponse> batchDelete(@RequestBody List<V> dataList){
        baseService.batchDelete(dataList);
        return new ResponseEntity<RestResponse>(RestResponseGenerator.genSuccessResponse(), HttpStatus.OK);
    }

}
