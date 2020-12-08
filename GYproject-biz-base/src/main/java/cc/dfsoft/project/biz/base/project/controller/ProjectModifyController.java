package cc.dfsoft.project.biz.base.project.controller;

import cc.dfsoft.project.biz.base.design.enums.UnitTypeEnum;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.ResultInfo;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Desc: 工程信息修改Controller
 * @Auther: zhangnx
 * @Date: 2019/4/15 15:59
 * @Version:1.0
 */
@Controller
@RequestMapping(value = "/projectModify")
public class ProjectModifyController {


    @Resource
    ProjectService projectService;
    @Resource
    ScaleDetailService scaleDetailService;
    @Resource
    OperateRecordService operateRecordService;



    /**
     * @MethodDesc: 主页
     * @Author zhangnx
     * @Date 2019/4/15 17:23
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView modelView) {
        modelView.setViewName("project/modify/projectMain");
        return modelView;
    }


    /**
     * @MethodDesc: 工程列表查询
     * @Author zhangnx
     * @Date 2019/4/15 17:22
     */
    @RequestMapping(value = "/queryProjectMap")
    @ResponseBody
    public Map<String, Object> queryProjectMap(ProjectQueryReq probDoctReq) {
        probDoctReq.setCorpId(SessionUtil.getLoginInfo().getCorpId());
        return projectService.queryProjectMap(probDoctReq);
    }


    /**
     * @MethodDesc: 右侧详细页面p
     * @Author zhangnx
     * @Date 2019/4/15 17:23
     */
    @RequestMapping(value = "/viewRightPage")
    public ModelAndView viewRightPage(ModelAndView model) {
        model.addObject("unitType", UnitTypeEnum.GAS_COMPANY.getValue());	// 燃气集团
        model.addObject("designerPost", PostTypeEnum.DESIGNER.getValue());	// 设计员
        model.addObject("surveyPost", PostTypeEnum.SURVEYER.getValue());	// 踏勘员
        model.addObject("surveyBuilderPost", PostTypeEnum.BUILDER.getValue());	//现场代表
        model.setViewName("project/modify/projectRight");
        return model;
    }


    /**
     * @MethodDesc: 详细
     * @Author zhangnx
     * @Date 2019/4/15 17:31
     */
    @RequestMapping(value = "/viewProjectDetail")
    @ResponseBody
    public Project viewProjectDetail(@RequestParam(required = true) String id) {
        Project project = projectService.queryProjectById(id);
        return project;
    }


    /**
     * @MethodDesc: 条件查询弹出框
     * @Author zhangnx
     * @Date 2019/1/24 17:56
     */
    @RequestMapping(value = "/projctPopPage")
    public ModelAndView projctPopPage(ModelAndView model) {
        model.addObject("projStatusEnum", ProjStatusEnum.values());
        model.setViewName("project/modify/projectPopPage");
        return model;
    }


    /**
     * @MethodDesc: 修改
     * @Author zhangnx
     * @Date 2019/1/22 14:18
     */
    @RequestMapping(value = "/updateProjectRelationInfo")
    @ResponseBody
    public boolean updateProjectRelationInfo(@RequestBody(required = true) Project project) {
        return projectService.updateProjectRelationInfo(project);
    }



    /**
    * @ Description: 工程复位
    * @ author zhangnx
    * @ date 2019/11/20 15:33
    **/
    @RequestMapping(value = "/recoveryProject")
    @ResponseBody
    public ResultInfo recoveryProject(@RequestBody AbandonedRecord abandonedRecord) {

        return operateRecordService.recoveryProject(abandonedRecord);

    }


    /**
     * @MethodDesc: 规模明细查询
     * @Author zhangnx
     * @Date 2019/4/18 9:51
     */
    @RequestMapping(value = "/queryScaleDetail")
    @ResponseBody
    public Map<String,Object> queryScaleDetail(HttpServletRequest request,ScaleDetailQueryReq scaleDetailQueryReq){
        try {
            scaleDetailQueryReq.setSortInfo(request);
            Map<String, Object> map = scaleDetailService.queryScaleDetail(scaleDetailQueryReq);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
