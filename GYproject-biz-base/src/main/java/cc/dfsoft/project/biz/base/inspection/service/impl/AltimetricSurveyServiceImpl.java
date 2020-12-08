package cc.dfsoft.project.biz.base.inspection.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.AltimetricSurveyDao;
import cc.dfsoft.project.biz.base.inspection.dto.AltimetricSurveyReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListASReq;
import cc.dfsoft.project.biz.base.inspection.entity.AltimetricSurvey;
import cc.dfsoft.project.biz.base.inspection.service.AltimetricSurveyService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class AltimetricSurveyServiceImpl implements AltimetricSurveyService{
	@Resource
	AltimetricSurveyDao altimetricSurveyDao;

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveAltimSurvey(ProjectCheckListASReq projectCheckListASReq) {
		//根据pcid删除所有的高程测量记录
		altimetricSurveyDao.deleteByPcId(projectCheckListASReq.getPcId());
		//保存列表中高程测量记录
		List<AltimetricSurvey> list=projectCheckListASReq.getList();
		List<AltimetricSurvey> listNew=new ArrayList<AltimetricSurvey>();
		for(AltimetricSurvey as:list){
			as.setAsId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
			listNew.add(as);
		}
		
		altimetricSurveyDao.batchInsertObjects(listNew);
		

		/*for(int i=0;i<list.size();i++){
			if(StringUtils.isBlank(list.get(i).getAsId())){
				//DerustingPreservative dp=list.get(i);
				//新增
				list.get(i).setAsId(IDUtil.getUniqueId(Constants.MODULE_CODE_ALTIMETRIC_SURVEY));
				altimetricSurveyDao.save(list.get(i));
			}else{
				//DerustingPreservative dp2=list.get(i);
				altimetricSurveyDao.update(list.get(i));
			}
		}*/
		
	}

	@Override
	public Map<String, Object> queryAltimSurvey(AltimetricSurveyReq altimetricSurveyReq) {
		// TODO Auto-generated method stub
		return altimetricSurveyDao.queryAltimSurvey(altimetricSurveyReq);
	}

}
