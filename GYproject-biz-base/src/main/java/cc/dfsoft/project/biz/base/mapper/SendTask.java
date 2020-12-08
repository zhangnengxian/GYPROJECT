package cc.dfsoft.project.biz.base.mapper;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SendTask {


    /**
     * 发送消息菜单
     * @return
     */
    String menuName () default "";

    /**
     * 描述
     * @return
     */
    String description();

    /**
     * 消息类型
     * @return
     */
    String message();
}
