package cc.dfsoft.project.biz.base.maintain.service.impl;

import cc.dfsoft.project.biz.base.maintain.dao.SysCodeDescDao;
import cc.dfsoft.project.biz.base.maintain.dto.SysCodeDescReq;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;
import cc.dfsoft.project.biz.base.maintain.service.SysCodeDescService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:14
 * @Version:1.0
 */

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class SysCodeDescServiceImpl implements SysCodeDescService {

    @Resource
    SysCodeDescDao sysCodeDescDao;

    @Override
    public List<SysCodeDesc> getSysCodeDescList(SysCodeDescReq sysCodeDescReq) {
        return sysCodeDescDao.getSysCodeDescList(sysCodeDescReq);
    }
}