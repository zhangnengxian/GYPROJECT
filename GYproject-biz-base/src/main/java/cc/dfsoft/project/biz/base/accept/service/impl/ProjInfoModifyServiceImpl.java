package cc.dfsoft.project.biz.base.accept.service.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.dao.ProjInfoModifyDao;
import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.entity.ProjInfoModify;
import cc.dfsoft.project.biz.base.accept.enums.ProjInfoModifyEnum;
import cc.dfsoft.project.biz.base.accept.service.ProjInfoModifyService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProjInfoModifyServiceImpl implements ProjInfoModifyService{
	
	
	@Resource
	ProjInfoModifyDao porjInfoModifyDao;
	
	@Resource
	ProjectDao projectDao;
	/**
	 * 更正保存
	 * @author fulw
	 * @createTime 2017-01-3
	 * @param request
	 * @param cust
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveModify(ProjInfoModify info) {
		if(StringUtils.isBlank(info.getModifyId())){
			info.setModifyId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
		}
		porjInfoModifyDao.saveOrUpdate(info);
		Project project=projectDao.get(info.getProjId());
		if(project!=null){
			project.setIsModify(ProjInfoModifyEnum.NEED_MODIFY.getValue());
			projectDao.save(project);
		}
		
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param id 工程id
	 * @return ProjInfoModify
	 */
	@Override
	public ProjInfoModify queryById(String projId) {
		return porjInfoModifyDao.queryById(projId);
	}
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-01-3
	 * @param ProjInfoModifyReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryProModify(ProjInfoModifyReq req) throws ParseException {
		return porjInfoModifyDao.queryProModify(req);
	}
	
	
}
