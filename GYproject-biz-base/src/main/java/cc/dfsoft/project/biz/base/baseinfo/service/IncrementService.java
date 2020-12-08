package cc.dfsoft.project.biz.base.baseinfo.service;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.baseinfo.dto.IncrementReg;
import cc.dfsoft.project.biz.base.contract.entity.Increment;

public interface IncrementService {
	/**
	 * 查询税率信息
	 * @author pengtt
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryIncrementInfo(IncrementReg incrementReg);
	public Increment viewIncrementDetailById(String id);   //通过id查询税率信息
	public void deleteIncrement(String id);                  //删除税率
	public void saveIncrement(Increment increment);           //保存税率信息
	/**
	 * 查询所有税率信息
	 * @return
	 */
	public List<Increment> queryAllList(IncrementReg incrementReg);
}
