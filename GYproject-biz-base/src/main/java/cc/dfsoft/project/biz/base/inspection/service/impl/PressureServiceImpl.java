package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cc.dfsoft.project.biz.base.inspection.dao.PressureDao;
import cc.dfsoft.project.biz.base.inspection.dto.PressureQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListPressureReq;
import cc.dfsoft.project.biz.base.inspection.entity.Pressure;
import cc.dfsoft.project.biz.base.inspection.service.PressureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 试压记录接口实现
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PressureServiceImpl implements PressureService{
	
	/**试压记录Dao*/
	@Resource
	PressureDao pressureDao;
	
	/**
	 * 试压记录列表查询
	 * @author fuliwei
	 * @createTime 2016-7-20
	 * @param pressureQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryPressure(PressureQueryReq pressureQueryReq) throws ParseException {
		return pressureDao.queryPressure(pressureQueryReq);
	}
	
	/**
	 * 保存试压记录(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-21
	 * @param list
	 * @return String
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePressureRecord(ProjectCheckListPressureReq req) {
		if(req.getList().size()>0){
			//删除所有的试压记录
			pressureDao.deleteByPcId(req.getPcId());
			//批量增加
			List<Pressure> list=req.getList();
			List<Pressure> preList=new ArrayList<Pressure>();
			for(Pressure ps:list){
				ps.setPressureId(IDUtil.getUniqueId(Constants.MODULE_CODE_PRESSURE));
				preList.add(ps);
			}
			//批量插入对象
			pressureDao.batchInsertObjects(preList);
		}else{
			//删除所有的试压记录
			pressureDao.deleteByPcId(req.getPcId());
		}
	}
}
