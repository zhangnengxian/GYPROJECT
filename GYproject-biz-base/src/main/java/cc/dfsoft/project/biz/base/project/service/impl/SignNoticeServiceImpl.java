package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeStandardDao;
import cc.dfsoft.project.biz.base.project.dto.SignNoticeReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.PostDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.SignSortNoUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * 
 * @author sunyul
 *
 */

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SignNoticeServiceImpl implements SignNoticeService {
	
	/**签字标准Dao*/
	@Resource
	SignNoticeStandardDao signNoticeStandardDao;
	
	/**工程Dao*/
	@Resource
	ProjectDao projectDao;
	
	/**签字通知*/
	@Resource
	SignNoticeDao signNoticeDao;
	
	@Resource
	MenuDao menuDao;
	
	@Resource
	StaffDao staffDao;
	

	/**
	 * 下计划时批量生成签字
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	@Override
	public void insertSignNotice(String projId,String post,String name,String genarateType) {
		
		Project proj=projectDao.get(projId);
		
		SignNoticeReq req=new SignNoticeReq();
		req.setPost(post);
		req.setGenerateType(genarateType);
		
		//查询签字通知标准
		Map<String,Object>  map=signNoticeStandardDao.querySignNoticeStandard(req);
		List<SignNoticeStandard> standList=(List<SignNoticeStandard>) map.get("data"); 
		
		if(standList!=null && standList.size()>0){
			for(SignNoticeStandard sns:standList){
				//查询是否生成过
				SignNotice sn=signNoticeDao.queryByProjIdAndPost(projId, post, sns.getDataType());
				if(sn!=null){
					//已生成
					sn.setSigner(name);
					signNoticeDao.saveOrUpdate(sn);
				}else{
					sn=new SignNotice();
					sn.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
					sn.setPost(post);                          		//职务
					sn.setPostName(sns.getPostName());
					sn.setDataType(sns.getDataType());         		//资料类型
					sn.setDataTypeName(sns.getDataTypeName());
					sn.setSortNo(sns.getSortNo());
					sn.setProjId(projId);
					sn.setProjNo(proj.getProjNo());
					sn.setProjName(proj.getProjName());
					sn.setSigner(name);
					sn.setStatus(SignStateEnum.NO_SIGN.getValue());//未签字
					signNoticeDao.save(sn);
				}
			}
		}
		
		
		//如果下计划 则生成负责人签字
		SignNotice signLeaderNotice=signNoticeDao.queryByProjIdAndPost(projId, SignPostEnum.PROJECT_LEADER.getValue(),SignDataTypeEnum.STARTING_REPORT.getValue() );
		
		List<SignNoticeStandard> projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(SignPostEnum.PROJECT_LEADER.getValue(),SignDataTypeEnum.STARTING_REPORT.getValue(),proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());

		if(projectLeaderStandardList == null || projectLeaderStandardList.size()<1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(SignPostEnum.PROJECT_LEADER.getValue(),SignDataTypeEnum.STARTING_REPORT.getValue(),Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		}
		if(signLeaderNotice==null){
			if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
				SignNoticeStandard projectLeaderStandard=projectLeaderStandardList.get(0);
				signLeaderNotice=new SignNotice();
				signLeaderNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
				signLeaderNotice.setPost(projectLeaderStandard.getPost());//职务
				signLeaderNotice.setPostName(projectLeaderStandard.getPostName());
				signLeaderNotice.setDataType(SignDataTypeEnum.STARTING_REPORT.getValue()); //资料类型
				signLeaderNotice.setDataTypeName(SignDataTypeEnum.STARTING_REPORT.getMessage());
				signLeaderNotice.setProjId(projId);
				signLeaderNotice.setSortNo(projectLeaderStandard.getSortNo());
				signLeaderNotice.setProjNo(proj.getProjNo());
				signLeaderNotice.setProjName(proj.getProjName());
				signLeaderNotice.setSigner(name);
				signLeaderNotice.setStatus(SignStateEnum.NO_SIGN.getValue());//未签字
				signNoticeDao.save(signLeaderNotice);
			}
		}
	}
	
	/**
	 * 查询是否已生成
	 * @author fuliwei
	 * @createTime 2018年1月18日
	 * @param 
	 * @return
	 */
	@Override
	public SignNotice queryByProjIdAndPost(String projId, String post, String dataType) {
		return signNoticeDao.queryByProjIdAndPost(projId, post, dataType);
	}
	
	
	
	
	/**
	 * 调用方法 生成下一个签字通知
	 * @author fuliwei
	 * @createTime 2018年1月20日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNotice(String post, String dataType, String businessOrderId,String projId) {
		//查signNotice是否存在 
		SignNotice signNotice=signNoticeDao.queryByBusiIdAndPost(businessOrderId, post, dataType);
		
		Project proj=projectDao.get(projId);
		//签字标准
		List<SignNoticeStandard> projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(post,dataType,proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
		if(projectLeaderStandardList == null || projectLeaderStandardList.size()<1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(post,dataType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		}
		
		/*if(signNotice==null && projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			//不存在 生成 且置为无效
			signNotice=new SignNotice();
			signNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
			signNotice.setPost(projectLeaderStandardList.get(0).getPost());//职务
			signNotice.setPostName(projectLeaderStandardList.get(0).getPostName());
			signNotice.setDataType(projectLeaderStandardList.get(0).getDataType()); //资料类型
			signNotice.setDataTypeName(projectLeaderStandardList.get(0).getDataTypeName());
			signNotice.setProjId(projId);
			signNotice.setSortNo(projectLeaderStandardList.get(0).getSortNo());
			signNotice.setProjNo(proj.getProjNo());
			signNotice.setProjName(proj.getProjName());
			signNotice.setBusinessOrderId(businessOrderId);
			//signNotice.setSigner(name);
			signNotice.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//已签字
		}else */
		if(signNotice!=null && projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			//存在  
			signNotice.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//已签字
			//找到他的下一个或多个 参数 当前的签字顺序 业务单类型
			this.saveNextSignNotice(dataType, businessOrderId, projId, projectLeaderStandardList.get(0).getSortNo(),null);
			signNoticeDao.saveOrUpdate(signNotice);
		}else if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			this.saveNextSignNotice(dataType, businessOrderId, projId, projectLeaderStandardList.get(0).getSortNo(),null);
		}
	}
	
	/**
	 * 查找下一个签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveNextSignNotice(String dateType, String businessOrderId, String projId,String sortNo,List<Signature> signs) {
		//下一个签字顺序
		String nextSortNo=SignSortNoUtil.workFlowStatus(sortNo);
		Project proj=projectDao.get(projId);
		Object obj2 = Constants.getSysConfigByKey(proj.getCorpId()+"_"+proj.getProjectType()+"_"+proj.getContributionMode()+"_"+dateType);
		if(obj2==null){
			obj2=Constants.getSysConfigByKey("1101_11_1_"+dateType);//如开始顺序为3(监理)
		}
		
		
		String pushNo="";
		if("9".equals(proj.getContributionMode())){
			//如果是小规模
			pushNo=String.valueOf(Integer.valueOf(nextSortNo)+1);
		}else{
			pushNo=nextSortNo;
		}
		
		Object obj = Constants.getSysConfigByKey("1101_11_1_baoyan_audit");
		if(obj!=null){
			String [] type=obj.toString().split(",");
			if( Arrays.asList(type).contains(dateType) && obj2.toString().equals(pushNo)){
				//判断下一个是否是要推送审核的人 是的话返回，不生成通知，在推送的时候再生成
				return;
			}
		}
		
		
		SignNotice signNotice;
		
	    //先查标准,按下一顺序签字人、签字类型、公司ID、工程类型、出资方式查询
		List<SignNoticeStandard> projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(nextSortNo,dateType,proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
		 //先查标准,按下一顺序签字人、签字类型、公司ID、工程类型、默认出资方式查询
		if(projectLeaderStandardList.size() <1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(nextSortNo,dateType,proj.getCorpId(),proj.getProjectType(),Constants.CONTRIBUTION_MODE);
		}
		 //先查标准,按下一顺序签字人、签字类型、公司ID、默认工程类型、默认出资方式查询
		if(projectLeaderStandardList.size() <1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(nextSortNo,dateType,proj.getCorpId(),Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		}
		 //先查标准,按下一顺序签字人、签字类型、默认公司ID、默认工程类型、默认出资方式查询
		if(projectLeaderStandardList.size() <1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(nextSortNo,dateType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		}
		if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			for(SignNoticeStandard stand:projectLeaderStandardList){
				//去查notice 无损检测 现场代表给自己发就会有问题
				signNotice=signNoticeDao.queryByBusiIdAndPost(businessOrderId, stand.getPost(), dateType);
				
				if(signNotice!=null){
					//未完成签字的激活
					if(!signNotice.getStatus().equals(SignStateEnum.ALREADY_SIGN.getValue())){
						signNotice.setStatus(SignStateEnum.READY_SIGN.getValue());//准备签字
					}
					signNoticeDao.saveOrUpdate(signNotice);
				}else{
					if(isSignsPost(stand, signs)){
						signNotice=new SignNotice();
						signNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
						signNotice.setPost(stand.getPost());//职务
						signNotice.setPostName(stand.getPostName());
						signNotice.setDataType(stand.getDataType()); //资料类型
						signNotice.setDataTypeName(stand.getDataTypeName());
						signNotice.setProjId(projId);
						signNotice.setSortNo(stand.getSortNo());
						signNotice.setProjNo(proj.getProjNo());
						signNotice.setProjName(proj.getProjName());
						signNotice.setBusinessOrderId(businessOrderId);
						//signNotice.setSigner(name);
						signNotice.setStatus(SignStateEnum.READY_SIGN.getValue());//准备签字
						signNoticeDao.save(signNotice);
					}
				}
			}
		}
	}
	
	/**
	 * 将所有的签字通知置为已签字
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateAllSignState(String dataType, String businessOrderId) {
		/*List<SignNotice> list=signNoticeDao.queryByBusiIdAndDataType(businessOrderId, dataType);
		if(list!=null && list.size()>0){
			for(SignNotice sn:list){
				sn.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//已签字
				signNoticeDao.saveOrUpdate(sn);
			}
		}*/
		signNoticeDao.updateStatusByBusId(SignStateEnum.ALREADY_SIGN.getValue(), businessOrderId, null, dataType, null);
	}
	
	/**
	 * 通知置为无效
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateSignNotice(String post, String dataType, String businessOrderId, String projId) {
		/*SignNotice signNotice=signNoticeDao.queryByBusiIdAndPost(businessOrderId, post, dateType);
		if(signNotice!=null){
			signNotice.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//已签字
			signNoticeDao.saveOrUpdate(signNotice);
		}*/
		signNoticeDao.updateStatusByBusId(SignStateEnum.ALREADY_SIGN.getValue(), businessOrderId, post, dataType, projId);
	}
	
	/**
	 * 查询签字通知
	 * @author fuliwei
	 * @createTime 2018年1月15日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> querySignNotice(PageSortReq pageSortReq) {
		List<SignNotice> noticeList=signNoticeDao.querySignNotice();
		Map<String, Object> mapResult=new HashMap<String,Object>();
		if(noticeList!=null && noticeList.size()>0){
			for(SignNotice nr:noticeList){
				if(StringUtils.isNotBlank(nr.getDataTypeName()) && StringUtils.isNotBlank(nr.getGrade())){
					//为报验审核通知
					Menu menu=menuDao.get("1301201");
					if(menu!=null ){
						nr.setMenuId(menu.getMenuId());
						nr.setUrl(menu.getUrl());
					}
				}else if(StringUtils.isNotBlank(nr.getDataTypeName())){
					//查菜单
					Menu menu=menuDao.findByName(nr.getDataTypeName());
					if(menu!=null ){
						nr.setMenuId(menu.getMenuId());
						nr.setUrl(menu.getUrl());
					}
				}
			}
			
			mapResult.put("recordsTotal", noticeList.size());
			mapResult.put("recordsFiltered", noticeList.size());
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", noticeList);
		}else{
			mapResult.put("recordsTotal", 0);
			mapResult.put("recordsFiltered", 0);
			mapResult.put("draw", pageSortReq.getDraw());
			mapResult.put("data", noticeList);
		}
		return mapResult;
	}
	
	/**
	 * 本次的置为无效
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveThisSignNotice(String post, String dataType, String businessOrderId, String projId,String signStat) {
		/*SignNotice signNotice=signNoticeDao.queryByBusiIdAndPost(businessOrderId, post, dataType);
		if(signNotice!=null){
			//存在  
			signNotice.setStatus(signStat);//已签字
			//
			signNoticeDao.update(signNotice);
		}*/
		signNoticeDao.updateStatusByBusId(signStat,businessOrderId,post,dataType,projId);
	}
	
	/**
	 * 新方法
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveSignNoticeNew(String dataType, String businessOrderId, String projId, String post,List<Signature> signs ) {
		//判断是否是报验 报验的话不生成签字通知 (同时需满足当前签字的顺序+1=要发通知的签字顺序)
		//如管沟开挖 如施工员签字顺序是1，t_sys_config配置的签字开始顺序是3，则不走这段代码，走原来的签字通知，将签字通知发给质检员和项目经理
		//质检员、项目经理签字顺序是2 则走里面的代码，不发送签字通知
		//Object obj = Constants.getSysConfigByKey("1101_11_1_baoyan_audit");
		Project proj=projectDao.get(projId);
		
		//post  工程id 业务单id 查询处理的数据与标准比
		String thisSignSortNo="0";
		List<SignNoticeStandard> projectLeaderStandardList = null;
		//按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
	    projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(post,dataType,proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
		if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			thisSignSortNo=projectLeaderStandardList.get(0).getSortNo();
		}
		//按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
		if( projectLeaderStandardList.size() < 1){
			 projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(post,dataType,proj.getCorpId(),proj.getProjectType(),Constants.CONTRIBUTION_MODE);
			 if(projectLeaderStandardList != null && projectLeaderStandardList.size() > 0){
				 thisSignSortNo=projectLeaderStandardList.get(0).getSortNo();
			 }
		}
		//按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
		if( projectLeaderStandardList.size() < 1){
			 projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(post,dataType,proj.getCorpId(),Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		     if(projectLeaderStandardList != null && projectLeaderStandardList.size() > 0){
				  thisSignSortNo=projectLeaderStandardList.get(0).getSortNo();
				   }
				}
		//查找默认签字通知表
		if(projectLeaderStandardList.size() < 1){
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(post,dataType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
			if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
				thisSignSortNo=projectLeaderStandardList.get(0).getSortNo();
			}
		}
		
		
		//ProjectChecklist projectCheckList=projectChecklistDao.get(businessOrderId);
		//配置的从报验审核从哪个顺序开始发通知
		/*Object obj2 = Constants.getSysConfigByKey(proj.getCorpId()+"_"+proj.getProjectType()+"_"+proj.getContributionMode()+"_"+dataType);
		if(obj2==null){
			obj2=Constants.getSysConfigByKey("1101_11_1_"+dataType);//如开始顺序为3(监理)
		}
		int intThisSignSortNo=Integer.valueOf(thisSignSortNo);*/
		/*if(obj!=null && projectCheckList!=null){
			String [] type=obj.toString().split(",");
			//if( Arrays.asList(type).contains(dataType) && intThisSignSortNo+1==Integer.valueOf(obj2.toString()) ){
			if( Arrays.asList(type).contains(dataType) && "0".equals(projectCheckList.getFlag())){
				//包含报验单 且报验单状态是待推送的，则不走原来的签字通知方法，在点了推送之后才发签字通知
				return;
			}
		}*/
		
		
		
		//签字顺序 、 和dataType
		if(projectLeaderStandardList!=null && projectLeaderStandardList.size()>0){
			SignNoticeStandard stand=projectLeaderStandardList.get(0);
			String sortNo=stand.getSortNo();
			
			//查标准表的数据,当前签字类型的签字顺序标准
			SignNoticeReq req=new SignNoticeReq();
			req.setSortNo(sortNo);//1
			req.setDataType(dataType);
			req.setSignPost(this.getSignPosts(signs,post));
			req.setCorpId(projectLeaderStandardList.get(0).getCorpId());
			req.setContributionMode(projectLeaderStandardList.get(0).getContributionMode());
			req.setProjType(projectLeaderStandardList.get(0).getProjType());
			Map<String,Object> map=signNoticeStandardDao.querySignNoticeStandard(req);
			List<SignNoticeStandard> standSortList=(List<SignNoticeStandard>) map.get("data");
			
			//查notice表的数据
			List<SignNotice> noticeList=signNoticeDao.queryByBusiIdAndSort(businessOrderId, sortNo);
			int size;
			if(noticeList!=null && noticeList.size()>0){
				size=noticeList.size();
			}else{
				size=0;
			}
			
			if(standSortList!=null && standSortList.size()>0){
				if(size+1==standSortList.size()){
					//说明当前顺序的人已完成签字 
					//激活下一顺序的人
					this.saveNextSignNotice(dataType, businessOrderId, projId, sortNo,signs);
				}
				//当前顺序下的签字通知
				updateThisSignNotice(standSortList,businessOrderId,dataType,proj,sortNo, post);
			}
		}
	}
	/**
	 * 当前顺序签字标准下的签字通知
	 * @author liaoyq
	 * @createTime 2018年4月12日
	 * @param standSortList
	 * @param signs 
	 * @param businessOrderId
	 * @param dataType
	 */
	private void updateThisSignNotice(List<SignNoticeStandard> standSortList,String businessOrderId, String dataType,Project proj,String sortNo,String curPost) {
		LoginInfo loginInfo= SessionUtil.getLoginInfo();  //登录信息
		for(SignNoticeStandard stand: standSortList){
			//当前职务为已签字
			String status = SignStateEnum.READY_SIGN.getValue();
			if(stand.getPost().equals(curPost)){
				status = SignStateEnum.ALREADY_SIGN.getValue();
			}
			//如果本次的没有生成过 生成成本次的
			SignNotice sn=signNoticeDao.queryByBusiIdAndPost(businessOrderId, stand.getPost(), dataType);
			if(sn!=null){
				//已生成
				if(sn.getPost().equals(curPost)){
					sn.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//当前职务已签字
					sn.setSigner(loginInfo.getStaffName());  //签字人
				}
				
				signNoticeDao.saveOrUpdate(sn);
			}else{
				sn=new SignNotice();
				sn.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
				sn.setPost(stand.getPost()); 
				sn.setPostName(stand.getPostName());
				sn.setDataType(StringUtil.isNotBlank(dataType)?dataType:"");         		//资料类型
				sn.setDataTypeName(StringUtil.isNotBlank(dataType)?SignDataTypeEnum.valueof(dataType).getMessage():"");
				sn.setSortNo(sortNo);
				sn.setProjId(proj.getProjId());
				sn.setProjNo(proj.getProjNo());
				sn.setBusinessOrderId(businessOrderId);
				sn.setProjName(proj.getProjName());
				sn.setStatus(status);
				sn.setSigner(loginInfo.getStaffName());  //签字人
				signNoticeDao.save(sn);
			}
		}
	}

	/**
	 * 页面传递的签字posts
	 * @author liaoyq
	 * @createTime 2018年4月12日
	 * @param signs
	 * @param curPost
	 * @return
	 */
	private List<String> getSignPosts(List<Signature> signs, String curPost) {
		List<String> posts = new ArrayList<String>();
		if(signs!=null && signs.size()>0){
			for(Signature sign : signs){
				if(StringUtil.isNotBlank(sign.getPostType())){
					posts.add(sign.getPostType());
				}
			}
		}
		return posts;
	}

	/**
	 * 判断页面传递的签字职务中是否有当前签字标准的职务
	 * 有则，生成签字通知，没有则不生成签字通知
	 * @author liaoyq
	 * @createTime 2018年4月12日
	 * @param stand
	 * @param signs
	 * @return
	 */
	private boolean isSignsPost(SignNoticeStandard stand, List<Signature> signs) {
		if(signs==null || signs.size()<1){
			return true;
		}
		boolean flag = false;
		for(Signature sign : signs){
			//判断签字职务是否在标准里，存在返回true，不存在，返回false
			if(StringUtil.isNotBlank(sign.getPostType())){
				if(sign.getPostType().equals(stand.getPost())){
					flag = true;
					break;
				}else{
					flag = false;
				}
			}
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByProjIdAndType(String projId, String dataType) {
		signNoticeDao.deleteByProjIdAndType(projId,dataType);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByBsId(String BsId,String pcDesId) {
		// TODO Auto-generated method stub
			signNoticeDao.deleteByBsId (BsId,pcDesId);
	}
	/**
	 * 根据当前业务单id和当前签字顺序，把当前的通知置为无效，且生成下一个通知
	 * @param businessOrderId
	 * @param soryNo
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void createNextInspectionAuditNotice(String businessOrderId,String grade,String projId) {
		//将当前的签字顺序，置为无效
		signNoticeDao.updateInspectionThisAudit(businessOrderId,grade);
		Project proj=projectDao.get(projId);
		List<SignNotice> list=signNoticeDao.queryByBusiIdAndGrade(businessOrderId,grade);
		String nextSortNo="";
		String dataType="";
		if(list!=null && list.size()>0){
			SignNotice nr=list.get(0);
			nextSortNo=String.valueOf(Integer.valueOf(nr.getSortNo())+1);
			dataType=nr.getDataType();
		}
		
		//激活下一个通知
		String nextGrade=String.valueOf(Integer.valueOf(grade)+1);
		
		//先查之前是否生成过签字通知
		List<SignNotice> snList=signNoticeDao.queryByBusiIdAndSort(businessOrderId,nextSortNo);
		SignNotice sn=new SignNotice();
		if(snList!=null && snList.size()>0){
			//已生成，将签字通知置为有效，即待签字
			sn=snList.get(0);
			sn.setStatus(SignStateEnum.READY_SIGN.getValue());  //待签字
			sn.setGrade(nextGrade);								//如2级审核则存入2
			signNoticeDao.saveOrUpdate(sn);
		}else{
			//查询签字标准 生成报验审核第一级通知,查询配置
			List<SignNoticeStandard> signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType(nextSortNo,dataType,proj.getCorpId(),proj.getProjectType(),proj.getContributionMode());
			if(signNoticeStandardList.size() < 1){
				//查询默认签字通知
				signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType(nextSortNo,dataType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
			}
			if(signNoticeStandardList!=null && signNoticeStandardList.size()>0){
				SignNoticeStandard stand=signNoticeStandardList.get(0);
				sn.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
				sn.setPost(stand.getPost());                          						//职务
				sn.setPostName(SignPostEnum.valueof(stand.getPost()).getMessage());
				sn.setDataType(StringUtil.isNotBlank(dataType)?dataType:"");         		//资料类型
				sn.setDataTypeName(StringUtil.isNotBlank(dataType)?SignDataTypeEnum.valueof(dataType).getMessage():"");
				sn.setSortNo(nextSortNo);
				sn.setProjId(proj.getProjId());
				sn.setProjNo(proj.getProjNo());
				sn.setBusinessOrderId(businessOrderId);
				sn.setProjName(proj.getProjName());
				sn.setStatus(SignStateEnum.READY_SIGN.getValue());							//待签字
				sn.setGrade(nextGrade);
				signNoticeDao.save(sn);
			}
		}
		
		
	}

	/**
	 * 生成该业务单下单据的第一签字顺序通知
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void createFirstNotice(String busOrderId,
			SignDataTypeEnum jointAcceptance, Project project) {
		List<SignNotice> firstNotice =  signNoticeDao.queryFirstByBusId(busOrderId, jointAcceptance.getValue());
		if(firstNotice!=null && firstNotice.size()>0){
			for(SignNotice signNotice : firstNotice){
				signNotice.setStatus(SignStateEnum.READY_SIGN.getValue());
				signNoticeDao.saveOrUpdate(signNotice);
			}
		}else{
			firstNotice = new ArrayList<SignNotice>();
			//生成第一签字顺序的签字通知
			//获取签字标准
			List<SignNoticeStandard> noticeStandards = signNoticeStandardDao.queryBySortNoAndDateType("1", jointAcceptance.getValue(), project.getCorpId(), project.getProjectType(), project.getContributionMode());
			if(noticeStandards == null || noticeStandards.size()<1){
				noticeStandards = signNoticeStandardDao.queryBySortNoAndDateType("1", jointAcceptance.getValue(), project.getCorpId(),project.getProjectType(), Constants.CONTRIBUTION_MODE);
			}
			if(noticeStandards == null || noticeStandards.size()<1){
				noticeStandards = signNoticeStandardDao.queryBySortNoAndDateType("1", jointAcceptance.getValue(), project.getCorpId(), Constants.PROJECT_TYPE, Constants.CONTRIBUTION_MODE);
			}
			if(noticeStandards == null || noticeStandards.size()<1){
				//默认的
				noticeStandards = signNoticeStandardDao.queryBySortNoAndDateType("1", jointAcceptance.getValue(), Constants.CORP_ID, Constants.PROJECT_TYPE, Constants.CONTRIBUTION_MODE);
			}
			if(noticeStandards!=null && noticeStandards.size()>0){
				for(SignNoticeStandard noticeStandard : noticeStandards){
					SignNotice signNotice = new SignNotice();
					//复制相同属性的值
					BeanUtil.copyNotNullProperties(signNotice, noticeStandard);
					signNotice.setSignNoticeId(IDUtil.getUniqueId(Constants.MODULE_CODE_NOTICE));
					signNotice.setProjId(project.getProjId());
					signNotice.setProjName(project.getProjName());
					signNotice.setProjNo(project.getProjNo());
					signNotice.setStatus(SignStateEnum.READY_SIGN.getValue());
					signNotice.setBusinessOrderId(busOrderId);
					firstNotice.add(signNotice);
				}
				//批量插入数据
				signNoticeDao.batchInsertOrUpdateObjects(firstNotice);
			}
		}
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public boolean signNoticeSetInvalid(String businessId, String projId, String dataType, String post, String status) {
		/*List<SignNotice> signNoticeList=signNoticeDao.querysignNoticeList(businessId,projId,dataType,post,status);
		if (signNoticeList==null || signNoticeList.size()<1) return false;

		for (SignNotice s:signNoticeList) {
			s.setStatus(SignStateEnum.ALREADY_SIGN.getValue());//已签
			signNoticeDao.saveOrUpdate(s);
		}*/
		signNoticeDao.updateAlreadySignByprojId(businessId,projId,dataType,post,status);
		return true;
	}

	@Override
	public void updateFinishSignaturNotice(String businessOrderId, List<Signature> signs) {

		List<String> finishPost=new ArrayList<>();
		for (Signature s:signs) {
			if (StringUtils.isNotBlank(s.getSignClobStr())){
				finishPost.add(s.getPostType());
			}
		}
		signNoticeDao.updateFinishSignaturNotice(businessOrderId,signs.get(0).getDataType(),finishPost);
	}


	/**
	 * 查询联合验收相关人员签字通知
	 * @author fuliwei
	 * @date 2019/8/29
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryJointNotice(String post) {

		List<Map<String, Object>> returnMap = new ArrayList<>();

		List<Map<String, Object>> menuMap = new ArrayList<>();
		Map mapRole = new HashMap();
		List<Map<String, Object>> list = signNoticeDao.queryJointNotice(post);
		for(Map<String, Object> map:list){
			String staffId = String.valueOf(map.get("STAFF_ID"));
			menuMap = staffDao.getStaffMenuIdList(staffId);
			String menuId = String.valueOf(map.get("MENU_ID"));
			mapRole.put("MENU_ID",menuId);
			if(menuMap!=null && menuMap.size()>0 && menuMap.contains(mapRole)){
				//有权限
				returnMap.add(map);
			}else{
				continue;
			}
		}

		return returnMap;
	}


	/**
	 * 查询总工程师签字
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	@Override
	public List<Map<String, Object>> queryCeneralEngineerNotice() {
		List<Map<String, Object>> returnMap = new ArrayList<>();

		List<Map<String, Object>> menuMap = new ArrayList<>();
		Map mapRole = new HashMap();
		List<Map<String, Object>> list = signNoticeDao.queryCeneralEngineerNotice();
		for(Map<String, Object> map:list){
			String staffId = String.valueOf(map.get("STAFF_ID"));
			menuMap = staffDao.getStaffMenuIdList(staffId);
			String menuId = String.valueOf(map.get("MENU_ID"));
			mapRole.put("MENU_ID",menuId);
			if(menuMap!=null && menuMap.size()>0 && menuMap.contains(mapRole)){
				//有权限
				returnMap.add(map);
			}else{
				continue;
			}
		}


		return returnMap;
	}
	/**
	 * 施工单位总工
	 * @author fuliwei
	 * @date 2019/9/2
	 */
	@Override
	public List<Map<String, Object>> queryCuCeneralEngineerNotice() {
		List<Map<String, Object>> returnMap = new ArrayList<>();

		List<Map<String, Object>> menuMap = new ArrayList<>();
		Map mapRole = new HashMap();
		List<Map<String, Object>> list = signNoticeDao.queryCuCeneralEngineerNotice();
		for(Map<String, Object> map:list){
			String staffId = String.valueOf(map.get("STAFF_ID"));
			menuMap = staffDao.getStaffMenuIdList(staffId);
			String menuId = String.valueOf(map.get("MENU_ID"));
			mapRole.put("MENU_ID",menuId);
			if(menuMap!=null && menuMap.size()>0 && menuMap.contains(mapRole)){
				//有权限
				returnMap.add(map);
			}else{
				continue;
			}
		}


		return returnMap;
	}

	/**
	 * 查询分公司部长
	 * @author fuliwei
	 * @date 2019/9/1
	 */
	@Override
	public List<Map<String, Object>> queryMinisterNotice() {
		List<Map<String, Object>> returnMap = new ArrayList<>();

		List<Map<String, Object>> menuMap = new ArrayList<>();

		Map mapRole = new HashMap();

		//List<Menu> menus = new ArrayList<Menu>();
		List<Map<String, Object>> list = signNoticeDao.queryMinisterNotice();
		for(Map<String, Object> map:list){
			String staffId = String.valueOf(map.get("STAFF_ID"));
			menuMap = staffDao.getStaffMenuIdList(staffId);
			//menus = (List<Menu>) menuMap.get("menus");//所有菜单id
			String menuId = String.valueOf(map.get("MENU_ID"));
			mapRole.put("MENU_ID",menuId);
			if(menuMap!=null && menuMap.size()>0 && menuMap.contains(mapRole)){
				//有权限
				returnMap.add(map);
			}else{
				continue;
			}
		}


		return returnMap;
	}
}
