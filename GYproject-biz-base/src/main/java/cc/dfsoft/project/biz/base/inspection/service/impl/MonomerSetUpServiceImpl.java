package cc.dfsoft.project.biz.base.inspection.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.AltimetricSurveyDao;
import cc.dfsoft.project.biz.base.inspection.dao.MonomerSetUpDao;
import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListMSUReq;
import cc.dfsoft.project.biz.base.inspection.entity.AltimetricSurvey;
import cc.dfsoft.project.biz.base.inspection.entity.MonomerSetUp;
import cc.dfsoft.project.biz.base.inspection.service.AltimetricSurveyService;
import cc.dfsoft.project.biz.base.inspection.service.MonomerSetUpService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MonomerSetUpServiceImpl implements MonomerSetUpService{
	@Resource
	MonomerSetUpDao monomerSetUpDao;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMonomerSetUps(ProjectCheckListMSUReq projectCheckListMSUReq) {
		//根据pcid删除所有的单体调校记录
		monomerSetUpDao.deleteByPcId(projectCheckListMSUReq.getPcId());
		//保存列表中高程测量记录
		List<MonomerSetUp> list=projectCheckListMSUReq.getList();
		List<MonomerSetUp> listNew=new ArrayList<MonomerSetUp>();
		for(MonomerSetUp as:list){
			as.setId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			listNew.add(as);
		}
		monomerSetUpDao.batchInsertObjects(listNew);
	}

	@Override
	public Map<String, Object> queryMonomerSetUp(AltimetricSurveyReq altimetricSurveyReq) {
		return monomerSetUpDao.queryAltimSurvey(altimetricSurveyReq);
	}

}
