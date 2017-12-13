package com.kangle.form;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.stylesheet.AbstractRendering;
import com.teamcenter.rac.util.PropertyLayout;

public class CustomItemRevisionForm extends AbstractRendering{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] properties = {"current_name","user_data1","user_data2","user_data3"};
	
	private String[] properties_ZN = {"当前名称","用户数据1","用户数据2","用户数据3"};
	
	private TCComponentForm revision;
	public CustomItemRevisionForm(TCComponent arg0) throws Exception {
		super(arg0);
		// TODO Auto-generated constructor stub
		
		if(arg0 instanceof TCComponentForm){
			revision = (TCComponentForm) arg0;
		}else{
			return;
		}
		
		loadRendering();
	}

	public CustomItemRevisionForm(TCComponentType arg0) throws Exception {
		super(arg0);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void loadRendering() throws TCException {
		// TODO Auto-generated method stub
		init();
	}

	@Override
	public void saveRendering() {
		// TODO Auto-generated method stub
		
	}
	
	private void init() throws TCException{
		JPanel  panel = new JPanel();
		panel.setLayout(new PropertyLayout());
		for(int i = 0;i<properties.length;i++){
			JLabel label = new JLabel(properties_ZN[i]);
			JTextField field = new JTextField(10);
			field.setText(revision.getProperty(properties[i]));
			if(properties[i].equals("current_name")){
				field.setEditable(false);
			}
			panel.add(label,i+1+".1.left.top");
			panel.add(field, i+1+".2.left.top");
			
		}
		panel.setBackground(new Color(255,255,255));
		this.add(panel);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setVisible(true);
		
	}

}
