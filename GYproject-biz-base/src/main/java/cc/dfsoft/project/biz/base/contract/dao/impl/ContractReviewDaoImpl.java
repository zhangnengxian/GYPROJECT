package cc.dfsoft.project.biz.base.contract.dao.impl;

import java.awt.Container;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fr.report.core.A.c;

import cc.dfsoft.project.biz.base.accept.dao.ProjInfoBackDao;
import cc.dfsoft.project.biz.base.contract.dao.ContractReviewDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractReviewDto;
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;

/**
 * 合同评审dao实现层
 * 
 * @author Administrator
 *
 */
@Repository
public class ContractReviewDaoImpl extends NewBaseDAO<ContractReview, String> implements ContractReviewDao {

	/**
	 * 根据参数查询合同评审记录
	 * 
	 * @param contractReviewReq
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryContractReview(ContractReviewDto contractReviewReq) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria = super.getCriteria();

		// 合同评审主键ID
		if (StringUtils.isNotBlank(contractReviewReq.getCrId())) {
			criteria.add(Restrictions.eq("crId", contractReviewReq.getCrId()));
		}

		// 工程ID
		if (StringUtils.isNoneBlank(contractReviewReq.getProjId())) {
			criteria.add(Restrictions.eq("projId", contractReviewReq.getProjId()));
		}

		// 工程编号
		if (StringUtils.isNotBlank(contractReviewReq.getProjNo())) {
			criteria.add(Restrictions.eq("projNo", contractReviewReq.getProjNo()));
		}

		// 工程名称
		if (StringUtils.isNotBlank(contractReviewReq.getProjName())) {
			criteria.add(Restrictions.like("projName", "%" + contractReviewReq.getProjName() + "%"));
		}

		// 工程地点
		if (StringUtils.isNotBlank(contractReviewReq.getProjAddr())) {
			criteria.add(Restrictions.like("projAddr", "%" + contractReviewReq.getProjAddr() + "%"));
		}

		// 是否打印
		if (StringUtils.isNotBlank(contractReviewReq.getIsPrint())) {
			criteria.add(Restrictions.eq("isPrint", contractReviewReq.getIsPrint()));
		}

		// 经办人
		if (StringUtils.isNotBlank(contractReviewReq.getOperator())) {
			criteria.add(Restrictions.like("operator", "%" + contractReviewReq.getOperator() + "%"));
		}

		// 用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 推送日期开始
		if (StringUtils.isNotBlank(contractReviewReq.getPushTimeStart())) {
			criteria.add(Restrictions.ge("pushTime", sdf.parse(contractReviewReq.getPushTimeStart())));
		}
		// 推送日期结束
		if (StringUtils.isNotBlank(contractReviewReq.getPushTimeEnd())) {
			criteria.add(Restrictions.le("pushTime", sdf.parse(contractReviewReq.getPushTimeEnd())));
		}

		criteria.add(Restrictions.eq("delStatus", "1")); // 加载未删除的
		criteria.add(Restrictions.eq("pushStatus", "1")); // 加载已推送的

		LoginInfo loginInfo = SessionUtil.getLoginInfo(); // 取得登录信息
		this.bylogInfoUnitType(loginInfo, criteria, contractReviewReq); // 引用方法添加过滤sql

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<ContractReview> projectList = this.findBySortCriteria(criteria, contractReviewReq);
		// 返回结果
		return ResultUtil.pageResult(filterCount, contractReviewReq.getDraw(), projectList);
		
	}

	/**
	 * 传入登录人信息和criteria和contractReviewReq 通过登录人单位类型判断走那个方法过滤 有其他单位可以扩展此方法
	 * 
	 * @return
	 */
	public Criteria bylogInfoUnitType(LoginInfo loginInfo, Criteria criteria, ContractReviewDto contractReviewReq) {
		// 判断登录人的单位类型
		if (loginInfo.getUnitType().equals(UnitTypeEnum.GAS_COMPANY.getValue())) { // 燃气单位
			this.gasFunction(loginInfo, criteria, contractReviewReq);
		} else if (loginInfo.getUnitType().equals(UnitTypeEnum.CONSTRUCTION_UNIT.getValue())) { // 施工单位
			this.subFunction(loginInfo, criteria, contractReviewReq);
		}else{
		    //其他单位从此处扩展  
		}

		return criteria;

	}

	/**
	 * 传入登录人信息和criteria 燃气公司过滤方法
	 * 
	 * @return
	 */
	public Criteria gasFunction(LoginInfo loginInfo, Criteria criteria, ContractReviewDto contractReviewReq) {

		StringBuffer hql = new StringBuffer();
		hql.append("proj_id in (select proj_id from project where 1=1 and corp_id = '").append(loginInfo.getCorpId())
				.append("'");

		// 若是营销员职务
		if (loginInfo.getPost().contains((PostTypeEnum.SALESMA.getValue()))) {
			List<DataFilerSetUpDto> filterlist = Constants.getDataFilterMapByKey(PostTypeEnum.SALESMA.getValue() + "_"
					+ loginInfo.getDeptId() + "_" + contractReviewReq.getMenuId());
			if (filterlist !=null && filterlist.size() > 0 && StringUtils.isNotBlank(filterlist.get(0).getSupSql())) { // 判断是否有配置且sql不为空
				hql.append(filterlist.get(0).getSupSql()).append(" = '").append(loginInfo.getStaffId()).append("'");
			} else {
				criteria.add(Restrictions.eq("operatorId", loginInfo.getStaffId())); // 默认加载经办人的工程
			}

		}
		hql.append(")");

		criteria.add(Restrictions.sqlRestriction(hql.toString()));
		return criteria;

	}

	/**
	 * 传入登录人信息和criteria 分包单位过滤方法 no nothing
	 * 
	 * @return
	 */
	public Criteria subFunction(LoginInfo loginInfo, Criteria criteria, ContractReviewDto contractReviewReq) {
		return criteria;

	}
	
}
