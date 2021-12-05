package com.yankaizhang.bootspark;

import lombok.extern.slf4j.Slf4j;
import org.apache.spark.api.java.JavaSparkContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SparkAspect {


    @Pointcut("@annotation(com.yankaizhang.bootspark.InjectSparkContext)")
    public void pointCut() {
    }

    @AfterReturning("pointCut()")
    public void closeSparkContext(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof JavaSparkContext){
                JavaSparkContext context = (JavaSparkContext) arg;
                log.info("尝试关闭JavaSparkContext -- master: {}, appName: {}, conf: {}", context.master(),context.appName(), context.getConf());
                context.close();
            }
        }
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "ex")
    public void exceptSparkContext(JoinPoint joinPoint, Exception ex){
        log.error("SparkContext上下文使用异常: {}", ex.getMessage());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof JavaSparkContext){
                JavaSparkContext context = (JavaSparkContext) arg;
                log.info("尝试关闭JavaSparkContext -- master: {}, appName: {}, conf: {}", context.master(),context.appName(), context.getConf());
                context.close();
            }
        }
    }
}
