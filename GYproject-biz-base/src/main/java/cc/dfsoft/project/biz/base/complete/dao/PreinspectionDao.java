package cc.dfsoft.project.biz.base.complete.dao;



import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.PreinspectionReq;
import cc.dfsoft.project.biz.base.complete.entity.Preinspection;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface PreinspectionDao extends CommonDao<Preinspection,String>{

	/**
	 * 根据工程ID查预验收信息
	 * @param id
	 * @return
	 */
	Preinspection findByProjId(String projId);
	
	/**
	 * 详述
	 * @param projId
	 * @return
	 */
	List<Preinspection> findById(String projId);
	
	/**
	 * 自检单列表查询
	 * @author fuliwei
	 * @createTime 2017年8月4日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySelInspection(PreinspectionReq req)throws ParseException ;

	/**
	 * 标记删除
	 * @author liaoyq
	 * @createTime 2018年7月27日
	 * @param projId
	 * @param string
	 */
	void deleteByprojId(String projId, String isDel);

	/**
	 * 查询删除标记的验收记录
	 * @author liaoyq
	 * @createTime 2018年7月27日
	 * @param projId
	 * @return
	 */
	List<Preinspection> findIsDelByProjId(String projId);
	
}
