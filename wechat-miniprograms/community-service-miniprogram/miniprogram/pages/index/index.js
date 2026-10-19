const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    banner: [{
        picUrl: '/images/tab1.png'
      },
      {
        picUrl: '/images/tab2.png'
      }
    ],
    goodList:[]
  },
  //页面可见
  onShow() {
    this.getTopBanner(); //请求顶部轮播图
    this.getHotGood()
  },
  //轮播图点击事件

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
  goFabu() {
    wx.navigateTo({
      url: '/pages/fabu/fabu'
    })
  },
  goToMall() {
    wx.navigateTo({
      url: '/pages/mall/mall'
    })
  },
  goWuye() {
    wx.navigateTo({
      url: '/pages/news/news'
    })
  },
  goKefu() {
    wx.navigateTo({
      url: '/pages/kefu/kefu'
    })
  },
  // goDetail(e) {
  //   wx.navigateTo({
  //     url: '/pages/detail/detail?goodid=' + e.currentTarget.dataset.id
  //   })
  // },
    // 跳转确认订单页面
    gotoOrder: function (e) {
      // var arr = wx.getStorageSync('cart') || [];
      // if (!arr || arr.length == 0) {
      //   wx.showModal({
      //     title: '提示',
      //     content: '请选择商品'
      //   })
      //   return;
      // }
  
      // let userInfo = app.globalData.userInfo;
      // if (!userInfo || !userInfo.nickName) {
      //   this.showLoginView()
      //   return;
      // }
      wx.navigateTo({
        url: '/pages/mall/mall?goodid=' + e.currentTarget.dataset.id
      })
    },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },
  //获取首页顶部轮播图
  getTopBanner() {
    wx.cloud.database().collection("lunbotu")
      .get()
      .then(res => {
        console.log("首页banner成功", res.data)
        if (res.data && res.data.length > 0) {
          //如果后台配置轮播图就用后台的，没有的话就用本地默认的
          this.setData({
            banner: res.data
          })
        }
      }).catch(res => {
        console.log("首页banner失败", res)
      })
  },
  //获取首页推荐位的商品
  getHotGood() {
    wx.cloud.callFunction({
      name: "getGoodList",
      data: {
        action: 'getHot'
      }
    }).then(res => {
      console.log("首页推荐商品数据", res.result)
      this.setData({
        goodList: res.result.data,
      })
    }).catch(res => {
      console.log("数据请求失败", res)
    })
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