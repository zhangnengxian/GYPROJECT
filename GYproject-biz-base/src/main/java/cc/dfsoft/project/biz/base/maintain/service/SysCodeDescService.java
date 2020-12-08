package cc.dfsoft.project.biz.base.maintain.service;

import cc.dfsoft.project.biz.base.maintain.dto.SysCodeDescReq;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;

import java.util.List;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:13
 * @Version:1.0
 */
public interface SysCodeDescService {
   List<SysCodeDesc> getSysCodeDescList(SysCodeDescReq sysCodeDescReq);
}
