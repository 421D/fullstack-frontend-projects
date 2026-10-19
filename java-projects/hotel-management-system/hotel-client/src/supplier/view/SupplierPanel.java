package supplier.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import supplier.bean.Supplier;
import supplier.dao.TokenUnvalidException;
import supplier.model.SupplierModel;
import user.Client;
import user.view.LoginFrame;

public class SupplierPanel extends JPanel {  //搜索面板和表格面板
	SupplierModel model;
	JTable table;
	public SupplierPanel() {
		//向现有边框添加一个标题"供应商信息"
		/*this.setBorder(BorderFactory.createTitledBorder(null, "客人信息", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
				new Font("微软雅黑", Font.BOLD, 12), null));*/
		this.setLayout(new BorderLayout());
		
		//设计搜索面板
		JScrollPane searchPanel = new JScrollPane(); //带有滚动条的面板只能放置1个组件
		searchPanel.setPreferredSize(new Dimension(0, 80));
		JPanel search = new JPanel();   //中介容器
		search.setLayout(null);
		//"客户名称"标签
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(10, 34, 100, 15);
		search.add(nameLabel);
		//"客户名称"搜索框
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(80, 31, 119, 25);
		nameTextField.setColumns(10);
		search.add(nameTextField);
		//"地址"标签
		JLabel afzIDLabel = new JLabel("身份证号码：");
		afzIDLabel.setBounds(209, 34, 170, 15);
		search.add(afzIDLabel);
		//"地址"搜索框
		JTextField sfzIDTextField = new JTextField();
		sfzIDTextField.setBounds(290, 31, 160, 25);
		sfzIDTextField.setColumns(10);
		search.add(sfzIDTextField);
		//"搜索"按钮
		JButton searchButton = new JButton("搜索");
		searchButton.setBounds(480, 31, 77, 23);
		search.add(searchButton);
		//"添加"按钮
		JButton insertButton = new JButton("添加");
		insertButton.setBounds(560, 31, 77, 23);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddSupplierFrame insertSupplier = new AddSupplierFrame();
				insertSupplier.setVisible(true);	
				
				List<Supplier> list = null;
				try {
					list = Client.supplierDao.selectSupplier(LoginFrame.token);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (TokenUnvalidException e) {
					JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				model.setRowCount(0);
				for(int i=0; i<list.size();i++) {
					Supplier supplier = list.get(i);
					model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
							supplier.getSex(), supplier.getAge(), supplier.getAddress(),
							supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
				}
			}	
		});
		search.add(insertButton);
		//"修改"按钮
		JButton updateButton = new JButton("修改");
		updateButton.setBounds(640, 31, 77, 23);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户选择的表格的行号（行号从0开始），未选择返回-1
				int row = table.getSelectedRow(); 
				if(row<0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！", 
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {	
					//获取要修改行的第一列（列序号为0）信息———id
					int id = Integer.parseInt(model.getValueAt(row, 0).toString());
					try {
						//将选择的Supplier对象的id写入File
						File file = new File("file.txt");
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write(id);
						out.close();
						
						UpdateSupplierFrame updateSupplier = new UpdateSupplierFrame();
						updateSupplier.setVisible(true);
						
						List<Supplier> list = null;
						try {
							list = Client.supplierDao.selectSupplier(LoginFrame.token);
						} catch (RemoteException e1) {
							e1.printStackTrace();
						} catch (TokenUnvalidException e2) {
							JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
									"信息提示框", JOptionPane.INFORMATION_MESSAGE);
							System.exit(0);
						}
						model.setRowCount(0);
						for(int i=0; i<list.size();i++) {
							Supplier supplier = list.get(i);
							model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
									supplier.getSex(), supplier.getAge(), supplier.getAddress(),
									supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
						}
						repaint();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		search.add(updateButton);
		//"删除"按钮
		JButton deleteButton = new JButton("删除");
		deleteButton.setBounds(720, 31, 77, 23);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//获取用户选择的表格的行号（行号从0开始），未选择返回-1
				int row = table.getSelectedRow(); 
				if(row<0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要删除的数据！", 
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				//获取要删除行的第一列（列序号为0）信息———id
				int id = Integer.parseInt(model.getValueAt(row, 0).toString());
				
				//调用远程方法删除数据库中的信息
				try {
					Client.supplierDao.deleteSupplier(id, LoginFrame.token);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				} catch (TokenUnvalidException e1) {
					JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				JOptionPane.showMessageDialog(getParent(), "数据删除成功！", 
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				model.removeRow(row);
				repaint();
			}
		});
		search.add(deleteButton);
		
		searchPanel.setViewportView(search);
		this.add(searchPanel, BorderLayout.NORTH);
		
		//设计表格面板   JTable
		model = new SupplierModel();
		table = new JTable(model);
		table.setRowHeight(25);   //设置行高
		model.setRowCount(5);
		
		//初始化供应商列表
		List<Supplier> list = null;
		try {
			list = Client.supplierDao.selectSupplier(LoginFrame.token);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (TokenUnvalidException e) {
			JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		model.setRowCount(0);
		for(int i=0; i<list.size();i++) {
			Supplier supplier = list.get(i);
			model.addRow(new Object[] {supplier.getId(), supplier.getSfzID(), supplier.getName(),
					supplier.getSex(), supplier.getAge(), supplier.getAddress(),
					supplier.getPhone(), supplier.getEmail(), supplier.getBeiz()});
		}
		
		JScrollPane tablePanel = new JScrollPane();
		tablePanel.setViewportView(table);
		this.add(tablePanel, BorderLayout.CENTER);
	}
}
