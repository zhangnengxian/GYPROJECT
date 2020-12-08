package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 签字服务接口
 * @author pengtt
 * @createTime 2016-09-08
 *
 */
public interface SignatureService {
	
	/**
	 * 批量保存或修改签字记录及本地磁盘png图片文件 
	 * @author pengtt
	 * @createTime 2016-09-12
	 * @param signs sign数据集list
	 * @param projId 工程id
	 * @param businessOrderId 业务操作记录id
	 * @param entityName 实体类名称
	 * @param flag 是否确定为首次新增 true 为确定首次新增
	 * 无返回值
	 */
	public void saveOrUpdateSign(String menuId,List<Signature> signs,String projId,String businessOrderId,String entityName,boolean flag) throws Exception;
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-20
	 * @return
	 */
	public List<Signature> getAll();
	
	/**
	 * 用于查询首页-工程分布 签字信息下拉选框值的方法
	 * @author pengtt
	 * @createTime 2016-09-21
	 * @return
	 */
	public List<Map<String,Object>> findSignStep();
	
	/**
	 * 简图上传方法
	 * @author pengtt
	 * @createTime 2016-09-26
	 * @param projId
	 * @param projNo
	 * @param businessOrderId
	 * @param menuDes
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void saveImage(HttpServletRequest request, MultipartFile[] files,String projId,String projNo,String businessOrderId,String menuDes) throws Exception;
	/**
	 * 查询简图
	 * @param boId
	 */
	public Signature selectImg(String boId,String menuDes);
	
	
	/**
	 * 保存二维码
	 * @author fuliwei
	 * @createTime 2017年3月5日
	 * @param 
	 * @return
	 */
	public void saveOrUpdateQrCode(String menuId,String menuDes,String projId,String businessOrderId,String entityName,String pngName);

	/**
	 * 删除二维码
	 * @author fuliwei
	 * @createTime 2017年3月6日
	 * @param 
	 * @return
	 */
	public void deleteByPcId(String fieldName,String pcId);

	/**
	 * @author liaoyq
	 * @createTime 2017-8-21
	 * 简图上传方法(缩略图)
	 * @param request
	 * @param files
	 * @param projId
	 * @param projNo
	 * @param businessOrderId
	 * @param menuDes 菜单描述
	 * @param menuId 菜单ID
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public void saveImage(HttpServletRequest request, MultipartFile[] files,String projId,String projNo,String businessOrderId, String menuDes,
			String menuId) throws Exception;

	/**
	 * @author liaoyq
	 * @createTime 2017-8-21
	 * 根据业务单ID和菜单ID查询简图信息
	 * @param boId 业务单ID
	 * @param menuId 菜单ID
	 * @return
	 */
	public Signature queryImg(String boId, String menuId);

	/**
	 * 根据业务单ID和菜单描述删除签字
	 * @author liaoyq
	 * @param businessOrderId 业务单ID
	 * @param menuId 菜单ID
	 */
	public void deleteImgByBIdAndMenuId(String businessOrderId, String menuId);
	
	
	/**
	 * 保存报验单签字
	 * @author fuliwei
	 * @createTime 2018年6月4日
	 * @param 
	 * @return
	 */
	public void saveCheckListSign(ProjectChecklist checkList,String dataType);
	
	
	public void saveOrUpdateSignPicture(String menuId, String grade,String projId,String businessOrderId,String entityName,boolean flag,String constants);

	public void saveOrUpdateSignPictureAuditIns(String longitude,String latitude,String menuId, String grade,String projId,String businessOrderId,String entityName,boolean flag,String constants);
	
	public void deleteByBId(String businessOrderId);

    List<Signature> findListByBusId(String busId);
}
