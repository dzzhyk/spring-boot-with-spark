package com.yankaizhang.bootspark.controller;

import com.yankaizhang.bootspark.service.ISparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dzzhyk
 */
@RestController
public class MySparkController {

    @Autowired
    ISparkService sparkService;

    public String testGetUserMovieMatrix(){



        return "testGetUserMovieMatrix";
    }

}
