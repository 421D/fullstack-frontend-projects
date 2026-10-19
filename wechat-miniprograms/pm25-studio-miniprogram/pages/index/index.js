import { DBPost } from '../../db/DBPost.js';
var app = getApp();
var util = require("../../util/util.js");
Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    pictures:{}
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var picturesUrl = app.globalData.picList +"/shop/goods/list"
    var categoryurl = app.globalData.picList + "/shop/goods/category/all"
    this.getNewsData(picturesUrl)     
    // this.setData({
    //   categoryId : "79586"
    // })
  },
  
  getNewsData: function (url){
    var that=this;
    wx.request({
      url: url,
      method:"GET",
      header:{
        "content-type":"application/xml"
      },
      success:function(res){
        that.processPicData(res.data);
      },
      fail:function(res){

      },complete:function(res){},

    })
  },
  processPicData: function (pics){
    var pictures = [];
    for(var idx in pics.data){
      var name = pics.data[idx].name;
      var pic = pics.data[idx].pic;
      var categoryId = pics.data[idx].categoryId;
      var id = pics.data[idx].id;
      var temp = {
        name: name,
        pic: pic,
        categoryId: categoryId,
        id:id
      }
      pictures.push(temp);
    }
   
    var readyData = {
      pictures: pictures
    };
    this.setData(readyData)
  },
  processCateData: function (pics) {
    var pictures = [];
    for (var idx in pics.data) {
      var name = pics.data[idx].name;
      var pic = pics.data[idx].pic;
      var categoryId = pics.data[idx].categoryId;
      var id = pics.data[idx].id;
      var temp = {
        name: name,
        pic: pic,
        categoryId: categoryId,
        id: id
      }
      pictures.push(temp);
    }

    var readyData = {
      pictures: pictures
    };
    this.setData(readyData)
    console.log("ssss")
    console.log(readyData)
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

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
    var postId = event.currentTarget.dataset.postId;
    wx.navigateTo({
      url: 'index-detail/index-detail?id=' + postId,
    })
  },

  switchRightTab: function (event) {
    var id = event.target.dataset.categoryId;
    var url = app.globalData.picList + "/shop/goods/list?categoryId=" + id    
    util.http(url, this.processCateData)
  },



})