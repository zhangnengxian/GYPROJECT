package cc.dfsoft.project.biz.base.project.service.impl;

import cc.dfsoft.project.biz.base.inspection.dao.GrooveRecordDao;
import cc.dfsoft.project.biz.base.inspection.entity.GrooveRecord;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeDao;
import cc.dfsoft.project.biz.base.project.dao.SignNoticeStandardDao;
import cc.dfsoft.project.biz.base.project.dao.SignatureDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.SignNotice;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeStandardService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.CoordinatesUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 签字接口实现
 * @author pengtt
 * @createTime 2016-09-08
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SignatureServiceImpl implements SignatureService{

	public static Logger logger = LoggerFactory.getLogger("interfaceinfo");


	@Resource
	SignatureDao signatureDao;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	/**管沟开挖*/
	@Resource
	GrooveRecordDao grooveRecordDao;
	
	/**签字标准*/
	@Resource
	SignNoticeStandardDao signNoticeStandardDao;
	
	@Resource
	MenuDao menuDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Autowired
	SignNoticeDao signNoticeDao;
	
	@Autowired
	SignNoticeStandardService signNoticeStandardService;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateSign(String menuId,List<Signature> signs, String projId, String businessOrderId, String entityName,
			boolean flag) throws Exception {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();

		List<String> successList=new ArrayList<>();
		List<String> errorList=new ArrayList<>();

		//磁盘路径
		String path = Constants.DISK_PATH + Constants.SIGN_DISK_PATH;
		if(signs!=null && signs.size()>0){
			//本次是否为全部新增记录
			if(flag){
				for(int i=0;i<signs.size();i++){
					Signature sign = signs.get(i);
					Map<String, Object> map = CoordinatesUtil.coordinatesConversion(sign.getLongitude(), sign.getLatitude());
					String[] code =null;
					if(null!=sign.getSignClobStr()){
						code = sign.getSignClobStr().split(",");
					}
					if(null!=sign.getSignTime()){
						String imgUrl = "";
						if(null!=code){
							imgUrl = FileUtil.svgToPng(code[code.length-1], System.nanoTime()+IDUtil.randomNum(8)+"");  //取纳秒时间加随机数八位
						}
						if("".equals(imgUrl)){
							logger.info("新增签字保存失败,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"+projId+"，业务单ID:"+businessOrderId+"，实体名称:"+entityName+"，flag:"+flag+"，签字时间:"+sign.getSignTime()+"，签字Str:"+(code!=null &&code.length>0?code[code.length-1]:""));
						}else{
							sign.setSignTime(signatureDao.getDatabaseDate());
							sign.setImgUrl(imgUrl);
							sign.setBusinessOrderId(businessOrderId);//业务id
							sign.setEntityName(entityName);
							sign.setProjId(projId);
							sign.setSignerId(loginInfo.getStaffId());//签字人Id
							sign.setSignatureName(loginInfo.getStaffName());//签字人名字
							sign.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
							if(map!=null && !map.isEmpty()){
								sign.setLongitude(map.get("x").toString());
								sign.setLatitude(map.get("y").toString());
							}
							signatureDao.saveOrUpdate(sign);
							/*logger.info("全部新增时：签字保存成功,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"
									+",工程ID:"+projId+"，业务单ID:"+businessOrderId+"，实体名称:"+entityName+"，flag:"+flag
									+"，签字时间:"+sign.getSignTime()+"，签字图片路径:"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+imgUrl
									+"签字人:"+sign.getSignerId()+",签字表id:"+sign.getSignatureId());*/

							if(StringUtil.isNotBlank(sign.getPostType())){
								signNoticeService.saveSignNoticeNew(sign.getDataType(), businessOrderId, projId, sign.getPostType(),signs);
							}
						}

					}
					
				}
			}else{
				//存在新增与修改签字记录
				for(int i=0;i<signs.size();i++){
					Signature sign = signs.get(i);
					Map<String, Object> map = CoordinatesUtil.coordinatesConversion(sign.getLongitude(), sign.getLatitude());
					String[] code =null;
					if (null!=sign.getSignClobStr()) {
						code = sign.getSignClobStr().split(",");
					} else { 
						List<Signature> oldSignature = signatureDao.findByProperties(entityName, sign.getFieldName(), businessOrderId,projId);
						if (oldSignature!=null && oldSignature.size() >0) { // 若用户之前签字过，然后用户删除签字了，表里面的签字记录也要删除且相应的图片也应删除
						    signatureDao.delete(oldSignature.get(0));
							if (StringUtil.isNotBlank(sign.getPostType())) { // 若用户之前签字过，然后用户删除签字了，表里面的签字通知也要置为待通知，置为待办条件是（签字通知配置表有配置该职务的签字）
								SignNotice signNotice =  signNoticeDao.queryByBusiIdAndPost(businessOrderId,sign.getPostType(),sign.getDataType());
								Project project = projectDao.get(projId); // 获取实体
								List<SignNoticeStandard> signNoticeStandards = signNoticeStandardService.querySignNoticeStandard(sign.getPostType(),sign.getDataType(),project.getCorpId(),project.getProjectType(),project.getContributionMode());
								if (signNotice !=null && signNoticeStandards.size() > 0) {
									signNotice.setStatus(SignStateEnum.READY_SIGN.getValue()); // 设置为待签字通知
								}
							}
							FileUtil.deleteFile(Constants.DISK_PATH+Constants.SIGN_DISK_PATH+oldSignature.get(0).getImgUrl());
						}

					}
					if(null!=sign.getSignTime()){
						String imgUrl = "";
						if(null!=code){
							imgUrl = FileUtil.svgToPng(code[code.length-1], System.nanoTime()+IDUtil.randomNum(8)+""); //取纳秒时间加随机数八位
						}
						List<Signature> oldList = signatureDao.findByProperties(entityName, sign.getFieldName(), businessOrderId,projId);
						if(map!=null && !map.isEmpty()){
							sign.setLongitude(map.get("x").toString());
							sign.setLatitude(map.get("y").toString());
						}
						if(oldList!=null && oldList.size()>0){
							Signature oldSign = oldList.get(0);
							if(StringUtils.isNotBlank(oldSign.getImgUrl())){
								//删除旧签字照片
								/*boolean b = FileUtil.deleteFile(Constants.DISK_PATH+Constants.SIGN_DISK_PATH+oldSign.getImgUrl());
								if(b){
									logger.info("删除原有签字文件成功,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"+oldSign.getProjId()+"，业务单ID:"+oldSign.getBusinessOrderId()+"，实体名称:"+oldSign.getEntityName()+"，flag:"+flag+"，签字时间:"+oldSign.getSignTime()+"，签字图片路径:"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+oldSign.getImgUrl()+",签字表id:"+oldSign.getSignatureId());
								}else{
									logger.info("删除原有签字文件失败,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"+oldSign.getProjId()+"，业务单ID:"+oldSign.getBusinessOrderId()+"，实体名称:"+oldSign.getEntityName()+"，flag:"+flag+"，签字时间:"+oldSign.getSignTime()+"，原因:签字图片路径:"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+oldSign.getImgUrl()+"不存在");
								}*/
								successList.add(Constants.DISK_PATH+Constants.SIGN_DISK_PATH+oldSign.getImgUrl());//原路径
							}
							if("".equals(imgUrl)){
								signatureDao.delete(oldSign);
								//logger.info("删除原有签字数据库数据成功,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"+oldSign.getProjId()+"，业务单ID:"+oldSign.getBusinessOrderId()+"，实体名称:"+oldSign.getEntityName()+"，flag:"+flag+"，签字时间:"+oldSign.getSignTime()+"，旧的签字图片路径:"+oldSign.getImgUrl()+"，新的签字图片路径:"+imgUrl);
							}else{
								String oldImgUrl=oldSign.getImgUrl();
								oldSign.setImgUrl(imgUrl);
								oldSign.setSignTime(sign.getSignTime());
								if(map!=null && !map.isEmpty()){
									oldSign.setLongitude(map.get("x").toString());
									oldSign.setLatitude(map.get("y").toString());
								}
								sign.setSignTime(signatureDao.getDatabaseDate());
								signatureDao.saveOrUpdate(oldSign);
								errorList.add(imgUrl);//新生成的路径
								/*logger.info("修改签字保存成功,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"
										+projId+"，业务单ID:"+businessOrderId+"，实体名称:"+entityName+"，flag:"+flag+"，签字时间:"+sign.getSignTime()
										+"，旧的签字图片路径:"+oldImgUrl+"，新的签字图片路径:"+imgUrl+",修改签字人："+loginInfo.getStaffId());*/

								if(StringUtil.isNotBlank(sign.getPostType())){
									signNoticeService.saveSignNoticeNew(sign.getDataType(), businessOrderId, projId, sign.getPostType(),signs);
								}
							}
						}else{
							if("".equals(imgUrl)){
								logger.info("新增签字保存失败,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"+",工程ID:"+projId+"，业务单ID:"+businessOrderId+"，实体名称:"+entityName+"，flag:"+flag+"，签字时间:"+sign.getSignTime()+"，签字Str:"+(code!=null &&code.length>0?code[code.length-1]:""));
							}else {
								sign.setSignTime(signatureDao.getDatabaseDate());
								sign.setImgUrl(imgUrl);
								sign.setProjId(projId);
								sign.setSignerId(loginInfo.getStaffId());//签字人Id
								sign.setSignatureName(loginInfo.getStaffName());//签字人名字
								sign.setBusinessOrderId(businessOrderId);//业务id
								sign.setEntityName(entityName);
								sign.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
								if(map!=null && !map.isEmpty()){
									sign.setLongitude(map.get("x").toString());
									sign.setLatitude(map.get("y").toString());
								}
								//新增签字记录
								signatureDao.saveOrUpdate(sign);
								errorList.add(imgUrl);//新生成的路径
								/*logger.info("部分新增时：新增签字保存成功,菜单为："+menuId+"["+sign.getMenuDes()+sign.getMenuId()+"]"
										+",工程ID:"+projId+"，业务单ID:"+businessOrderId+"，实体名称:"+entityName+"，flag:"+flag
										+"，签字时间:"+sign.getSignTime()+"，签字图片路径:"+Constants.DISK_PATH+Constants.SIGN_DISK_PATH+imgUrl
										+",签字人:"+sign.getSignerId()+",签字表id:"+sign.getSignatureId());*/
								if(StringUtil.isNotBlank(sign.getPostType())){
									signNoticeService.saveSignNoticeNew(sign.getDataType(), businessOrderId, projId, sign.getPostType(),signs);
								}
							}
						}
					}

				}
				SessionUtil.getSession().setAttribute("successList",successList);
				SessionUtil.getSession().setAttribute("errorlist",errorList);
			}
			signNoticeService.updateFinishSignaturNotice(businessOrderId,signs);
		}
	}

	@Override
	public List<Signature> getAll() {
		return signatureDao.getAll();
	}

	@Override
	public List<Map<String, Object>> findSignStep() {
		
		return signatureDao.findSignStep();
	}
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
    public void deleteImg(String businessOrderId, String menuDes){
    	Signature signature=signatureDao.selectImg(businessOrderId, menuDes);
    	if(signature!=null){
    		signatureDao.delete(signature);
    		FileUtil.deleteFile(Constants.DIAGRAM_DISK_PATH+signature.getImgUrl());
    	}
    }
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveImage(HttpServletRequest request, MultipartFile[] files,String projId, String projNo, String businessOrderId, String menuDes) throws Exception {
		//删除原简图文件
		this.deleteImg(businessOrderId, menuDes);
		if(files!=null){
		//上传文件
		String path = FileUtil.uploadFile(request, projNo, menuDes, files);
		
		for(MultipartFile file : files) {
		//	String filename = path+menuDes+"-"+file.getOriginalFilename();
			Signature sign = new Signature();
			sign.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			sign.setImgUrl(path);
			sign.setProjId(projId);
			sign.setMenuDes(menuDes);
			sign.setBusinessOrderId(businessOrderId);//业务id
			//新增签字记录
			signatureDao.saveOrUpdate(sign);
			request.setAttribute("drawUrl", path);
		}
		}
		
	}

	@Override
	public Signature selectImg(String boId, String menuDes) {
		// TODO Auto-generated method stub
		return signatureDao.selectImg(boId, menuDes);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateQrCode(String menuId,String menuDes,String projId, String businessOrderId, String entityName,String pngName ) {
		// TODO Auto-generated method stub
		Signature signature=new Signature();
		Map<String, Object> map = CoordinatesUtil.coordinatesConversion(signature.getLongitude(), signature.getLatitude());
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String name = sdf.format(d) + "/" + pngName;
		String imgUrl =  name+".png";
		signature.setMenuId(menuId);
		signature.setMenuDes(menuDes);
		signature.setImgUrl(imgUrl);
		signature.setBusinessOrderId(businessOrderId);//业务id
		signature.setEntityName(entityName);
		signature.setProjId(projId);
		signature.setFieldName("qrCode");
		signature.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
		if(map!=null && !map.isEmpty()){
			signature.setLongitude(map.get("x").toString());
			signature.setLatitude(map.get("y").toString());
		}
		signatureDao.saveOrUpdate(signature);
	}
	
	/**
	 * 删除二维码
	 * @author fuliwei
	 * @createTime 2017年3月6日
	 * @param 
	 * @return
	 */
	@Override
	public void deleteByPcId(String fieldName, String pcId) {
		signatureDao.deleteByPcId(fieldName, pcId);
		
	}

	/**
	 * 上传简图
	 */
	@Override
	public void saveImage(HttpServletRequest request, MultipartFile[] files,
			String projId, String projNo, String businessOrderId,
			String menuDes, String menuId) throws Exception {
		//删除原简图文件
		   this.deleteImg(businessOrderId, menuDes);
			if(files!=null){
				//上传文件
				String path;
				try {
					path = FileUtil.uploadFile(request, projNo, menuDes, files);
				} catch (Exception e) {
					e.printStackTrace();
					throw new Exception("保存失败！"); 
				}
				
				for(MultipartFile file : files) {
				//	String filename = path+menuDes+"-"+file.getOriginalFilename();
					Signature sign = new Signature();
					sign.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
					sign.setImgUrl(path);
					sign.setProjId(projId);
					sign.setMenuDes(menuDes);
					sign.setMenuId(menuId);
					sign.setBusinessOrderId(businessOrderId);//业务id
					//新增签字记录
					signatureDao.saveOrUpdate(sign);
					request.setAttribute("drawUrl", path);
				}
			}
				
	}

	
	@Override
	public Signature queryImg(String boId, String menuId) {
		return signatureDao.queryImg(boId,menuId);
	}

	/**
	 * 根据业务单ID和菜单ID删除签字
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteImgByBIdAndMenuId(String businessOrderId, String menuId) {
		List<Signature> list = signatureDao.findByBIdAndMenuId(businessOrderId,menuId);
		if(list!=null&&list.size()>0){
			for(Signature signature : list){
				signatureDao.delete(signature);//删除签字记录信息
				if(!signature.getImgUrl().contains(Constants.SIGN_PCTURE_PATH)){
					FileUtil.deleteFile(Constants.DISK_PATH+Constants.SIGN_DISK_PATH+signature.getImgUrl());//删除签字
				}
			}
		}
	}
	/**
	 * 根据业务的Id删除签字
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteByBId(String businessOrderId) {
		signatureDao.deleteByBId(businessOrderId);
	}
	
	/**
	 * 保存报验单签字
	 * @author fuliwei
	 * @createTime 2018年6月4日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCheckListSign(ProjectChecklist checkList,String dataType) {
		if(ProjectChecklistTypeEnum.GROOVE_RECORD.getValue().equals(checkList.getPcDesId())){
			//管沟开挖
			this.saveGrooveRecordSign(checkList,dataType);
		}
	}
	
	
	public void saveGrooveRecordSign(ProjectChecklist checkList,String dataType){
		String [] recordsIdList=checkList.getRecordsId().split(",");
		GrooveRecord gr=new GrooveRecord();
		Project project = projectDao.get(checkList.getProjId());  //根据ID取得实体
		//先查配置
		List<SignNoticeStandard> signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType("1", checkList.getPcDesId(),project.getCorpId(),project.getProjectType(),project.getContributionMode());
		if(signNoticeStandardList.size() < 1){
			//查询默认
			signNoticeStandardList=signNoticeStandardDao.queryBySortNoAndDateType("1", checkList.getPcDesId(),Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		}
		String post="";
		if(signNoticeStandardList!=null && signNoticeStandardList.size()>0){
			post=signNoticeStandardList.get(0).getPost();
		}else{
			post="-1";
		}
		
		List<Signature> signs=new ArrayList<Signature>();
		Signature signature=new Signature();
		signature.setPostType(post);
		
		for(String recordsId:recordsIdList){
			//记录单id去查记录 判断下一个是否签字
			gr= grooveRecordDao.get(recordsId);
			if(gr!=null && StringUtils.isNotBlank(gr.getGrBuilder())){
				//判断第一个人是否签字 如果已签字
				signNoticeService.saveSignNoticeNew(dataType, recordsId, checkList.getProjId(), post,signs);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateSignPicture(String menuId, String grade, String projId, String businessOrderId,
			String entityName, boolean flag,String constants) {
		    LoginInfo login=SessionUtil.getLoginInfo();
		    
		    Signature signature=new Signature();
		    
		    signature.setSignatureName(login.getStaffName());
		    signature.setSignerId(login.getStaffId());
			Menu menu = null;
		    if(StringUtil.isNotBlank(menuId)){
				menu = menuDao.get(menuId);
			}
		    if(menu!=null){
		    	signature.setMenuId(menuId);
				signature.setMenuDes(menu.getMenuName());
		    }
			
			signature.setImgUrl(login.getSignPicture());
			signature.setBusinessOrderId(businessOrderId);//业务id
			signature.setEntityName(entityName);
			signature.setProjId(projId);
			signature.setFieldName(constants+grade);
			signature.setSignTime(menuDao.getDatabaseDate());
			signature.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			
			signatureDao.saveOrUpdate(signature);
		
	}


	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveOrUpdateSignPictureAuditIns(String longitude, String latitude, String menuId, String grade, String projId, String businessOrderId, String entityName, boolean flag, String constants) {
		LoginInfo login=SessionUtil.getLoginInfo();
		Signature signature=new Signature();
		signature.setSignatureName(login.getStaffName());
		signature.setSignerId(login.getStaffId());
		signature.setPostType(login.getPost());
		
		Project proj=projectDao.get(projId);
		Object obj = Constants.getSysConfigByKey(proj.getCorpId()+"_"+proj.getProjectType()+"_"+proj.getContributionMode()+"_inspection");
		if(obj!=null){
			grade=String.valueOf(Integer.valueOf(obj.toString())+Integer.valueOf(grade));
		}
		Menu menu = null;
		if(StringUtil.isNotBlank(menuId)){
			menu = menuDao.get(menuId);
		}
		if(menu!=null){
			signature.setMenuId(menuId);
			signature.setMenuDes(menu.getMenuName());
		}
		signature.setLongitude(longitude);
		signature.setLatitude(latitude);
		signature.setImgUrl(login.getSignPicture());
		signature.setBusinessOrderId(businessOrderId);//业务id
		signature.setEntityName(entityName);
		signature.setProjId(projId);
		signature.setFieldName(constants+grade);
		signature.setSignTime(menuDao.getDatabaseDate());
		signature.setSignatureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
		signatureDao.saveOrUpdate(signature);
	}

	@Override
	public List<Signature> findListByBusId(String busId) {
		return signatureDao.findListByBusId(busId);
	}
}
