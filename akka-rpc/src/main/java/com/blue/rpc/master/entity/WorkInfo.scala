package com.blue.rpc.master.entity

/**
  * @author shenjie
  * @version v1.0
  * @Description
  * @Date: Create in 15:31 2018/6/15
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

class WorkerInfo(val id:String,val memory:Int,val cores:Int) {

  //上次心跳更新的时间
  var lastHeartBeatTime: Long = 0

  //更新上次心跳的时间
  def updateLastHeartBeatTime() = {
    lastHeartBeatTime = System.currentTimeMillis
  }
}