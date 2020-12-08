package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.InspectionListDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.InspectionListQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionList;
import cc.dfsoft.project.biz.base.constructmanage.entity.InspectionRecord;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionListService;
import cc.dfsoft.project.biz.base.constructmanage.service.InspectionRecordService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class InspectionListServiceImpl extends NewBaseDAO<InspectionList, String> implements InspectionListService {
	
	/** 安全质量检查Dao*/
	@Resource
	InspectionListDao inspectionListDao;
	@Resource
	InspectionRecordService inspectionRecordService;
	@Resource
	SignatureService signatureService;
	@Override
	public Map<String, Object> queryInspectionList(InspectionListQueryReq inspectionListQueryReq)throws Exception {
		/**
		 * 安全质量检查记录条件查询
		 */
		return inspectionListDao.queryInspectionList(inspectionListQueryReq);
	}
	@Override
	public InspectionList viewInspectionListResult(String id) {
		InspectionList inspectionList = inspectionListDao.get(id);
		return inspectionList;
	}
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public Map<String, Object> saveInspectionList(InspectionList inspectionList) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(inspectionList.getIlId())){ //新增检查记录
			flag = true;
			inspectionList.setIlId(IDUtil.getUniqueId(Constants.MODULE_CODE_PURGE));
		}
		inspectionListDao.saveOrUpdate(inspectionList);
		List<InspectionRecord> inspectionRecords = inspectionList.getInspectionRecords();
		Map<String,Object> mapIns = new HashMap<String, Object>();
		mapIns.put("insList", inspectionRecords);
		mapIns.put("ilId", inspectionList.getIlId());
		
		inspectionRecordService.reSaveInspctionRecords(mapIns);
		Map<String, Object> imgurl = new HashMap<>();
		signatureService.saveOrUpdateSign("menuId+menuNane+15",inspectionList.getSign(), inspectionList.getProjId(), inspectionList.getIlId(), inspectionList.getClass().getName(), flag);
		mapIns = null; // 手动置为null,加快gc垃圾回收
		return imgurl;
	}
	@Override
	public List<Map<String, Object>> queryInspectionList(String param) {
		return inspectionListDao.queryInspectionList(param);
	}
}

