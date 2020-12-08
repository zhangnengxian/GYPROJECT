package cc.dfsoft.project.biz.base.design.controller;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.service.DesignInfoService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value="/designDrawingPrint")
public class DesignDrawingPrintController {

    /** 日志实例 */
    private static Logger logger=LoggerFactory.getLogger(DesignDrawingPrintController.class);

    /**工程服务接口*/
    @Resource
    ProjectService projectService;
    @Autowired
    DesignInfoService designInfoService;

    /**
     * 打开主页面
     * @return
     */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
        modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
        modelView.setViewName("design/designDrawingPrint");
        return modelView;
    }

    /**
     * 工程列表条件查询
     * @param designerQueryReq
     * @author
     * @createTime
     * @return
     */
    @RequestMapping(value = "/queryProject")
    @ResponseBody
    public Map<String,Object> queryProject(HttpServletRequest request, DesignerQueryReq designerQueryReq ){
        try {
            return designInfoService.getDataList(designerQueryReq);
        } catch (Exception e) {
            logger.error("工程信息查询失败！", e);
            return null;
        }
    }

    @RequestMapping(value = "/queryCptUrl")
    @ResponseBody
    public String queryCptUrl(String projId,String menuId){
        LoginInfo loginInfo= SessionUtil.getLoginInfo();
        Project project=projectService.queryProjectById(projId);
        String key = "";
        if(project != null){
            key = menuId+"_"+project.getProjectType();
        }
        String defaultCpt = "";
        defaultCpt = Constants.getSysConfigByKey(key)!=null?Constants.getSysConfigByKey(key).toString():defaultCpt;
        return defaultCpt;
    }



    /**
     * 弹屏查询
     * @return
     */
    @RequestMapping(value = "/scSearchPopPage")
    public ModelAndView subContractSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        //modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
        modelview.setViewName("design/dSearchPopPage");
        return modelview;
    }

}
