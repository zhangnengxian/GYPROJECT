package cc.dfsoft.project.biz.base.update.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.update.dto.UpdateInfoReq;
import cc.dfsoft.project.biz.base.update.entity.UpdateInfo;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 
 * @author dn
 *
 */
public interface UpdateInfoDao extends CommonDao<UpdateInfo, String>{


	UpdateInfo findById(String id);
	/**
	 * 根据版本类型查版本信息
	 * @param veType
	 * @return
	 */
	List<UpdateInfo> findInfo(String updateNumber,Date updateTime);
	/**
	 * 查询全部内容
	 * @return
	 */
	List<UpdateInfo> findAllInfo();
	/**
	 * 增加信息
	 * @param updateInfo
	 * @return
	 */
	public void insertUpdateInfo(UpdateInfo updateInfo);
	/**
	 * 修改信息
	 * @param updateInfo
	 * @return
	 */
	void updateUpdateInfo(UpdateInfo updateInfo);
	/**
	 * 
	 * @param updateInfo
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryUpdateInfo(UpdateInfoReq updateInfo) throws ParseException;
	
	
}
