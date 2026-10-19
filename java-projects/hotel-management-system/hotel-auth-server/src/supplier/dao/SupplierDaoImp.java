package supplier.dao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import server.DataConnection;
import server.TokenManager;
import server.TokenUnvalidException;
import supplier.bean.Supplier;

//开发一个类实现远程接口及远程方法，需要继承UnicastRemoteObject，使用UnicastRemoteObject去连接RMI系统。
//如果一个类继承与UnicastRemoteObject，那么它必须要提供一个构造函数并且声明抛出一个RemoteException对象。
//当这个函数调用了super(),就激活了UnicastRemoteObject中的构造方法，完成RMI的连接和远程对象的初始化。
public class SupplierDaoImp extends UnicastRemoteObject implements SupplierDao {
	private static final long serialVersionUID = 1L;
	public SupplierDaoImp() throws RemoteException {    //构造函数
		super();
	}
	
	DataConnection connection = new DataConnection();   //加载数据库驱动
	Connection conn = null;               
	//添加供应商信息的方法
	@Override 
	public void insertSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();      //创建与指定数据库的连接
		try {
			PreparedStatement statement = conn.prepareStatement("insert into tb_supplier values(?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, supplier.getSfzID());
			statement.setString(2, supplier.getName());
			statement.setString(3, supplier.getSex());
			statement.setString(4, supplier.getAge());
			statement.setString(5, supplier.getAddress());
			statement.setString(6, supplier.getPhone());
			statement.setString(7, supplier.getEmail());
			statement.setString(8, supplier.getBeiz());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override    // 查询供应商表中全部数据的方法
	public List<Supplier> selectSupplier(String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		List<Supplier> list = new ArrayList<Supplier>();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier"); // 执行查询语句
			while(rest.next()) {   // 循环遍历查询结果集
				Supplier supplier = new Supplier();    // 创建与数据表对应的JavaBean对象
				supplier.setId(rest.getInt(1));        // 应用查询结果设置对象属性
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // 按编号查询供应商信息的方法
	public Supplier selectSupplierbyid(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where id = " + id);
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}
	@Override    // 按客户地址查询供应商信息的方法
	public List<Supplier> selectSupplierByAddress(String address, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where sfzID = '" + address + "'");
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // 按客户名称查询供应商信息的方法
	public List<Supplier> selectSupplierByName(String name, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where name = '" + name + "'");
			while(rest.next()) {
				Supplier supplier = new Supplier();    // 创建与数据表对应的JavaBean对象
				supplier.setId(rest.getInt(1));        // 应用查询结果设置对象属性
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // 按客户名称和客户地址查询供应商信息的方法
	public List<Supplier> selectSupplierByNameAddress(String address, String name, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		Supplier supplier = new Supplier();
		conn = connection.getCon();
		List<Supplier> list = new ArrayList<Supplier>();
		try {
			Statement statement = conn.createStatement();
			ResultSet rest = statement.executeQuery("select * from tb_supplier where sfzID = '" + name + "' and name = '" + address + "'");
			while(rest.next()) {
				supplier.setId(rest.getInt(1));
				supplier.setSfzID(rest.getString("sfzID"));
				supplier.setName(rest.getString("name"));
				supplier.setSex(rest.getString("sex"));
				supplier.setAge(rest.getString("age"));
				supplier.setAddress(rest.getString("address"));
				supplier.setPhone(rest.getString("phone"));
				supplier.setEmail(rest.getString("email"));
				supplier.setBeiz(rest.getString("beiz"));
				list.add(supplier);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override    // 修改供应商信息的方法
	public void updateSupplier(Supplier supplier, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		try {
			String sql = "update tb_supplier set sfzID=?, name=?, sex=?, age=?, address=?, phone=?, email=?, beiz=? where id=?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, supplier.getSfzID());
			statement.setString(2, supplier.getName());
			statement.setString(3, supplier.getSex());
			statement.setString(4, supplier.getAge());
			statement.setString(5, supplier.getAddress()); 
			statement.setString(6, supplier.getPhone());
			statement.setString(7, supplier.getEmail());
			statement.setString(8, supplier.getBeiz());
			statement.setInt(11, supplier.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override    // 删除供应商信息的方法
	public void deleteSupplier(int id, String token) throws RemoteException, TokenUnvalidException {
		if(!TokenManager.istance.verify(token)) {  //验证令牌
			throw new TokenUnvalidException();
		}
		conn = connection.getCon();
		try {
			Statement statement = conn.createStatement();
			statement.executeUpdate("delete from tb_supplier where id = " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
