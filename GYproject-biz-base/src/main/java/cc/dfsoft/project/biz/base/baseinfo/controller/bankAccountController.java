package cc.dfsoft.project.biz.base.baseinfo.controller;

import cc.dfsoft.project.biz.base.baseinfo.dto.BankQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.AccountBank;
import cc.dfsoft.project.biz.base.baseinfo.service.BankService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 银行账号控制层
 * @Auther: zhangnx
 * @Date: 2019/4/22 9:33
 * @Version:1.0
 */
@Controller
@RequestMapping(value="/bankAccount")
public class bankAccountController {

    @Resource
    BankService bankService;
    @Resource
    DepartmentService departmentService;



   /**
    * @MethodDesc: 功能描述
    * @Author zhangnx
    * @Date 2019/4/22 9:35
    */
   @RequestMapping(value="/main")
   public ModelAndView main(ModelAndView model){
       model.setViewName("baseinfo/bankaccount/bankaccountMain");
       return model;
   }

    /**
     * @MethodDesc: 银行账号MapList查询
     * @Author zhangnx
     * @Date 2019/4/22 9:39
     */
    @RequestMapping(value = "/queryBankAccountMapList")
    @ResponseBody
    public Map<String, Object> queryBankAccountMapList(BankQueryReq req) {
        if (StringUtil.isBlank(req.getCorpId())) {
            req.setCorpId(SessionUtil.getLoginInfo().getCorpId());
        }

        Map<String, Object> map = bankService.queryBank(req);
        return map;
    }


    /**
     * @MethodDesc: 加载右侧操作区页面
     * @Author zhangnx
     * @Date 2019/4/22 9:41
     */
    @RequestMapping(value = "/viewRightPage")
    public ModelAndView viewRightPage(ModelAndView model) {
        model.addObject("departmentList",companyFilter());
        model.setViewName("baseinfo/bankaccount/bankaccountRight");
        return model;
    }





    public List<Department> companyFilter(){
        String corpId = SessionUtil.getLoginInfo().getCorpId();
        List<Department> reultList=new ArrayList<>();
        List<Department> departmentList = departmentService.findDepartmentByType();
        if (departmentList==null||departmentList.size()<1) return null;

        for (Department d:departmentList) {
            if (d.getDeptId().equals(corpId)){
                reultList.add(d);
            }
        }
        return (reultList!=null&&reultList.size()>0)?reultList:departmentList;
    }


    /**
     * @MethodDesc: 保存或修改
     * @Author zhangnx
     * @Date 2019/4/22 9:42
     */
    @RequestMapping(value = "/saveOrUpdateBankAccount")
    @ResponseBody
    public boolean saveOrUpdateBankAccount(@RequestBody(required = true)  AccountBank accountBank) {
        return bankService.saveOrUpdateBankAccount(accountBank);
    }

    /**
     * @MethodDesc: 删除
     * @Author zhangnx
     * @Date 2019/4/22 9:44
     */
    @RequestMapping(value = "/delBankAccountById")
    @ResponseBody
    public boolean delBankAccountById(String abId) {
        bankService.deleteOpenBank(abId);
       return false;
    }

    /**
     * @MethodDesc: 查询详细
     * @Author zhangnx
     * @Date 2019/4/22 9:45
     */
    @RequestMapping(value = "/viewBankAccountDetail")
    @ResponseBody
    public AccountBank viewBankAccountDetail(@RequestParam(required = true) String id) {
        return  bankService.viewOpenBankById(id);
    }

    /**
     * @MethodDesc: 条件查询弹出框
     * @Author zhangnx
     * @Date 2019/4/22 9:48
     */
    @RequestMapping(value = "/bankAccountPopPage")
    public ModelAndView bankAccountPopPage(ModelAndView model) {
        model.addObject("departmentList",companyFilter());
        model.setViewName("baseinfo/bankaccount/bankaccountPopPage");
        return model;
    }

}
