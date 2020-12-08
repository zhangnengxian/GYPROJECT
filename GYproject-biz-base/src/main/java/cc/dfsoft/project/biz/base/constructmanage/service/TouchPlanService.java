package cc.dfsoft.project.biz.base.constructmanage.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.constructmanage.dto.TouchPlanQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.entity.TouchPlan;

/**
 * 碰口方案service
 * @author cui
 * @createTime 2016-07-27
 *
 */
public interface TouchPlanService {

	/**
	 * @author cui
	 * 碰口方案保存
	 * @param touchPlan
	 * @return 
	 */
	public void saveTouchPlan(TouchPlan touchPlan) throws Exception;

	/**
	 * @author cui
	 * 开挖保存
	 * @param digRoadList
	 * @return 
	 */
	public void saveDigRoad(List<DigRoad> digRoadList);

	/**
	 * 显示出该工程碰口记录详述
	 * @param projId
	 * @return
	 */
	public TouchPlan touchPlanDetail(String id,String menuDes);
	public TouchPlan touchPlanDetail(String id);

	/**
	 * 显示出该工程碰口开挖路况详述
	 * @param tpId
	 * @return
	 */
	public List<DigRoad> digRoadDetail(String tpId);
	
	/**
	 * 碰口方案审核列表查询
	 * @return
	 * @throws ParseException 
	 */
	public Map<String ,Object> queryTouchPlanAudit(TouchPlanQueryReq touchPlanQueryReq) throws ParseException;
	
	/**
	 * 是否允许操作碰口记录
	 * @author pengtt
	 * @createTime 2016-08-24
	 * @param projId
	 * @return
	 */
	public boolean isAllowRecord(String projId);
	
	/**
	 * 
	 * @param request
	 * @param touchPlan
	 * @param files
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws ParseException 
	 */
	public void saveTouchPlan(HttpServletRequest request,TouchPlan touchPlan,MultipartFile[] files) throws Exception;

}
