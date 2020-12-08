package cc.dfsoft.project.biz.base.baseinfo.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.baseinfo.service.SystemSetService;
import cc.dfsoft.project.biz.base.common.entity.SystemSet;
import cc.dfsoft.project.biz.base.common.enums.AssociateTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptTypeEnum;
import cc.dfsoft.uexpress.biz.sys.dept.service.DepartmentService;
import cc.dfsoft.uexpress.common.constant.Constants;
@Controller
@RequestMapping(value="/systemSet")
public class SystemSetController {
	/** 日志实例 */
	private static Logger logger=LoggerFactory.getLogger(SystemSetController.class);
	@Resource
	SystemSetService systemSetService;
	
	/**部门服务接口*/
	@Resource
	DepartmentService departmentService;
	
	/**
	 * 打开主页面
	 * @author zhangjj
	 *  
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelView=new ModelAndView();	
		modelView.addObject("associateTypeEnum", AssociateTypeEnum.values());
		modelView.addObject("corp",departmentService.findByType(DeptTypeEnum.SUBCOMPANY.getValue()));//分公司
		modelView.setViewName("baseinfo/systemSet/systemSet");
		return modelView;
	}
	
	@RequestMapping(value = "/saveData")
	@ResponseBody
	public String saveData(@RequestBody SystemSet sys){
		
		try {
			systemSetService.saveSystemSet(sys);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.OPERATE_RESULT_FAILURE;
	}
	
	@RequestMapping(value = "/findDataByMenuId")
	@ResponseBody
	public SystemSet findDataByMenuId(String menuId) throws ParseException {
		LoginInfo login=SessionUtil.getLoginInfo();
		return systemSetService.querySystemSet(menuId,login.getCorpId());
	}

	
	@RequestMapping(value = "/findAssociateByMenuId")
	@ResponseBody
	public Object findAssociateByMenuId(String menuId) throws ParseException {
		
		try {
			LoginInfo login=SessionUtil.getLoginInfo();
			SystemSet ss=systemSetService.querySystemSet(menuId,login.getCorpId());
			if(ss!=null){
				String ats= ss.getAssociateType();
				if(ats!=null){
					Map map=new HashMap<String,String>();
					String[] strs=ats.split(",");
					if(strs.length>0){
					for(int i=0;i<strs.length;i++){
					map.put(i+1, strs[i]);
					}
					}
					return strs;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
