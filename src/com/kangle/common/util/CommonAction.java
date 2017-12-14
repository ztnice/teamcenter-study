package com.kangle.common.util;

import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.common.actions.AbstractAIFAction;
import com.teamcenter.rac.util.MessageBox;

public class CommonAction extends AbstractAIFAction {
	private AbstractAIFApplication app;
	private String type;

	/**
	 * 一个通用的AbstractAIFAction子类，根据调用顺序可以调用这一层
	 * 
	 * @param app
	 *            根据继承AbstractAIFAction要求，需要传入AbstractAIFApplication，虽然没什么用，
	 *            但是要求实现父类构造函数
	 * @param type
	 *            根据调用顺序，需要调用具体的AbstractAIFDialog的子类
	 */
	public CommonAction(AbstractAIFApplication app, String type) {
		super(app, type);
		this.app = app;
		this.type = type;
	}

	@Override
	public void run() {
		try {
			AbstractAIFCommand abstractaifcommand = new CmommonCommand(parent, app, type);
			abstractaifcommand.executeModal();
		} catch (Exception exception) {
			MessageBox.post(parent, exception);
		}

	}

}
