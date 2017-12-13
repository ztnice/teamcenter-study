package com.kangle.study.bom;


import com.teamcenter.rac.aif.AbstractAIFOperation;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentBOMWindow;
import com.teamcenter.rac.kernel.TCComponentBOMWindowType;
import com.teamcenter.rac.kernel.TCComponentItemRevision;
import com.teamcenter.rac.kernel.TCComponentItemRevisionType;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.kernel.TCTypeService;
import com.teamcenter.rac.util.MessageBox;

public class CreateBomOperation extends AbstractAIFOperation{

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
	

	public CreateBomOperation(InterfaceAIFComponent targetComponent, String text, String text2, TCSession session2) {
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
		
		TCComponentItemRevisionType itemType = (TCComponentItemRevisionType)session.getTypeComponent("ItemRevision");
		TCComponentItemRevision[] itemRevisions = itemType.findRevisions(itemId, revesionId);
		TCComponentItemRevision componentRevisoin = (TCComponentItemRevision) this.component;
		
		TCTypeService service = session.getTypeService();
		TCComponentBOMWindowType type = (TCComponentBOMWindowType) service.getTypeComponent("BOMWindow");
		//创建bomWidow
		TCComponentBOMWindow view  = type.create(null);
		
		
		//创建bomLine
		TCComponentBOMLine bomLine = view.setWindowTopLine(componentRevisoin.getItem(),componentRevisoin,null,null);
		bomLine.add("view",itemRevisions[0]);
		
		
		MessageBox.post("将" + itemId + "/" + revesionId + "挂载在" + component + "的BOM下成功", "成功", MessageBox.INFORMATION);
		
		
	}

}
