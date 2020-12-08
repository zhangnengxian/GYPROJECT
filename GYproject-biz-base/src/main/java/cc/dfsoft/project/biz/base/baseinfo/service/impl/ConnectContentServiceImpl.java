package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.ConnectContentDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.baseinfo.service.ConnectContentService;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ConnectContentServiceImpl implements ConnectContentService {

	@Resource
	ConnectContentDao connectContentDao;
	
	@Override
	public Map<String, Object> queryConnectContent(ConnectContentReq connectContentReq) throws ParseException {

		return connectContentDao.queryConnectContent(connectContentReq);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteConnectContent(String id) {
		 connectContentDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveOrUpdateConnectContent(ConnectContent connectContent) {
		if(StringUtil.isNoneBlank(connectContent.getId())){
			connectContentDao.update(connectContent);
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			int id = Integer.parseInt(connectContentDao.queryNum(connectContent.getType()));
			connectContent.setId(id+1+"");
			connectContentDao.save(connectContent);
			return Constants.OPERATE_RESULT_SUCCESS;
		}
	}

}
