package server.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ServerFrame extends JFrame {
	private int width = 800;
	private int height = 600;
	public static LocalTableModel model = null;
	private JTable table = null;
	public static JLabel noticeLabel = null;
	public ServerFrame() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width-width)/2, (screenSize.height-height)/2, width, height);
		this.setTitle("服务器监控");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//布局管理器BorderLayout
		this.setLayout(new BorderLayout());
		
		//设计标签面板
		JPanel fixPanel = new JPanel();
		noticeLabel = new JLabel("当前在线人数：0；当前服务器内存占用：" + 
							Runtime.getRuntime().totalMemory()/(8*1024*1024) + "MB");
		//1Byte = 8bit; 1KB = 1024Byte; 1MB = 1024KB;
		noticeLabel.setFont(new Font("微软雅黑", 0, 16));
		noticeLabel.setForeground(new Color(0, 125, 183));
		fixPanel.add(noticeLabel);
		
		//设置列表面板
		JScrollPane tablePanel = new JScrollPane();  //列表的大小是不可控的
		//JTable用于显示二维数据，提供编辑、选择等功能。
		//JTable的数据源是DefaultTableModel（表格用的数据模型）的对象，在JTable创建时即可绑定。
		model = new LocalTableModel();
		table = new JTable(model);
		table.setRowHeight(25);  //设置行高
		model.setRowCount(5);
		tablePanel.setViewportView(table);
		
		
		this.add(fixPanel, BorderLayout.NORTH);   //将标签面板添加到窗体的NORTH区域
		this.add(tablePanel, BorderLayout.CENTER);//将列表面板添加到窗体的Center区域
	}
	
	//列表模型内部类
	public static class LocalTableModel extends DefaultTableModel {
		Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
		//Object是所有Java类的祖先，可以使用类型为Object的变量指向任意类型的对象
		boolean[] canEdit = new boolean[] {false, false, false, false, false};
		
		//重写DefaultTableModel中的getColumnClass()，返回表格中每一列数据的数据类型
		public Class getColumnClass(int columnIndex) {
			return types[columnIndex];
		}
		
		//重写DefaultTableModel中的getCellEditable()，返回表格是否可以被编辑
		public boolean isCellEditable(int rowIdext, int columnIndex) {
			return canEdit[columnIndex];
		}
		
		public LocalTableModel() { //构造方法名
			//DefaultTableModel的构造方法:DefaultTableModel(Object[][] data, object[] columnNames)
			//Object[][] data 表格中的数据， object[] columnNames 列名
			super(new Object[][] {}, new String[] {"序号", "用户", "令牌", "持续时间", "剩余时间"});
		}
	}
}
