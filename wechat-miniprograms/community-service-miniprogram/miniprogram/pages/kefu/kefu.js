Page({
  onLoad() {
    wx.cloud.database().collection('huishou').get()
      .then(res => {
        console.log('物业联系人数据', res)
        this.setData({
          list: res.data
        })
      }).catch(res => {
        console.log('物业联系人数据获取失败', res)
      })
  },
  //拨打物业电话
  goPone(e) {
    console.log(e.currentTarget.dataset.phone)
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.phone
    })
  },
  //复制物业微信
  goWeiXin(e) {
    let weixin = e.currentTarget.dataset.weixin
    wx.setClipboardData({
      data: weixin,
      success(res) {
        wx.showToast({
          title: '已复制',
        })
      }
    })
  }
})