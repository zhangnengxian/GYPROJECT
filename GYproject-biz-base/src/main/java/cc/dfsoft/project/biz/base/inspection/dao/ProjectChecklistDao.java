package cc.dfsoft.project.biz.base.inspection.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.auditina.dto.AuditInsReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface ProjectChecklistDao extends CommonDao<ProjectChecklist, String>{
	
	/**
	 * 
	 * @param projectQueryReq
	 * @return
	 * @throws ParseException 
	 */
	public Map<String,Object> queryProjectChecklist(ProjectChecklistQueryReq listQueryReq) throws ParseException;

	public List<ProjectChecklist> getByPcId(String pcId);
	
	/**
	 * 按工程id和报验单ID查询
	 * @param id 主键id
	 * @return
	 */
	public ProjectChecklist viewProjectCheckList(String projId,String pcId);

	/**
	 * 根据报验单ID修改完成状态
	 * @param pcId
	 * @param flag
	 */
	public void updateFlagByPcId(String pcId, Integer flag);

	/**
	 * 根据工程id和报验单类型查询报验单列表
	 * @param projId
	 * @param pcDesId
	 * @param flag
	 * @return
	 */

	List<ProjectChecklist> findByProjIdAndDesId(String projId, String pcDesId,
			Integer flag);
	/**
	 * @author 王会军
	 * @createTime 2018-1-24
	 */
 	public void deleteListById(String pcId);

	public void sendListById(String pcId);


	public Map<String,Object> getDataList(AuditInsReq listQueryReq) throws ParseException;


	public void updateData(String pcId);

	void updateFlagByPcId1(ProjectChecklist proChe);

	/**
	 * 查询记录表和报验单否完成签字
	 * @author fuliwei
	 * @date 2019/3/15
	 * @param
	 * @return
	*/
	public String checkIsFinishSign(String pcId,String tableName);

	/**
	 * 检查报验单是否完成签字
	 * @author fuliwei
	 * @date 2019/3/15
	 * @param
	 * @return
	*/
	public String checkListIsFinishSign(String pcId);
}
