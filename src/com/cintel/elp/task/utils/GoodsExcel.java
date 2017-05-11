package com.cintel.elp.task.utils;

import org.jplus.hyberbin.excel.annotation.ExcelVoConfig;
import org.jplus.hyberbin.excel.annotation.Lang;
import org.jplus.hyberbin.excel.annotation.input.InputDicConfig;
import org.jplus.hyberbin.excel.annotation.output.OutputDicConfig;
import org.jplus.hyberbin.excel.annotation.validate.DicValidateConfig;
import org.jplus.hyberbin.excel.bean.BaseExcelVo;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.Preproject;
@ExcelVoConfig
public class GoodsExcel extends BaseExcelVo{
	@Lang(value = "物资编号")//Excel导出的配置
    private String goods_code;
    @Lang(value = "物资名称")//Excel导出的配置
    private String goods_name;
    @Lang(value = "物资类型")//Excel导出的配置
    private String goods_kind;
    @Lang(value = "物资数量")//Excel导出的配置
	private double goods_num;
    @Lang(value = "物资单位")//Excel导出的配置
	private String unit;
    @Lang(value = "运送批次")//Excel导出的配置
	private String priority;
    @Lang(value = "运送批次")//Excel导出的配置
    private Integer save_cycle;
    
	public static String [] getStrings(){
    	return new String [] {"goods_code","goods_name","goods_kind","goods_num","unit","priority"};
    }
    
	public GoodsExcel() {
	}
	
	public GoodsExcel(PreGoods pregoods) {
		this.goods_code = pregoods.getGoods().getCode();
		this.goods_kind = pregoods.getGoods().getGoods_name();
		this.goods_name = pregoods.getGoods().getGoods_kind();
		this.goods_num = pregoods.getAmount();
		this.priority = pregoods.getPriority();
		this.unit = pregoods.getGoods().getUnit();
		this.save_cycle = pregoods.getSave_cycle();
	}
	
	public PreGoods getPreGoods(){//预案信息和savecycle需要在外层加入。
		PreGoods preGoods = new PreGoods();
		Goods goods = new Goods();
		goods.setCode(this.goods_code);
		preGoods.setGoods(goods);
		preGoods.setAmount(this.goods_num);
		preGoods.setPriority(this.priority);
		
		return preGoods;
	}
	
	@Override
	public int getHashVal() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getGoods_code() {
		return goods_code;
	}

	public void setGoods_id(String goods_code) {
		this.goods_code = goods_code;
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

	public double getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(double goods_num) {
		this.goods_num = goods_num;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	public Integer getSave_cycle() {
		return save_cycle;
	}

	public void setSave_cycle(Integer save_cycle) {
		this.save_cycle = save_cycle;
	}

	@Override
	public String toString() {
		return "GoodsExcel [goods_code=" + goods_code + ", goods_name=" + goods_name + ", goods_kind=" + goods_kind
				+ ", goods_num=" + goods_num + ", unit=" + unit + ", priority=" + priority + ", save_cycle="
				+ save_cycle + "]";
	}

	
	
}
