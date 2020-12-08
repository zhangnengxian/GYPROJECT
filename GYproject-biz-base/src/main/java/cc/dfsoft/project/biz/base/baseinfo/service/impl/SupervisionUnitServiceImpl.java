package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import cc.dfsoft.project.biz.base.baseinfo.dao.SupervisionUnitDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.SupervisionQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SupervisionUnit;
import cc.dfsoft.project.biz.base.baseinfo.service.SupervisionUnitService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Map;

/**
 * 
 * @author cui 监理单位ServiceiImpl
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SupervisionUnitServiceImpl implements SupervisionUnitService {

	/** 监理单位dao */
	@Resource
	SupervisionUnitDao supervisionUnitDao;

	@Resource
	ProjectDao projectDao;
	@Resource
	ConstructionPlanDao constructionPlanDao;

	@Resource
	OperateRecordService operateRecordService;
	@Resource
	OperateRecordDao operateRecordDao;

	/**
	 * 监理单位列表查询
	 * 
	 * @param supervisionUnitReq
	 * @return
	 */
	@Override
	public Map<String, Object> querySupervisionUnit(SupervisionQueryReq supervisionUnitReq) throws ParseException {

		return supervisionUnitDao.querySupervisionUnit(supervisionUnitReq);
	}

	/**
	 * 查询监理单位
	 */
	@Override
	public SupervisionUnit viewSupervisionById(String id) {
		return supervisionUnitDao.get(id);
	}

	/**
	 * 删除监理单位
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delSupervision(String id){
		supervisionUnitDao.delete(id);
	}



	/**
	 * 保存监理单位
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveSupervision(SupervisionUnit supervisionUnit){
		SupervisionUnit supervisionUnitResult=supervisionUnit;
		if(StringUtils.isBlank(supervisionUnit.getSuId())){  // 新增
		//监理单位是否已存在
		if(this.viewSupervisionById(supervisionUnit.getSuId())!=null){
			return "exist";
		}
		supervisionUnitResult.setSuId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));//生成主键ID 先用分包ID
//		supervisionUnitDao.save(supervisionUnit);	
		}else{  //修改
			supervisionUnitResult = supervisionUnitDao.get(supervisionUnit.getSuId());
			supervisionUnitResult.setSuName(supervisionUnit.getSuName());
			supervisionUnitResult.setShortName(supervisionUnit.getShortName());
			supervisionUnitResult.setSuDirector(supervisionUnit.getSuDirector());
			supervisionUnitResult.setSuPhone(supervisionUnit.getSuPhone());
			supervisionUnitResult.setSuMobile(supervisionUnit.getSuMobile());
//			supervisionUnitDao.save(supervisionUnitResult);
		}
		supervisionUnitDao.saveOrUpdate(supervisionUnitResult);
		return Constants.OPERATE_RESULT_SUCCESS;
	}


	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void modifyInfo(ConstructionPlan cplan) {

		Project pro = projectDao.get(cplan.getProjId());
		if (pro!=null){
			pro.setSuId(cplan.getSuId());
			pro.setSuName(cplan.getSuName());
			pro.setSuDirector(cplan.getSuDirector());
			projectDao.saveOrUpdate(pro);
		}

		ConstructionPlan cp = constructionPlanDao.findByProjId(cplan.getProjId());
		String suId=cp!=null?cp.getSuId():null;

		if (cp!=null){
			cp.setSuId(cplan.getSuId());
			cp.setSuName(cplan.getSuName());
			cp.setSuDirector(cplan.getSuDirector());
			cp.setSuPhone(cplan.getSuPhone());
			constructionPlanDao.saveOrUpdate(cp);
		}

		if (StringUtils.isNotBlank(suId) && !suId.equals(cplan.getSuId())){//监理单位改变时更新监理派遣通知
			operateRecordDao.batUpdateHistoryRecordByBoId(cplan.getProjId(),null, StepEnum.SUPERVISION_DISPATCH.getValue());
			operateRecordService.saveOperateRecord(cplan.getProjId());//生成派遣通知
		}

	}
}
