package com.yh.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * ************************************
 * 反射工具类
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class ReflectUtil {
	/**
	 * ************************************
	 * 利用反射获取指定对象的指定属性
	 * @param obj 目标对象
	 * @param fieldName 目标属性
	 * @return 目标属性的值
	 * ************************************
	 */
	public static Object getFieldValue(Object obj,String fieldName){
		Object result=null;
		Field field=ReflectUtil.getField(obj, fieldName);
		if (field!=null) {
			field.setAccessible(true);
			try {
				result=field.get(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * ************************************
	 * 利用反射获取指定对象里面的指定属性
	 * @param obj 目标对象
	 * @param fieldName 目标属性
	 * @return 目标字段
	 * ************************************
	 */
	public static Field getField(Object obj,String fieldName){
		Field field=null;
		for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
			try {
				field=clazz.getDeclaredField(fieldName);
				break;
			} catch (Exception e) {
			}
		}
		return field;
	}
	/**
	 * ************************************
	 * 利用反射设置指定对象的指定属性为指定的值
	 * @param obj 目标对象
	 * @param fieldName 目标属性
	 * @param fieldValue 目标值
	 * ************************************
	 */
	public static void setFieldValue(Object obj, String fieldName,String fieldValue){
		Field field=ReflectUtil.getField(obj, fieldName);
		if (field!=null) {
			try {
				field.setAccessible(true);
				field.set(obj, fieldValue);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * ************************************
	 * 获得超类的参数类型，取第一个参数类型 
	 * @param <T>
	 * @param clazz
	 * @return
	 * ************************************
	 */
	public static<T> Class<T> getClassGenricType(Class clazz){
		return (Class<T>) ((ParameterizedType)clazz.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
