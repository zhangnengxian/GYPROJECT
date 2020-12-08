package cc.dfsoft.project.biz.base.maintain.controller;

import cc.dfsoft.project.biz.base.common.entity.TSysConstants;
import cc.dfsoft.project.biz.base.common.service.TSysConstantsService;
import cc.dfsoft.project.biz.base.maintain.dto.ImgResultDto;
import cc.dfsoft.project.biz.base.maintain.dto.ProblemDocumentReq;
import cc.dfsoft.project.biz.base.maintain.entity.ProblemDocument;
import cc.dfsoft.project.biz.base.maintain.service.ProblemDocumentService;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.auth.service.MenuService;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.BeanExcel;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Desc: 问题单据控制层
 * @Auther: zhangnx
 * @Date: 2019/1/21 14:38
 * @Version:1.0
 */

@Controller
@RequestMapping(value = "/problemDocument")
public class ProblemDocumentController {

    @Resource
    ProblemDocumentService problemDocumentService;
    @Resource
    DepartmentService departmentService;
    @Resource
    MenuService menuService;
    @Resource
    TSysConstantsService tSysConstantsService;

    private final String tableName="problem_document";
    /**
     * @MethodDesc: 返回主页面
     * @Author zhangnx
     * @Date 2019/1/21 14:48
     * @Param
     * @Return
     */
    @RequestMapping(value = "/main")
    public ModelAndView main(ModelAndView modelView) {
        modelView.setViewName("maintain/problemDocumentMain");
        return modelView;
    }


    /**
     * @MethodDesc: 问题单据查询List
     * @Author zhangnx
     * @Date 2019/1/21 16:51
     */
    @RequestMapping(value = "/queryProblemDocumentList")
    @ResponseBody
    public Map<String, Object> queryProblemDocumentList(ProblemDocumentReq probDoctReq) {
        CollectionUtils.setSysCodeDescList(null);
        Map<String, Object> stringObjectMap = problemDocumentService.queryProblemDocumentList(probDoctReq);
        return stringObjectMap;
    }





    /**
     * @MethodDesc: 加载右侧操作区页面
     * @Author zhangnx
     * @Date 2019/1/22 14:16
     */
    @RequestMapping(value = "/viewRightPage")
    public ModelAndView viewRightPage(ModelAndView model) {
        ModelAndView modelView = addModelview(model);
        modelView.setViewName("maintain/problemDocumentRight");
        return modelView;
    }


    /**
     * @MethodDesc: 查询子菜单
     * @Author zhangnx
     * @Date 2019/1/22 14:18
     */
    @RequestMapping(value = "/findChildMenuList")
    @ResponseBody
    public List<Menu> findChildMenuList(String menuId) {
        List<Menu> menuList = menuService.findChildMenuList(menuId);
        return menuList;
    }


    /**
     * @MethodDesc: 保存或修改
     * @Author zhangnx
     * @Date 2019/1/22 14:18
     */
    @RequestMapping(value = "/saveProblemDocument")
    @ResponseBody
    public boolean saveOrUpdateProblemDocument(@RequestBody(required = true) ProblemDocument problemDoc) {
        return problemDocumentService.saveOrUpdateProblemDocument(problemDoc);
    }


    /**
     * @MethodDesc: 删除
     * @Author zhangnx
     * @Date 2019/1/22 14:18
     */
    @RequestMapping(value = "/deleteProblemDocumentById")
    @ResponseBody
    public boolean deleteProblemDocumentById(String pdId) {
        return problemDocumentService.deleteProblemDocumentById(pdId);
    }

    /**
     * @MethodDesc: 查询详细
     * @Author zhangnx
     * @Date 2019/1/23 9:17
     */
    @RequestMapping(value = "/viewProblemDocumentDetail")
    @ResponseBody
    public ProblemDocument viewProblemDocumentDetail(@RequestParam(required = true) String id) {
        ProblemDocument problemDocument = problemDocumentService.viewProblemDocumentDetail(id);
        return problemDocument;
    }


    /**
     * @MethodDesc: 条件查询弹出框
     * @Author zhangnx
     * @Date 2019/1/24 17:56
     */
    @RequestMapping(value = "/problemDocumentPopPage")
    public ModelAndView problemDocumentPopPage(ModelAndView model) {
        ModelAndView modelView = addModelview(model);
        modelView.setViewName("maintain/problemDocumentPopPage");
        return modelView;
    }



    public ModelAndView addModelview(ModelAndView model) {
        model.addObject("departmentList", departmentService.findDepartmentByType());//公司查询
        model.addObject("level2MenuList", menuService.findlevel2MenuList());//模块(二级菜单)
        model.addObject("problemTypeList", CollectionUtils.getSysCodeDescList(tableName, "problemType"));//问题类型
        model.addObject("problemStateList", CollectionUtils.getSysCodeDescList(tableName, "problemState"));//问题状态
        model.addObject("emergencyLevelList", CollectionUtils.getSysCodeDescList(tableName, "emergencyLevel"));//问题紧急程度
        model.addObject("handlerList", CollectionUtils.getSysCodeDescList(tableName, "handler"));//处理人
        return model;
    }


    /**
     * @MethodDesc: 导出
     * @Author zhangnx
     * @Date 2019/1/24 17:55
     */
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(ProblemDocumentReq probDoctReq, HttpServletResponse response) {
        CollectionUtils.setSysCodeDescList(null);
        List<ProblemDocument> problemDocumentList = problemDocumentService.exportExcelList(probDoctReq);

        boolean b=false;
        BeanExcel<ProblemDocument> beanExcel = new BeanExcel<>();

        TSysConstants tSysConstants = tSysConstantsService.findTSysConstantsById("problem_document_20190121");
        if (tSysConstants!=null && StringUtils.isNotBlank(tSysConstants.getCnvalue())){

           boolean isHeadersCode="true".equals(tSysConstants.getReserve1())?true:false;

            b = beanExcel.exportExcel("工程管理系统问题单据", tSysConstants.getCnvalue(), problemDocumentList, response,isHeadersCode);

        }else {
            b = beanExcel.exportExcel("工程管理系统问题单据", null, problemDocumentList, response);
        }

        return b?"导出完成！":"请检查配置导出字段是否正确;格式为json数组";
    }


    /**
     * @MethodDesc: 导入
     * @Author zhangnx
     * @Date 2019/1/24 18:01
     */
    @RequestMapping(value = "/importExcel")
    @ResponseBody
    public String importExcel(@RequestParam(value = "uploadfile") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {

        BeanExcel beanExcel = new BeanExcel<>();
        List<ProblemDocument> problemDocumentList = beanExcel.getExcelDataList(file, ProblemDocument.class);

        return null;
    }






/**
 * @MethodDesc: 富文本编辑器图片上传
 * @Author zhangnx
 * @Date 2019/3/1 20:42
 * RequestParam中的属性名称要和前端定义的一致，上面说明了．所以写"img"
 * 使用List<MultipartFile>进行接收
 * 返回的是一个Ｄto类，后面会说明，使用@ResponseBody会将其转换为Ｊson格式数据
 */
    @RequestMapping(value = "/uploadImg")
    @ResponseBody
    public ImgResultDto uploadEditorImg(@RequestParam("img") List<MultipartFile> fileList){
        ImgResultDto imgResultDto = new ImgResultDto();
        String[] urlData = new String[fileList!=null&&fileList.size()>0?fileList.size():0];

        int index = 0;
        for(MultipartFile img : fileList) {
            String fileName = img.getOriginalFilename();
            if(StringUtil.isBlank(fileName))continue;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            SimpleDateFormat ymsdf = new SimpleDateFormat("yyyyMM");
            String suffix = fileName.substring(fileName.lastIndexOf("."));
            String finalFileName = (sdf.format(new Date())+new Random().nextInt(1000)) +suffix; //文件名

            String ImgPath=Constants.DISK_PATH+"editorFile/"+ymsdf.format(new Date());
            File newfile = new File(ImgPath ,finalFileName);
            if (!newfile.getParentFile().exists())newfile.getParentFile().mkdirs();

            try {
                img.transferTo(newfile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            urlData[index++] = "attachments/editorFile/"+ymsdf.format(new Date())+"/"+finalFileName;
            imgResultDto.setErrno(0);
        }
        imgResultDto.setData(urlData);
        return imgResultDto;
    }


}
