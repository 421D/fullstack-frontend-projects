package server.dao;

import user.bean.User;

public interface UserDao {
	//将所有对数据源的访问操作
	//SELECT * FROM tb_users WHERE username = user.getUserName() and passWord = user.getPassWord()
	public User getUser(String userName, String passWord);
}
