Page({
  data: {
    dataList: []
  },
  getchudi(num = 5, page = 0) {
    wx.cloud.callFunction({
      name:"getchudiList",
      data: {
        num: num,
        page: page
      }
    }).then(res => {
      // var oldData = res.result.dataList
      // var newData = oldData.concat(res.result.data)
      this.setData({
        dataList: this.data.dataList.concat(res.result.data)
      })
      console.log(res.result.data)
    })
  },
  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    var page = this.data.dataList.length
    this.getchudi(3, page)

  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getchudi()
  },

  //点击将阅读数增加1
  //1、获取点击的id和索引值
  //2、云函数进行更新操作
  //3、前端连后端，将数据传输给后端返回数据
  //4、重新渲染列表
  clickRow(res) {
    wx.showLoading({
      title: '数据加载中'
      // mask: true
    })
    var {
      id,
      idx
    } = res.currentTarget.dataset
    wx.cloud.callFunction({
      name: "newsUpList",
      data: {
        id: id
      }
    }).then(res => {
      var newData = this.data.dataList
      newData[idx].hits += 1
      this.setData({
        dataList: newData
      })
      wx.hideLoading()
    })
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
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})