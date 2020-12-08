package cc.dfsoft.project.biz.base.charge.dao.impl;

import cc.dfsoft.project.biz.base.charge.dao.AccrualsRecordDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.AccrualsRecord;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.subpackage.dao.IntelligentMeterContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubContractIntelligentDao;
import cc.dfsoft.project.biz.base.subpackage.dao.SubSupplyContractDao;
import cc.dfsoft.project.biz.base.subpackage.entity.IntelligentMeterContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContract;
import cc.dfsoft.project.biz.base.subpackage.entity.SubContractIntelligent;
import cc.dfsoft.project.biz.base.subpackage.entity.SubSupplyContract;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;
/**
 * 应收应付记录DAO处理实现类
 * @author 
 *
 */
@Repository
public class AccrualsRecordDaoImpl extends NewBaseDAO<AccrualsRecord, String> implements AccrualsRecordDao{

	/**
	 * 智能表合同
	 */
	@Resource
	IntelligentMeterContractDao imcDao;
	@Resource
	ContractDao contractDao;
	@Resource
	SubContractDao subContractDao;
	@Resource
	SubContractIntelligentDao subIntelligentDao;
	/**
	 * 智能表分合同
	 */
	@Resource
	SubContractIntelligentDao subImcDao;
	
	/**补充协议DAO*/
	@Resource
	SupplementalContractDao supContractDao;
	
	/**分包补充协议DAO*/
	@Resource
	SubSupplyContractDao subSupContractDao;
	
	@Override
	public Map<String, Object> queryAccrualsRecord(ChargeReq chargeReq) {
		Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(chargeReq.getProjId())){
			 c.add(Restrictions.eq("projId",chargeReq.getProjId()));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getArAtatus())){
			 c.add(Restrictions.eq("arStatus",chargeReq.getArAtatus()));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",chargeReq.getProjNo()));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getArFlag()) && chargeReq.getArFlag().equals("1")){
			 c.add(Restrictions.gt("arFlag",new Integer(-1))); //查询退款记录和收款记录
		 }else if(StringUtils.isNotBlank(chargeReq.getArFlag())){
			 c.add(Restrictions.eq("arFlag",new Integer(chargeReq.getArFlag())));//查询付款记录
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getArType())){
			 c.add(Restrictions.eq("arType",chargeReq.getArType()));
		 }
		 //未收齐
		 if(StringUtils.isNotBlank(chargeReq.getArOver())){
			 c.add(Restrictions.or(Restrictions.gtProperty("arCost", "receiveAmount"),Restrictions.isNull("receiveAmount")));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getBillState())){
			 if(chargeReq.getBillState().equals("1")){
				 c.add(Restrictions.or(Restrictions.eq("invoice", "1"), Restrictions.eq("receiveInvoice", "1"))); 
			 }else{
				 c.add(Restrictions.and(Restrictions.eq("invoice", "0"), Restrictions.eq("receiveInvoice", "0")));  
			 }
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getBillType())){
			 c.add(Restrictions.eq("billType",chargeReq.getBillType()));
		 }
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AccrualsRecord> list = this.findBySortCriteria(c, chargeReq);
		 if(list!=null && list.size()>0){
			 for(AccrualsRecord ar : list){
				 //智能表合同款
				 String increment="";
				 String conNo="";
				 String contractAmount="";
				 if(StringUtils.isNotBlank(ar.getContractType()) && ar.getContractType().equals(ArContractTypeEnum.INTELLIGENCE.getValue())){
					if(ar.getArFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue())){
						 IntelligentMeterContract imc = imcDao.findContractByprojId(ar.getProjId());
						 if(imc != null){
							increment = imc.getIncrement();
							conNo = imc.getImcNo();
							contractAmount = imc.getTotalCost().toString();
						 }else{
							 //智能表分包合同号
							 SubContractIntelligent subIntelligent = subIntelligentDao.findContractByprojId(ar.getProjId());
							 if(subIntelligent != null){
								conNo = subIntelligent.getItScNo();
								contractAmount = subIntelligent.getTotalCost().toString();
							 }
						 }
					 }
				 }else if(StringUtils.isNotBlank(ar.getContractType()) && ar.getContractType().equals(ArContractTypeEnum.SUP_CONTRACT.getValue())){
					 //补充协议
					 if(ar.getArFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue())){
						 List<SupplementalContract> supCons = supContractDao.findByScNo(ar.getConNo());
						 if(supCons != null && supCons.size()>0){
							increment = supCons.get(0).getIncrement();
							conNo = ar.getConNo();
							contractAmount =  supCons.get(0).getScAmount().toString();
						 }else{
							 //分包补充协议-目前没有分包协议
							 SubSupplyContract subSupplyContract = subSupContractDao.findByProjId(ar.getProjId());
							 if(subSupplyContract != null){
								conNo = subSupplyContract.getSscNo();
								contractAmount = subSupplyContract.getSscAmount().toString();
							 }
						 }
					 }
				}else{//其他合同
					 if(ar.getArFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue())){
						 //安装合同
					 	Contract contract=contractDao.viewContractByprojId(ar.getProjId());
						if(contract!=null){
							increment = contract.getIncrement();
							conNo = contract.getConNo();
							contractAmount = contract.getContractAmount().toString();
						}
					 }else{
						 //分包合同
						 SubContract subCon=subContractDao.findByProjId(ar.getProjId());
						 if(subCon!=null){
							conNo = subCon.getScNo();
							contractAmount = subCon.getScAmount().toString();
						 }
					 }
				 }
				 ar.setIncrement(StringUtils.isNotBlank(increment)?increment:"");
				 ar.setConNo(conNo);
				 ar.setContractAmount(contractAmount);
			 }
		 }
			
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, chargeReq.getDraw(),list);
	}
	@Override
	public Map<String, Object> queryAccrualsRecordNew(ChargeReq chargeReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		 StringBuffer str=new StringBuffer();
		 str.append("exists (select * from accruals_record a left join project p on a.proj_id=p.proj_id left join customer c on p.cust_id=c.cust_id where 1=1");
		 if(StringUtils.isNotBlank(chargeReq.getArAtatus())){
			 str.append(" and a.ar_Status="+chargeReq.getArAtatus());
		 }
		 if(StringUtils.isNotBlank(chargeReq.getProjNo())){
			 str.append(" and a.proj_no='"+chargeReq.getProjNo()).append("'");
		 }
		 if(StringUtils.isNotBlank(chargeReq.getArFlag())){
			 str.append(" and a.ar_Flag="+chargeReq.getArFlag());			
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getArType())){
			 str.append(" and a.ar_Type="+chargeReq.getArType());	
		 }
		 //工程名称
		 if(StringUtils.isNotBlank(chargeReq.getProjName())){
			 str.append(" and p.proj_name like '%"+chargeReq.getProjName()+"%'");	
		 }
		 //工程地点
		 if(StringUtils.isNotBlank(chargeReq.getProjAddr())){
			 str.append(" and p.proj_addr like '%"+chargeReq.getProjAddr()+"%'");	
		 }
		 //工程状态
		 if(StringUtils.isNotBlank(chargeReq.getProjStatusId())){
			 str.append(" and p.proj_status_id ="+chargeReq.getProjStatusId());	
		 }
		 //分公司ID
		 str.append(" and p.CORP_ID like '").append(loginInfo.getCorpId()).append("%'");
		 //部门过滤
		 List<DataFilerSetUpDto> filters = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"_"+chargeReq.getMenuId());
		 if(filters!=null && filters.size()>0){
			 str.append(" and p.DEPT_ID like '").append(loginInfo.getDeptId()).append("%'");
		 }
		 //客户名称
		 if(StringUtils.isNotBlank(chargeReq.getCustName())){
			 str.append(" and c.cust_name like '%"+chargeReq.getCustName()+"%'");	
		 }
		 if(StringUtils.isNotBlank(chargeReq.getBillState())){
			 if(chargeReq.getBillState().equals("1")){
				 str.append(" and (a.invoice=1 or receive_Invoice=1)");	
			 }else{
				 str.append(" and (a.invoice=0 and receive_Invoice=0)");	
			 }
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getBillType())){
			 str.append(" and a.bill_Type="+chargeReq.getBillType());	
		 }
		 
		 if(StringUtils.isNotBlank(chargeReq.getAmountScale())){
			 if("1".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost<20000");
			 }else if("2".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost>20000 and ar_cost<=50000");
			 }else if("3".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost>50000 and ar_cost<=200000");
			 }else if("4".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost>200000 and ar_cost<=500000");
			 }else if("5".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost>500000 and ar_cost<=2000000");
			 }else if("6".equals(chargeReq.getAmountScale())){
				 str.append("and ar_cost>2000000");
			 }
		 }
		 if(StringUtils.isNotBlank(chargeReq.getOverDaysStart())){
			 str.append(" and sysdate()-ar_date>=").append(chargeReq.getOverDaysStart());
		 }
		 if(StringUtils.isNotBlank(chargeReq.getOverDaysEnd())){
			 str.append(" and sysdate()-ar_date<=").append(chargeReq.getOverDaysEnd());
		 }
		 
		 str.append(" and a.ar_id=this_.ar_id)");
		 str.append(" and ar_Cost>(case when receive_Amount is null then 0 else  receive_Amount end)");
		 c.add(Restrictions.sqlRestriction(str.toString()));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AccrualsRecord> list = this.findBySortCriteria(c, chargeReq);
		/*Criteria c = super.getCriteria();
		
		StringBuffer str=new StringBuffer();
		str.append("select ar_id ar_id,ar_date ar_date,ar_type ar_type,ar_cost ar_cost,INVOICE INVOICE,AR_OPERATE AR_OPERATE,'应收' flag from accruals_record a left join project p on a.proj_id=p.proj_id left join customer c on p.cust_id=c.cust_id where ar_flag='1'");
		str.append(" union");
		str.append(" select cf_id ar_id,cf_date ar_date,cf_type ar_type,cf_amount ar_cost,INVOICE INVOICE,CF_OPERATOR CF_OPERATOR,'实收' flag from cash_flow a left join project p on a.proj_id=p.proj_id left join customer c on p.cust_id=c.cust_id where a.cf_flag='1'");
		
		c.add(Restrictions.sqlRestriction(str.toString()));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<AccrualsRecord> list = this.findBySortCriteria(c, chargeReq);*/
		 return ResultUtil.pageResult(filterCount, chargeReq.getDraw(),list);
	}
	@Override
	public List<AccrualsRecord> findbyProjIdType(String projId, String projNo, String arFlag,String conNo) {
		
		StringBuffer hql = new StringBuffer("from AccrualsRecord ar where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and ar.projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(projNo)){
			hql.append(" and ar.projNo ='").append(projNo).append("'");
		}
		if(StringUtils.isNotBlank(conNo)){
			hql.append(" and ar.conNo ='").append(conNo).append("'");
		}
		if(StringUtils.isNotBlank(arFlag)){
			hql.append(" and ar.arFlag = ").append(Integer.parseInt(arFlag));
		}
		return super.findByHql(hql.toString());
		
	}
	@Override
	public List<AccrualsRecord> getDataByProj(String projId, String arType, String arFlag, String arStatus){
		StringBuffer hql = new StringBuffer("from AccrualsRecord ar where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and ar.projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(arType)){
			hql.append(" and ar.arType ='").append(arType).append("'");
		}
		if(StringUtils.isNotBlank(arStatus)){
			hql.append(" and ar.arStatus ='").append(arStatus).append("'");
		}
		if(StringUtils.isNotBlank(arFlag)){
			hql.append(" and ar.arFlag = ").append(Integer.parseInt(arFlag));
		}
		hql.append(" and ar.receiveAmount is not null ");
		return super.findByHql(hql.toString());
	}

	@Override
	public List<AccrualsRecord> getDataByProjNew(String projId, String arType){
		StringBuffer hql = new StringBuffer("from AccrualsRecord ar where 1=1");
		if(StringUtils.isNotBlank(projId)){
			hql.append(" and ar.projId = '").append(projId).append("'");
		}
		if(StringUtils.isNotBlank(arType)){
			hql.append(" and ar.arType ='").append(arType).append("'");
		}
		return super.findByHql(hql.toString());
	}
	
	@Override
	public List<AccrualsRecord> findbyProjIdType(String projNo) {
		
		StringBuffer hql = new StringBuffer("from AccrualsRecord ar where 1=1");
		
		if(StringUtils.isNotBlank(projNo)){
			hql.append(" and ar.projNo ='").append(projNo).append("'");
		}
		return super.findByHql(hql.toString());
		
	}
	
	/**
	 * 根据工程ID、款项类型获取应收应付流水
	 * @param projId
	 * @param arType
	 * @return
	 */
	@Override
	public AccrualsRecord findByType(String projId, String arType) {
		Criteria criteria = super.getCriteria();
		 //工程id
		if(StringUtils.isNotBlank(projId)){
			criteria.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(arType)){
			criteria.add(Restrictions.eq("arType",arType));
		}
		//未收完费的
		criteria.add(Restrictions.eq("arStatus","1"));
		 
		List<AccrualsRecord> list = this.findByCriteria(criteria);
		if (list !=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询应收大于实收记录数
	 * @author fuliwei
	 * @createTime 2017年3月5日
	 * @param 
	 * @return
	 */
	@Override
	public List<AccrualsRecord> queryAccrualsRecordLength() {
		StringBuffer hql = new StringBuffer("from AccrualsRecord where ar_Cost>receive_Amount or receive_Amount is null");
		return super.findByHql(hql.toString());
	}
	@Override
	public List<AccrualsRecord> findForUpdate(String projId, String arFlag, String contractType) {
		Criteria c = super.getCriteria();
		 //工程id
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 if(StringUtils.isNotBlank(arFlag)){
			 c.add(Restrictions.eq("arFlag",new Integer(arFlag)));
		 }
		 if(StringUtils.isNotBlank(contractType)){
			 c.add(Restrictions.eq("contractType",contractType));
		 }
		return this.findByCriteria(c);
	}
	
	/**
	 * 查询收费通知
	 * @author fuliwei
	 * @createTime 2017年11月24日
	 * @param 
	 * @return
	 */
	@Override
	public List<AccrualsRecord> findAmountNotice(String arType, String arFlag, String deptId) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		
		/*Criteria criteria = super.getCriteria();
		if(StringUtils.isNotBlank(arType)){
			criteria.add(Restrictions.eq("arType", arType));
		}
		
		if(StringUtils.isNotBlank(arFlag)){
			criteria.add(Restrictions.eq("arFlag",new Integer(arFlag)));
		}
		if(StringUtils.isNotBlank(deptId)){
			StringBuffer sql= new StringBuffer("proj_id in(select proj_id from Project where dept_id like'").append(deptId).append("%')");
			 criteria.add(Restrictions.sqlRestriction(sql.toString()));
		}
		
		StringBuffer sql2=new StringBuffer("proj_id in(select proj_id from Accruals_Record where RECEIVE_AMOUNT is null or RECEIVE_AMOUNT='' )");
		criteria.add(Restrictions.sqlRestriction(sql2.toString()));
		
		List<AccrualsRecord> list = this.findByCriteria(criteria);
		return list;*/
		
		StringBuffer hql = new StringBuffer("from AccrualsRecord where (receive_amount is null or receive_amount='' ) and ar_type='").append(arType).append("'");
		hql.append(" and ar_flag=").append(arFlag).append(" and projId in(select projId from Project where corp_id like '").append(loginInfo.getCorpId()).append("%' and dept_id like '").append(deptId).append("%')");
		hql.append(" and projId in(select projId from AccrualsRecord where RECEIVE_AMOUNT is null or RECEIVE_AMOUNT='' ) group by projId");
		return super.findByHql(hql.toString());
		
		
		
		
		
		
		
	}
	/**
	 * 删除工程的应收流水
	 */
	@Override
	public void deleteArList(String projId,String arFlag,String conNo) {
		StringBuffer hql = new StringBuffer("delete from AccrualsRecord where projId='").append(projId).append("'");
		if(StringUtil.isNotBlank(conNo)){
			hql.append(" and conNo='").append(conNo).append("'");
		}
		if(StringUtil.isNotBlank(arFlag)){
			hql.append(" and arFlag='").append(arFlag).append("'");
		}
		super.executeHql(hql.toString());
	}
}
