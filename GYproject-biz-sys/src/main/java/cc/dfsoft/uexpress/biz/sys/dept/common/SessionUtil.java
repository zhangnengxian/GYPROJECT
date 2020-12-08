package cc.dfsoft.uexpress.biz.sys.dept.common;

import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 登录信息会话管理
 *
 * @author 1919
 *
 */
public class SessionUtil {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(SessionUtil.class);

	/**
	 * 获取session登录信息
	 *
	 * @return
	 */
	public static LoginInfo getLoginInfo() {
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			HttpSession session = request.getSession();
			Object obj = session.getAttribute(Constants.SESSION_LOGININFO);
			if (obj == null) {
				return null;
			}
			return (LoginInfo) obj;
		} catch (Exception e) {
			logger.error("获取session登录信息发生异常", e);
			return null;
		}
	}

	public static HttpSession getSession() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		return  request.getSession();
	}

	/**
	 * 获取session租户ID
	 * @return
	 */
	public static String getTenantId(){
		LoginInfo loginInfo = getLoginInfo();
		//return loginInfo != null ? loginInfo.getTenantId() : null;
		//临时用
		return loginInfo != null ? loginInfo.getTenantId() : "1000000";
	}

	/**
	 * 设置session值
	 * @param key
	 * @param param
	 */
	public static void setSession(String key,Object param){
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(key,param);
	}


	public static String getLoginPersonLatestTransferDeptId(){
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		if (loginInfo==null)return null;

		String deptTransfer = loginInfo.getDeptTransfer();
		if (StringUtils.isNotBlank(deptTransfer)){
			String[] split = deptTransfer.split(",");
			return split[split.length-1];
		}
		return null;
	}

}
