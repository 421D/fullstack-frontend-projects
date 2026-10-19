// pages/index/index-detail/index-detail.js
var g_app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var postId = options.id;
    var that = this;
    var picturesUrl = g_app.globalData.picList + "/shop/goods/detail?id=" + postId
    console.log(picturesUrl)
    this.getpicData(picturesUrl)
  },
  getpicData: function (url) {
    var that = this;
    wx.request({
      url: url,
      method: "GET",
      header: {
        "content-type": "application/xml"
      },
      success: function (res) {
        console.log("111")
        console.log(res.data)
        that.processpicData(res.data);
      },
      fail: function (res) {

      },
      complete: function (res) { },
    })
  },
  processpicData: function (pics) {
    var pictures = [];
    console.log("3333")
    console.log(pics.data.basicInfo)
    for (var idx in pics.data) {
      var name = pics.data.basicInfo.name;
      wx.setNavigationBarTitle({
        title: name
      })
      var pic = pics.data.basicInfo.pic;
      var categoryId = pics.data.basicInfo.categoryId;
      var id = pics.data.basicInfo.id;
      
      var temp = {
        name: name,
        pic: pic,
        categoryId: categoryId,
        id: id
      }

      pictures.push(temp);
      
    }
    console.log(pictures)
    var readyData = {};
    readyData = {
      pictures: pictures
    };
    this.setData(readyData);
    console.log("2222")
    console.log(readyData)
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})