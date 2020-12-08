package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.DerustingPreservativeDao;
import cc.dfsoft.project.biz.base.inspection.dto.DerustingPreservativeQueryReq;
import cc.dfsoft.project.biz.base.inspection.dto.PreservativeReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListDerustingReq;
import cc.dfsoft.project.biz.base.inspection.entity.DerustingPreservative;
import cc.dfsoft.project.biz.base.inspection.service.DerustingPreservativeService;
import cc.dfsoft.project.biz.base.project.dao.AccessoryDao;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.AccessorySourceEnum;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.SignPostEnum;
import cc.dfsoft.project.biz.base.project.enums.SignStateEnum;
import cc.dfsoft.project.biz.base.project.service.AccessoryService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

/**
 * 除锈防腐service实现
 * @author Administrator
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DerustingPreservativeServiceImpl implements DerustingPreservativeService{
	
	/**除锈防腐Dao*/
	@Resource
	DerustingPreservativeDao derustingPreservativeDao;
	@Resource
	SignatureService signatureService;
	@Resource
	AccessoryDao accessoryDao;
	@Resource
	AccessoryService accessoryService;
	
	/**签字通知服务*/
	@Resource
	SignNoticeService signNoticeService;
	
	/**
	 * 除锈防腐列表查询
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param dpQueryReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryDerusting(DerustingPreservativeQueryReq dpQueryReq) throws ParseException {
		return derustingPreservativeDao.queryDerusting(dpQueryReq);
	}
	
	/**
	 * 保存除锈防腐(批量增加)
	 * @author fuliwei
	 * @createTime 2016-7-26
	 * @param list
	 * @return void
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDerustingRecord(ProjectCheckListDerustingReq req) {
		List<DerustingPreservative> list=req.getList();
		if(list.size()>0){
			derustingPreservativeDao.deleteByPcId(req.getPcId());
		
				//批量保存
				List<DerustingPreservative> listNew=new ArrayList<DerustingPreservative>();
				for(DerustingPreservative dp:list){
					dp.setDpId(IDUtil.getUniqueId(Constants.MODULE_CODE_DERUSTING));//设置id
					listNew.add(dp);
				}
				derustingPreservativeDao.batchInsertObjects(listNew);
		}else{
			derustingPreservativeDao.deleteByPcId(req.getPcId());
		}
				
	}

	/**
	 * 分页查询防腐记录
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param preservativeReq 防腐记录查询辅助类
	 * @return Map<String, Object> 防腐记录以及分页信息
	 */
	@Override
	public Map<String, Object> queryRecords(PreservativeReq preservativeReq) {
		return derustingPreservativeDao.queryRecords(preservativeReq);
	}

	/**
	 * 保存防腐记录信息
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param checkListDReq 防腐记录保存辅助类
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveDerustingPreservativeRecord(
			ProjectCheckListDerustingReq checkListDReq) {
		//有记录
		if(checkListDReq.getList().size()>0){
			//先删除记录
			deleteRecord(checkListDReq);
			//批量保存记录
			List<DerustingPreservative> listNew = new ArrayList<DerustingPreservative>();
			for(DerustingPreservative dp : checkListDReq.getList()){
				dp.setDpId(IDUtil.getUniqueId(Constants.MODULE_CODE_PRESERVATIVE));
				listNew.add(dp);
			}
			derustingPreservativeDao.batchInsertObjects(listNew);
			
		}else {
			deleteRecord(checkListDReq);
		}
	}

	private void deleteRecord(ProjectCheckListDerustingReq checkListDReq) {
		//已报验过
		if(StringUtils.isNotBlank(checkListDReq.getPcId())){
			//有pcId,则根据pcId删除记录
			derustingPreservativeDao.deleteByPcIdAndType(checkListDReq.getPcId(),checkListDReq.getPreservativeType());
		}else{
			derustingPreservativeDao.deleteByProjIdAndType(checkListDReq.getProjId(),checkListDReq.getPreservativeType());
		}
	}

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param recordsId 防腐记录ID组合
	 * @param pcId 报验单ID
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updatePcIdByRecordsId(String recordsId, String pcId,String projId,String projNo) {
		//先置空之前报验的pcId
		derustingPreservativeDao.updateByPcId(pcId);
		//质空附件表中的报验单ID
		accessoryService.clearBRId(projId,projNo,pcId);
		String [] ids = recordsId.split(",");
		//
		for(String id : ids){
			DerustingPreservative record = derustingPreservativeDao.get(id);
			if(record!=null){
				derustingPreservativeDao.updatePcIdByRecordId(id,pcId);
				//回写拍照pcId
				accessoryDao.updateBId(pcId,record.getProjId(),id,AccessorySourceEnum.PHOTO_FILE.getValue());
			}
		}
	}

	/**
	 * 根据记录ID回写报验单ID
	 * @author liaoyq
	 * @createTime 2017-7-24
	 * @param id 记录ID
	 */
	@Override
	public DerustingPreservative viewRecordById(String id) {
		return derustingPreservativeDao.get(id);
	}

	/**
	 * 保存防腐记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param preservative
	 * @return 防腐记录ID
	 */
	@Override
	@Transactional(readOnly = false,  propagation =Propagation.REQUIRED)
	public String saveRecord(DerustingPreservative preservative) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(preservative.getDpId())){
			preservative.setDpId(IDUtil.getUniqueId(Constants.MODULE_CODE_DERUSTING));//防腐记录
			flag = true;
		}
		derustingPreservativeDao.saveOrUpdate(preservative);
		
		
		
		List<Signature> signs=preservative.getSign();
		if(signs!=null && signs.size()>0){
			for(Signature sign:signs){
				sign.setDataType(SignDataTypeEnum.PRESERVATIVE.getValue());
			}
			preservative.setSign(signs);
		}
		
		
		if(preservative.getSign()!=null){
			signatureService.saveOrUpdateSign(preservative.getMenuId(),preservative.getSign(), preservative.getProjId(), preservative.getDpId(), preservative.getClass().getName(), flag);
		}
		return preservative.getDpId();
	}

	/**
	 *根据记录ID删除防腐记录
	 * @author liaoyq
	 * @createTime 2017-8-18
	 * @param id 防腐记录ID
	 */
	@Override
	@Transactional(readOnly = false,  propagation =Propagation.REQUIRED)
	public void deleteRecordById(String id) {
		derustingPreservativeDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false,  propagation =Propagation.REQUIRED)
	public void saveSignNotice(String cwId) {
		String signState = SignStateEnum.ALREADY_SIGN.getValue();
		if(StringUtils.isNotBlank(cwId)){
			//builder施工员
			//firstParty甲方
		    //supervision监理
			DerustingPreservative grooveRecord=derustingPreservativeDao.get(cwId);
			if(grooveRecord!=null){
				if(StringUtils.isNotBlank(grooveRecord.getBuilder())){
					//施工员为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.CONSTRUCTION.getValue(), SignDataTypeEnum.PRESERVATIVE.getValue(),
							grooveRecord.getDpId(), grooveRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(grooveRecord.getFirstParty())){
					//甲方为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.BUILDER.getValue(), SignDataTypeEnum.PRESERVATIVE.getValue(),
							grooveRecord.getDpId(), grooveRecord.getProjId(),signState);
				}
				if(StringUtils.isNotBlank(grooveRecord.getSupervision())){
					//监理为无效
					signNoticeService.saveThisSignNotice(SignPostEnum.SUJGJ.getValue(), SignDataTypeEnum.PRESERVATIVE.getValue(),
							grooveRecord.getDpId(), grooveRecord.getProjId(),signState);
				}
			}
		}
		
	}
}
