// 云函数入口文件
const cloud = require('wx-server-sdk')

cloud.init()
const db = cloud.database();
// const currentTime=_getCurrentTime(new Date());

// 云函数入口函数
exports.main = async (event, context) => {
  var num = event.num
  // var page=event.page
  return await db.collection("baoshis")
    //  .skip(page)
    // .orderBy('_getCurrentTime','desc')
    .limit(num)
    .get()
}