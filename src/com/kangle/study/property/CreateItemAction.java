package com.kangle.study.property;

import java.awt.Frame;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.kangle.study.form.CreateFormCommand;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.common.actions.AbstractAIFAction;
import com.teamcenter.rac.util.MessageBox;

public class CreateItemAction  extends AbstractAIFAction{

	private AbstractAIFApplication app;
	private String str;
	public CreateItemAction(AbstractAIFApplication arg0, String arg1) {
		super(arg0, arg1);
		this.app = arg0;
		this.str = arg1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		CreateItemCommand command = new CreateItemCommand();
		try {
			command.executeModal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MessageBox.post("产生了一个未知错误","错误",MessageBox.ERROR);
//			e.printStackTrace();
		}
	}

	

}
