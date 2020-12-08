package cc.dfsoft.project.biz.base.complete.dao.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.complete.dao.GasApplyDao;
import cc.dfsoft.project.biz.base.complete.dto.GasApplyReq;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.biz.sys.dept.enums.PostTypeEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Repository
public class GasApplyDaoImpl extends NewBaseDAO<GasApply, String> implements GasApplyDao{
	
	/**
	 * 根据工程id查询GasApply
	 * @param id
	 * @return
	 */
	@Override
	public GasApply viewGasApplyById(String id) {
		Criteria c = super.getCriteria();
		if(StringUtils.isNotBlank(id)){
			c.add(Restrictions.eq("projId", id));
			List<GasApply> list = this.findByCriteria(c);
			if(list != null && list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public Map<String, Object> queryGasApply(GasApplyReq gasApplyReq) throws ParseException {
		Criteria c = super.getCriteria();
		//工程编号
		if(StringUtils.isNotBlank(gasApplyReq.getProjNo())){
			c.add(Restrictions.eq("projNo",gasApplyReq.getProjNo()));
		}
		//状态
		if(StringUtils.isNotBlank(gasApplyReq.getConfrimState())){
			c.add(Restrictions.eq("confrimState",gasApplyReq.getConfrimState()));
		}
		//工程名称
		if(StringUtils.isNotBlank(gasApplyReq.getProjName())){
			c.add(Restrictions.like("projName", "%"+gasApplyReq.getProjName()+"%"));
		}
		//通气类型
		if(StringUtils.isNotBlank(gasApplyReq.getGasType())){
			c.add(Restrictions.eq("gasType",gasApplyReq.getGasType()));
		}
		//用于字符串日期格式转换
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//通气申请日期开始日期
		if(StringUtils.isNotBlank(gasApplyReq.getGasApplyTimeStart())){
			c.add(Restrictions.ge("gasApplyTime", sdf.parse(gasApplyReq.getGasApplyTimeStart())));
		}
		//通气申请日期结束日期
		if(StringUtils.isNotBlank(gasApplyReq.getGasApplyTimeEnd())){
			c.add(Restrictions.le("gasApplyTime", sdf.parse(gasApplyReq.getGasApplyTimeEnd())));
		}
		//通气日期开始日期
		if(StringUtils.isNotBlank(gasApplyReq.getConfirmGasTimeStart())){
			c.add(Restrictions.ge("confirmGasTime", sdf.parse(gasApplyReq.getConfirmGasTimeStart())));
		}
		//通气日期结束日期
		if(StringUtils.isNotBlank(gasApplyReq.getConfirmGasTimeEnd())){
			c.add(Restrictions.le("confirmGasTime", sdf.parse(gasApplyReq.getConfirmGasTimeEnd())));
		}
		LoginInfo loginInfo=SessionUtil.getLoginInfo();
		
		// 当前登录操作者 管网工艺员
		if (StringUtil.isNotBlank(loginInfo.getPost()) && loginInfo.getPost().contains(PostTypeEnum.WEBSITE_ENGINEER.getValue())) {
			//通过分包单位人员id查询
			 StringBuffer sql = new StringBuffer("proj_id in(select cp.proj_id from project cp where cp.area='").append(loginInfo.getDeptId()).append("')");
			 c.add(Restrictions.sqlRestriction(sql.toString()));
		}
		
		c.addOrder(Order.desc("gasApplyTime"));
		
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<GasApply> gasApplyList = this.findBySortCriteria(c, gasApplyReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, gasApplyReq.getDraw(),gasApplyList);
	}

}
