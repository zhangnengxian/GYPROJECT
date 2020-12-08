package cc.dfsoft.project.biz.base.baseinfo.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.baseinfo.dao.SafetyPunishDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.SafetyPunishReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.SafetyPunish;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Repository
public class SafetyPunishDaoImpl extends NewBaseDAO<SafetyPunish,String> implements SafetyPunishDao{

	@Override
	public Map<String, Object> querySafetyPunish(SafetyPunishReq safetyPunishReq) throws ParseException {
		
		Criteria c = super.getCriteria();
		
		if (StringUtils.isNotBlank(safetyPunishReq.getCorpId())) {
			c.add(Restrictions.like("corpId", "%"+safetyPunishReq.getCorpId()+"%"));
		}
		
		//根据主键Id进行查询
		if(StringUtils.isNotBlank(safetyPunishReq.getId())){
	        c.add(Restrictions.eq("id",safetyPunishReq.getId()));
		}
		c.add(Restrictions.sqlRestriction("id like '"+safetyPunishReq.getCorpId()+"-__'"));
		//名称
		if(StringUtils.isNotBlank(safetyPunishReq.getDes())){
			c.add(Restrictions.like("des","%"+safetyPunishReq.getDes()+"%"));
		}
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		// 根据条件获取查询信息
		List<SafetyPunish> list = this.findBySortCriteria(c, safetyPunishReq);
		// 返回结果
		return ResultUtil.pageResult( filterCount, safetyPunishReq.getDraw(),list);
	}

	@Override
	public String findId(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("select Max(id) from safety_punish where 1=1");
		if(StringUtils.isNotBlank(id)){
			sql.append(" and id like '").append(id).append("%'");
			sql.append(" and id like '____'");
		}else{
			sql.append(" and id like '__'");
		}
		List list = super.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			String maxId = (String) list.get(0);
			if(StringUtils.isNotBlank(maxId)){
				int nextId = Integer.parseInt(maxId)+1;
				return ""+nextId;
			}
		}
		return "";
	}
	
	@Override
	public String findSmId(String id) {
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		StringBuffer sql = new StringBuffer();
		sql.append("select Max(id) from safety_punish where 1=1");
		if(StringUtils.isNotBlank(id)){
			sql.append(" and id like '").append(id).append("%'");
			//sql.append(" and id like '____'");
			sql.append(" and id like '").append(loginInfo.getCorpId()).append("-____'");
		}else{
			//sql.append(" and id like '__'");
			sql.append(" and id like '").append(loginInfo.getCorpId()).append("-__'");
		}
		List list = super.findBySql(sql.toString());
		if(list!=null && list.size()>0){
			String maxId = (String) list.get(0);
			if(StringUtils.isNotBlank(maxId)){
				String [] ids=maxId.split("-");
				String thisId=ids[ids.length-1];
				int nextId = Integer.parseInt(thisId)+1;
				return ids[0]+"-"+nextId;
			}
		}
		return "";
	}

	

	@Override
	public Map<String, Object> querySafetyPunishMin(SafetyPunishReq safetyPunishReq) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(safetyPunishReq.getId())){
	        c.add(Restrictions.like("id",safetyPunishReq.getId()+"%"));
		}
		    //c.add(Restrictions.sqlRestriction("id like '____'"));
		c.add(Restrictions.sqlRestriction("id like '"+safetyPunishReq.getCorpId()+"-____'"));    
		 // 数据库根据条件过滤记录数
			int filterCount = this.countByCriteria(c);
			// 根据条件获取查询信息
			List<SafetyPunish> list = this.findBySortCriteria(c, safetyPunishReq);
			// 返回结果
			return ResultUtil.pageResult( filterCount, safetyPunishReq.getDraw(),list);
	}

	@Override
	public void deleteAll(String id) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from safety_punish where 1=1");
		if(StringUtils.isNotBlank(id)){
			sql.append(" and id like '").append(id).append("%'");
		}
		super.executeSql(sql.toString());
	}
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<SafetyPunish> getByType(String type,String corpId){
		Criteria c = super.getCriteria();
		 c.add(Restrictions.eq("type", type));
		 
		 if(StringUtil.isNotBlank(corpId)){
			 c.add(Restrictions.like("corpId", "%"+corpId+"%"));
		 }
		 
		 List<SafetyPunish> list = this.findByCriteria(c);
		 return list;
	}
}
