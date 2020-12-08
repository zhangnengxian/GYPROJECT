package cc.dfsoft.project.biz.base.constructmanage.dao;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * 描述:整改通知dao接口
 * @author liaoyq
 * @createTime 2017年8月4日
 */
public interface RectifyNoticeDao extends CommonDao<RectifyNotice, String> {

	/**
	 * 分页查询整改通知记录
	 * @author liaoyq
	 * @param rectifyNoticeReq
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	Map<String, Object> queryRectifyNotice(RectifyNoticeReq rectifyNoticeReq) throws ParseException;

}
