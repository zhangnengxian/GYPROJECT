package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.constructmanage.dao.DigRoadDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.DigRoadQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.DigRoad;
import cc.dfsoft.project.biz.base.constructmanage.enums.RoadTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.DigRoadService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DigRoadServiceImpl implements DigRoadService{

	@Resource
	DigRoadDao digRoadDao;
	
	@Override
	public Map<String,Object> digRoadDetail(DigRoadQueryReq digRoadQueryReq) {
		
		Map<String,Object> result = digRoadDao.queryDigRoad(digRoadQueryReq);
		List<DigRoad> digRoadList = (List<DigRoad>) result.get("data");
		List<DigRoad> newList = new ArrayList();
		if(StringUtils.isNotBlank(digRoadQueryReq.getTpId()) && digRoadList!=null && digRoadList.size()!=0){
			return result;
		}else if(StringUtils.isNotBlank(digRoadQueryReq.getTpId())){
			return result;
		}else{
			RoadTypeEnum[] types = RoadTypeEnum.values();
			for(int i=0;i<types.length;i++){
				DigRoad digRoad = new DigRoad();
				digRoad.setDrRoads(types[i].getMessage());
				digRoad.setDrId(types[i].getValue());
				//digRoad.setDrId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTSTART));
				newList.add(digRoad);
			}
			result.put("data", newList);
		}
		return result;
	}

}
