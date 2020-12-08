package cc.dfsoft.project.biz.base.complete.service;

import cc.dfsoft.project.biz.base.complete.dto.IgniteReq;
import cc.dfsoft.project.biz.base.complete.entity.Ignite;

import java.text.ParseException;
import java.util.Map;

public interface IgniteService {

    /**
     * 点火通知单保存
     * @auther cui by 2017-11-17
     * @param ignite
     */
    void igniteSave(Ignite ignite) throws Exception;

    /**
     * 点火通知单查询
     * @auther cui by 2017-11-17
     * @param igniteReq
     */
    Map<String,Object> queryIgnite(IgniteReq igniteReq) throws ParseException;

    /**
     * 根据通气工程Id查详述
     * @auther cui by 2017-11-17
     * @param gprojId
     */
    Ignite viewByGprojId(String gprojId);

    /**
     * 标记打印
     * @auther cui by 2017-11-17
     * @param igniteId
     */
    void signIgnitePrint(String igniteId);
}
