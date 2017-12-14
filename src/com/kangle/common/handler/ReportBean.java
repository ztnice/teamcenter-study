package com.kangle.common.handler;

public class ReportBean {
	
	public String owning_user = "";  //发放申请者
	public String date_released = ""; //发放时间
	public String item_revision_id = "";   //发放图纸版本
	public String object_name = "";   //发放图纸名称
	public String item_id = "";   //发放图纸编号
	public String index = "";   //序号
	
	public ReportBean(String owning_user, String date_released, String item_revision_id, 
			String object_name, String item_id, String index) {

		this.owning_user = owning_user;
		this.date_released = date_released;
		this.item_id = item_id;
		this.object_name = object_name;
		this.item_revision_id = item_revision_id;
		this.index = index;
	}
	
	public ReportBean(){
		
	}
	
	public String getOwning_user() {
		return owning_user;
	}
	public void setOwning_user(String owning_user) {
		this.owning_user = owning_user;
	}
	public String getDate_released() {
		return date_released;
	}
	public void setDate_released(String date_released) {
		this.date_released = date_released;
	}
	public String getItem_revision_id() {
		return item_revision_id;
	}
	public void setItem_revision_id(String item_revision_id) {
		this.item_revision_id = item_revision_id;
	}
	public String getObject_name() {
		return object_name;
	}
	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}



}
