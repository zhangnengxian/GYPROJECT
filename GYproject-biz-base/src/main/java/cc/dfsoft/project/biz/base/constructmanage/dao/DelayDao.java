package cc.dfsoft.project.biz.base.constructmanage.dao;

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Delay;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.Map;

public interface DelayDao extends CommonDao<Delay,String> {

    public Map<String, Object> getDataList(RectifyNoticeReq rectifyNoticeReq);

    public Map<String, Object> getDate(String id);
}
