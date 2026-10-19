package server;

import java.sql.*;

public class DataConnection {
	private String className = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost:1433;DatabaseName = hotel";
	private String user = "sa";
	private String password = System.getenv().getOrDefault("HOTEL_DB_PASSWORD", "");  // set HOTEL_DB_PASSWORD
	private Connection cn;
	private PreparedStatement pstm;
	//加载数据库驱动
	public DataConnection() {
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			System.out.println("加载数据库驱动失败!");
			e.printStackTrace();
		}
	}
	
	//获取数据库连接
	public Connection getCon() {
		try {
			cn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("建立数据库连接失败!");
			e.printStackTrace();
		}
		return cn;  //返回数据库连接对象
	}
	
	
	//执行SQL语句(其中params为传递的参数值数组)
	public void doPstm(String sql, Object[] params) {
		if(sql != null && !sql.equals("")) {
			if(params == null) {
				params = new Object[0];
			}
			getCon();
			try {
				pstm = cn.prepareStatement(sql);
				for(int i = 0; i < params.length; i++) {  //用于设置参数
					pstm.setObject(i+1, params[i]);
				}
				pstm.execute();
			} catch (SQLException e) {
				System.out.println("doPstm()方法出错！");
				e.printStackTrace();
			}
		}
	}
	
	//返回查询结果
	public ResultSet getRs() throws SQLException {
		return pstm.getResultSet();
	}
	
	//获取受影响的数据行数
	public int getCount() throws SQLException {
		return pstm.getUpdateCount();	
	}
	
	//关闭数据库连接
	public void closed() {
		try {
			if(cn != null) {
				cn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭Connection对象失败");
			e.printStackTrace();
		}
		
		
		try {
			if(pstm != null) {
				pstm.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭PrepardeStatement对象失败");
			e.printStackTrace();
		}	
	}
}
