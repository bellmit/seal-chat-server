package com.mn.im.core.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * Created by qiaohao on 2017/9/14.
 */
@Slf4j
public class CommonFileUtils {

    public static final String SLANT_LINE = "/";

    /**
     * 按行读取项目中文件，并且换行
     * @return
     */
    public static String readLineFileln(String filePath,Class clazz) throws Exception{
        InputStream inputStream = clazz.getResourceAsStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String lineTxt = null;
        StringBuffer result =new StringBuffer();
        while((lineTxt = bufferedReader.readLine()) != null){
            result.append(lineTxt+"\n");
        }
        return result.toString();
    }

    /**
     * 向指定目录输出指定文件
     * @param filePath
     * @param fileName
     * @param data
     */
    public static void outFile(String filePath,String fileName,String data){
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            File file = new File(filePath);
            if(!file.isDirectory())
                file.mkdirs();
            file = new File(filePath+"/"+fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(data);
            bufferedWriter.close();
        }catch (Exception ex){
            log.error(ex.getMessage());
            ex.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null)
                    bufferedWriter.close();
                if (fileWriter != null)
                    fileWriter.close();
            }catch (Exception ex){
                log.error(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    /**
     * @Title:
     * @Description:   读取file文件并向输出流中写入
     * @param fileCompletePath
     * @param out
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/31 06:08:36
     */
    public static void writerFile(String fileCompletePath,OutputStream out) throws IOException{
        writerFile(new FileInputStream(new File(fileCompletePath)),out);
    }

    /**
     * @Title:
     * @Description:   将file文件流输出流中写入
     * @param inputStream
     * @param out
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/01/31 06:08:36
     */
    public static void writerFile(InputStream inputStream,OutputStream out) throws IOException{

        BufferedInputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedInputStream(inputStream);
            byte [] buff = new byte[1024];
            int i = bufferedInputStream.read(buff);
            while (i != -1){
                out.write(buff);
                out.flush();
                i = bufferedInputStream.read(buff);
            }
        } finally {
            if(bufferedInputStream != null){
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * @Title:
     * @Description:   截取文件名
     * @param fileCompletePath
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/04/02 08:10:38
     */
    public static String getFileName(String fileCompletePath){
        if(StringUtils.isNotTrimBlank(fileCompletePath)){
            String [] results = fileCompletePath.split("/");
            if(ArrayUtils.isNotNullAndLengthNotZero(results)){
                return results[results.length - 1];
            }
        }
        return null;
    }



    /**
     * @Title:
     * @Description:   返回一个文件输出流
     * @param filePath
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/05/14 06:07:43
     */
    public static FileOutputStream getFileOutputStream(String filePath) throws FileNotFoundException {
        return new FileOutputStream(new File(filePath));
    }



    /**
     * @Title:
     * @Description:   将文件名切割，以UUID重命名后返回
     * @param originalFilename
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/26 02:53:35
     */
    public static String getUUIDName(String originalFilename){
        if(StringUtils.isNotTrimBlank(originalFilename))
            return UUIDUtils.getUUID() + getFileSuffix(originalFilename);
        return null;
    }

    /**
     * @Title:
     * @Description:   取出文件后缀
     * @param originalFilename
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/03/26 04:08:55
     */
    public static String getFileSuffix(String originalFilename){
        if(StringUtils.isNotTrimBlank(originalFilename))
            return originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        return null;
    }

    /**
     * @Title:
     * @Description: 将文件路径拼装起来 ，并判断是否多加了 /
     * @param val1
     * @param val2
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/05/14 06:16:31
     */
    public static String joinFilePath(String val1,String val2){
        if(StringUtils.isNotTrimBlank(val1,val2)){
            if(val1.endsWith(SLANT_LINE) && !val2.startsWith(SLANT_LINE)){
                return val1 + val2;
            } else if(!val1.endsWith(SLANT_LINE) && val2.startsWith(SLANT_LINE)){
                return val1 + val2;
            } else if(!val1.endsWith(SLANT_LINE) && !val2.startsWith(SLANT_LINE)){
                return val1 + SLANT_LINE + val2;
            } else if(val1.endsWith(SLANT_LINE) && val2.startsWith(SLANT_LINE)){
                return val1 + val2.substring(1);
            } else{
                return null;
            }
        }
        return null;
    }


    /**
     * @Title:
     * @Description: 将文件路径拼装起来 ，并判断是否多加了 /
     * @param values
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2018/05/14 06:16:31
     */
    public static String joinFilePath(String ...values){
        String result = null;
        for(String value : values){
            if(result == null)
                result = value;
            else
                result = joinFilePath(result,value);
        }
        return result;
    }

}
