package com.kangle.study.queryItem;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import com.kangle.study.form.CreateFormAction;
import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;

public class CreateItemQueryHandler extends AbstractHandler{

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		
		AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
		CreateItemQueryAction action = new CreateItemQueryAction(app, "创建一个查询构建器");
		new Thread(action).start();
		return null;
	}

}
