package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.FilingDataDao;
import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class FilingDataDaoImpl extends NewBaseDAO<FilingData, String> implements FilingDataDao{

	@Override
	public Map<String, Object> queryFilingData(CompletionTransferReq completionTransferReq) throws ParseException {
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(completionTransferReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",completionTransferReq.getProjNo()));
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(completionTransferReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+completionTransferReq.getProjName()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(completionTransferReq.getProjId())){
			 c.add(Restrictions.eq("projId",completionTransferReq.getProjId()));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //归档日期开始
		 if(StringUtils.isNotBlank(completionTransferReq.getFdDateStart())){
			 c.add(Restrictions.ge("fdDate", sdf.parse(completionTransferReq.getFdDateStart())));
		 }
		 //归档日期结束
		 if(StringUtils.isNotBlank(completionTransferReq.getFdDateEnd())){
			 c.add(Restrictions.le("fdDate", sdf.parse(completionTransferReq.getFdDateEnd())));
		 }
		//交接日期开始
		 if(StringUtils.isNotBlank(completionTransferReq.getFdConnectDateStart())){
			 c.add(Restrictions.ge("fdConnectDate", sdf.parse(completionTransferReq.getFdConnectDateStart())));
		 }
		 //交接日期结束
		 if(StringUtils.isNotBlank(completionTransferReq.getFdConnectDateEnd())){
			 c.add(Restrictions.le("fdConnectDate", sdf.parse(completionTransferReq.getFdConnectDateEnd())));
		 }
		//是否打印
		 if(StringUtils.isNotBlank(completionTransferReq.getIsPrint())){
			 c.add(Restrictions.eq("isPrint", completionTransferReq.getIsPrint()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息 
		 List<FilingData> projectList = this.findBySortCriteria(c, completionTransferReq);
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, completionTransferReq.getDraw(),projectList);
	}

	@Override
	public FilingData findByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<FilingData> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	
}
