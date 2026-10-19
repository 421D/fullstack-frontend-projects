package Seller.view;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SellerPanel extends JPanel {
	public SellerPanel() {
		//向现有边框添加一个标题"客人住房管理"
		this.setBorder(BorderFactory.createTitledBorder(null, "客人住房管理", 
					TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, 
					new Font("微软雅黑", Font.BOLD, 12), null));
	}
}
