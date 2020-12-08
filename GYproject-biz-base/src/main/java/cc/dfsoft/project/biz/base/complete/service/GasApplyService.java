package cc.dfsoft.project.biz.base.complete.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.complete.dto.GasApplyReq;
import cc.dfsoft.project.biz.base.complete.entity.GasApply;

public interface GasApplyService {
	
	/**
	 * 保存通气确认 
	 * @author flw
	 * @createTime 2016-1-12
	 * @param  ga
	 * @return void
	 */
	public String saveGasApply(GasApply ga) throws Exception;

	/**
	 * 通气申请条件查询
	 * @param gasApplyReq
	 * @return
	 */
	public Map<String, Object> queryGasApply(GasApplyReq gasApplyReq) throws ParseException ;

	/**
	 * 更新通气申请（通气反馈信息）
	 * @param gasApply
	 */
	public String updateGasApply(GasApply gasApply);

	/**
	 * 通气申请详述
	 * @param id
	 * @return
	 */
	public GasApply gasApplyDetail(String gaId);
}
