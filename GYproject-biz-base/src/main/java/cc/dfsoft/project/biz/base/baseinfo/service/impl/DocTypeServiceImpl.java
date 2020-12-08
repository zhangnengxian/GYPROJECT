package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.baseinfo.service.DocTypeService;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DocTypeServiceImpl implements DocTypeService{

    @Resource
    DocTypeDao docTypeDao;

    /**部门*/
    @Resource
    DepartmentDao departmentDao;

    /**工程类型*/
    @Resource
    ProjectTypeDao projectTypeDao;

	@Override
	public Map<String, Object> queryDocType(DocTypeQueryReq docTypeQueryReq) throws ParseException {
		return docTypeDao.queryDocType(docTypeQueryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDocType(DocType docType) {
		if(StringUtils.isBlank(docType.getId())){
			docType.setId(docTypeDao.getNextId());//获取下一个主键id
		}
		docTypeDao.saveOrUpdate(docType);
	}

	@Override
	public DocType findByStepId(String stepId) {

		return docTypeDao.findByStepId(stepId);
	}

	/**
	 * 审核记录报表查询单据类型
	 * @author fuliwei
	 * @createTime 2017-1-1
	 * @param PageSortReq
	 * @return
	 */
	@Override
	public List queryDocTypeDes(DocTypeQueryReq docTypeQueryReq) {
		Map<String, Object> map=docTypeDao.queryDocType(docTypeQueryReq);
		List list=(List) map.get("data");
		return list;
	}

	/**
	 * 通过主键id查询
	 * @author fuliwei
	 * @createTime 2018年7月16日
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteById(String id) {
		docTypeDao.delete(id);
	}

	/**
	 * 通过步骤id查询单据对象
	 * @param stepId 步骤id
	 * @createTime 2016-08-23
	 * @return
	 */
	@Override
	public DocType findByStepId(String stepId, String corpId,String projectType,String contributionCode) {
		return docTypeDao.findByStepId(stepId, corpId,projectType,contributionCode);
	}




	/**
	 * @MethodDesc: 功能描述
	 * @Author zhangnx
	 * @Date 2019/7/29 11:43
	 */
	@Override
	public String queryAuditLevel(String stepId, String corpId, String projectType, String contributionMode) {
		DocType docType = docTypeDao.findByStepId(stepId, corpId, projectType, contributionMode);
		if (docType==null){
			docType = docTypeDao.findByStepId(stepId, corpId, projectType, Constants.CONTRIBUTION_MODE);
		}
		if (docType==null){
			docType = docTypeDao.findByStepId(stepId, corpId, Constants.PROJECT_TYPE,  Constants.CONTRIBUTION_MODE);
		}

		if (docType==null){
			docType = docTypeDao.findByStepId(stepId, Constants.CORP_ID, Constants.PROJECT_TYPE,  Constants.CONTRIBUTION_MODE);
		}

		if (docType!=null){
			return docType.getGrade();
		}

		return "";
	}
}
