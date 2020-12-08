package cc.dfsoft.project.biz.base.mapper;

import cc.dfsoft.project.biz.base.mapper.service.SendTaskLogService;
import cc.dfsoft.project.biz.base.project.service.OperateRecordService;
import cc.dfsoft.project.biz.base.project.service.ProjectService;
import cc.dfsoft.uexpress.biz.sys.dept.service.StaffService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 切面
 */
@Aspect //该注解标示该类为切面类
@Component //注入依赖
public class JpushAspect {

    private static Logger logger = LoggerFactory.getLogger(JpushAspect.class);

    @Autowired
    private OperateRecordService operateRecordService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private SendTaskLogService sendTaskLogService;

    //线程池
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);

    @Pointcut("execution(* cc.dfsoft.project.biz.base.*..*(..))")
    private void doAspect() {}


    @AfterReturning(value = "doAspect()")
    public void doAfter(JoinPoint jp){
        // 拦截的实体类，就是当前正在执行的controller
        Object target = jp.getTarget();
        // 拦截的放参数类型
        Signature sig = jp.getSignature();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = sig.getName();
        // 获得被拦截的方法
        Method method = null;
        try {
            if (!(sig instanceof MethodSignature)) {
                throw new IllegalArgumentException("该注解只能用于方法");
            }
            MethodSignature msig = (MethodSignature) sig;
            Class[] parameterTypes = msig.getMethod().getParameterTypes();
            method = target.getClass().getMethod(methodName, parameterTypes);
            if (method != null) {
                // 判断是否包含自定义的注解，说明一下这里的SystemLogAnn就是我自己自定义的注解
                if (method.isAnnotationPresent(SendTask.class)) {
                    SendTask sla = method.getAnnotation(SendTask.class);
                    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                    String uri = request.getRequestURI();
                    //发送待办消息
                    threadPool.execute(new LThread(operateRecordService,projectService,staffService,sendTaskLogService,request.getSession(),sla.menuName(),sla.description(),sla.message(),uri));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 异常执行类
     * @param jp
     * @param ex
     */
    @AfterThrowing(pointcut = "doAspect()", throwing = "ex")
    public void doAfterThrowingMethod(JoinPoint jp, Exception ex) {

    }

}
