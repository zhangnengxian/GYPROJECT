package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.CompleteReportReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.CompleteReport;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Yuanyx
 *
 */
public interface CompleteReportService {

	/**
	 * 竣工报告列表查询
	 * @param startApplicationReq
	 * @return
	 */
	Map<String, Object> queryCompleteReport(CompleteReportReq completeReportReq) throws ParseException;
	/**
	 * 根据竣工报告id查询详述
	 * @param crId
	 * @return
	 */
	CompleteReport findByCrId(String crId);
	/**
	 * 保存竣工报告
	 * @param completeReport
	 * @return
	 */
	String completeReportSave(CompleteReport completeReport) throws Exception;
	void deleteById(String crId);

    CompleteReport findByProjId(String projId) throws ParseException;
    
    /**
     * 根据工程类型、菜单Id、公司ID查询报表版本
     * @author wanghuijun
     * @createTime 2018年10月9日
     * @param projId
     * @param type
     * @return
     */
	List<Object> findPrintDataByProjId(String projId,String type);
	

	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	void saveSignNotice(String cwId);
	/**
	 * 查询未删除的竣工报告
	 * @author liaoyq
	 * @createTime 2018年6月15日
	 * @param projId
	 * @param string
	 * @return
	 */
	CompleteReport findByProjId(String projId, String string);

    boolean rollBackCompleteReport(String projId, String fallbackReason);
    /**
     * 
     * 注释：根据工程ID查询有效竣工报告是否已完成签字
     * @author liaoyq
     * @createTime 2019年9月18日
     * @param projId
     * @return
     *
     */
	boolean signCompleted(String projId);
}

