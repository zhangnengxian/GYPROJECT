package cc.dfsoft.project.biz.base.constructmanage.controller;

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

import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutdownApproval;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownPushStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownApprovalService;
import cc.dfsoft.project.biz.base.constructmanage.service.ShutdownRecordService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 停复工
 * @author liaoyq
 */
@Controller
@RequestMapping("/shutdownRecord")
public class ShutdownRecordController {
	/** 日志实例 */
	public static Logger logger = LoggerFactory.getLogger(ShutdownRecordController.class);
	@Resource
	private ShutdownRecordService shutdownRecordService;
	@Resource
	private ShutdownApprovalService shutdownApprovalService;
	/**
	 * 打开主页面
	 * @return
	 */
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("loginPost", SessionUtil.getLoginInfo().getPost());
		modelAndView.addObject("shutdownTypeEnum",ShutdownTypeEnum.values());//停复工类型
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("cuManagerPost",PostTypeEnum.CU_MANAGER);//施工经理
		modelAndView.addObject("sucesPost",PostTypeEnum.SUCSE);//总监理工程师
		//监理停复工页面
		modelAndView.setViewName("constructmanage/shutdownRecord");
		return modelAndView;
	}
	
	@RequestMapping(value="queryShutdownRecord")
	@ResponseBody
	public Map<String,Object> queryShutdownRecord(HttpServletRequest request,ShutdownRecordReq shutdownRecordReq){
		try {
			shutdownRecordReq.setSortInfo(request);
			shutdownRecordReq.setPushStatus(ShutdownPushStatusEnum.NO_PUSH.getValue());
			Map<String, Object> map = shutdownRecordService.queryShutdownRecord(shutdownRecordReq);
			return map;
		} catch (Exception e) {
			logger.error("停复工列表查询失败!", e);
			return null;
		}
		
	}
	
	/**
	 * 弹出查询屏
	 * @return
	 */
	@RequestMapping(value="/viewSearchPopPage")
	@ResponseBody
	public ModelAndView viewRectifyNoticeSearchPopPage(HttpServletRequest request,ShutdownRecordReq shutdownRecordReq){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("constructmanage/shutdowRecordSearchPopPage");
		return modelAndView; 
	}
	/**
	 * 查询停复工详述
	 * @return
	 */
	@RequestMapping(value="viewShutdownRecord")
	@ResponseBody
	public ShutDownRecord viewShutdownRecord(HttpServletRequest request,@RequestParam(required=true) String id){
		ShutDownRecord downRecord = shutdownRecordService.queryById(id);
		if(downRecord!=null){
			if(downRecord.getSdrStatus().toString().equals(ShutdownStatusEnum.REWORK_ORDER.getValue())){
				ShutdownApproval approval = shutdownApprovalService.queryBySdrId(downRecord.getSdrId());
				if(approval!=null && approval.getReworkDate()!=null){
					downRecord.setReWorkDate(approval.getReworkDate());
				}
			}
		}
		
		
		return downRecord;
	}
	
	@RequestMapping(value="/saveSign")
	@ResponseBody
	public String saveSign(HttpServletRequest request,@RequestBody(required = true)ShutDownRecord shutDownRecord){
		try{
			String msg="";
			System.err.println("---"+shutDownRecord.getSdrType()+"====" + ShutdownTypeEnum.SHUTDOWN.getValue());
			
			/*Integer resCount =0;
			//根据工程id、工序、停工编号、停复工状态查询
			if(!StringUtils.isNoneBlank(shutDownRecord.getSdrId())){
				resCount = shutdownRecordService.queryByCondition(shutDownRecord);
			}
			if(resCount>0){
				logger.error("该工程的此工序" +msg+ "已存在");
				return "exist";
			}*/
			if(ShutdownTypeEnum.SHUTDOWN.getValue().equals(shutDownRecord.getSdrType())){
				shutDownRecord.setSdrStatus(Integer.parseInt(ShutdownStatusEnum.SHUTDOWN_ORDER.getValue()));//工程处于停工状态
				msg = "停工令";
			}else if(ShutdownTypeEnum.REWORK.getValue().equals(shutDownRecord.getSdrType())){
				shutDownRecord.setSdrStatus(Integer.parseInt(ShutdownStatusEnum.PUSH_REWORK_ORDER.getValue()));//工程处于待下达复工令状态
				//修改停工记录状态
				shutdownRecordService.updateStatusBySdrNo(shutDownRecord.getSdrNo(), ShutdownTypeEnum.SHUTDOWN.getValue(),Integer.parseInt(ShutdownStatusEnum.PUSH_REWORK_ORDER.getValue()));
				msg = "复工令";
			}
			String sdrId = shutdownRecordService.saveShutdownRecord(shutDownRecord);
			return sdrId;
			
		}catch(Exception e){
			request.getSession().setAttribute("singtureType","error");
			logger.error("保存停复工信息失败!", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	@RequestMapping(value="/pushShutdownRecord")
	@ResponseBody
	public String pushShutdownRecord(HttpServletRequest request,@RequestBody(required = true)ShutDownRecord shutDownRecord){
		try {
			shutdownRecordService.pushShutdownRecord(shutDownRecord);
			return Constants.OPERATE_RESULT_SUCCESS;
		} catch (Exception e) {
			request.getSession().setAttribute("singtureType","error");
			logger.error("推送停复工信息失败!", e);
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}
	
	/**
	 * 删除停复工记录
	 * create person wanghuijun
	 * 2019年5月31
	 * @return
	 */
	@RequestMapping(value = "/deleteSDRecordBySDRId")
	@ResponseBody
	public String  deleteSDRecordBySDRId(@RequestBody(required = true)ShutDownRecord shutDownRecord) {
		try {
			return shutdownRecordService.deleteSDRecordBySDRId(shutDownRecord);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Constants.OPERATE_RESULT_FAILURE;
		}
		
		
	}
}
