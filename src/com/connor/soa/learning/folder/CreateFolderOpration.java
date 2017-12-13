package com.connor.soa.learning.folder;

import java.util.ArrayList;
import java.util.List;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.common.create.BOCreateDefinitionFactory;
import com.teamcenter.rac.common.create.CreateInstanceInput;
import com.teamcenter.rac.common.create.IBOCreateDefinition;
import com.teamcenter.rac.common.create.ICreateInstanceInput;
import com.teamcenter.rac.common.create.SOAGenericCreateHelper;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;

public class CreateFolderOpration extends AbstractAIFOperation{

	private TCSession session;
	private AbstractAIFApplication app;
	private String folderName;
	private TCComponent component;
	
	
	public CreateFolderOpration(String name) {
		
		this.folderName=name;
	}


	/**
	 * 创建文件夹
	 */
	@Override
	public void executeOperation() throws Exception {
		//查看要创建的文件夹是否存在，然后在进行文件夹的创建
		app = AIFUtility.getCurrentApplication();
		component = (TCComponent) app.getTargetComponent();
		if(null == component){
			System.out.println("请选择一个文件夹");
			return;
		}
		//这里可以做一个递归查询
		else{
			execute();
		}
		
	}
	
	/**
	 * 用SOA创建文件夹的过程
	 * @throws TCException 
	 */
	public void execute() throws TCException{
		BOCreateDefinitionFactory factory = BOCreateDefinitionFactory.getInstance();
		this.session = (TCSession) app.getSession();
		IBOCreateDefinition definition = factory.getCreateDefinition(session, "Item");
		CreateInstanceInput instanceInput = new CreateInstanceInput(definition);
		List<ICreateInstanceInput> list = new ArrayList<>();
		list.add(instanceInput);
		List<TCComponent> tcComponentList = SOAGenericCreateHelper.create(session, definition, list);
		if(tcComponentList.isEmpty()){
			System.out.println("文件夹创建失败");
			return;
		}
		// 获得文件夹对象-真实对象类型是TCComponentFolder
		TCComponent folder = tcComponentList.get(0);
		folder.setStringProperty("object_name", folderName);
		// 将文件夹挂到目标下面
		if (!(component instanceof TCComponent)) {
			session.getUser().getNewStuffFolder().add("contents", folder);
			MessageBox.post("文件夹创建成功！\n当前对象:" + component + " 不是TCComponent，项目文件夹" + folderName + "保存到NewStuff文件夹下", "提示",
					MessageBox.WARNING);
			return;
		}
		TCComponent targetComp = (TCComponent) component;
		targetComp.add(targetComp.getDefaultPasteRelation(), folder);
		MessageBox.post("文件夹创建成功！\n项目文件夹" + folderName + "保存到" + targetComp + "下", "提示", MessageBox.INFORMATION);
	
	}
}
