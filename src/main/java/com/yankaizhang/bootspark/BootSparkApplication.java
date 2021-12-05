package com.yankaizhang.bootspark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

    public static void main(String[] args) {
        SpringApplication.run(BootSparkApplication.class, args);
    }

}
