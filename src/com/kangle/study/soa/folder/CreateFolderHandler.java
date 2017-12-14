package com.kangle.study.soa.folder;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class CreateFolderHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		// TODO Auto-generated method stub
		CreateFolderDialog action = new CreateFolderDialog();
		new Thread(action).start();
		
		return null;				
	}
	
}
