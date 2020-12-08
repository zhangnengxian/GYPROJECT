package cc.dfsoft.project.biz.base.budget.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.budget.dto.ChangeManagementQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ChangeManagement;
import cc.dfsoft.project.biz.base.project.entity.Project;

public interface ChangeManagementService {
	
	/**
	 * 变更记录列表条件查询
	 * @author fuliwei
	 * @createTime 2016-07-12
	 * @param changeManagementQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryChangeManagement(ChangeManagementQueryReq changeManagementQueryReq) throws ParseException;
	
	Map<String,Object> queryChange(ChangeManagementQueryReq changeManagementQueryReq) throws ParseException;

	/**
	 *  查询变更详述
	 * @author cui
	 * @createTime 2016-07-20
	 * 
	 */
	ChangeManagement viewChangeManagement(String id,String menuDes);
	ChangeManagement viewChangeManagement(String id);
	/**
	 * 变更记录保存
	 * @author cui
	 * @createTime  2016-7-21
	 * 
	 */
	public void saveChangeManagement(ChangeManagement changeManagement) throws Exception;
	/**
	 * 更新变更状态
	 * @author zhangjj
	 * @param id
	 */
	public void updateChangeState(String id);
	
	/**
	 * @author pengtt
	 * @createTime 2016-09-26
	 * @param request
	 * @param changeManagement
	 * @param files
	 * @throws IOException 
	 * @throws IllegalStateException 
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	public void saveChangeConnect(HttpServletRequest request,UploadResult changeManagement,MultipartFile[] files) throws Exception;
	public void saveChangeManagement(HttpServletRequest request,UploadResult changeManagement,MultipartFile[] files) throws Exception;

	/**
	 * 变更审核列表条件查询
	 * @param changeManagementQueryReq
	 * @return
	 * @throws ParseException
	 */
	Map<String, Object> queryChangeManagementAudit(ChangeManagementQueryReq changeManagementQueryReq)throws ParseException;

	/**
	 * 变更记录推送至造价处
	 * @param cmId
	 * @return
	 */
	String pushChangeManagement(String cmId);

	/**
	 * 根据工程ID查询变更信息
	 * @param projId
	 * @return
	 */
	ChangeManagement findByProjId(String projId);

	/**
	 * 根据工程id组装批量打印变更报表路径及参数
	 * @param projId
	 * @return
	 */
	
	/**
	 * 根据工程ID 、菜单Id查询报表
	 * @author wanghuijun
	 * @createTime 2018年10月8日
	 * @param projId
	 * @param type
	 * @return
	 */
	List<Object> findPrintDataByProjId(String projId,String type);
	
	
	/**
	 * 保存变更记录新方法
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	public void saveChangeManagement(ChangeManagement changeManagement,String type);
	
	/**
	 * 更新变更状态
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	public String updateChangeState(String cmId,String type) throws ParseException;
	
	/**
	 * 根据工程id和主键id查询工程
	 * @author fuliwei
	 * @createTime 2017年11月6日
	 * @param 
	 * @return
	 */
	public  ChangeManagement viewChangeManagementById(String projId,String cmId);
	
	
	//变更审核保存
	public void saveChangeManagementAudit(HttpServletRequest request,UploadResult changeManagement,MultipartFile[] files) throws Exception;


	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 变更材料整合材料信息到材料表
	 * @param pro
	 * @param changeManagement
	 * @return 
	 */
	boolean updateMaterialList(Project pro, ChangeManagement changeManagement);

	/**
	 * 查询未终止的用户变更
	 * @author liaoyq
	 * @createTime 2018年4月26日
	 * @param projId
	 * @return
	 */
	List<ChangeManagement> noCancelChangeManagement(String projId,
			String changeType);
}
