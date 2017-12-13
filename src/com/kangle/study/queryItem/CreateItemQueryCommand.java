package com.kangle.study.queryItem;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateItemQueryCommand extends AbstractAIFCommand{
	public CreateItemQueryCommand(){
		CreateItemQueryDialog dialog = new CreateItemQueryDialog();
		
		new Thread(dialog).start();
	}
}
