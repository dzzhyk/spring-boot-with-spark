package com.yankaizhang.bootspark;


import com.mongodb.spark.MongoSpark;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Arrays;

/**
 * spark-mongodb的controller需要实现序列化接口
 */
@RestController
public class SparkController implements Serializable {


    @Autowired
    transient MongoTemplate mongoTemplate;


    @GetMapping("/movies")
    @InjectSparkContext(inputUrl = "mongodb://127.0.0.1:27017/movie-recommend.movies")
    public String testMovies(JavaSparkContext javaSparkContext) {
        int a = 1/0;
        return "movies";
    }

    @GetMapping("/testWriteMongo")
    @InjectSparkContext(inputUrl = "mongodb://127.0.0.1:27017/movie-recommend.movies")
    public String testWriteMongo(JavaSparkContext javaSparkContext) {
        // 测试创建一个数组
        // 需要convert the data into a Document (or BsonDocument or a DBObject)
        JavaRDD<Document> documents = javaSparkContext.parallelize(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)).map
                (new Function<Integer, Document>() {
                    @Override
                    public Document call(final Integer i) throws Exception {
                        return Document.parse("{test: " + i + "}");
                    }
                });

        // 创建 WriteConfig
        // 可用参数：https://docs.mongodb.com/spark-connector/current/configuration/#std-label-spark-output-conf
        // Map<String, String> writeOverrides = new HashMap<>();
        // WriteConfig writeConfig = WriteConfig.create(javaSparkContext).withOptions(writeOverrides);

        // spark写入数组到mongodb
        MongoSpark.save(documents);
        return "testWriteMongo";
    }
}
