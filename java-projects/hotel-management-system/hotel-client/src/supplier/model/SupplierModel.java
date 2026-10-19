package supplier.model;

import javax.swing.table.DefaultTableModel;

public class SupplierModel extends DefaultTableModel {
	Class[] types = new Class[] {java.lang.Object.class, java.lang.Object.class, 
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
			java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
	//Object是所有Java类的祖先，可以使用类型为Object的变量指向任意类型的对象
	boolean[] canEdit = new boolean[] {false, false, false, false, false, false, 
										false, false, false, false, false};
	
	//重写DefaultTableModel中的getColumnClass()，返回表格中每一列数据的数据类型
	public Class getColumnClass(int columnIndex) {
		return types[columnIndex];
	}
	
	//重写DefaultTableModel中的getCellEditable()，返回表格是否可以被编辑
	public boolean isCellEditable(int rowIdext, int columnIndex) {
		return canEdit[columnIndex];
	}
	
	public SupplierModel() { //构造方法名
		//DefaultTableModel的构造方法:DefaultTableModel(Object[][] data, object[] columnNames)
		//Object[][] data 表格中的数据， object[] columnNames 列名
		super(new Object[][] {}, new String[] {"编号", "身份证号码", "姓名", "性别", "年龄", 
				"住址", "联系方式", "邮箱", "备注"});
	}
}
