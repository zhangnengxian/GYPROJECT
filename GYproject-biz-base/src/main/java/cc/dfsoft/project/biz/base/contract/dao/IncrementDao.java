package cc.dfsoft.project.biz.base.contract.dao;

import java.util.List;
import java.util.Map;

import com.fr.general.jsqlparser.parser.ParseException;

import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.contract.entity.Increment;
import cc.dfsoft.uexpress.common.dao.CommonDao;

/**
 * 
 * 描述:税率dao接口类
 * @author liaoyq
 * @createTime 2017年11月26日
 */
public interface IncrementDao extends CommonDao<Increment, String>{

	List<Increment> queryAll();
	/**
	 * 查询税率信息
	 * @param incrementReg
	 * @return
	 * @throws ParseException
	 */
	public Map<String, Object> queryIncrementInfo(IncrementReg incrementReg);
	/**
	 * 
	 * @author liaoyq
	 * @createTime 2018年5月17日
	 * @param incrementReg
	 * @return
	 */
	List<Increment> queryIncrement(IncrementReg incrementReg);

}
