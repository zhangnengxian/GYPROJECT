package cc.dfsoft.uexpress.common.controller;

import java.util.Map;

import cc.dfsoft.uexpress.common.util.ResultUtil;

/**
 * controller控制器基类
 * @author 1919
 *
 */
public class BaseController {

	/**
	 * 返回结果
	 * @param resultCode
	 * @param resultMsg
	 * @return
	 */
	public Map<String, Object> resultMap(String resultCode, String resultMsg){
		return ResultUtil.resultMap(resultCode, resultMsg);
	}
}
