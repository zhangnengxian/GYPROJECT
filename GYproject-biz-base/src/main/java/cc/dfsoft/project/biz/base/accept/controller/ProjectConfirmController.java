
package cc.dfsoft.project.biz.base.accept.controller;


import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.accept.entity.ProjectApplication;
import cc.dfsoft.project.biz.base.accept.service.ProjectApplicationService;
import cc.dfsoft.project.biz.base.baseinfo.enums.BackReasonEnum;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.WorkFlowUtil;


/**
 * 立项确认
 * @author pengtt
 * @createTime 2016-07-22
 *
 */
@Controller
@RequestMapping(value="/projectConfirm")
public class ProjectConfirmController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(ProjectConfirmController.class);
	
	/** 工程服务接口 */
	@Resource
	ProjectService projectService;
	
	/** 工程规模明细服务接口 */
	@Resource
	ScaleDetailService scaleDetailService;
	
	/** 立项申请单信息服务接口*/
	@Resource
	ProjectApplicationService projectApplicationService;
	@Resource
	ProjectTypeService  projectTypeService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		
		ModelAndView modelView=new ModelAndView();
		//工程状态
		modelView.addObject("projStatus", ProjStatusEnum.values());
		modelView.setViewName("accept/projectConfirm");
		return modelView;
	}
	
	/**
	 * 打开右侧详述页面
	 * @return
	 */
	@RequestMapping(value = "/viewPage")
	public ModelAndView viewPage() {
		ModelAndView modelview = new ModelAndView();
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		modelview.addObject("area", AreaEnum.values());
		modelview.addObject("backReason", BackReasonEnum.values());//退单原因
		modelview.setViewName("accept/projectConfirmRight");
		return modelview;
		
	}
	
	/**
	 * 弹出搜索
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/projectConfirmSearch")
	public ModelAndView projectSearchPopPage() {
		ModelAndView modelview = new ModelAndView();
		List<ProjectType> list=projectTypeService.queryAllList();
		modelview.addObject("projLtype", list);
		//modelview.addObject("projLtype", ProjLtypeEnum.values());
		modelview.setViewName("accept/projectConfirmSearch");
		return modelview;
	}

	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-9
	 * @param id 工程id
	 * @return Project
	 */
	@RequestMapping(value="/viewProjectConfirm")
	@ResponseBody
	public Project viewProjectConfirm(HttpServletRequest request,@RequestParam(required=true) String id){
		try {
			Project pro=projectService.viewProject(id);
			//通过id查立项申请信息
			ProjectApplication application=projectApplicationService.queryById(id);
			if(application!=null){
				pro.setPaNo(application.getPaNo());
			}
			return pro;
		} catch (Exception e) {
			logger.error("工程详述信息查询失败！", e);
		}
		return null;
	}
	@RequestMapping(value="/uploadPhoto")
	@ResponseBody
	public String uploadPhoto(HttpServletRequest request,@RequestParam(value = "file[]", required = false) MultipartFile[] file){
		try {
			int totalbytes = request.getContentLength();
			byte[] dataOrigin = new byte[totalbytes];
			DataInputStream in = new DataInputStream(request.getInputStream());
			in.readFully(dataOrigin); // 根据长度，将消息实体的内容读入字节数组dataOrigin中
			in.close(); // 关闭数据流
			String reqcontent = new String(dataOrigin); // 从字节数组中得到表示实体的字符串
			InputStream inputStream=request.getInputStream();
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);//判断是否是表单文件类型
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload sfu = new ServletFileUpload(factory);
			List items = sfu.parseRequest(request);//从request得到所有上传域的列表
			for(Iterator iter = items.iterator();iter.hasNext();){
			    FileItem fileitem =(FileItem) iter.next();
			    if(!fileitem.isFormField()&&fileitem!=null){//判读不是普通表单域即是file
			        
			    }
			}
		} catch (Exception e) {
			logger.error("工程详述信息查询失败！", e);
		}
		return null;
	}
	
	/**
	 * 工程列表条件查询
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-06-21
	 * @return
	 */
	@RequestMapping(value = "/queryProject")
	@ResponseBody
	public Map<String,Object> queryProject(HttpServletRequest request,ProjectQueryReq projectQueryReq){
		try {
			projectQueryReq.setProjStatusId(ProjStatusEnum.TO_PROJECT_CONFIRM.getValue());//待立项确认
			projectQueryReq.setSortInfo(request);
			projectQueryReq.setStepId(StepEnum.PROJECT_CONFIRM.getValue());
			return projectService.queryProjectByTime(projectQueryReq);
		} catch (Exception e) {
			logger.error("工程信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 工程规模明细
	 * @param projectQueryReq
	 * @author pengtt
	 * @createTime 2016-06-22
	 * @return
	 */
	@RequestMapping(value = "/queryScaleDetail")
	@ResponseBody
	public Map<String,Object> queryScaleDetail(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
		try {
			scaleDetailQueryReq.setSortInfo(request);
			return scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
		} catch (Exception e) {
			logger.error("工程规模明细信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 立项确认保存
	 * @param projectAcceptDtos
	 * @return
	 */
	@RequestMapping(value = "/totalSave")
	@ResponseBody
	public String totalSave(@RequestBody(required = true) Project project){
		try {
			if(project.getFlag().equals("1")){
				project.setProjStatusId(WorkFlowUtil.workFlowStatus(WorkFlowActionEnum.PROJECT_CONFIRM.getActionCode()));
			}else{
				project.setProjStatusId(ProjStatusEnum.TO_PROJECT_CONFIRM.getValue());
			}
			
			return projectService.acceptTotalSave(project, true);
		} catch (Exception e) {
			logger.error("立项确认信息保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	@RequestMapping(value = "/projPhoto")
    public ModelAndView projPhoto(){
    	ModelAndView modelView=new ModelAndView();
		modelView.setViewName("accept/projPhoto");
		return modelView;
    }
}
	  
	
	
