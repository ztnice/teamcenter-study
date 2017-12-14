package com.kangle.common.util;

import com.teamcenter.rac.aif.AbstractAIFUIApplication;
import com.teamcenter.rac.aifrcp.AIFUtility;

/**
 * 测试用通用Handler
 * 
 * @author Administrator
 *
 */
public class CommonHandler {

	public String dialogName;

	public CommonHandler() {
	}

	public CommonHandler(String dialogName) {
		this.dialogName = dialogName;
	}

	public void CallCommonAction() {
		if (this.dialogName != null || !"".equals(this.dialogName)) {
			AbstractAIFUIApplication app = AIFUtility.getCurrentApplication();
			CommonAction action = new CommonAction(app, dialogName);
			new Thread(action).start();
		} else {
			try {
				throw new Exception("Dialog名不能为空");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
