/**
 * 0.0.0.1
 */
package com.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 第一个注解类
 * @author gaoqing
 * 2014-6-22
 */
@Retention(RetentionPolicy.RUNTIME)    //保留策略
@Target(ElementType.TYPE)			   //使用对象
@Documented							  //是否可以被文档化
public @interface FirstAnnotation {

	String value();
	

}
