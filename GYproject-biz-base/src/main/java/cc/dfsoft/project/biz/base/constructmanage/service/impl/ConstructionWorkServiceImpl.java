package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.ConstructionWorkDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.constructmanage.service.ConstructionWorkService;
import cc.dfsoft.project.biz.base.maintain.service.AbandonedRecordService;
import cc.dfsoft.project.biz.base.messagesync.pojo.ResultMsg;
import cc.dfsoft.project.biz.base.messagesync.service.SynchronizedService;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.NoticeService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.project.biz.ifs.WebServiceTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.exception.ExpressException;
import cc.dfsoft.uexpress.common.util.Annotations;
import cc.dfsoft.uexpress.common.util.CoordinatesUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ConstructionWorkServiceImpl extends NewBaseDAO<ConstructionWork,String> implements ConstructionWorkService {
	
	@Resource
	ConstructionWorkDao constructionWorkDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	NoticeService noticeServie;
	
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Resource
	SignatureService signatureService;
	@Resource
	SignNoticeDao signNoticeDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;

	@Resource
	AbandonedRecordService abandonedRecordService;
	@Resource
	SynchronizedService synchronizedService;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String constructionWorkSave(ConstructionWork constructionWork) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(constructionWork.getCwId())){
			flag = true;
			constructionWork.setCwId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
		}
		//更新工程坐标
		Project pro=projectDao.get(constructionWork.getProjId());

		if(StringUtils.isBlank(pro.getProjLongitude())){//如果工程经纬度为空 就保存交底获取的坐标
			if(StringUtils.isNotBlank(constructionWork.getLongitude())){
				Map<String, Object> map = CoordinatesUtil.coordinatesConversion(constructionWork.getLongitude(), constructionWork.getLatitude());
				if(map!=null&&!map.isEmpty()){
					pro.setProjLongitude(map.get("x").toString());
					pro.setProjLatitude(map.get("y").toString());
					projectDao.update(pro);
				}
			}
		}
		constructionWork.setDelFlag(0);
		constructionWorkDao.saveOrUpdate(constructionWork);
		
		//2018 1-20-fuliwei
		
		/*//现场代表 设计员duDeputy 施工员cuPm 监理suFieldJgj
		if(StringUtils.isNotBlank(constructionWork.getFieldPrincipal())
			&& StringUtils.isNotBlank(constructionWork.getDuDeputy())
			&& StringUtils.isNotBlank(constructionWork.getCuPm())
			&& StringUtils.isNotBlank(constructionWork.getSuFieldJgj())){
			//所有通知置为已签字
			signNoticeService.updateAllSignState(SignDataTypeEnum.TECHNOLOGY_TELL.getValue(),constructionWork.getCwId());
		}else{
			if(StringUtils.isNotBlank(constructionWork.getFieldPrincipal())
					&& StringUtils.isBlank(constructionWork.getDuDeputy())
					&& StringUtils.isBlank(constructionWork.getCuPm())
					&& StringUtils.isBlank(constructionWork.getSuFieldJgj())){
				//只有现场代表的签字的时候 才激活下一个签字
				//把自己的置为已签字 
				signNoticeService.saveSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.TECHNOLOGY_TELL.getValue(),
						constructionWork.getCwId(), constructionWork.getProjId());
		}*/
		
		List<Signature> signs=constructionWork.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.TECHNOLOGY_TELL.getValue());
			}
			constructionWork.setSign(signs);
		}
				
		
		signatureService.saveOrUpdateSign("menuId+menuNane+10",constructionWork.getSign(), constructionWork.getProjId(), constructionWork.getCwId(), constructionWork.getClass().getName(), flag);

		//签完字后调用调用鸿巨接口（会审交底信息新增/修改）返回信息
		if ("1".equals(constructionWork.getSignState())){
			ResultMsg resultMsg = synchronizedService.callSynchronizedConstructionWork(constructionWork.getProjId(), constructionWork.getCwId(), WebServiceTypeEnum.CONSTRUCTION_WORK.getValue());
			if (resultMsg!=null && resultMsg.getCode()!=0){
				throw new ExpressException(resultMsg.getCode()+"",resultMsg.getMsg());
			}
		}

		return constructionWork.getCwId();
	}
	
	@Override
	public ConstructionWork constructionWorkDetail(String projId,String dataType) {
		Project project = projectDao.get(projId);
		ConstructionWork conWork = new ConstructionWork();
		List<ConstructionWork> conWorks = constructionWorkDao.findByProjId(projId,dataType);
		if(conWorks!=null && conWorks.size()!=0){
			conWork = conWorks.get(0);
			return conWork;
		}
		conWork.setProjId(project.getProjId());
		conWork.setProjNo(project.getProjNo());
		conWork.setProjName(project.getProjName());
		conWork.setCustName(project.getCustName());
		conWork.setDuName(project.getDuName());
		conWork.setProjAddr(project.getProjAddr());
		//交底都需要定位
		//conWork.setLatitude(project.getProjLatitude());   //纬度
		//conWork.setLongitude(project.getProjLongitude()); //精度
		List<ConstructionPlan> constructionPlans = constructionPlanDao.findByProjNo(project.getProjNo());
		try {
			if(constructionPlans!=null && constructionPlans.size()!=0){
				ConstructionPlan constructionPlan = constructionPlans.get(0);
				conWork.setConstructionUnit(constructionPlan.getCuName());//施工单位
				conWork.setSuName(constructionPlan.getSuName());//监理单位
				conWork.setCuName(constructionPlan.getCuName());//分包单位
				//TODO 天然气公司
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return conWork;
	}

	@Override
	public List<ConstructionWork> findByProjId(String projId) {
		StringBuffer hql = new StringBuffer("from ConstructionWork where projId='").append(projId).append("'");
		List<ConstructionWork> result = super.findByHql(hql.toString());
		return result;
	}

	/**
	 * 分页查询会审交底记录
	 * @author liaoyq
	 */
	@Override
	public Map<String, Object> queryList(ConstructionWorkReq constructionWorkReq) {
		Map<String, Object> map = constructionWorkDao.queryList(constructionWorkReq);
		List<ConstructionWork> list = (List<ConstructionWork>)map.get("data");
		if(list !=null && list.size() >0){
			for (ConstructionWork constructionWork : list) {
				Project project = projectDao.get(constructionWork.getProjId()); //根据工程Id取得记录
				constructionWork.setProjectType(project.getProjectType());
				constructionWork.setCorpId(project.getCorpId());   
			}
		}
		return map;
	}

	/**
	 * 根据ID查询会审交底详述
	 * @author liaoyq
	 * @param id 
	 */
	@Override
	public ConstructionWork findDetailById(String id) {
		return constructionWorkDao.get(id);
	}

	/**
	 * 会审交底报表批量打印
	 * 组装打印路径及参数
	 */
	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询交底记录信息
		//多条交底信息
		List<ConstructionWork> cws = constructionWorkDao.findByProjId(projId, null);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//安装合同报表
		String arrayStr = CompletionDataPrintEnum.TECHNOLOGY_TELL.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && cws!=null && cws.size()>0 && project !=null){
			JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			for(ConstructionWork cw : cws){
				 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
				 Object reportVersion = Constants.getSysConfigByKey(key);
				   if(reportVersion !=null){
					   //记录特定字符索引  
					   int beginIndex = dto.getReportlet().indexOf("/"); 
					   int endIndex = dto.getReportlet().lastIndexOf("/");
				       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				   }
				result = "{reportlet:'"+dto.getReportlet()+"',projName:'"+cw.getProjName()+"',cwId:'"+cw.getCwId()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				list.add(result);
			}
		}
		return list;
	}
	/**
	 * 获取服务器时间
	 */
	@Override
	public Date getDataBaseDate() {
		return constructionWorkDao.getDatabaseDate();
	}
	
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			ConstructionWork constructionWork=constructionWorkDao.get(cwId);
			if(constructionWork!=null){
				if(StringUtils.isNotBlank(constructionWork.getDuDeputy())){
					//通知置为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.DESIGNER.getValue(), SignDataTypeEnum.TECHNOLOGY_TELL.getValue(),
							constructionWork.getCwId(), constructionWork.getProjId(),signState);
				}
				//施工员
				if(StringUtils.isNotBlank(constructionWork.getCuPm())){
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.TECHNOLOGY_TELL.getValue(),
							constructionWork.getCwId(), constructionWork.getProjId(),signState);
				}
				//监理
				if(StringUtils.isNotBlank(constructionWork.getSuFieldJgj())){
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.TECHNOLOGY_TELL.getValue(),
							constructionWork.getCwId(), constructionWork.getProjId(),signState);
				}
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean updateConstrctWorkById(String cwId) throws Exception {
		ConstructionWork constructionWork = constructionWorkDao.get(cwId);
		if (constructionWork == null ){
			return false;
		}
			constructionWork.setDelFlag(1);
			constructionWorkDao.update(constructionWork);
			this.signNoticeSetInvalid(cwId);//将签字通知置为无效；
			return true;
		
	}

/**
 * 根据业务单ID将签字通知置为已办
 * @param busId
 * @return
 * @throws Exception
 */
   @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean signNoticeSetInvalid(String busId) throws Exception{
	   signNoticeDao.deleteSignNoticeByBsId(busId);
		return true;


	}

	@Override
	public Integer countSignedByProjId(String projId, String signStatus) {
		return constructionWorkDao.countSignedByProjId(projId,signStatus);
	}

	@Override
	public String firstSignStatusByProjId(String projId) {
		List<ConstructionWork> list = constructionWorkDao.findByProjId(projId, null);
		if (list!=null && list.size()>0){
			return list.get(0).getSignState();
		}else {
			return null;
		}
	}






	/**
	* @Description: 会审交底
	* @author zhangnx
	* @date 2019/8/23 23:40
	*/
	@Override
	public boolean delBackupsConstructionWork(String projId,String rollBackReason) {
		List<ConstructionWork> cwList = constructionWorkDao.findByProjId(projId);
		if (cwList==null || cwList.size()<1) return true;

		Map<String,Object> criteriaMap=new HashMap<>();
		String t_projId = Annotations.getFieldGetMethodColumnAnNameVal(ConstructionWork.class, "projId");
		criteriaMap.put(t_projId,projId);
		String stepId=StepEnum.TECHNOLOGYTELL.getValue();

		String tableName = Annotations.getClassTableAnNameVal(ConstructionWork.class);
		for (ConstructionWork cw:cwList) {
			abandonedRecordService.delBackupsThisTableRecordAndSignature(tableName,criteriaMap,cw.getCwId(),rollBackReason,stepId);
		}


		return true;
	}

}
