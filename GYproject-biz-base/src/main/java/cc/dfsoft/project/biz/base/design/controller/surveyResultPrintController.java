package cc.dfsoft.project.biz.base.design.controller;


import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.design.dto.SurveyInfoQueryReq;
import cc.dfsoft.project.biz.base.design.service.surveyResultPrintService;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;



/**
 * 勘察结果打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/surveyResultPrint")
public class surveyResultPrintController {

    private static Logger logger= LoggerFactory.getLogger(surveyResultPrintController.class);
    @Autowired
    private surveyResultPrintService surveyResultPrintService;
    
    /**工程服务接口*/
    @Resource
    ProjectService projectService;

    /**
     * 打开主页面
     * @author fuliwei
     * @createTime 2017年7月22日
     * @param
     * @return
     */
    @RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
        modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
        //modelView.addObject("url", String.valueOf(Constants.getSysConfigByKey("anShun_surveyResultPrint")));//报表地址
        modelView.addObject("imgUrl", Constants.DISK_PATH+Constants.SIGN_DISK_PATH);//签字图片地址
        modelView.setViewName("design/surveyResultPrint");
        return modelView;
    }

    /**
     * 查询现场踏勘打印数据
     * @param request
     * @param
     * @return
     */
    @RequestMapping(value = "/getSurveyResult")
    @ResponseBody
    public Map<String,Object> getSurveyResult(HttpServletRequest request, SurveyInfoQueryReq surveyInfoQueryReq){
        try {
            Map<String,Object> map=surveyResultPrintService.getSurveyResult(surveyInfoQueryReq);
            return map;
        } catch (Exception e) {
            logger.error("现场踏勘打印数据查询失败！", e);
            return null;
        }
    }

    /**
     * 打印标记
     * @param
     * @return
     */
    @RequestMapping(value = "/sign")
    @ResponseBody
    public String sign(@RequestBody(required = true) String id){
        try {
            return surveyResultPrintService.sign(id);
        } catch (Exception e) {
            logger.error("踏勘打印标记失败！", e);
            return Constants.OPERATE_RESULT_FAILURE;
        }
    }
    /**
     * 弹屏查询
     * @return
     */
    @RequestMapping(value = "/searchPopPage")
    public ModelAndView subContractSearchPopPage() {
        ModelAndView modelview = new ModelAndView();
        modelview.addObject("isPrint", ContractIsPrintEnum.values());//是否打印
        modelview.setViewName("design/searchPopPage");
        return modelview;
    }
    
    /**
     * 
     * @author fuliwei  
     * @date 2018年11月7日  
     * @version 1.0
     */
    @RequestMapping(value="viewSurveyReport",method = RequestMethod.POST)
	@ResponseBody
	public String viewContractReport(String projId, String menuId){
		String cptUrl = "";
		//获取工程信息
		Project project = projectService.queryProjectById(projId);
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String corpId = loginInfo.getCorpId();//默认当前用户的公司ID
		String projType = ProjLtypeEnum.CIVILIAN.getValue();//默认民用类型工程
		if(project!=null){
			corpId = project.getCorpId();
			projType = project.getProjectType();
		}
		
		//组装key值得到信息
		String key = projType + "_" + corpId + "_" + menuId;
		Object obj = Constants.getSysConfigByKey(key);
		if(obj == null){
			return cptUrl;
		}
		return obj.toString();
		
	}

/**
 * @methodDesc: cpt模板查询
 * @author: zhangnx
 * @date: 15:07 2018/12/6
 */
    @RequestMapping(value = "/queryCptUrl")
    @ResponseBody
    public String queryCptUrl(String projId,String menuId){
        LoginInfo loginInfo= SessionUtil.getLoginInfo();
        Project pro=projectService.queryProjectById(projId);

        String ctmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+(pro!=null?pro.getProjectType(): ProjLtypeEnum.PUBLIC.getValue())+"_"+menuId;//分公司+类型配置
        String cmKey=(pro!=null?pro.getCorpId():loginInfo.getCorpId())+"_"+menuId;//分公司默认配置
        String defaultCPT="surveyResultPrint.cpt";//全局默认

        return queryCPT(ctmKey)!=null?queryCPT(ctmKey).toString():queryCPT(cmKey)!=null?queryCPT(cmKey).toString():defaultCPT;
    }

    public Object queryCPT(String key){
        return Constants.getSysConfigByKey(key);
    }

}
