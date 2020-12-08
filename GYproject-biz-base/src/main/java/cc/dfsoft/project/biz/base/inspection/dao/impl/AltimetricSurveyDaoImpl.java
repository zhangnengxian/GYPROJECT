package cc.dfsoft.project.biz.base.inspection.dao.impl;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.AltimetricSurveyDao;
import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.entity.AltimetricSurvey;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
@Repository
public class AltimetricSurveyDaoImpl extends NewBaseDAO<AltimetricSurvey, String> implements  AltimetricSurveyDao {

	@Override
	public Map<String, Object> queryAltimSurvey(AltimetricSurveyReq altimetricSurveyReq) {
		Criteria c = super.getCriteria();			
		 //工程id
		 if(StringUtils.isNotBlank(altimetricSurveyReq.getPcId())){
			 c.add(Restrictions.eq("pcId",altimetricSurveyReq.getPcId()));
		 }
		
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AltimetricSurvey> list = this.findBySortCriteria(c, altimetricSurveyReq);
			
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, altimetricSurveyReq.getDraw(),list);
	}
	
	/**
	 * 删除高程测量记录
	 * @return 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByPcId(String pcId) {
		StringBuffer hql = new StringBuffer("delete from AltimetricSurvey where pcId='").append(pcId).append("'");
		super.executeHql(hql.toString());
	}

	
}
