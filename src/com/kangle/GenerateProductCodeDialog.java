package com.kangle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.teamcenter.rac.kernel.TCComponent;
import com.teamcenter.rac.kernel.TCComponentForm;
import com.teamcenter.rac.kernel.TCException;
import com.teamcenter.rac.kernel.TCPropertyDescriptor;
import com.teamcenter.rac.util.MessageBox;
import com.teamcenter.rac.util.PropertyLayout;

public class GenerateProductCodeDialog implements ActionListener {
	private JFrame frame;
	/** 产品代号对话框 */
	private JDialog dialog;
	/** 顶级panel */
	private JPanel topPane;
	/************ 大框定义-start ***************/
	/** 编码申请框 */
	private JPanel codeApplyPanel;
	/** 是否修改号框 */
	private JPanel modifyCodePanel;
	/** 参数代号框 */
	private JPanel paramCodePanel;
	/** 特征号框 */
	private JPanel featureCodePanel;
	/** 表面处理框 */
	private JPanel surfacePanel;
	/** 序号框 */
	private JPanel serialPanel;
	/** 等级框 */
	private JPanel levelPanel;
	/** 最后的编码框 */
	private JPanel codePanel;
	/** 按钮panel */
	private JPanel btn_Panel;

	/** －－－－－－序号框－－－－－－ */
	/** 铰链区 */
	private JPanel hingePanel;
	/** 组合件区 */
	private JPanel groupPanel;
	/** 获取流水号区 */
	private JPanel serialNumPanel;

	/** -------------输入编码申请框的-------------- */
	/** 材料代号 */
	private JComboBox<String> CB_materialCode;
	/** 种类单元 */
	private JComboBox<String> CB_varietyCode;

	/** -------------是否修改号框------------------ */
	/** 修改号选择按钮 */
	private JCheckBox CK_modifyCode;

	/** -------------参数代号框-------------------- */
	/** 参数代号输入框 */
	private JTextField Field_paramCode;

	/** -------------特征号框 --------------------- */
	/** 特征代号单选按钮 */
	private ButtonGroup BtnG_featureCode;
	private JRadioButton RB_featureCode_left;
	private JRadioButton RB_featureCode_right;
	private JRadioButton RB_featureCode_nofeature;
	private Map<JRadioButton, String> Map_RB_featureCode;
	/** -------------按钮panel--------------------- */
	/** 申请按钮 */
	private JButton Btn_apply;
	/** 获取 */
	private JButton Btn_get;
	/** 退出按钮 */
	private JButton Btn_exit;

	/** -------------表面处理框 -------------------- */
	/** 表面处理小类 */
	private JComboBox<String> CB_surfaceCode;
	private DefaultComboBoxModel<String> surfaceCode_model;
	/** 表面处理大类 */
	private JComboBox<String> CB_surfaceCode_Type;

	/** -------------序号框 ------------------------- */

	/** ============ 铰链区 ============ */
	/** 铰链下拉框 */
	private ButtonGroup BtnG_teeath;
	private JRadioButton Btn_hingebtn_doubleteeth;
	private JRadioButton Btn_hingebtn_signalteeth;
	private Map<JRadioButton, String> Map_Btn_teeth;
	/** ============组合件区 ============ */
	/** 组合件下拉框 */
	private JComboBox<String> CB_groupType;
	private JCheckBox confirmIsGroup;
	private DefaultComboBoxModel<String> groupType_model;
	/** 手工序号输入框 */
	private JTextField Field_menualSerialNum;

	/** ============获取流水号区 ============ */
	/** 获取序号流水输入框 */
	private JTextField Field_serialNum;
	/** 获取序号流水按钮 */
	private JButton Btn_getSerialNum;

	/** -------------等级框 ---------------------- */
	/** 产品等级按钮组 */
	private ButtonGroup BtnG_product_level;
	/** 优等品 */
	private JRadioButton RB_level_good;
	/** 注塑件 */
	private JRadioButton RB_level_moulding;
	/** 一等品 */
	private JRadioButton RB_level_1;
	/** 合格品 */
	private JRadioButton RB_level_eligibility;

	private Map<JRadioButton, String> Map_RB_level;
	/** -------------最后的编码框 --------------- */
	/** 最后显示的代号输入框 */
	private JTextField Field_result;

	/** 最终的产品代号 */
	private StringBuilder code;

	/** 材料代号对应关系 */
	private Map<String, String> MapMaterialCode;
	/** 种类单元对应关系 */
	private Map<String, String[]> MapVarietyCode;
	/** 模拟首选项，材料 */
	private final String[] StrMaterialCode = { "含铅白铜:B", "无铅锌白铜:X", "蒙乃尔:M", "黄铜:H", "青铜:P", "铍青铜:Q", "纯钛:T", "β钛:T",
			"不锈钢:C", "铝合金:L" };
	/** 模拟首选项 ，种类 */
	private final String[] StrVarietyCode = { "镜腿-圆铜芯:T-", "镜腿-插芯:H-HP", "镜腿-光腿:L-LP", "镜腿-弹簧镜腿:Y-YP", "普通铰链-塑铰:A-AP",
			"普通铰链-对口铰:B-BP", "普通铰链-庄头:C-CP", "普通铰链-胶圈铰:E-", "弹簧铰链-固定件:G-", "弹簧铰链-芯轴:R-", "弹簧铰链-弹簧芯子(单牙、芯子):V-VP",
			"弹簧铰链-弹簧前铰:D-DP", "弹簧铰链-弹簧盒:F-FP", "弹簧铰链-弹簧铰链组合件:-PP", "角花:J-JP", "鼻梁:Z-ZP", "饰件:S-SP", "锁块:K-KP", "丝通:Q-",
			"标配件:X-XP" };
	/** 表面处理获得的对应关系 */
	private Map<String, Map<String, String>> MapSurfaceCode;

	/** 表面处理初始化时控制 */
	private boolean isInitSurfaceCode = true;

	/** 统一控制的字体 */
	private Font commonFont = new Font("黑体", Font.PLAIN, 15);
	/** 按钮框上方panel */
	private JPanel north;
	/** 下方按钮大的panel */
	private JPanel south;
	/** 颜色控制 */
	private Color commonColor = new Color(200, 200, 200);
	private String featureCode = "";
	/** 等级代码 */
	private String levelCode = "";

	/** 一个TC表单回想 */
	private TCComponentForm form;

	/** 需要更新的区域，如果我不写，可能是二期项目，嘿嘿嘿，到底加不加呢？不加可以二期要价！！ */
	private JTextField field;

	/** 获取外部传入的表单对象 */
	public void getTarget(TCComponent form, JTextField field) {
		this.form = (TCComponentForm) form;
		this.field = field;
	}

	public void run() {
		/* 开始初始化组件 */
		init();
		/* 开始画UI，没有完全执行 */
		drawUI();
		/* 添加监听 */
		addListener();
		/* 执行最后一步 */
		executeLastStep();

	}

	private void executeLastStep() {
		north.add(topPane);
		dialog.setLayout(new BorderLayout());
		dialog.add(north, BorderLayout.NORTH);
		dialog.add(south, BorderLayout.SOUTH);
		dialog.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - dialog.getWidth()) / 2,
				(Toolkit.getDefaultToolkit().getScreenSize().height - dialog.getHeight()) / 2);

		///////////////////////////////////// 最后显示的步骤,是最后一步////////////////////////////////////
		dialog.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * 画UI
	 */
	private void drawUI() {
		// 外框和dialog定义大小和布局
		dialog.setSize(840, 550);

		north.setSize(840, 450);
		north.setLocation(0, 0);

		south.setSize(840, 50);
		south.setLocation(0, 500);

		north.setLayout(new FlowLayout());
		south.setLayout(new FlowLayout());

		topPane.setPreferredSize(new Dimension(840, 450));
		topPane.setLocation(0, 0);
		topPane.setLayout(null);
		// 申请编号框
		addCodeApplyPanel();
		// 修改号框
		addModifyCodePanel();
		// 先加入外框

		// // 参数代号框
		addParamCodePanel();

		// 特征号框
		addFeatureCodePanel();

		// 表面处理框
		addSurfacePanel();

		// 序号框
		addSerialPanel();

		// 产品等级框
		addLevelPanel();

		// 编码框，最后显示
		addCodePanel();

		// 按钮框
		btn_Panel.setLayout(new FlowLayout());
		btn_Panel.add(Btn_get);
		btn_Panel.add(Btn_apply);
		btn_Panel.add(Btn_exit);
		// 按钮框
		/* 在顶级panel添加 */
		north.add(topPane);
		south.add(btn_Panel);
	}

	/** 添加显示最后的结果框 */
	private void addCodePanel() {
		JPanel top8 = new JPanel();
		top8.setLayout(new FlowLayout());
		top8.setSize(410, 80);
		top8.setLocation(400, 360);

		codePanel.setLayout(new GridLayout(2, 1));
		codePanel.setPreferredSize(new Dimension(400, 70));

		JLabel lab_code = new JLabel("编码");
		Field_result.setEditable(false);
		codePanel.add(lab_code);
		codePanel.add(Field_result);
		top8.add(codePanel);
		topPane.add(top8);
	}

	/** 添加等级框 */
	private void addLevelPanel() {
		JPanel top7 = new JPanel();
		top7.setLayout(new FlowLayout());
		top7.setSize(410, 70);
		top7.setLocation(400, 285);

		levelPanel.setPreferredSize(new Dimension(400, 60));
		levelPanel.setLayout(new FlowLayout());
		levelPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "产品等级"));

		int local = 20;
		for (JRadioButton btn_level : Map_RB_level.keySet()) {
			btn_level.setLocation(local += 10, 20);
			btn_level.setBorder(new EmptyBorder(5, 20, 0, 0));
			btn_level.setFont(commonFont);
			BtnG_product_level.add(btn_level);
			levelPanel.add(btn_level);
		}
		top7.add(levelPanel);
		topPane.add(top7);
	}

	/** 添加序号框 */
	private void addSerialPanel() {
		JPanel top6 = new JPanel();
		top6.setLayout(new FlowLayout());
		top6.setSize(410, 265);
		top6.setLocation(400, 10);

		serialPanel.setLayout(new FlowLayout());
		serialPanel.setPreferredSize(new Dimension(410, 260));
		serialPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "序号区"));

		// hingePanel.setPreferredSize(new Dimension(10, 100));

		hingePanel.setLayout(new GridLayout(1, 2));
		hingePanel.setPreferredSize(new Dimension(380, 50));
		hingePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "铰链"));
		Btn_hingebtn_signalteeth.setBorder(new EmptyBorder(0, 20, 0, 0));
		Btn_hingebtn_signalteeth.setFont(commonFont);
		Btn_hingebtn_doubleteeth.setFont(commonFont);

		hingePanel.add(Btn_hingebtn_signalteeth);
		hingePanel.add(Btn_hingebtn_doubleteeth);

		JPanel groupPanel_paren = new JPanel();
		groupPanel_paren.setPreferredSize(new Dimension(380, 120));
		groupPanel_paren.setLayout(new PropertyLayout());
		groupPanel_paren.add(confirmIsGroup, "1.1.left.top");
		groupPanel_paren.add(groupPanel, "2.1.left.top");
		confirmIsGroup.setBounds(-5, -5, 20, 20);

		groupPanel.setLayout(new PropertyLayout());
		groupPanel.setPreferredSize(new Dimension(380, 90));
		groupPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "组合件"));

		JLabel lab_type = new JLabel("组合件类型");
		lab_type.setBounds(10, 10, 100, 100);
		lab_type.setBorder(new EmptyBorder(0, 20, 0, 0));
		lab_type.setFont(commonFont);
		JLabel lab_num = new JLabel("手工序号");
		lab_num.setBounds(10, 40, 100, 100);
		lab_num.setBorder(new EmptyBorder(0, 20, 0, 0));
		lab_num.setFont(commonFont);
		Field_menualSerialNum.setPreferredSize(new Dimension(130, 26));
		initGT();
		CB_groupType.setPreferredSize(new Dimension(220, 24));
		CB_groupType.setFont(commonFont);
		Field_menualSerialNum.setPreferredSize(new Dimension(220, 24));
		groupPanel.add(lab_type, "1.1.left.top");
		groupPanel.add(CB_groupType, "1.2.left.top");
		groupPanel.add(lab_num, "2.1.left.top");
		groupPanel.add(Field_menualSerialNum, "2.2.left.top");

		serialNumPanel.setLayout(new FlowLayout());
		serialNumPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		serialNumPanel.add(Field_serialNum);
		serialNumPanel.add(Btn_getSerialNum);

		Field_serialNum.setPreferredSize(new Dimension(160, 26));
		Btn_getSerialNum.setPreferredSize(new Dimension(130, 26));

		serialPanel.add(hingePanel);
		serialPanel.add(groupPanel_paren);
		serialPanel.add(serialNumPanel);
		top6.add(serialPanel);
		topPane.add(top6);

	}

	/** 添加表面处理框 */
	private void addSurfacePanel() {
		JPanel top5 = new JPanel();
		top5.setLayout(new FlowLayout());
		top5.setSize(340, 80);
		top5.setLocation(20, 360);

		surfacePanel.setPreferredSize(new Dimension(340, 70));
		surfacePanel.setLayout(new PropertyLayout());
		surfacePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "表面处理"));

		JLabel lab_empty = new JLabel("");
		lab_empty.setPreferredSize(new Dimension(20, 0));
		lab_empty.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode_Type.setPreferredSize(new Dimension(100, 30));
		// CB_surfaceCode_Type.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode_Type.setFont(commonFont);

		CB_surfaceCode.setPreferredSize(new Dimension(150, 30));
		// CB_surfaceCode.setBorder(new EmptyBorder(5, 40, 0, 0));
		CB_surfaceCode.setFont(commonFont);
		// 添加下拉框
		CB_surfaceCode_Type.setSelectedIndex(0);
		// 想panel添加单选按钮
		surfacePanel.add(lab_empty, "1.1.left.top");
		surfacePanel.add(CB_surfaceCode_Type, "1.2.left.top");
		surfacePanel.add(CB_surfaceCode, "1.3.left.top");
		top5.add(surfacePanel);
		topPane.add(top5);
	}

	/** 添加特征框 */
	private void addFeatureCodePanel() {
		JPanel top4 = new JPanel();
		top4.setSize(340, 70);
		top4.setLocation(20, 285);
		top4.setLayout(new FlowLayout());
		featureCodePanel.setPreferredSize(new Dimension(340, 60));
		featureCodePanel.setLayout(new GridLayout(1, 1));
		featureCodePanel
				.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
						BorderFactory.createLineBorder(commonColor)), "特征代号"));
		for (JRadioButton btn_feature : Map_RB_featureCode.keySet()) {
			btn_feature.setBorder(new EmptyBorder(0, 20, 0, 0));
			btn_feature.setPreferredSize(new Dimension(70, 20));
			btn_feature.setFont(commonFont);
			BtnG_featureCode.add(btn_feature);
			featureCodePanel.add(btn_feature);
		}
		top4.add(featureCodePanel);
		topPane.add(top4);
	}

	/** 添加参数代码框 */
	private void addParamCodePanel() {
		JPanel top3 = new JPanel();
		top3.setSize(340, 70);
		top3.setLocation(20, 210);
		top3.setLayout(new FlowLayout());

		paramCodePanel.setPreferredSize(new Dimension(340, 60));
		paramCodePanel.setLayout(new PropertyLayout());
		paramCodePanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "参数代号"));

		JLabel lab_paramCode = new JLabel("参数代号");
		lab_paramCode.setFont(commonFont);
		// 设置大小和间距
		lab_paramCode.setPreferredSize(new Dimension(80, 20));
		lab_paramCode.setBorder(new EmptyBorder(3, 20, 0, 0));

		Field_paramCode.setPreferredSize(new Dimension(130, 26));
		paramCodePanel.add(lab_paramCode, "1.1.left.top");
		paramCodePanel.add(Field_paramCode, "1.2.left.top");
		top3.add(paramCodePanel);
		topPane.add(top3);
	}

	/** 添加是否修改框 */
	private void addModifyCodePanel() {
		JPanel top2 = new JPanel();
		top2.setSize(340, 70);
		top2.setLocation(20, 125);
		top2.setLayout(new FlowLayout());

		modifyCodePanel.setLayout(new GridLayout(1, 1));
		modifyCodePanel
				.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
						BorderFactory.createLineBorder(commonColor)), "修改号"));
		// 如果使用了布局管理器，则不能直接setSize，应使用setPreferredSize设置组件大小
		modifyCodePanel.setPreferredSize(new Dimension(340, 60));
		CK_modifyCode.setPreferredSize(new Dimension(40, 10));
		CK_modifyCode.setBorder(new EmptyBorder(5, 20, 5, 0));
		CK_modifyCode.setFont(commonFont);
		modifyCodePanel.add(CK_modifyCode);
		top2.add(modifyCodePanel);
		topPane.add(top2);

	}

	/** 添加编码申请框 */
	private void addCodeApplyPanel() {
		JPanel top = new JPanel();
		top.setSize(340, 110);
		top.setLocation(20, 10);
		top.setLayout(new FlowLayout());
		// 设置样式和布局
		codeApplyPanel.setPreferredSize(new Dimension(340, 100));
		codeApplyPanel.setLocation(0, 0);
		codeApplyPanel.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createLineBorder(commonColor)), "编码申请"));
		codeApplyPanel.setLayout(new PropertyLayout());
		// 添加标签元素
		JLabel lab_MaterialCode = new JLabel("材料代号");
		JLabel lab_VarietyCode = new JLabel("种类单元");
		lab_MaterialCode.setFont(commonFont);
		lab_VarietyCode.setFont(commonFont);
		lab_MaterialCode.setPreferredSize(new Dimension(80, 20));
		lab_VarietyCode.setPreferredSize(new Dimension(80, 20));
		lab_MaterialCode.setBorder(new EmptyBorder(6, 20, 0, 0));
		lab_VarietyCode.setBorder(new EmptyBorder(6, 20, 0, 0));

		CB_materialCode.setFont(commonFont);
		CB_varietyCode.setFont(commonFont);
		// // 添加组件
		codeApplyPanel.add(lab_MaterialCode, "1.1.left.top");
		codeApplyPanel.add(CB_materialCode, "1.2.left.top");
		codeApplyPanel.add(lab_VarietyCode, "2.1.left.top");
		codeApplyPanel.add(CB_varietyCode, "2.2.left.top");
		// // 设置大小
		CB_varietyCode.setPreferredSize(new Dimension(210, 30));
		CB_materialCode.setPreferredSize(new Dimension(210, 30));
		top.add(codeApplyPanel);
		topPane.add(top);
	}

	/**
	 * 初始化一些必要的对象
	 */
	private void init() {
		// frame初始化
		frame = new JFrame("生成产品代号");
		// 模式对话框，强占窗口
		dialog = new JDialog(frame, "生成产品代号", true);
		/* 初始化顶级panel */
		south = new JPanel();
		north = new JPanel();
		topPane = new JPanel();
		/*------------------ 申请panel初始化 ----------------*/
		codeApplyPanel = new JPanel();
		// 材料号下拉框初始化
		CB_materialCode = new JComboBox<String>();
		// 种类单元下拉框初始化
		CB_varietyCode = new JComboBox<String>();
		// 对应关系
		MapMaterialCode = new HashMap<String, String>();
		//
		MapVarietyCode = new HashMap<String, String[]>();
		/*------------------ 申请panel初始化 ----------------*/

		/*------------------ 初始化修改号选择框----------------*/
		modifyCodePanel = new JPanel();
		CK_modifyCode = new JCheckBox("是否修改");
		/*------------------ 初始化修改号选择框----------------*/

		/*------------------ 初始化参数代号输入框----------------*/
		paramCodePanel = new JPanel();
		Field_paramCode = new JTextField(17);
		/*------------------ 初始化参数代号输入框----------------*/

		/*------------------ 初始化特征代号单选框----------------*/
		featureCodePanel = new JPanel();
		RB_featureCode_left = new JRadioButton("左");
		RB_featureCode_right = new JRadioButton("右");
		RB_featureCode_nofeature = new JRadioButton("无特征");
		BtnG_featureCode = new ButtonGroup();
		Map_RB_featureCode = new LinkedHashMap<JRadioButton, String>();
		Map_RB_featureCode.put(RB_featureCode_left, "L");
		Map_RB_featureCode.put(RB_featureCode_right, "R");
		Map_RB_featureCode.put(RB_featureCode_nofeature, "");
		/*------------------ 初始化特征代号单选框----------------*/

		/*------------------ 表面处理框----------------*/
		surfacePanel = new JPanel();
		CB_surfaceCode = new JComboBox<>();
		CB_surfaceCode_Type = new JComboBox<>();
		surfaceCode_model = new DefaultComboBoxModel<>();
		/*------------------ 表面处理框----------------*/

		/*------------------ 序号框----------------*/
		serialPanel = new JPanel();
		hingePanel = new JPanel();
		groupPanel = new JPanel();
		serialNumPanel = new JPanel();
		BtnG_teeath = new ButtonGroup();
		Btn_hingebtn_doubleteeth = new JRadioButton("双牙");
		Btn_hingebtn_signalteeth = new JRadioButton("单牙");
		BtnG_teeath.add(Btn_hingebtn_doubleteeth);
		BtnG_teeath.add(Btn_hingebtn_signalteeth);
		Map_Btn_teeth = new HashMap<JRadioButton, String>();
		Map_Btn_teeth.put(Btn_hingebtn_doubleteeth, "2");
		Map_Btn_teeth.put(Btn_hingebtn_signalteeth, "1");

		groupType_model = new DefaultComboBoxModel<>();
		CB_groupType = new JComboBox<>();
		Field_menualSerialNum = new JTextField();
		confirmIsGroup = new JCheckBox("是否组合件");

		Field_serialNum = new JTextField(16);
		Btn_getSerialNum = new JButton("获取流水序号");
		/*------------------ 序号框----------------*/

		/*------------------ 等级框----------------*/
		levelPanel = new JPanel();

		BtnG_product_level = new ButtonGroup();
		RB_level_good = new JRadioButton("优等品");
		RB_level_moulding = new JRadioButton("注塑件");
		RB_level_1 = new JRadioButton("一等品");
		RB_level_eligibility = new JRadioButton("合格品");
		Map_RB_level = new LinkedHashMap<JRadioButton, String>();
		Map_RB_level.put(RB_level_good, "1");
		Map_RB_level.put(RB_level_moulding, "2");
		Map_RB_level.put(RB_level_1, "9");
		Map_RB_level.put(RB_level_eligibility, "");
		/*------------------ 等级框----------------*/

		/*------------------ 最后的编码框----------------*/
		codePanel = new JPanel();
		Field_result = new JTextField();
		/*------------------ 最后的编码框----------------*/

		/*------------------ 按钮框初始化----------------*/
		btn_Panel = new JPanel();
		// 申请按钮初始化
		Btn_apply = new JButton("申请");
		// 获取代号
		Btn_get = new JButton("获取代号");
		// 退出按钮初始化
		Btn_exit = new JButton("退出");
		/*------------------ 按钮框初始化----------------*/
		// 最终结果初始化
		code = new StringBuilder();
		// 装填值初始化，装填下拉框初始值
		initSurfaceCode();
		initCB();
	}

	/** 初始化表面处理 */
	private void initSurfaceCode() {
		// 获取首选项////
		// 滚镀
		Map<String, String> barrelPlating = new HashMap<String, String>();
		// 挂镀
		Map<String, String> rackPlating = new HashMap<String, String>();
		// 喷漆
		Map<String, String> sprayPlating = new HashMap<String, String>();

		/*** 装载 ***/
		barrelPlating.put("镀金", "G10");
		barrelPlating.put("日本金", "G20");
		barrelPlating.put("镀玫瑰金", "G30");
		barrelPlating.put("镀镍", "G01");
		barrelPlating.put("镀银", "G02");
		barrelPlating.put("镀钯", "G03");
		barrelPlating.put("镀铜", "G04");
		barrelPlating.put("镀锌", "G05");
		barrelPlating.put("镀铬", "G06");
		barrelPlating.put("镀锡", "G07");
		barrelPlating.put("镀铜锡", "G08");

		rackPlating.put("镀金", "H10");
		rackPlating.put("日本金", "H20");
		rackPlating.put("镀玫瑰金", "H30");
		rackPlating.put("镀镍", "H01");
		rackPlating.put("镀银", "H02");
		rackPlating.put("镀钯", "H03");
		rackPlating.put("镀铜", "H04");
		rackPlating.put("镀锌", "H05");
		rackPlating.put("镀铬", "H06");
		rackPlating.put("镀锡", "H07");
		rackPlating.put("镀铜锡", "H08");

		sprayPlating.put("金色", "P10");
		sprayPlating.put("蓝色", "P20");
		sprayPlating.put("绿色", "P30");
		sprayPlating.put("枪色", "P40");
		sprayPlating.put("红色", "P50");
		sprayPlating.put("紫色", "P60");

		MapSurfaceCode = new HashMap<String, Map<String, String>>();
		MapSurfaceCode.put("滚镀", barrelPlating);
		MapSurfaceCode.put("挂镀", rackPlating);
		MapSurfaceCode.put("喷漆", sprayPlating);
		CB_surfaceCode.setModel(surfaceCode_model);
		for (String key : MapSurfaceCode.keySet()) {
			CB_surfaceCode_Type.addItem(key);
			if (isInitSurfaceCode) {
				for (String codes : MapSurfaceCode.get(key).keySet()) {
					surfaceCode_model.addElement(codes);
				}
				isInitSurfaceCode = false;
			}
		}
	}

	/**
	 * 初始化材料代号和种类
	 */
	private void initCB() {
		// 获取首选项
		for (String item : StrMaterialCode) {
			String[] result = item.split(":");
			String Mater = result[0];
			String code = result[1];
			MapMaterialCode.put(Mater, code);
			CB_materialCode.addItem(Mater);
		}
		for (String item : StrVarietyCode) {
			String[] result = item.split(":");
			String type = result[0];
			String[] code = result[1].split("-");
			MapVarietyCode.put(type, code);
			CB_varietyCode.addItem(type);
		}
	}

	/**
	 * 初始化组合件类型下拉框
	 */
	private void initGT() {
		// 也有可能是获取首选项的
		Map<String, String> groupType = new HashMap<String, String>();
		groupType.put("弹簧盒组合件", "");
		groupType.put("弹簧芯子组合件", "");
		groupType.put("弹簧铰链组合件", "");
		groupType.put("锁块(形状规则)", "");
		groupType.put("锁块(形状不规则)", "");
		groupType.put("角花", "");
		groupType.put("鼻梁", "");
		groupType.put("饰件", "");
		groupType.put("丝通", "");
		groupType.put("特殊种类", "");
		CB_groupType.setModel(groupType_model);
		for (String key : groupType.keySet()) {
			groupType_model.addElement(key);
		}
		CB_groupType.setEnabled(false);
		// confirmIsGroup.setEnabled(false);
		Field_menualSerialNum.setEnabled(false);
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		/* 申请按钮 */
		Btn_apply.addActionListener(this);
		/* 退出按钮 */
		Btn_exit.addActionListener(this);
		/* 产品等级 */
		for (JRadioButton btn_level : Map_RB_level.keySet()) {
			btn_level.addActionListener(this);
		}
		/* 特征 */
		for (JRadioButton btn_feature : Map_RB_featureCode.keySet()) {
			btn_feature.addActionListener(this);
		}
		/* 表面处理 */
		CB_surfaceCode_Type.addActionListener(this);
		confirmIsGroup.addActionListener(this);
		Btn_get.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* 申请之后拼接字符串，并传到表单上，拼接的顺序是有要求的 */
		if (e.getSource().equals(Btn_get)) {
			String code = execute();
			Field_result.setText(code);
			System.out.println(code);
		}

		else if (e.getSource().equals(Btn_apply)) {
			if (this.code == null || "".equals(this.code) || code.length() <= 0)
				execute();
			try {
				// List<TCPropertyDescriptor> des =
				// form.getDisplayablePropertyDescriptors();
				form.lock();
				form.setProperty("kh3_cpdh", code.toString());
				form.save();
				form.unlock();
				form.refresh();
				field.setText(code.toString());
			} catch (TCException e1) {
				e1.printStackTrace();
			}
			dialog.dispose();
			MessageBox.post("已自动生成产品代号:" + code, "成功", MessageBox.INFORMATION);
		}
		/* 退出对话框 */
		else if (e.getSource().equals(Btn_exit)) {
			dialog.dispose();
		} else if (Map_RB_level.containsKey(e.getSource())) {
			levelCode = Map_RB_level.get(e.getSource());
		} else if (e.getSource().equals(CB_surfaceCode_Type)) {
			surfaceCode_model.removeAllElements();
			for (String string : MapSurfaceCode.get(CB_surfaceCode_Type.getSelectedItem()).keySet()) {
				surfaceCode_model.addElement(string);
			}
		} else if (Map_RB_featureCode.containsKey(e.getSource())) {
			featureCode = Map_RB_featureCode.get(e.getSource());
		} else if (e.getSource().equals(confirmIsGroup)) {
			if (confirmIsGroup.isSelected()) {
				CB_groupType.setEnabled(true);
				Field_menualSerialNum.setEnabled(true);
			} else {
				CB_groupType.setEnabled(false);
				Field_menualSerialNum.setEnabled(false);
			}
		}
	}

	private String execute() {
		code = new StringBuilder();
		String key = (String) CB_materialCode.getSelectedItem();
		code.append(MapMaterialCode.get(key));
		key = (String) CB_varietyCode.getSelectedItem();
		if (confirmIsGroup.isSelected()) {
			code.append(MapVarietyCode.get(key)[1]);
		} else {
			code.append(MapVarietyCode.get(key)[0]);
		}

		code.append(Field_serialNum.getText());
		if (CK_modifyCode.isSelected())
			code.append("-A");
		key = Field_paramCode.getText();
		code.append(key);
		code.append(levelCode);
		code.append(featureCode);
		code.append("/");
		key = (String) CB_surfaceCode_Type.getSelectedItem();
		Map<String, String> v = MapSurfaceCode.get(key);
		key = v.get(CB_surfaceCode.getSelectedItem());
		code.append(key);
		return code.toString();
	}
	public static void main(String[] args) {
		GenerateProductCodeDialog dialog  = new GenerateProductCodeDialog();
		dialog.run();
	}
}
