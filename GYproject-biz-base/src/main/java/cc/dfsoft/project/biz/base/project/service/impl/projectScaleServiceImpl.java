package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.ProjectScaleDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.project.biz.base.project.service.ProjectScaleService;
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
public class projectScaleServiceImpl implements ProjectScaleService {
	
	@Resource
	ProjectScaleDao ProjectScaleDao;

	@Override
	public Map<String, Object> queryProjectScaleList(ProjectScaleReq ProjectScaleReq) {
		// TODO Auto-generated method stub
		return ProjectScaleDao.queryProjectScaleList(ProjectScaleReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void delProjectScale(String id) {
		 ProjectScaleDao.delProjectScale(id);
		
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateProjectScale(ProjectScale ProjectScale) {
		if(!StringUtil.isNotBlank(ProjectScale.getPsId())){
			ProjectScale.setPsId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		}
		ProjectScaleDao.saveOrUpdate(ProjectScale);
	}

	
	

}
