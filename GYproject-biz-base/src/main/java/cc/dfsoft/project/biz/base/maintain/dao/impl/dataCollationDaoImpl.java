package cc.dfsoft.project.biz.base.maintain.dao.impl;

import cc.dfsoft.project.biz.base.maintain.dao.AbandonedRecordDao;
import cc.dfsoft.project.biz.base.maintain.dao.DataCollationDao;
import cc.dfsoft.project.biz.base.maintain.dto.AbandonedRecordReq;
import cc.dfsoft.project.biz.base.maintain.entity.AbandonedRecord;
import cc.dfsoft.project.biz.base.maintain.entity.MenuDocument;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc: 类描述
 * @Auther: zhangnx
 * @Date: 2019/3/25 22:16
 * @Version:1.0
 */
@Repository
public class dataCollationDaoImpl extends NewBaseDAO<MenuDocument, String> implements DataCollationDao {


}