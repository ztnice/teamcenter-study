package com.kangle.study.soa.folder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentFolderType;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;
import com.teamcenter.rac.util.iTextField;

public class CreateFolderDialog extends AbstractAIFDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TCSession session;
	private JButton onButton;
	private JButton cancelButton;
	private JLabel label;
	private JTextField textField;
	
	public CreateFolderDialog() {
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		session = (TCSession) app.getSession();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		initUI();
	}

	public void initUI() {
		this.setSize(new Dimension(240, 140));
		this.setLayout(new FlowLayout());
		this.setTitle("创建文件夹对话框");
		//定义3个panel，分别是顶层panel，内容panel和按钮panel
		JPanel topPanel = new JPanel(new BorderLayout());
		JPanel connentPanel = new JPanel(new PropertyLayout());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		topPanel.setPreferredSize(new Dimension(220, 100));
		connentPanel.setPreferredSize(new Dimension(160, 60));

		this.textField = new iTextField(20);
		this.label = new JLabel("文件夹名称:");
		
		this.textField.setPreferredSize(new Dimension(100, 20));
		this.label.setPreferredSize(new Dimension(80, 20));
		
		
		
		
		this.onButton = new JButton("确定");
		this.cancelButton = new JButton("取消");
		
		
		this.onButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		
		
		buttonPanel.add(onButton);
		buttonPanel.add(cancelButton);
		connentPanel.add("1.1.left.top",label);
		connentPanel.add("1.2.left.top",textField);
		
		topPanel.add(buttonPanel,BorderLayout.SOUTH);
		topPanel.add(connentPanel, BorderLayout.NORTH);
		this.add(topPanel);
		this.setVisible(true);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(onButton)){
			CreateFolderOpration folderOperation = new CreateFolderOpration(textField.getText());
			session.queueOperation(folderOperation);
			this.dispose();
		}else{
			this.dispose();
		}
	}
}
