package common.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import purchase.view.PurchasePanel;

public class AlphaScrollPane extends JScrollPane {
	//private GlassButton jibendanganButton = null;    //基本档案按钮
	private GlassButton kerenguanliButton = null;  //客人信息管理按钮
	private GlassButton kerenzhufangButton = null;           //客人住房管理按钮
	private GlassButton fangjianButton = null;          //房间管理按钮
	private GlassButton jiucanButton = null;          //就餐管理按钮
	private GlassButton renyuanButton = null;        //人员管理按钮
	public AlphaScrollPane() {
		super();   //调用父类的构造函数
		this.setBackground(new Color(151, 188,229));
		
		//添加6个按钮。先将6个按钮放置在一个JPanel上，在将JPanel作为一个整体添加到JScrollPane上。
		JPanel scrollPanel = new JPanel();
		scrollPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 38, 8));
		
		//scrollPanel.add(getJibendanganButton());  
		scrollPanel.add(getKerenguanliButton());
		scrollPanel.add(getKerenzhufangButton());
		scrollPanel.add(getFangjianButton());
		scrollPanel.add(getJiucanButton());
		scrollPanel.add(getumenButton());
		
		//ButtonGroup用于为一组按钮创建一个多斥作用域。
		//使用相同的ButtonGroup意味着“开启”其中一个按钮时，将关闭组中的其他所有按钮。
		//JButton、JMenuItem放入按钮组没有任何意义。   JToggleButton
		ButtonGroup buttonGroup = new ButtonGroup();
		//buttonGroup.add(getJibendanganButton());
		buttonGroup.add(getKerenguanliButton());
		buttonGroup.add(getumenButton());
		buttonGroup.add(getFangjianButton());
		buttonGroup.add(getJiucanButton());
		buttonGroup.add(getKerenzhufangButton());
		
		this.setViewportView(scrollPanel); //将按钮组面板设置为滚动面板的视图
	}
	
	//设计按钮
	/*private GlassButton getJibendanganButton() {
		if(jibendanganButton == null) {
			jibendanganButton = new GlassButton();	
			URL url = getClass().getResource("../icon/eat.png");
			jibendanganButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			jibendanganButton.setText("基本档案");
			jibendanganButton.setFont(new Font("微软雅黑", 1, 12));
			jibendanganButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "基本档案管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(MainFrame.panel_1);
					repaint();
				}
				
			});
		}	
		return jibendanganButton; 
	}*/
	private GlassButton getKerenguanliButton() {
		if(kerenguanliButton == null) {
			kerenguanliButton = new GlassButton();	
			URL url = getClass().getResource("../icon/keren.png");
			kerenguanliButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			kerenguanliButton.setText("客人信息管理");
			kerenguanliButton.setFont(new Font("微软雅黑", 1, 12));
			kerenguanliButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "客人信息管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					MainFrame.workspacePanel.add(MainFrame.panel_1);
					repaint();
				}
				
			});
		}	
		return kerenguanliButton; 
	}
	private GlassButton getKerenzhufangButton() {
		if(kerenzhufangButton == null) {
			kerenzhufangButton = new GlassButton();	
			URL url = getClass().getResource("../icon/zuf.png");
			kerenzhufangButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			kerenzhufangButton.setText("客人住房管理");
			kerenzhufangButton.setFont(new Font("微软雅黑", 1, 12));
			kerenzhufangButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "客人住房管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return kerenzhufangButton; 
	}
	private GlassButton getFangjianButton() {
		if(fangjianButton == null) {
			fangjianButton = new GlassButton();	
			URL url = getClass().getResource("../icon/fangjian.png");
			fangjianButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			fangjianButton.setText("房间信息管理");
			fangjianButton.setFont(new Font("微软雅黑", 1, 12));
			fangjianButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "房间信息管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return fangjianButton; 
	}
	private GlassButton getJiucanButton() {
		if(jiucanButton == null) {
			jiucanButton = new GlassButton();	
			URL url = getClass().getResource("../icon/eat.png");
			jiucanButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			jiucanButton.setText("就餐管理");
			jiucanButton.setFont(new Font("微软雅黑", 1, 12));
			jiucanButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "就餐管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
			
		}	
		return jiucanButton; 
	}
	private GlassButton getumenButton() {
		if(renyuanButton == null) {
			renyuanButton = new GlassButton();	
			URL url = getClass().getResource("../icon/reygl.png");
			renyuanButton.setIcon(new ImageIcon(url));  //设置按钮的图标
			renyuanButton.setText("人员管理");
			renyuanButton.setFont(new Font("微软雅黑", 1, 12));
			renyuanButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO 自动生成的方法存根
					MainFrame.workspacePanel.removeAll();
					MainFrame.workspacePanel.setBorder(BorderFactory.createTitledBorder(null, "人员管理", 
							TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
							new Font("微软雅黑", Font.BOLD, 12), null));
					PurchasePanel purchasePanel =new PurchasePanel();
					MainFrame.workspacePanel.add(purchasePanel, BorderLayout.CENTER);
					
					repaint();
				}});
		}	
		return renyuanButton; 
	}
}
