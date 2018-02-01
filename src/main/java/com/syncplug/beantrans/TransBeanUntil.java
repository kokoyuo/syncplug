package com.syncplug.beantrans;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/11.
 */
public class TransBeanUntil {
    public static void translate(Object targetBean,Object valueBean) throws IllegalAccessException {

        List<Field> targetFieldList = Arrays.asList(targetBean.getClass().getDeclaredFields());
        Map<String,Field> targetFieldMap = new HashMap<>();
        for(int b=0;b<targetFieldList.size();b++){
            targetFieldList.get(b).setAccessible(true);
            targetFieldMap.put(targetFieldList.get(b).getName(),targetFieldList.get(b));
        }

        List<Field> valueFieldList = Arrays.asList(valueBean.getClass().getDeclaredFields());
        Map<String,Field> valueFieldMap = new HashMap<>();
        for(int b=0;b<valueFieldList.size();b++){
            valueFieldList.get(b).setAccessible(true);
            valueFieldMap.put(valueFieldList.get(b).getName(),valueFieldList.get(b));
        }

        Field targetField;
        for(int i=0;i<valueFieldList.size();i++){
            Field valueField = valueFieldList.get(i);
            valueField.setAccessible(true);
            /**判断是否使用了TransFiled注解*/
            if(valueField.isAnnotationPresent(TransFiled.class)){
                /**获取所有注解*/
                Annotation anno = valueField.getAnnotation(TransFiled.class);
                    //System.out.println(((CeopFiled)anno).value());
                targetField = targetFieldMap.get(((TransFiled) anno).value());
                    try{
                        if(targetFieldMap.get(((TransFiled)anno).value())!=null && valueField.get(valueBean) != null)
                        {
                            targetField.set(targetBean,getFieldTypeValue(targetField,valueField,valueBean));
                        }
                    }catch (Exception e){
                        throw e;
                    }
            }
        }
    }

    private static Object getFieldTypeValue(Field targetField,Field valueField,Object obj) throws IllegalAccessException {
        if("String".equalsIgnoreCase(targetField.getType().getSimpleName()) && ("int".equalsIgnoreCase(valueField.getType().getSimpleName())))
        {
            valueField.setAccessible(true);
            return valueField.get(obj).toString();
        }else if("Integer".equalsIgnoreCase(targetField.getType().getSimpleName()) && "String".equalsIgnoreCase(valueField.getType().getSimpleName()))
        {
            valueField.setAccessible(true);
            String s = (String)valueField.get(obj);
            return Integer.valueOf(s);
        }else if("String".equalsIgnoreCase(targetField.getType().getSimpleName()) && "Integer".equalsIgnoreCase(valueField.getType().getSimpleName()))
        {
            valueField.setAccessible(true);
            Integer s = (Integer) valueField.get(obj);
            return s.toString();
        }else if("int".equalsIgnoreCase(targetField.getType().getSimpleName()) && "String".equalsIgnoreCase(valueField.getType().getSimpleName()))
        {
            valueField.setAccessible(true);
            int s = Integer.valueOf((String) valueField.get(obj)).intValue();
            return s;
        } else if("BigDecimal".equalsIgnoreCase(valueField.getType().getSimpleName()) && !"BigDecimal".equalsIgnoreCase(targetField.getType().getSimpleName()))
        {
            if("int".equalsIgnoreCase(targetField.getType().getSimpleName()))
            {
                BigDecimal bd = (BigDecimal) valueField.get(obj);
                return bd.intValue();
            }else if("double".equalsIgnoreCase(targetField.getType().getSimpleName()))
            {
                BigDecimal bd = (BigDecimal)valueField.get(obj);
                return bd.doubleValue();
            }
            return valueField.get(obj);
        }

        return valueField.get(obj);
    }

    public static void main(String[] args)
    {
        SimpleValueBean valueBean = new SimpleValueBean();
        valueBean.setName("myName");
        valueBean.setAge("18");
        valueBean.setMoney(new BigDecimal(100.00));
        SimpleTargetBean targetBean = new SimpleTargetBean();
        try {
            translate(targetBean,valueBean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(targetBean.getUserName()+targetBean.getUserAge()+targetBean.getMoney());

    }
}
