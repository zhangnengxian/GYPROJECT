package cc.dfsoft.project.biz.ifs.material.service;

import java.text.ParseException;

public interface MaterialNcService {
	
	/**
	 * 调用物资接口发送物料信息
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param proID 工程ID
	 * @param businessId 变更单ID
	 * @param changeType 材料表类型
	 * @param materialType
	 * @param operateType 操作类型
	 * @param serviceType 接口类型
	 * @return
	 * @throws ParseException
	 */
	String synProjectInfoClient(String proID, String businessId,String changeType, String materialType, String operateType,String serviceType)throws ParseException;
}
