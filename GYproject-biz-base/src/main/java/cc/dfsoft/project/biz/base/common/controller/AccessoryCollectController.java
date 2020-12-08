package cc.dfsoft.project.biz.base.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.stat.TableStat.Name;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.DataCollectionTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;


/**
 * 附件
 * @author jingjing
 *
 */
@Controller
@RequestMapping(value="/accessoryCollect")
public class AccessoryCollectController {
	
	/**日志实例*/
	private  Logger logger = LoggerFactory.getLogger(AccessoryCollectController.class);
	
	/** 附件记录服务接口 */
	@Resource 
	AccessoryService accessoryService;
	
	
	/**
	 * 弹出资料上传
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginName", SessionUtil.getLoginInfo());
		modelview.addObject("alreadyCompleted",ProjStatusEnum.ALREADY_COMPLETED.getValue());
		modelview.setViewName("common/dataCollection");
		return modelview;
	}

	/**
	 * 查询资料标准收集
	 * @return
	 */
	@RequestMapping(value = "/queryAccessoryItem")
	@ResponseBody
	public List<CollectAccessoryItem> queryAccessoryItem(AccessoryQueryReq accessoryQueryReq){
		try {
			accessoryQueryReq.setDataType(DataCollectionTypeEnum.DESIGN_DATA.getValue());//设计资料
			return accessoryService.queryAccessoryItem(accessoryQueryReq);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 设置工程图片
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projPicture")
	public ModelAndView projPicture() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("common/projPicture");
		return modelview;
	}
	
	/**
	 * 文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public JSONObject uploadFile(HttpServletRequest request,AccessoryList accDto,@RequestParam(value = "files[]", required = false) MultipartFile[] files) {
		
		try {
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
			// TODO Auto-generated catch block
			FileUtil.deleteFile(accDto.getAlPath());
			e.printStackTrace();
		} 
	 
		return null;
		
	}
	/**
	 * 上传工程图片（首页展示）
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadPicture")
	@ResponseBody
	public String uploadPicture(@RequestBody AccessoryList acc){
		try {
			accessoryService.uploadPicture(acc);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
		
	}
	
	
	
	/**
	 * 附件列表查询
	 * @param searchParams
	 * @return
	 */
	@RequestMapping(value = "/queryAccessoryList")
	@ResponseBody
	public Map<String, Object> queryAccessoryList(HttpServletRequest request,AccessoryQueryReq accessoryQueryReq) {
		accessoryQueryReq.setSortInfo(request);
		return  accessoryService.queryAccessoryList(accessoryQueryReq);
	}
	/**
	 * 附件列表查询
	 * @param searchParams
	 * @return
	 */
	@RequestMapping(value = "/queryAccListPhoto")
	@ResponseBody
	public Map<String, Object> queryAccListPhoto(AccessoryQueryReq accessoryQueryReq) {
		return  accessoryService.queryAccListPhoto(accessoryQueryReq);
	}
	/**
	 * 打开附件查看
	 * @param fpath
	 * @return
	 */
	@RequestMapping(value = "/openFile")
	@ResponseBody
	public String openFile(HttpServletResponse response,String id){
		try {
			AccessoryQueryReq accessoryQueryReq=new AccessoryQueryReq();
			accessoryQueryReq.setCaiId(id);
			AccessoryList acc=accessoryService.queryAccessoryList(id);
			if(null!=acc){
			  FileUtil.downLoad(acc.getAlName(),acc.getAlPath(),response,true);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//logger.info("——————————————————————打开"+e.getMessage());
			return "false";
		}
		return "true";
	}
	/**
	 * 打开用户变更附件
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/openChangeFile")
	@ResponseBody
	public String openCcangeFile(HttpServletResponse response,String id){
		try {
			List<AccessoryList> accs=accessoryService.queryAccessoryByBus(id,AccessorySourceEnum.CHANGE_FILE.getValue());
			if(accs!=null&&accs.size()>0){
				AccessoryList acc = accs.get(0);
				if(null!=acc){
				    FileUtil.downLoad(acc.getAlName(),acc.getAlPath(),response,true);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	/**
	 * 
	 * @param alId 附件列表id
	 * @param path 附件路径
	 * @return
	 */
	@RequestMapping(value = "/delAccessoryList")
	@ResponseBody
	public String delAccessoryList(@RequestParam(required=true)String alId,String path){
		try {
			accessoryService.delAccessoryList(alId, path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	
	/**
	 * 
	 * @param alId 附件列表id
	 * @param path 附件路径
	 * @return
	 */
	@RequestMapping(value = "/delAccPhoto")
	@ResponseBody
	public String delAccPhoto(@RequestParam(required=true)String alPath){
		try {
			 accessoryService.delAccPhoto(alPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
	/**
	 * 拍照文件上传
	 * 
	 * @param request
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/uploadPhoto")
	@ResponseBody
	public String uploadFile(HttpServletRequest request,AccessoryList req) {		
		try {			
			 accessoryService.uploadPhoto(request,req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	 
		return null;
		
	}
	
	@RequestMapping(value="/openCoFile")
	@ResponseBody
	public String openCoFile(HttpServletResponse response,AccessoryList req){
		try {			
			List<AccessoryList> accs=accessoryService.queryAccessory(req);
			if(accs!=null&&accs.size()>0){
				AccessoryList acc = accs.get(0);
				if(null!=acc){
					  FileUtil.downLoad(acc.getAlName(),acc.getAlPath(),response,true);
					}
			}
		} catch (Exception e) {
			logger.error("查询附件失败！",e);
			e.printStackTrace();
		} 
	 
		return null;
	}
	
	
	/**
	 * 附件列表查询
	 * @param searchParams
	 * @return
	 */
	@RequestMapping(value = "/queryQualificationAccessoryList")
	@ResponseBody
	public Map<String, Object> queryQualificationAccessoryList(HttpServletRequest request,AccessoryQueryReq accessoryQueryReq) {
		accessoryQueryReq.setSortInfo(request);
		return  accessoryService.queryQualificationAccessoryList(accessoryQueryReq);
	}
	/**
	 * 查询竣工资料中的附件
	 * @author liaoyq
	 * @createTime 2018-1-17
	 */
	@RequestMapping(value="/queryCompletionAccList")
	@ResponseBody
	public Map<String,Object> queryCompletionAccList(HttpServletRequest request,AccessoryQueryReq accessoryQueryReq){
		accessoryQueryReq.setSortInfo(request);
		return accessoryService.queryCompletionAccList(accessoryQueryReq);
	}
	
	
	/**
	 * 查询详情
	 * @return
	 */
	@RequestMapping(value = "/queryAccessoryList/{alId}")
	@ResponseBody
	public String queryAccessoryList(@PathVariable String alId){
		try{
			return accessoryService.queryAccessoryList(alId).getAlPath();
		}catch (Exception e){
			logger.error("附件查询失败！",e);
			return Constants.OPERATE_RESULT_FAILURE; //返回操作失败标识符
		}
		
	}
} 
