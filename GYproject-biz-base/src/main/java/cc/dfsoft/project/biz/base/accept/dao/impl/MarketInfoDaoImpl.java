package cc.dfsoft.project.biz.base.accept.dao.impl;

import cc.dfsoft.project.biz.base.accept.dao.MarketInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class MarketInfoDaoImpl extends NewBaseDAO<DesignInfo, String> implements MarketInfoDao {

	/**
	 * 查询市场营销，加载市场营销员任务表格
	 */
	@Override
	public Map<String, Object> queryMarket(DesignerQueryReq designerQueryReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select s.staff_name market,s.staff_id staffId");
		//待结果登记状态
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",count(case when proj_status_id=? then proj_status_id end) marketRegister");
			params.add(designerQueryReq.getProjStatus());
		}
		sql.append(" from project p right join staff s on (p.accepter_id = s.staff_id ) where 1=1");
		//所属单位
		if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
			sql.append(" and s.dept_id like ?");
			params.add(designerQueryReq.getDeptId()+"%");
		}
		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.post like ?");
			params.add("%"+designerQueryReq.getPostType()+"%");
		}
		sql.append(" group by s.staff_name");
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}


	
}
