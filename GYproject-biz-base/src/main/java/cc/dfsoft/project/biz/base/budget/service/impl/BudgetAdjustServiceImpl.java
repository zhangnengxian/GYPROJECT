package cc.dfsoft.project.biz.base.budget.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.budget.dao.BudgetAdjustDao;
import cc.dfsoft.project.biz.base.budget.dao.BudgetDao;
import cc.dfsoft.project.biz.base.budget.dao.BudgetTotalTableDao;
import cc.dfsoft.project.biz.base.budget.dao.ChangeManagementDao;
import cc.dfsoft.project.biz.base.budget.dao.ProjectCostSummaryDao;
import cc.dfsoft.project.biz.base.budget.dao.ProjectRateDao;
import cc.dfsoft.project.biz.base.budget.dto.BudgetChangeReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetReq;
import cc.dfsoft.project.biz.base.budget.dto.BudgetTotalQueryReq;
import cc.dfsoft.project.biz.base.budget.dto.ProjCostSummaryReq;
import cc.dfsoft.project.biz.base.budget.entity.Budget;
import cc.dfsoft.project.biz.base.budget.entity.BudgetAdjust;
import cc.dfsoft.project.biz.base.budget.entity.BudgetTotalTable;
import cc.dfsoft.project.biz.base.budget.entity.ProjectCostSummary;
import cc.dfsoft.project.biz.base.budget.entity.ProjectRate;
import cc.dfsoft.project.biz.base.budget.enums.BudgetAdjustEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetCostTypeEnum;
import cc.dfsoft.project.biz.base.budget.enums.BudgetTypeEnum;
import cc.dfsoft.project.biz.base.budget.service.BudgetAdjustService;
import cc.dfsoft.project.biz.base.contract.enums.IsSignEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class BudgetAdjustServiceImpl implements BudgetAdjustService{
	
	/**预算Dao*/
	@Resource
	BudgetDao budgetDao;
	/**预算费率Dao*/
	@Resource
	ProjectRateDao projectRateDao;
	
	/**预算调整Dao*/
	@Resource
	BudgetAdjustDao budgetAdjustDao;
	
	/**单位工程费用汇总Dao*/
	@Resource
	ProjectCostSummaryDao projectCostSummaryDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**业务操作记录服务接口*/
	OperateRecordService operateRecordService;
	
	/**变更记录*/
	@Resource
	ChangeManagementDao changeManagementDao;
	
	/**预算总表*/
	@Resource
	BudgetTotalTableDao budgetTotalTableDao;
	/**
	 * 批量导入单位工程费用汇总表
	 * @author fuliwei
	 * @createTime  2016-7-14
	 * @param jsonArr 工程id 费用类型 是否预算调整
	 * @return 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertCostSum(JSONArray jsonArr,BudgetReq req) {
		List<ProjectCostSummary> list=new ArrayList<ProjectCostSummary>();
		BigDecimal amount=new BigDecimal(0.00);
		Budget budget=budgetDao.queryByType(req.getCmId(), req.getMcType());
		
		if(budget==null||budget.getBudgetId()==null){
			budget=new Budget();
			//Budget budget1=budgetDao.queryById(req.getProjId(),BudgetTypeEnum.ADJUSTED.getValue());//根据工程id和预算调整类型查询		
			Project proj=projectDao.get(req.getProjId());
			//BeanUtil.copyNotNullProperties(budget, budget1);
			budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));//新建调整预算
			budget.setProjId(req.getProjId());										  //更新工程ID						
			budget.setProjNo(proj.getProjNo());								  //更新工程编号
			//ChangeManagement change=changeManagementDao.queryByprojId(req.getProjId());//根据projId查变更记录
			//budget.setCmId(change.getCmId());								  //更新变更记录id
			budget.setBudgetAdjustDate(budgetDao.getDatabaseDate());		  //更新调整时间
			budget.setBudgetTypeId(BudgetTypeEnum.ADJUSTED.getValue());       //更新预算总表预算类型-调整预算
			budget.setCmId(req.getCmId());
			budget.setMcType(req.getMcType());
			budgetDao.save(budget);//保存预算总表
		}
		
			 
		
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("costName"))){
				continue;
			}
			ProjectCostSummary pro=JsonUtils.jsonToBean(jo, ProjectCostSummary.class);
			pro.setPcsId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
			pro.setCostType(req.getCostType());	//费用类型
			pro.setProjId(req.getProjId());
			pro.setBudgetId(budget.getBudgetId());
			pro.setIsAdjust(req.getIsAdjust());	//是否调整
			pro.setIsAdjust(BudgetAdjustEnum.ADJUSTED.getValue());
			if(pro.getCostName().equals("建筑工程造价")){
				amount=pro.getAmount();
			}
			list.add(pro);
		}
		ProjCostSummaryReq pros=new ProjCostSummaryReq();
		pros.setCostType(req.getCostType());
		pros.setBudgetId(budget.getBudgetId());
		pros.setIsAdjust("1");
		List<ProjectCostSummary> li =projectCostSummaryDao.queryCostSummaryList(pros);
		//单位费用汇总已存在
		if(null!=li&&li.size()>0){
			projectCostSummaryDao.batchDeleteObjects(li);
		}
		
			//不存在单位费用汇总 批量插入
		projectCostSummaryDao.batchInsertObjects(list);
		BigDecimal total=null==budget.getBudgetTotalCost()?new BigDecimal(0):budget.getBudgetTotalCost();
		ProjectRate projectRate=projectRateDao.get("1");
		//民用户内
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_INDOOR.getValue())){
			BigDecimal val=null==budget.getHomeInstallCost()?new BigDecimal(0):budget.getHomeInstallCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setHomeInstallCost(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);//更新预算
			return;
		}
		//民用庭院
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_COURTYARD.getValue())){
			BigDecimal val=null==budget.getYardInstallCost()?new BigDecimal(0):budget.getYardInstallCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setYardInstallCost(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);//更新预算
			return;
		}
		//民用土建
		if(req.getCostType().equals(BudgetCostTypeEnum.CIVIL_ENGINEERING.getValue())){
			BigDecimal val=null==budget.getCivilCost()?new BigDecimal(0):budget.getCivilCost();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setCivilCost(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);//更新预算
			return;
		}
		//锅炉仪表
		if(req.getCostType().equals(BudgetCostTypeEnum.BOILER_INSTRUMENT.getValue())){
			BigDecimal val=null==budget.getBoilerMeter()?new BigDecimal(0):budget.getBoilerMeter();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setBoilerMeter(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);
			return;
	
		}
		//工艺
		if(req.getCostType().equals(BudgetCostTypeEnum.TECHNOLOGY.getValue())){
			BigDecimal val=null==budget.getTechnology()?new BigDecimal(0):budget.getTechnology();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setTechnology(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);
			return;
	
		}
		//庭院土方
		if(req.getCostType().equals(BudgetCostTypeEnum.COURTYARD_EARTHWORK.getValue())){
			BigDecimal val=null==budget.getYardEarthwork()?new BigDecimal(0):budget.getYardEarthwork();
			budget.setBudgetTotalCost(total.subtract(val).add(amount));
			budget.setYardEarthwork(amount);
			budgetCount(budget,projectRate);
			budgetDao.update(budget);
			return;
	
		}
	}
	
	/**
	 * 总表导入
	 * @author
	 * @createTime 2016-12-30
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertTotalCostSum(JSONArray jsonArr, BudgetReq req) {
		List<BudgetTotalTable> list=new ArrayList<BudgetTotalTable>();
		BigDecimal amount=new BigDecimal(0.00);//合计
		BigDecimal inspectionCost=new BigDecimal(0.00);//监检费
		BigDecimal suCost=new BigDecimal(0.00);//监检费
		BigDecimal storeCost=new BigDecimal(0.00);//监检费
		String note="";
		BudgetTotalQueryReq btReq=new BudgetTotalQueryReq();
		//查找调整预算
		Budget budget=budgetDao.queryById(req.getProjId(), BudgetTypeEnum.ADJUSTED.getValue(),req.getCmId());
		
		
		if(budget==null||budget.getBudgetId()==null){
			budget=new Budget();
			Project proj=projectDao.get(req.getProjId());
			if(proj!=null){
				budget.setBudgetId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));//主键id
				budget.setProjId(req.getProjId());
				budget.setProjNo(proj.getProjNo());
				budget.setBudgetTypeId(BudgetTypeEnum.ADJUSTED.getValue());//更新预算总表预算类型-调整预算
				budget.setCmId(req.getCmId());//变更id
				budget.setBudgetAdjustDate(budgetDao.getDatabaseDate());//预算调整时间
				budget.setMcType(req.getMcType());//预算类型  0变更 1签证
				budget.setIsSignSuContrct(IsSignEnum.HAVE_NOT_SIGN.getValue());//未签订补充协议	
				budgetDao.save(budget);
			}
		}
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			//如果分项名称为空
			if(StringUtils.isBlank(jo.getString("subitemName"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				bt.setSubitemName("分项合计");
				BigDecimal bd=new BigDecimal(jo.getString("amount")); 
				bt.setAmount(bd);
				bt.setBudgetType(BudgetTypeEnum.ADJUSTED.getValue());
				bt.setCmId(req.getCmId());
				list.add(bt);
				continue;
			}
			//如果金额不为空
			if(StringUtils.isNotBlank(jo.getString("amount"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				bt.setBudgetType(BudgetTypeEnum.ADJUSTED.getValue());
				bt.setCmId(req.getCmId());
				if(bt.getSubitemName().equals("监检费")){
					inspectionCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("监理费")){
					suCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("农民工工资储备金")){
					storeCost=bt.getAmount();
				}
				if(bt.getSubitemName().equals("合计")){
					amount=bt.getAmount();
				}
				list.add(bt);
			}
			//备注拼写
			if(StringUtils.isBlank(jo.getString("amount"))){
				BudgetTotalTable bt=JsonUtils.jsonToBean(jo, BudgetTotalTable.class);
				bt.setBtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COST));
				bt.setProjId(req.getProjId());
				bt.setProjNo(budget.getProjNo());
				bt.setBudgetId(budget.getBudgetId());
				note=note+bt.getSubitemName();
			}
			
		}
		
		
		btReq.setProjId(req.getProjId());
		btReq.setBudgetId(budget.getBudgetId());
		btReq.setCmId(req.getCmId());
		btReq.setBudgetType(BudgetTypeEnum.ADJUSTED.getValue());
		
		List<BudgetTotalTable> li =budgetTotalTableDao.queryBudgetTotalTableList(btReq);
		//删除原来的总造价表(调整类型)
		if(null!=li&&li.size()>0){
			budgetTotalTableDao.batchDeleteObjects(li);
		}
		budgetTotalTableDao.batchInsertObjects(list);
		//更新预算表
		budget.setBudgetTotalCost(amount);
		budget.setInspectionCost(inspectionCost);
		budget.setSuCost(suCost);
		budget.setStoreCost(storeCost);
		budget.setRemark(note);
		budgetDao.update(budget);
	}
	
	
	
	
	
private Budget budgetCount(Budget budget,ProjectRate projectRate){
	    BigDecimal val1=null==budget.getYardEarthwork()?new BigDecimal(0):budget.getYardEarthwork();
	    BigDecimal val2=null==budget.getTechnology()?new BigDecimal(0):budget.getTechnology();
	    BigDecimal val3=null==budget.getBoilerMeter()?new BigDecimal(0):budget.getBoilerMeter();
	    BigDecimal val4=null==budget.getCivilCost()?new BigDecimal(0):budget.getCivilCost();
	    BigDecimal val5=null==budget.getYardInstallCost()?new BigDecimal(0):budget.getYardInstallCost();
	    BigDecimal val6=null==budget.getHomeInstallCost()?new BigDecimal(0):budget.getHomeInstallCost();
	    BigDecimal inspectionCost=null==budget.getInspectionCost()?new BigDecimal(0):budget.getInspectionCost();
	    BigDecimal suCost=null==budget.getSuCost()?new BigDecimal(0):budget.getSuCost();
	    BigDecimal storeCost=null==budget.getStoreCost()?new BigDecimal(0):budget.getStoreCost();
	    BigDecimal tempVal=new BigDecimal(0.00);
	    
	    DecimalFormat df=new DecimalFormat("#.00");  
	    BigDecimal total=val1.add(val2).add(val3).add(val4).add(val5).add(val6);	   
	    budget.setInspectionCost( new BigDecimal(df.format(total.multiply(projectRate.getInspection()))));	    
	    budget.setSuCost(new BigDecimal(df.format((budget.getInspectionCost()).add(total).multiply(projectRate.getSupervisor()))));
	    budget.setStoreCost(new BigDecimal(df.format((budget.getInspectionCost().add(total).add( budget.getSuCost())).multiply(projectRate.getStore()))));
	    tempVal=budget.getBudgetTotalCost().subtract(inspectionCost.add(suCost).add(storeCost));
	    tempVal=tempVal.add(budget.getInspectionCost().add(budget.getSuCost()).add(budget.getStoreCost()));
	    // budget.setBudgetTotalCost(new BigDecimal(df.format((budget.getInspectionCost().add(total).add( budget.getSuCost()).add(budget.getStoreCost())))));
	    budget.setBudgetTotalCost(tempVal);
	    return budget;
}
	@Override
	public BudgetAdjust queryByprojId(String projId) {
		BudgetAdjust budgetAdjust=budgetAdjustDao.queryByprojId(projId);
		return budgetAdjust;
	}
	
	/**
	 * 更新预算调整表
	 * @author fuliwei
	 * @createTime 2016-7-13
	 * @param budgetAdjust
	 * @return 
	 */	
	@Override
	public void updateBudgeAdjsut(BudgetAdjust budgetAdjust) {
		budgetAdjustDao.updateNotNullProperties(budgetAdjust, budgetAdjust.getBudgetAdjustId());
		Project pro= projectDao.get(budgetAdjust.getProjId());
		/*pro.setProjStatusId(WorkFlowUtil.workFlowStatus(StepEnum.BUDGET_RESULT_REGISTER.getValue()));
		projectDao.update(pro);*/
		//形成操作记录
		String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COST);//生成唯一ID
		//预算调整的步骤不对
		operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(),StepEnum.BUDGET_RESULT_REGISTER.getValue(), StepEnum.BUDGET_RESULT_REGISTER.getMessage(), "");
	}

	
	@Override
	public Map<String, Object> queryBudgetAdjust(BudgetChangeReq budgetChangeReq) throws ParseException{
		return budgetDao.queryBudgetAdjust(budgetChangeReq);
	}
	
}
