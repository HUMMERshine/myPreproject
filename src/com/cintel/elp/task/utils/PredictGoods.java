package com.cintel.elp.task.utils;

import java.util.ArrayList;
import java.util.List;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreGoods;

public class PredictGoods {
	public static List<PreGoods> getFood(double population) {
		double unit_people = population / 10000;
		List<PreGoods> list = new ArrayList<>();

		String[] strings = new String[] { "1000100005", "1000100006", "1000100007" };
		
		for (int i = 20, cycle = 1; i <= 2000; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people * 0.125 * i;
				
				for(int j = 1; j <= cycle; j++){
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j + "");
					preGoods.setAmount(doubleInvert(sum / cycle));
					preGoods.setSave_cycle(i);
					
					list.add(preGoods);
				}
			}
		}
		return list;
	}
	
	public static List<PreGoods> getMedicine(double population) {
		double unit_people = population / 10;
		List<PreGoods> list = new ArrayList<>();

		String[] strings = new String[] { "1001000003", "1001000001", "1000900001" };
		
		for (int i = 20, cycle = 1; i <= 200; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people * ((double)1/(double)24) * i;
				
				for(int j = 0; j <= cycle; j++){
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j+1 + "");
					preGoods.setAmount(Math.round(sum / (cycle+1)));
					preGoods.setSave_cycle(i);
					
					list.add(preGoods);
				}
			}
		}
		return list;
	}
	
	public static List<PreGoods> getShelter(double population) {
		double unit_people = population / 10000;
		List<PreGoods> list = new ArrayList<>();

		String[] strings = new String[] { "1001100001", "1001100002", "1001100004" };
		
		for (int i = 20, cycle = 1; i <= 200; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people * i;
				
				if(code.equals("1001100004")){
					sum = sum / 2;
				}
				
				for(int j = 0; j <= cycle; j++){
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j+1 + "");
					preGoods.setAmount(doubleInvert(sum / (cycle+1)));
					preGoods.setSave_cycle(i);
					
					list.add(preGoods);
				}
			}
		}
		return list;
	}
	
	public static double doubleInvert(double d){
		d = d * 100;
		d = Math.ceil(d);
		return d/100;
	}
}
