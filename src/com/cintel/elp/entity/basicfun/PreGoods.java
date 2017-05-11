package com.cintel.elp.entity.basicfun;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import com.cintel.elp.entity.base.BaseEntity;

/**
 * 
 * @author lishutao
 * 预案操作记录
 */
@Alias("PreGoods")
public class PreGoods  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	private Integer pre_goods_id;
	private Preproject preproject;
	private Goods goods;
	private String priority;
	private double amount;
	private Integer save_cycle;
	public Integer getPre_goods_id() {
		return pre_goods_id;
	}
	public void setPre_goods_id(Integer pre_goods_id) {
		this.pre_goods_id = pre_goods_id;
	}
	public Preproject getPreproject() {
		return preproject;
	}
	public void setPreproject(Preproject preproject) {
		this.preproject = preproject;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Integer getSave_cycle() {
		return save_cycle;
	}
	public void setSave_cycle(Integer save_cycle) {
		this.save_cycle = save_cycle;
	}
	@Override
	public String toString() {
		return "PreGoods [pre_goods_id=" + pre_goods_id + ", preproject=" + preproject + ", goods=" + goods
				+ ", priority=" + priority + ", amount=" + amount + ", save_cycle=" + save_cycle + "]";
	}
	
	
}
