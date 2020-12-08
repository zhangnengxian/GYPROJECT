package cc.dfsoft.project.biz.base.accept.dao;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;

public interface MarketInfoDao extends CommonDao<DesignInfo, String>{
	/**
	 * 查询市场营销，加载市场营销员任务表格
	 */
	public Map<String, Object> queryMarket(DesignerQueryReq designerQueryReq);
	
}
