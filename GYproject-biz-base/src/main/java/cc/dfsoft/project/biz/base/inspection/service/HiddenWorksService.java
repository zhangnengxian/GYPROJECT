package cc.dfsoft.project.biz.base.inspection.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.inspection.dto.HiddenWorksReq;
import cc.dfsoft.project.biz.base.inspection.dto.ProjectCheckListHWReq;
import cc.dfsoft.project.biz.base.inspection.entity.HiddenWorks;
import cc.dfsoft.project.biz.base.inspection.entity.ProjectChecklist;

/**
 * 隐蔽工程检查记录
 * @author Administrator
 *
 */
public interface HiddenWorksService {
	
	/**
	 * 保存隐蔽工程检查记录
	 * @author fuliwei
	 * @createTime 2016-7-28
	 * @param checkList
	 * @return String
	 */
	public String saveHiddenWorks(HiddenWorks hiddenWorks,String uniqueId);
	
	public void saveHiddenWorksRecordFile(HttpServletRequest request, UploadResult hiddenWorks, MultipartFile[] files) throws IllegalStateException, IOException, SerialException, SQLException;
	
	/**
	 * 按报验单ID查询
	 * @author fuliwei
	 * @createTime 2016-7-29
	 * @param pcId
	 * @return HiddenWorks
	 */
	public HiddenWorks viewHiddenWorks(String id,String menuDes);

	/**
	 * 根据记录ID回写报验单ID
	 * @param recordsId
	 * @param pcId
	 * @param projNo 
	 * @param projiId 
	 */
	public void updatePcIdByRecordsId(String recordsId, String pcId, String projiId, String projNo);

	/**
	 * 分页查询隐蔽工程信息
	 * @param hiddenWorksReq
	 * @return
	 */
	public Map<String, Object> queryHiddenWorks(HiddenWorksReq hiddenWorksReq);

	/**
	 * 保存隐蔽工程记录
	 * @author liaoyq
	 * @Date 2017-7-21
	 * @param checkListHWReq
	 */
	public void saveRecords(ProjectCheckListHWReq checkListHWReq);

	/**
	 * 分页查询隐蔽工程记录
	 * @param hiddenWorksReqt
	 * @return
	 */
	public Map<String, Object> queryRecords(HiddenWorksReq hiddenWorksReqt);

	/**
	 * 根据主键ID查询记录详述
	 * @param id
	 * @return
	 */
	public HiddenWorks viewRecordById(String id);

	public String saveRecord(HiddenWorks hw);

	public void deleteRecordById(String hwId);
	
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);
}
