package cc.dfsoft.project.biz.base.charge.dao.impl;

import cc.dfsoft.project.biz.base.charge.dao.CashFlowDao;
import cc.dfsoft.project.biz.base.charge.dto.ChargeReq;
import cc.dfsoft.project.biz.base.charge.entity.CashFlow;
import cc.dfsoft.project.biz.base.charge.enums.ARFlagEnum;
import cc.dfsoft.project.biz.base.charge.enums.ArContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.contract.entity.SupplementalContract;
import cc.dfsoft.project.biz.base.project.enums.IsSignEnum;
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
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
@Repository
public class CashFlowDaoImpl extends NewBaseDAO<CashFlow, String> implements CashFlowDao{
	@Resource
	IntelligentMeterContractDao imcDao;
	@Resource
	ContractDao contractDao;
	@Resource
	SubContractIntelligentDao subIntelligentDao;
	@Resource
	SubContractDao subContractDao;
	@Resource
	SupplementalContractDao supContractDao;
	@Resource
	SubSupplyContractDao subSupContractDao;
	@Override
	public Map<String, Object> queryCashFlow(ChargeReq chargeReq) {
		 Criteria c =super.getCriteria();
		 
		 //工程id
		 if(StringUtils.isNotBlank(chargeReq.getProjId())){
			 c.add(Restrictions.eq("projId",chargeReq.getProjId()));
		 }
		 //有效记录
		 c.add(Restrictions.eq("isValid","1"));
		 
		 if(StringUtils.isNotBlank(chargeReq.getCfStatus())){
			 c.add(Restrictions.eq("cfStatus",chargeReq.getCfStatus()));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getProjNo())){
			 c.add(Restrictions.eq("projNo",chargeReq.getProjNo()));
		 }
		 if(StringUtils.isNotBlank(chargeReq.getCfFlag()) && chargeReq.getCfFlag().equals("1") ){
			 //c.add(Restrictions.eq("cfFlag",new Integer(chargeReq.getCfFlag())));
			 StringBuffer sql = new StringBuffer();
			 sql.append("1 =1 and cf_flag = '1' or cf_flag = '0'"); //查询收款记录和退款记录
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		 }else if(StringUtils.isNotBlank(chargeReq.getCfFlag())){
			 c.add(Restrictions.eq("cfFlag",new Integer(chargeReq.getCfFlag())));  //查询退款记录
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getCfType())){
			 c.add(Restrictions.gt("cfType",chargeReq.getCfType()));
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
		 List<CashFlow> list = this.findBySortCriteria(c, chargeReq);
		 if(list!=null && list.size()>0){
			 for(CashFlow ar : list){
				 //智能表合同款
				 String increment="";
				 String conNo="";
				 String contractAmount="";
				 if(StringUtils.isNotBlank(ar.getContractType()) && ar.getContractType().equals(ArContractTypeEnum.INTELLIGENCE.getValue())){
					if(ar.getCfFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) || ar.getCfFlag().toString().equals(ARFlagEnum.REFUND_MENT.getValue())){
						 IntelligentMeterContract imc = imcDao.findContractByprojId(ar.getProjId());
						 if(imc != null){
							//increment = imc.getIncrement();
							conNo = imc.getImcNo();
							contractAmount = imc.getTotalCost().toString();
						 }
					 }else{
						 //智能表分包合同号
						 SubContractIntelligent subIntelligent = subIntelligentDao.findContractByprojId(ar.getProjId());
						 if(subIntelligent != null){
							conNo = subIntelligent.getItScNo();
							contractAmount = subIntelligent.getTotalCost().toString();
						 }
					 }
				 }else if(StringUtils.isNotBlank(ar.getContractType()) && ar.getContractType().equals(ArContractTypeEnum.SUP_CONTRACT.getValue())){
					 //补充协议
					 if(ar.getCfFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) || ar.getCfFlag().toString().equals(ARFlagEnum.REFUND_MENT.getValue())){
						 List<SupplementalContract> supCons = supContractDao.findByScNo(ar.getConNo());
						 if(supCons != null && supCons.size()>0){
							//increment = supCons.get(0).getIncrement();
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
					 if(ar.getCfFlag().toString().equals(ARFlagEnum.RECEIVE_ACCOUNT.getValue()) || ar.getCfFlag().toString().equals(ARFlagEnum.REFUND_MENT.getValue())){
						 //安装合同
						 Contract contract=contractDao.viewContractByprojId(ar.getProjId());
						 if(contract!=null){
							//increment = contract.getIncrement();
							conNo = contract.getConNo();
							contractAmount = contract.getContractAmount().toString();
						 }
					 }else{
						 SubContract subCon=subContractDao.findByProjId(ar.getProjId());
						 if(subCon!=null){
							conNo = subCon.getScNo();
							contractAmount = subCon.getScAmount().toString();
						 }
					 }
				 }
				// ar.setIncrement(StringUtils.isNotBlank(increment)?increment:"");
				 ar.setConNo(conNo);
				 ar.setContractAmount(contractAmount);
			 }
		 }
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, chargeReq.getDraw(),list);
	}
	@Override
	public Map<String, Object> queryCashFlowNew(ChargeReq chargeReq) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		Criteria c = super.getCriteria();
		StringBuffer str=new StringBuffer();
		  str.append(" exists(select * from cash_flow a left join project p on a.proj_id=p.proj_id left join customer c on p.cust_id=c.cust_id where 1=1");
		 if(StringUtils.isNotBlank(chargeReq.getCfStatus())){
			 str.append(" and a.cf_Status="+chargeReq.getCfStatus());
		 }
		 if(StringUtils.isNotBlank(chargeReq.getProjNo())){
			 str.append(" and a.proj_no='"+chargeReq.getProjNo()).append("'");
		 }
		 if(StringUtils.isNotBlank(chargeReq.getCfFlag())){
			 str.append(" and a.cf_Flag="+chargeReq.getCfFlag());			
		 }
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getCfType())){
			 str.append(" and a.cf_Type="+chargeReq.getCfType());	
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
		 //有效记录
		 str.append(" and a.is_valid= '1' ");	
		 //收款类型
		 if(StringUtils.isNotBlank(chargeReq.getBillType())){
			 str.append(" and a.bill_Type="+chargeReq.getBillType());	
		 }
		 str.append(" and a.cf_id=this_.cf_id)");
		 //金额范围
		 if(StringUtils.isNotBlank(chargeReq.getAmountScale())){
			 if("1".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount<20000");
			 }else if("2".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount>20000 and cf_amount<=50000");
			 }else if("3".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount>50000 and cf_amount<=200000");
			 }else if("4".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount>200000 and cf_amount<=500000");
			 }else if("5".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount>500000 and cf_amount<=2000000");
			 }else if("6".equals(chargeReq.getAmountScale())){
				 str.append("and cf_amount>2000000");
			 }
		 }
		 
		 if(StringUtils.isNotBlank(chargeReq.getOverDaysStart())){
			 str.append(" and sysdate()-cf_date>=").append(chargeReq.getOverDaysStart());
		 }
		 if(StringUtils.isNotBlank(chargeReq.getOverDaysEnd())){
			 str.append(" and sysdate()-cf_date<=").append(chargeReq.getOverDaysEnd());
		 }
		 
		 c.add(Restrictions.sqlRestriction(str.toString()));
		 // 数据库根据条件过滤记录数
		 int filterCount = this.countByCriteria(c);

		 // 根据条件获取查询信息
		 List<CashFlow> list = this.findBySortCriteria(c, chargeReq);
			
		 // 返回结果
		 return ResultUtil.pageResult(filterCount, chargeReq.getDraw(),list);
	}
	/**
	 * 根据工程ID获得指定类型的收付流失
	 * @param projID
	 * @param typeID
	 * @return
	 */
	public BigDecimal getDownPayment(String projID ,String typeID){
		BigDecimal downPayment  = BigDecimal.ZERO;
		List list = this.queryCashFlawByProjID(projID);
		
		if (list !=null && list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				CashFlow cashFlow = (CashFlow)list.get(i);
				
				if (cashFlow.getCfType().equals(typeID)) {
					downPayment = cashFlow.getCfAmount();
				}
			}
		}
		
		return downPayment;
	}
	

	/**
	 * 根据工程ID获得收付流失
	 * @param projID
	 * @param typeID
	 * @return
	 */
	public List queryCashFlawByProjID(String projID ){
		Criteria criteria = super.getCriteria();
		criteria.add(Restrictions.eq("projId",projID));
		List<CashFlow> list = this.findByCriteria(criteria);
		return list;
	}
	
	
	/**
	 * 查询已付合同款或工程款
	 * @author fuliwei
	 * @createTime 2017年8月16日
	 * @param 
	 * @return
	 */
	@Override
	public BigDecimal queryPayAmount(String projId, String type) {
		 Criteria c = super.getCriteria();
		
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 
		 if(StringUtils.isNotBlank(type)){
			 c.add(Restrictions.eq("cfFlag",new Integer(type)));
		 }
		 c.add(Restrictions.eq("isValid","1"));//有效的
		 List<CashFlow> list= this.findByCriteria(c);
		 BigDecimal cfAmount=BigDecimal.ZERO;
		 if(list!=null && list.size()>0){
			 for(int i=0;i<list.size();i++){
				 if(list.get(i).getCfAmount()!=null){
					 cfAmount=cfAmount.add(list.get(i).getCfAmount());
				 }
			 }
		 }
		 
		 return cfAmount;
	}
	
	/**
	 * 根据实收付单id修改收付成功标记
	 * @param cfId
	 * @param resultFlag
	 * @author liaoyq
	 * @createTime 2017-11-09
	 * @return
	 */
	@Override
	public boolean modifyCashFlowById(String cfId,String resultFlag) {
		if(StringUtils.isNotBlank(cfId)){
			String hql = "update CashFlow set resultFlag = '" + resultFlag + "' where cfId ='"+cfId+"'";
			 return this.executeHql(hql);
		}
		return false;
	}
	/**
	 * 根据付款单ID和工程编号查询付款信息
	 */
	@Override
	public CashFlow queryById(String cfId, String projNo) {
		 Criteria c = super.getCriteria();
			
		 if(StringUtils.isNotBlank(projNo)){
			 c.add(Restrictions.eq("projNo",projNo));
		 }
		 if(StringUtils.isNotBlank(cfId)){
			 c.add(Restrictions.eq("cfId",cfId));
		 }
		 List<CashFlow> list= this.findByCriteria(c);
		 if(list!=null && list.size()>0){
			 return list.get(0);
		 }
		return null;
	}

	@Override
	public List<CashFlow> queryCashFlawOnly(String projId, String billStatus) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId",projId));
		}
		if(StringUtils.isNotBlank(billStatus)){
			c.add(Restrictions.eq("billStatus",billStatus));
		}
		return this.findByCriteria(c);
	}
	@Override
	public List<CashFlow> queryCashFlowByProjIdType(String projId, String cfFlag,String conNo) {
		Criteria c = super.getCriteria();
		
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
		 }
		 if(StringUtils.isNotBlank(cfFlag)){
			 c.add(Restrictions.eq("cfFlag",Integer.parseInt(cfFlag)));
		 }
		 if(StringUtils.isNotBlank(conNo)){
			 c.add(Restrictions.eq("conNo",conNo));
		 }
		 c.add(Restrictions.eq("isValid","1"));//有效
		 return this.findByCriteria(c);
	}



	@Override
	public boolean isHaveVirtualInvoice(String projId) {
		Criteria c = super.getCriteria();
		c.add(Restrictions.eq("projId",projId));
		c.add(Restrictions.eq("billStatus", IsSignEnum.IS_SIGN.getValue()));//是虚拟发票标记
		c.add(Restrictions.eq("isValid","1"));//有效
		List<CashFlow> byCriteria = this.findByCriteria(c);
		if (byCriteria!=null && byCriteria.size()>0){
			return true;
		}
		return false;
	}

	/**
	 * 查询是否满足全额收款的条件
	 * @author fulw
	 * @createTime 2017-1-11
	 * @param projId
	 * @return String
	 *//*
	@Override
	public String queryIsFullAmount(String id,String cfFlag) {
		Criteria c=super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId", id));
		}
		if(StringUtils.isNotBlank(cfFlag)){
			c.add(Restrictions.eq("cfFlag", cfFlag));
		}
		List<CashFlow> cfList=this.findByCriteria(c);
		
		if(cfList.size()>0){
			return "true";
		}else{
			return "false";
		}
	}*/
}
