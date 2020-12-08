package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.InsulationResistanceTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.InsulationResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.entity.InsulationResistanceTest;
import cc.dfsoft.project.biz.base.inspection.entity.Purge;
import cc.dfsoft.project.biz.base.inspection.service.InsulationResistanceTestService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 电器绝缘电阻
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class InsulationResistanceTestServiceImpl implements InsulationResistanceTestService{
	
	/**绝缘电阻测试记录单Dao*/
	@Resource 
	InsulationResistanceTestDao insulationResistanceTestDao;
	
	/**
	 * 电器绝缘电阻记录列表查询
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param purgeQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryInsulationResistanceTest(InsulationResistanceTestReq req) {
		return insulationResistanceTestDao.queryInsulationResistanceTest(req);
	}
	
	/**
	 * 保存电器绝缘电阻记录(批量增加)
	 * @author fuliwei
	 * @createTime 2017-2-6
	 * @param req
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveInsulationResistanceTestRecord(InsulationResistanceTestReq req) {
		if(req.getList()!=null && req.getList().size()>0){
			//删除所有的电器绝缘电阻记录
			insulationResistanceTestDao.deleteByPcId(req.getPcId());
			//批量保存列表中的值
			List<InsulationResistanceTest> list=req.getList();
			List<InsulationResistanceTest> listNew=new ArrayList<InsulationResistanceTest>();
			for(InsulationResistanceTest irt:list){
				irt.setIrtId(IDUtil.getUniqueId(Constants.INSULATION_RESISTANCE_TEST));
				listNew.add(irt);
			}
			insulationResistanceTestDao.batchInsertObjects(listNew);
		}else{
			//删除所有的电器绝缘电阻记录
			insulationResistanceTestDao.deleteByPcId(req.getPcId());
		}
	}
	

}
