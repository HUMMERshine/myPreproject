package com.cintel.elp.entity.basicfun;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

@Alias("Goods")
public class Goods extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String goods_ID;
	private String code;
	private String unit;
	private String goods_name;
	private String goods_kind;
	private Double goods_weight;
	private Double goods_volume;
	private String goods_size;
	private String goods_weight_unit;
	private String goods_volume_unit;
	
	public String getGoods_ID() {
		return goods_ID;
	}
	public void setGoods_ID(String goods_ID) {
		this.goods_ID = goods_ID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_kind() {
		return goods_kind;
	}
	public void setGoods_kind(String goods_kind) {
		this.goods_kind = goods_kind;
	}
	public Double getGoods_weight() {
		return goods_weight;
	}
	public void setGoods_weight(Double goods_weight) {
		this.goods_weight = goods_weight;
	}
	public Double getGoods_volume() {
		return goods_volume;
	}
	public void setGoods_volume(Double goods_volume) {
		this.goods_volume = goods_volume;
	}
	public String getGoods_size() {
		return goods_size;
	}
	public void setGoods_size(String goods_size) {
		this.goods_size = goods_size;
	}
	public String getGoods_weight_unit() {
		return goods_weight_unit;
	}
	public void setGoods_weight_unit(String goods_weight_unit) {
		this.goods_weight_unit = goods_weight_unit;
	}
	public String getGoods_volume_unit() {
		return goods_volume_unit;
	}
	public void setGoods_volume_unit(String goods_volume_unit) {
		this.goods_volume_unit = goods_volume_unit;
	}
	@Override
	public String toString() {
		return "Goods [goods_ID=" + goods_ID + ", code=" + code + ", unit=" + unit + ", goods_name=" + goods_name
				+ ", goods_kind=" + goods_kind + ", goods_weight=" + goods_weight + ", goods_volume=" + goods_volume
				+ ", goods_size=" + goods_size + ", goods_weight_unit=" + goods_weight_unit + ", goods_volume_unit="
				+ goods_volume_unit + "]";
	}
	
}
