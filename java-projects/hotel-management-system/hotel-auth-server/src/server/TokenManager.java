package server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.UUID;
import server.view.ServerFrame;
import user.bean.User;

/*单例模式步骤：1.把构造函数私有2.自己创建一个对象3.对外暴露一个方法，允许获取上面创建的对象*/
public class TokenManager {
	public static TokenManager istance = new TokenManager();  //2.自己创建一个对象
	
	//用户、Token、剩余时间 
	//Hashtable哈希表（键值对key-value），可以看成一张两列的表格，key是不重复的，可以通过get(key)得到value
	//static修饰的方法或者变量 不依赖于对象进行访问，可以通过类型直接访问，整个系统中只存在一个
	private static Hashtable<User, String> user2token = new Hashtable<User, String>(); //存储用户和令牌的对应关系
	private static Hashtable<String, User> token2user = new Hashtable<String, User>(); //存储令牌和用户的对应关系
	private static Hashtable<String, Long> token2time = new Hashtable<String, Long>(); //存储令牌和剩余时间的关系
	private static Hashtable<String, String> username2token = new Hashtable<String, String>();//存储用户名和令牌的关系
	private static int availablePeriod = 60;     //令牌的有效时间(初始时间)
	
	private TokenManager() {  //1.把构造函数私有
		Thread thread = new Thread(new Thread() {  //匿名内部类
			public void run() {
				try {
					Properties prop = new Properties();
					//读取配置文件到输入流
					InputStream in = Class.class.getResourceAsStream("/common/util/server.properties");
					//加载配置文件
					prop.load(in);
					//读取文件中的属性 getProperty()
					availablePeriod = Integer.parseInt(prop.getProperty("tokenTimeOut"));
					in.close();
				} catch (IOException e1) {
					System.out.println("加载输入流失败");
					e1.printStackTrace();
				}
				
				while(true) {
					long sleepTime = availablePeriod/2; //休眠时间
					if(sleepTime<1) {
						sleepTime = 6*1000;
					}
					
					try {
						Thread.sleep(sleepTime);
						istance.checkToken();  //定时循环检查令牌是否超期
						
						//绘制服务器端监控界面
						//1、清除监控界面中table的数据 
						while(ServerFrame.model.getRowCount() != 0) {//getRowCount()获取行的数量
							ServerFrame.model.removeRow(0);  //删除行
						}
						//2、重新绘制table中的数据
						Iterator<User> iterator = user2token.keySet().iterator();
						int i = 1;     //table中的序号
						long now = System.currentTimeMillis();      //当前时间
						while(iterator.hasNext()) {
							User user = iterator.next();
							String token = user2token.get(user);
							long loginTime = token2time.get(token);  //登录时间
							long last = (now - loginTime)/1000;      //持续时间ms-->s
							ServerFrame.model.addRow(new Object[] {i, user.getUserName(), 
									              token, last, availablePeriod-last});   //添加一行
							i++;
						}
						//3、重新绘制标签区域的数据
						ServerFrame.noticeLabel.setText("当前在线人数：" + (i-1) + "当前服务器内存占用：" + 
										Runtime.getRuntime().totalMemory()/(8*1024*1024) + "MB");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}		
				}
			}
		});
		thread.start();	
	}
	
	//生成Token
	private String generateToken(User user) {
		return UUID.randomUUID().toString().substring(0, 16);
		//UUID(Universally Unique Identifier) 产生一个号称全球唯一的ID
		//randomUUID()随机的UUID
		//toString()转换为字符串
		//substring(int beginIndex, int endIndex)截取字符串
	}
	
	//存储用户、令牌和剩余时间(= 令牌有效时间-在线时间（当前时间-登录时间）)的相互关系
	private synchronized void putToken(User user, String token) { //synchronized同步锁（不同的线程顺序执行）
		user2token.put(user, token);
		token2user.put(token, user);
		token2time.put(token, System.currentTimeMillis());
		username2token.put(user.getUserName(), token);
	}
	
	//根据用户获取Token --- 身份验证成功之后调用
	public synchronized String getToken(User user) {
		//String token = user2token.get(user);
		String token = username2token.get(user.getUserName());
		if(token == null) {  //第一次登陆
			token = generateToken(user);
			putToken(user, token);
		} else {             //已登陆
			token = "login";
		}
		return token;
	}
	
	//验证Token是否有效 --- 是否已经分配给某个用户
	public synchronized boolean verify(String token) {
		return token2user.get(token) != null;
	}
	
	//根据Token获取用户
	public User getUser(String token) {
		return token2user.get(token);
	}
	
	//设置Token的有效时长（单位：s）
	public static void setAvailablePeriod(int period) {
		availablePeriod = period;
	}
	
	//检查令牌是否超时  --- 实际上，是用于检查用户是否超时
	private synchronized void checkToken() {  //循环检查所有的Token
		long crt = System.currentTimeMillis(); //当前时刻的时间，单位：ms
		//Iterator迭代器，用于循环检查列表。Iterator迭代器，又称为游标，提供一种方法用于访问一个容器中的各个对象，负责访问和遍历。
		Iterator<String> iterator = token2user.keySet().iterator();
		String token = null;
		Long time = new Long(0);    //用户的登录时间
		//原因：在Java中，有的运算必须在两个类的对象之间执行，不允许对象和数字之间直接运算
		//基本数据类型      long    int     byte
		//对象类型（类）  Long   Integer  Byte
		while(iterator.hasNext()) {  //hasNext()判断访问的数据是否存在，向下循环
			token = iterator.next(); //next()返回的当前访问的数据的值
			time = token2time.get(token);
			//判断有效时间和在线时间之间的关系
			if( (crt-time) > availablePeriod*1000) {  //超时
				token2time.remove(token);
				user2token.remove(token2user.get(token));
				username2token.remove(token2user.get(token).getUserName());
				token2user.remove(token);
			}
		}
	}
}
