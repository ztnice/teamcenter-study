package com.kangle.study.form;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentFolder;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCComponentFormType;
import com.teamcenter.rac.kernel.TCComponentItem;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.iTextField;

public class CreateFormDialog extends AbstractAIFDialog implements ActionListener{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TCSession session;
	private String folderName;
	private TCComponent tccomponent;
	private JDialog jDialog ;
	private AbstractAIFUIApplication app;
	private Object button;
	public CreateFormDialog(){
		this.app = AIFUtility.getCurrentApplication();
		session = (TCSession) app.getSession();
		tccomponent = (TCComponent) app.getTargetComponent();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// super.run();
		initUI();
		jDialog = this;
	}

	public void initUI() {
		
		setTitle("创建表单");
		Dimension dimension = new Dimension();
		dimension.setSize(300, 70);
		setPreferredSize(dimension);
		JPanel parentPanel = new JPanel(new FlowLayout());
		
		final iTextField itext = new iTextField(20);
		JLabel label = new JLabel("表单名称:");
		JButton button = new JButton("确定");
		button.addActionListener(this);
		button.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				TCComponentFormType t;
				
				try {
					
					t = (TCComponentFormType) session.getTypeComponent("ItemRevision Master");
					TCComponentForm f =  t.create(itext.getText(), "My Form Description","ItemRevision Master");
//					System.out.println(tccomponent.getType());
					if(tccomponent instanceof TCComponentFolder){
						TCComponentFolder folder = (TCComponentFolder) app.getTargetComponent();
						TCComponent folders[] = folder.getRelatedComponents("contents");	
						boolean isRepeat = CreateFormDialog.checkRepeat(folders,itext.getText());
						if(isRepeat){
							MessageBox.post("傻吊，名字重复了，改下名字","提示",MessageBox.ERROR);
						}else{
						tccomponent.add("contents", f);
						jDialog.dispose();
						}
					}
					else if(tccomponent instanceof TCComponentItem){
						tccomponent.add("IMAN_reference", f);
						jDialog.dispose();
					}else if(tccomponent instanceof TCComponentItemRevision){
						tccomponent.add("IMAN_specification", f);
						
						jDialog.dispose();
					}
				
				} catch (TCException e1) {
					// TODO Auto-generated catch block
					MessageBox.post("出现了一个未知错 误","错误",MessageBox.ERROR);
					
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
	public static boolean checkRepeat(TCComponent[] components,String str){
		for(TCComponent component : components){
//			System.out.println(component.toString());
			if(str.equals(component.toString())){
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
