package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.ControlDebuggingDao;
import cc.dfsoft.project.biz.base.inspection.dto.ControlDebuggingReq;
import cc.dfsoft.project.biz.base.inspection.entity.ControlDebugging;
import cc.dfsoft.project.biz.base.inspection.entity.InsulationResistanceTest;
import cc.dfsoft.project.biz.base.inspection.service.ControlDebuggingService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 控制系统调试
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ControlDebuggingServiceImpl implements  ControlDebuggingService{
	
	/**控制系统调试Dao*/
	@Resource 
	ControlDebuggingDao controlDebuggingDao;
	
	/**
	 * 控制系统调试记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryControlDebugging(ControlDebuggingReq req) {
		return controlDebuggingDao.queryControlDebugging(req);
	}
	
	/**
	 * 保存控制系统调试记录(批量增加)
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param req
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveControlDebugging(ControlDebuggingReq req) {
		if(req.getList()!=null && req.getList().size()>0){
			//删除所有的控制系统调试记录
			controlDebuggingDao.deleteByPcId(req.getPcId());
			//批量保存列表中的值
			List<ControlDebugging> list=req.getList();
			List<ControlDebugging> listNew=new ArrayList<ControlDebugging>();
			for(ControlDebugging cd:list){
				cd.setCdId(IDUtil.getUniqueId(Constants.CONTROL_DEBUGGING));
				listNew.add(cd);
			}
			controlDebuggingDao.batchInsertObjects(listNew);
		}else{
			//删除所有的控制系统调试记录
			controlDebuggingDao.deleteByPcId(req.getPcId());
		}
		
	}

}
