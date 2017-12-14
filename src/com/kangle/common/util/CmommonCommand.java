package com.kangle.common.util;
import java.awt.Frame;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFCommand;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.util.MessageBox;
public class CmommonCommand extends AbstractAIFCommand {
	private Object targetArray;
	private String type;
	/**
	 * 通用的AbstractAIFCommand子类，根据调用顺序调用这一层
	 * 
	 * @param frame
	 * @param app
	 * @param type
	 *            其他的都不重要，重要的是传入需要调用的下一层的AbstractAIFDialog子类，即业务层调用的Dialog
	 */
	public CmommonCommand(Frame frame, AbstractAIFApplication app, String type) {
		try {
			this.type = type;
			targetArray = app.getTargetComponent();
			if (targetArray != null) {
				if (this.type != null || "".equals(this.type)) {
					// 反射实例化传入的需要下级调用的AbstractAIFDialog子类，这个子类是具体业务相关了，不能再通用了
					//dialog是用来画界面的，如果不需要界面则可以不调用dialog了
					AbstractAIFDialog typeClass = (AbstractAIFDialog) Class.forName(type).newInstance();
					if (typeClass != null) {
						setRunnable(typeClass);
					}
				}
			} else {
				MessageBox.post("请选择对象", "提示 ", MessageBox.WARNING);
			}
		} catch (Exception exception) {
			MessageBox.post(frame, exception);
		}
	}

}
