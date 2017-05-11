package com.cintel.elp.task.utils;

import java.sql.Timestamp;

import org.jplus.hyberbin.excel.annotation.ExcelVoConfig;
import org.jplus.hyberbin.excel.annotation.Lang;
import org.jplus.hyberbin.excel.annotation.input.InputDicConfig;
import org.jplus.hyberbin.excel.annotation.output.OutputDicConfig;
import org.jplus.hyberbin.excel.annotation.validate.DicValidateConfig;
import org.jplus.hyberbin.excel.bean.BaseExcelVo;

import com.cintel.elp.entity.basicfun.PreCondition;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
@ExcelVoConfig
public class PreOperateExcel extends BaseExcelVo{
	
	@Lang(value = "预案编号")//Excel导出的配置
    private String pre_id;
    @Lang(value = "操作人")//Excel导出的配置
    private String operater_name;
    @Lang(value = "操作类型")//Excel导出的配置
	private String operate_type;
    @Lang(value = "操作详情")//Excel导出的配置
	private String operate_describe;
    @Lang(value = "操作时间")//Excel导出的配置
	private String operate_time;
    @Lang(value = "审核状态")//Excel导出的配置
	private boolean approval_state;
    @Lang(value = "审核人")//Excel导出的配置
	private String approval_name;
    @Lang(value = "审核结果")//Excel导出的配置
	private String approval_result;
    @Lang(value = "审核建议")//Excel导出的配置
	private String approval_proposal;
    @Lang(value = "审核时间")//Excel导出的配置
	private String approval_time;
    
    public static String [] getStrings(){
    	return new String [] {"pre_id","operater_name","operate_type", "operate_describe", "operate_time","approval_state","approval_name","approval_result","approval_proposal","approval_time"};
    }
    
	public PreOperateExcel() {
	}
	
	public PreOperateExcel(PreOperate preOperate) {
		this.pre_id = preOperate.getPreproject().getPre_id();
		this.operater_name = preOperate.getOperater_name();
		this.operate_type = preOperate.getOperate_type();
		this.operate_describe = preOperate.getOperate_describe();
		this.operate_time = preOperate.getOperate_time() == null ? null : preOperate.getOperate_time().toString();
		this.approval_state = preOperate.isApproval_state();
		this.approval_name = preOperate.getApproval_name();
		this.approval_result = preOperate.getApproval_result();
		this.approval_proposal = preOperate.getApproval_proposal();
		this.approval_time = preOperate.getApproval_time() == null ? null : preOperate.getApproval_time().toString();
	}
	
	public PreOperate getPreOperate(){//外部进行preproject的set操作。
		PreOperate preOperate = new PreOperate();
		
		preOperate.setOperater_name(this.operater_name);
		preOperate.setOperate_type(this.operate_type);
		preOperate.setOperate_describe(this.operate_describe);
		preOperate.setOperate_time(this.operate_time == null ? null :Timestamp.valueOf(this.operate_time));
		preOperate.setApproval_state(this.approval_state);
		preOperate.setApproval_name(this.approval_name);
		preOperate.setApproval_result(this.approval_result);
		preOperate.setApproval_proposal(this.approval_proposal);
		preOperate.setApproval_time(this.approval_time == null ? null :Timestamp.valueOf(this.approval_time));
		
		return preOperate;
	}
	
	@Override
	public int getHashVal() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getPre_id() {
		return pre_id;
	}
	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
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

	public String getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(String operate_time) {
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

	public String getApproval_time() {
		return approval_time;
	}

	public void setApproval_time(String approval_time) {
		this.approval_time = approval_time;
	}

	@Override
	public String toString() {
		return "PreOperateExcel [pre_id=" + pre_id + ", operater_name=" + operater_name + ", operate_type="
				+ operate_type + ", operate_describe=" + operate_describe + ", operate_time=" + operate_time
				+ ", approval_state=" + approval_state + ", approval_name=" + approval_name + ", approval_result="
				+ approval_result + ", approval_proposal=" + approval_proposal + ", approval_time=" + approval_time
				+ "]";
	}

}
