package com.kangle.study.item;

import javax.swing.ScrollPaneLayout;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.kangle.study.form.CreateFormAction;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;

public class CreateItemHandler extends AbstractHandler{
	
	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		CreateItemAction action = new CreateItemAction(app, "创建一个item");
		new Thread(action).start();
		return null;
	}

}
