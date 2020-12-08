package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.entity.FilingData;

public interface DataFeedbackService {
	
	/**
	 * 归档资料详述
	 * @param id
	 */
	FilingData jointDetail(String id);
	
	/**
	 * 保存归档资料
	 * @param jointAcceptance
	 */
	void saveData(FilingData filingData) throws Exception;
}
