package com.yankaizhang.bootspark;

import java.lang.annotation.*;

/**
 * 为方法注入Spark-MongoDB连接的上下文
 * @author dzzhyk
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
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
