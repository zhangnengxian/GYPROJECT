package cc.dfsoft.project.biz.base.accept.service.impl;

import cc.dfsoft.project.biz.base.accept.dao.MarketInfoDao;
import cc.dfsoft.project.biz.base.accept.service.MarketInfoService;
import cc.dfsoft.project.biz.base.design.dto.DesignDispatchDto;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MarketInfoServiceImpl implements MarketInfoService {
    
	@Resource
	MarketInfoDao marketInfoDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	OperateRecordService operateRecordService;

	@Resource
	OperateRecordDao operateRecordDao;

	/**
	 * 市场营销员列表查询
	 * @author wangang
	 * @createTime 2019年11月27日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryMarket(DesignerQueryReq designerQueryReq){
		return marketInfoDao.queryMarket(designerQueryReq);
	};

	/**
	 * 市场派工后更新工程表
	 * @author wangang
	 * @createTime 2019-11-27
	 * @param designDispatchReq
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation= Propagation.REQUIRED)
	public void update(DesignDispatchDto designDispatchReq) {
		//更新工程信息
		Project pro=projectDao.get(designDispatchReq.getProjId());//根据Id查询工程
		pro.setAccepterId(designDispatchReq.getMarketId()); 	     //市场营销员
		pro.setAccepter(designDispatchReq.getMarket());
		//String statusId=workFlowService.queryProjStatusId(pro.getCorpId(),pro.getProjectType(),pro.getContributionMode(), WorkFlowActionEnum.DESIGN_DISPATCH.getActionCode(), true);
		String statusId=ProjStatusEnum.PROJECT_ACCEPT.getValue();//受理申请
		pro.setProjStatusId(statusId);          				 //更新工程状态
		pro.setToDoer(designDispatchReq.getMarket());
		//更新工程
		projectDao.update(pro);


		//生成受理申请待办
		operateRecordService.createWxOperateRecord(pro.getProjId(), pro.getProjNo(),StepEnum.PROJECT_ACCEPT.getValue(), StepEnum.PROJECT_ACCEPT.getMessage(),"市场派工",
				designDispatchReq.getMarket(),","+designDispatchReq.getMarketId()+",", OperateWorkFlowEnum.WAIT_DONE.getValue(),pro.getCorpId(),pro.getProjectType(),pro.getContributionMode());

		//将操作记录置为已办
		OperateRecord or = operateRecordDao.findByGread(pro.getProjId(), pro.getCorpId(), pro.getProjectType(), pro.getContributionMode(), StepEnum.MARKET_DISPATCH.getValue(), "", OperateWorkFlowEnum.WAIT_DONE.getValue());
		if (or != null) {
			or.setModifyStatus(OperateWorkFlowEnum.HAVE_DONE.getValue());
			or.setOperateTime(operateRecordDao.getDatabaseDate());
			operateRecordDao.saveOrUpdate(or);
		}

	}

}
