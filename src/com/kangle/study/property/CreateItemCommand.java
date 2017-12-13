package com.kangle.study.property;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateItemCommand extends AbstractAIFCommand{
	public CreateItemCommand(){
		CreateItemDialog dialog = new CreateItemDialog();
		
		new Thread(dialog).start();
	}
}
