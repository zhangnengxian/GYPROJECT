package cc.dfsoft.uexpress.common.util;

import org.apache.commons.lang3.StringUtils;

import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
/**
 * 签字顺序
 * @author fuliwei
 *
 */
public class SignSortNoUtil {
public static String workFlowStatus(String actionCode){
		
		//获取静态工作流数组
		String[] workFlowArray = Constants.SIGN_ACTION_CODE;
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
		return nextAction;
	}
}
