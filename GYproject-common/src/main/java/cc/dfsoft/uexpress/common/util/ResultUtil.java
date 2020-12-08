package cc.dfsoft.uexpress.common.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.ResultCodeEnum;

/**
 * 结果处理工具类
 * 
 * @author 1919
 *
 */
public class ResultUtil {

	/**
	 * 分页结果对象
	 * 
	 * @param totalCount
	 *            总记录数
	 * @param filterCount
	 *            过滤记录数
	 * @param draw
	 *            请求次数计数器，原值返回
	 * @param data
	 *            结果集
	 * @return
	 */
	public static Map<String, Object> pageResult(int filterCount, int draw, Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//过滤掉查询总记录数，Datatable中不带条件的总记录数查询意义不大，影响效率
		resultMap.put("recordsTotal", filterCount);
		resultMap.put("recordsFiltered", filterCount);
		resultMap.put("draw", draw);
		resultMap.put("data", data);
		return resultMap;
	}

	/**
	 * 结果信息
	 * 
	 * @param resultCode
	 * @param resultMsg
	 * @return
	 */
	public static Map<String, Object> resultMap(String resultCode, String resultMsg) {
		return resultMap(resultCode, resultMsg, null);
	}
	
	/**
	 * 结果信息
	 * @param resultCodeEnum
	 * @return
	 */
	public static Map<String, Object> resultMap(ResultCodeEnum resultCodeEnum) {
		return resultMap(resultCodeEnum.getValue(), resultCodeEnum.getMessage(), null);
	}

	/**
	 * 结果信息
	 * 
	 * @param resultCode
	 * @param result
	 * @return
	 */
	public static Map<String, Object> resultMap(String resultCode, Object result) {
		return resultMap(resultCode, null, result);
	}

	/**
	 * 结果信息
	 * 
	 * @param resultCode
	 * @param resultMsg
	 * @param result
	 * @return
	 */
	public static Map<String, Object> resultMap(String resultCode, String resultMsg, Object result) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put(Constants.RESULT_CODE, resultCode);
		resultMap.put(Constants.RESULT_MSG, StringUtil.isNotBlank(resultMsg) ? resultMsg : "");
		if (result != null) {
			resultMap.put(Constants.RESULT, result);
		}
		return resultMap;
	}
	
	/**
	 * 返回错误结果信息
	 * @param errorMsg
	 * @return
	 */
	public static Map<String, Object> errorResult(int draw, List<? extends Object> data, 
			ResultCodeEnum resultCodeEnum, String errorMsg) {
		Map<String, Object> resultMap = pageResult(0, draw, data);
		resultMap.put(Constants.DATATABLES_ERROR_KEY, MessageFormat.format(resultCodeEnum.getMessage(), errorMsg));
		return resultMap;
	}
}
