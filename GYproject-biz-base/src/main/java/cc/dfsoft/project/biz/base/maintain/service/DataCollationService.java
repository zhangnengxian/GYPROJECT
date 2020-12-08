package cc.dfsoft.project.biz.base.maintain.service;

import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.MenuDocument;

import java.util.List;
import java.util.Map;

/**
* @Description: 类描述
* @author zhangnx
* @date 2019/8/20 14:33
*@Version:1.0
*/
public interface DataCollationService {


    void saveUpdateThisTableData(MenuDocument menuDocument);

    MenuDocument queryThisTableData(MenuDocument menuDocument);
}
