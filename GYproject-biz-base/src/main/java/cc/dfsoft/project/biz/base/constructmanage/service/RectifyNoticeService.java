package cc.dfsoft.project.biz.base.constructmanage.service;

import java.text.ParseException;
import java.util.Map;

import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;

/**
 * 
 * 描述:整改通知业务接口层
 * @author liaoyq
 * @createTime 2017年8月4日
 */
public interface RectifyNoticeService {

	/**
	 * 分页查询整改通知记录
	 * @author liaoyq
	 * @param rectifyNoticeReq
	 * @return Map<String, Object>
	 * @throws ParseException 
	 */
	Map<String, Object> queryRectifyNotice(RectifyNoticeReq rectifyNoticeReq) throws ParseException;

	/**
	 * 根据id查询详述
	 * @param id 整改通知ID
	 * @return
	 */
	RectifyNotice viewRectifyNotice(String id);

	/**
	 * 保存整改通知记录
	 * @author liaoyq
	 * @param rectifyNotice 整改通知信息
	 * @return 整改通知ID
	 */
	String saveRectifiyNotice(RectifyNotice rectifyNotice) throws Exception;

	/**
	 * 整改通知推送至施工单位
	 * @param rnId
	 * @return
	 */
	String pushRectifyNotice(String rnId);

	/**
	 * 施工单位整改完成后回复通知
	 * @param rnId
	 * @return
	 */
	String replyRectifyNotice(String rnId);
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 删除整改通知
	 * @author liaoyq
	 * @createTime 2018年7月9日
	 * @param rnId
	 * @return
	 */
	String deletById(String rnId);
}
