package user;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import supplier.dao.SupplierDao;
import user.view.LoginFrame;

public class Client {
	public static String serverAddress = "127.0.0.1";  //"localhost"
	public static int authServerPort = 12345;
	public static int appServerPort = 12346;
	public static SupplierDao supplierDao;
	
	public static void main(String[] args) {
		//获取远程实例，并将其复制给静态变量，供其他的类直接访问
		try {
			supplierDao = (SupplierDao)Naming.lookup("rmi://" + serverAddress 
										+ ":" + appServerPort + "/supplierDao");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}				
		
		LoginFrame loginFrame = new LoginFrame();  //创建登录窗体
		loginFrame.setVisible(true);               //设置窗体可见
	}
}

