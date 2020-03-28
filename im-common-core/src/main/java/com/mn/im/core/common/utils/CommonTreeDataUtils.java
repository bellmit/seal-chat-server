package com.mn.im.core.common.utils;

import com.mn.im.core.common.annotation.ChildTreeId;
import com.mn.im.core.common.annotation.ParentTreeId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qiaomengnan
 * @ClassName: CommonTreeDataUtils
 * @Description: 树形共通
 * @date 2018/3/29
 */
@Slf4j
@Data
@Component
public class CommonTreeDataUtils {

    /**
     * @Title:
     * @Description:   根据id查找所有子节点
     * @param datas
     * @param id
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getChildResults(List<T> datas, Object id){
        if (ArrayUtils.isNotNullAndLengthNotZero(datas)) {
            Field fieldC = AnnotationUtils.findField(ChildTreeId.class, datas.get(0).getClass());
            Field fieldP = AnnotationUtils.findField(ParentTreeId.class, datas.get(0).getClass());
            try {
                return getChildResults(datas, id,fieldC,fieldP);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @Title:
     * @Description:   根据id查找所有子节点
     * @param datas
     * @param id
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getChildResults(List<T> datas, Object id , String fieldCName , String fieldPName, String fieldPName2){
        if (ArrayUtils.isNotNullAndLengthNotZero(datas)) {
            try {
                Field fieldC = ReflectUtils.getField(fieldCName,datas.get(0).getClass());
                Field fieldP = ReflectUtils.getField(fieldPName,datas.get(0).getClass());
                Field fieldP2 = ReflectUtils.getField(fieldPName2,datas.get(0).getClass());
                return getChildResults(datas, id,fieldC,fieldP,fieldP2);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /**
     * @Title:
     * @Description:   根据id查找所有子节点
     * @param datas
     * @param id
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getChildResults(List<T> datas, Object id , Field fieldC , Field fieldP) throws Exception{
        return getChildResults(datas,id,fieldC,fieldP,null);
    }

    /**
     * @Title:
     * @Description:   根据id查找所有子节点
     * @param datas
     * @param id
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getChildResults(List<T> datas, Object id , Field fieldC , Field fieldP, Field fieldP2) throws Exception{
        List<T> results = new ArrayList<>();
        if(StringUtils.isTrimBlank(id)){
            return results;
        }
        try {
            if (ArrayUtils.isNotNullAndLengthNotZero(datas)) {
                findChild(results,datas,id,fieldC,fieldP,fieldP2);
                return results;
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
            return null;
        }
        return results;
    }


    /**
     * @Title:
     * @Description: 递归查找子节点
     * @param results
     * @param datas
     * @param id
     * @param fieldC
     * @param fieldP
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:19:42
     */
    private static void findChild(List results, List datas, Object id, Field fieldC, Field fieldP , Field fieldP2) throws
            IllegalAccessException {
        for(Object object : datas){
            if (id.equals(fieldP.get(object)) || (fieldP2 != null && id.equals(fieldP2.get(object)))) {
                results.add(object);
                Object child = fieldC.get(object);
                if(child instanceof List) {
                    //递归查找
                    findChild(results,(List)child,id,fieldC,fieldP,fieldP2);
                } else {
                    //递归查找
                    findChild(results,datas,fieldC.get(object),fieldC,fieldP,fieldP2);
                }
            }
        }
    }

    /**
     * @Title:
     * @Description:   根据id查找所有父节点
     * @param datas
     * @param id
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getParentResults(List<T> datas, Object id){
        List<T> results = new ArrayList<>();
        if(StringUtils.isTrimBlank(id)){
            return results;
        }
        try {
            if (ArrayUtils.isNotNullAndLengthNotZero(datas)) {
                Field fieldC = AnnotationUtils.findField(ChildTreeId.class, datas.get(0).getClass());
                Field fieldP = AnnotationUtils.findField(ParentTreeId.class, datas.get(0).getClass());
                Map<Object, T> mapDatas = new HashMap<Object, T>();
                for(int i=0; i<datas.size(); i++){
                    mapDatas.put(fieldC.get(datas.get(i)), datas.get(i));
                }
                findParent(results,mapDatas,id,fieldC,fieldP);
                return results;
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return results;
    }

    /**
     * @Title:
     * @Description:   根据datas查找所有父节点
     * @param allDatas
     * @param datas
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:20:34
     */
    public static <T> List<T> getParentsResults(List<T> allDatas, List<T> datas){
        List<T> results = new ArrayList();
        if(StringUtils.isTrimBlank(datas)){
            return results;
        }
        try {
            if (ArrayUtils.isNotNullAndLengthNotZero(allDatas)) {
                Field fieldC = AnnotationUtils.findField(ChildTreeId.class, allDatas.get(0).getClass());
                Field fieldP = AnnotationUtils.findField(ParentTreeId.class, allDatas.get(0).getClass());
                Map<Object, T> mapDatas = new HashMap<Object, T>();
                for(int i=0; i<allDatas.size(); i++){
                    mapDatas.put(fieldC.get(allDatas.get(i)), allDatas.get(i));
                }

                for(int i=0; i<datas.size(); i++){
                    List<T> result = new ArrayList<>();
                    findParent(result,mapDatas,fieldC.get(datas.get(i)),fieldC,fieldP);
                    if(StringUtils.isTrimBlank(result)){
                        continue;
                    }
                    for(int j=0; j<result.size(); j++){
                        if(!results.contains(result.get(j))){
                            results.add(result.get(j));
                        }
                    }
                }
                return results;
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
            return null;
        }
        return results;
    }
    /**
     * @Title:
     * @Description: 递归查找父节点
     * @param results
     * @param mapDatas
     * @param id
     * @param fieldC
     * @param fieldP
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:19:42
     */
    private static void findParent(List results, Map mapDatas, Object id, Field fieldC, Field fieldP) throws
            IllegalAccessException {
        if(mapDatas.containsKey(id)){
            results.add(mapDatas.get(id));
            findParent(results,mapDatas,fieldP.get(mapDatas.get(id)),fieldC,fieldP);
        }
    }






    /**
     * @Title:
     * @Description: 递归返回树形结构
     * @param results
     * @param mapDatas
     * @param id
     * @param fieldC
     * @param fieldP
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:19:42
     */
    private static void findNodes(List results, Map mapDatas, Object id, Field fieldC, Field fieldP) throws
            IllegalAccessException {
        if(mapDatas.containsKey(id)){
            results.add(mapDatas.get(id));
            findParent(results,mapDatas,fieldP.get(mapDatas.get(id)),fieldC,fieldP);
        }
    }

    /**
     * @Title:
     * @Description: 递归返回树形结构
     * @param parentId
     * @param datas
     * @param fieldC
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/29 02:19:42
     */
    private static boolean isRoot(Object parentId , List datas, Field fieldC) throws IllegalAccessException {
        if(StringUtils.isTrimBlank(parentId)){
            return true;
        }
        for(int i=0; i<datas.size(); i++){
            if(parentId.equals(fieldC.get(datas.get(i)))){
                return false;
            }
        }
        return true;
    }

}
