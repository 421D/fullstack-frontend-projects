package common.view;

import java.awt.*;
import java.awt.MultipleGradientPaint.CycleMethod;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.*;

public class GlassButton extends JToggleButton { //JToggleButton按钮按下去时会陷下去，不会弹回来，除非再按一次
	private boolean paintFlag = false;
	public GlassButton() { //构造方法，自定义初始化
		this.setContentAreaFilled(false);//是否填充
		this.setBorderPainted(false);//不画边框
		
		this.setVerticalTextPosition(SwingConstants.BOTTOM);  //文字相对与图标的垂直位置
		this.setVerticalAlignment(SwingConstants.CENTER);     //垂直对其方式
		this.setHorizontalTextPosition(SwingConstants.CENTER);//文字相对与图标的水平直位置
		this.setHorizontalAlignment(SwingConstants.CENTER);   //水平对其方式
		this.setIconTextGap(0);  //设置icon和text的距离
		this.setMargin(new Insets(0, 0, 0, 0));//设置按钮边框和标签之间的空白
		//Insets指定一个容器在它的各个边界上应留出的空白空间，Insets(int, int, int, int)指定上下左右四个空白宽度。
	
		//为按钮注册鼠标事件监听器
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				paintFlag = true;
				repaint();	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				paintFlag = false;
				repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
		});
	}

	//重写paint方法，绘制单击按钮后的按钮图像
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g.create();  //强制类型转换，得到Graphics2D的对象
		super.paint(g2);
		Rectangle bs = g2.getClipBounds();//返回矩形边框
		if(isSelected() || paintFlag) {
			Point2D center = new Point2D.Float(bs.width/2, bs.height/2); //圆心
			float radius = Math.min(bs.width/2, bs.height/2);   //半径
			Point2D focus = new Point2D.Float(bs.width/2, bs.height/2); //第一种颜色映射的点
			Color[] colors = {Color.WHITE, new Color(255, 255, 255, 0)}; //渐变分布颜色
			                                      //new Color(255, 255, 255, 0) 第1个参数为透明度参数,其后为红,绿和蓝.
			float[] dist = {0f, 0.8f};
			if(radius > 0 ) {
				//RadialGradientPaint使用圆形辐射颜色渐变模式填充某一形状的方式
				RadialGradientPaint p = new RadialGradientPaint(center,    
						radius, focus, dist, colors, CycleMethod.NO_CYCLE); 
				g2.setPaint(p);   //设置填充效果
				g2.fillRect(0, 0, bs.width, bs.height);
			}
		}	 
	}
}
