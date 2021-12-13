package com.yankaizhang.bootspark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dzzhyk
 */
@EnableScheduling
@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
public class BootSparkApplication {

    @Bean
    public JavaSparkContext javaSparkContext(){
        SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("movielikes")
                .set("spark.mongodb.input.uri", "mongodb://127.0.0.1:27017/movie-recommend.movies")
                .set("spark.mongodb.output.uri", "mongodb://127.0.0.1:27017/spark-output.test");
        return new JavaSparkContext(conf);
    }

    public static void main(String[] args) {
        SpringApplication.run(BootSparkApplication.class, args);
    }

}
