package com.blue.rpc.master

import akka.actor.{Actor, ActorSystem, Props}
import com.blue.rpc.master.entity.WorkerInfo
import com.blue.rpc.vo._
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._

/**
  * @author shenjie
  * @version v1.0
  * @Description
  * @Date: Create in 14:44 2018/6/15
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


class Master(val host:String,val port:Int) extends Actor{

  val CHECK_INTERVAL : Int =10000

  //workerId->workerInfo
  val workInfos=new scala.collection.mutable.HashMap[String,WorkerInfo]

  //为了便于一些额外的逻辑，比如按Worker的剩余可用memory进行排序
  val workers= new scala.collection.mutable.HashSet[WorkerInfo]

  override def preStart(): Unit = {
    //使用schedule必须导入该扩展的隐式变量
    import context.dispatcher
    //millis是毫秒的单位，其在包scala.concurrent.duration下
    context.system.scheduler.schedule(0 millis,CHECK_INTERVAL millis,self,CheckTimeOutWorker)

    println("master start success!!")
  }

  override def receive = {
    case CheckTimeOutWorker=>{ //定时检测是否有超时的worker并进行处理
      val cur=System.currentTimeMillis
      //过滤出超时的worker
      val deadWorker= workers.filter(x=>cur-x.lastHeartBeatTime>CHECK_INTERVAL)
      //从记录删除删除超时的worker
      for(w <- deadWorker) {
        workInfos -= w.id
        workers -= w
        println("remove workerId "+w.id)
      }
      println(workers.size)
    }
    case RegisterWorker(id,memory,cores)=> {//注册消息
      if(!workInfos.contains(id)){
        val work = new WorkerInfo(id,memory,cores)
        workInfos.put(id,work)
        workers += work

        //这里简单发生Master的url通知worker注册成功
        sender ! RegisteredWorker(s"akka.tcp://MasterSystem@$host:$port/user/Master")
      }
    }
    case HeartBeat(workerId)=>{ //处理Worker的心跳
      if(workInfos.contains(workerId)){
        workInfos(workerId).updateLastHeartBeatTime()

        sender ! Message(3,"received heart")
      }
    }
    case Message(1,msg)=>{ //接收消息
      println("receive msg:"+msg)
      sender ! Message(2,"world")
    }
    case Message(msgType,msg)=>{
      //other message
    }
  }
}

object Master{
  def main(args: Array[String]): Unit = {
      val host = args(0)
      val port = args(1).toInt

    //构造配置参数值，使用3个双引号可以多行，使用s可以在字符串中使用类似Shell的变量
      val configStr =
        s"""
           |akka.actor.provider = "akka.remote.RemoteActorRefProvider"
           |akka.remote.netty.tcp.hostname = "$host"
           |akka.remote.netty.tcp.port = "$port"
         """.stripMargin

    //通过工厂方法获得一个config对象
    val config = ConfigFactory.parseString(configStr)

    //初始化一个ActorSystem，其名为MasterSystem
    val actorSystem = ActorSystem("MasterSystem",config)

    //使用actorSystem实例化一个名为Master的actor,注意这个名称在Worker连接Master时会用到
    val master = actorSystem.actorOf(Props(new Master(host,port)),"Master")

    //阻塞当前线程直到系统关闭退出
    actorSystem.whenTerminated
//    actorSystem.awaitTermination
  }
}
