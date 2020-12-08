package cc.dfsoft.project.biz.base.complete.dao;

import java.util.List;

import cc.dfsoft.project.biz.base.complete.entity.FilingData;
import cc.dfsoft.uexpress.common.dao.CommonDao;

public interface DataFeedbackDao  extends CommonDao<FilingData, String>{

	List<FilingData> findByProjNo(String id);


}
