package cc.dfsoft.project.biz.base.complete.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.CompletionTransferDao;
import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.CompletionTransfer;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;

@Repository
public class CompletionTransferDaoImpl extends NewBaseDAO<CompletionTransfer, String> implements CompletionTransferDao{

	@Override
	public Map<String, Object> queryCompletionTransfer(CompletionTransferReq completionTransferReq) {
		Criteria c = super.getCriteria();
		//根据碰口内容Id进行查询
		if(StringUtils.isNotBlank(completionTransferReq.getProjId())){
			 c.add(Restrictions.eq("projId",completionTransferReq.getProjId()));
		}
		
		if(StringUtils.isNotBlank(completionTransferReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",completionTransferReq.getProjNo()));
		}
		
		if(StringUtils.isNotBlank(completionTransferReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+completionTransferReq.getProjName()+"%"));
		}
		
//		if(StringUtils.isNotBlank(completionTransferReq.getCmoName())){
//			 c.add(Restrictions.eq("cmoName",completionTransferReq.getCmoName()));
//		}
//		
//		if(StringUtils.isNotBlank(completionTransferReq.getSuName())){
//			 c.add(Restrictions.eq("suName",completionTransferReq.getSuName()));
//		}
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<CompletionTransfer> list = this.findBySortCriteria(c, completionTransferReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, completionTransferReq.getDraw(),list);
	}

}
