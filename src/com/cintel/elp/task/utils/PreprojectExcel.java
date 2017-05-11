package com.cintel.elp.task.utils;

import java.sql.Timestamp;

import org.jplus.hyberbin.excel.annotation.ExcelVoConfig;
import org.jplus.hyberbin.excel.annotation.Lang;
import org.jplus.hyberbin.excel.annotation.input.InputDicConfig;
import org.jplus.hyberbin.excel.annotation.output.OutputDicConfig;
import org.jplus.hyberbin.excel.annotation.validate.DicValidateConfig;
import org.jplus.hyberbin.excel.bean.BaseExcelVo;

import com.cintel.elp.entity.basicfun.PreCondition;
import com.cintel.elp.entity.basicfun.Preproject;
@ExcelVoConfig
public class PreprojectExcel extends BaseExcelVo{
	@Lang(value = "预案编号")//Excel导出的配置
    private String pre_id;
    @Lang(value = "预案名称")//Excel导出的配置
    private String pre_name;
    @Lang(value = "灾害类型")//Excel导出的配置
    private String pre_kind;
    @Lang(value = "灾害强度")//Excel导出的配置
	private double strength;
    @Lang(value = "受灾范围")//Excel导出的配置
	private double area;
    @Lang(value = "灾害人口")//Excel导出的配置
	private Integer people;
    @Lang(value = "气象信息")//Excel导出的配置
	private String climate;
    @Lang(value = "地理信息")//Excel导出的配置
	private String geography;
    @Lang(value = "匹配度")//Excel导出的配置
	private String match;
    @Lang(value = "创建时间")//Excel导出的配置
	private String pre_time;
    
    public static String [] getStrings(){
    	return new String [] {"pre_id","pre_name","pre_kind","strength","area","people","climate","geography","match","pre_time"};
    }
    
	public PreprojectExcel() {
	}
	
	public PreprojectExcel(Preproject preproject) {
		this.pre_id = preproject.getPre_id();
		this.pre_name = preproject.getPre_name();
		this.pre_kind = preproject.getPreCondition().getDisaster_kind();
		this.strength = preproject.getPreCondition().getDisaster_strength();
		this.area = preproject.getPreCondition().getDisaster_area();
		this.people = preproject.getPreCondition().getDisaster_people();
		this.climate = preproject.getPreCondition().getClimate();
		this.geography = preproject.getPreCondition().getGeography();
		this.match = preproject.getMatch();
		this.pre_time = preproject.getPre_time() == null ? null : preproject.getPre_time().toString();
	}
	
	public Preproject getPreproject(){
		Preproject pre = new Preproject();
		pre.setPre_id(this.pre_id);
		pre.setPre_name(this.pre_name);
		pre.setMatch(this.match);
		pre.setPre_time(Timestamp.valueOf(this.pre_time));
		return pre;
	}
	
	public PreCondition getPreCondition(){
		PreCondition precondition = new PreCondition();
		
		precondition.setPre_id(this.pre_id);
		precondition.setDisaster_kind(this.pre_kind);
		precondition.setDisaster_area(this.area);
		precondition.setDisaster_people(this.people);
		precondition.setDisaster_strength(this.strength);
		precondition.setGeography(this.geography);
		precondition.setClimate(this.climate);
		
		return precondition;
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
	public String getPre_name() {
		return pre_name;
	}
	public void setPre_name(String pre_name) {
		this.pre_name = pre_name;
	}
	public String getPre_kind() {
		return pre_kind;
	}
	public void setPre_kind(String pre_kind) {
		this.pre_kind = pre_kind;
	}
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	public String getClimate() {
		return climate;
	}
	public void setClimate(String climate) {
		this.climate = climate;
	}
	public String getGeography() {
		return geography;
	}
	public void setGeography(String geography) {
		this.geography = geography;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	public String getPre_time() {
		return pre_time;
	}
	public void setPre_time(String pre_time) {
		this.pre_time = pre_time;
	}

	@Override
	public String toString() {
		return "PreprojectExcel [pre_id=" + pre_id + ", pre_name=" + pre_name + ", pre_kind=" + pre_kind + ", strength="
				+ strength + ", area=" + area + ", people=" + people + ", climate=" + climate + ", geography="
				+ geography + ", match=" + match + ", pre_time=" + pre_time + "]";
	}
	
	
}
