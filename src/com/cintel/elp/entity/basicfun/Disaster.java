package com.cintel.elp.entity.basicfun;

import org.apache.ibatis.type.Alias;
import com.cintel.elp.entity.base.BaseEntity;
/**
 * 
 * @author cintel.guo
 *灾情管理
 */
@Alias("Disaster")
public class Disaster extends BaseEntity{
	private Integer dis_ID;
	private Preproject preproject;
	private String dis_kind;
	private String place_name;
	private double area;
	private Integer people;
	private double strength;
	private String occurtime;
	private double place_long;
	private double place_lat;
	private String dis_info;
	public Integer getDis_ID() {
		return dis_ID;
	}
	public void setDis_ID(Integer dis_ID) {
		this.dis_ID = dis_ID;
	}
	public Preproject getPreproject() {
		return preproject;
	}
	public void setPreproject(Preproject preproject) {
		this.preproject = preproject;
	}
	public String getDis_kind() {
		return dis_kind;
	}
	public void setDis_kind(String dis_kind) {
		this.dis_kind = dis_kind;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
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
	public double getStrength() {
		return strength;
	}
	public void setStrength(double strength) {
		this.strength = strength;
	}
	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime;
	}
	public double getPlace_long() {
		return place_long;
	}
	public void setPlace_long(double place_long) {
		this.place_long = place_long;
	}
	public double getPlace_lat() {
		return place_lat;
	}
	public void setPlace_lat(double place_lat) {
		this.place_lat = place_lat;
	}
	public String getDis_info() {
		return dis_info;
	}
	public void setDis_info(String dis_info) {
		this.dis_info = dis_info;
	}
	@Override
	public String toString() {
		return "Disaster [dis_ID=" + dis_ID + ", preproject=" + preproject + ", dis_kind=" + dis_kind + ", place_name="
				+ place_name + ", area=" + area + ", people=" + people + ", strength=" + strength + ", occurtime="
				+ occurtime + ", place_long=" + place_long + ", place_lat=" + place_lat + ", dis_info=" + dis_info
				+ "]";
	}
	
	
}
