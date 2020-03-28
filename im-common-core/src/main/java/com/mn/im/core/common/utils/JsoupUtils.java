package com.mn.im.core.common.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.parser.XmlTreeBuilder;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsoupUtils {

    public static String getTagValue(String tag, String value){
        Document document = Jsoup.parse(value,"UTF-8",new Parser(new XmlTreeBuilder()));
        if(document != null){
            List<Element> elements = document.getElementsByTag(tag);
            if(ArrayUtils.isNotNullAndLengthNotZero(elements))
                return elements.get(0).text();
        }
        return null;
    }

    /**
     * @Title:
     * @Description:   XML转成Map
     * @param xml
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/04/25 05:02:37
     */
    public static Map<String, String> xmlToMap(String xml){
        if(StringUtils.isNotTrimBlank(xml)){
            Document document = Jsoup.parse(xml,"UTF-8",new Parser(new XmlTreeBuilder()));
            Elements elementsList = document.getAllElements();
            Iterator<Element> in = elementsList.iterator();
            Map<String, String> result = new HashMap<>();
            while(in.hasNext()){
                Element element = in.next();
                result.put(element.tagName(),element.text());
            }
            return result;
        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(getTagValue("string","<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<string xmlns=\"http://tempuri.org/\">162158772</string>"));
//    }

}
