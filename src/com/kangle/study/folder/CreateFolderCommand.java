package com.kangle.study.folder;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateFolderCommand extends AbstractAIFCommand{
	
	public CreateFolderCommand(){
		CreateFolderDialog dialog = new CreateFolderDialog();
		
		new Thread(dialog).start();
	}
}
