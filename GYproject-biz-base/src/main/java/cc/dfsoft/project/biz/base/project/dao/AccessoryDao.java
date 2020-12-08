package cc.dfsoft.project.biz.base.project.dao;

import java.util.List;
import java.util.Map;

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.uexpress.common.dao.CommonDao;
/**
 * 附件Dao
 * @author jingjing
 *
 */
public interface AccessoryDao extends CommonDao<AccessoryList, String>{
	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryAccessoryList(AccessoryQueryReq accessoryQueryReq);
	/**
	 * 查询拍照记录
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String, Object> queryAccListPhoto(AccessoryQueryReq accessoryQueryReq);
	
	public void delAccPhoto(String alPath);
	/**
	 * 根据业务单Id和附件来源查附件
	 * @param id
	 * @param sourceType
	 * @return
	 */
	public List<AccessoryList> queryAccessoryByBus(String id, String sourceType);
	
	public void delAccPicture(String projId,String stepId,String sourceType);
	/**
	 * 查询附件信息
	 * @param req
	 * @return
	 */
	public List<AccessoryList> queryAccessory(AccessoryList req);
	/**
	 * 报验保存时回写拍照的业务单ID
	 * @param busRecordId 业务单ID
	 * @param projId 工程ID
	 * @param projNo 记录ID
	 * @param sourceType 来源
	 */
	public void updateBId(String busRecordId, String projId, String id, String sourceType);
	
	/**
	 * 查询资质证书
	 * @author fuliwei
	 * @createTime 2017年9月18日
	 * @param 
	 * @return
	 */
	public List<AccessoryList>  queryQualificationAccessoryList(AccessoryQueryReq accessoryQueryReq);
	
	
	public void delAccPictureByStep(String projId,String step,String sourceType);
	/**
	 * 根据step及查询条件查询竣工资料附件
	 * @param accessoryQueryReq
	 * @param list
	 * @return
	 */
	public Map<String, Object> queryCompletionAccList(
			AccessoryQueryReq accessoryQueryReq, List<String> list);
	/**
	 * 
	 * 注释：根据工程ID查询相应的工程封面图片
	 * @author liaoyq
	 * @createTime 2019年9月19日
	 * @param projIds
	 * @param step
	 * @return
	 *
	 */
	public List<AccessoryList> getAll(List<String> projIds, String step);
	/**
	 * 
	 * 注释：将原业务单ID置为新的业务单ID
	 * @author liaoyq
	 * @createTime 2019年10月8日
	 * @param OldCoId 原业务单ID
	 * @param newCoId 新业务单ID
	 *
	 */
	public void updateCOId(String OldCoId, String newCoId);
}
