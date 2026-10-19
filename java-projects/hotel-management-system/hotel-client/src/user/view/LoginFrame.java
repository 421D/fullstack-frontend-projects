package user.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

import common.view.MainFrame;
import user.Client;
import user.bean.User;

public class LoginFrame extends JFrame {
	JTextField usernameTextField;
	JPasswordField passwordField;
	private boolean isDraging = false;    //标记是否移动窗体
	private int x, y;                     //记录鼠标的x、y坐标     
	private int width = 535;
	private int height = 410;
	public static String token;
	public LoginFrame() {
		//获取屏幕对象，将窗体固定到屏幕的中央。 //Dimension封装了一个构件的高度和宽度
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//如果登录窗体大小超出屏幕大小，让窗体跟屏幕等大
		if(width > screenSize.width) {
			width = screenSize.width;
		}
		if(height > screenSize.height) {
			height = screenSize.height;
		}
		
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setResizable(false);  //设置窗体大小不可变
		this.setUndecorated(true); //设置为没有标题栏

		//下一步，添加背景面板JPanel......
		//JFrame为什么不能直接添加组件？   JFrame不是一个容器，它只是一个框架，如果直接添加组件，会抛出异常。
		//怎么解决？ 需要一个中间容器，如JPanel。此时，需要将组件先添加到容器中，再用setContentPane()方法将容器设置为JFrame的内容面板。
		BackgroundPanel backgroundPanel = getLoginPanel();   //创建并初始化背景面板
		
		this.setContentPane(backgroundPanel);   //将背景面板添加到登录窗体。  把backgroundPanel设置为LoginFrame的内容面板
	}
	
	//登录背景面板设计
	public BackgroundPanel getLoginPanel() {
		BackgroundPanel backgroundPanel = new BackgroundPanel();  // 创建登录面板对象	
		backgroundPanel.setImage(getToolkit().getImage(getClass().getResource("../icon/login.png")));
		
		//添加帐号的标签和输入框
		JLabel usernameLabel = new JLabel("用户名");
		usernameLabel.setFont(new java.awt.Font("微软雅黑", 0, 16)); //字体设置，Font类通过字型、样式和字号进行构造
		usernameLabel.setForeground(/*Color.black*/ new Color(0, 125, 183));
		usernameLabel.setBounds(423, 240, 73, 35);
		backgroundPanel.add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		usernameTextField.setBounds(166, 240, 243, 34);
		backgroundPanel.add(usernameTextField);
		
		//添加密码的标签和密码输入框
		JLabel passwordLabel = new JLabel("密码");
		passwordLabel.setFont(new java.awt.Font("微软雅黑", 0, 16));
		passwordLabel.setForeground(new Color(0, 125, 183));
		passwordLabel.setBounds(423, 274, 73, 35);
		backgroundPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setBounds(166, 277, 243, 34);
		backgroundPanel.add(passwordField);

		JLabel noticeLabel = new JLabel("输入账号和密码");
		noticeLabel.setFont(new java.awt.Font("微软雅黑", 0, 16));
		noticeLabel.setForeground(new Color(0, 125, 183));
		noticeLabel.setBounds(166, 310, 243, 34);
		backgroundPanel.add(noticeLabel);
		
		//添加登录按钮
		JButton loginButton = new JButton("登录");
		loginButton.setBounds(166, 358, 243, 34);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = usernameTextField.getText();  
				char[] password = passwordField.getPassword();
				User user = new User();
				user.setUserName(userName);
				user.setPassWord(String.valueOf(password));
				//下一步，发送user到服务器端进行验证  ......未完待续
				try {
					//客户端的Socket是new出来的
					//第一个参数：要寻找的服务器网络地址,127.0.0.1是每台计算机都拥有的回环地址，指向本机，也可以写成localhost
					//第二个参数：端口号
					Socket socket = new Socket(Client.serverAddress, Client.authServerPort);
					
					//向服务器端发送用户名和密码      user
					OutputStream os = socket.getOutputStream(); //得到Socket自带的字节流
					ObjectOutputStream oos = new ObjectOutputStream(os); //对象流
					oos.writeObject(user);
					
					//接收服务器端返回的验证信息
					InputStream is = socket.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String yorn= br.readLine();
					System.out.println(yorn); //测试
					
					if(yorn.equals("Failed")) {
						JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误，请重新输入！");
					} else if(yorn.equals("login")) {
						JOptionPane.showMessageDialog(getContentPane(), "该用户已登录！");
					}else {   //验证成功，正常登录
						token = yorn;
						setVisible(false);
						MainFrame mainFrame = new MainFrame();
						mainFrame.setVisible(true);
					}
					
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		backgroundPanel.add(loginButton);
		
		//添加“关闭”按钮
		JButton closeButton = new JButton();
		ImageIcon closeIcon = new ImageIcon(getClass().getResource("../icon/close.png"));
		closeButton.setIcon(closeIcon);
		closeButton.setBorder(null);  //取消边框
		closeButton.setBounds(508, 8, closeIcon.getIconWidth(), closeIcon.getIconHeight());
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);  //退出整个程序，如果有多个窗口，全部销毁退出。
			}		
		});
		backgroundPanel.add(closeButton);
		
		//添加“最小化”按钮
		JButton minimizeButton = new JButton();
		ImageIcon minimizeIcon = new ImageIcon(getClass().getResource("../icon/minimize.png"));
		minimizeButton.setIcon(minimizeIcon);
		minimizeButton.setBorder(null);  //取消边框
		minimizeButton.setBounds(475, 8, minimizeIcon.getIconWidth(), minimizeIcon.getIconHeight());
		minimizeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);  //窗体最小化
			}		
		});
		backgroundPanel.add(minimizeButton);
		
		//接下来的任务：1 设置窗体可移动（鼠标事件）   MouseListener  MouseMotionListener
		backgroundPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) { //单击
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseEntered(MouseEvent e) { //进入组件
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {  //离开组件
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mousePressed(MouseEvent e) {  //按下
				isDraging = true;
				x = e.getX();
				y = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {  //释放
				isDraging = false;	
			}
			
		});
		backgroundPanel.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {   //拖动
				if(isDraging) {
					// setSize(width, height)
					// setBounds(x, y, width, height)
					// setLocation(x, y)
					int left = getLocation().x;
					int top = getLocation().y;
					setLocation(left + (e.getX()-x), top + (e.getY() - y));
				}
				
			}
			@Override
			public void mouseMoved(MouseEvent e) {   //移动
				// TODO Auto-generated method stub
				
			}
		});
			
		return backgroundPanel;
	}
}

 //创建登录窗体背景类
class BackgroundPanel extends JPanel {
	private Image image;
	public BackgroundPanel() {
		this.setOpaque(false);  //设置控件透明
		this.setLayout(null);
	}
		
	//设置背景图片对象的方法
	public void setImage(Image image) {
		this.image = image;
	}
	//画出背景
	protected void paintComponent(Graphics g) {
		if(image != null) {                 //如果图片已经初始化
			g.drawImage(image, 0, 0, this); //画出图片
		}
		super.paintComponent(g);
	}
}
