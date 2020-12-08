package cc.dfsoft.project.biz.base.project.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.project.dto.AccessoryQueryReq;
import cc.dfsoft.project.biz.base.project.entity.AccessoryList;
import cc.dfsoft.project.biz.base.project.entity.CollectAccessoryItem;

/**
 * 附件接口
 * @author jingjing
 *
 */
public interface AccessoryService {
	
	/**
	 * 
	 * 上传附件并存入附件列表
	 * @param request
	 * @param acc
	 * @param files
	 */
	public void uploadAccessory(HttpServletRequest request,AccessoryList acc,MultipartFile[] files)throws Exception;
	/**
	 * 
	 * 上传附件并存入附件列表----(业务记录Id不能为空)
	 * @param request
	 * @param acc
	 * @param files
	 */
	public String uploadAccessoryBusRecordId(HttpServletRequest request,AccessoryList acc,MultipartFile[] files)throws Exception;
	/**
	 * 
	 * 上传首页图片并存入附件列表
	 * @param request
	 * @param acc
	 * @param files
	 */
	public void uploadPicture(AccessoryList acc)throws Exception;
	/**
	 * 查询附件列表
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryAccessoryList(AccessoryQueryReq accessoryQueryReq);
	
	/**
	 * 附件删除功能（删除列表记录和相应文件）
	 * @param id 附件列表id
	 * @param path 附件路径
	 */
	public void delAccessoryList(String id,String path);
	
	/**
	 * 查询资料标准项
	 * @return
	 */
	
	public List<CollectAccessoryItem> queryAccessoryItem(AccessoryQueryReq accessoryQueryReq);
	
	/**
	 * 查询资料标准列表
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryAccessoryItemList(AccessoryQueryReq accessoryQueryReq);
	
	/**
	 * 保存更新资料标准
	 * @param collectAccessoryItem
	 * @return
	 */
	public String saveOrUpdateCollectAccessoryItem(CollectAccessoryItem collectAccessoryItem);
	/**
	 * 删除资料标准
	 * @param id
	 * @return
	 */
	public void deleteCollectAccessoryItem(String id);
	
	public  AccessoryList queryAccessoryList(String id);
	/**
	 * 上传拍照
	 * @param request
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public String uploadPhoto(HttpServletRequest request,AccessoryList req) throws Exception;
	/**
	 * 查询拍照
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String, Object> queryAccListPhoto(AccessoryQueryReq accessoryQueryReq);
	
	public void delAccPhoto(@RequestParam(required=true)String alPath);
	/**
	 * 根据业务单Id和附件来源查附件
	 * @param id
	 * @param sourceType
	 * @return
	 */
	public  List<AccessoryList> queryAccessoryByBus(String id,String sourceType);
	/**
	 * 根据工程ID和工程编号更改业务单id
	 * @param projId 工程Id
	 * @param projNo 工程编号(报验中为报验记录ID)
	 * @param busRecordId 业务ID(报验中为报验单号)
	 */
	public void updateBIdByProjIdAndNo(String projId, String projNo, String busRecordId);
	/**
	 * 根据工程Id和编号删除附件(删除记录及相关文件)
	 * @param projId 工程Id
	 * @param projNo 工程编号(报验中为报验记录ID)
	 * @param path 附件路径
	 */
	public void delAccessoryListByProjIdAndNo(String projId, String projNo,
			String path);
	/**
	 * 置空业务单ID
	 * @param projId 工程Id
	 * @param projNo 工程编号
	 * @param busRecordId 业务ID(报验中为报验单号)
	 * 
	 */
	public void clearBRId(String projId, String projNo, String busRecordId);
	/**
	 * 查询附件信息
	 * @param req
	 * @return
	 */
	public List<AccessoryList> queryAccessory(AccessoryList req);
	
	/**
	 * 查询资质证书
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String,Object> queryQualificationAccessoryList(AccessoryQueryReq accessoryQueryReq);
	/**
	 * 根据工程ID和步骤ID查询该步骤是否已上传附件
	 * @param projId
	 * @param stepId
	 * @param sourceType 
	 * @return
	 */
	public boolean isUploadFile(String projId, String stepId, String busRecordId, String sourceType);
	/**
	 * 查询竣工资料中的附件
	 * @param accessoryQueryReq
	 * @return
	 */
	public Map<String, Object> queryCompletionAccList(
			AccessoryQueryReq accessoryQueryReq);
	/**
	 * 
	 * 描述:根据人员查询资质证明
	 * @author liaoyq
	 * @createTime 2018年10月15日
	 * @param accessoryQueryReq
	 * @return
	 */
	public List<AccessoryList> queryQualificationAccListByStaffId(
			AccessoryQueryReq accessoryQueryReq);
	
}
