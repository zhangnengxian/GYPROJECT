package cc.dfsoft.uexpress.biz.sys.auth;

import cc.dfsoft.uexpress.common.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fr.report.core.A.m;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class updateCheckInterceptor implements HandlerInterceptor {

    public static Logger logger = LoggerFactory.getLogger("interfaceinfo");
    //校验请求是否在执行
    private static HashMap<String,String> map = new HashMap<String,String>();
    
    private static String pattern = "(save)+";  //正则表达式、判断是否含有save无论大小写，也可以转换为小写，直接用indexOF或者contains判断是否包含
    // 创建 Pattern 对象
    private  static Pattern reg = Pattern.compile(pattern);  //编译
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestPath = request.getServletPath();
        Matcher isExist = reg.matcher(requestPath.toLowerCase());  //匹配是否有sava
        if(isExist.find()){  //若包含save无论大小写
            String str = map.get(request.getSession().getId()+requestPath);
            if("execute".equals(str)){
                logger.info(requestPath+"======================重复保存!!!");
                return false;
            }
            //设置在执行
            map.put(request.getSession().getId()+requestPath,"execute");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestPath = request.getServletPath();
        Matcher isExist = reg.matcher(requestPath.toLowerCase());  //匹配是否有sava


        //清除执行标识
        if(isExist.find()){
            map.remove(request.getSession().getId()+requestPath);
            List<String> oldlist= (List<String>) request.getSession().getAttribute("successList");
            List<String> newlist= (List<String>) request.getSession().getAttribute("errorlist");
            String error= (String) request.getSession().getAttribute("singtureType");
            request.getSession().removeAttribute("successList");
            request.getSession().removeAttribute("errorlist");
            request.getSession().removeAttribute("singtureType");
            if(error != null && !"".equals(error) && !"null".equals(error)){
                //失败了 删新的
             if (newlist != null) {
            	   for(String imgUrl:newlist){
                       FileUtil.deleteFile(imgUrl);
                       logger.info("保存失败删除新的图片路径----"+imgUrl);
                   }
             }
            }else{
                //删除旧的签字
               if (oldlist != null) {
            	   for(String imgUrl:oldlist){
                       FileUtil.deleteFile(imgUrl);
                       logger.info("保存成功删除旧的图片路径----"+imgUrl);
                   }
               }
            }


        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
