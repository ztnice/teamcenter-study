package com.kangle.study.folder;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentFolderType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.iTextField;

public class CreateFolderDialog extends AbstractAIFDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TCSession session;
	private String folderName;
	private TCComponent tccomponent;

	public CreateFolderDialog() {
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		session = (TCSession) app.getSession();
		tccomponent = (TCComponent) app.getTargetComponent();

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
		JPanel parentPanel = new JPanel(new FlowLayout());

		final iTextField itext = new iTextField(20);
		JLabel label = new JLabel("文件夹名称:");
		JButton button = new JButton("确定");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TCComponentFolderType t;
				try {

					t = (TCComponentFolderType) session.getTypeComponent("Folder");
					TCComponentFolder f = t.create(itext.getText(), "My Folder Description", "Folder");
					tccomponent.add("contents", f);
					gdispose();
				} catch (TCException e1) {
					// TODO Auto-generated catch block
					MessageBox.post("出现了一个未知错误", "错误", MessageBox.ERROR);

					e1.printStackTrace();
				}

			}

		});
		parentPanel.add(label);
		parentPanel.add(itext);
		parentPanel.add(button);

		getContentPane().add(parentPanel);
		pack();
		centerToScreen(1.0D, 0.75D);
		this.setVisible(true);
	}

	private void gdispose() {
		this.dispose();
	}
}
