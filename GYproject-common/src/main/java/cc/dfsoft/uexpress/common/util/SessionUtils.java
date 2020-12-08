package cc.dfsoft.uexpress.common.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/21 16:44
 * @Version:1.0
 */
public class SessionUtils {

    public static Object getProjNo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object projNo = session.getAttribute("projNo");
        session.setAttribute("projNo",null);
        session.setAttribute("businessOrderId",null);
        return projNo;
    }

    public static Object getBusinessOrderId(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Object businessOrderId = session.getAttribute("businessOrderId");
        session.setAttribute("projNo",null);
        session.setAttribute("businessOrderId",null);
        return businessOrderId;
    }

}
