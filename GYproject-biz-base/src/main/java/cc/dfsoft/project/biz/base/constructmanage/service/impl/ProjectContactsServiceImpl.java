package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.ProjectContactsDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.WorkReportDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ProjectContactsReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ProjectContacts;
import cc.dfsoft.project.biz.base.constructmanage.service.ProjectContactsService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectContactsServiceImpl implements ProjectContactsService {

	 /**工程联系单Dao*/
    @Resource
	ProjectContactsDao projectContactsDao;

	@Resource
	SubContractDao subContractDao;
    /** 签字服务接口*/
    @Resource
    SignatureService signatureService;

	@Resource
	ConstructionPlanDao constructionPlanDao;

	@Resource
	WorkReportDao workReportDao;
	/**
	 * 工程联系单列表查询
	 * @param  projectContactsReq
	 * return
	 */
	@Override
	public Map<String, Object> queryProjectContacts( ProjectContactsReq  projectContactsReq) throws ParseException {
		return projectContactsDao.queryProjectContacts(projectContactsReq);
	}

	/**
	 * 保存工程联系单
	 * @param  projectContacts
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String projectContactsSave(ProjectContacts projectContacts) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank( projectContacts.getPcsId())){
			flag = true;
			 projectContacts.setPcsId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			}
		projectContactsDao.saveOrUpdate(projectContacts);
		signatureService.saveOrUpdateSign("menuId+menuNane+18",projectContacts.getSign(),  projectContacts.getProjId(),  projectContacts.getPcsId(),  projectContacts.getClass().getName(), flag);
		return  projectContacts.getPcsId();
	}

}
