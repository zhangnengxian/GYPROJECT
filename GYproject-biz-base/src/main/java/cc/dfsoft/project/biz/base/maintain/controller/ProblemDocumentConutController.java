package cc.dfsoft.project.biz.base.maintain.controller;

import cc.dfsoft.project.biz.base.common.entity.TSysConstants;
import cc.dfsoft.project.biz.base.common.service.TSysConstantsService;
import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.service.ProblemDocumentService;
import cc.dfsoft.uexpress.common.util.BeanExcel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 问题单据控制层
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:38
 * @Version:1.0
 */

@Controller
@RequestMapping(value = "/problemDocumentCount")
public class ProblemDocumentConutController {

    @Resource
    ProblemDocumentService problemDocumentService;
    @Resource
    TSysConstantsService tSysConstantsService;

    /**
     * @MethodDesc: 返回主页面
     * @Author zhangnx
     * @Date 2019/1/21 14:48
     * @Param
     * @Return
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView modelView) {
        modelView.setViewName("maintain/problemDocumentCountMain");
        return modelView;
    }

    /**
     * @MethodDesc: 问题单据统计查询
     * @Author zhangnx
     * @Date 2019/1/21 16:51
     */
    @RequestMapping(value = "/findProblemTypeConut")
    @ResponseBody
    public Map<String, Object> findProblemTypeConut(ProblemDocumentReq probDoctReq) {
        Map<String, Object> stringObjectMap = problemDocumentService.findProblemTypeConut(probDoctReq);
        return stringObjectMap;
    }

    /**
     * @MethodDesc: 导出
     * @Author zhangnx
     * @Date 2019/1/24 17:55
     */
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(ProblemDocumentReq probDoctReq, HttpServletResponse response) {
        Map<String, Object> stringObjectMap = problemDocumentService.findProblemTypeConut(probDoctReq);
        List<Map<String,Object>> mapList = (List<Map<String, Object>>) stringObjectMap.get("data");


        boolean b=false;
        BeanExcel<Map<String,Object>> beanExcel = new BeanExcel<>();

        TSysConstants tSysConstants = tSysConstantsService.findTSysConstantsById("problem_document_20190227");
        if (tSysConstants!=null && StringUtils.isNotBlank(tSysConstants.getCnvalue())){
             b = beanExcel.exportExcel("工程管理系统问题统计", tSysConstants.getCnvalue(), mapList, response);

        }else {
            b = beanExcel.exportExcel("工程管理系统问题统计", null, mapList, response);
        }

        return b?"导出完成！":"请检查配置导出字段是否正确;格式为json数组";
    }


}
