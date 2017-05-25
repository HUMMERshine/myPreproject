package com.cintel.elp.task.utils;

import java.util.ArrayList;
import java.util.List;

import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.PreGoods;

public class PredictGoods {
	public static List<PreGoods> getFood(double population) {
		double unit_people = population < 0 ? 0 - population : population;
		List<PreGoods> list = new ArrayList<>();

		String[] strings = new String[] { "1000100005", "1000100006", "1000100007" };

		for (int i = 20, cycle = 2; i <= 2000; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people * 0.125 * i;

				for (int j = 1; j <= cycle; j++) {
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j + "");
					preGoods.setAmount(doubleInvert(sum * handleCycle(cycle, j)));
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

		for (int i = 20, cycle = 2; i <= 200; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people * ((double) 1 / (double) 24) * i;

				for (int j = 1; j <= cycle; j++) {
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j + "");
					preGoods.setAmount(Math.round(sum * handleCycle(cycle, j)));
					preGoods.setSave_cycle(i);

					list.add(preGoods);
				}
			}
		}
		return list;
	}

	public static List<PreGoods> getShelter(double population) {
		double unit_people = population;
		List<PreGoods> list = new ArrayList<>();

		String[] strings = new String[] { "1001100001", "1001100002", "1001100004" };

		for (int i = 20, cycle = 2; i <= 200; i = i * 10, cycle++) {
			for (String code : strings) {
				double sum = unit_people / 2;

				if (code.equals("1001100004")) {
					sum = sum / 2;
				}

				for (int j = 1; j <= cycle; j++) {
					PreGoods preGoods = new PreGoods();
					Goods goods = new Goods();
					goods.setCode(code);
					preGoods.setGoods(goods);
					preGoods.setPriority(j + "");
					preGoods.setAmount(doubleInvert(sum * handleCycle(cycle, j)));
					preGoods.setSave_cycle(i);

					list.add(preGoods);
				}
			}
		}
		return list;
	}

	public static double doubleInvert(double d) {
//		d = d * 100;
//		d = Math.ceil(d);
		//return d / 100;
		return (double)(Math.round(d));
	}

	public static double handleCycle(int cycle, int index) {
		switch (cycle) {
		case 2:
			if(index == 1){
				return 0.3;
			}else{
				return 0.7;
			}
		case 3:
			if(index == 1){
				return 0.2;
			}else if(index == 2){
				return 0.3;
			}else{
				return 0.5;
			}
		case 4:
			if(index == 1){
				return 0.1;
			}else if(index == 2){
				return 0.2;
			}else if(index == 3){
				return 0.3;
			}else{
				return 0.4;
			}
		default:
			return 0;
		}
	}
}
