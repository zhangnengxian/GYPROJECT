package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.AccessoryItemDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectScaleDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectTypeReq;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 附件接口实现类
 * @author 
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class projectTypeServiceImpl implements ProjectTypeService {
	
	@Resource
	ProjectTypeDao projectTypeDao;
	@Resource
	ProjectScaleDao projectScaleDao;
	@Resource
	AccessoryItemDao accessoryItemDao;

	@Override
	public Map<String, Object> queryProjectTypeList(ProjectTypeReq projectTypeReq) {
		// TODO Auto-generated method stub
		return projectTypeDao.queryProjectTypeList(projectTypeReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String delProjectType(String id) {
		 ProjectScale ps=projectScaleDao.get("projType.projTypeId", id);
		 if(ps!=null){
			return "psExit" ;
		 }else{
			 CollectAccessoryItem cit=accessoryItemDao.get("projType.projTypeId", id);
			 if(cit!=null){
					return "citExit" ;
			 }else{
				 projectTypeDao.delProjectType(id); 
			 }
		 }
		return "true";
		 
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateProjectType(ProjectType projectType) {
		if(!StringUtil.isNotBlank(projectType.getProjTypeId())){
			projectType.setProjTypeId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		}
		projectTypeDao.saveOrUpdate(projectType);
	}

	@Override
	public List<ProjectType> queryAllList() {
		// TODO Auto-generated method stub
		return projectTypeDao.findProjectType();
	}
	
	/**
	 * 查询计划内或计划外的工程类型列表
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectType> queryProjectTypeList(String type) {
		return projectTypeDao.queryProjectTypeList(type);
	}
	
	/**
	 * 部门id
	 * @author fuliwei
	 * @createTime 2017年8月2日
	 * @param 
	 * @return
	 */
	@Override
	public List<ProjectType> queryProjectTypeByDeptId(String deptId) {
		return projectTypeDao.queryProjectTypeByDeptId(deptId);
	}
	
	/**
	 * 通过主键id查询合同类型
	 * @author fuliwei
	 * @createTime 2017年8月30日
	 * @param 
	 * @return
	 */
	@Override
	public String findByProjTypeId(String projTypeId) {
		ProjectType proType=projectTypeDao.get(projTypeId);
		if(proType!=null){
			return proType.getContractType();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}

	
	

}
