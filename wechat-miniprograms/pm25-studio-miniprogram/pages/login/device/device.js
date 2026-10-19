Page({
  data: {
    phoneInfo: [],
    softInfo: [],
    screenInfo: [],
  },
  onLoad: function () {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        console.log(res)
        that.setData({
          phoneInfo: [
            { key: "手机型号", val: res.model },
            { key: "手机语言", val: res.language }, 
            { key: "微信版本", val: res.version },
            { key: "操作系统版本", val: res.system },
          ],
          softInfo: [
            { key: "是否允许微信使用麦克风", val: Boolean(res.microphoneAuthorized)},
            { key: "是否允许微信使用摄像头", val: Boolean(res.cameraAuthorized )},
            { key: "是否允许微信使用定位", val: Boolean(res.locationAuthorized)},
            { key: "是否打开微信通知开关", val: Boolean(res.notificationAuthorized)}
          ],
          screenInfo: [
            { key: "屏幕像素比", val: res.pixelRatio },
            { key: "屏幕尺寸", val: res.windowWidth + '×' + res.windowHeight }
          ]
        });
      }
    });
  }
});