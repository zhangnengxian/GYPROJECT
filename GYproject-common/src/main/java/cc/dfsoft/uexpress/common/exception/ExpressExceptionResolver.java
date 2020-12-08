package cc.dfsoft.uexpress.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fr.report.core.A.p;
import com.fr.third.fr.pdf.slf4j.Logger;
import com.fr.third.fr.pdf.slf4j.LoggerFactory;

import javassist.bytecode.stackmap.BasicBlock.Catch;
import net.sf.json.JSONString;

/**
 * 全局处理异常类 处理全局异常 错误信息返回给用户
 * 
 * @author wanghuijun
 * @version 2019年9月29日
 */

public class ExpressExceptionResolver implements HandlerExceptionResolver {
	/** 记录异常日志 */
	private static Logger logger = LoggerFactory.getLogger(ExpressExceptionResolver.class);
	/** 实例化对象 */
	private DaoException daoException = null;
	/** 实例化对象 */
	private ServiceException serviceException = null;
	/** 错误编码 */
	private String code = "unknown error";
	/** 错误信息 */
	private String message;

	/**
	 * 判断是否ajax请求 
	 * creater wanghuijun
	 * @version 2019年9月29日
	 * @param request
	 * @return true or false
	 */
	public static Boolean isAjax(HttpServletRequest request) {
		// 得到请求头参数
		String header = request.getHeader("X-Requested-With");
		// 是否是ajax请求
		boolean isAjaxRequest = StringUtils.isNotBlank(header) && StringUtils.equals(header, "XMLHttpRequest");
		if (isAjaxRequest) {
			return true;
		}
		return false;

	}

	/**
	 * exception转为字符串 creater wanghuijun
	 * @version 2019年9月29日
	 * @param ex
	 * @return exception(字符串)
	 */
	public static String getExceptionToString(Exception ex) {
		if (ex == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw=null;
		try {
			sw = new StringWriter();
		    pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			return sw.toString();
			
		} catch (Exception ee) {
			logger.error("异常信息", ex);
			return null;
		} finally {
			try {
				sw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("异常信息", ex);
				e.printStackTrace();
			}
			pw.close();
		}
	}

	/**
	 * 异常处理 ，处理ajax异常与普通异常 返回异常信息到前台页面 根据不同的异常进行提示
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		String clientException = ex.getClass().getSimpleName(); 
		// 客户端异常则返回空，避免影响业务
		boolean isClientException = StringUtils.isNotBlank(clientException) && StringUtils.equals(clientException, "ClientAbortException");
		if (isClientException) {
			logger.error("客户端异常",ex);
			return null;
		}  
		
		// 若是dao自定义异常
		if (ex instanceof DaoException) {
			daoException = (DaoException)ex;
			code = daoException.getCode();
			message = daoException.getMessage();
		} else if (ex instanceof ServiceException) {
			serviceException = (ServiceException)ex;
			code = serviceException.getCode();
			message = serviceException.getMessage();
		} else {
			message = this.getExceptionToString(ex);
		}
		if (this.isAjax(request)) {
			// 组装json字符串
			String exceptionMessage = "{\"code\":\"" + code + "\", \"message\":\"" + message + "\"}";
			PrintWriter printWriter = null;
			try {
				printWriter = response.getWriter();
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/plain;charset=utf-8");
				// 设置response状态码为500让其走ajax的error，
				response.setStatus(500);
				// 写入异常信息到response
				if (printWriter != null) {
					printWriter.write(exceptionMessage);
					printWriter.flush();
				}
				return null;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				logger.error("不支持编码异常", e);
			} catch (IOException e) {
				// TODO: handle exception
				logger.error("输入流异常", e);
			} catch(NullPointerException ne) {
				logger.error("空指针异常", ne);
			}finally {
				if (printWriter != null) {
					printWriter.close();
				}
			}

		}
		return null;
	}

}
