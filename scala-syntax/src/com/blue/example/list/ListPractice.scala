package com.blue.example.list

/**
  * @author shenjie
  * @version v1.0
  * @Description
  * @Date: Create in 9:23 2018/6/13
  * @Modifide By:
  **/

//      ┏┛ ┻━━━━━┛ ┻┓
//      ┃　　　　　　 ┃
//      ┃　　　━　　　┃
//      ┃　┳┛　  ┗┳　┃
//      ┃　　　　　　 ┃
//      ┃　　　┻　　　┃
//      ┃　　　　　　 ┃
//      ┗━┓　　　┏━━━┛
//        ┃　　　┃   神兽保佑
//        ┃　　　┃   代码无BUG！
//        ┃　　　┗━━━━━━━━━┓
//        ┃　　　　　　　    ┣┓
//        ┃　　　　         ┏┛
//        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛
//          ┃ ┫ ┫   ┃ ┫ ┫
//          ┗━┻━┛   ┗━┻━┛

object ListPractice {
  def main(args: Array[String]): Unit = {
    //创建一个List
    val lst0 = List(1, 7, 9, 8, 0, 3, 5, 4, 6, 2)
    //将lst0中每个元素乘以10后生成一个新的集合
    val lst1 = lst0.map(_ * 10)
    println(lst1)

    //将lst0中的偶数取出来生成一个新的集合

    val lst2 = lst0.filter(_ % 2 == 0)
    println(lst2)

    //将lst0排序后生成一个新的集合
    val lst3 = lst0.sorted
    println(lst3)

    //反转顺序
    val lst4 = lst0.sorted.reverse
    println(lst4)

    //将lst0中的元素4个一组,类型为Iterator[List[Int]]
    val lst5 = lst0.grouped(4)
    println(lst5)

    //将Iterator转换成List
    val lst6 = lst5.toList
    println(lst6)

    //将多个list压扁成一个List

    val lst7 = lst6.flatten
    println(lst7)

    val lines = List("hello tom hello jerry", "hello jerry", "hello kitty")
    //先按空格切分，在压平
    //    val lst8 = lines.map(_.split(" ")).flatten
    val lst8 = lines.flatMap(_.split(" "))
    println(lst8)

    //并行计算求和
    val sum1 = lst0.fold(0)(_ + _)
    println(sum1)

    //化简：reduce
    val sum2 = lst0.reduce(_ + _)
    println(sum2)

    //将非特定顺序的二元操作应用到所有元素

    val sum3 = lst0.reduce((x, y) => x + y)
    println(sum3)

    //安装特点的顺序
    val sum4 = lst0.reduceLeft(_ + _)
    println(sum4)

    //折叠：无初始值（无特定顺序）
    val sum5 = lst0.fold(0)(_ + _)
    println(sum5)

    //折叠：有初始值（无特定顺序）
    val sum6 = lst0.fold(10)(_ + _)
    println(sum6)

    //折叠：无初始值（有特定顺序）
    val sum7 = lst0.foldLeft(0)(_ + _)
    println(sum7)

    //折叠：有初始值（有特定顺序）
    val sum8 = lst0.par.foldLeft(10)(_ + _)
    println(sum8)


    //聚合
    val arr = List(List(1, 2, 3), List(3, 4, 5), List(2), List(0))
    val arr1 = arr.flatten
    println(arr1)

    val l1 = List(5, 6, 4, 7)
    val l2 = List(1, 2, 3, 4)
    //求并集
    println(l1 union l2)

    //求交集
    println(l1 intersect l2)

    //求差集
    println(l1 diff l2)
  }

}
