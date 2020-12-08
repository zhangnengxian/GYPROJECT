package cc.dfsoft.project.biz.base.inspection.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectChecklistQueryReq;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 工程报验单
 * @author Administrator
 *
 */
public interface ProjectChecklistService {
	/**
	 * 工程报验单条件查询
	 * @author fuliwei
	 * @createTime 2016-06-21
	 * @param listQueryReq 查询条件dto
	 * @return Map<String,Object>
	 * @throws ParseException 
	 */
	Map<String,Object> queryPrProjectChecklist(ProjectChecklistQueryReq listQueryReq) throws ParseException;
	
	/**
	 * 按报验单ID查询
	 * @param id 主键id
	 * @return
	 */
	public ProjectChecklist viewProjectCheckList(String id);
	
	/**
	 * 按报验单ID查询
	 * @param id
	 * @param menuDes
	 * @return
	 */
	public ProjectChecklist viewProjectCheckListFile(String id,String menuDes);
	
	/**
	 * 测量放线保存
	 * @author fuliwei
	 * @createTime  2016-7-19
	 * @param checkList
	 * @return String
	 */
	//public String saveCheckList(ProjectChecklist checklist);
	
	/**
	 * 吹扫记录保存
	 * @author fuliwei
	 * @createTime  2016-7-20
	 * @param checkList
	 * @return String
	 */
	//public String savePurge(ProjectChecklist checkList);
	/**
	 * 报验单保存
	 * @author zhangjj
	 * @param t
	 */
	public void saveProChecklist(ProjectChecklist t);
	
	/**
	 * 试压记录保存
	 * @author fuliwei
	 * @createTime  2016-7-20
	 * @param checkList
	 * @return String
	 */
	//public String savePressure(ProjectChecklist checkList);
	
	/**
	 * 除锈工序保存
	 * @author fuliwei
	 * @createTime  2016-7-25
	 * @param checkList
	 * @return String
	 */
	//public String saveDerusting(ProjectChecklist checkList);
	
	
	/**
	 * 保存报验单公共方法
	 * @author fuliwei
	 * @createTime 2016-7-25
	 * @param checkList
	 * @return String
	 */
	public String saveProCheckList(ProjectChecklist checkList,String uniqueId) throws Exception;

	/**
	 * 保存报验单（图片）
	 * @param request
	 * @param proCheckList
	 * @param files
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 */
	String saveProCheckListFile(HttpServletRequest request,UploadResult proCheckList,MultipartFile[] files,String pcDesId) throws Exception;
	
	/**
	 * 按工程id和报验单ID查询
	 * @param id 主键id
	 * @return
	 */
	public ProjectChecklist viewProjectCheckList(String projId,String pcId);

	/**
	 * 修改报验单完成状态
	 * @param pcId
	 * @param flag
	 */
	String updateFlag(String pcId, Integer flag);

	/**
	 * 重置报验单
	 */
	public String resetCheck(ProjectChecklist checkList);

	/**
	 * 根据工程ID组装批量打印报验单报表的路径和参数
	 * @param projId
	 * @param pcDesId
	 * * @param arrayStr
	 * @return
	 */
	
	List<Object> findPrintDataByProjId(String projId, String pcDesId, String arrayStr);
	
	/**
	 * 生成签字
	 * @author fuliwei
	 * @createTime 2018年1月22日
	 * @param 
	 * @return
	 */
	public String saveProjectCheckListSignNotice(ProjectChecklist list);
	/**
	 * 删除报验列表的公共方法
	 * @author 王会军
	 * @createTime 2018年1月24日
	 * @param pcId
	 * @return
	 */
	public void deleteListById(String pcId);

	public void sendListById(String pcId);

	/**
	 * 系统时间
	 * @return
	 */
	Date getSysDate();

	String strengthTestUpdateFlag(String pcId, Integer flag,String resetReason);
}
