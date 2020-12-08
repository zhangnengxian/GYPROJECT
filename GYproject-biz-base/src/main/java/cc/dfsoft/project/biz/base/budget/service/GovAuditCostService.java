package cc.dfsoft.project.biz.base.budget.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.budget.entity.GovAuditCost;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;

/**
 * 
 * 描述:政府审定价service接口
 * @author liaoyq
 * @createTime 2017年9月8日
 */
public interface GovAuditCostService {

	/**
	 * 更新政府审定价
	 * @param govAuditCost
	 * @return
	 */
	String updateGovAUditCost(GovAuditCost govAuditCost);

	/**
	 * 根据工程ID和审定价类型查询详述
	 * @param projId
	 * @param gACType
	 * @return
	 */
	GovAuditCost queryByProjIdAndType(String projId, String gACType);


	/**
	 * 保存或更新审定价记录(包含附件上传)
	 * @author liaoyq
	 * @createTime 2017-8-14
	 * @param request
	 * @param uploadResult
	 * @param files 
	 * @return 记录主键ID
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	String updateBudgetFile(HttpServletRequest request,
			UploadResult uploadResult,  MultipartFile[] files) throws IllegalStateException, IOException;

	/**
	 * 审定价推送
	 * @param govAuditCost
	 */
	void pushGovAuditCost(GovAuditCost govAuditCost);

}
