package cc.dfsoft.project.biz.base.inspection.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.fr.page.PageResultSheetProvider;

import cc.dfsoft.project.biz.base.inspection.dao.MeasurementDao;
import cc.dfsoft.project.biz.base.inspection.dto.MeasurementReq;
import cc.dfsoft.project.biz.base.inspection.entity.Measurement;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 *计量表dao的实现层
 * @author wanghuijun
 * @createTime 2018年9月17日
 */
@Repository   
public class MeasurementDaoImpl extends NewBaseDAO<Measurement, String> implements MeasurementDao {

	/**
	 * 根据工程Id查询计量表
	 * @author wanghuijun
	 * @createTime 2018年9月17日
	 * @return
	 */
	@Override
	public Map<String, Object> queryMeasurement(MeasurementReq measurementReq) throws Exception {
		// TODO Auto-generated method stub
		Criteria criteria  = super.getCriteria();
		
		//根据工程编号查询
		if(StringUtils.isNotBlank(measurementReq.getProjNo()) ){
			criteria.add(Restrictions.like("projNo", "%"+measurementReq.getProjNo()+"%"));  
		}
		
		//根据工程ID查询
		if(StringUtils.isNotBlank(measurementReq.getProjId())){
			criteria.add(Restrictions.eq("projId", measurementReq.getProjId()));
		}
		//根据计量表铭牌号查询
		if(StringUtils.isNotBlank(measurementReq.getTableNumber())){
			criteria.add(Restrictions.like("tableNumber", "%"+measurementReq.getTableNumber()+"%"));  
		}
		
		//根据计量表类型查询
		if(StringUtils.isNotBlank(measurementReq.getTableType())){
			criteria.add(Restrictions.like("tableType", "%"+measurementReq.getTableType()+"%"));  
		}
		
		//是否是智能表
		if(StringUtils.isNotBlank(measurementReq.getIntelligentTable())){
			criteria.add(Restrictions.eq("intelligentTable", measurementReq.getIntelligentTable()));
		}
		
		
		int fileterCount = this.countByCriteria(criteria); //得到总条数
	    List<Measurement>  measurement = this.findByCriteria(criteria);
		return ResultUtil.pageResult(fileterCount, measurementReq.getDraw(), measurement);
	}

	
	/**
	 * 根据工程ID查询计量表记录
	 * @author wanghuijun
	 * @createTime 2018年10月10日
	 * @param projId
	 * @return
	 */
	@Override
	public List<Measurement> findByProjId(String projId) {
		Criteria criteria = super.getCriteria();
		//若工程ID不为空
		if(StringUtil.isNotBlank(projId)){
			criteria.add(Restrictions.eq("projId", projId));
		}
		List<Measurement> list = this.findByCriteria(criteria);
		return list;
	}

}
