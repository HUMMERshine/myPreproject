package com.cintel.elp.entity.basicfun;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

@Alias("Warehouse")
public class Warehouse extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String loc_id;
	private String loc_name;
	private String place_long;
	private String place_lat;
	private String location_detail;
	private String road_distance;
	private String air_distance;
	private String loc_type;
	private String master;
	private String department;
	private String contact;
	private String distance_unit;
	
	public String getLoc_name() {
		return loc_name;
	}
	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}
	public String getPlace_long() {
		return place_long;
	}
	public void setPlace_long(String place_long) {
		this.place_long = place_long;
	}
	public String getPlace_lat() {
		return place_lat;
	}
	public void setPlace_lat(String place_lat) {
		this.place_lat = place_lat;
	}
	public String getRoad_distance() {
		return road_distance;
	}
	public void setRoad_distance(String road_distance) {
		this.road_distance = road_distance;
	}
	public String getAir_distance() {
		return air_distance;
	}
	public void setAir_distance(String air_distance) {
		this.air_distance = air_distance;
	}
	public String getLoc_type() {
		return loc_type;
	}
	public void setLoc_type(String loc_type) {
		this.loc_type = loc_type;
	}
	public String getMaster() {
		return master;
	}
	public void setMaster(String master) {
		this.master = master;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getDistance_unit() {
		return distance_unit;
	}
	public void setDistance_unit(String distance_unit) {
		this.distance_unit = distance_unit;
	}
	public String getLocation_detail() {
		return location_detail;
	}
	public void setLocation_detail(String location_detail) {
		this.location_detail = location_detail;
	}
	public String getLoc_id() {
		return loc_id;
	}
	public void setLoc_id(String loc_id) {
		this.loc_id = loc_id;
	}
	@Override
	public String toString() {
		return "Warehouse [loc_id=" + loc_id + ", loc_name=" + loc_name + ", place_long=" + place_long + ", place_lat="
				+ place_lat + ", location_detail=" + location_detail + ", road_distance=" + road_distance
				+ ", air_distance=" + air_distance + ", loc_type=" + loc_type + ", master=" + master + ", department="
				+ department + ", contact=" + contact + ", distance_unit=" + distance_unit + "]";
	}
}
