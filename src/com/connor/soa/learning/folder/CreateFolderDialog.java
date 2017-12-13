package com.connor.soa.learning.folder;

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
		// super.run();
		initUI();
	}

	public void initUI() {
		setTitle("创建文件夹对话框");
		Dimension dimension = new Dimension();
		dimension.setSize(300, 70);
		setPreferredSize(dimension);
		JPanel jPanel = new JPanel(new FlowLayout());
		
		this.textField = new iTextField(20);
		this.label = new JLabel("文件夹名称:");
		this.onButton = new JButton("确定");
		this.cancelButton = new JButton("取消");
		this.onButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		
		jPanel.add(label);
		jPanel.add(textField);
		jPanel.add(onButton);
		jPanel.add(cancelButton);
		getContentPane().add(jPanel);
//		pack();
		centerToScreen(1.0D, 0.75D);
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
