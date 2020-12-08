package cc.dfsoft.uexpress.biz.sys.dept.dao;

import java.util.List;

import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 附件Dao
 * @author Yuanyx
 *
 */
public interface EnclosureDao extends CommonDao<Enclosure, String>{

	/**
	 * 根据业务单Id和附件来源查附件
	 * @param id
	 * @param sourceType
	 * @return
	 */
	List<Enclosure> queryEnuclosureByBus(String id, String sourceType);

}
