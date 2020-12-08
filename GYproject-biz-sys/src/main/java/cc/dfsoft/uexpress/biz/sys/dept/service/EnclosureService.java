package cc.dfsoft.uexpress.biz.sys.dept.service;

import java.util.List;

import cc.dfsoft.uexpress.biz.sys.dept.entity.Enclosure;

/**
 * 附件接口
 * @author jingjing
 *
 */
public interface EnclosureService {
	
	/**
	 * 根据业务单Id和附件来源查附件
	 * @param id
	 * @param sourceType
	 * @return
	 */
	public  List<Enclosure> queryEnclosureByBus(String id,String sourceType);

}
