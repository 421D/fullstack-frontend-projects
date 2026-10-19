package server.dao;

import java.sql.*;
import server.DataConnection;
import user.bean.User;

public class UserDaoImp implements UserDao {
	//当需要和数据源进行交互的时候则使用这个接口，并且编写一个单独的类来实现这个接口在逻辑上对应这个特定的数据存储。
	//SELECT * FROM tb_users WHERE username = user.getUserName() and passWord = user.getPassWord()
	DataConnection dataConnection = new DataConnection();  //加载数据库驱动
	Connection con = dataConnection.getCon();   //创建与指定数据库的连接
	@Override
	public User getUser(String userName, String passWord) {
		User user = null;  //局部变量一定要先初始化，再使用
		try {
			//执行具体的sql语句  Statement  PreparedStatement  ResultSet
			String sql = "SELECT * FROM tb_users WHERE username = ? and passWord = ?";//定义查询语句
			PreparedStatement pst = con.prepareStatement(sql);  //实例化PreparedStatement对象
			pst.setString(1, userName);           //设置预处理语句的参数
			pst.setString(2, passWord);
			ResultSet rest = pst.executeQuery();   //执行预处理语句
			while(rest.next()) {
				user = new User();
				user.setId(rest.getInt(1));         //应用查询结果设置对象属性
				user.setUserName(rest.getString(2));
				user.setPassWord(rest.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;         //返回查询结果
	}
}

//UserDaoImp userDaoImp = new UserDaoImp();
//userDaoImp.getUser(userName, passWord);
//userDaoImp.getUser(admin, admin);
