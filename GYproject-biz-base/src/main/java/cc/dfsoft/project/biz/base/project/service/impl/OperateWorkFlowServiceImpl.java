package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.Order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fr.general.jsqlparser.expression.StringValue;
import com.fr.report.core.A.l;
import com.fr.third.v2.org.openxmlformats.schemas.drawingml.x2006.main.STAdjAngle;
import com.mchange.v2.async.StrandedTaskReporting;

import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dao.OperateRecordDao;
import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowDao;
import cc.dfsoft.project.biz.base.project.dao.OperateWorkFlowRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dto.OperateRecordQueryReq;
import cc.dfsoft.project.biz.base.project.dto.OperateWorkFlowReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.OperateRecord;
import cc.dfsoft.project.biz.base.project.entity.OperateWorkFlow;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateWorkFlowService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import ch.qos.logback.classic.Logger;
import sun.util.logging.resources.logging;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class OperateWorkFlowServiceImpl implements OperateWorkFlowService {

	@Resource
	OperateWorkFlowDao operateWorkFlowDao;

	/** 工程类型 */
	@Resource
	ProjectTypeDao proejctTypeDao;

	/** 出资方式 */
	@Resource
	ContributionModeDao contributionModeDao;

	/** 工程 */
	@Resource
	ProjectDao projectDao;

	@Resource
	OperateWorkFlowRecordDao operateWorkFlowRecordDao;

	@Resource
	StaffDao staffDao;

	@Autowired
	OperateRecordDao operateRecordDao;
	
	@Autowired
	DepartmentDao departmentDao;

	/**
	 * 查询流程配置标准
	 * 
	 * @author fuliwei
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	@Override
	public Map<String, Object> queryList(OperateWorkFlowReq req) {

		Map<String, Object> map = operateWorkFlowDao.queryList(req);
		List<OperateWorkFlow> list = (List<OperateWorkFlow>) map.get("data");
		if (list != null && list.size() > 0) {
			for (OperateWorkFlow owf : list) {
				if (StringUtils.isNotBlank(owf.getProjectType())) {
					ProjectType pt = proejctTypeDao.get(owf.getProjectType());
					if(pt !=null){
						owf.setProjectTypeDes(pt.getProjTypeDes());
					}
				}
				if (StringUtil.isNotBlank(owf.getContributionMode())) {
					ContributionMode cm = contributionModeDao.get(owf.getContributionMode());
					if(cm!=null ){
						owf.setContributionModeDes(cm.getContributionDes());
					}
				}
			}
		}

		return map;
	}

	/**
	 * 通过条件查询查询流程配置标准
	 * 
	 * @author fuliwei
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	@Override
	public List<OperateWorkFlow> queryListByReq(OperateWorkFlowReq req) {
		return operateWorkFlowDao.queryListByReq(req);
	}

	/**
	 * 保存操作流
	 * 
	 * @author fuliwei updata wanghuijun 2019-04-30
	 * @date 2018年9月7日
	 * @version 1.0
	 * @throws Exception 
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveOperateWork(OperateWorkFlow opl) throws Exception {
		OperateWorkFlow operateWorkFlow = null;
		if (StringUtils.isNotBlank(opl.getOwfId())) {
			operateWorkFlow = operateWorkFlowDao.get(opl.getOwfId()); // 根据Id取得实体
		}
		
		if (StringUtils.isBlank(opl.getOwfId())) { // 新增步骤
			opl.setOwfId(IDUtil.getUniqueId(Constants.STANDARD)); // 生成唯一ID号
			// 根据Id取得实体
			operateWorkFlow = operateWorkFlowDao.findByGrade(opl.getCorpId(), opl.getProjectType(),opl.getContributionMode(), opl.getStepId(), opl.getGrade(), opl.getOpType()); 
			if (operateWorkFlow != null) { // 判断流程是否已经存在，流程存在则返回存在标识符
				return "exist"; // 返回存在标识符
			}

			// 若是新增--处理操作记录开始
			OperateWorkFlowReq operateWorkFlowReq = new OperateWorkFlowReq(); // 创建对象
			/*if(StringUtils.isNotBlank(opl.getStepId())){ //stepId 不为空
				opl.setStepName(StepEnum.valueof(opl.getStepId()).getMessage());  //步骤名称
			}*/
			if (StringUtils.isNotBlank(opl.getCorpId()) && StringUtils.isNotBlank(opl.getProjectType())
					&& StringUtils.isNotBlank(opl.getOpType()) && StringUtils.isNotBlank(opl.getContributionMode())) {
				operateWorkFlowReq.setCorpId(opl.getCorpId());
				operateWorkFlowReq.setProjectType(opl.getProjectType());
				operateWorkFlowReq.setContributionMode(opl.getContributionMode());
				operateWorkFlowReq.setOpType(opl.getOpType());
				operateWorkFlowReq.setOperaterId(opl.getOpereaterId()); //操作人ID 
				operateWorkFlowReq.setOperater(opl.getOpereater());  //操作人
				operateWorkFlowReq.setStepName(opl.getStepName());
				operateWorkFlowReq.setOpType(opl.getOpType());
				operateWorkFlowReq.setOwfId(opl.getOwfId());
				this.updateOperateRecord(operateWorkFlowReq,opl.getStepId(), opl.getGrade()); // 新增时调用方法处理操作记录
			
			}
			// 若是新增--处理操作记录结束

		} else if (operateWorkFlow != null && !(opl.getOpereater().equals(operateWorkFlow.getOpereater()))) { // 非新增且操作人改变
			try {
				operateRecordDao.updateOperateRecord(opl); // 更新操作记录
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Constants.FAIL_CODE;
			}

		}
		if(operateWorkFlow != null){
			operateWorkFlowDao.evict(operateWorkFlow); // 托管对象，同一个方法内不能有两个id相同的对象，故需要进行托管
		}
		operateWorkFlowDao.saveOrUpdate(opl);

		return Constants.SUCCESS_CODE;
	}

	/**
	 * 更新操作记录当新增操作流时
	 * 
	 * @param operateWorkFlowReq,newStepId
	 * 参数  操作流辅助实体查询，新增stepId
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateOperateRecord(OperateWorkFlowReq operateWorkFlowReq,String newStepId,String grade) throws Exception {
		HashSet<String> removeSameStepId = new HashSet<String>(); // 用作去重
		List<String> os = new ArrayList<String>();
		String lastStepId = null;   //上一步骤stepId
		List<OperateRecord> operList = null; // 定义一个list
		OperateRecordQueryReq ordr = null; // 定义一个辅助实体
		List<OperateWorkFlow> operateWorkFlows = operateWorkFlowDao.queryListByReq(operateWorkFlowReq); // 获取操作流
		if (operateWorkFlows != null && operateWorkFlows.size() > 0) { // 有记录,循环放入removeSameStepId里面去重，利用hashset去重
			for (int i = 0; i <= operateWorkFlows.size() - 1; i++) {
				removeSameStepId.add(operateWorkFlows.get(i).getStepId());
			}
		}

		if(removeSameStepId != null && removeSameStepId.size() > 0){ //有记录，循环放入os（List中）,放入list集合中，便于排序
			for (String stepId : removeSameStepId) {
				os.add(stepId);   //循环放入list
			}
			 Collections.sort(os);  //对os(list)进行排序 
		}
	
		if(os != null && os.size() > 0){
			for(int i = 0; i <= os.size()-1; i++){
				/**遍历排序后的os集合,判断os里面的stepId是否大于新增newStepId,大于则得到新增newStepId之前的stepId
				 * 取得上一步骤iD ,查询出历史数据，判断是否是代办或者是未办
				 */
				if(i==0 && newStepId.compareTo(os.get(i)) < 0){  //若是添加最前端的stepId
					lastStepId = null;
					break;
				}else if(newStepId.compareTo(os.get(i)) < 0){
					lastStepId = String.valueOf(os.get(i-1));  //取的新增之前的stepId
					break;   //取到stepID终止循环
				}else if(i == (os.size()-1)){  //若是添加末尾步骤StepId
					lastStepId = os.get(os.size()-1);  //
				}else if(newStepId.equals(os.get(i))){  //若是添加具有审核级别的id
					lastStepId = newStepId;
					break ;  //取到具有审核级别的stepId，终止本次循环
				}
			}
		}
		if(StringUtils.isNotBlank(lastStepId)){  //上一步骤id不为空,处理历史数据
			operateWorkFlowReq.setStepId(lastStepId);
		    operateWorkFlows = operateWorkFlowDao.queryListByReq(operateWorkFlowReq); // 获取唯一操作流
		    if(operateWorkFlows != null ){
		    	ordr = new OperateRecordQueryReq(); // 新建对象

				ordr.setStepId(operateWorkFlows.get(0).getStepId()); // 步骤ID
				ordr.setCorpId(operateWorkFlows.get(0).getCorpId()); // 公司ID
				ordr.setProjectType(operateWorkFlows.get(0).getProjectType()); // 工程类型
				if (StringUtils.isNotBlank(operateWorkFlows.get(0).getGrade())) { // 审核级别
					ordr.setGrade(operateWorkFlows.get(0).getGrade()); 
				}
				ordr.setContributionMode(operateWorkFlows.get(0).getContributionMode()); // 出资方式
				ordr.setModifyStatus("1"); // 修改状态
				ordr.setIsValid("1"); // 是否有效
				Map<String, Object> operateRecord = operateRecordDao.queryOperateRecordLists(ordr);
				operList = (List<OperateRecord>) operateRecord.get("data"); // 取得条件查询的记录
		    }
		} 
		 
		if(operList !=null  && operList.size() > 0 ){  //有记录，批量处理记录并批量生成记录
			for (OperateRecord operateRecord : operList) {
				/**
				 * 工程Id赋值 步骤Id赋值 公司ID赋值 审核级别赋值 工程类型赋值 出资方式赋值 是否有效赋值
				 */
				ordr = new OperateRecordQueryReq(); // 新建对象
				ordr.setProjId(operateRecord.getProjId());
				ordr.setStepId(operateWorkFlows.get(0).getStepId());
				ordr.setCorpId(operateWorkFlows.get(0).getCorpId());
				if (StringUtils.isNotBlank(operateWorkFlows.get(0).getGrade())) { // 审核级别
					ordr.setGrade(operateWorkFlows.get(0).getGrade());
				}
				ordr.setProjectType(operateWorkFlows.get(0).getProjectType());
				ordr.setContributionMode(operateWorkFlows.get(0).getContributionMode());
				ordr.setIsValid("1");
				ordr.setModifyStatus("1"); // 修改状态
				OperateRecord ord = operateRecordDao.queryEndOperateRecord(ordr); // 查询最新记录
				if(ord != null && StringUtils.isNotBlank(ord.getModifyStatus()) && (!ord.getModifyStatus().equals("1"))){  //有记录,且是待办，故生成新增步骤的未办操作记录
					OperateRecord newOperateRecord = new OperateRecord();  
					newOperateRecord.setOrId(IDUtil.getUniqueId(Constants.STANDARD));
					newOperateRecord.setProjId(ord.getProjId());
					newOperateRecord.setProjNo(ord.getProjNo());
					newOperateRecord.setStepId(newStepId);
					newOperateRecord.setStepName(operateWorkFlowReq.getStepName());
					newOperateRecord.setOperateCsr1(operateWorkFlowReq.getOperaterId());
					newOperateRecord.setOperater(operateWorkFlowReq.getOperater());
					if(StringUtils.isNotBlank(grade)){
						newOperateRecord.setGrade(grade);  //新增流程的审核级别
					}
					newOperateRecord.setProjectType(ord.getProjectType());
					newOperateRecord.setContributionMode(ord.getContributionMode());
					newOperateRecord.setCorpId(ord.getCorpId());
					newOperateRecord.setIsValid("1");
					newOperateRecord.setModifyStatus("0");
					newOperateRecord.setOwfId(operateWorkFlowReq.getOwfId());
					newOperateRecord.setOpType(operateWorkFlowReq.getOpType());  //操作流程类型
					operateRecordDao.saveOrUpdate(newOperateRecord); //保存新操作 
				}
			}
		}
	}

	/**
	 * 通过id删除
	 * 
	 * @author fuliwei update wanghuijun 20190429
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteByOwfId(String owfId) throws Exception {
		OperateWorkFlow operateWorkFlow = operateWorkFlowDao.get(owfId); // 根据Id取得实体
		List<OperateRecord> operList = null; // 定义一个list
		OperateRecordQueryReq ordr = null; // 定义一个辅助实体
		if (operateWorkFlow != null) { // 判断是否非空
			ordr = new OperateRecordQueryReq(); // 新建对象

			ordr.setStepId(operateWorkFlow.getStepId()); // 步骤ID
			ordr.setCorpId(operateWorkFlow.getCorpId()); // 公司ID
			ordr.setProjectType(operateWorkFlow.getProjectType()); // 工程类型
			if (StringUtils.isNotBlank(operateWorkFlow.getGrade())) { // 审核级别
				ordr.setGrade(operateWorkFlow.getGrade());
			}
			ordr.setContributionMode(operateWorkFlow.getContributionMode()); // 出资方式
			ordr.setModifyStatus("1"); // 修改状态
			ordr.setIsValid("1"); // 是否有效
			Map<String, Object> operateRecord = operateRecordDao.queryOperateRecordLists(ordr);
			operList = (List<OperateRecord>) operateRecord.get("data"); // 取得条件查询的记录
		}
		if (operList !=null && operList.size() > 0) { // 判断记录是否非空，不为空则处理数据
			for (OperateRecord operateRecord : operList) { // 循环处理数据
				/**
				 * 工程Id赋值 步骤Id赋值 公司ID赋值 审核级别赋值 工程类型赋值 出资方式赋值 是否有效赋值
				 */
				ordr = new OperateRecordQueryReq(); // 新建对象
				ordr.setProjId(operateRecord.getProjId());
				ordr.setStepId(operateWorkFlow.getStepId());
				ordr.setCorpId(operateWorkFlow.getCorpId());
				if (StringUtils.isNotBlank(operateWorkFlow.getGrade())) { // 审核级别
					ordr.setGrade(operateWorkFlow.getGrade());
				}
				ordr.setProjectType(operateWorkFlow.getProjectType());
				ordr.setContributionMode(operateWorkFlow.getContributionMode());
				ordr.setIsValid("1");
				ordr.setModifyStatus("1"); // 修改状态
				OperateRecord ord = operateRecordDao.queryEndOperateRecord(ordr); // 查询最新记录
				boolean flag = true; // 判断标识符，用于判断只执行一次
				if (ord != null && StringUtils.isBlank(ord.getGrade())) { // 记录不为空且审核级别为空
					if (StringUtils.isNotBlank(ord.getModifyStatus()) && "2".equals(ord.getModifyStatus())) { // 当前记录是否为待办，若当前记录为待办则下一步骤置为待办
						this.updateOperRecord(ordr); // 更新下一步骤待办状态
					}

				} else if (ord != null && StringUtils.isNotBlank(ord.getGrade())) {
					int grade = Integer.parseInt(ord.getGrade()); // 审核级别
					for (int i = 0; i < 100; i++) { // 审核级别循环加1，若不满足if,跳出此次循环,给100级，因为审核不可能到达100级。
						grade = grade + 1;
						ordr.setGrade(String.valueOf(grade));
						OperateRecord nextNewRecord = operateRecordDao.queryEndOperateRecord(ordr); // 查询下一级别最新记录
						if (nextNewRecord != null) { // 判断是否有记录
							nextNewRecord.setGrade(String.valueOf((Integer.parseInt(nextNewRecord.getGrade()) - 1))); // 审核级别减去1，例如：要删除的审核级别是2，但是该步骤有3级审核，删除2级审核之后，3级审核应为2级审核，故审核级减一
							if (StringUtils.isNotBlank(ord.getModifyStatus()) && "2".equals(ord.getModifyStatus())
									&& flag) { // 判断是否是代办
								nextNewRecord.setModifyStatus("2"); // 上一级为待办，将当前待办状态置为待办，且本次循环只执行一次
								flag = false; // 执行完毕置为false
							}
							operateRecordDao.saveOrUpdate(nextNewRecord); // 更新操作记录

						} else {
							// dosomething
							if (StringUtils.isNotBlank(ord.getModifyStatus())
									&& String.valueOf(grade - 1).equals(ord.getGrade())
									&& "2".equals(ord.getModifyStatus())) { // 当前记录为待办且是删除最后一级，则下一步骤置为待办
								ordr.setGrade(null); // 审核级别置为空
								this.updateOperRecord(ordr); // 更新下一步骤为待办状态
							}
							break; // 跳出此次循环
						}
					}

				}
				operateRecordDao.delete(ord.getOrId()); // 删除操作记录
			}
		}
		if (StringUtils.isNotBlank(operateWorkFlow.getGrade())) { // 判断是否有审核级别
			// 判断删除非最后一级时，将后面的级数递减
			int grade = Integer.parseInt(operateWorkFlow.getGrade()); // 审核级别加1
			while (true) { // 符合条件时跳出循环
				// dosomething
				grade = grade + 1;
				OperateWorkFlow nextOperateWorkFlow = operateWorkFlowDao.findByGrade(operateWorkFlow.getCorpId(),
						operateWorkFlow.getProjectType(), operateWorkFlow.getContributionMode(),
						operateWorkFlow.getStepId(), String.valueOf(grade), operateWorkFlow.getOpType());
				if (nextOperateWorkFlow != null) {
					nextOperateWorkFlow.setGrade(String.valueOf(Integer.parseInt(nextOperateWorkFlow.getGrade()) - 1)); // 审核级别减去1
					operateWorkFlowDao.saveOrUpdate(nextOperateWorkFlow); // 更新操作流
				} else {
					break;
				}

			}

		}
		operateWorkFlowDao.delete(owfId); // 根据操作流id删除操作流
	}
	/**
	 * 更新操作记录 将下一操作步骤置为待办
	 * 
	 * @param operateRecord
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateOperRecord(OperateRecordQueryReq operateRecordQueryReq) throws Exception{ // stepId和想像中的不一致，需要更具操作流取得所有stepID，然后取得下一步骤stepID
		HashSet<String> removeSameStepId = new HashSet<String>(); // 用作去重
		List<String> os = new ArrayList<String>();
		String deleteStepId = null; //本次步骤ID
		String nextStepId = null;  //下一步骤id
		OperateWorkFlowReq operateWorkFlowReq = new OperateWorkFlowReq();
		if(operateRecordQueryReq !=null){
			operateWorkFlowReq.setCorpId(operateRecordQueryReq.getCorpId());
			operateWorkFlowReq.setProjectType(operateRecordQueryReq.getProjectType());
			operateWorkFlowReq.setContributionMode(operateRecordQueryReq.getContributionMode());
			operateWorkFlowReq.setOpType(operateRecordQueryReq.getOpType());
			deleteStepId = operateRecordQueryReq.getStepId();
		}
		List<OperateWorkFlow> operateWorkFlows = operateWorkFlowDao.queryListByReq(operateWorkFlowReq); // 获取操作流
		if (operateWorkFlows != null && operateWorkFlows.size() > 0) { // 有记录,循环放入removeSameStepId里面去重，利用hashset去重
			for (int i = 0; i <= operateWorkFlows.size() - 1; i++) {
				removeSameStepId.add(operateWorkFlows.get(i).getStepId());
			}
		}

		if(removeSameStepId != null && removeSameStepId.size() > 0){ //有记录，循环放入os（List中）,放入list集合中，便于排序
			for (String stepId : removeSameStepId) {
				os.add(stepId);   //循环放入list
			}
			 Collections.sort(os);  //对os(list)进行排序 
		}
	
		if(os != null && os.size() > 0){
			for(int i = 0; i <= os.size()-1; i++){
				/**遍历排序后的os集合进行处理，取得下一步骤ID
				 */
				if(StringUtils.isNotBlank(deleteStepId) && deleteStepId.equals(os.get(i)) && (i<os.size()-1)){ 
					nextStepId = os.get(i+1);
					break;
				}else if(i==(os.size()-1)){ //若是最后一位
					break;
				}
				
			}
		}
			operateRecordQueryReq.setStepId(nextStepId); 
			OperateRecord nextNewRecord = operateRecordDao.queryEndOperateRecord(operateRecordQueryReq); // 查询下一级别最新记录
			if (nextNewRecord != null) {
				nextNewRecord.setModifyStatus("2");
				operateRecordDao.saveOrUpdate(nextNewRecord); // 更新操作记录
			}

		
	}

	/**
	 * 通过主键id查详述
	 * 
	 * @author fuliwei
	 * @date 2018年9月7日
	 * @version 1.0
	 */
	@Override
	public OperateWorkFlow queryById(String owfId) {
		OperateWorkFlow owf = operateWorkFlowDao.get(owfId);
		return owf;
	}

	/**
	 * 查询已选的人员
	 * 
	 * @author fuliwei
	 * @date 2018年10月26日
	 * @version 1.0
	 */
	@Override
	public Map<String, Object> queryOpStaff(OperateWorkFlowReq req) {
		// TODO Auto-generated method stub
		Staff staff;
		List<Staff> list = new ArrayList<Staff>();
		OperateWorkFlow of = operateWorkFlowDao.get(req.getOwfId());
		if (of != null) {
			String operaterId = of.getOpereaterId();
			String[] ids = operaterId.split(",");
			for (int i = 0; i < ids.length; i++) {
				Staff staffOp = staffDao.get(ids[i]);
				if (staffOp != null) {
					staff = new Staff();
					staff.setPostName(staffOp.getPostName());
					staff.setStaffName(staffOp.getStaffName());
					staff.setStaffNo(staffOp.getStaffNo());
					list.add(staff);
				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", list);
		return map;
	}

}
