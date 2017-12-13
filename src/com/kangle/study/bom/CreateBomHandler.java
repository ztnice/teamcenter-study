package com.kangle.study.bom;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.kangle.study.form.CreateFormAction;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;

public class CreateBomHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		CreateBomAction action = new CreateBomAction(app, "创建一个物料清单");
		new Thread(action).start();
		return null;
	}

}
