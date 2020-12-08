package cc.dfsoft.uexpress.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @author 帐前卒
 */
public class MyMultipartResolver extends CommonsMultipartResolver {  
    private String[] excludeUrls;
    public void setExcludeUrls(String[] excludeUrls) {
        this.excludeUrls = excludeUrls;
    }


    /**
     * 这里是处理Multipart http的方法。如果这个返回值为true,那么Multipart http body就会MyMultipartResolver 消耗掉.如果这里返回false
     * 那么就会交给后面的自己写的处理函数处理例如刚才ServletFileUpload 所在的函数
     * @see org.springframework.web.multipart.commons.CommonsMultipartResolver#isMultipart(javax.servlet.http.HttpServletRequest)
     */
    @Override
    public boolean isMultipart(HttpServletRequest request) {
        for (String url: excludeUrls) {
            // 这里可以自己换判断
            if (request.getRequestURI().contains(url)) {
                return false;
            }
        }
        
        return super.isMultipart(request);
    }
     
}