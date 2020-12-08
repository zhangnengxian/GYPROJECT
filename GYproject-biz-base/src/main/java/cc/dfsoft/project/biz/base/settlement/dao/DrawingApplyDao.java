package cc.dfsoft.project.biz.base.settlement.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 电子图审核
 * @author fuliwei
 *
 */
public interface DrawingApplyDao extends CommonDao<DrawingApply, String>{
	
	/**
	 * 通过工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public List<DrawingApply> findByProjId(String projId);
	
	/**
	 * 查询竣工图申请单列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDrawingApply(DrawingApplyReq req) throws ParseException ;

	/**
	 * 查询待审核的图纸申请记录
	 * @author liaoyq
	 * @createTime 2018年3月29日
	 * @param value
	 * @return
	 * @throws ParseException 
	 */
	List<DrawingApply> queryDrawingApplyNotice(DrawingApplyReq req) throws ParseException;
	
}
