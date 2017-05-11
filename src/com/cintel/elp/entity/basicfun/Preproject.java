package com.cintel.elp.entity.basicfun;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

/**
 * 
 * @author lishutao
 * 预案
 */
@Alias("Preproject")
public class Preproject extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private String pre_id;
	private String pre_name;
	private Timestamp pre_time;
	private PreCondition preCondition;
	private String match;
	
	public String getPre_id() {
		return pre_id;
	}
	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}
	public String getPre_name() {
		return pre_name;
	}
	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public Timestamp getPre_time() {
		return pre_time;
	}
	public void setPre_time(Timestamp pre_time) {
		this.pre_time = pre_time;
	}
	public PreCondition getPreCondition() {
		return preCondition;
	}
	public void setPreCondition(PreCondition preCondition) {
		this.preCondition = preCondition;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	@Override
	public String toString() {
		return "Preproject [pre_id=" + pre_id + ", pre_name=" + pre_name + ", pre_time=" + pre_time + ", preCondition="
				+ preCondition + ", match=" + match + "]";
	}
	
}
