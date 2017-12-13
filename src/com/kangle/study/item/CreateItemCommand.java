package com.kangle.study.item;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateItemCommand extends AbstractAIFCommand{
	public CreateItemCommand(){
		CreateItemDialog dialog = new CreateItemDialog();
		
		new Thread(dialog).start();
	}
}
