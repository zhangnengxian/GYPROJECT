package cc.dfsoft.project.biz.base.change.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;

import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;

public interface MaterialChangeService {
	
public void saveMaterialChange(List<MaterialChange> list);

public Map<String,Object> queryMaterialChange(MaterialChangeReq materialChangeReq);

public Object getSupplement(String cmId,String mcType,String projId)throws ParseException;



	/**
	 * 变更记录导入材料表
	 * @param jsonArr 
	 * @param req  
	 * 
	 */
	public void batInsertMaterialChange(JSONArray jsonArr,MaterialChangeReq req);
	
	/**
	 * 根据工程id查询
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	public List<MaterialChange> findById(String projId);
}
