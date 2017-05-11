package com.cintel.elp.entity.basicfun;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

@Alias("GoodsContain")
public class GoodsContain extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String goods_name;
	private String goods_kind;
	private String goods_num;
	
	public GoodsContain() {
		super();
	}
	public GoodsContain(String code, String goods_name, String goods_kind, String goods_num) {
		super();
		this.code = code;
		this.goods_name = goods_name;
		this.goods_kind = goods_kind;
		this.goods_num = goods_num;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(String goods_num) {
		this.goods_num = goods_num;
	}
	@Override
	public String toString() {
		return "GoodsContain [code=" + code + ", goods_name=" + goods_name + ", goods_kind=" + goods_kind
				+ ", goods_num=" + goods_num + "]";
	}
	
	
}
