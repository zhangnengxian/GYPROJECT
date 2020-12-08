package cc.dfsoft.project.biz.base.complete.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.complete.dao.CompletionTransferDao;
import cc.dfsoft.project.biz.base.complete.dto.CompletionTransferReq;
import cc.dfsoft.project.biz.base.complete.entity.CompletionTransfer;
import cc.dfsoft.project.biz.base.complete.service.CompletionTransferService;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import net.sf.json.JSONSerializer;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class CompletionTransferServiceImpl implements CompletionTransferService {

	@Resource
	CompletionTransferDao completionTransferDao;
	
	@Resource
	SignatureService signatureService;
	
	@Override
	public Map<String, Object> queryCompletionTransfer(CompletionTransferReq completionTransferReq) {
		Map<String,Object> result = completionTransferDao.queryCompletionTransfer(completionTransferReq);
		List<CompletionTransfer> data =  (List<CompletionTransfer>) result.get("data");
		  if(data!=null && data.size()>0){

			for(int i=0 ;i<data.size();i++){
				Map<String,String> levelBtn = new HashMap();
				levelBtn.put("level1", "2"); 	// 待审
				levelBtn.put("level2", "-1"); 	// 未审
				levelBtn.put("level3", "-1"); 	// 未审
				levelBtn.put("level4", "-1"); 	// 未审
				
				if(data.get(i).getFilingDate() != null){
					levelBtn.put("level1", "1"); 	//审核通过
					levelBtn.put("level2", "1"); 	//审核通过
					levelBtn.put("level3", "1");    //审核通过
					levelBtn.put("level4", "1");    //审核通过
				}else if(data.get(i).getCmoAuditCompleted() != null){
					levelBtn.put("level1", "1");    // 审核通过
					levelBtn.put("level2", "1");    // 审核通过
					levelBtn.put("level3", "1");    // 审核通过
					levelBtn.put("level4", "2");    // 待审
				}else if(data.get(i).getSuAuditCompleted() != null){
					levelBtn.put("level1", "1"); 	// 审核通过
					levelBtn.put("level2", "1"); 	// 审核通过
					levelBtn.put("level3", "2"); 	// 待审
					levelBtn.put("level4", "-1"); 	// 未审
				}else if(data.get(i).getReciveDate() != null){
					levelBtn.put("level1", "1");  	// 审核通过
					levelBtn.put("level2", "2");  	// 待审
					levelBtn.put("level3", "-1"); 	// 未审
					levelBtn.put("level4", "-1"); 	// 未审
				}
				data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		  }
		  return result;
		}

	@Override
	public void saveCompletionTransfer(CompletionTransfer completionTransfer) throws Exception {
		boolean flag = false;
		if(StringUtils.isBlank(completionTransfer.getCtId())){
			flag = true;
			completionTransfer.setCtId(IDUtil.getUniqueId(Constants.MODULE_CODE_COMPLETE));
		}
		completionTransferDao.saveOrUpdate(completionTransfer);
		signatureService.saveOrUpdateSign("menuId+menuNane",completionTransfer.getSign(), completionTransfer.getProjId(), completionTransfer.getCtId(), completionTransfer.getClass().getName(),flag);
	}
	
}