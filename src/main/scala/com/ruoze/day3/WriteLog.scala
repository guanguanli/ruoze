package com.ruoze.day3

import java.io.{File, PrintWriter}

object WriteLog {


  def main(args: Array[String]): Unit = {

    var  writer =new PrintWriter(new File("config/log.txt"))
    for(i <- 1 to 100000)
      {
       var contextString= getLineContext
        writer.println(contextString)
      }
    writer.close()
  }


  def getLineContext(): StringBuilder =
  {
    var line=new StringBuilder

    //domain
    var domainarr=List("www.ruozedata.com","www.zhibo8.com","www.dongqiudi.com")
    var randomNum=(new util.Random()).nextInt(2)
    var domain=domainarr(randomNum)
    line.append(domain+"\t")


    //trafficd
    var trafficd=(new util.Random()).nextInt(60000)
    var sj=(new util.Random()).nextInt(10)
    if(sj==1)
      {
        //var base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        line.append((new util.Random()).nextString(3) +"\t")
      }else
      {
        line.append(trafficd+"\t")
      }



    //time
    var time1=10+(new util.Random()).nextInt(14)
    var time2=10+(new util.Random()).nextInt(50)

    line.append("[2018-07-"+time1+" "+time1+":"+time2+":"+time1+"]")


    line
  }


}
