package cc.dfsoft.project.biz.base.baseinfo.service;

import java.text.ParseException;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import cc.dfsoft.project.biz.base.baseinfo.dto.ConnectContentReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConnectContent;

public interface ConnectContentService {
	/**
	 * 碰口内容列表查询
	 * @param PageSortReq
	 * @return
	 */
	public Map<String, Object> queryConnectContent(ConnectContentReq connectContentReq) throws ParseException;
	/**
	 * 删除碰口内容
	 * @param id
	 * @return
	 */
	public void deleteConnectContent(String id);
	/**
	 * 保存碰口内容
	 * @param connectContentReq
	 * @return
	 */
	public String saveOrUpdateConnectContent(@RequestBody(required = true) ConnectContent connectContent);
}
