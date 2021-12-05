package com.yankaizhang.bootspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * JavaSparkContext自定义参数解析器，为方法上下文创建JavaSparkContext对象参数
 * @author dzzhyk
 */
@Component
public class SparkContextArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 直解析含有InjectSparkContext注解的方法中的JavaSparkContext参数
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasMethodAnnotation(InjectSparkContext.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        InjectSparkContext injectSparkContext = parameter.getMethodAnnotation(InjectSparkContext.class);
        assert injectSparkContext != null;
        String master = injectSparkContext.master();
        String appName = injectSparkContext.appName();
        String inputUrl = injectSparkContext.inputUrl();
        String outputUrl = injectSparkContext.outputUrl();
        System.out.println(master + appName + inputUrl + outputUrl);

        SparkConf conf = new SparkConf()
                .setMaster(master)
                .setAppName(appName)
                .set("spark.mongodb.input.uri", inputUrl)
                .set("spark.mongodb.output.uri", outputUrl);

        JavaSparkContext context = new JavaSparkContext(conf);
        return context;
    }
}
