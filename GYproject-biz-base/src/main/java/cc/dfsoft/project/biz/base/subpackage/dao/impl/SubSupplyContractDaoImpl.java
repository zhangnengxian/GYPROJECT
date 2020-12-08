package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.SubSupplyContractQueryReq;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class SubSupplyContractDaoImpl extends NewBaseDAO<SubSupplyContract, String> implements SubSupplyContractDao{

	@Override
	public SubSupplyContract findByProjId(String projId) {
		if(StringUtils.isNotBlank(projId)){
			Criteria c = super.getCriteria();
			c.add(Restrictions.eq("projId", projId));
			List<SubSupplyContract> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> querySubSupplyContract(SubSupplyContractQueryReq subSupplyContractQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",subSupplyContractQueryReq.getProjNo()));
		 }
		 
		 //分包合同编号
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getSscNo())){
			 c.add(Restrictions.like("sscNo","%"+subSupplyContractQueryReq.getSscNo()+"%"));
		 }
		 
		 //工程状态
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getProjStatusId())){
			 StringBuffer hql = new StringBuffer("from Project where projStatusId = '").append(subSupplyContractQueryReq.getProjStatusId()).append("'");
			 List<Project> projects = super.findByHql(hql.toString());
			 List<String> projIds = new ArrayList();
			 if(projects.size()>0){
				 for(Project project:projects){
					 projIds.add(project.getProjId());
				 }
				 c.add(Restrictions.in("projId",projIds));
			 }else{
				// 返回结果
				 return ResultUtil.pageResult(0, subSupplyContractQueryReq.getDraw(),new ArrayList());
			 }
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+subSupplyContractQueryReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+subSupplyContractQueryReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getProjId())){
			 c.add(Restrictions.eq("projId",subSupplyContractQueryReq.getProjId()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getSscSignDateStart())){
			 c.add(Restrictions.ge("sscSignDate", sdf.parse(subSupplyContractQueryReq.getSscSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getSscSignDateEnd())){
			 c.add(Restrictions.le("sscSignDate", sdf.parse(subSupplyContractQueryReq.getSscSignDateEnd())));
		 }
		 //是否打印
		 if(StringUtils.isNotBlank(subSupplyContractQueryReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", subSupplyContractQueryReq.getIsPrint()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<SubSupplyContract> subSupplyContracts = this.findBySortCriteria(c, subSupplyContractQueryReq);
		 if(subSupplyContracts!=null&&subSupplyContracts.size()>0){
			 for(SubSupplyContract sub :subSupplyContracts){
				 if(sub.getSscAmount()!=null){
					 sub.setLegalAmount(MoneyConverter.Num2RMB(sub.getSscAmount().doubleValue()));
				 }
				
			 } 
		 }
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, subSupplyContractQueryReq.getDraw(),subSupplyContracts);
	}
	
}
