package cc.dfsoft.project.biz.base.subpackage.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesDto;
import cc.dfsoft.project.biz.base.subpackage.dto.SubQuantitiesReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;

public interface SubQuantitiesService {
	
	/**
	 * 工程量审定页面查询分包工程量
	 * @author
	 * @createTime 2016-7-8
	 * @param	projId工程ID
	 * @return  分包工程量
	 */
	public SubQuantities queryById(String projId);

	/**
	 * 批量添加分包工程量
	 * @author zhangjj
	 * @createTime 2016-7-13
	 * @param qdto
	 * @throws ParseException 
	 */
	public String batInsertSubQualities(SubQuantitiesDto qdto) throws ParseException;

	/**
	 * 工程量清单列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String,Object> queryQuantityStandard(SubQuantitiesReq subQuantitiesReq) throws ParseException;
	
	/**
	 * 此方法用于工程量申报右侧列表无分页查询
	 * @param subQuantitiesReq
	 * @return
	 * @throws ParseException
	 */
	public Map<String,Object> queryQuantityStandardNoPage(SubQuantitiesReq subQuantitiesReq) throws ParseException;

	public Map<String, Object> querySubQuantityStandard(SubQuantitiesReq subQuantitiesReq) throws ParseException;
	
	/**
	 * 批量添加分包工程量
	 * @param list
	 * @throws ParseException
	 */
	public void batInsertSubQualities(List<SubQuantities> list) throws ParseException;
	
	/**
	 * 分包工程量导出Excel文件
	 * @author fuliwei
	 * @createTime 2016-12-31
	 * @param String  projId
	 * @return
	 */
	public List<SubQuantities> exprotExcel(String projId,String sbId);
	
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017年8月1日
	 * @param 
	 * @return
	 */
	public Project findProjectByProjId(String projId);
}
