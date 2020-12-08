package cc.dfsoft.uexpress.biz.sys.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;

/**
 * 权限拦截器
 * 
 * @author 1919
 *
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	/** 日志实例 */
	private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	/**
	 * 在业务处理器处理请求之前对该请求进行拦截处理
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestPath = request.getServletPath();
		// 所有访问地址都要经过登录判断
		if (SessionUtil.getLoginInfo() == null && !requestPath.contains("project/api")) {
			// 如果是ajax请求响应头会有，x-requested-with
			if (request.getHeader("x-requested-with") != null
					&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				// 在响应头设置session状态
				response.setHeader("sessionstatus", "timeout");
			} else {
            	response.sendRedirect(request.getContextPath()+"/login.html");
            }  
            return false; 
		}
		//request.setAttribute("baseUrl", request.getContextPath());
		
		// 不作权限判断的地址
		if (requestPath.equals("/index.jsp")) {
			logger.info("不做权限检查，访问路径：" + requestPath);
			return super.preHandle(request, response, handler);
		}

		logger.info("执行权限检查，访问路径：" + requestPath);
		return super.preHandle(request, response, handler);
	}

	/**
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
		super.postHandle(request, response, handler, mv);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
}
