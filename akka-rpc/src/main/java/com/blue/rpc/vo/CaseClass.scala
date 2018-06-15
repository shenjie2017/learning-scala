package com.blue.rpc.vo

/**
  * @author shenjie
  * @version v1.0
  * @Description
  * @Date: Create in 15:24 2018/6/15
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

trait RemoteMessage extends Serializable
case class  RegisterWorker(val id:String,val memory:Int,val cores:Int) extends  RemoteMessage
case class HeartBeat(val workerId:String) extends RemoteMessage
case class Message(val msgType:Int, val msg:String) extends RemoteMessage
case class RegisteredWorker(val masterUrl:String) extends  RemoteMessage
case class SendMessage(val msg:String)

case object CheckTimeOutWorker
case object SendHeartBeat
