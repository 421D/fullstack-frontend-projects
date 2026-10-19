package common.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

import Seller.view.SellerPanel;
import supplier.view.SupplierPanel;


public class MainFrame extends JFrame{
	//任务：窗体大小 1000*600 ，定位到屏幕中央，设置为可调节大小
	private int width = 1000;
	private int height = 600;
	private JMenuBar menuBar = null;  //菜单栏
	SmallScrollPanel moduleButtonGroup = null;   //主菜单按钮区域
	JTree tree = null;
	JPanel messagePanel = null;
	public MainFrame() {
		//获取屏幕对象，将窗体定位到屏幕中央
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if(width > screenSize.width) {
			width = screenSize.width;
		}
		if(height > screenSize.height) {
			height = screenSize.height;
		}
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setResizable(true);   //设置为可调节大小
		this.setTitle("酒店管理系统");
		URL url = getClass().getResource("../icon/hotel.png");
		this.setIconImage(new ImageIcon(url).getImage());
		
		//关于窗体关闭的问题：
		//JFrame标题栏上的关闭按钮之关闭窗口，不关闭程序
		//setDefaultCloseOperation()设置单击关闭按钮的操作：
		// 1 EXIT_ON_CLOSE 退出应用程序（调用Sysytem.exit(0);）
		// 2 HIDE_ON_CLOSE 隐藏窗体 (默认)
		// 3
		// 4
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//接下来的任务：1 设置菜单栏  2 设置窗体的布局方式  3 主菜单按钮区域设置
		
		// 设置菜单栏
		this.setJMenuBar(getMenus());
		
		//设置窗体的布局方式——BorderLayout
		/*//方法一     加一个中间容器
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(getContentPanel(), BorderLayout.CENTER);
		contentPane.add(getModuleButtonGroup(), BorderLayout.NORTH);
		this.add(contentPane);
		*/
		
		//方法二   JFrame有一个内容面板
		this.getContentPane().setLayout(new BorderLayout());
		//this.getContentPane().setBackground(Color.BLACK);     //测试
		//在Center区域添加工作空间面板
		this.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		//在North区域添加主菜单按钮区域面板
		this.getContentPane().add(getModuleButtonGroup(), BorderLayout.NORTH);
	}
	
	//设计菜单栏
	private JMenuBar getMenus() {
		if(menuBar == null) {         //健壮性
			//创建菜单栏。  窗体上要添加菜单组件，首先要添加菜单栏。
			menuBar = new JMenuBar();
			//创建一级菜单
			JMenu m1 = new JMenu("文件");
			JMenu m2 = new JMenu("帮助");
			//创建二级菜单
			JMenuItem mi11 = new JMenuItem("退出");
			JMenuItem mi21 = new JMenuItem("关于");
			//将二级菜单添加到一级菜单
			m1.add(mi11);
			m2.add(mi21);
			//将一级菜单添加到菜单栏
			menuBar.add(m1);
			menuBar.add(m2);
			
			//给二级菜单注册事件监听器 ActionListener
			mi11.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}	
			});
			mi21.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(getContentPane(), "关于我们");
				}
			});
		}
		return menuBar;
	}
	
	//设计主菜单按钮区域面板
	private SmallScrollPanel getModuleButtonGroup() {
		if(moduleButtonGroup == null) {
			moduleButtonGroup = new SmallScrollPanel();
			//moduleButtonGroup.setBackground(Color.YELLOW); //测试
		}
		return moduleButtonGroup;
	}
	
	//设计工作空间面板
	static JPanel workspacePanel = null;  //workspacePanel分为两部分：左侧的树形目录面板  右侧的内容面板
	static JPanel panel_1;
	static JPanel panel_2;
	private JPanel getContentPanel() {
		if(workspacePanel == null) {
			workspacePanel = new JPanel();
			//向现有边框添加一个标题  createTitledBorder()
		workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "客人信息管理", 
					TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
					new Font("微软雅黑", Font.BOLD, 12), null));
			workspacePanel.setLayout(new BorderLayout());   //布局方式
			
			messagePanel = new JPanel();   //中介容器，右侧的信息面板
			
			panel_1=new JPanel();
			panel_1.setLayout(new BorderLayout());
			//panel_1.add(treePanel, BorderLayout.WEST);
			panel_1.add(messagePanel, BorderLayout.CENTER);
			
			
			//workspacePanel.add(treePanel, BorderLayout.WEST);	
			
			//设计右侧的内容面板（先设计供应商管理模块）
			messagePanel.setLayout(new BorderLayout());
			SupplierPanel supplierPanel = new SupplierPanel();
			messagePanel.add(supplierPanel,BorderLayout.CENTER);
			workspacePanel.add(panel_1, BorderLayout.CENTER);
			
			
			
		}
		return workspacePanel;
	}
}
