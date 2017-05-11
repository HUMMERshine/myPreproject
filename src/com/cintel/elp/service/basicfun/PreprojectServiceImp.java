package com.cintel.elp.service.basicfun;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Workbook;
import org.jplus.hyberbin.excel.PreprojectToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreCondition;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.repository.basicfun.PreprojectDao;
import com.cintel.elp.service.base.BaseServiceImp;
import com.cintel.elp.task.utils.GoodsExcel;
import com.cintel.elp.task.utils.MatchUtil;
import com.cintel.elp.task.utils.PreOperateExcel;
import com.cintel.elp.task.utils.PreprojectExcel;

@Service("PreprojectService")
public class PreprojectServiceImp extends BaseServiceImp<Preproject> implements PreprojectService {

	public void preprojectExport(PreprojectToExcel excel, Workbook workbook, List<Preproject> list, String sheetName,
			String contentName) throws Exception {
		List<PreprojectExcel> lists = new ArrayList<>();
		for (Preproject preproject : list) {
			lists.add(new PreprojectExcel(preproject));
		}
		excel.preprojectExport(workbook, lists, PreprojectExcel.getStrings(), sheetName, contentName);
	}

	public void preGoodsExport(PreprojectToExcel excel, Workbook workbook, List<PreGoods> list, String sheetName,
			String contentName) throws Exception {

		List<GoodsExcel> lists = new ArrayList<>();
		for (PreGoods pregoods : list) {
			lists.add(new GoodsExcel(pregoods));
		}
		excel.preprojectExport(workbook, lists, GoodsExcel.getStrings(), sheetName, contentName);
	}
	
	public void preOperateExport(PreprojectToExcel excel, Workbook workbook, List<PreOperate> list, String sheetName,
			String contentName) throws Exception {

		List<PreOperateExcel> lists = new ArrayList<>();
		for (PreOperate preOperate : list) {
			lists.add(new PreOperateExcel(preOperate));
		}
		excel.preprojectExport(workbook, lists, PreOperateExcel.getStrings(), sheetName, contentName);
	}

	@Override
	public Page<Preproject> match(Map<String, Object> map) {
		// TODO Auto-generated method stub
		Page<Preproject> result = new Page<>();
		Preproject target = new Preproject();
		PreCondition condition = new PreCondition();
		target.setPre_time(Timestamp.valueOf((String)map.get("disaster_time")));
		target.setPreCondition(condition);
		condition.setClimate((String)map.get("disaster_climate"));
		condition.setGeography((String)map.get("disaster_geography"));
		condition.setDisaster_people(Integer.parseInt((String)map.get("disaster_people")));
		condition.setDisaster_area(Double.parseDouble((String)map.get("disaster_area")));
		condition.setDisaster_kind((String)map.get("disaster_type"));
		condition.setDisaster_strength(Double.parseDouble((String)map.get("disaster_level")));
		
		System.out.println(target);
		
		List<Preproject> list = find(new Preproject());
		MatchUtil match = new MatchUtil();
		
		Map<String, Double> res = match.earthQuake(list, target);
		
		List<Map.Entry<String, Double>> lists = new ArrayList<>(res.entrySet());
		Collections.sort(lists, new Comparator<Map.Entry<String, Double>>() {

			@Override
			public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
				// TODO Auto-generated method stub
				double d = o1.getValue() - o2.getValue();
				if(d > 0) return 1;
				if(d < 0) return -1;
				return 0;
			}
		});
		
		List<Preproject> temp = new ArrayList<>();
		for(Map.Entry<String, Double> entry : lists){
			//System.out.println(entry.getKey() + " " + entry.getValue());
			if(temp.size() == 5) break;
			String id = entry.getKey();
			double num = 100 * (1 - entry.getValue());
			String s = String.format("%.2f", num);
			s = s + "%";
			for(Preproject preproject : list){
				if(preproject.getPre_id().equals(id)){
					preproject.setMatch(s);
					temp.add(preproject);
					break;
				}
			}
		}
		
		for(Preproject preproject : temp){
			System.out.println(preproject);
		}
		
		result.setTotalPage(1);
		result.setTotalRecord(temp.size());
		result.setResults(temp);
		
		return result;
	}
	
}
