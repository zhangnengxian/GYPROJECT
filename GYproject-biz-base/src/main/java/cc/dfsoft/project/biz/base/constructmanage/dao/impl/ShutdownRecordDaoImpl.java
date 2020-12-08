package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mchange.lang.IntegerUtils;

import cc.dfsoft.project.biz.base.constructmanage.dao.ShutdownRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ShutdownRecordReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ShutDownRecord;
import cc.dfsoft.project.biz.base.constructmanage.enums.ShutdownStatusEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 停复工dao实现层
 * @author liaoyq
 *
 */
@Repository
public class ShutdownRecordDaoImpl extends NewBaseDAO<ShutDownRecord, String>
		implements ShutdownRecordDao {

	/**
	 * 根据工程id、停复工记录id、停复工编号查询停复工记录信息
	 */
	@Override
	public Map<String, Object> queryShutdownRecord(
			ShutdownRecordReq shutdownRecordReq) {
		Criteria c = super.getCriteria();
		//工程Id
		if(StringUtils.isNotBlank(shutdownRecordReq.getProjId())){
			c.add(Restrictions.eq("projId",shutdownRecordReq.getProjId()));
		}
		//停复工记录Id
		if(StringUtils.isNotBlank(shutdownRecordReq.getSdrId())){
			c.add(Restrictions.like("sdrId",shutdownRecordReq.getSdrId()));
		} 
		//停复工编号
		if(StringUtils.isNotBlank(shutdownRecordReq.getSdrNo())){
			c.add(Restrictions.eq("sdrNo",shutdownRecordReq.getSdrNo()));
		}
		//停复工类型
		if(StringUtils.isNotBlank(shutdownRecordReq.getSdrType())){
			c.add(Restrictions.eq("sdrType",shutdownRecordReq.getSdrType()));
		}
		//状态
		if(shutdownRecordReq.getSdrStatus()!=null&&shutdownRecordReq.getSdrStatus()>0){
			c.add(Restrictions.eq("sdrStatus",shutdownRecordReq.getSdrStatus()));
		}
		//工序
		if(StringUtils.isNotBlank(shutdownRecordReq.getSdrProcess())){
			c.add(Restrictions.eq("sdrProcess",shutdownRecordReq.getSdrProcess()));
		}
		//开始日期-截至日期
		/*if(StringUtils.isNoneBlank(shutdownRecordReq.getShutdownDateEnd())&&StringUtils.isNoneBlank(shutdownRecordReq.getShutdownDateEnd())){
			c.add(Restrictions.between("sdrDate", mysqlSqlConveter.dataOperate(shutdownRecordReq.getShutdownDateEnd()), shutdownRecordReq.getShutdownDateEnd()));
		}*/
		c.addOrder(Order.desc("createDate"));
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<ShutDownRecord> list = this.findBySortCriteria(c, shutdownRecordReq);
		
		// 返回结果
		return ResultUtil.pageResult(filterCount, shutdownRecordReq.getDraw(),list);
	}

	@Override
	public ShutDownRecord queryById(String id) {
		return super.get(id);
	}

	@Override
	public Integer queryByCondition(ShutDownRecord shutDownRecord) {
		Criteria c = super.getCriteria();
		//工程Id
		if(StringUtils.isNotBlank(shutDownRecord.getProjId())){
			c.add(Restrictions.eq("projId",shutDownRecord.getProjId()));
		}
		//停复工记录Id
		if(StringUtils.isNotBlank(shutDownRecord.getSdrProcess())){
			c.add(Restrictions.eq("sdrProcess",shutDownRecord.getSdrProcess()));
		} 
		//停复工编号
		if(StringUtils.isNotBlank(shutDownRecord.getSdrNo())){
			c.add(Restrictions.eq("sdrNo",shutDownRecord.getSdrNo()));
		}
		//停复工类型
		if(StringUtils.isNotBlank(shutDownRecord.getSdrType())){
			c.add(Restrictions.eq("sdrType",shutDownRecord.getSdrType()));
		}
		//状态
		if(shutDownRecord.getSdrStatus()!=null&&shutDownRecord.getSdrStatus()>0){
			c.add(Restrictions.eq("sdrStatus",shutDownRecord.getSdrStatus()));
		}
		
		/*//开始日期
		if(StringUtils.isNoneBlank(shutdownRecordReq.getShutdownDateEnd())){
			c.add(Restrictions.)
		}
		//结束日期
		if(StringUtils.isNoneBlank(shutdownRecordReq.getShutdownDateEnd())){
			
		}*/
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);
		return filterCount;
	}

	@Override
	public boolean updateStatusById(String sdrId, Integer sdrStatus) {
		StringBuffer hql = new StringBuffer("update ShutDownRecord set sdrStatus=?  where sdrId=?");
		List<Object> params = new ArrayList<Object>();
		params.add(sdrStatus);
		params.add(sdrId);
		return super.executeHql(hql.toString(),params.toArray());
	}

	@Override
	public void updateStatusBySdrNo(String sdrNo, String sdrType,Integer sdrStatus) {
		StringBuffer hql = new StringBuffer("update ShutDownRecord set sdrStatus=?  where sdrNo=? and sdrType=?");
		List<Object> params = new ArrayList<Object>();
		params.add(sdrStatus);
		params.add(sdrNo);
		params.add(sdrType);
		super.executeHql(hql.toString(),params.toArray());
		
	}

	@Override
	public Integer findSRByProjdId(String projId) {
		String sql = "select count(SDR_ID) from shutdown_record where PROJ_ID =? and SDR_STATUS>? and SDR_STATUS<?";
		List<Object> params = new ArrayList<>();
		params.add(projId);
		params.add(ShutdownStatusEnum.SHUTDOWN_ORDER.getValue());
		params.add(ShutdownStatusEnum.REWORK_ORDERED.getValue());
		return this.getCountBySql(sql, params.toArray());
	}


	/**
	 * 删除停复工记录
	 * create person wanghuijun
	 * 2019年5月31
	 * @return
	 */
	@Override
	public void deleteSDRecordBySDRId(ShutDownRecord shutDownRecord) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("delete from shutdown_record where sdr_id = '").append(shutDownRecord.getSdrId()).append("'");
		this.executeSql(sql.toString());
		
	}
}
