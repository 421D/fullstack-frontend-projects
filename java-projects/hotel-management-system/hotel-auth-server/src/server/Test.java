package server;

import java.sql.*;

public class Test {
	public static void main(String[] args) {
		DataConnection dataConnection = new DataConnection();
		
		Connection cn = dataConnection.getCon();  //获取数据库连接
		
		String sql = "SELECT * FROM tb_users where username = ? and passWord = ?";
		Object[] params = new Object[2];
		params[0] = "jsj161";
		params[1] = "123456";
		dataConnection.doPstm(sql, params);
		try {
			ResultSet rs = dataConnection.getRs();
			while(rs.next()) {
				System.out.println(rs.getString("username") + "\t" + rs.getString("passWord"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			System.out.print(dataConnection.getCount());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataConnection.closed();	
	}
}
