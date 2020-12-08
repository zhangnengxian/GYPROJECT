package cc.dfsoft.project.biz.base.subpackage.dao.impl;

import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dto.IntelligentMeterContractReq;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MoneyConverter;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
/**
 * 
 * 描述:智能表合同dao实现层
 * @author liaoyq
 * @createTime 2017年9月16日
 */
@Repository
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class IntelligentMeterContractDaoImpl extends NewBaseDAO<IntelligentMeterContract, String>
		implements IntelligentMeterContractDao {
	@Resource
	DepartmentDao departmentDao;

	/**
	 * 根据工程ID查询智能表合同
	 * @author liaoyq
	 * @createTime 2017-9-18
	 * @param projId
	 * @return IntelligentMeterContract
	 */
	@Override
	public IntelligentMeterContract findContractByprojId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
			List<IntelligentMeterContract> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	/**
	 * 根据合同编号查询付款信息
	 */

	@Override
	public List<IntelligentMeterContract> findByImcNo(String imcNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from IntelligentMeterContract where 1=1");
		if(StringUtils.isNotBlank(imcNo)){
			hql.append(" and imcNo = '").append(imcNo).append("'");
		}
		List<IntelligentMeterContract> result= super.findByHql(hql.toString());
		return result;
	}

	/**
	 * 分页查询智能表合同信息
	 * @throws ParseException 
	 */
	@Override
	public Map<String, Object> queryContract(IntelligentMeterContractReq imcReq) throws ParseException {
		LoginInfo loginInfo = SessionUtil.getLoginInfo(); 
		Criteria c = super.getCriteria();
		 //工程编号 
		 if(StringUtils.isNotBlank(imcReq.getProjNo())){
			 c.add(Restrictions.like("projNo",imcReq.getProjNo()));
		 }
		 
		 //智能表合同编号
		 if(StringUtils.isNotBlank(imcReq.getImcNo())){
			 c.add(Restrictions.like("imcNo",imcReq.getImcNo()));
		 }
		//修改审核状态
		if(StringUtils.isNotBlank(imcReq.getModifyStatus())){
			if(!imcReq.getModifyStatus().equals("all")){
				c.add(Restrictions.eq("modifyStatus",imcReq.getModifyStatus()));
			}
		}else{
			StringBuffer sql=new StringBuffer("(MODIFY_STATUS !='0' OR MODIFY_STATUS IS NULL)");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}

		 //工程名称
		 if(StringUtils.isNotBlank(imcReq.getProjName())){
			 c.add(Restrictions.like("projName","%"+imcReq.getProjName()+"%"));
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(imcReq.getProjAddr())){
			 c.add(Restrictions.like("projAddr","%"+imcReq.getProjAddr()+"%"));
		 }
		 //工程id
		 if(StringUtils.isNotBlank(imcReq.getProjId())){
			 c.add(Restrictions.eq("projId",imcReq.getProjId()));
		 }else{
			 Department department = departmentDao.queryDepartmentByDivide(null, loginInfo.getDeptId());
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.PROJ_STATUS_ID !='2001' and cp.corp_id like '").append(department.getOrgId()).append("%')");
			 c.add(Restrictions.sqlRestriction(sql.toString())); 
		 }
		 //乙方名称
		 if(StringUtil.isNotBlank(imcReq.getsPartyName())){
			 c.add(Restrictions.like("sPartyName","%"+imcReq.getsPartyName()+"%"));
		 }
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 //签定日期开始
		 if(StringUtils.isNotBlank(imcReq.getImcSignDateStart())){
			 c.add(Restrictions.ge("imcSignDate", sdf.parse(imcReq.getImcSignDateStart())));
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(imcReq.getImcSignDateEnd())){
			 c.add(Restrictions.le("imcSignDate", sdf.parse(imcReq.getImcSignDateEnd())));
		 }
		 //推送状态
		 if(StringUtil.isNotBlank(imcReq.getFlag())){
			 c.add(Restrictions.eq("flag", imcReq.getFlag()));
		 }
		
		//是否打印
		if(StringUtils.isNotBlank(imcReq.getIsPrint())){
			c.add(Restrictions.eq("isPrint", imcReq.getIsPrint()));
		}
		
		 c.addOrder(Order.desc("projNo"));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<IntelligentMeterContract> contracts = this.findBySortCriteria(c, imcReq);
		
		 if(contracts!=null && contracts.size()>0){
			 for (IntelligentMeterContract imc : contracts) {
				if(imc.getTotalCost()!=null){
					imc.setTotalCostLegalAmount(MoneyConverter.Num2RMB(imc.getTotalCost().doubleValue()));
					imc.setUnitCostLegalAmount(MoneyConverter.Num2RMB(imc.getUnitCost().doubleValue()));
					if(imc.getFirstPayment()!=null){
						imc.setFirstPayMentLeaglAmount(MoneyConverter.Num2RMB(imc.getFirstPayment().doubleValue()));
					}
				}
			}
		 }
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, imcReq.getDraw(),contracts);
	}

	/**
	 * 根据条件查询智能表合同工程
	 */
	@Override
	public Map<String, Object> queryImContractBySql(
			IntelligentMeterContractReq imcReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo(); //得到登陆用户的信息
		StringBuffer sql = new StringBuffer("select *  from (select p.PROJ_ID as projId,p.PROJ_NO as projNo,p.PROJ_NAME as projName,p.PROJECT_TYPE AS projectType,p.PROJ_STATUS_ID as projStatusId,p.corp_id as corpId,p.corp_name as corpName,imc.flag, ").append(mysqlSqlConveter.rownumberConveter(" rn from")).append(" project as p left join intelligent_meter_contract imc on imc.PROJ_ID = p.PROJ_ID  where 1=1");
        StringBuffer sqlCount = new StringBuffer("select count(p.PrOJ_ID) from project as p left join intelligent_meter_contract imc on imc.PROJ_ID = p.PROJ_ID  where 1=1");
		StringBuffer filter = new StringBuffer();
		boolean flag = true;
		//合同推送状态
		if(StringUtil.isNotBlank(imcReq.getFlag())){
			if(imcReq.getFlag().equals("0")){
				filter.append(" and (imc.FLAG='").append(imcReq.getFlag()).append("' or imc.FLAG is null)");
			}else{
				filter.append(" and imc.FLAG='").append(imcReq.getFlag()).append("'");
			}
		}
		//乙方名称
		if(StringUtil.isNotBlank(imcReq.getsPartyName())){
			filter.append(" and imc.SECOND_PARTY_NAME like '%").append(imcReq.getsPartyName()).append("%'");
			flag = false;
		}
		//智能表合同编号
		if(StringUtil.isNotBlank(imcReq.getImcNo())){
			filter.append(" and imc.IMC_NO like '%").append(imcReq.getImcNo()).append("%'");
			flag = false;
		}
		//签定日期开始
		 if(StringUtils.isNotBlank(imcReq.getImcSignDateStart())){
			 filter.append(" and imc.IMC_SIGN_DATE>='").append(imcReq.getImcSignDateStart()).append("'");
		 }
		 //签定日期结束
		 if(StringUtils.isNotBlank(imcReq.getImcSignDateEnd())){
			 filter.append(" and imc.IMC_SIGN_DATE<='").append(imcReq.getImcSignDateEnd()).append("'");
		 }
		//工程地点
		if(StringUtil.isNotBlank(imcReq.getProjAddr())){
			filter.append(" and p.PROJ_ADDR like '%").append(imcReq.getProjAddr()).append("%'");
			flag = false;
		}
		//工程编号
		if(StringUtil.isNotBlank(imcReq.getProjNo())){
			filter.append(" and p.PROJ_NO like '%").append(imcReq.getProjNo()).append("%'");
			flag = false;
		}
		//工程名称
		if(StringUtil.isNotBlank(imcReq.getProjName())){
			filter.append(" and p.PROJ_NAME like '%").append(imcReq.getProjName()).append("%'");
			flag = false;
		}
		//工程状态
		if(StringUtil.isNotBlank(imcReq.getProjStatusId())){
			filter.append(" and p.PROJ_STATUS_ID='").append(imcReq.getProjStatusId()).append("'");
		}
		//工程类型
		if(StringUtil.isNotBlank(imcReq.getProjLtypeId())){
			filter.append(" and p.PROJ_LTYPE_ID='").append(imcReq.getProjLtypeId()).append("'");
		}
		//是否是智能表
		if(imcReq.getIsIntelligentMeter()!=null){
			filter.append(" and p.IS_INTELLIGENT_METER='").append(imcReq.getIsIntelligentMeter()).append("'");
		}
		//工程类型
		if(imcReq.getProjLtypeId()!=null){
			filter.append(" and p.PROJ_LTYPE_ID='").append(imcReq.getProjLtypeId()).append("'");
		}
		//市场部踏勘员，查看派工给自己的
		 if(loginInfo.getPost()!=null && loginInfo.getPost().length()>0 && loginInfo.getPost().contains(PostTypeEnum.SURVEYER.getValue())){
			 List<DataFilerSetUpDto> costerList = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+PostTypeEnum.SURVEYER.getValue()+"_"+imcReq.getMenuId());
			 if(costerList!=null && costerList.size()>0){
				 if(StringUtils.isNotBlank(costerList.get(0).getSupSql())){
					 filter.append(costerList.get(0).getSupSql()).append("'"+loginInfo.getStaffId()+"'");
				 }
			 }
		 }
		if(flag){
			//默认不加载已竣工的工程
			filter.append(" and p.PROJ_STATUS_ID <> '").append(ProjStatusEnum.ALREADY_COMPLETED.getValue()).append("'");
		} 
		//过滤掉终止的和按照公司过滤
		filter.append(" and p.PROJ_STATUS_ID !='2001'");
		filter.append(" and p.corp_id like '").append(loginInfo.getCorpId()).append("%'");
		
		// 数据库根据条件过滤记录数
		int filterCount = this.getCountBySql(sqlCount.append(filter).toString());
		// 根据条件获取查询信息
		int start = imcReq.getStart()+1; 
		int end = start+(imcReq.getLength()-1); 
		sql.append(filter);
		sql.append(" order by p.PROJ_NO desc ) result");
		
		sql.append(" where result.rn between "+start+" and "+ end);
		
		List<Map<String, Object>> mapList = this.findListBySql(sql.toString());
		
		 // 返回结果
		 return ResultUtil.pageResult( filterCount, imcReq.getDraw(),mapList);
	}
}
