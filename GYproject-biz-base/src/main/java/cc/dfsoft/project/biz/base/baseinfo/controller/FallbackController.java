package cc.dfsoft.project.biz.base.baseinfo.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.CorrelationReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.WorkFlowReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.entity.Correlation;
import cc.dfsoft.project.biz.base.baseinfo.enums.*;
import cc.dfsoft.project.biz.base.baseinfo.service.CorrelationService;
import cc.dfsoft.project.biz.base.project.dto.MenuBackSetReq;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.project.biz.base.project.service.MenuBackSetService;
import cc.dfsoft.project.biz.base.project.service.ProjectTypeService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 回退配置
 */
@Controller
@RequestMapping(value="/fallback")
public class FallbackController {

    /** 日志实例 */
    private static Logger logger= LoggerFactory.getLogger(FallbackController.class);
    @Resource
    DepartmentService departmentService;
    @Resource
    ProjectTypeService projectTypeService;

    @Resource
    ContributionModeService contributionModeService;
    @Resource
    CorrelationService correlationService;
    @Autowired
    MenuBackSetService menuBackSetService;
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.setViewName("baseinfo/fallbackSet/fallback");
        return modelView;
    }


    @RequestMapping(value = "/getDataList")
    @ResponseBody
    public Map<String,Object> getDataList(HttpServletRequest request, MenuBackSetReq req){
        try {
            req.setSortInfo(request);

            if(StringUtils.isBlank(req.getCorpId())){
                LoginInfo loginInfo=SessionUtil.getLoginInfo();
                req.setCorpId(loginInfo.getCorpId());
            }
            Map<String,Object> map= menuBackSetService.getDataList(req);
            return map;
        } catch (Exception e) {
            logger.error("列表查询失败！", e);
            return null;
        }
    }

    @RequestMapping(value = "/viewPage")
    public ModelAndView viewPage(HttpServletRequest request) {
        ModelAndView modelview = new ModelAndView();
        modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
        modelview.addObject("projType",projectTypeService.queryAllList());//工程类型
        modelview.addObject("contributionMode",contributionModeService.queryAllList());//出资方式
//        modelview.addObject("allType", correlationService.queryAssistWorkFlow());
        modelview.addObject("stepId", StepEnum.values());//步骤
        modelview.setViewName("baseinfo/fallbackSet/fallbackRight");
        return modelview;

    }
    @RequestMapping(value="/getDataById")
    @ResponseBody
    public MenuBackSet getDataById(@RequestParam(required=true) String id) {
        MenuBackSet menuBackSet =  menuBackSetService.getDataById(id);
        return menuBackSet;
    }

    @RequestMapping(value = "/getStepDataList")
    @ResponseBody
    public Map<String,Object> getStepDataList(HttpServletRequest request,MenuBackSetReq req){
        try {
            Map<String,Object> map =menuBackSetService.getStepDataList(req);
            return map;
        } catch (Exception e) {
            logger.error("列表查询失败！", e);
            return null;
        }
    }

    @RequestMapping(value = "/saveUpdateData")
    @ResponseBody
    public String saveUpdateData(@RequestBody MenuBackSet menuBackSet){
        try {
            return  menuBackSetService.saveUpdateData(menuBackSet);
        } catch (Exception e) {
            logger.error("保存失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }


    @ResponseBody
    @RequestMapping(value = "/deleteData")
    public String deleteData(String id){
        try{
            return menuBackSetService.deleteData(id);
        }catch(Exception e){
            logger.error("删除失败！",e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }

    @RequestMapping(value = "/fallbackSetSearchPopPage")
    public ModelAndView projectSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
        modelview.addObject("projType",projectTypeService.queryAllList());							//工程类型
        modelview.addObject("contributionMode",contributionModeService.queryAllList());				//出资方式
        modelview.setViewName("baseinfo/fallbackSet/fallbackSetSearchPopPage");
        return modelview;
    }
}
