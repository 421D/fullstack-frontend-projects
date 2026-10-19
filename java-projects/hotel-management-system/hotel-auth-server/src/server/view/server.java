package server.view;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import server.TokenManager;
import server.dao.UserDaoImp;
import supplier.dao.SupplierDao;
import supplier.dao.SupplierDaoImp;
import user.bean.User;

public class server {
	public static void main(String[] args) {
		ServerFrame serverFrame = new ServerFrame();
		serverFrame.setVisible(true);
		
		//启动服务器并监听客户请求
		try {		
			Properties prop = new Properties();
			//读取配置文件到输入流
			InputStream in = Class.class.getResourceAsStream("/common/util/server.properties");
			//加载配置文件
			prop.load(in);
			//读取文件中的属性 getProperty()
			int authServerPort = Integer.parseInt(prop.getProperty("authServerPort"));
			int appServerPort =  Integer.parseInt(prop.getProperty("appServerPort"));
			String serverAddress = prop.getProperty("serverAddress");
			in.close();
			
			//在服务器端注册RMI通讯端口与通讯路径
			//注册通讯端口
			LocateRegistry.createRegistry(appServerPort);
		    //注册通讯路径
			SupplierDao supplierDao = new SupplierDaoImp();
			Naming.rebind("//" + serverAddress + ":" + appServerPort + "/supplierDao", supplierDao);
		   
			
			//每个服务器套接字运行在服务器绑定特定的端口，监听这个端口
			ServerSocket serverSocket = new ServerSocket(authServerPort);
			System.out.println("监听前......");
			
			//循环监听客户端
			while(true) {
				//服务器端的Socket
				//此时服务器端程序停在accept()上，也就是8000端口，一直到客户端程序访问它，程序才继续执行
				Socket socket = serverSocket.accept();
				System.out.println("监听后......");
				
				//接收客户端发送过来的用户名和密码
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				User user = (User)ois.readObject(); //读取用户信息（User在客户端和服务器端的包名一定一致）
				System.out.println(user.getUserName()); //测试
				System.out.println(user.getPassWord()); //测试
				
				//到数据库中验证用户身份信息
				UserDaoImp dao = new UserDaoImp();
				User userTemp = dao.getUser(user.getUserName(), user.getPassWord()); //验证用户身份信息
				
				OutputStream os = socket.getOutputStream();           //得到Socket自带的字节流
				OutputStreamWriter osw = new OutputStreamWriter(os);  //将字节流转换为字符流
				PrintWriter pw = new PrintWriter(osw, true);          //PrintWriter是带有缓冲区的写操作
				if(userTemp != null) {
					//验证成功
					//pw.println("OK");	
					String token = TokenManager.istance.getToken(userTemp);
					pw.println(token);
				} else {
					//验证失败
					pw.println("Failed");
				}
			}
				
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}
}

