package com.cintel.elp.entity.basicfun;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

/**
 * 
 * @author lishutao
 * 预案操作记录
 */
@Alias("PreOperate")
public class PreOperate  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Integer pre_operate_id;
	private Preproject preproject;
	private String operater_name;
	private String operate_type;
	private String operate_describe;
	private Timestamp operate_time;
	private boolean approval_state;
	private String approval_name;
	private String approval_result;
	private String approval_proposal;
	private Timestamp approval_time;
	
	public PreOperate() {
	}

	public PreOperate(String operater_name, String operate_type, String operate_descirbe, Timestamp operate_time, boolean approval_state) {
		this.operater_name = operater_name;
		this.operate_type = operate_type;
		this.operate_describe = operate_descirbe;
		this.operate_time = operate_time;
		this.approval_state = approval_state;
	}
	
	public Integer getPre_operate_id() {
		return pre_operate_id;
	}
	public void setPre_operate_id(Integer pre_operate_id) {
		this.pre_operate_id = pre_operate_id;
	}
	public Preproject getPreproject() {
		return preproject;
	}
	public void setPreproject(Preproject preproject) {
		this.preproject = preproject;
	}
	public String getOperater_name() {
		return operater_name;
	}
	public void setOperater_name(String operater_name) {
		this.operater_name = operater_name;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	public String getOperate_describe() {
		return operate_describe;
	}

	public void setOperate_describe(String operate_describe) {
		this.operate_describe = operate_describe;
	}

	public Timestamp getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Timestamp operate_time) {
		this.operate_time = operate_time;
	}
	public boolean isApproval_state() {
		return approval_state;
	}
	public void setApproval_state(boolean approval_state) {
		this.approval_state = approval_state;
	}
	public String getApproval_name() {
		return approval_name;
	}
	public void setApproval_name(String approval_name) {
		this.approval_name = approval_name;
	}
	public String getApproval_result() {
		return approval_result;
	}
	public void setApproval_result(String approval_result) {
		this.approval_result = approval_result;
	}
	public String getApproval_proposal() {
		return approval_proposal;
	}
	public void setApproval_proposal(String approval_proposal) {
		this.approval_proposal = approval_proposal;
	}
	public Timestamp getApproval_time() {
		return approval_time;
	}
	public void setApproval_time(Timestamp approval_time) {
		this.approval_time = approval_time;
	}

	@Override
	public String toString() {
		return "PreOperate [pre_operate_id=" + pre_operate_id + ", preproject=" + preproject + ", operater_name="
				+ operater_name + ", operate_type=" + operate_type + ", operate_describe=" + operate_describe
				+ ", operate_time=" + operate_time + ", approval_state=" + approval_state + ", approval_name="
				+ approval_name + ", approval_result=" + approval_result + ", approval_proposal=" + approval_proposal
				+ ", approval_time=" + approval_time + "]";
	}
}
