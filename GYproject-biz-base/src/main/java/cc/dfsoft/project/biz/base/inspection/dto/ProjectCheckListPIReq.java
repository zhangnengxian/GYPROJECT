package cc.dfsoft.project.biz.base.inspection.dto;

import cc.dfsoft.project.biz.base.inspection.entity.PreservativeInpect;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;

/**
 * 防腐检查辅助类
 * @author liaoyq
 *
 */
public class ProjectCheckListPIReq extends ProjectChecklist {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PreservativeInpect preservativeInpect; //防腐检查
	
	public PreservativeInpect getPreservativeInpect() {
		return preservativeInpect;
	}
	public void setPreservativeInpect(PreservativeInpect preservativeInpect) {
		this.preservativeInpect = preservativeInpect;
	}
	
}
