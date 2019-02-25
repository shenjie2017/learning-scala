package com.blue.example.bigint

import java.math.BigInteger

/**
  * @Author: Jason
  * @E-mail: 1075850619@qq.com
  * @Date: create in 2019/2/25 14:41
  * @Modified by: 
  * @Project: learning-scala
  * @Package: com.blue.example.bigint
  * @Description:
  */
object BigIntegerCase {

  def factoral_scala(x: BigInt): BigInt =
    if (x == 0) 1 else x * factoral_scala(x - 1)

  def factoral_java(x: BigInteger): BigInteger = {
    if (x == BigInteger.ZERO) BigInteger.ONE else x.multiply(factoral_java(x.subtract(BigInteger.ONE)))
  }

  def main(args: Array[String]): Unit = {
    println(factoral_scala(30))
    println(factoral_java(new BigInteger("30")))
  }
}
