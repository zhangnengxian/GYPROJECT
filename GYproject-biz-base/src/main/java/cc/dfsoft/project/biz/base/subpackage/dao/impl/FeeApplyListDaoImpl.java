package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.subpackage.dao.FeeApplyListDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PaymentApplyReq;
import cc.dfsoft.project.biz.base.subpackage.entity.FeeApplyList;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 费用申请工程清单
 * @author fuliwei
 *
 */
@Repository
public class FeeApplyListDaoImpl extends NewBaseDAO<FeeApplyList,String> implements FeeApplyListDao {
	
	/**
	 * 查询费用申请工程列表
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryFeeApplyList(PaymentApplyReq req) throws ParseException {
		Criteria c = super.getCriteria();
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		
		//根据Id进行查询
		if(StringUtils.isNotBlank(req.getProjId())){
			 c.add(Restrictions.eq("projId",req.getProjId()));
		}
		
		//付款申请单id
		if(StringUtils.isNotBlank(req.getPaId())){
			 c.add(Restrictions.eq("paId",req.getPaId()));
		}
		
		//工程编号
		if(StringUtils.isNotBlank(req.getProjNo())){
			c.add(Restrictions.like("projNo","%"+req.getProjNo()+"%"));	
		}
		//工程名称
		if(StringUtils.isNotBlank(req.getProjName())){
			c.add(Restrictions.like("projName","%"+req.getProjName()+"%"));	
		}
		//工程地点
		if(StringUtils.isNotBlank(req.getProjAddr())){
			c.add(Restrictions.like("projAddr","%"+req.getProjAddr()+"%"));	
		}
		//结算
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<FeeApplyList> list = this.findBySortCriteria(c, req);
		// 返回结果
		return ResultUtil.pageResult(filterCount, req.getDraw(),list);
		
	}
	
	/**
	 * 根据申请单id删除申请清单
	 * @author fuliwei
	 * @createTime 2017年12月11日
	 * @param 
	 * @return
	 */
	@Override
	public void deleteByPaId(String paId) {
		StringBuffer hql = new StringBuffer("delete from FeeApplyList where paId='").append(paId).append("'");
		super.executeHql(hql.toString());
	}
	
	/***
     * 付款清单
     * @author fuliwei
     * @createTime 2017年12月25日
     * @param 
     * @return
     */
	@Override
	public List<FeeApplyList> findByPaId(String paId) {
		Criteria c = super.getCriteria();
		
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		
		//付款申请单id
		if(StringUtils.isNotBlank(paId)){
			 c.add(Restrictions.eq("paId",paId));
			 List<FeeApplyList> list=this.findByCriteria(c);
			 return list;
		}
		return null;
	}

}
