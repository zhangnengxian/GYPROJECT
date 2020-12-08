package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.DelayDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.Delay;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.project.biz.base.constructmanage.enums.RnStatusEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.DelayService;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.project.biz.base.project.enums.SignDataTypeEnum;
import cc.dfsoft.project.biz.base.project.service.SignatureService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DelayServiceImpl implements DelayService {

    @Autowired
    private DelayDao delayDao;
    @Autowired
    private SignatureService signatureService;

    @Override
    public Map<String, Object> getDataList(RectifyNoticeReq rectifyNoticeReq) {

        return delayDao.getDataList(rectifyNoticeReq);
    }

    @Override
    public String saveDelay(Delay delay) throws Exception {
        boolean flag = false;
        if(StringUtil.isBlank(delay.getAdId())){
            delay.setAdId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
            flag = true;
        }
        delay.setApplicant(SessionUtil.getLoginInfo().getStaffName());
        delay.setAddtime(delayDao.getDatabaseDate());
        delay.setState("1");
        delayDao.saveOrUpdate(delay);
        List<Signature> signs=delay.getSign();
        if(signs!=null && signs.size()>0){
            for(Signature sign:signs){
                sign.setDataType(SignDataTypeEnum.RECTIFY_NOTICE_BACK.getValue());
            }
            delay.setSign(signs);
            signatureService.saveOrUpdateSign("menuId+menuNane+12",delay.getSign(), delay.getProjId(), delay.getAdId(), delay.getClass().getName(),flag);

        }
        return delay.getAdId();
    }

    /**
     * 查询详述
     * @param id
     * @return
     */
    @Override
    public Delay viewDelay(String id) {
        Delay de = delayDao.get(id);
        Map<String,Object> date = delayDao.getDate(id);
        if(date != null){
            de.setPlanStatus(String.valueOf(date.get("PLANSTART")));
            de.setPlanEnd(String.valueOf(date.get("PLANEND")));
            de.setStatusDate(String.valueOf(date.get("STUTASDATE")));
        }
        return de;
    }

    /**
     * 推送
     * @param adId
     * @return
     */
    @Override
    public String push(String adId) {
        Delay de = delayDao.get(adId);
        if(de!=null){
            de.setState("2");
            delayDao.update(de);
        }
        return Constants.OPERATE_RESULT_SUCCESS;
    }
}
