package com.kangle.study.property;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.PropertyLayout;


public class CreateItemDialog extends AbstractAIFDialog implements ActionListener{

	private static final long serialVersionUID = 1L;
	/*
	 *提示输入便签 
	 */
	private JLabel labItemName;
	
	/*
	 * 输入框
	 */
	private JTextField filedName;
	
	private TCSession session;
	
	private AbstractAIFApplication app;
	
	private JButton btn_on;
	
	private JButton btn_cancel;
	
	private JTextField itemDiscription;
	
	private JLabel item;
	
	private JTextField lab_propertyName;
	
	private JLabel lab_setProperty;

	
	public CreateItemDialog() {
		// TODO Auto-generated constructor stub
	app = AIFUtility.getCurrentApplication();
	session =(TCSession) app.getSession();
		
	}
	/**
	 * 实现监听
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btn_on)){
			CreateItemOperation operation = new CreateItemOperation(filedName.getText(),itemDiscription.getText(),lab_propertyName.getText(),session);
			session.queueOperation(operation);//执行operation
			this.dispose();
		}else if(e.getSource().equals(btn_cancel)){
			this.dispose();
		}
	}
	
	@Override
	public void run(){
		super.run();
		init();
		initUI();
		addListener();
	}
	
	/*
	 * 初始化本地字段
	 */
	private void init(){
		this.setPreferredSize(new Dimension(200, 200));
		labItemName = new JLabel("请输入Item名称");
		item = new JLabel("请输入item描述");
		lab_setProperty = new JLabel("请设置属性");
		lab_propertyName = new JTextField(16);
		itemDiscription = new JTextField(16);
		filedName = new JTextField(16);
		btn_on = new JButton("确认");
		btn_cancel = new JButton("取消");
	}
	
	/*
	 * 初始化UI窗口
	 */
	public void initUI(){
		
		this.setSize(new Dimension(250, 150));
		this.setTitle("新建一个item");
		
		JPanel topPanel = new JPanel();//顶层panel
		JPanel contentPanel = new JPanel();//内容panel
		JPanel btnPanel = new JPanel();//按钮panel
		
		
		//显示自定义大小
		topPanel.setPreferredSize(new Dimension(250, 100));
		contentPanel.setPreferredSize(new Dimension(240, 80));
		
		topPanel.setLayout(new FlowLayout());
		//
		contentPanel.setLayout(new PropertyLayout());
		btnPanel.setLayout(new FlowLayout());
		
		labItemName.setPreferredSize(new Dimension(100,20));
		filedName.setPreferredSize(new Dimension(100, 20));
		itemDiscription.setPreferredSize(new Dimension(100, 20));
		item.setPreferredSize(new Dimension(100, 20));
		lab_propertyName.setPreferredSize(new Dimension(100, 20));
		lab_setProperty.setPreferredSize(new Dimension(100, 20));
		
		contentPanel.add(labItemName,"1.1.left.top");//第一行 第一列
		contentPanel.add(filedName, "1.2.left.top");
		contentPanel.add(item, "2.1.left.top");
		contentPanel.add(itemDiscription,"2.2.left.top");
		
		contentPanel.add(lab_propertyName, "3.1.left.top");
		contentPanel.add(lab_setProperty, "3.2.left.top");
		
		btnPanel.add(btn_on);
		btnPanel.add(btn_cancel);
		
		topPanel.add(contentPanel);
		topPanel.add(btnPanel);
		
		this.add(topPanel);
		this.setVisible(true);
		
	}
	
	/*
	 * 添加按钮监听
	 */
	public void addListener(){
		btn_on.addActionListener(this);
		btn_cancel.addActionListener(this);
	}
		
}
