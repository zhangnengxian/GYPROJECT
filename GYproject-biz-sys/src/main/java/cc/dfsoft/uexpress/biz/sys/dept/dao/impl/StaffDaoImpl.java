package cc.dfsoft.uexpress.biz.sys.dept.dao.impl;

import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.converter.StaffDtoConverter;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.dto.DeptStaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffDto;
import cc.dfsoft.uexpress.biz.sys.dept.dto.StaffQueryReq;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.DeleteOfMarkEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.MapUtils;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 员工处理DAO实现类
 * @author 1919
 *
 */
@Repository
public class StaffDaoImpl extends NewBaseDAO<Staff, String> implements StaffDao {

	@Override
	public void insertStaff(Staff staff) {
		this.save(staff);
	}

	@Override
	public boolean deleteStaff(String tenantId, String staffId) {
		String hql = "update Staff set markOfDelet = "+DeleteOfMarkEnum.LEAVE_JOB.getValue()+" where staffId=?";
		return this.executeHql(hql, new Object[]{staffId});
	}

	@Override
	public void updateStaff(Staff staff) {
		this.update(staff);
	}

	@Override
	public Staff queryStaff(String tenantId, String staffId) {
		Criteria criteria = super.getCriteria();
		if(StringUtil.isNotBlank(staffId)){  //员工ID
			criteria.add(Restrictions.eq("staffId", staffId));
		}
		List<Staff> staffList = this.findByCriteria(criteria);
		if(staffList.size() > 0){
			return staffList.get(0);
		}

		return null;
	}

	@Override
	public Map<String, Object> queryStaffList(StaffQueryReq staffQueryReq) {
		Criteria criteria = super.getCriteria();
		//criteria.add(Restrictions.eq("tenantId", loginInfo.getTenantId()));
		//criteria.add(Restrictions.eq("deptId", loginInfo.getDeptId()));
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		// 过滤条件
		if (StringUtil.isNotBlank(staffQueryReq.getPost())) {
			criteria.add(Restrictions.like("post", "%"+staffQueryReq.getPost()+"%"));
		}
		if(StringUtil.isNotBlank(staffQueryReq.getStaffName())){
			criteria.add(Restrictions.like("staffName", "%"+staffQueryReq.getStaffName()+"%"));
		}
		//员工编号
		if(StringUtils.isNotBlank(staffQueryReq.getStaffNo())){
			criteria.add(Restrictions.like("staffNo", "%"+staffQueryReq.getStaffNo()+"%"));
		}
		//单位类型
		if(StringUtils.isNotBlank(staffQueryReq.getUnitType())){
			criteria.add(Restrictions.eq("unitType", staffQueryReq.getUnitType()));
		}
		if(StringUtil.isBlank(staffQueryReq.getMarkOfDelet())){
			criteria.add(Restrictions.eq("markOfDelet", DeleteOfMarkEnum.ON_THE_JOB.getValue()));  //默认查询有效人员
		}else{
			criteria.add(Restrictions.eq("markOfDelet", staffQueryReq.getMarkOfDelet()));  //默认查询有效人员
		}
		//部门Id
		//查看如果后台配置过滤条件：查询的 部门+职务
		List<DataFilerSetUpDto> list = Constants.getDataFilterMapByKey(staffQueryReq.getDeptId()+"-"+staffQueryReq.getPost()+"_"+(StringUtil.isNotBlank(staffQueryReq.getMenuId())?staffQueryReq.getMenuId():"0"));

		if(list!=null && list.size()>0){
			StringBuffer hql = new StringBuffer();
			if(StringUtils.isNotBlank(list.get(0).getSupSql())){
				hql.append(list.get(0).getSupSql());
			}
			criteria.add(Restrictions.sqlRestriction(hql.toString()));
		}else{
			//用当前登录人部门id 配置 用于配置六盘水查询多个设计单位的设计员
			List<DataFilerSetUpDto> degisnlist = Constants.getDataFilterMapByKey(loginInfo.getDeptId()+"-"+staffQueryReq.getPost()+"_"+(StringUtil.isNotBlank(staffQueryReq.getMenuId())?staffQueryReq.getMenuId():"0"));
			if(degisnlist!=null && degisnlist.size()>0){

			} else{
				if(StringUtils.isNotBlank(staffQueryReq.getDeptId())){
					if(staffQueryReq.getDeptId().indexOf(",")>0){
						String [] deptIds = staffQueryReq.getDeptId().split(",");
						Disjunction dis = Restrictions.disjunction();
						for (int i = 0; i < deptIds.length; i++) {
							dis.add(Restrictions.like("deptId", deptIds[i]+"%"));
						}
						criteria.add(dis);
					}else{
						criteria.add(Restrictions.like("deptId", staffQueryReq.getDeptId()+"%"));
					}
				}
			}
		}
		//在前台页面传corp_id,查询属于分公司派驻的人，如设计员、项目经理等
		if(StringUtils.isNotBlank(staffQueryReq.getCorpId())){
			criteria.add(Restrictions.like("belongCorpId", "%"+staffQueryReq.getCorpId()+"%"));
		}


		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(criteria);

		// 根据条件获取查询信息
		List<Staff> staffList = this.findBySortCriteria(criteria, staffQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, staffQueryReq.getDraw(),
				StaffDtoConverter.convert(staffList));
	}

	@Override
	public Map<String, Object> queryDeptStaffList(StaffQueryReq staffQueryReq) {
		String tenantId = SessionUtil.getTenantId();
		LoginInfo loginInfo = SessionUtil.getLoginInfo();  //得到登陆人信息
		List<Object> paramList = new ArrayList<Object>();
		String filterSql="";
		String selSql="";
		if(staffQueryReq.getCorpType()==null){
			staffQueryReq.setCorpType("0");
		}
		if(staffQueryReq.getCorpType()==null){
			staffQueryReq.setUnitType("1");
		}

		if(staffQueryReq.getCorpType().equals("0")){
			boolean existStaff = this.isExistStaff(staffQueryReq.getLoginAccount());
			if (existStaff) {//本公司管理人员只能看本公司员工账号
				filterSql = " staff a,department b where a.dept_id = b.dept_id and b.dept_id like '" + loginInfo.getCorpId() + "%'";
			}else {//设计单位
				filterSql = " staff a,department b where a.dept_id = b.dept_id";
			}

			selSql = "select *  from (select a.staff_id as STAFFID, a.staff_no as STAFFNO, a.login_account as LOGINACCOUNT,post as POST,a.post_name as POSTNAME, "
					+ "a.staff_name as STAFFNAME, a.mobile as MOBILE, a.phone as PHONE, b.dept_name as DEPTNAME,"+mysqlSqlConveter.rownumberConveter(" rn from ");

		}else{
			filterSql = " staff a,business_partners b where a.dept_id = b.unit_id ";
			selSql = "select *  from (select a.staff_id as STAFFID, a.staff_no as STAFFNO, a.login_account as LOGINACCOUNT,post as POST,a.post_name as POSTNAME, "
					+ "a.staff_name as STAFFNAME, a.mobile as MOBILE, a.phone as PHONE, b.unit_name as DEPTNAME, "+mysqlSqlConveter.rownumberConveter(" rn from ");
		}

		if(StringUtil.isNotEmpty(staffQueryReq.getStaffName())){
			filterSql += " and a.staff_name like ?";
			paramList.add("%"+staffQueryReq.getStaffName()+"%");
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getLoginAccount())){
			filterSql += " and a.login_account = ?";
			paramList.add(staffQueryReq.getLoginAccount());
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getUnitType())){
			filterSql += " and a.unit_type = ?";
			paramList.add(staffQueryReq.getUnitType());
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getPost())){
			filterSql += " and a.post like ?";
			paramList.add("%"+staffQueryReq.getPost()+"%");
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getUnitName())){
			filterSql += " and b.dept_name like ?";
			paramList.add("%"+staffQueryReq.getUnitName()+"%");
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getCorpId())){
			filterSql += " and a.corp_id like ?";
			paramList.add(staffQueryReq.getCorpId()+"%");
		}
		if(StringUtil.isNotEmpty(staffQueryReq.getDeptId())){
			filterSql += " and a.dept_id like ?";
			paramList.add(staffQueryReq.getDeptId()+"%");
		}
		if(StringUtil.isBlank(staffQueryReq.getMarkOfDelet())){  //员工在职状态
			filterSql += " and a.MARK_OF_DELETE = ?";  //默认查询在职的员工
			paramList.add(DeleteOfMarkEnum.ON_THE_JOB.getValue());
		}else{
			filterSql += " and a.MARK_OF_DELETE = ? " ;  //默认查询未删除的员工
			paramList.add(staffQueryReq.getMarkOfDelet());
		}



		//oracle
		//String filterCountSql = "select count(a.staff_id) from" + filterSql;
		//mysql
		String filterCountSql = "select count(a.staff_id) from" + filterSql;
		int filterCount = this.getCountBySql(filterCountSql, paramList.toArray());
		int start = staffQueryReq.getStart()+1;
		int end = start+(staffQueryReq.getLength()-1);
		selSql += filterSql ;
		selSql += " order by  "+staffQueryReq.getSortName()+" "+staffQueryReq.getSort()+") result";
		selSql += " where result.rn between "+start+" and "+ end;

		List<Map<String, Object>> mapList = this.findListBySql(selSql, paramList.toArray());


		List<DeptStaffDto> deptStaffDtoList = new ArrayList<DeptStaffDto>();
		// 组装对象，将获得的map值set到对象中
		if (mapList != null && mapList.size() > 0) {
			for(Map<String, Object> map : mapList){
				DeptStaffDto deptStaffDto = new DeptStaffDto();
				deptStaffDto.setDeptName((String)map.get("DEPTNAME"));
				deptStaffDto.setLoginAccount((String)map.get("LOGINACCOUNT"));
				deptStaffDto.setMobile((String)map.get("MOBILE"));
				deptStaffDto.setPost((String)map.get("POST"));
				deptStaffDto.setPostName((String)map.get("POSTNAME"));
				deptStaffDto.setPhone((String)map.get("PHONE"));
				deptStaffDto.setStaffId((String)map.get("STAFFID"));
				deptStaffDto.setStaffName((String)map.get("STAFFNAME"));
				deptStaffDto.setStaffNo((String)map.get("STAFFNO"));
				deptStaffDtoList.add(deptStaffDto);
			}
		}
		// 返回结果
		return ResultUtil.pageResult( filterCount, staffQueryReq.getDraw(), deptStaffDtoList);
	}

	@Override
	public Staff queryStaffLoginInfo(String tenantId, String loginAccount) {
		String hql = "from Staff where loginAccount= ? and  markOfDelet = " +DeleteOfMarkEnum.ON_THE_JOB.getValue();  //查询在职员工
		List<Object> params = new ArrayList<>();
		params.add(loginAccount);
		List list = this.findByHql(hql,params.toArray());
		if (list!=null && list.size()>0) {
			return (Staff) list.get(0);
		}
		return null;
	}

	@Override
	public List<Staff> queryStaffByDeptId(String tenantId, String deptId) {
		Criteria criteria = super.getCriteria();

		// 过滤条件
		if (StringUtil.isNotBlank(tenantId)) {
			//	criteria.add(Restrictions.eq("tenantId", tenantId));
		}
		if (StringUtil.isNotBlank(deptId)) {
			criteria.add(Restrictions.eq("deptId", deptId));
		}

		List<Staff> staffList = this.findByCriteria(criteria);
		return staffList;
	}

	@Override
	public void updatePassword(String tenantId, String loginAccount, String password) {
		//String hql = "update Staff set password = ? where tenantId = ? and staffId = ?";
		String hql = "update Staff set password = ? where  loginAccount = ?";
		this.executeHql(hql, new Object[]{password, loginAccount});
	}

	@Override
	public List<Staff> findByStaffNo(String staffNo) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Staff where staffNo = ?");
		List<Object> params = new ArrayList<>();
		params.add(staffNo);
		return super.findByHql(hql.toString(),params.toArray());
	}

	/**
	 * 按角色查询员工
	 * @author
	 * @createTime 2016-12-18
	 * @param roleId
	 * @return Staff
	 */
	@Override
	public List findByRoleId(String roleId) {
		StringBuffer sql=new StringBuffer("select * from staff_role where role_ids like '%").append(roleId).append("%'");
		List list=this.findBySql(sql.toString());
		return list;
	}

	/**
	 * 查询职务
	 * @author fuliwei
	 * @createTime 2017-1-21
	 * @param
	 * @return List
	 */
	@Override
	public List queryPost(String post) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select staff_name staffName from staff where staff.post like '%").append(post).append("%'");
		List list=this.findBySql(sql.toString());
		return list;
	}

	/**
	 * 查询员工列表信息
	 * @param staffQueryReq
	 * @return
	 */
	@Override
	public Map<String, Object> queryManageStaffList(StaffQueryReq staffQueryReq) {

		Criteria c=super.getCriteria();

		if(StringUtil.isNotBlank(staffQueryReq.getPost())){
			c.add(Restrictions.like("post","%"+staffQueryReq.getPost()+"%"));
		}
		//员工编号
		if(StringUtils.isNotBlank(staffQueryReq.getCorpId())){
			c.add(Restrictions.like("corpId",staffQueryReq.getCorpId()+"%"));
		}
		//员工部门
		if(StringUtils.isNotBlank(staffQueryReq.getDeptId())){
			c.add(Restrictions.like("deptId",staffQueryReq.getDeptId()+"%"));
		}

		//所属分公司id
		if(StringUtils.isNotBlank(staffQueryReq.getBelongCorpId())){
			c.add(Restrictions.like("belongCorpId","%"+staffQueryReq.getBelongCorpId()+"%"));
		}

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Staff> projectList = this.findBySortCriteria(c, staffQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, staffQueryReq.getDraw(),projectList);
	}

	@Override
	public List<Staff> findByLoginAccount(String loginAccount) {
		StringBuffer hql = new StringBuffer();
		hql.append("from Staff where loginAccount = ?");
		List<Object> params = new ArrayList<>();
		params.add(loginAccount);
		return super.findByHql(hql.toString(),params.toArray());
	}

	@Override
	public List<Map<String, Object>> getUserByIds(String[] ids) {
		String sql0 = "select REGISTRATIONID,LOGIN_ACCOUNT,STAFF_NAME from staff where STAFF_ID in (";
		String sql2 = "";
		for(String id :ids){
			if("".equals(sql2)){
				sql2 += "'"+id+"'";
			}else {
				sql2 += ",'"+id+"'";
			}
		}
		String sql = sql0 + sql2 +")";
		return this.findListBySql(sql);
	}




	@Override
	public List<Staff> findStaffListByBelongCorpIdAndPost(String corpId,String suId,String cuId) {
		Criteria c=super.getCriteria();

		if(StringUtil.isNotBlank(corpId)){
			c.add(Restrictions.like("belongCorpId","%"+corpId+"%"));
		}
		if(StringUtil.isNotBlank(suId)||StringUtil.isNotBlank(cuId)){
			c.add(Restrictions.in("deptId",new Object[]{suId,cuId}));
		}

		c.add(Restrictions.eq("markOfDelet","1"));

		return this.findByCriteria(c);
	}



	/**
	 * 查询操作人--操作流程设置查询使用
	 * @param request
	 * @param staffQueryReq
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> queryOperateStaff(StaffQueryReq staffQueryReq) throws Exception {
		Criteria c=super.getCriteria();

		if(StringUtil.isNotBlank(staffQueryReq.getPost())){
			c.add(Restrictions.like("post","%"+staffQueryReq.getPost()+"%"));
		}
		//公司ID
		if(StringUtils.isNotBlank(staffQueryReq.getCorpId())){  //"corpId",staffQueryReq.getCorpId()+"%")
			c.add(Restrictions.or(Restrictions.like("corpId", staffQueryReq.getCorpId()+"%"), Restrictions.like("belongCorpId", "%"+staffQueryReq.getCorpId()+"%")));
		}
		//员工部门
		if(StringUtils.isNotBlank(staffQueryReq.getDeptId())){
			c.add(Restrictions.like("deptId",staffQueryReq.getDeptId()+"%"));
		}
		//员工ＩＤ
		if(StringUtils.isNotBlank(staffQueryReq.getStaffNo())){
			c.add(Restrictions.like("staffId", "%"+staffQueryReq.getStaffNo()+"%"));

		}
		//员工名字
		if(StringUtils.isNotBlank(staffQueryReq.getStaffName())){
			c.add(Restrictions.like("staffName", "%"+staffQueryReq.getStaffName()+"%"));

		}

			/*//所属分公司id
			if(StringUtils.isNotBlank(staffQueryReq.getBelongCorpId())){
				c.add(Restrictions.like("belongCorpId","%"+staffQueryReq.getBelongCorpId()+"%"));
			}*/

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Staff> projectList = this.findBySortCriteria(c, staffQueryReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, staffQueryReq.getDraw(),projectList);
	}

	@Override
	public boolean isExistStaff(String staffId) {
		if (StringUtil.isBlank(staffId)) return true;
		List<Object> paramList=new ArrayList<>();
		StringBuilder sql=new StringBuilder();
		sql.append("select a.* from staff a,department b where a.DEPT_ID=b.DEPT_ID and a.STAFF_ID='").append(staffId).append("'").append(" and b.DEPT_TYPE !=2");
		List<Map<String, Object>> list = this.findListBySql(sql.toString(),paramList.toArray());
		return (list!=null && list.size()>0)?true:false;
	}

	@Override
	public StaffDto obtainOperater(String staffCorpId,String roleCorpId, String projectType, String contributionMode, String backStepId, int i) {
		List<Object> paramList=new ArrayList<>();
		StringBuilder querySql=new StringBuilder();
		querySql.append("SELECT a.staff_id 'staffId' , a.staff_name 'staffName',b.grade 'grade' FROM sys_staff_role a, role b  WHERE a.role_id = b.role_id ");
		querySql.append(" AND a.corp_id =? ");
		querySql.append(" AND b.corp_id =? ");
		querySql.append(" AND b.proj_type =? ");
		querySql.append(" AND b.conb =? ");
		querySql.append(" AND b.step_id =? ");
		querySql.append(" AND b.grade =? ");

		paramList.add(staffCorpId);
		paramList.add(roleCorpId);
		paramList.add(projectType);
		paramList.add(contributionMode);
		paramList.add(backStepId);
		paramList.add(i);

		List<Map<String, Object>> listBySql = this.findListBySql(querySql.toString(),paramList.toArray());
		if (listBySql==null || listBySql.size()<1) return null;

		List<StaffDto> dtoList = MapUtils.convertListMap2ListBean(listBySql, StaffDto.class);

		StaffDto staffDto=new StaffDto();
		for (StaffDto s:dtoList) {
			staffDto.setGrade(s.getGrade());

			if (StringUtil.isNotBlank(staffDto.getStaffId())){
				staffDto.setStaffId(staffDto.getStaffId()+","+s.getStaffId());
			}else {
				staffDto.setStaffId(s.getStaffId());
			}

			if (StringUtil.isNotBlank(staffDto.getStaffName())){
				staffDto.setStaffName(staffDto.getStaffName()+","+s.getStaffName());
			}else {
				staffDto.setStaffName(s.getStaffName());
			}
		}
		return staffDto;
	}

	@Override
	public List<Staff> queryStaffListByIds(String[] staffIds) {
		Criteria c=super.getCriteria();
		c.add(Restrictions.in("staffId",staffIds));
		return this.findByCriteria(c);
	}

	/**
	 * 获得员工所有的菜单id
	 * @author fuliwei
	 * @date 2019/9/2
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getStaffMenuIdList(String staffId) {
		String  sql= "select menu.menu_id MENU_ID";
				sql+=" from staff staff ,staff_role sr,role_menu rm,menu menu";
				sql+=" where staff.staff_id = sr.staff_id and FIND_IN_SET(rm.role_id,sr.role_ids)>0 ";
				sql+=" and  FIND_IN_SET(menu.menu_id,rm.menu_ids)>0 ";
				sql+=" and staff.STAFF_ID='"+staffId+"'";
		List<Map<String, Object>> listBySql = this.findListBySql(sql, new Object[]{});
		return  listBySql;
	}

	@Override
	public void clearRegistrationID(String staffId,String registrationID) {
		String hql = "update Staff set registrationId ='' where staffId !=? and registrationId = ?";
		this.executeHql(hql, new Object[]{staffId,registrationID});
	}

	@Override
	public void clearCurStaffRegistrationId(String staffId,
			String registrationId) {
		String hql = "update Staff set registrationId ='' where staffId =? and registrationId = ?";
		this.executeHql(hql, new Object[]{staffId,registrationId});
	}
}
