package com.kangle.study.dataSet;

import com.teamcenter.rac.aif.AbstractAIFCommand;

public class CreateDataSetCommand extends AbstractAIFCommand{
	public CreateDataSetCommand(){
		CreateDataSetDialog dialog = new CreateDataSetDialog();
		
		new Thread(dialog).start();
	}
}
