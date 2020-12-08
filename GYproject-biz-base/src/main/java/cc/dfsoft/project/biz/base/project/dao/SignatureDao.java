package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.SignatureQueryDto;
import cc.dfsoft.project.biz.base.project.entity.Signature;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author pengtt
 * @CreateTime 2016-09-07
 *
 */
public interface SignatureDao extends CommonDao<Signature, String>{
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-07
	 * @param entityName 实体类id
	 * @param fieldName  字段id
	 * @param boId  业务操作id
	 * @return
	 */
	public List<Signature> findByProperties(String entityName,String fieldName,String boId,String projId);
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-21
	 * @param entityName
	 * @param fieldName
	 * @param boId
	 * @param projId
	 * @param flag
	 * @return
	 */
	public List<Signature> findByProperties(String entityName,String fieldName,String boId,String projId,boolean flag);
	
	
	/**
	 * 用于查询首页-工程分布 签字信息下拉选框值的方法
	 * @author pengtt
	 * @createTime 2016-09-21
	 * @return
	 */
	public List<Map<String,Object>> findSignStep();
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-21
	 * @param signatureQueryDto
	 * @return
	 */
	public List<Signature> findByProperties(SignatureQueryDto signatureQueryDto);
	/**
	 * 查询简图
	 * @param boId
	 */
	public Signature selectImg(String boId,String menuDes);
	
	/**
	 * 删除二维码
	 * @author fuliwei
	 * @createTime 2017年3月6日
	 * @param 
	 * @return
	 */
	public void deleteByPcId(String projId,String pcId);

	/**
	 * @author liaoyq
	 * @createTime 2017-8-21
	 * 根据业务ID和菜单ID查询简图
	 * @param boId 业务ID
	 * @param menuId 菜单ID
	 */
	public Signature queryImg(String boId, String menuId);

	public List<Signature> findByBIdAndMenuId(String businessOrderId,
			String menuId);
	
	public void deleteByBId(String buId);

    List<Signature> findListByBusId(String busId);
}
