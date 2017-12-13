package com.kangle.study.form;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateFormCommand extends AbstractAIFCommand{
	
	public CreateFormCommand(){
		CreateFormDialog dialog = new CreateFormDialog();
		
		new Thread(dialog).start();
	}
}
