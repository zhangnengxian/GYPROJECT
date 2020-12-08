package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.third.org.apache.poi.hssf.record.formula.functions.False;

import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.DailyLogDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.dto.DailyLogQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DailyLog;
import cc.dfsoft.project.biz.base.constructmanage.service.DailyLogService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DailyLogServiceImpl implements DailyLogService{

	@Resource
	DailyLogDao dailyLogDao;
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	SignatureService signatureService;
	
	@Override
	public Map<String, Object> queryDailyLog(DailyLogQueryReq dailyLogQueryReq) {
		return dailyLogDao.queryDailyLog(dailyLogQueryReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public Map<String, Object> saveDailyLog(DailyLog dailyLog) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(dailyLog.getDlId())){
			flag = true;
			dailyLog.setDlId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			dailyLog.setDlRecorderId(loginInfo.getStaffId());
			dailyLog.setDlRecorderPost(loginInfo.getPost());
		}
		dailyLogDao.saveOrUpdate(dailyLog);
		
		Map<String,Object> imgurl = new HashMap<>();
		signatureService.saveOrUpdateSign("menuId+menuNane+11",dailyLog.getSign(), dailyLog.getProjId(), dailyLog.getDlId(), dailyLog.getClass().getName(),flag);
		return imgurl;
	}

	@Override
	public List<Object> findPrintDataByProjId(String projId,String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>(); 
		//根据工程ID查询施工日志信息
		//多条交底信息
		List<DailyLog> dls = dailyLogDao.findByProjId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//施工日志报表
		String arrayStr = CompletionDataPrintEnum.CU_DAIRY.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && dls!=null && dls.size()>0 && project !=null){
			JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			 String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			 String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			for(DailyLog dl : dls){
				 String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
				 Object reportVersion = Constants.getSysConfigByKey(key);
				   if(reportVersion !=null){
					   //记录特定字符索引  
					   int beginIndex = dto.getReportlet().indexOf("/"); 
					   int endIndex = dto.getReportlet().lastIndexOf("/");
				       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				   }
				result = "{reportlet:'"+dto.getReportlet()+"',projId:'"+dl.getProjId()+ "',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH +"',dlId:'"+dl.getDlId()+ "'}";
				list.add(result);
			}
		}
		return list;
	}
	/**
	 * 删除工程日志，根据dlId
	 * @param dlId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void byDlIdDeletedialy(String dlId) throws Exception {
		// TODO Auto-generated method stub
		 dailyLogDao.delete(dlId);;
	}
	
}
