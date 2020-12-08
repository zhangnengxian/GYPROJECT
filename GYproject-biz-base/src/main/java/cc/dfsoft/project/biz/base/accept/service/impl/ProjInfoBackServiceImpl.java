package cc.dfsoft.project.biz.base.accept.service.impl;

import java.text.ParseException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.accept.dto.ProjInfoModifyReq;
import cc.dfsoft.project.biz.base.accept.service.ProjInfoBackService;
/**
 * 工程回退信息
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ProjInfoBackServiceImpl implements ProjInfoBackService{

	@Override
	public Map<String, Object> queryProModify(ProjInfoModifyReq req) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}

}
