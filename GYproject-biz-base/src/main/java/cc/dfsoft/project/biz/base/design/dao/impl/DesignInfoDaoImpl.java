package cc.dfsoft.project.biz.base.design.dao.impl;

import cc.dfsoft.project.biz.base.design.dao.DesignInfoDao;
import cc.dfsoft.project.biz.base.design.dto.DesignerQueryReq;
import cc.dfsoft.project.biz.base.design.entity.DesignInfo;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeptDivideEnum;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DesignInfoDaoImpl extends NewBaseDAO<DesignInfo, String> implements DesignInfoDao{
	
	/**
	 * 查询设计员，加载设计员任务表格
	 */
	@Override
	public Map<String, Object> querySurveyer(DesignerQueryReq designerQueryReq) {
		//查询参数集合
		List<Object> params = new ArrayList<Object>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select s.staff_name surveyer,s.staff_id staffId");
		//待结果登记状态
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",count(case when proj_status_id=? then proj_status_id end) surveyRegister");
			params.add(designerQueryReq.getProjStatus());
		}
		
		sql.append(" from project p right join staff s on (p.surveyer_id = s.staff_id ) where 1=1");
		
		//所属单位
		if(StringUtils.isNotBlank(designerQueryReq.getDeptId())){
			sql.append(" and (s.dept_transfer like ? or s.dept_id like ? )");
			params.add("%"+designerQueryReq.getDeptId()+"%");
			params.add(designerQueryReq.getDeptId()+"%");
		}
		//职位类型
		if(StringUtils.isNotBlank(designerQueryReq.getPostType())){
			sql.append(" and s.post like ?");
			params.add("%"+designerQueryReq.getPostType()+"%");
		}
	   //所属公司
	   if(StringUtils.isNotBlank(designerQueryReq.getCorpId())){
		   sql.append(" and s.BELONG_CORP_ID = ?");
		   params.add(designerQueryReq.getCorpId());
	   }
	   
	   sql.append(" and s.MARK_OF_DELETE = ?"); 
	   params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());   //默认查询在职人员
	   
		sql.append(" group by s.staff_name");
		
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}

	@Override
	public DesignInfo queryInfoByProjId(String projId) {
		Criteria c = super.getCriteria();
		 //工程编号
		 if(StringUtils.isNotBlank(projId)){
			 c.add(Restrictions.eq("projId",projId));
			List<DesignInfo> list=this.findByCriteria(c);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		 }
		return null;
	}
	
	/**
	 * 设计派遣页面根据设计员名称查询staffName
	 * @author fuliwei
	 * @createTime 2016-7-1
	 * @param
	 * @return	List<Staff>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> findByStaffName(String staffName) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Staff s where s.staffName='").append(staffName).append("'");
		return super.findByHql(hql.toString());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DesignInfo> findByOcoNo(String ocoNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from DesignInfo d where d.ocoNo = '").append(ocoNo).append("'");
		return super.findByHql(hql.toString());
	}
	
	/**
	 * 更新设计信息的设计人
	 * 
	 */
	@Override
	public void updateDesignInfo(Project pro) {
		DesignInfo designInfo = this.queryInfoByProjId(pro.getProjId());
		if(designInfo==null){
			designInfo = new DesignInfo();
			designInfo.setProjId(pro.getProjId());
			designInfo.setProjNo(pro.getProjNo());
		}
		designInfo.setDesigner(pro.getDesigner());
		//不更新设计完成时间，图纸签收时完成设计
		//designInfo.setDuCompleteDate(this.getDatabaseDate());
		this.update(designInfo);
	}



	/**
	 * 查询设计员
	 */
	@Override
	public Map<String, Object> queryDesigner(DesignerQueryReq designerQueryReq) {
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("select a.staff_id 'staffId',a.staff_name 'staffName'");
		if(StringUtils.isNotBlank(designerQueryReq.getProjStatus())){
			sql.append(",( select count(a.proj_no) from project a where a.designer_id = a.staff_id and a.proj_status_id =?) taskCount");
			params.add(designerQueryReq.getProjStatus());
		}
		sql.append(" from staff a where 1=1 ");

		if (StringUtils.isNotBlank(designerQueryReq.getCorpId())){
			sql.append(" and a.belong_corp_id like ? ");
			params.add(designerQueryReq.getCorpId()+"%");
		}
		if (StringUtils.isNotBlank(designerQueryReq.getPost())){
			sql.append(" and a.post like ?");
			params.add("%"+designerQueryReq.getPost()+"%");
		}
		sql.append(" and a.MARK_OF_DELETE = ?");  //默认查询在职的
		params.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());
		List<Map<String, Object>> data = this.findListBySql(sql.toString(), params.toArray());
		return ResultUtil.pageResult(data.size(), designerQueryReq.getDraw(), data);
	}

	@Override
	public Map<String, Object> getDataList(DesignerQueryReq designerQueryReq) throws ParseException {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(designerQueryReq.getProjNo())){
			c.add(Restrictions.like("projNo", "%"+designerQueryReq.getProjNo()+"%"));
		}
		if(StringUtils.isNotBlank(designerQueryReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+designerQueryReq.getProjName()+"%"));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//开始日期
		if(StringUtils.isNotBlank(designerQueryReq.getSignDateStart())){
			c.add(Restrictions.ge("ocoDate", sdf.parse(designerQueryReq.getSignDateStart())));
		}
		//结束日期
		if(StringUtils.isNotBlank(designerQueryReq.getSignDateEnd())){
			c.add(Restrictions.le("ocoDate", sdf.parse(designerQueryReq.getSignDateEnd())));
		}
		if(StringUtils.isNotBlank(designerQueryReq.getProjAddr())){
			c.add(Restrictions.like("projAddr", "%"+designerQueryReq.getProjAddr()+"%"));
		}
		if(StringUtils.isNotBlank(designerQueryReq.getDesigner())){
			c.add(Restrictions.like("designer", "%"+designerQueryReq.getDesigner()+"%"));
		}
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		String post = loginInfo.getPost();

		if(!DeptDivideEnum.DESIGN_INSTITUTE.getValue().equals(loginInfo.getDeptDivide())){
			//不是设计院
			StringBuffer sql = new StringBuffer(" proj_id in(select proj_id from project where corp_id like '%").append(loginInfo.getCorpId()).append("%')");
			c.add(Restrictions.sqlRestriction(sql.toString()));
		}else{
			if(post!=null && post.contains(PostTypeEnum.DESIGNER.getValue())){
				StringBuffer hql = new StringBuffer();
				hql.append(" proj_id in(select proj_id from project where designer_id='").append(loginInfo.getStaffId()).append("'");
				c.add(Restrictions.sqlRestriction(hql.toString()));
			}
		}

		c.addOrder(Order.desc("ocoDate"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<DesignInfo> projectList = this.findBySortCriteria(c, designerQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, designerQueryReq.getDraw(),projectList);

	}
}
