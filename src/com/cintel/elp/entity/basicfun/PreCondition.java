package com.cintel.elp.entity.basicfun;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

/**
 * 
 * @author lishutao
 * 预案使用条件
 */
@Alias("PreCondition")
public class PreCondition extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	private Integer condition_id;
	private String pre_id;
	private String disaster_kind;
	private double disaster_strength;
	private double disaster_area;
	private Integer disaster_people;
	private String climate;
	private String geography;
	
	public Integer getCondition_id() {
		return condition_id;
	}
	public void setCondition_id(Integer condition_id) {
		this.condition_id = condition_id;
	}
	public String getPre_id() {
		return pre_id;
	}
	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}
	public String getDisaster_kind() {
		return disaster_kind;
	}
	public void setDisaster_kind(String disaster_kind) {
		this.disaster_kind = disaster_kind;
	}
	public double getDisaster_strength() {
		return disaster_strength;
	}
	public void setDisaster_strength(double disaster_strength) {
		this.disaster_strength = disaster_strength;
	}
	public double getDisaster_area() {
		return disaster_area;
	}
	public void setDisaster_area(double disaster_area) {
		this.disaster_area = disaster_area;
	}
	public Integer getDisaster_people() {
		return disaster_people;
	}
	public void setDisaster_people(Integer disaster_people) {
		this.disaster_people = disaster_people;
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
	
	@Override
	public String toString() {
		return "PreCondition [condition_id=" + condition_id + ", pre_id=" + pre_id + ", disaster_kind=" + disaster_kind
				+ ", disaster_strength=" + disaster_strength + ", disaster_area=" + disaster_area + ", disaster_people="
				+ disaster_people + ", climate=" + climate + ", geography=" + geography + "]";
	}
	
}