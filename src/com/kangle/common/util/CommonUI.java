package com.kangle.common.util;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.util.PropertyLayout;

public class CommonUI {

	////// 标签说明
	private JLabel lable_name;
	private JTextField Name_Field;

	private JLabel lable_Desc;
	private JTextArea Desc_Area;

	private JTextField Desc_Field;

	private JPanel inputPanel;

	private JLabel[] lab_labels;
	private JTextField[] tf_fields;
	private boolean combind=false;
	
	public CommonUI() {
		super();
	}

	public CommonUI(String lable_name, int name_width, String label_Desc, int Desc_width) {
		super();
		this.lable_name = new JLabel(lable_name);
		this.Name_Field = new JTextField(name_width);
		this.lable_Desc = new JLabel(label_Desc);
		this.Desc_Field = new JTextField(Desc_width);
	}

	public CommonUI(String lable_name, int name_width, String label_Desc, int Desc_width, int Desc_heigth) {
		super();
		this.lable_name = new JLabel(lable_name);
		this.Name_Field = new JTextField(name_width);
		this.lable_Desc = new JLabel(label_Desc);
		this.Desc_Area = new JTextArea(Desc_width, Desc_heigth);
	}

	public JPanel generateCommonLab_Tf_UI(String[] labels, int[] tfs) throws Exception {
		if (labels.length != tfs.length) {
			throw new Exception("请设置两组相同长度的label和fields");
		}
		if (labels.length == 0 || tfs.length == 0) {
			throw new Exception("需要传入相同长度的label和fields");
		}
		combind=true;
		lab_labels = new JLabel[tfs.length];
		tf_fields = new JTextField[tfs.length];
		inputPanel = new JPanel(new PropertyLayout());
		for (int i = 0; i < tfs.length; i++) {
			lab_labels[i] = new JLabel(labels[i]);
			tf_fields[i] = new JTextField(tfs[i]);
			inputPanel.add(i+1 + "." + 1 + ".left.top", lab_labels[i]);
			inputPanel.add(i+1 + "." + 2 + ".left.top", tf_fields[i]);
		}
		return inputPanel;
	}

	public JLabel getLable_name() {
		return lable_name;
	}

	public void setLable_name(JLabel lable_name) {
		this.lable_name = lable_name;
	}

	public JTextField getName_Field() {
		return Name_Field;
	}

	public void setName_Field(JTextField name_Field) {
		Name_Field = name_Field;
	}

	public JLabel getLable_Desc() {
		return lable_Desc;
	}

	public void setLable_Desc(JLabel lable_Desc) {
		this.lable_Desc = lable_Desc;
	}

	public JTextArea getDesc_Area() {
		return Desc_Area;
	}

	public void setDesc_Area(JTextArea desc_Area) {
		Desc_Area = desc_Area;
	}

	public JTextField getDesc_Field() {
		return Desc_Field;
	}

	public void setDesc_Field(JTextField desc_Field) {
		Desc_Field = desc_Field;
	}

	public JPanel getInputPanel() {
		return inputPanel;
	}

	public void setInputPanel(JPanel inputPanel) {
		this.inputPanel = inputPanel;
	}

	public JLabel[] getLab_labels() {
		return lab_labels;
	}

	public void setLab_labels(JLabel[] lab_labels) {
		this.lab_labels = lab_labels;
	}

	public JTextField[] getTf_fields() {
		return tf_fields;
	}

	public void setTf_fields(JTextField[] tf_fields) {
		this.tf_fields = tf_fields;
	}

	public boolean isCombind() {
		return combind;
	}

	public JPanel generateNameFieldAndDescField() {
		this.inputPanel = new JPanel(new PropertyLayout());
		inputPanel.add("1.1.left.top", lable_name);
		inputPanel.add("1.2.left.top", Name_Field);
		inputPanel.add("2.1.left.top", lable_Desc);
		inputPanel.add("2.2.left.top", Desc_Field);
		return inputPanel;
	}

	public JPanel generateNameFieldAndDescArea() {
		this.inputPanel = new JPanel(new PropertyLayout());
		inputPanel.add("1.1.left.top", lable_name);
		inputPanel.add("1.2.left.top", Name_Field);
		inputPanel.add("2.1.left.top", lable_Desc);
		inputPanel.add("2.2.left.top", Desc_Area);
		return inputPanel;
	}

	public JPanel generateBtnListPanel(String... btnName) {
		JPanel rootJPanel = new JPanel(new FlowLayout());
		for (int i = 0; i < btnName.length; i++) {
			rootJPanel.add(new JButton(btnName[i]));
		}
		return rootJPanel;
	}

	public JButton[] generateBtnListPanel(AbstractAIFDialog addItemDialog, JPanel rootJPanel, String... btnName) {

		JButton[] button = new JButton[btnName.length];
		for (int i = 0; i < btnName.length; i++) {
			button[i] = new JButton(btnName[i]);
			button[i].addActionListener((ActionListener) addItemDialog);
			rootJPanel.add(button[i]);
		}
		return button;
	}
}
