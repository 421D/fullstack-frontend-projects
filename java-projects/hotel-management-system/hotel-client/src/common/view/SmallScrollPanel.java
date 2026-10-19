package common.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class SmallScrollPanel extends JPanel {
	private JButton leftScrollButton = null;   //左侧微调按钮
	private JButton rightScrollButton = null;  //右侧微调按钮
	private AlphaScrollPane alphaScrollPane = null; //主菜单按钮滚动面板
	public SmallScrollPanel() {  //在构造方法中定义初始化方法
		//设置布局管理器——BorderLayout
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(0);  //设置组件之间的水平间距
		this.setLayout(borderLayout);
		this.setOpaque(false);
		//setSize()    setLocation()    setBounds()  无效的
		//setPreferredSize()需要在使用布局管理器的时候使用，仅仅是设置初始的大小
		//这个不一定与实际显示出来的一致（根据界面整体的变化而变化）
		this.setPreferredSize(new Dimension(0, 84));
		
		//左侧微调按钮（WEST）   右侧微调按钮（EAST）    主菜单按钮滚动面板（CENTER）
		this.add(getLeftScrollButton(), BorderLayout.WEST); //添加左侧微调按钮
		this.add(getRightScrollButton(), BorderLayout.EAST);//添加右侧微调按钮
		this.add(getAlphaScrollPane(), BorderLayout.CENTER);//添加主菜单按钮滚动面板
	}
	
	//设计主菜单按钮滚动面板
	private AlphaScrollPane getAlphaScrollPane() {
		if(alphaScrollPane == null) {
			alphaScrollPane = new AlphaScrollPane();
			alphaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);//始终不显示垂直滚动条
			alphaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//始终不显示水平滚动条
		
			//注册事件监听器（组件事件的监听器ComponentListener,组件的大小、位置或可见性发生变化）
			alphaScrollPane.addComponentListener(new ComponentListener() {
				@Override
				public void componentHidden(ComponentEvent arg0) { //组件变得不可见时调用
					// TODO Auto-generated method stub
					
				}
				@Override
				public void componentMoved(ComponentEvent arg0) { //组件位置更改时调用 
					// TODO Auto-generated method stub
					
				}
				@Override
				public void componentResized(ComponentEvent arg0) { //组件大小更改时调用
					JScrollBar scrollBar = alphaScrollPane.getHorizontalScrollBar();//获取横向滚动条	
					//获取范围限制参数
					int scrollWidth = scrollBar.getMaximum();   //横向滚动条的长度
					int paneWidth = alphaScrollPane.getWidth(); //滚动面板的宽度
					//当容器大于内容的时候隐藏左右微调按钮
					if(paneWidth >= scrollWidth) {
						getLeftScrollButton().setVisible(false);
						getRightScrollButton().setVisible(false);
					}
					//当容器小于内容的时候显示左右微调按钮
					if(paneWidth < scrollWidth) {
						getLeftScrollButton().setVisible(true);
						getRightScrollButton().setVisible(true);
					}
				}

				@Override
				public void componentShown(ComponentEvent arg0) {//组件变得可见时调用
					// TODO Auto-generated method stub				
				}				
			});
		}
		return alphaScrollPane;
	}
	
	//设计左侧微调按钮
	private JButton getLeftScrollButton() {
		if(leftScrollButton == null) {
			leftScrollButton = new JButton();
			//创建按钮图标
			ImageIcon icon1 = new ImageIcon(getClass().getResource("../icon/zuoyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource("../icon/zuoyidongon.png"));
			leftScrollButton.setIcon(icon1); //设置按钮图标
			leftScrollButton.setPressedIcon(icon2);  //按下时的图标
			leftScrollButton.setRolloverIcon(icon2); //鼠标光标经过时的图标
			leftScrollButton.setPreferredSize(new Dimension(38, 0)); //设置初始大小
			leftScrollButton.setOpaque(false);
			//设置边框 setBorder()
			//工厂设计模式，提供了一个类BorderFactory，可以快速设计边框，隐藏设计细节。
			//leftScrollButton.setBorder(BorderFactory.createRaisedBevelBorder()); //凸起来的按钮
			//createEmptyBorder(int top, int left, int bottom, int right) 创建空边框
			//上、左、下、右逆时针方向距离边框的像素值
			leftScrollButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
			leftScrollButton.setFocusable(false); //取消按钮的焦点功能
			leftScrollButton.setContentAreaFilled(false); //取消按钮内容填充
			
			//事件监听器 (鼠标事件监听器 MouseListener  MouseMotionListener)
			leftScrollButton.addMouseListener(new MouseListener() {
				JScrollBar scrollBar = getAlphaScrollPane().getHorizontalScrollBar();//获取滚动面板的横向滚动条
				Boolean isPressed = false;  //标记是否按下鼠标
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseExited(MouseEvent e) { //离开按钮
					isPressed = false;				
				}
				@Override
				public void mousePressed(MouseEvent e) { //按下按钮
					isPressed = true;
					
					/*Thread t = new Thread();
					t.start();*/
					
					new Thread(){     //开辟新线程，while(isPressed)会出现死循环
						int oldValue = scrollBar.getValue(); //保存原有滚动条的值
						public void run() {  //run() 线程的入口
							while(isPressed) {   //循环移动面板
								try {
									Thread.sleep(10);   //否则移动的太快
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								oldValue = scrollBar.getValue();  //获取滚动条的当前值
								scrollBar.setValue(oldValue - 3); //设置滚动条向右移动3个像素
							}
						}
					}.start();
					
				}
				@Override
				public void mouseReleased(MouseEvent e) { //释放按钮
					isPressed = false;					
				}
			});
		}
		return leftScrollButton;	
	}
	
	
	//设计右侧微调按钮
	private JButton getRightScrollButton() {
		if(rightScrollButton == null) {
			rightScrollButton = new JButton();
			ImageIcon icon1 = new ImageIcon(getClass().getResource("../icon/youyidongoff.png"));
			ImageIcon icon2 = new ImageIcon(getClass().getResource("../icon/youyidongon.png"));
			rightScrollButton.setIcon(icon1); 
			rightScrollButton.setPressedIcon(icon2);  
			rightScrollButton.setRolloverIcon(icon2); 
			rightScrollButton.setPreferredSize(new Dimension(38, 0)); 
			rightScrollButton.setOpaque(false);
			rightScrollButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			rightScrollButton.setFocusable(false); 
			rightScrollButton.setContentAreaFilled(false); 
			
			//事件监听器 (鼠标事件监听器 MouseListener  MouseMotionListener)
			rightScrollButton.addMouseListener(new MouseListener() {
				JScrollBar scrollBar = getAlphaScrollPane().getHorizontalScrollBar();//获取滚动面板的横向滚动条
				Boolean isPressed = false;  //标记是否按下鼠标
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void mouseExited(MouseEvent e) { //离开按钮
					isPressed = false;				
				}
				@Override
				public void mousePressed(MouseEvent e) { //按下按钮
					isPressed = true;
					
					/*Thread t = new Thread();
					t.start();*/
					
					new Thread(){     //开辟新线程，while(isPressed)会出现死循环
						int oldValue = scrollBar.getValue(); //保存原有滚动条的值
						public void run() {  //run() 线程的入口
							while(isPressed) {   //循环移动面板
								try {
									Thread.sleep(10);   //否则移动的太快
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								oldValue = scrollBar.getValue();  //获取滚动条的当前值
								scrollBar.setValue(oldValue + 3); //设置滚动条向右移动3个像素
							}
						}
					}.start();
					
				}
				@Override
				public void mouseReleased(MouseEvent e) { //释放按钮
					isPressed = false;					
				}
			});
		}
		return rightScrollButton;	
	}
}

/*JAVA中JButton常用设置 
1、 对JButton大小的设置
     ——因为JButen是属于小器件类型的，所以一般的setSize不能对其大小的设置，所以一般用
     button.setPreferredSize(new Dimension(30,30));
     //（30，30） 是你要设置按钮的大小
2、 对JButton透明的设置
     ——按钮设置为透明，这样就不会挡着后面的背景
     button.setContentAreaFilled(false);
3、 对JButton去掉按钮的边框的设置
     ——如果有时候你的按钮不需要边框因为边框影响美观或者是因为你需要的是点击之前按钮呈现普通图
     标形式，点击之后才有各种效果的话就可以用这种方法去掉边框
     button.setBorderPainted(false);
4、 对JButton添加图标的设置
     —— // 实例化一个图标对象
     ImageIcon image = new ImageIcon(icons[i]);
     // 实例化按钮对象，并且设置按钮上显示图片
     JButton button = new JButton(image);
     ——或者
     button.setIcon(new ImageIcon(getClass().getResource("qq.png")));
     //qq.png是你要添加的图片
5 、 让按钮随按钮上的图案变化
     butten.setMargin(new Insets(0,0,0,0));
6、 设置凸起来的按钮，很多其他的swing也可用此方法
     butten.setBorder(BorderFactory.createRaisedBevelBorder());
7、 设置凹起来的按钮，很多其他的swing也可用此方法
     button.setBorder(BorderFactory.createLoweredBevelBorder());
8、 设置按钮的前景色和背景色
     button.setFont(new java.awt.Font("华文行楷", 1, 15));
     button.setBackground(Color.green);
9、 改变按钮的样式 
     UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
*/
