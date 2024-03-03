package com.blue.scala

object ExceptionDemo {

  def func1():Unit = {
    1/0
  }
  def main(args: Array[String]): Unit = {
    try{
      1/0
    }catch {
      case ex:Exception => println("cause exception1")
    }

    try{
      func1()
    }catch {
    case ex:Exception => println("cause exception2")
    }
  }
}
