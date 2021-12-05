package com.yankaizhang.bootspark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC配置
 * @author dzzhyk
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    SparkContextArgumentResolver sparkContextArgumentResolver;

    /**
     * 自定义参数解析配置
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sparkContextArgumentResolver);
    }
}
