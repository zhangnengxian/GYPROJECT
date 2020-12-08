package cc.dfsoft.project.biz.base.maintain.dao;

import cc.dfsoft.project.biz.base.maintain.dto.SysCodeDescReq;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:15
 * @Version:1.0
 */
public interface SysCodeDescDao extends CommonDao<SysCodeDesc, String> {
    List<SysCodeDesc> getSysCodeDescList(SysCodeDescReq sysCodeDescReq);
}
