package cc.dfsoft.project.biz.base.inspection.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.complete.enums.CheckResultEnum;
import cc.dfsoft.project.biz.base.inspection.dao.CheckListTypeDao;
import cc.dfsoft.project.biz.base.inspection.entity.CheckList;
import cc.dfsoft.project.biz.base.inspection.entity.CheckListType;
import cc.dfsoft.project.biz.base.inspection.service.CheckListService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 报验信息
 * @author 王梦远
 *
 */
@Controller
@RequestMapping(value="/checkList")
public class CheckListController {
	@Resource
	CheckListService checkListService;
	@Resource
	CheckListTypeDao checkListTypeDao;
	@Autowired
	private ProjectChecklistService projectChecklistService;
	/**
	 * 显示主页面
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView modelview = new ModelAndView();
		//报验资料类型
		List<CheckListType> list=checkListTypeDao.getAll();
		modelview.addObject("checkType",list);
		modelview.addObject("loginName", SessionUtil.getLoginInfo());
		modelview.addObject("cRQuality",CheckResultEnum.values());
		modelview.setViewName("inspection/checkList");
		return modelview;
		
	}
	/**
	 * 查质量自检记录详述
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/selfInspectionRecordQuqlity")
	@ResponseBody
	public Map<String, String> viewSelfInspectionRecordQuqlity(HttpServletRequest request,@RequestParam(required=true) String id){
		return checkListService.viewSelfInspectionRecordQuqlity(id);
	}
	/**
	 * 保存自检单
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveCheckList")
	@ResponseBody
	public String saveCheckList(@RequestBody(required = true) List<CheckList> list){
		try {
			checkListService.saveCheckList(list);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			//logger.error("自检记录保存失败！", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	/**
	 * 报验单重置
	 * @return
	 */
	@RequestMapping(value = "/resetCheck")
	@ResponseBody
	public String resetCheck(@RequestBody(required = true) ProjectChecklist checkList){
		try {
			projectChecklistService.resetCheck(checkList);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
}
