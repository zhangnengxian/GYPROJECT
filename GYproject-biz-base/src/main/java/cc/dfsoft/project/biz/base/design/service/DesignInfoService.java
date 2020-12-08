package cc.dfsoft.project.biz.base.design.service;

import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.entity.Project;

import java.text.ParseException;
import java.util.Map;


public interface DesignInfoService {
	
	/**
	 * 踏勘员列表查询
	 * @author fuliwei
	 * @createTime 2017年7月21日
	 * @param 
	 * @return
	 */
	public Map<String,Object> querySurveyer(DesignerQueryReq designerQueryReq);
    
	/**
	 * 保存设计信息-资料收集
	 * @param designInfo
	 */
	public String saveDesignInfo(DesignInfo designInfo);
	
	/**
	 * 设计派遣一级页面更新工程信息
	 * @author fuliwei
	 * @createTime 2016-09-02
	 * @param designDispatchReq
	 * @return
	 */
	public void updateProject(DesignDispatchDto designDispatchReq);
	
	/**
	 * 更新工程信息
	 * @author fuliwei
	 * @createTime 2016-06-29
	 * @param project
	 * @return
	 */
	public void update(DesignDispatchDto designDispatchReq);
	
	/**
	 * 图纸审核页面查询设计信息
	 * @author
	 * @createTime 2016-7-5
	 * @param	projId工程ID
	 * @return  DesignInfo设计信息
	 */
	public DesignInfo queryById(String projId);

	/**
	 * 创建设计信息
	 * @param project
	 */
	public void insertDesignInfo(Project project);

	/**
	 * 改派设计员
	 * @param
	 */
	public void updateSurveyer(DesignDispatchDto designDispatchReq); 
	
	/**
	 * 查询详述
	 * @return
	 */
	public Project findByProjId(String projId);


	/**
	 * 查询设计员
	 */
    Map<String,Object> queryDesigner(DesignerQueryReq designerQueryReq);

    void designDispatchUpdateProject(DesignDispatchDto designDispatchReq);

	Map<String,Object> getDataList(DesignerQueryReq designerQueryReq) throws ParseException;

    boolean rollBackContainsSurvey(String projId, String rollBackReason);
}
