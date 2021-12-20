package com.yankaizhang.bootspark.spark

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object SparkApplication {

  private val log = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkSession.builder()
      .master("local[*]")
      .appName("ScalaApplication")
      .getOrCreate()

    log.info("hello! spark!")

    sparkSession.stop()
  }

}