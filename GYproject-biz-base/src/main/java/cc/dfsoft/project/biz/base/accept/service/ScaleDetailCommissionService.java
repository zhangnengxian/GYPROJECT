package cc.dfsoft.project.biz.base.accept.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;

/**
 * 委托规模详述接口
 * @author Administrator
 *
 */
public interface ScaleDetailCommissionService {
	
	public Map<String,Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException;

	
}
