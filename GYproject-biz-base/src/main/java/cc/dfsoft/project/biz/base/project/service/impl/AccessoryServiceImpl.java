package cc.dfsoft.project.biz.base.project.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.constructmanage.dao.BusinessCommunicationDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.enums.StepOutWorkflowEnum;
import cc.dfsoft.project.biz.base.plan.dao.ConstructionPlanDao;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.project.controller.ApplyDelayController;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.dao.AccessoryItemDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.CompletionFileEnum;
import cc.dfsoft.project.biz.base.project.enums.OperateWorkFlowEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.CoordinatesUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.SessionUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;
import cc.dfsoft.uexpress.common.util.WaterMarkUtil;

/**
 * 附件接口实现类
 * @author 
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class AccessoryServiceImpl implements AccessoryService {
	
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(ApplyDelayController.class);
	
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryItemDao accessoryItemDao;
	
	@Resource
	StaffDao staffDao;
	
	/**施工计划*/
	@Resource
	ConstructionPlanDao constructionPlanDao;
	
	@Autowired
	OperateRecordService operateRecordService;
	
	@Autowired
	OperateRecordDao operateRecordDao;
	
	/**菜单*/
	@Resource
	MenuDao menuDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	BusinessCommunicationDao businessCommunicationDao;
	/**
	 * 
	 * 上传附件并存入附件列表
	 * @param request
	 * @param acc
	 * @param files
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void uploadAccessory(HttpServletRequest request, AccessoryList acc, MultipartFile[] files) throws Exception {
		//上传文件
		String path=FileUtil.uploadFile(request, acc.getAlPath(), files);		
		String fileName = files[0].getOriginalFilename();
		//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
		String filePath= Constants.FIRST_DISK_PATH + path ;
		acc.setAlPath(filePath);
		//文件名称没有后缀
		String name=fileName.substring(0,fileName.lastIndexOf("."));	
		String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());	
		acc.setAlName(name);
		acc.setAlTypeId(fileType);
		if(StringUtil.isBlank(acc.getSourceType())){
			acc.setSourceType(AccessorySourceEnum.COLLECT_FILE.getValue());
		}
		if(StringUtil.isBlank(acc.getEncryption())){
			acc.setEncryption(AccessorySourceEnum.COLLECT_FILE.getValue());//默认0-不加密文件
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		acc.setAlOperateCsrId(loginInfo.getStaffId());
		acc.setAlOperateCsr(loginInfo.getStaffName());
		acc.setAlOperateTime(accessoryDao.getDatabaseDate());
		acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
		accessoryDao.save(acc);
		
		//某个业务下的操作记录
		if(StringUtil.isNotBlank(acc.getBusRecordId()) && StringUtil.isNotBlank(acc.getStep())){
			OperateRecordQueryReq operateRecordQueryReq = new OperateRecordQueryReq();
			operateRecordQueryReq.setProjId(acc.getProjId());
			operateRecordQueryReq.setIsValid("1");
			operateRecordQueryReq.setBusinessOrderId(acc.getBusRecordId());
			operateRecordQueryReq.setModifyStatus(OperateWorkFlowEnum.WAIT_DONE.getValue());
			operateRecordQueryReq.setStepId(acc.getStep());//菜单ID
			operateRecordQueryReq.setProjectType(acc.getProjLtypeId());
			operateRecordQueryReq.setOpType("2");//todo:这个值已被用，通气流程
			List<OperateRecord> listOperateRecord = operateRecordDao.queryOperateRecords(operateRecordQueryReq);
			// 查出不为空则更新记录为待办
			if (listOperateRecord != null && listOperateRecord.size() > 0) { 
				listOperateRecord.get(0).setModifyStatus("1");
				listOperateRecord.get(0).setOperateTime(operateRecordDao.getDatabaseDate());
				listOperateRecord.get(0).setOperateDept1(SessionUtil.getLoginInfo().getDeptId());
				listOperateRecord.get(0).setOperateCsr1(","+SessionUtil.getLoginInfo().getStaffId()+",");
				listOperateRecord.get(0).setOperater(SessionUtil.getLoginInfo().getStaffName());
				listOperateRecord.get(0).setBusinessOrderId(acc.getBusRecordId());
				operateRecordDao.update(listOperateRecord.get(0)); //更新操作记录
			}
		}
	}

	/**
	 * 
	 * 上传附件并存入附件列表----(业务记录Id不能为空)
	 * @param request
	 * @param acc
	 * @param files
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String uploadAccessoryBusRecordId(HttpServletRequest request, AccessoryList acc, MultipartFile[] files)
			throws Exception {
		//上传文件
		if(StringUtils.isNotBlank(acc.getBusRecordId())){
			String path=FileUtil.uploadFile(request, acc.getAlPath(), files);		
			String fileName = files[0].getOriginalFilename();
			//String filePath= Constants.FIRST_DISK_PATH + path + fileName;
			String filePath= Constants.FIRST_DISK_PATH + path ;
			acc.setAlPath(filePath);
			//文件名称没有后缀
			String name=fileName.substring(0,fileName.lastIndexOf("."));	
			String fileType=fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());	
			acc.setAlName(name);
			acc.setAlTypeId(fileType);
			acc.setSourceType(AccessorySourceEnum.COLLECT_FILE.getValue());
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			acc.setAlOperateCsrId(loginInfo.getStaffId());
			acc.setAlOperateCsr(loginInfo.getStaffName());
			acc.setAlOperateTime(accessoryDao.getDatabaseDate());
			acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
			accessoryDao.save(acc);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryAccessoryList(AccessoryQueryReq accessoryQueryReq) {
		/*//查权限
		List list=staffDao.findByRoleId("1037");
		LoginInfo login=SessionUtil.getLoginInfo();
		
		boolean flag=false;
		if(list!=null && list.size()>0){
			int size=list.size();
			for(int i=0;i<list.size();i++){
				//登陆人有查看的权限
				Object[]obj=(Object[]) list.get(i);
				if(login.getStaffId().equals(obj[0])){
					flag=true;
					break;
				}
			}
		}
		//登陆人有查看的权限
		if(flag){
			return accessoryDao.queryAccessoryList(accessoryQueryReq);
		}else{
			//只能看不加密的
			accessoryQueryReq.setEncryption("0");
			return accessoryDao.queryAccessoryList(accessoryQueryReq);
		}*/
		return accessoryDao.queryAccessoryList(accessoryQueryReq);
	}
	/**
	 * 查询附件列表根据id
	 * @param id
	 * @return
	 */
	@Override
	public  AccessoryList queryAccessoryList(String id) {
		return accessoryDao.get(id);
	}
	@Override
	public  List<AccessoryList> queryAccessoryByBus(String id,String sourceType) {
		return accessoryDao.queryAccessoryByBus(id,sourceType);
	}
	
	/**
	 * 附件删除功能（删除列表记录和相应文件）
	 * @param id 附件列表id
	 * @param path 附件路径
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void delAccessoryList(String id,String path){
		//删除记录
		if(StringUtil.isNoneBlank(id)){
			accessoryDao.delete(id);			
		}
		//删除文件
		if(StringUtil.isNoneBlank(path)){
		FileUtil.deleteFile(Constants.DISK_PATH+path);
	 }
	}

	/**
	 * 查询资料标准项
	 * @return
	 */
	@Override
	public List<CollectAccessoryItem> queryAccessoryItem(AccessoryQueryReq accessoryQueryReq) {
		List<CollectAccessoryItem> list = accessoryItemDao.queryAll(accessoryQueryReq);
		if(list == null || list.size()==0){
			//如果未配置 默认取贵阳的
			accessoryQueryReq.setCorpId(Constants.START_REPORT_CPT_CORP_MODE);
			return accessoryItemDao.queryAll(accessoryQueryReq);
		}else{
			return  list;
		}
	}
	
	/**
	 * 资料标准列表条件查询
	 * @param 
	 * @author 
	 * @createTime 2016-07-14
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryAccessoryItemList(AccessoryQueryReq accessoryQueryReq) {
		return accessoryItemDao.queryAccessoryItemList(accessoryQueryReq);
	}
	
	/**
	 * 更新保存资料标准
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveOrUpdateCollectAccessoryItem(CollectAccessoryItem collectAccessoryItem) {
		if(StringUtil.isNoneBlank(collectAccessoryItem.getCaiId())){
			accessoryItemDao.update(collectAccessoryItem);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			if((accessoryItemDao.queryByAccessoryName(collectAccessoryItem.getAccessoryName())) == null){
				collectAccessoryItem.setCaiId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
				accessoryItemDao.save(collectAccessoryItem);
				return Constants.OPERATE_RESULT_SUCCESS;
			}else{
				if((accessoryItemDao.queryByAccessoryName(collectAccessoryItem.getAccessoryName()).getProjType().getProjTypeId().equals(collectAccessoryItem.getProjType().getProjTypeId()))){
					return "exist";
				}
				else{
					collectAccessoryItem.setCaiId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
					accessoryItemDao.save(collectAccessoryItem);
					return Constants.OPERATE_RESULT_SUCCESS;
				}
				
			}
		}
	}
	
	/**
	 * 删除资料标准
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteCollectAccessoryItem(String id) {
		accessoryItemDao.delete(id);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String uploadPhoto(HttpServletRequest request, AccessoryList req) throws Exception{
		if(ServletFileUpload.isMultipartContent(request)){
			ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);
            String projNo="";
            String projId="";
            String busRecordId="";
            String stepId="";
            String projLatitude="";
            String projLongitude="";
            req =new AccessoryList();
            while(iter.hasNext()){
	            FileItemStream item = iter.next();
	            InputStream is = item.openStream();
	           
	            
	            if(item.isFormField()) {
	            	String name=item.getFieldName();
	            	if(name.equals("projNo")){
	            		projNo=Streams.asString(is,"utf-8");
	            		req.setProjNo(projNo);
	            		//工程编号包含新的去掉
	            		if(StringUtil.isNotBlank(projNo) && projNo.contains(Constants.ZUNYI_XINPU_PROJNO_PREX)){
	            			projNo = projNo.replace(Constants.ZUNYI_XINPU_PROJNO_PREX,"");
	            		}
	            		continue;
	            	}
	            	if(name.equals("projId")){
	            		projId=Streams.asString(is,"utf-8");
	            		req.setProjId(projId);
	            		continue;
	            	}
	            	if(name.equals("busRecordId")){
	            		busRecordId=Streams.asString(is,"utf-8");
	            		req.setBusRecordId(busRecordId);
	            		continue;
	            	}
	            	if(name.equals("projLatitude")){
	            		projLatitude=Streams.asString(is,"utf-8");
	            		continue;
	            	}
	            	if(name.equals("projLongitude")){
	            		projLongitude=Streams.asString(is,"utf-8");
	            		continue;
	            	}
	            	if(name.equals("stepId")){
	            		stepId=Streams.asString(is,"utf-8");
	            		req.setStep(stepId);
	            		//用stepId去查menu
	            		Menu menu=menuDao.get(stepId);
	            		if(menu!=null){
	            			req.setStepId(menu.getMenuName());
	            		}
	            		continue;
	            	}
	            }else{
	            	String fileName = item.getName();
            		String fileType=fileName.substring(fileName.lastIndexOf("."),fileName.length());	
            		String randomName=IDUtil.getUniqueId(Constants.MOBILE_CODE);	 
            		//String pathTemp="mobile/"+projNo+"/"+stepId+"/";  
            		String pathTemp=Constants.MOBILE_DISK_PATH+projNo+"/"+stepId+"/"; 
            		
            		
            		String pathTemp1="";
            		if(StringUtil.isNoneBlank(busRecordId)){
            			pathTemp1=pathTemp+busRecordId;
            			pathTemp=pathTemp+busRecordId+"/"+randomName+fileType;
            			
            		}else{
            			pathTemp1=pathTemp;
            			pathTemp=pathTemp+randomName+fileType;
            			
            		}
            		File uploadedFile1=new File(Constants.DISK_PATH+pathTemp1);
            		if(!uploadedFile1.exists()){
            			uploadedFile1.mkdirs();
    				}
            		File uploadedFile=new File(Constants.DISK_PATH+pathTemp);
            		LoginInfo loginInfo = SessionUtil.getLoginInfo(); 
            		logger.info("上传照片：" + Constants.DISK_PATH+pathTemp);
                    OutputStream os = new FileOutputStream(uploadedFile);
                    Streams.copy(is, os, true);
                    //转换定位坐标
                    if(StringUtils.isNotBlank(projLongitude)){
        				Map<String, Object> map = CoordinatesUtil.coordinatesConversion(projLongitude, projLatitude);
        				if(map!=null&&!map.isEmpty()){
        					projLongitude = map.get("x").toString();
        					projLatitude= map.get("y").toString();
        				}
        			}
                    //照片添加水印
                    //String textStr = (StringUtil.isNotBlank(projLatitude)?"纬度："+projLatitude.substring(0, projLatitude.indexOf(".")+10):"") + (StringUtil.isNotBlank(projLongitude)?" 经度："+projLongitude.substring(0,projLongitude.indexOf(".")+10):"" +" 时间:"+WaterMarkUtil.getCurrentTime());
                    String textStr = "时间:"+WaterMarkUtil.getCurrentTime()+",纬度："+projLatitude + ",经度："+projLongitude+",操作人："+loginInfo.getStaffName()+",电话："+loginInfo.getMobile();
                    WaterMarkUtil.markImageByText(textStr, Constants.DISK_PATH+pathTemp, Constants.DISK_PATH+pathTemp);
                    req.setAlPath(pathTemp);
                    req.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
                    req.setSourceType(AccessorySourceEnum.PHOTO_FILE.getValue());
                    req.setAlOperateTime(accessoryDao.getDatabaseDate());
                    req.setAlOperateCsrId(loginInfo.getStaffId());
                    req.setAlOperateCsr(loginInfo.getStaffName());
                    req.setAlTypeId("jpg");
                    req.setAlName("上传照片："+randomName);
                    req.setEncryption("0");
                    accessoryDao.save(req);
            	}
        	}
		}
		return null;   
	}
	
	public Map<String, Object> queryAccListPhoto(AccessoryQueryReq accessoryQueryReq) {
		return accessoryDao.queryAccListPhoto(accessoryQueryReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delAccPhoto(String alPath) {
		//删除记录
		accessoryDao.delAccPhoto(alPath);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void uploadPicture(AccessoryList acc) throws Exception {
		String partPath="";
		String prefixPath=Constants.DISK_PATH+Constants.PICTURE_PATH+acc.getProjNo()+"/";
		if(StringUtil.isNoneBlank(acc.getPictureStr())){
			//修改原来的参数
			accessoryDao.delAccPictureByStep(acc.getProjId(), acc.getStep(), acc.getSourceType());
			
			String imgStr="";
			String fileType="";			
			String str[]=acc.getPictureStr().split(",");
			if(str.length>0){
				imgStr=str[1];
				String temp[]=str[0].split(";");
				String temp1[]=temp[0].split("/");
				fileType=temp1[1];
			}
			String tempName=IDUtil.getUniqueId("1");
		    partPath=tempName+"."+fileType;
			String imgFilePath=prefixPath+partPath;
			FileUtil.createDir(prefixPath);
			FileUtil.GenerateImage(imgStr, imgFilePath);
			if(fileType.equals("png")){
				String tempPath=tempName+".jpg";
				partPath=Constants.PICTURE_PATH+acc.getProjNo()+"/"+tempPath;
				String newPath=prefixPath+tempPath;
				
				FileUtil.pngToJpg(imgFilePath, newPath);
			}
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			acc.setAlId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
			acc.setAlPath(partPath);
			acc.setAlTypeId("jpg");			
			acc.setAlOperateCsr(loginInfo.getStaffName());
			acc.setAlOperateCsrId(loginInfo.getStaffId());
			acc.setAlOperateTime(accessoryDao.getDatabaseDate());
			accessoryDao.save(acc);
		}
		
	}

	/**
	 * @author liaoyq
	 * @createTime 2017-8-7
	 */
	@Override
	public void updateBIdByProjIdAndNo(String projId, String projNo,
			String busRecordId) {
		String hql = "update AccessoryList set busRecordId='"+busRecordId+"' where projId='"+projId+"' and projNo='"+projNo+"'";
		accessoryDao.executeHql(hql);
	}

	/**
	 * @author liaoyq
	 * @createTime 2017-8-7
	 */
	@Override
	public void delAccessoryListByProjIdAndNo(String projId, String projNo,
			String path) {
		String hql = "delete from AccessoryList where projId='"+projId+"' and projNo='"+projNo+"'";
		accessoryDao.executeHql(hql);
		//删除文件
		if(StringUtil.isNoneBlank(path)){
			FileUtil.deleteDirectory(Constants.DISK_PATH+Constants.MOBILE_DISK_PATH+path);
		}
	}

	/**
	 * 修改报验单时置空记录表附件的业务ID
	 * @author liaoyq
	 * @createTime 2017-8-7
	 */
	@Override
	public void clearBRId(String projId,String projNo, String busRecordId) {
		String hql="update AccessoryList set busRecordId=null where projId='"+projId+"'"+" and projNo !='"+projNo+"' and busRecordId='"+busRecordId+"'";
		accessoryDao.executeHql(hql);
	}

	@Override
	public List<AccessoryList> queryAccessory(AccessoryList req) {
		return accessoryDao.queryAccessory(req);
	}

	@Override
	public Map<String, Object> queryQualificationAccessoryList(AccessoryQueryReq accessoryQueryReq) {
		Map<String, Object> map=accessoryDao.queryAccessoryList(accessoryQueryReq);
		List<AccessoryList> list=(List<AccessoryList>) map.get("data");
		
		ConstructionPlan plan=constructionPlanDao.viewPlanById(accessoryQueryReq.getProjId());
		Project project = projectDao.get("projId", accessoryQueryReq.getProjId());
		List<String> listStaffIds=new ArrayList<String>();
		if(plan!=null){
			if(StringUtils.isNotBlank(plan.getBuilderId())){
				listStaffIds.add(plan.getBuilderId());			//现场代表
			}
			if(StringUtils.isNotBlank(plan.getCuPmId())){
				listStaffIds.add(plan.getCuPmId());				//项目经理
			}
			if(StringUtils.isNotBlank(plan.getManagementQaeId())){
				listStaffIds.add(plan.getManagementQaeId());	//施工员
			}
			if(StringUtils.isNotBlank(plan.getManagementWacfId())){
				listStaffIds.add(plan.getManagementWacfId());	//材料员
			}
			if(StringUtils.isNotBlank(plan.getSaftyOfficerId())){
				listStaffIds.add(plan.getSaftyOfficerId());		//安全员
			}
			if(StringUtils.isNotBlank(plan.getTechnicianId())){
				listStaffIds.add(plan.getTechnicianId());		//质检员
			}
			if(StringUtils.isNotBlank(plan.getSuJgjId())){
				listStaffIds.add(plan.getSuJgjId());			//现场监理师
			}
			if(StringUtils.isNotBlank(plan.getSuCseId())){
				listStaffIds.add(plan.getSuCseId());			//总监理工程师
			}
			if(StringUtil.isNotBlank(plan.getSuId())){
				listStaffIds.add(plan.getSuId());			//监理单位
			}
			if(StringUtil.isNotBlank(plan.getCuId())){
				listStaffIds.add(plan.getCuId());			//施工单位
			}
		}
		if(project!=null){
			if(StringUtil.isNotBlank(project.getDuId())){
				listStaffIds.add(project.getDuId());			//设计单位
			}
			//检测单位-委托单
			List<BusinessCommunication> listBc = businessCommunicationDao.queryByProjIdAndTypeDetail(accessoryQueryReq.getProjId(),"2011");
			if(listBc!=null && listBc.size()>0){
				listStaffIds.add(listBc.get(0).getDeptId()); //检测单位
			}
		}
		
		AccessoryQueryReq req=new AccessoryQueryReq();
		req.setStaffIdList(listStaffIds);
		
		List<AccessoryList> listQualification=accessoryDao.queryQualificationAccessoryList(req);
		
		
		if(listQualification!=null && listQualification.size()>0){
			for(AccessoryList acc:listQualification){
				//持证人
				Staff staff = staffDao.get(acc.getBusRecordId());
				if(staff!=null){
					acc.setAlTypeId(staff.getStaffName());
				}
				list.add(acc);
			}
		}
		
		map.put("data", list);
		return map;
	}
	/**
	 * 根据工程id和步骤id查询是否已上传附件
	 * @author liaoyq
	 * @createTime 2017-11-16
	 * @param projId
	 * @param stepId
	 * @param sourceType
	 * @return
	 */
	@Override
	public boolean isUploadFile(String projId, String stepId,String busRecordId,String sourceType) {
		AccessoryList req = new AccessoryList();
		req.setProjId(StringUtil.isNotBlank(projId)?projId:"");
		req.setBusRecordId(StringUtil.isNotBlank(busRecordId)?busRecordId:"");
		req.setStep(stepId!=null?stepId:"");
		req.setSourceType(sourceType!=null?sourceType:"");
		//根据工程id和菜单查询附件列表
		List<AccessoryList> list = this.queryAccessory(req);
		if(list!=null && list.size()>0){
			return true;//已上传预算书
		}
		return false;
	}

	/**
	 * 查询竣工资料附件
	 */
	@Override
	public Map<String, Object> queryCompletionAccList(
			AccessoryQueryReq accessoryQueryReq) {
		List<String> list = new ArrayList<String>();
		for(CompletionFileEnum e: CompletionFileEnum.values()) {
			list.add(e.getValue());
    	}
		Map<String, Object> map = accessoryDao.queryCompletionAccList(accessoryQueryReq,list);
		List<AccessoryList> listAcc = (List<AccessoryList>) map.get("data");
		List<AccessoryList> lists = new ArrayList<AccessoryList>();
		if(listAcc!=null && listAcc.size()>0){
			for(AccessoryList accessoryList : listAcc){
				CompletionFileEnum completionFileEnum = CompletionFileEnum.valueof(accessoryList.getStep());
				if(completionFileEnum!=null){
					accessoryList.setStepDes(completionFileEnum.getMessage());
				}
				lists.add(accessoryList);
			}
		}
		map.put("data", lists);
		return map;
	}

	/**
	 * 根据人员查询资质证明附件列表
	 */
	@Override
	public List<AccessoryList> queryQualificationAccListByStaffId(
			AccessoryQueryReq accessoryQueryReq) {
		return accessoryDao.queryQualificationAccessoryList(accessoryQueryReq);
	}
}
