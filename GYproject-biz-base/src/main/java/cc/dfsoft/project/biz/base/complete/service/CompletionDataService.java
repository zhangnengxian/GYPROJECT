package cc.dfsoft.project.biz.base.complete.service;

import java.text.ParseException;
import java.util.List;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompletionDataPrintDto;

/**
 * 
 * 描述:竣工资料业务实现接口
 * @author liaoyq
 * @createTime 2017年9月28日
 */
public interface CompletionDataService {

	/**
	 * 根据工程id查询批量打印资料
	 * 并返回报表地址组装
	 * @param dataTypes
	 * @param projId
	 * @return
	 * @throws ParseException 
	 */
	List<Object> printCompletionData(String dataTypes, String projId) throws ParseException;

}
