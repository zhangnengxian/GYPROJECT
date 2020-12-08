package cc.dfsoft.project.biz.base.maintain.controller;

import cc.dfsoft.project.biz.base.maintain.dto.SysCodeDescReq;
import cc.dfsoft.project.biz.base.maintain.entity.SysCodeDesc;
import cc.dfsoft.project.biz.base.maintain.service.SysCodeDescService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 16:50
 * @Version:1.0
 */

@Component
public class CollectionUtils {

    @Resource
    private SysCodeDescService sysCodeDescService;
    private static CollectionUtils codeUtils;
    private static  List<SysCodeDesc> sysCodeDescList;

    @PostConstruct
    public void init() {
        codeUtils =this;
    }


    public static List<SysCodeDesc> getSysCodeDescList() {
        return sysCodeDescList;
    }

    public static void setSysCodeDescList(List<SysCodeDesc> sysCodeDescList) {
        CollectionUtils.sysCodeDescList = sysCodeDescList;
    }




    public static String getDescByCode(String code, String tableName, String type){
        List<SysCodeDesc> sysCodeDescList = fillterList(tableName,type);
        for (SysCodeDesc s:sysCodeDescList) {
            if (s.getCode().equals(code))return s.getDesc();
        }
        return null;
    }


    public static List<SysCodeDesc> fillterList(String tableName,String type){
        List<SysCodeDesc> descList=new ArrayList<>();
        List<SysCodeDesc> sysCodeDescList = getSysCodeDescList(tableName);
        for (SysCodeDesc s:sysCodeDescList) {
            if (s.getType().equals(type))descList.add(s);
        }
        return descList;
    }


    public static List<SysCodeDesc> getSysCodeDescList(String tableName){
        if (getSysCodeDescList()==null){
            SysCodeDescReq sysCodeDescReq=new SysCodeDescReq();
            sysCodeDescReq.setTableName(tableName);
            sysCodeDescList = codeUtils.sysCodeDescService.getSysCodeDescList(sysCodeDescReq);
        }
        return sysCodeDescList;
    }

    public static List<SysCodeDesc> getSysCodeDescList(String tableName,String type){
        SysCodeDescReq sysCodeDescReq=new SysCodeDescReq();
        sysCodeDescReq.setTableName(tableName);
        sysCodeDescReq.setType(type);
        return codeUtils.sysCodeDescService.getSysCodeDescList(sysCodeDescReq);
    }
}
