package cc.dfsoft.project.biz.base.design.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DesignInfoDao extends CommonDao<DesignInfo, String>{
	/**
	 * 踏勘员列表查询
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySurveyer(DesignerQueryReq designerQueryReq);
	/**
     * 根据工程id获取计划
     * @param projId
     * @return
     */
	public DesignInfo queryInfoByProjId(String projId);
	
	/**
	 * 按设计员名称查询csr_id
	 * @param csrName
	 * @return
	 */
	public List<Staff> findByStaffName(String csrName);

	/**
	 * 根据委托单号查设计信息
	 * @param ocoNo
	 * @return
	 */
	public List<DesignInfo> findByOcoNo(String ocoNo);
	
	/**
	 * 更新设计信息
	 * @param projID
	 * @return
	 */
	public void updateDesignInfo(Project pro);

	/**
	 * 设计
	 */
    Map<String,Object> queryDesigner(DesignerQueryReq designerQueryReq);

	Map<String,Object> getDataList(DesignerQueryReq designerQueryReq) throws ParseException;
}
