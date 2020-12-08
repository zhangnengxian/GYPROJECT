package cc.dfsoft.project.biz.base.contract.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.contract.dao.ProjectCostDao;
import cc.dfsoft.project.biz.base.contract.entity.ProjectCost;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;

@Repository
public class ProjectCostDaoImpl  extends NewBaseDAO<ProjectCost, String> implements ProjectCostDao{

	@Override
	public ProjectCost queryByProjId(String projId) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(projId)){
			c.add(Restrictions.eq("projId", projId));
		}
		List<ProjectCost> list = this.findByCriteria(c);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询派工的造价员
	 */
	@Override
	public Map<String, Object> queryCoster(DesignerQueryReq designerQueryReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();

		StringBuilder sql = new StringBuilder();
		sql.append("select s.STAFF_NAME costMember,s.STAFF_ID costMemberId");
		//待造价记录
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			//sql.append(",count(case when PROJ_STATUS_ID=? and m.COST_MEMBER_ID is null then PROJ_STATUS_ID end) costMemberTask");
			sql.append(",count(case when PROJ_STATUS_ID=?  then PROJ_STATUS_ID end) costMemberTask");

			params.add(designerQueryReq.getProjStatus());
		}
		//sql.append(" from project p right join staff s on (p.COST_MEMBER_ID = s.STAFF_ID) left join project_cost m on ( s.STAFF_ID = m.COST_MEMBER_ID ) where 1=1");
		sql.append(" from project p right join staff s on (p.COST_MEMBER_ID = s.STAFF_ID)  where 1=1");

		LoginInfo loginInfo= SessionUtil.getLoginInfo();
		//其他人员可查询自己部门的造价员
		sql.append(" and s.CORP_ID like ?");
		params.add(loginInfo.getCorpId()+"%");
		
		sql.append(" and s.MARK_OF_DELETE = ?");
		params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());  //查在职员工
		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.POST like ?");
			params.add("%"+designerQueryReq.getPostType()+"%");
		}
		//部门
		if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
			sql.append(" and s.DEPT_ID like ?");
			params.add(designerQueryReq.getDeptId()+"%");
		}
		sql.append(" group by s.STAFF_NAME,s.STAFF_ID");

		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}

}
