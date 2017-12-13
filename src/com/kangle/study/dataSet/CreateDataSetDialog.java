package com.kangle.study.dataSet;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileSystemView;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;


public class CreateDataSetDialog extends AbstractAIFDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private TCSession session;
	
	private AbstractAIFApplication app;
	
	/*
	 * 确认按钮
	 */
	private JButton onButton;
	
	/*
	 * 取消按钮
	 */
	private JButton cancelButton;
	
	/*
	 * 描述标签说明
	 */
	private JLabel label;
	
	/*
	 * 选择文件路径框
	 */
	private JTextField fieldPath;
	
	private String path;
	
	/*
	 * 选择文件按钮
	 */
	private JButton pathButton ;
	
	JLabel folder= new JLabel("文件名");
	
	JTextField folderName = new JTextField(20);
	
	JLabel folderDesc = new JLabel("文件描述");
	
	/*
	 * 文件描述域大小
	 */
	JTextArea folderDescArea = new JTextArea(6,16);
	
	/*
	 * 定义fileChoose的时候也定义了路径
	 */
	private JFileChooser  fileChooser = new JFileChooser();
	
	
	public CreateDataSetDialog() {
		this.app = AIFUtility.getCurrentApplication();
		this.session = (TCSession) app.getSession();
	}
	/**
	 * 实现监听
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object btn = e.getSource();
		if(btn.equals(onButton)){
			if(folderName.getText()!= null && !"".equals(folderName.getText()) ){
				CreateDataSetOperation dataSetOpration = new CreateDataSetOperation(
						app.getTargetComponent(),folderName.getText(),session, folderDescArea.getText()
						, path);
				session.queueOperation(dataSetOpration,true);
				this.disposeDialog();
				this.dispose();
			}else{
				MessageBox.post("文件名称不能为空","提示",MessageBox.ERROR);
			}
		}else if(btn.equals(pathButton)){
			selectChooseButtonListener();
		}else{
			this.disposeDialog();
			this.dispose();
		}
	}
	
	@Override
	public void run(){
		super.run();
		initUI();
	}
	
	/**
	 * 初始化本地字段
	 */
	private void init(){
		
	}
	
	/**
	 * 初始化UI窗口
	 */
	public void initUI(){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		//桌面路径获取
		String deskPath = fsv.getHomeDirectory().getPath();
		this.setSize(new Dimension(600, 400));
		this.setTitle("上传文件");
		this.label = new JLabel("选择文件:");
		this.fieldPath = new JTextField(deskPath);

		this.pathButton = new JButton("...");
		this.fileChooser = new JFileChooser();

		this.fileChooser.setCurrentDirectory(new File(deskPath));// 文件选择器的初始目录定为当前用户桌面
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		this.onButton = new JButton("确定");
		this.cancelButton = new JButton("取消");

		this.onButton.addActionListener(this);
		this.cancelButton.addActionListener(this);
		this.pathButton.addActionListener(this);

		JPanel centerPanel = new JPanel(new PropertyLayout());
		centerPanel.add("1.1.left.top", folder);
		centerPanel.add("1.2.left.top", folderName);
		centerPanel.add("2.1.left.top", folderDesc);
		centerPanel.add("2.2.left.top", folderDescArea);

		centerPanel.add("3.1.left.top", label);

		centerPanel.add("3.2.left.top", fieldPath);

		centerPanel.add("3.3.left.top", pathButton);

		JPanel rootJPanel = new JPanel(new FlowLayout());
		rootJPanel.add(onButton);
		rootJPanel.add(cancelButton);

		this.setLayout(new BorderLayout());
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(rootJPanel, BorderLayout.SOUTH);
		this.setVisible(true);
		this.centerToScreen();
		// this.pack();
		this.showDialog();
		
		
		
	}
	
	/*
	 * 添加按钮监听
	 */
	public void selectChooseButtonListener(){
		int state = fileChooser.showOpenDialog(null);
		if(state == 1){//确定
			return;
		}else{
			File file = fileChooser.getSelectedFile();
			path = file.getAbsolutePath();
			
		}
	}
		
}
