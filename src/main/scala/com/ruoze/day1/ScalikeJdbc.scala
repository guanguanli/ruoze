package com.ruoze.day1


import scalikejdbc.DB
import scalikejdbc.SQL

object ScalikeJdbc {


  case class User(id:Int,name:String,age:Int)

  def main(args: Array[String]): Unit = {

    //解析配置文件
    scalikejdbc.config.DBs.setupAll()

    DB autoCommit { implicit session =>
      SQL("create table user ( id int(20) not null AUTO_INCREMENT, name varchar(10), age INT(3),  primary key (id))").execute.apply()
    }

    insertBatch("zhangsan",11)

  }



  //在数据插入的时候建立一个事务。
  def insertBatch( name: String, age : Int) =
    DB.localTx { implicit session =>
    SQL("insert into user(name,age) values(?,?)").bind(name, age).update().apply()
  }



  def delete(id:Int) = {
    DB.autoCommit { implicit session =>
      SQL("delete from user where id = ?").bind(id).update().apply()
    }
  }


  def update(name:String,age:Int) {
    DB.autoCommit { implicit session =>
      SQL("update user set age = ? where name = ?").bind(age, name).update().apply()
    }
  }

  //select查询到数据之后会产生一个rs的对象集，然后可以得到这个对象集里面的数据。
  def select() = {
    DB.readOnly { implicit session =>
      SQL("select * from user").map(rs => User(rs.int("id"),rs.string("name"), rs.int("age"))).list().apply()
    }
  }



  }


