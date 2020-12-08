package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Delay;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface DelayService {

    public Map<String, Object> getDataList(RectifyNoticeReq rectifyNoticeReq);

    public String saveDelay(@RequestBody(required = true) Delay delay) throws Exception;

    public Delay viewDelay(String id);

    public String push(String adId);

}
