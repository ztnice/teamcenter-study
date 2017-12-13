package com.kangle.study.queryItem;


import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMWindow;
import com.teamcenter.rac.kernel.TCComponentBOMWindowType;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemRevisionType;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTextService;
import com.teamcenter.rac.kernel.TCTypeService;
import com.teamcenter.rac.util.MessageBox;

public class CreateItemQueryOperation extends AbstractAIFOperation{

	private InterfaceAIFComponent component;
	
	private TCSession session;
	
	/*
	 * 零组件id
	 */
	private String itemId;
	
	/*
	 * 零组件版本id
	 */
	private String revesionId;
	

	public CreateItemQueryOperation(InterfaceAIFComponent targetComponent, String text, String text2, TCSession session2) {
		this.component = targetComponent;
		this.itemId = text;
		this.session = session2;
		this.revesionId = text2;
	
	
	}



	@Override
	public void executeOperation() throws Exception {
		if(!(component instanceof TCComponentItemRevision)){
			MessageBox.post("请选择在零组件下面挂载","错误",MessageBox.ERROR);
			return;
		}
		if(null == itemId || "".equals(itemId)){
			MessageBox.post("请输入零组件id","错误",MessageBox.ERROR);
			return;
		}
		if(null == revesionId || "".equals(revesionId)){
			MessageBox.post("请输入零组件版本号","错误",MessageBox.ERROR);
			return;
		}
		
		TCTextService tcTextService = session.getTextService();
		String[] context = {"item_revision_id,item_id"};
		String[] asKeys = tcTextService.getTextValues(context);
		String[] asValues = {"itemId","revesionId"};
		
		InterfaceAIFComponent[] interfaceAifComponent = session.search("custom_hzt", asKeys, asValues);
		
//		TCComponentItemRevisionType itemType = (TCComponentItemRevisionType)session.getTypeComponent("ItemRevision");
		
		TCComponentItemRevision itemRevision = (TCComponentItemRevision) interfaceAifComponent[0];
		TCComponentItemRevision componentRevisoin = (TCComponentItemRevision) this.component;
		
		TCTypeService service = session.getTypeService();
		TCComponentBOMWindowType type = (TCComponentBOMWindowType) service.getTypeComponent("BOMWindow");
		//创建bomWidow
		TCComponentBOMWindow view  = type.create(null);
		
		
		//创建bomLine
		TCComponentBOMLine bomLine = view.setWindowTopLine(componentRevisoin.getItem(),componentRevisoin,null,null);
		bomLine.add("view",itemRevision);
		
		
		MessageBox.post("将" + itemId + "/" + revesionId + "挂载在" + component + "的BOM下成功", "成功", MessageBox.INFORMATION);
		
		
	}

}
