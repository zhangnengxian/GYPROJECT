package cc.dfsoft.uexpress.common.util;

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;

/**
 * 
 * @author pengtt
 * @createTime 2016-06-24
 *
 */
public class WorkFlowUtil {
	
    /**
     * 根据操作id，获取工程状态
     * @author pengtt
     * @param actionCode 操作id
     * @return statusCode 工程状态码
     */
	public static String workFlowStatus(String actionCode){
		
		//获取静态工作流数组
		String[] workFlowArray = Constants.WORKFLOW_ACTION_CODE;
		//下一个操作id
		String nextAction = "";
		//遍历数组，获取下一下操作id
		for(int i=0;i<workFlowArray.length;i++){
			String acode = workFlowArray[i];
			if(acode.equals(actionCode)){
				if(i<workFlowArray.length-1){
					nextAction = workFlowArray[i+1];
				}else{
					//若无定义下一个操作，则获取 “终止” 操作码id
					nextAction = WorkFlowActionEnum.CONTRACT_END.getActionCode();
				}
				break;
			}
		}
		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		if(StringUtils.isBlank(nextAction)){
			nextAction = WorkFlowActionEnum.CONTRACT_END.getActionCode();
		}
		//返回状态码
		return WorkFlowActionEnum.byActionCode(nextAction).getStatusCode();
		
	}
	
	
	/**
	 * 根据操作id，获取工程状态
	 * 例如  审图操作，审图未通过   则需要重新画草图  即工程状态翻转为待出草图（上一个状态）；若审图通过，则工程状态翻转为下一个状态
	 * @author pengtt
	 * @param actionCode 操作id
	 * @param flag  true  下一个状态   false  上一个状态
	 * @return statusCode 工程状态码
	 */
	public static String workFlowStatus(String actionCode,boolean flag){
		
		String action = WorkFlowUtil.upDownStepId(actionCode,flag);
		//返回状态码
		return WorkFlowActionEnum.byActionCode(action).getStatusCode();
	}
	
	/**
	 * 根据步骤id，获取上一个或者下一个步骤id
	 * @author pengtt
	 * @createTime 2016-08-10
	 * @param stepId 步骤id
	 * @param flag true：下一个步骤id；  false：上一个步骤id
	 * @return stepId 步骤id
	 */
	public static String upDownStepId(String stepId,boolean flag){
		
		//获取静态工作流数组
		String[] workFlowArray = Constants.WORKFLOW_ACTION_CODE;
		//操作id
		String action = "";
		//遍历数组，获取下一下操作id
		for(int i=0;i<workFlowArray.length;i++){
			String acode = workFlowArray[i];
			if(acode.equals(stepId)){
				if(!flag && i>0){
					return  workFlowArray[i-1];
				}else if(flag && i<workFlowArray.length-1){
					return workFlowArray[i+1];
				}else{
					//若无定义操作，则获取 “终止” 操作码id
					return WorkFlowActionEnum.CONTRACT_END.getActionCode();
				}
			}
		}
		//若传入操作id有误，则下一个操作id为空串  则获取 “终止” 操作码id
		return  WorkFlowActionEnum.CONTRACT_END.getActionCode();
	}
}
