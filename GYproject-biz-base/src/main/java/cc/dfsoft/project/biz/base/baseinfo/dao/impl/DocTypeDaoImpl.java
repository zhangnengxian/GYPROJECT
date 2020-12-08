package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.DocTypeDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.DocTypeQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.DocType;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class DocTypeDaoImpl extends NewBaseDAO<DocType,String> implements DocTypeDao{

	@Override
	public Map<String, Object> queryDocType(DocTypeQueryReq docTypeQueryReq) {
		Criteria c = super.getCriteria();
		
		if(StringUtils.isNotBlank(docTypeQueryReq.getCorpId())){
			c.add(Restrictions.like("corpId", "%"+docTypeQueryReq.getCorpId()+"%"));
		}
		
		if(StringUtils.isNotBlank(docTypeQueryReq.getProjType())){
			c.add(Restrictions.eq("projType", docTypeQueryReq.getProjType()));
		}
		
		if(StringUtils.isNotBlank(docTypeQueryReq.getContributionCode())){
			c.add(Restrictions.eq("contributionCode", docTypeQueryReq.getContributionCode()));
		}
		if(StringUtils.isNotBlank(docTypeQueryReq.getContributionMode())){
			c.add(Restrictions.eq("contributionMode", docTypeQueryReq.getContributionMode()));
		}
		
		if(StringUtils.isNotBlank(docTypeQueryReq.getStepId())){
			c.add(Restrictions.eq("stepId", docTypeQueryReq.getStepId()));
		}
		
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DocType> list = this.findBySortCriteria(c, docTypeQueryReq);
		for(DocType docType:list){
			if(docType.getStepId().contains("-")){
				this.evict(docType);//实体游离，否则会直接修改数据库
				docType.setStepId(docType.getStepId().split("-")[0]);
			}
		}
		// 返回结果
		return ResultUtil.pageResult(filterCount, docTypeQueryReq.getDraw(),list);
	}

	@Override
	public String getNextId() {
		StringBuffer sql = new StringBuffer("select max(abs(d.id))+1 from doc_type d");
		List<Object[]> maxId = super.findBySql(sql.toString());
		if(maxId.size()>0){
			return String.valueOf(maxId.get(0));
		}
		return "1";
	}

	@Override
	public DocType findByStepId(String stepId) {
		StringBuffer hql = new StringBuffer("from DocType d where 1=1");
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and d.stepId='").append(stepId).append("'");
		}
		List<DocType> docTypes = super.findByHql(hql.toString());
		if(docTypes!=null && docTypes.size()>0){
			return docTypes.get(0);
		}
		return null;
	}
	
	/**
	 * 通过步骤id，corpId查询单据对象
	 * @param stepId
	 * @param corpId
	 * @return
	 */
	@Override
	public DocType findByStepId(String stepId, String corpId,String projectType,String contributionCode) {
		StringBuffer hql = new StringBuffer("from DocType d where 1=1");
		if(StringUtils.isNotBlank(stepId)){
			hql.append(" and d.stepId='").append(stepId).append("'");
		}
		if(StringUtils.isNotBlank(corpId)){
			hql.append(" and d.corpId='").append(corpId).append("'");
		}
		
		if(StringUtils.isNotBlank(projectType)){
			hql.append(" and d.projType='").append(projectType).append("'");
		}
		
		if(StringUtils.isNotBlank(contributionCode)){
			hql.append(" and d.contributionCode='").append(contributionCode).append("'");
		}
		
		
		List<DocType> docTypes = super.findByHql(hql.toString());
		if(docTypes!=null && docTypes.size()>0){
			return docTypes.get(0);
		}
		return null;
	}

	/**
	 * @MethodDesc: 回退审核获取审核级别
	 * @Author zhangnx
	 * @Date 2019/7/4 19:45
	 */
	@Override
	public String getAuditLevel(Project pro,String originalStepId, String fallbackStepId) {

		if (pro==null) return "1";


		String	faofbStepId= StepOutWorkflowEnum.FALLBACK_AUDIT.getValue()+"-"+originalStepId+"-"+fallbackStepId;

		DocType docType = this.findByStepId(faofbStepId,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(faofbStepId,pro.getCorpId(),pro.getProjectType(),null);
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(faofbStepId,pro.getCorpId(),null,null);
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(faofbStepId,null,null,null);
		if (docType!=null) {
			return docType.getGrade();
		}





		String fafbStepId= StepOutWorkflowEnum.FALLBACK_AUDIT.getValue()+"-"+fallbackStepId;

		 docType = this.findByStepId(fafbStepId,pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(fafbStepId,pro.getCorpId(),pro.getProjectType(),null);
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(fafbStepId,pro.getCorpId(),null,null);
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(fafbStepId,null,null,null);
		if (docType!=null) {
			return docType.getGrade();
		}







		docType = this.findByStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue(),pro.getCorpId(),pro.getProjectType(),null);
		if (docType!=null) {
			return docType.getGrade();
		}

		docType = this.findByStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue(),pro.getCorpId(),null,null);
		if (docType!=null) {
			return docType.getGrade();
		}
		docType = this.findByStepId(StepOutWorkflowEnum.FALLBACK_AUDIT.getValue(),null,null,null);
		if (docType!=null) {
			return docType.getGrade();
		}


		return "1";
	}

	@Override
	public boolean isAuditStep(String stepId) {
		Criteria c = super.getCriteria();

		if (StringUtils.isNotBlank(stepId)){
			c.add(Restrictions.eq("stepId", stepId));
		}
		c.add(Restrictions.eq("standardType", "1"));
		c.add(Restrictions.isNotNull("grade"));

		List<DocType> docTypeList = this.findByCriteria(c);
		if (docTypeList!=null && docTypeList.size()>0){
			return true;
		}
		return false;
	}
}
