package cc.dfsoft.project.biz.base.constructmanage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.StaleObjectStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.constructmanage.dto.BusinessCommunicationReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.NdeRecordBcDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.BcType;
import cc.dfsoft.project.biz.base.constructmanage.entity.BusinessCommunication;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.project.biz.base.constructmanage.enums.NdeTestItemEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.NdeWeldMethodEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.TestResultEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.BcTypeService;
import cc.dfsoft.project.biz.base.constructmanage.service.BusinessCommunicationService;
import cc.dfsoft.project.biz.base.constructmanage.service.NdeRecordService;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.ExceptionUtil;
import cc.dfsoft.uexpress.common.util.FileUtil;

/**
 * 
 * 描述:无损检测控制类
 * @author liaoyq
 * @createTime 2017年9月27日
 */
@Controller
@RequestMapping(value="/ndeRecord")
public class NdeRecordController {

	/**输出日志实例*/
	private static Logger logger = LoggerFactory.getLogger(BusinessCommunicationController.class);
	/**业务沟通服务接口*/
	@Resource
	BusinessCommunicationService businessCommunicationService;
	/** 附件记录服务接口 */
	@Resource 
	AccessoryService accessoryService;
	@Resource
	BcTypeService bcTypeService;
	@Resource
	NdeRecordService ndeRecordService;
	
	/**业务合作伙伴*/
	@Resource
	BusinessPartnersService businessPartnersService;
	
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("loginId",SessionUtil.getLoginInfo().getStaffId());
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH);
		modelView.addObject("drawUrl",Constants.DISK_PATH+Constants.DIAGRAM_DISK_PATH);
		modelView.addObject("unitTypes", UnitTypeEnum.values());
		List<BcType> bcTypeList = bcTypeService.findBcType("","");
		List<BcType> bcTypeDetailList = bcTypeService.findAllBcType();
		modelView.addObject("bcType",bcTypeList);
		modelView.addObject("bcTypeDetail",bcTypeDetailList);
		modelView.addObject("ndeTestItem",NdeTestItemEnum.values());//无损检测项目
		modelView.addObject("ndeWeldMethod",NdeWeldMethodEnum.values());//无损检测焊接方法
		modelView.addObject("testResultEnum",TestResultEnum.values());//无损检测结果
		
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelView.addObject("ndeRecord",loginInfo.getPost()); 			     	//当前登录人职务
		modelView.addObject("loginInfo",loginInfo);                            //登录人信息
		modelView.addObject("bcInitiatorPost",loginInfo.getPost()); 		    //通知人职务
		modelView.addObject("receivePost",PostTypeEnum.CHECKER.getValue()); 		//接收人职位-检测职务
		
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(loginInfo.getCorpId()+"_"+"120214");
	    if(list != null && list.size() > 0){
	    	modelView.addObject("sujgjPost",PostTypeEnum.BUILDER.getValue());  	//现场代表职务
	    	modelView.addObject("suCsePost",PostTypeEnum.PROJECT_LEADER.getValue());  	//工程负责人职务
	    	modelView.addObject("bcRecipientPost",PostTypeEnum.BUILDER.getValue()); 		//接收人职位-现场代表职务
	    }else{
	    	modelView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 		//现场监理
			modelView.addObject("suCsePost",PostTypeEnum.SUCSE.getValue()); 		//总监
			modelView.addObject("bcRecipientPost",PostTypeEnum.SUJGJ.getValue()); 		//接收人职位-监理职务
	    }
		
		BusinessPartners bp=businessPartnersService.viewBusinessPartnersById(loginInfo.getDeptId());
		if(bp!=null){
			modelView.addObject("unitType",bp.getUnitType()); 									//监理单位
		}else{
			modelView.addObject("unitType",UnitTypeEnum.GAS_GROUP.getValue()); //燃气集团
		}
		modelView.addObject("checkUnit",UnitTypeEnum.DETECTION_UNIT.getValue()); 		//检测单位
		modelView.addObject("suUnit",UnitTypeEnum.CONSTRUCTION_CONTROL_UNIT.getValue()); 		//监理单位
		modelView.setViewName("constructmanage/ndeRecord");
		return modelView;
	}
	
	/**
	 * 查询无损检测业务通知单
	 * @param request
	 * @param businessCommunicationReq
	 * @return
	 */
	@RequestMapping(value = "/queryBusinessCommunication")
	@ResponseBody
	public Map<String,Object> queryBusinessCommunication(HttpServletRequest request,BusinessCommunicationReq businessCommunicationReq){
		try {
			businessCommunicationReq.setBcTypeDetail("2011");//探伤
			businessCommunicationReq.setSortInfo(request);
			Map<String,Object> result = businessCommunicationService.queryBusinessCommunication(businessCommunicationReq);
			return result;
		} catch (Exception e) {
			logger.error("无损检测业务通知单列表查询失败！", ExceptionUtil.getMessage(e));
			return null;
		}
	}
	/**
	 * 保存无损检测通知
	 * @param ndeRecord
	 * @return
	 */
	@RequestMapping(value="/saveNdeRecord")
	@ResponseBody
	public String saveNdeRecord(HttpServletRequest request,@RequestBody(required = true) NdeRecord ndeRecord){
		try {
			String res = ndeRecordService.saveNdeRecord(ndeRecord);
			NdeRecordBcDto ndeRecordBcDto = businessCommunicationService.viewByBcId(ndeRecord.getBcId());
			//统一版本
			if(ndeRecordBcDto!=null && ndeRecordBcDto.getbCommunication()!=null && ndeRecordBcDto.getNdeRecord()!=null){
				BusinessCommunication bc = ndeRecordBcDto.getbCommunication();
				NdeRecord nd = ndeRecordBcDto.getNdeRecord();
				Integer version=bc.getVersion().compareTo(nd.getVersion())>0?bc.getVersion():nd.getVersion();
				bc.setVersion(version);
				businessCommunicationService.updateByBcId(bc);
				nd.setVersion(version);
				ndeRecordService.updateByNdId(nd);
			}
			return  res;
		}catch(HibernateOptimisticLockingFailureException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！", ExceptionUtil.getMessage(e));
			return "already";
		}catch(StaleObjectStateException e ){
			request.getSession().setAttribute("singtureType","error");
			logger.error("版本冲突，需刷新页面！",ExceptionUtil.getMessage(e));
			return "already";
		}catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("无损检测通知保存失败！", ExceptionUtil.getMessage(e));
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/saveUploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		
		try {
			 if(StringUtils.isBlank(accDto.getBusRecordId())){
				 JSONObject json = new JSONObject();
				 json.put("result","noBusiness");
				 return json;
			 }
			accessoryService.uploadAccessory(request, accDto, files);
			JSONArray js = new JSONArray();
			JSONObject jso = new JSONObject();
			JSONObject json = new JSONObject();
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);

			return json;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			// TODO Auto-generated catch block
			System.out.println(accDto.getAlPath());
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
		} 
	 
		return null;
		
	}
}
