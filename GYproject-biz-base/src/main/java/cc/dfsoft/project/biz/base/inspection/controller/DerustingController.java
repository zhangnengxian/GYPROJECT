package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAntisepsisSpecReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListDerustingReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.DpCheckRequireTypeEnum;
import cc.dfsoft.project.biz.base.inspection.enums.DpCkeckMethodEnum;
import cc.dfsoft.project.biz.base.inspection.enums.DpResultEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.AntisepsisSpecService;
import cc.dfsoft.project.biz.base.inspection.service.DerustingPreservativeService;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.plan.entity.ConstructionPlan;
import cc.dfsoft.project.biz.base.plan.service.ConstructionPlanService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;


@Controller
@RequestMapping(value="/derusting")
public class DerustingController {
	
	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(DerustingController.class);
	
	/**工程报验单服务接口*/
	@Resource
	ProjectChecklistService projectChecklistService;
	
	/**工程服务接口*/
	@Resource
	ProjectService projectService;
	
	/**施工计划服务接口*/
	@Resource
	ConstructionPlanService constructionPlanService;
	
	/**除锈防腐记录*/
	@Resource
	DerustingPreservativeService  derustingPreservativeService;
	/**除锈防腐检查规格*/
	@Resource
	AntisepsisSpecService antisepsisSpecService;
	/**
	 * 除锈工序主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("pcDesId",ProjectChecklistTypeEnum.DERUSTING.getValue());//除锈记录
		modelView.addObject("dpCheckItem",DpCheckRequireTypeEnum.values());//检查项目
		modelView.addObject("dpCheckMethod",DpCkeckMethodEnum.values());//检查方法
		modelView.addObject("dpResult",DpResultEnum.values());//检查结果
		modelView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelView.addObject("constPCpost",PostTypeEnum.DEPUTY_DIRECTOR.getValue());
		modelView.addObject("constructionQcpost",PostTypeEnum.QUALITATIVE_CHECK_MEMBER.getValue());
		modelView.addObject("constructionQaePost",PostTypeEnum.MANAGEMENTQAE.getValue());
		modelView.addObject("suJgjpost",PostTypeEnum.SUJGJ.getValue());
		modelView.addObject("builderpost",PostTypeEnum.BUILDER.getValue());
		modelView.setViewName("inspection/derusting");
		return modelView;
	}
	/**
	 * 右侧页面
	 * @return
	 */
	@RequestMapping(value="/viewPage")
	public ModelAndView viewPage(){
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("");
		return modelView;
	}
	
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-07-25
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/queryProjectList")
	@ResponseBody
	public Map<String,Object> queryProjectList(HttpServletRequest request,ProjectChecklistQueryReq listQueryReq){
		try {
			listQueryReq.setSortInfo(request);
			listQueryReq.setPcDesId(ProjectChecklistTypeEnum.DERUSTING.getValue());
			return projectChecklistService.queryPrProjectChecklist(listQueryReq);
		} catch (Exception e) {
			logger.error("报验单信息查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 查询详述
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param id 工程id
	 * @return ProjectCheckList
	 */
	@RequestMapping(value="/viewCheckList")
	@ResponseBody
	public ProjectChecklist viewCheckList(HttpServletRequest request,@RequestParam(required=true) String id){
		ProjectChecklist list=projectChecklistService.viewProjectCheckList(id);
		return list;
	}
	
	/**
	 * 除锈报验单保存
	 * @author fuliwei
	 * @createTime  2016-7-25
	 * @param checkList
	 * @return String
	 */
	@RequestMapping(value = "/saveDerusting")
	@ResponseBody
	public String saveDerusting(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			return projectChecklistService.saveProCheckList(checkList,Constants.MODULE_CODE_DERUSTING);
		} catch (Exception e) {
			logger.error("除锈工序报验区保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 除锈防腐列表查询
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param dpQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/queryDerusting")
	@ResponseBody
	public Map<String, Object> queryPressure(HttpServletRequest request,DerustingPreservativeQueryReq dpQueryReq) {
		try {
			dpQueryReq.setSortInfo(request);
			Map<String, Object> map=derustingPreservativeService.queryDerusting(dpQueryReq);
		    return map;
		} catch (Exception e) {
			logger.error("除锈防腐列表查询失败！", e);
			return null;
		}
	}
	
	/**
	 * 保存除锈防腐
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param list
	 * @return String
	 */
	@RequestMapping(value = "/saveDerustingRecord")
	@ResponseBody
	public String saveDerustingRecord(@RequestBody ProjectCheckListDerustingReq req){ 
		try {
			derustingPreservativeService.saveDerustingRecord(req);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			logger.error("保存除锈防腐检验单失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	/**
	 * 除锈防腐检查规格列表查询
	 * @author zhangjj
	 * @createTime 2016-8-23
	 * @param pcId 报验单id,asType 规格类型 暂时不用
	 * @return List<AntisepsisSpec>
	 */
	@RequestMapping(value = "/queryAntSpec")
	@ResponseBody
	public List<AntisepsisSpec> queryAntSpec(String pcId,String asType){
		 try {
			return antisepsisSpecService.queryAntSpecByPcId(pcId, asType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 保存除锈防腐检查规格
	 * @author zhangjj
	 * @createTime 2016-8-23
	 * @param list 
	 */
	@RequestMapping(value = "/saveAntSpecList")
	@ResponseBody
	public String saveAntSpecList(@RequestBody ProjectCheckListAntisepsisSpecReq req){
		 try {
			 antisepsisSpecService.saveAntSpecList(req);
			 return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
			
		}
	}
	
}
