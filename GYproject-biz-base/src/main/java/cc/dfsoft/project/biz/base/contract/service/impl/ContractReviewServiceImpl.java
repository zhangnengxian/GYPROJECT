package cc.dfsoft.project.biz.base.contract.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gexin.rp.sdk.http.utils.Constant;

import cc.dfsoft.project.biz.base.baseinfo.service.WorkFlowService;
import cc.dfsoft.project.biz.base.complete.entity.JointAcceptance;
import cc.dfsoft.project.biz.base.contract.dao.ContractReviewDao;
import cc.dfsoft.project.biz.base.contract.dto.ContractReviewDto;
import cc.dfsoft.project.biz.base.contract.entity.ContractReview;
import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.project.biz.base.contract.enums.ContractTypeEnum;
import cc.dfsoft.project.biz.base.contract.service.ContractReviewService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.SignNoticeService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import ch.qos.logback.access.servlet.Util;

/**
 * 合同评审service实现层
 * 
 * @author wanghuijun 2019年7月19日
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ContractReviewServiceImpl implements ContractReviewService {

	@Autowired
	ContractReviewDao contractReviewDao;

	@Autowired
	ProjectDao projectDao;

	@Autowired
	WorkFlowService workFlowService;

	@Autowired
	OperateRecordService operateRecordService;

	@Autowired
	SignNoticeService signNoticeService;

	@Autowired
	SignatureService signatureService;

	@Autowired
	ProjectTypeDao projectTypeDao;

	/**
	 * 保存合同评审
	 * 
	 * @param contractReview
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public synchronized String saveContractReview(ContractReview contractReview) throws Exception {
		boolean flag = false; // 是否是新增标识符，false表示不是新增，true表示新增
		Project project = projectDao.get(contractReview.getProjId()); // 根据工程ID查找记录
		project.setProjName(contractReview.getProjName()); // 设置工程名称
		project.setProjAddr(contractReview.getProjAddr()); // 设置工程地点
		// 若是新增,则生合同评审主键ID
		if (StringUtils.isBlank(contractReview.getCrId())) {
			flag = true;
			contractReview.setCrId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT)); // 生成合同评审主键ID
			signNoticeService.createFirstNotice(contractReview.getCrId(), SignDataTypeEnum.CONTRACT_REVIEW, project); // 生成签字通知
			ContractReview oldContractReview = contractReviewDao.get("projId", contractReview.getProjId()); // 通过projId查询有无实体
			if (oldContractReview != null) { // 若已有合同评审记录，则返回不再执行程序
				return "already";
			}
			projectDao.saveOrUpdate(project); // 更新工程状态
		}
		
		
		// 若是推送,存入推送日期和改变工程状态
		if (StringUtils.isNotBlank(contractReview.getPushStatus()) && "1".equals(contractReview.getPushStatus())) {
			contractReview.setPushTime(contractReviewDao.getDatabaseDate()); // 若是推送则存入推送日期
			if (project != null) { // 非空
				String projStatusId = workFlowService.queryProjStatusId(project.getCorpId(), project.getProjectType(),
						project.getContributionMode(), WorkFlowActionEnum.TO_CONTRACT_REVIEW.getActionCode(), true);// 取得下一步状态ID
				project.setProjStatusId(projStatusId); // 更新工程状态
				// 形成操作记录
				String orId = IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT);
				String todoer = operateRecordService.createOperateRecord(orId, project.getProjId(), project.getProjNo(),
						StepEnum.TO_CONTRACT_REVIEW.getValue(), StepEnum.TO_CONTRACT_REVIEW.getMessage(), "");
				project.setToDoer(todoer);// 设置下一步的待办人
				projectDao.saveOrUpdate(project); // 更新工程状态
			}
		}
		// 保存签字开始
		List<Signature> signs = contractReview.getSign();
		if (signs != null && signs.size() > 0) {
			for (Signature sign : signs) {
				sign.setDataType(SignDataTypeEnum.CONTRACT_REVIEW.getValue());
			}
			contractReview.setSign(signs);
		}
		signatureService.saveOrUpdateSign("menuId+menuNane+4", contractReview.getSign(), contractReview.getProjId(),
				contractReview.getCrId(), contractReview.getClass().getName(), flag);
		// 保存签字结束
		contractReviewDao.saveOrUpdate(contractReview); // 保存合同评审信息
		return Constants.OPERATE_RESULT_SUCCESS; // 返回操作成功标识符
	}

	/**
	 * 查询合同评审记录
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public ContractReview contractReviewDetail(String id) throws Exception {
		// TODO Auto-generated method stub
		LoginInfo loginInfo = SessionUtil.getLoginInfo(); // 登录人信息
		Project project = projectDao.get(id); // 根据主键ID取得实体
		ContractReview contractReview = new ContractReview(); // 新建对象
		ContractReview newCtractReview = contractReviewDao.get("projId", id); // 根据Id取得实体
		if (newCtractReview != null) { // 若不等于null
			contractReview = newCtractReview; // 对象进行赋值
			contractReview.setProjName(project.getProjName()); // 设置工程名称
			contractReview.setProjAddr(project.getProjAddr()); // 设置工程地点
			contractReview.setProjectTypeDes(projectTypeDao.get(project.getProjectType()).getProjTypeDes()); // 设置工程类型
			contractReview.setConNo(project.getProjNo()); // 合同编号
		} else {
			contractReview.setProjNo(project.getProjNo().toString()); // 设置工程编号
			contractReview.setProjId(project.getProjId()); // 设置工程ID
			contractReview.setProjName(project.getProjName()); // 设置工程名称
			contractReview.setProjAddr(project.getProjAddr()); // 设置工程地点
			contractReview.setProjectTypeDes(projectTypeDao.get(project.getProjectType()).getProjTypeDes()); // 设置工程类型
			contractReview.setConNo(project.getProjNo()); // 合同编号
		}

		//// 登录人是营销员则存经办人信息
		if (loginInfo.getPost().contains(PostTypeEnum.SALESMA.getValue())
				&& StringUtils.isBlank(contractReview.getOperatorId())) {
			contractReview.setOperatorId(loginInfo.getStaffId());
			contractReview.setOperator(loginInfo.getStaffName());
		}
		return contractReview; // 返回实体
	}

	/**
	 * 根据参数查询合同评审记录
	 * 
	 * @param contractReviewReq
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryContractReview(ContractReviewDto contractReviewReq) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> contractReviewMap = contractReviewDao.queryContractReview(contractReviewReq);
		List<ContractReview> contractReviewList = (List<ContractReview>) contractReviewMap.get("data");
		for (ContractReview contractReview : contractReviewList) {
			Project project = projectDao.get(contractReview.getProjId()); // 取得工程信息
			contractReview.setProjName(project.getProjName());
			contractReview.setProjAddr(project.getProjAddr());
			contractReview.setProjectTypeDes(project.getProjectTypeDes());
			contractReview.setProjectType(project.getProjectType());
			contractReview.setCorpId(project.getCorpId());
		}
		return contractReviewMap;
	}

	/**
	 * 标记打印状态
	 * 
	 * @param crId
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String signPrint(String crId) throws Exception {
		// TODO Auto-generated method stub
		ContractReview contractReview = contractReviewDao.get(crId);
		if (contractReview != null) {
			contractReview.setIsPrint(ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());
			contractReviewDao.update(contractReview);
		}
		return Constants.OPERATE_RESULT_SUCCESS; // 返回成功标识符
	}

}
