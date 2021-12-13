package com.yankaizhang.bootspark.service;

/**
 * @author dzzhyk
 */
public interface ISparkService {

    /**
     * 获取一个RDD，其内容为用户-电影评分列表
     * RDD [ userId, (movieId, rating)... ]
     */
    void getUserMovieMatrix();

}
