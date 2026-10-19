package supplier.dao;

import java.rmi.*;
import java.util.List;
import supplier.bean.Supplier;

public interface SupplierDao extends Remote { //扩展接口
	//添加供应商信息的方法
	public void insertSupplier(Supplier supplier, String token) 
			throws RemoteException, TokenUnvalidException;
	//查询供应商表中全部数据的方法
	public List<Supplier> selectSupplier(String token) 
			throws RemoteException, TokenUnvalidException; 
	//按编号查询供应商信息的方法
	public Supplier selectSupplierbyid(int id, String token) 
			throws RemoteException, TokenUnvalidException;  
	//按客户地址查询供应商信息的方法
	public List<Supplier> selectSupplierByAddress(String address, String token) 
			throws RemoteException, TokenUnvalidException;   
	//按客户名称查询供应商信息的方法
	public List<Supplier> selectSupplierByName(String name, String token) 
			throws RemoteException, TokenUnvalidException; 
	//按客户名称和客户地址查询供应商信息的方法
	public List<Supplier> selectSupplierByNameAddress(String address, String name, 
			String token) throws RemoteException, TokenUnvalidException; 
	//修改供应商信息的方法
	public void updateSupplier(Supplier supplier, String token) 
			throws RemoteException, TokenUnvalidException;  
	//删除供应商信息的方法
	public void deleteSupplier(int id, String token) 
			throws RemoteException, TokenUnvalidException; 
}
