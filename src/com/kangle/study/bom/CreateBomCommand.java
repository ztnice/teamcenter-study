package com.kangle.study.bom;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateBomCommand extends AbstractAIFCommand{
	public CreateBomCommand(){
		CreateBomDialog dialog = new CreateBomDialog();
		
		new Thread(dialog).start();
	}
}
