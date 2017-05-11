package com.cintel.elp.service.basicfun;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cintel.elp.common.mybatis.Page;
import com.cintel.elp.entity.basicfun.Goods;
import com.cintel.elp.entity.basicfun.GoodsContain;
import com.cintel.elp.service.base.BaseServiceImp;

@Service("GoodsContainService")
public class GoodsContainServiceImpl implements GoodsContainService {
	public Page<GoodsContain> findByPage(GoodsContain o, Page<GoodsContain> page) {
		List<GoodsContain> list = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd hh");
		String code = time.format(date).replace('-', ' ').replace(" ", "");
		int i = 1;
		list.add(new GoodsContain(code + i++, "代血浆", "紧急医疗救护", "1000件"));
		list.add(new GoodsContain(code + i++, "手电", "现场安全", "200部"));
		list.add(new GoodsContain(code + i++, "水泥", "交通与岩土工程抢修", "1000.0kg"));
		list.add(new GoodsContain(code + i++, "汽油", "能源动力保障", "2.00吨"));
		list.add(new GoodsContain(code + i++, "糖盐水", "紧急医疗救护", "1000.00件"));
		list.add(new GoodsContain(code + i++, "棉衣", "人员庇护", "5.00万件"));
		list.add(new GoodsContain(code + i++, "压缩食品", "饮食保障", "5.00万包"));
		list.add(new GoodsContain(code + i++, "面包", "饮食保障", "5.00万包"));
		list.add(new GoodsContain(code + i++, "帐篷", "人员庇护", "1.00万件"));
		list.add(new GoodsContain(code + i++, "水", "饮食保障", "5.00万瓶"));
		
		if(page.getPageSize() > 5){
			page.setResults(list);
		}else{
			if(page.getPageNum() == 1){
				page.setResults(list.subList(0, 5));
			}else{
				page.setResults(list.subList(5, list.size()));
			}
		}
		page.setTotalRecord(10);
		page.setTotalPage(2);
		return page;
	}
	
}
