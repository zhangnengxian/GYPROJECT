package cc.dfsoft.project.biz.base.settlement.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.settlement.dto.DrawingApplyReq;
import cc.dfsoft.project.biz.base.settlement.entity.DrawingApply;

/**
 * 电子图申请
 * @author fuliwei
 *
 */
public interface DrawingApplyService {
	
	/**
	 * 通过工程id查询详述
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public DrawingApply findByProjId(String projId);
	
	/**
	 * 保存电子图审核申请
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public void saveDrawingApply(DrawingApply da);
	
	/**
	 * 查询左侧列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryProject(ProjectQueryReq req)throws ParseException;
	
	/**
	 * 查询竣工图申请单列表
	 * @author fuliwei
	 * @createTime 2017年8月7日
	 * @param 
	 * @return
	 */
	public Map<String,Object> queryDrawingApply(DrawingApplyReq req)throws ParseException ;
	
}
