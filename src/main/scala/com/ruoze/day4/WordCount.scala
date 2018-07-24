package com.ruoze.day4

import scala.io.Source

object WordCount {

  def main(args: Array[String]): Unit = {
    var log=Source.fromFile("config/log.txt").getLines().toList
    var logmap=log.flatMap(_.split("\t")).map((_,1)).groupBy(_._1).mapValues(_.map((_._2)).reduce(_+_))

    println(logmap)
  }

}
