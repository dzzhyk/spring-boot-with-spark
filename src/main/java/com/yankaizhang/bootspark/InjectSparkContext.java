package com.yankaizhang.bootspark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dzzhyk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InjectSparkContext {
    String master() default "local[*]";
    String appName() default "movielikes";

    /**
     * mongodb数据库连接参数，输入url
     * eg: mongodb://127.0.0.1:27017/test.myCollection
     */
    String inputUrl();

    String outputUrl() default "mongodb://127.0.0.1:27017/spark-output.test";
}