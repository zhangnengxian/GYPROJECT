package cc.dfsoft.project.biz.base.inspection.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fr.third.org.apache.poi.hssf.record.formula.functions.True;

import cc.dfsoft.project.biz.base.complete.enums.CompletionDataPrintEnum;
import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;
import cc.dfsoft.project.biz.base.inspection.dao.MeasurementDao;
import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.project.biz.base.inspection.service.MeasurementService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 计量表实现类
 * @author wanghuijun
 * @createTime 2018年9月17日
 */
@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class MeasurementServiceImpl implements MeasurementService{
	
	
	@Resource
	MeasurementDao measurementDao;
	
	
	@Resource
	ProjectDao projectDao;
	
	/**
	 * 根据工程Id查询计量表
	 * @author wanghuijun
	 * @createTime 2018年9月17日
	 * @return
	 */
	@Override
	public Map<String, Object> queryMeasurement(MeasurementReq measurementReq) throws Exception {
		// TODO Auto-generated method stub
		return measurementDao.queryMeasurement(measurementReq);
	}
	
	/**
	 * 保存计量表
	 * @author wanghuijun
	 * @createTime 2018年9月18日
	 * @param measurement
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false ,propagation = Propagation.REQUIRED)
	public void savaMeasurement(Measurement measurement) throws Exception {
		// TODO Auto-generated method stub
        if(StringUtils.isBlank(measurement.getMsId())){   //判断msId是否为空，不为空则新增
        	measurement.setMsId(IDUtil.getUniqueId(Constants.MODULE_CODE_MEASUREMENT));
        }
        	 measurementDao.saveOrUpdate(measurement);   //保存计量记录
       
	}

	
	/**
	 * 根据计量表id查询计量表详述
	 * @author wanghuijun
	 * @createTime 2018年9月18日
	 * @param id
	 * @return
	 */
	@Override
	public Measurement viewMeasurement(String msId) throws Exception {
		// TODO Auto-generated method stub
		return measurementDao.get(msId);
	}

	
	/**
	 * 根据计量表msId删除记录
	 * @author wanghuijun
	 * @createTime 2018年9月19日
	 * @param msId
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public void byMsIdDeleteMeasurement(String msId) throws Exception {
		// TODO Auto-generated method stub
		measurementDao.delete(msId);
	}

	
	/**
	 * 根据工程ID 、 type类型去查找相应的cpt文件
	 * @author wanghuijun
	 * @createTime 2018年10月10日
	 * @param projId
	 * @param type
	 * @return
	 */
	@Override
	public List<Object> findPrintDataByProjId(String projId, String type) {
		String result ="";
		List<Object> list = new ArrayList<Object>();
		//根据工程ID查询计量表信息
		//计量表信息有多条
		List<Measurement> measurementList = measurementDao.findByProjId(projId);
		Project project = projectDao.get(projId);  //根据工程ID取得实体
		//计量表记录报表
		String arrayStr = CompletionDataPrintEnum.MEASUREMENT_RECORD.getCptUrl();
		//2、使用JSONArray
		JSONArray jsonArray=JSONArray.fromObject(arrayStr);
		if(jsonArray!=null&&jsonArray.size()>0 && measurementList!=null && measurementList.size()>0 && project !=null){
			JSONObject jsonObject=JSONObject.fromObject(jsonArray.get(0));
			CompletionDataPrintDto dto = (CompletionDataPrintDto)JSONObject.toBean(jsonObject, CompletionDataPrintDto.class);
			String[] menuIdAndMark = type.split("-"); // 分解施工任务单标识符和菜单ID,从而取得菜单ID
			String menuId = menuIdAndMark[menuIdAndMark.length-1]; // 获取菜单id
			String key = project.getProjectType()+"_"+project.getCorpId()+"_"+menuId;
			Object reportVersion = Constants.getSysConfigByKey(key);
				   if(reportVersion !=null){
					   //记录特定字符索引  
					   int beginIndex = dto.getReportlet().indexOf("/"); 
					   int endIndex = dto.getReportlet().lastIndexOf("/");
				       String reportlet = dto.getReportlet().substring(beginIndex, endIndex+1);  //截取字符'/'之间的字符串
					   dto.setReportlet(reportlet+reportVersion.toString());   //若reportVersion不为空则重新设置报表路径
				   }
				result = "{reportlet:'"+dto.getReportlet()+"',projId:'"+measurementList.get(0).getProjId()+"',imgUrl:'" + Constants.DISK_PATH+Constants.SIGN_DISK_PATH + "'}";
				list.add(result);
		}
		return list;
	}
	

}
