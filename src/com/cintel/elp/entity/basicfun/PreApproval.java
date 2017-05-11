package com.cintel.elp.entity.basicfun;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

/**
 * 
 * @author lishutao
 * 预案操作记录
 */
@Alias("PreApproval")
public class PreApproval  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Integer pre_approval_id;
	private PreOperate preOperate;
	private String approval_name;
	private String approval_result;
	private String approval_proposal;
	private Timestamp approval_time;
	public Integer getPre_approval_id() {
		return pre_approval_id;
	}
	public void setPre_approval_id(Integer pre_approval_id) {
		this.pre_approval_id = pre_approval_id;
	}
	public PreOperate getPreOperate() {
		return preOperate;
	}
	public void setPreOperate(PreOperate preOperate) {
		this.preOperate = preOperate;
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
		return "PreApproval [pre_approval_id=" + pre_approval_id + ", preOperate=" + preOperate + ", approval_name="
				+ approval_name + ", approval_result=" + approval_result + ", approval_proposal=" + approval_proposal
				+ ", approval_time=" + approval_time + "]";
	}
	
}
