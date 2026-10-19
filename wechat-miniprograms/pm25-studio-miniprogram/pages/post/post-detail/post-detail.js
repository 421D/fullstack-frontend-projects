var g_app = getApp();
// var g_app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //控制使用键盘还是发送语音
    useKeyboardFlag: true,
    //控制input组件的初始值
    keyboardInputValue: '',
    //控制是否显示图片选择面板
    sendMoreMsgFlag: false,
    //保存已选择的图片
    chooseFiles: [],
    //保存当前正播放语音的URL
    currentAudio: ''

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var postId = options.id;
    console.log(postId)
    var that = this;
    var articlesUrl = g_app.globalData.articleList + "/cms/news/list"
    console.log(articlesUrl)
    this.getarticlesData(articlesUrl, postId)
  },
  
  getarticlesData: function (url, postId) {
    var that = this;
    wx.request({
      url: url,
      method: "GET",
      header: {
        "content-type": "application/xml"
      },
      success: function (res) {
        console.log(res.data)
        that.processarticlesData(res.data, postId);
      },
      fail: function (res) {

      },
      complete: function (res) { },
    })
  },
  processarticlesData: function (artic, postId) {
    var articles = [];
    console.log(artic.data)
    for (var idx in artic.data) {
      if (artic.data[idx].id == postId) {
        var title = artic.data[idx].title;
        wx.setNavigationBarTitle({
          title: title
        })
        var pic = artic.data[idx].pic;
        var dateAdd = artic.data[idx].dateAdd;
        var id = artic.data[idx].id;
        var descript = artic.data[idx].descript;
        var keywords = artic.data[idx].keywords;
        var paixu = artic.data[idx].paixu;
        var postId = parseInt(idx)+1;
        var temp = {
          postId:postId,
          title: title,
          pic: pic,
          dateAdd: dateAdd,
          id: id,
          descript: descript,
          keywords: keywords,
          paixu: paixu
        }

        articles.push(temp);
      }

    }
    console.log(articles)
    var readyData = {};
    readyData = {
      articles: articles
    };
    this.setData(readyData);

  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */

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

  },
  onCommentTap: function (event){
    var postId = event.currentTarget.dataset.id;
    console.log(postId)
    wx.navigateTo({
      url: '../post-comment/post-comment?id=' + postId,
    })
  }


})