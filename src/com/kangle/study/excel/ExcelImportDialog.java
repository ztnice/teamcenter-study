package com.kangle.study.excel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;

import com.connor.study.handlers.ExportExcel.ExportExcelDialog;
import com.connor.study.handlers.ExportExcel.ExportExcelOperation;
import com.kangle.common.handler.ReportBean;
import com.kangle.common.util.ExcelUtil07;
import com.teamcenter.rac.aif.AbstractAIFApplication;
import com.teamcenter.rac.aif.AbstractAIFDialog;
import com.teamcenter.rac.aif.kernel.AIFComponentContext;
import com.teamcenter.rac.aif.kernel.InterfaceAIFComponent;
import com.teamcenter.rac.aifrcp.AIFUtility;
import com.teamcenter.rac.commands.subscribe.DateTimeButton;
import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentBOMLine;
import com.teamcenter.rac.kernel.TCComponentType;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCProperty;
import com.teamcenter.rac.kernel.TCSession;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;

/**
 * 
 * @author haozt
 * 2017年12月14日
 */
public class ExcelImportDialog extends AbstractAIFDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * session对象
	 */
	private TCSession session;
	
	/**
	 * app对象
	 */
	private AbstractAIFApplication app;
	
	/**
	 * 名称标签
	 */
	private JLabel nameLabel;
	
	/**
	 * 开始标签
	 */
	private JLabel startLabel;
	
	/**
	 * 结束 标签
	 */
	private JLabel endLabel;
	
	/**
	 * 文件位置标签
	 */
	private JLabel fileLocationLabel;
	
	/**
	 * 确认按钮
	 */
	private JButton onButton;
	
	/**
	 * 取消按钮
	 */
	private JButton cancelButton;
	
	/**
	 * 选择按钮
	 */
	private JButton chooseButton;
	
	/**
	 * 开始日期按钮
	 */
	private DateTimeButton startTimeButton;
	
	/**
	 * 结束日期按钮
	 */
	private DateTimeButton endTimeButton;
	
	/**
	 * 选择Excel文件导出目标位置
	 */
	private JTextField textField;
	
	/**
	 * 文件选择器
	 */
	private JFileChooser fileChooser;
	
	/**
	 * 导出文件属性
	 */
	private List<ReportBean> beanList;
	
	private int number = 0;

	public ExcelImportDialog() {
		super();
		app = AIFUtility.getCurrentApplication();
		session = (TCSession) app.getSession();
		beanList = new ArrayList<>();
	}

	
	
	@Override
	public void run(){
		super.run();
		initUI();
	}
	
	/**
	 * 初始化界面设计
	 */
	public void initUI(){
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		//桌面路径
		String deskPath = fileSystemView.getDefaultDirectory().getPath();
		
		this.setSize(new Dimension(600, 800));
		this.setTitle("图纸下发统计");
		
		nameLabel = new JLabel("发起时间");
		startLabel = new JLabel("开始日期");
		startTimeButton = new DateTimeButton();
		endLabel = new JLabel("结束日期");
		endTimeButton = new DateTimeButton();
		fileLocationLabel = new JLabel("文件导出位置");
		textField = new JTextField(deskPath);
		//初始目录定义在用户的桌面
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(deskPath));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		onButton = new JButton("确认");
		cancelButton = new JButton("取消");
		chooseButton = new JButton("...");
		
		//添加按钮点击事件
		onButton.addActionListener(this);
		cancelButton.addActionListener(this);
		chooseButton.addActionListener(this);
		
		//窗口 属性窗口
		JPanel jPanel = new JPanel(new PropertyLayout());
		//线框
		jPanel.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.WHITE),"请选择日期"));
		//各元素在窗口中的位置
		jPanel.add("1.1.left.top",nameLabel);
		jPanel.add("1.2.left.top",startLabel);
		jPanel.add("1.3.left.top",startTimeButton);
		jPanel.add("1.4.left.top",endLabel);
		jPanel.add("1.5.left.top",endTimeButton);
		
		jPanel.add("2.3.left.top",chooseButton);
		jPanel.add("2.2.left.top",textField);
		jPanel.add("2.1.left.top",fileLocationLabel);
		
		//创建按钮的窗口
		JPanel buttonJPanel = new JPanel(new FlowLayout());
		buttonJPanel.add(onButton);
		buttonJPanel.add(cancelButton);
		
		JPanel topJPanel = new JPanel(new BorderLayout());
		
		topJPanel.add(buttonJPanel,BorderLayout.SOUTH);
		topJPanel.add(jPanel, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(topJPanel,BorderLayout.CENTER);
		this.setVisible(true);
		this.centerToScreen();
		this.pack();
		this.showDialog();

	}
	
	/**
	 * 文件选择按钮点击事件
	 */
	public void selectFileButtonEvent(){
		fileChooser.setFileSelectionMode(1);// 设定只能选择到文件夹
		int state = fileChooser.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句
		if (state == 1) {
			return;
		} else {
			File f = fileChooser.getSelectedFile();// f为选择到的目录
			textField.setText(f.getAbsolutePath());
		}
	}
	
	/**
	 * 确认按钮点击事件
	 */
	public void onButtonEvent(){
		InterfaceAIFComponent resultCompS = app.getTargetComponent();
		if (resultCompS instanceof TCComponentBOMLine) {
			List<TCComponent> revList = new ArrayList<TCComponent>();
				revList.add((TCComponent) resultCompS);
					try {
						getProperty(revList);
					} catch (TCException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					InputStream is = ExcelImportDialog.class.getResourceAsStream("导出报表.xlsx");
					try {
						ExcelUtil07.writeExcel(textField.getText()+"\\"+"导出报表.xlsx", is, beanList);
						MessageBox.post("导出报表成功", "INFO", MessageBox.INFORMATION);

					} catch (IOException e1) {
						e1.printStackTrace();
						MessageBox.post("导出报表失败", "ERROR", MessageBox.ERROR);

					}
			this.disposeDialog();
			this.dispose();
		}
		 else{
			MessageBox.post("导出报表失败，请选择一个bomLine", "error", MessageBox.ERROR);
			return;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object sourceObj = e.getSource();
		if (sourceObj.equals(this.chooseButton)) {
			selectFileButtonEvent();
		} else if (sourceObj.equals(this.onButton)) {
			ExcelImportOperation operation = new ExcelImportOperation(this);
			this.session.queueOperation(operation);
			
			
		} else if (sourceObj.equals(this.cancelButton)) {
			this.disposeDialog();
			this.dispose();
		}
	}
	
	/**
	 * 获取属性
	 * @throws TCException 
	 */
	public void getProperty(List<TCComponent> revList) throws TCException{
		if (revList == null) {
			System.out.println("revlist为空");
			return;
		}
		
		TCProperty[][] propssRev;
		if (revList.size() > 0 && revList.get(0) instanceof TCComponentBOMLine) {
			// 获取一组对象的多个属性，参数1是多个对象的集合，参数2是需要获得的属性集（这些属性在对象中必须有，否则会返回null）
			propssRev = TCComponentType.getTCPropertiesSet(revList, new String[] { "bl_child_id", "bl_line_name" });
			for (int i = 0; i < revList.size(); i++) {
				String ownuser = propssRev[i][0].toString();
				ReportBean bean = new ReportBean();
				TCComponentBOMLine ffsj = null;
				TCComponent component = (TCComponent) revList.get(i);
				if (component instanceof TCComponentBOMLine) {
					ffsj = (TCComponentBOMLine) component;
					bean.setIndex("" + (number + 1));
					number++;
					String ffsjRev = ffsj.getTCProperty("bl_rev_item_revision_id").getStringValue();
					String ffsjItemId = ffsj.getProperty("bl_item_item_id");
					String ffsjName = ffsj.getItem().getProperty("object_name");
					bean.setItem_revision_id(ffsjRev);
					bean.setItem_id(ffsjItemId);
					bean.setObject_name(ffsjName);
					if (ownuser != null) {
						bean.setOwning_user(ownuser);
					} else {
						bean.setOwning_user("");
					}
				} else{
					continue;
				}
				this.beanList.add(bean);

				boolean hasChildren = ffsj.hasChildren();//getTCProperty("bl_has_children").getBoolValue();
				if (hasChildren) {
					List<TCComponent> bl_children_list = new ArrayList<TCComponent>();
					AIFComponentContext[] bl_children_List = ffsj.getChildren();
					for (AIFComponentContext child : bl_children_List) {
						bl_children_list.add((TCComponent) child.getComponent());
					}
					getProperty(bl_children_list);
				}
				
				
			}
		
		
		}
			
		
		
	}
	
}
