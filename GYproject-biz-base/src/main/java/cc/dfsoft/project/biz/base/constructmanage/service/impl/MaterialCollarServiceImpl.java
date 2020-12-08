package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialCollarDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlan;
import cc.dfsoft.project.biz.base.constructmanage.enums.MaterialPlanFeedBackEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialCollarService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.enums.ProcurementPlanExport;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialCollarServiceImpl implements MaterialCollarService{
	
	
	/**材料记录dao*/
	@Resource
	MaterialCollarDao materialCollarDao;
	
	/**材料清单dao*/
	@Resource
	MaterialListDao marterialListDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**施工计划Dao*/
	@Resource
	ConstructionPlanDao  constrcutionPlanDao;
	
	/**材料计划Dao*/
	@Resource
	MaterialPlanDao materialPlanDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialCollars(List<MaterialCollar> materialCollars) {
		
		if(materialCollars!=null && materialCollars.size()>0){
			List<MaterialList> list=new ArrayList<MaterialList>();
			for(int i=0;i<materialCollars.size();i++){
				MaterialCollar mc = materialCollars.get(i);
				//设置id
				mc.setMcId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				mc.setMaterialGetter(loginInfo!=null?loginInfo.getStaffId():"");
				if(mc.getBmPrice()!=null && mc.getMcNum()!=null){
					Double amount = mc.getBmPrice().multiply(mc.getMcNum()).doubleValue();
					mc.setMcAmount(amount+"");//合计金额
				}
				MaterialList ml = marterialListDao.get(mc.getMaterialId());
				BigDecimal getAnum = BigDecimal.ZERO;
				if(mc.getMcNum()!=null && ml.getGetAnum()!=null){
					getAnum = mc.getMcNum().add(ml.getGetAnum());//计算材料领用数量
				}else if(mc.getMcNum()!=null){
					getAnum = mc.getMcNum();
				}else if(ml.getGetAnum()!=null){
					getAnum = ml.getGetAnum();
				}
				ml.setGetAnum(getAnum);//更新材料领用总量
				list.add(ml);
			}
			marterialListDao.batchUpdateObjects(list);//更新材料清单
			materialCollarDao.batchInsertObjects(materialCollars);//新增材料记录
		}
		
		if(materialCollars!=null && materialCollars.size()>0){
			String projId=materialCollars.get(0).getProjId();
			Date mgDate=materialCollars.get(0).getMgDate();
			//通过时间删除材料计划
			materialPlanDao.deleteByMgDate(mgDate);
			//查找工程
			Project pro=projectDao.get(projId);
			ConstructionPlan cp=constrcutionPlanDao.viewPlanById(projId);
			MaterialPlan mp=new MaterialPlan();
			if(null!=pro && null!=cp){
				mp.setMpId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));//主键id
				mp.setProjId(pro.getProjId());									//工程Id
				mp.setProjNo(pro.getProjNo());									//工程编号
				mp.setProjName(pro.getProjName());								//工程名称
				mp.setCuName(cp.getCuName());//分包单位
				mp.setCuLegalRepresent(cp.getCuLegalRepresent());//项目经理
				mp.setBuilder(cp.getBuilder());//甲方代表
				mp.setIsFeedBack(MaterialPlanFeedBackEnum.HAVE_NOT_FEED_BACK.getValue());//未反馈
				mp.setCreateDate(mgDate);//创建时间
			}
			materialPlanDao.save(mp);
		}
		
		
		
		
		
		
		
		
		
		
	}
	
}
