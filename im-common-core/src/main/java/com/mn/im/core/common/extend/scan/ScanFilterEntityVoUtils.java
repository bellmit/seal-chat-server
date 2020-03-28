package com.mn.im.core.common.extend.scan;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiaomengnan
 * @ClassName: ScanFilterEntityVoUtils
 * @Description: 扫描实体和vo需要做过滤的类
 * @date 2019/12/12
 */
public class ScanFilterEntityVoUtils {

    private static final Map<Class, List<Field>> classFields = new ConcurrentHashMap<>();

    private static final List<Class> classList = new ArrayList<>();

    private static final List<String> classPathList = new ArrayList<>();


    public static final String entityVoPackage = "com.qmn.erp.steels.admin.pojo";

    public static void scanClassFields() {

    }

    private static void scanClass() {
        String classPath = ScanFilterEntityVoUtils.class.getResource("/").getPath();
        String basePack = entityVoPackage.replace(".","/");
    }

    private static void scanPath(File file) {
        if(file != null) {
            if(file.isDirectory()) {
                File [] files = file.listFiles();
//                for(File )
            } else {
                if(file.getName().endsWith(".class")) {
                    classPathList.add(file.getPath());
                }
            }
        }
    }




}
