package cc.dfsoft.project.biz.base.inspection.controller;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.inspection.dao.ProjectChecklistDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.inspection.enums.InspectionClumEnum;
import cc.dfsoft.project.biz.base.inspection.enums.ProjectChecklistTypeEnum;
import cc.dfsoft.project.biz.base.inspection.service.ProjectChecklistService;
import cc.dfsoft.project.biz.base.project.dao.SignatureDao;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/** 
 * 工程报验资料
 * * @author  wmy
 * *作者 E-mail: 
 * * @date 创建时间：2017年11月27日 上午11:37:17 
 * * @version 1.0 
 * * @parameter  
 * * @since  
 * * @return  
 * 
 * */
@Controller
@RequestMapping(value="/inspectionData")
public class InspectionDataController {
	@Resource
	ProjectChecklistService proCheckListService;
	
	@Resource
	ProjectChecklistDao projectChecklistDao;
	
	@Resource
	SignatureDao signatureDao;
	@RequestMapping(value="/main")
	public ModelAndView main(){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.addObject("actionName","inspectionData");//controller路径名
		//modelAndView.addObject("checkType",ProjectChecklistTypeEnum.ELECTRODE_RECORD.getValue());
		modelAndView.addObject("imgUrl",Constants.DISK_PATH+Constants.SIGN_DISK_PATH); 	//img url
		modelAndView.addObject("inspectionClumEnum",InspectionClumEnum.values()); //报检状态
		modelAndView.addObject("recorderPost",PostTypeEnum.RECORDER.getValue()); //记录人
		modelAndView.addObject("reviewerPost",PostTypeEnum.RECORDER.getValue()); //复核人
		
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		modelAndView.addObject("loginPost",loginInfo.getPost()); 			     //当前登录人职务
				
		modelAndView.addObject("builderPost",PostTypeEnum.BUILDER.getValue());  	//现场代表
		modelAndView.addObject("sujgjPost",PostTypeEnum.SUJGJ.getValue()); 			//现场监理
		modelAndView.setViewName("inspection/electionData");
		return modelAndView;
	}
	//查询列表
	@RequestMapping(value="queryList")
	@ResponseBody
	public Map<String,Object> queryCheckList(HttpServletRequest request,ProjectChecklistQueryReq proCheckListReq){
		
		try {
			proCheckListReq.setSortInfo(request);
			//proCheckListReq.setFlag(CheckFlagEnum.FLAG_COMPLETE.getValue());//已经完成
			Map<String, Object> map = proCheckListService.queryPrProjectChecklist(proCheckListReq);
			return map;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	//查询详述
	@RequestMapping(value="viewCheckList")
	@ResponseBody
	public ProjectChecklist viewCheckList(String id){
		try {
			//借用类型字段做url标识，赋值cpt名字
			//需要审核签字的报验单
			/*当签字表中无签字数据时，找不到菜单ID，报表名称会查询失败！！！！！---解决方案：1、新建一张表，录入菜单ID和PcDesId,按PcDesId查询出菜单ID,
			 * 或者在常量表中新增按PcDesId录入报表。不过报验资料基本是已经签字了的，所以很少情况会出现报验单无签字数据等情况，基于这个原因所以未做改进。留做后期改进。
			 * */
			List<ProjectChecklist> pcList = projectChecklistDao.getByPcId(id);   //根据pcID取得记录
			List<Signature> signature = signatureDao.findByBIdAndMenuId(pcList.get(0).getPcId(),null);   //从签字表中根据业务ID获取菜单Id,会出现的错误，当签字表中无签字数据时，找不到菜单ID，报表名称会查询失败！！！！！
			String type = null;  //菜单ID
			if(signature !=null && signature.size() > 0){  //判断签字表中是否有数据
				for (Signature sn : signature) {  //循环遍历记录
					if(!("1301201".equals(sn.getMenuId()))){  //若菜单ID不等于报验审核菜单Id
						type = sn.getMenuId();   //取得菜单ID
						break;   //结束循环
					}
				}
			}
			//防腐记录
			if(StringUtil.isBlank(type) && pcList != null && pcList.size() > 0 && ProjectChecklistTypeEnum.PRESERVATIVE.getValue().equals(pcList.get(0).getPcDesId())){
				type = Constants.PRESERVATIVE;  //防腐记录菜单ID
			}
			//管沟开挖
			if(StringUtil.isBlank(type) && pcList != null && pcList.size() > 0 && ProjectChecklistTypeEnum.GROOVE_RECORD.getValue().equals(pcList.get(0).getPcDesId())){
				type = Constants.GROOVE_RECORD;  //管沟开挖菜单ID
			}
			List<Map<String,Object>> cons = Constants.getConstantsMapByKey(Constants.AYDIT_INS);
			boolean boo = false;   //默认false,表示加载默认报验单
			Map<String,Object> map = null;
			for(Map<String,Object> m : cons){
				if(pcList.get(0).getPcDesId().equals(String.valueOf(m.get("RESERVE1")))){
					//查询是否有新报验单
					boo = true;
					break;
				}
			}
			//根据工程id和报验单类型、完成状态查询报验单列表
			String result ="";
			if (pcList==null || pcList.size() < 1){  //无报验记录返回null
				 return null;
				 }
			//遍历报验单
			for(ProjectChecklist pc : pcList){
				String reserve1="";  
				if(boo){  //true查找新报表,false查找默认报表
					if(StringUtil.isNotBlank(pc.getFinishedDate())){  //记录完成时间不为空时，根据最早一次完成时间，查找报表
						String [] dates = pc.getFinishedDate().split(",");   //按逗号分隔开完成时间
						if(DateUtil.getDaySub(DateUtil.StringToDate(Constants.UPGRADE_DATE),DateUtil.StringToDate(dates[dates.length-1]))>0){  //判断完成时间是否大于固定时间，若大于则查找新报验单
							reserve1 =Constants.UPGRADE_DATE;   //根据时间查询最新报表
						}
					}
				}
				map = Constants.getConsData(Constants.COMPLETION_DATA,type,reserve1);  //查找报表，根据时间，菜单ID,更新时间
				String arrayStr = "";
				if(map !=null){
					arrayStr = String.valueOf(map.get("CNVALUE"));
				}else{
					if(boo){
						map = Constants.getConsData(Constants.COMPLETION_DATA,type,Constants.UPGRADE_DATE);
					}else{
						map = Constants.getConsByKeyAndId(Constants.COMPLETION_DATA,type);
					}
					if(map != null){
						arrayStr = String.valueOf(map.get("CNVALUE"));
					}
				}
				
				//使用JSONArray
				net.sf.json.JSONArray jsonArray=net.sf.json.JSONArray.fromObject(arrayStr);
				net.sf.json.JSONObject jsonObject = null;
				CompletionDataPrintDto dto = null;
				JSONObject json = null;
				//遍历cpt
		        if(jsonArray.size() > 1){
		    		for(Object obj:jsonArray){
						 jsonObject=net.sf.json.JSONObject.fromObject(obj);
					     dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
						//记录类型与cpt类型相同
						//cpt的type不为空，则需要记录类型与cpt类型相同
						if(StringUtil.isNotBlank(dto.getType())){//cpt的type不为空，判断
							result = null;   //重置为null
							if(pcList.get(0).getPcDesId().equals(ProjectChecklistTypeEnum.PRESERVATIVE.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐记录
								 pcList.get(0).setPcDesId(dto.getReportlet());
							}else if(pcList.get(0).getPcDesId().equals(ProjectChecklistTypeEnum.PRESERVATIVE_INPECT.getValue()) && StringUtil.isNotBlank(pc.getPreservativeType()) && pc.getPreservativeType().equals(dto.getType())){//防腐检查
								 pcList.get(0).setPcDesId(dto.getReportlet());
							}else if(pcList.get(0).getPcDesId().equals(ProjectChecklistTypeEnum.PE_WELD_LINE_INPECT.getValue()) && pc.getMeltConnectType().equals(dto.getType())){//pe管焊缝检查
								 pcList.get(0).setPcDesId(dto.getReportlet());
							}else if(pcList.get(0).getPcDesId().equals(ProjectChecklistTypeEnum.STRENGTH_TEST.getValue()) && pc.getStPipeType().equals(dto.getType())){//强度试验
								 pcList.get(0).setPcDesId(dto.getReportlet());
							}
						
						}
					}
		        }else{
		        	 jsonObject=net.sf.json.JSONObject.fromObject(jsonArray.get(0));
				     dto = (CompletionDataPrintDto)net.sf.json.JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
				     pcList.get(0).setPcDesId(dto.getReportlet());
		        }
			}
			return  pcList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	//查询弹窗
	public ModelAndView searchPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("inspection/electionSearchPage");
		return modelView;
	}


	//查询弹窗
	@RequestMapping(value = "/inspectionResetPopPage")
	public ModelAndView inspectionResetPopPage(){
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("inspection/inspectionResetPopPage");
		return modelView;
	}

}
