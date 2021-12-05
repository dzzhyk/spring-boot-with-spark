package com.yankaizhang.bootspark

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import org.slf4j.LoggerFactory

object WordCount{

  private val log = LoggerFactory.getLogger(WordCount.getClass)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wordcount").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 读取数据
    val fileLines: RDD[String] = sc.textFile("data/input/words.txt")

    // 数据操作/转换
    val words: RDD[String] = fileLines.flatMap(_.split(" "))
    val wordAndOnes: RDD[(String, Int)] = words.map((_, 1))



  }
}