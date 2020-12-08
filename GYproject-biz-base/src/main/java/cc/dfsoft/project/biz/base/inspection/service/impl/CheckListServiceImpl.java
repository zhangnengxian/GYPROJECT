package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gexin.rp.sdk.http.utils.Constant;

import cc.dfsoft.project.biz.base.complete.entity.CheckItem;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionList;
import cc.dfsoft.project.biz.base.complete.entity.SelfInspectionRecord;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.inspection.dao.CheckListDao;
import cc.dfsoft.project.biz.base.inspection.dao.CheckListTypeDao;
import cc.dfsoft.project.biz.base.inspection.entity.CheckList;
import cc.dfsoft.project.biz.base.inspection.entity.CheckListType;
import cc.dfsoft.project.biz.base.inspection.service.CheckListService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CheckListServiceImpl implements CheckListService{
	@Resource
	CheckListDao checkListDao;
	@Resource
	CheckListTypeDao checkListTypeDao;
	@Resource
	ProjectDao projectDao;
	@Resource
	OperateRecordService operateRecordService;
	@Override
	public List<CheckList> queryByProjId(String id) {
		List<CheckList> checkList=checkListDao.queryByProjId(id);
		return checkList;
	}
	/**
	 * 质量自检
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public Map<String, String> viewSelfInspectionRecordQuqlity(String projId) {
		//获取记录
		List<CheckList> checkListRecords =checkListDao.queryByProjId(projId);
		//查检查项
		List<CheckListType> checkType = checkListTypeDao.getAll();
		Map<String,String> map = new HashMap<String,String>();
		if(checkListRecords!=null&&checkListRecords.size()>0){
			//如果只保存部分
			for(CheckList checkList :checkListRecords){
				if(checkList.getIsCheck()!=null){
					map.put(checkList.getCheckTypeId()+"_clId", checkList.getClId());
					map.put(checkList.getCheckTypeId()+"_projId", projId);
					map.put(checkList.getCheckTypeId()+"_checkTypeId", checkList.getCheckTypeId());
					map.put(checkList.getCheckTypeId()+"_isCheck",checkList.getIsCheck()); 
					map.put(checkList.getCheckTypeId()+"_remark", checkList.getRemark());
				}else{
					map.put(checkList.getCheckTypeId()+"_clId", checkList.getClId());
					map.put(checkList.getCheckTypeId()+"_projId", projId);
					map.put(checkList.getCheckTypeId()+"_checkTypeId", checkList.getCheckTypeId());
					map.put(checkList.getCheckTypeId()+"_remark", checkList.getRemark());
				}
				
			}
		}else{
			//初次加载没有保存质量自检项
			for(int i=0;i<checkType.size();i++){
				map.put(checkType.get(i).getId()+"_projId", projId);
				map.put(checkType.get(i).getId()+"_checkTypeId", checkType.get(i).getId());
			}
		}
		return map;
	}
	/**
	 * 保存自检信息
	 * @author fuliwei
	 * @createTime 2017年7月31日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCheckList(List<CheckList> list) throws SerialException, SQLException {
		if(list!=null&&list.size()>0){
			for(CheckList cl : list){
				if(StringUtils.isBlank(cl.getClId())){
					cl.setClId(IDUtil.getUniqueId(Constants.CHECK_LIST));
				}				
			}
			/*String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE);
			Project pro=projectDao.get(list.get(0).getProjId());
			operateRecordService.createOperateRecord(orId, pro.getProjId(), pro.getProjNo(), StepEnum.SELF_CHECK.getValue(), StepEnum.SELF_CHECK.getMessage(), "");*/
		}	
			checkListDao. batchInsertOrUpdateObjects(list);
	
	}


}
