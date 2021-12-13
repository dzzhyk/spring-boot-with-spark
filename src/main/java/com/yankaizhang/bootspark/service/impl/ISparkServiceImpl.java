package com.yankaizhang.bootspark.service.impl;

import com.yankaizhang.bootspark.InjectSparkContext;
import com.yankaizhang.bootspark.service.ISparkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author dzzhyk
 */
@Slf4j
@Service
public class ISparkServiceImpl implements ISparkService {

    @Override
    @InjectSparkContext(inputUrl = "mongodb://127.0.0.1:27017/movie-recommend.movies")
    public void getUserMovieMatrix() {

    }
}
