package cc.dfsoft.project.biz.base.update.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.project.dto.ProjectViewDto;
import cc.dfsoft.project.biz.base.update.dto.UpdateInfoReq;
import cc.dfsoft.project.biz.base.update.entity.UpdateInfo;
import cc.dfsoft.project.biz.base.update.service.UpdateInfoService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.UploadResult;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.EnclosureSourceEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.biz.sys.dept.service.EnclosureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FileUtil;


/**
 * 更新信息
 * @author dn
 *		
 */
@Controller
@RequestMapping(value="/updateInfo")
public class UpdateInfoController {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(UpdateInfoController.class);
	
	/** 工程服务接口 */
	@Resource
	UpdateInfoService updateInfoService;
	
	/** 附件服务接口 */
	@Resource
	EnclosureService enclosureService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	@RequestMapping(value = "/main")
	public ModelAndView main() {
		ModelAndView modelview = new ModelAndView();
		modelview.addObject("loginName", SessionUtil.getLoginInfo());
		modelview.setViewName("updateInfo/updateInfo");
		return modelview;
	}
	

	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	@ResponseBody
	public ModelAndView viewUpdatePage() {
		ModelAndView modelview = new ModelAndView();
		List<UpdateInfo> list=updateInfoService.findAllInfo();
		modelview.addObject("corp", departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelview.setViewName("updateInfo/updateInfoRight");
		modelview.addObject("list", list);
		return modelview;
		
	}
	

	
	/**
	 * 打开左侧列表页面
	 * @return
	 */
	@RequestMapping(value = "/queryPage")
	@ResponseBody
	public Map<String,Object> queryPage(HttpServletRequest request,UpdateInfoReq updateInfo) {
		try {
			updateInfo.setSortInfo(request);
			LoginInfo loginInfo=SessionUtil.getLoginInfo();
			updateInfo.setCorpId(loginInfo.getCorpId());
			return updateInfoService.queryUpdateInfo(updateInfo);	
		} catch (Exception e) {
			logger.error("更新信息查询失败！", e);
			return null;
		}
		
	}
	
	/**
	 * 查询详述
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/viewUpdateInfo")
	@ResponseBody
	public UpdateInfo viewUpdateInfo(HttpServletRequest request,@RequestParam(required=true) String id){
		UpdateInfo updateInfo=updateInfoService.findById(id);
		return updateInfo;
	}
	
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/UpdateInfoSearchPopPage")
	public ModelAndView UpdateInfoSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("updateInfo/updateInfoSearchPopPage");
		return modelview;
	}
	
	/**
	 * 更新信息保存
	 * @return
	 */
	@RequestMapping(value = "/updateInfoSave")
	@ResponseBody
	public String updateInfoSave(@RequestBody(required = true) UpdateInfo updateInfo){
		try {
				updateInfoService.updateUpdateInfo(updateInfo);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 通过工程id查询工程对象
	 * @param projId
	 * @return
	 */
	@RequestMapping(value = "/queryBaseInfo")
	@ResponseBody
	public ProjectViewDto queryBaseInfo(){
		try {
			return updateInfoService.queryBaseInfo();
		} catch (Exception e) {
			logger.error("查询基本信息失败！", e);
			return null;
		}
	}
	
	/**
	 * 删除更新信息
	 * @author fuliwei
	 * @createTime 2017年6月2日
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteUpdateInfo")
	public String deleteUpdateInfo(String id){
		try{
			updateInfoService.deleteById(id);
			return Constants.OPERATE_RESULT_SUCCESS;
		}catch(Exception e){
			logger.error("更新信息删除失败！",e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewUpdateInfoList")
	@ResponseBody
	public ModelAndView viewUpdateInfo() {
		ModelAndView modelview = new ModelAndView();
		modelview.setViewName("updateInfo/updateInfoList");
		return modelview;
		
	}

	/**
	 * 更新信息保存
	 * @param request
	 * @param changeManagement
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/updateInfoSaveFile")
	@ResponseBody
	public JSONObject updateInfoSaveFile(HttpServletRequest request,UploadResult updateInfo,@RequestParam(value = "files[]") MultipartFile[] files){
		JSONArray js = new JSONArray();
		JSONObject jso = new JSONObject();
		JSONObject json = new JSONObject();
		try {
			updateInfoService.updateInfoSaveFile(request,updateInfo, files);				
			jso.put("name", files[0].getOriginalFilename());
			jso.put("size", files[0].getSize());
			jso.put("type", "xlsx/xls");
			jso.put("url", files[0].getOriginalFilename());
			jso.put("thumbnailUrl", files[0].getOriginalFilename());
			jso.put("deleteUrl", "" + "deleteImport?fileName="+ files[0].getOriginalFilename());
			jso.put("deleteType", "DELETE");
			js.add(jso);
			json.put("files", js);
			json.put("drawUrl", Constants.DIAGRAM_DISK_PATH+request.getAttribute("drawUrl"));
			json.put("operateId", updateInfo.getOperateId());
			json.put("result", Constants.OPERATE_RESULT_SUCCESS);
			return json;
		} catch (Exception e) {
			logger.error("员工信息保存失败！", e);
		}
		json.put("result", Constants.OPERATE_RESULT_FAILURE);
		return json;
	}
	/**
	 * 更新信息保存
	 * @param request
	 * @param changeManagement
	 * @param files
	 * @return
	 */
	@RequestMapping(value = "/updateInfoSaveNoFile")
	@ResponseBody
	public String updateInfoSaveNoFile(HttpServletRequest request,@RequestBody(required=true)UploadResult updateInfo){
		try {
			updateInfoService.updateInfoSaveFile(request,updateInfo, null);				
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("员工信息保存失败！", e);
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
    /**
     * 打开附件
     */
	@RequestMapping(value = "/openupdateInfoFile")
	@ResponseBody
	public String openupdateInfoFile(HttpServletResponse response,String id){
		try {
			List<Enclosure> accs=enclosureService.queryEnclosureByBus(id,EnclosureSourceEnum.UPDATE.getValue());
			if(accs!=null&&accs.size()>0){
				Enclosure acc = accs.get(0);
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
}
