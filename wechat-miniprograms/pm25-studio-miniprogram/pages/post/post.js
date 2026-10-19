// pages/post/post.js
import {
  DBPost
} from '../../db/DBPost.js';
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    
    articles:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    // var dbPost = new DBPost();
    // this.setData({
    //   postList: dbPost.getAllPostData()
    // });
    var that = this;
    var articlesUrl = app.globalData.picList + "/cms/news/list"

    this.getNewsData(articlesUrl)

  },

  getNewsData: function (url) {
    var that = this;
    wx.request({
      url: url,
      method: "GET",
      header: {
        "content-type": "application/xml"
      },
      success: function (res) {
        that.processArticleData(res.data);
        console.log(res.data)
      },
      fail: function (res) {

      }, complete: function (res) { },

    })
  },

  processArticleData: function (artic) {
    var articles = [];
    for (var idx in artic.data) {
      var title = artic.data[idx].title;
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
    var readyData = {
      articles: articles
    };
    this.setData(readyData)

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  },
  onTapToDetail(event) {
    var postId = event.currentTarget.dataset.id;
    console.log(postId)
    wx.navigateTo({
      url: 'post-detail/post-detail?id=' + postId,
    })
  },

})