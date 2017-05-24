package com.cintel.elp.service.basicfun;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.jplus.hyberbin.excel.PreprojectToExcel;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.PreGoods;
import com.cintel.elp.entity.basicfun.PreOperate;
import com.cintel.elp.entity.basicfun.Preproject;
import com.cintel.elp.service.base.BaseService;

public interface PreprojectService extends BaseService<Preproject>{
	public void preGoodsExport(PreprojectToExcel excel, Workbook workbook, List<PreGoods> list, String sheetName,
			String contentName) throws Exception ;
	public void preprojectExport(PreprojectToExcel excel, Workbook workbook, List<Preproject> list, String sheetName,
			String contentName) throws Exception ;
	public void preOperateExport(PreprojectToExcel excel, Workbook workbook, List<PreOperate> list, String sheetName,
			String contentName) throws Exception ;
	public void exportToExcel(List<Preproject> list, String tableName, String sheetName, String contentName, String excelPath);
	
	public Page<Preproject> match(Map<String, Object> map);
	
	public Page<PreGoods> getGoods(double population, Preproject preproject);
	
	public List<Preproject> findAll(Preproject p);
}
