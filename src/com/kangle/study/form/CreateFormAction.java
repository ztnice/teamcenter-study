package com.kangle.study.form;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.common.actions.AbstractAIFAction;
import com.teamcenter.rac.util.MessageBox;

public class CreateFormAction extends AbstractAIFAction{	
	private AbstractAIFApplication app;
	private String str;
	public CreateFormAction(AbstractAIFApplication arg0, String arg1) {
		super(arg0, arg1);
		this.app = arg0;
		this.str = arg1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		CreateFormCommand command = new CreateFormCommand();
		try {
			command.executeModal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MessageBox.post("产生了一个未知错误","错误",MessageBox.ERROR);
//			e.printStackTrace();
		}
	}
	
}
