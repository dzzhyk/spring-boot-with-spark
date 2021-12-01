package com.yankaizhang.bootspark

import java.util.{Date, Locale}
import java.text.DateFormat

object ScalaApplication {
  def main(args: Array[String]): Unit = {
    println("hello!")
    val now = new Date
    val df = DateFormat.getDateInstance(DateFormat.LONG, Locale.CHINA)
    println(df.format(now))

    val circle = new Circle(20)
    println(circle.square)
    println(circle.perimeter)
    println(circle)
    println(circle.show())

    for (a <- 1 to 10; b <- 2 to 6) {
      println(a * b)
    }

    var c = 0
    while (c < 10) {
      println(c)
      c += 1

    }

    var d = 0
    do {
      println(d)
      d += 1
    }while(d < 10)
  }
}

object Timer {
  def oncePerSecond(callback: () => Unit) {
    while (true) {
      callback();
      Thread.sleep(1000)
    }
  }

  def timeFlies() {
    println("time flies like an arrow...")
  }

  def main(args: Array[String]) {
    oncePerSecond(timeFlies)
  }
}

class Circle(r: Double) extends Shape {
  override def square: Double = r * r * 3.1415926

  override def perimeter: Double = 2 * r * 3.1415926

  override def toString: String = "Circle(r=" + r + ")"

  override def show(): String = {
    return "I'm circle"
  }
}

abstract class Shape {
  def square: Double

  def perimeter: Double

  def show(): String
}

trait IPrint {

}