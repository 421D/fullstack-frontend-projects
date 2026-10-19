package supplier.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import supplier.bean.Supplier;
import supplier.dao.TokenUnvalidException;
import user.Client;
import user.view.LoginFrame;

public class UpdateSupplierFrame extends JDialog {  // 修改供应商面板布局
	JPanel contentPane;
	JLabel sfzIDLabel;
	JTextField sfzIDTextField;
	JLabel nameLabel;
	JTextField nameTextField;
	JLabel sexLabel;
	JTextField sexTextField;
	JLabel ageLabel;
	JTextField ageTextField;
	JLabel addressLabel;
	JTextField addressTextField;
	JLabel phoneLabel;
	JTextField phoneTextField;
	JLabel emailLabel;
	JTextField emailTextField;
	
	
	JLabel beizLabel;
	JTextArea beizTextArea;
	JButton updateButton;
	JButton closeButton;
	
	Supplier supplier = null;
	
	public UpdateSupplierFrame() {
		this.setModal(true);
		this.setTitle("修改客户信息框体");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds((screenSize.width - 800) / 2, (screenSize.height - 475) / 2, 800, 475);
		
		try {
			File file = new File("file.txt");
			FileInputStream in = new FileInputStream(file);
			int id = in.read();
			file.delete();
			in.close();
			
			supplier = Client.supplierDao.selectSupplierbyid(id, LoginFrame.token);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (TokenUnvalidException e1) {
			JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
					"信息提示框", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);

		sfzIDLabel = new JLabel("身份证号码:");
		sfzIDLabel.setBounds(49, 97, 100, 25);
		contentPane.add(sfzIDLabel);
		sfzIDTextField = new JTextField();
		sfzIDTextField.setText(supplier.getSfzID());
		sfzIDTextField.setBounds(142, 94, 184, 25);
		sfzIDTextField.setColumns(10);
		contentPane.add(sfzIDTextField);

		nameLabel = new JLabel("姓名：");
		nameLabel.setBounds(49, 53, 100, 25);
		contentPane.add(nameLabel);
		nameTextField = new JTextField();
		nameTextField.setText(supplier.getName());
		nameTextField.setBounds(142, 50, 184, 25);
		nameTextField.setColumns(10);
		contentPane.add(nameTextField);

		sexLabel = new JLabel("性别:");
		sexLabel.setBounds(385, 97, 100, 25);
		contentPane.add(sexLabel);
		sexTextField = new JTextField();
		sexTextField.setText(supplier.getSex());
		sexTextField.setBounds(483, 94, 184, 25);
		sexTextField.setColumns(10);
		contentPane.add(sexTextField);
		
		ageLabel = new JLabel("年龄:");
		ageLabel.setBounds(49, 140, 100, 25);
		contentPane.add(ageLabel);
		ageTextField = new JTextField();
		ageTextField.setText(supplier.getAge());
		ageTextField.setBounds(142, 137, 184, 25);
		ageTextField.setColumns(10);
		contentPane.add(ageTextField);
		
		addressLabel = new JLabel("客户住址：");
		addressLabel.setBounds(385, 53, 100, 25);
		contentPane.add(addressLabel);
		addressTextField = new JTextField();
		addressTextField.setText(supplier.getAddress());
		addressTextField.setColumns(10);
		addressTextField.setBounds(483, 50, 184, 25);
		contentPane.add(addressTextField);

		phoneLabel = new JLabel("联系方式:");
		phoneLabel.setBounds(385, 140, 100, 25);
		contentPane.add(phoneLabel);
		phoneTextField = new JTextField();
		phoneTextField.setText(supplier.getPhone());
		phoneTextField.setBounds(483, 137, 184, 25);
		phoneTextField.setColumns(10);
		contentPane.add(phoneTextField);

		emailLabel = new JLabel("邮箱:");
		emailLabel.setBounds(49, 180, 100, 25);
		contentPane.add(emailLabel);
		emailTextField = new JTextField();
		emailTextField.setText(supplier.getEmail());
		emailTextField.setBounds(142, 177, 184, 25);
		emailTextField.setColumns(10);
		contentPane.add(emailTextField);

		beizLabel = new JLabel("备注:");
		beizLabel.setBounds(49, 294, 100, 25);
		contentPane.add(beizLabel);
		beizTextArea = new JTextArea();
		beizTextArea.setText(supplier.getBeiz());
		beizTextArea.setBounds(142, 267, 525, 89);
		contentPane.add(beizTextArea);	

		// 为修改按钮添加事件监听器
		updateButton = new JButton("修改");
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String sfzID = sfzIDTextField.getText();
				String name = nameTextField.getText();
				String sex = sexTextField.getText();
				String age = ageTextField.getText();
				String address = addressTextField.getText();
				String phone = phoneTextField.getText();
				String email = emailTextField.getText();
				String beiz = beizTextArea.getText();
				
				supplier.setSfzID(sfzID);
				supplier.setName(name);
				supplier.setSex(sex);
				supplier.setAge(age);
				supplier.setAddress(address);
				supplier.setPhone(phone);
				supplier.setEmail(email);
				supplier.setBeiz(beiz);
				
				try {
					Client.supplierDao.updateSupplier(supplier, LoginFrame.token);
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (TokenUnvalidException e) {
					JOptionPane.showMessageDialog(null, "会话超时，请重新登录！", 
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				JOptionPane.showMessageDialog(getParent(), "数据修改成功！", 
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				do_closeButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(255, 388, 93, 23);
		contentPane.add(updateButton);
		
		// 退出按钮
		closeButton = new JButton("退出");
		closeButton.setBounds(385, 388, 93, 23);
		contentPane.add(closeButton);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
	}
	// 设置关闭按钮动作
	protected void do_closeButton_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}