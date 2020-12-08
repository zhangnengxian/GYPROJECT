package cc.dfsoft.project.biz.base.inspection.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.GroundResistanceTestDao;
import cc.dfsoft.project.biz.base.inspection.dto.GroundResistanceTestReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListGRTReq;
import cc.dfsoft.project.biz.base.inspection.entity.GroundResistanceTest;
import cc.dfsoft.project.biz.base.inspection.service.GroundResistanceTestService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class GroundResistanceTestServiceImpl implements GroundResistanceTestService{
	@Resource
	GroundResistanceTestDao groundResistanceTestDao;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveGroundResistanceTestSurvey(ProjectCheckListGRTReq projectCheckListGRTReq) {
		//根据pcid删除所有的接地测试记录
		groundResistanceTestDao.deleteByPcId(projectCheckListGRTReq.getPcId());
		//保存列表中高程测量记录
		List<GroundResistanceTest> list=projectCheckListGRTReq.getList();
		List<GroundResistanceTest> listNew=new ArrayList<GroundResistanceTest>();
		for(GroundResistanceTest as:list){
			as.setId(IDUtil.getUniqueId(Constants.GROUND_RESISTANCE_TEST));
			listNew.add(as);
		}
		
		groundResistanceTestDao.batchInsertObjects(listNew);
	}

	@Override
	public Map<String, Object> queryGroundResistanceTestSurvey(GroundResistanceTestReq groundResistanceTestReq) {
		// TODO Auto-generated method stub
		return groundResistanceTestDao.queryGroundResistanceTestSurvey(groundResistanceTestReq);
	}

}
