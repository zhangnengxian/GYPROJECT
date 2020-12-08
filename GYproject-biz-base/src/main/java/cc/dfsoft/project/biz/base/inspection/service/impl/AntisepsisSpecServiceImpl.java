package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import cc.dfsoft.project.biz.base.inspection.dao.AntisepsisSpecDao;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListAntisepsisSpecReq;
import cc.dfsoft.project.biz.base.inspection.entity.AntisepsisSpec;
import cc.dfsoft.project.biz.base.inspection.service.AntisepsisSpecService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class AntisepsisSpecServiceImpl implements AntisepsisSpecService{
	@Resource
	AntisepsisSpecDao antisepsisSpecDao;

	@Override
	public List<AntisepsisSpec> queryAntSpecByPcId(String pcId,String asType) {
		// TODO Auto-generated method stub
		return antisepsisSpecDao.queryAntSpecByPcId(pcId,asType);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveAntSpecList(ProjectCheckListAntisepsisSpecReq req) {
		
		//删除除锈防腐管道规格
		antisepsisSpecDao.deleteByPcId(req.getPcId());
		
		//批量保存
		List<AntisepsisSpec> list=req.getList();
		List<AntisepsisSpec> listNew=new ArrayList<AntisepsisSpec>();
		for(AntisepsisSpec as:list){
			as.setAsId(IDUtil.getUniqueId(Constants.MODULE_CODE_DERUSTING));//设置id
			listNew.add(as);
		}
		antisepsisSpecDao.batchInsertObjects(listNew);			
	}

	
}
