package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.entity.WorkReport;
import cc.dfsoft.project.biz.base.project.entity.Project;

import javax.sql.rowset.serial.SerialException;
import java.sql.SQLException;
import java.text.ParseException;
/**
 * 施工报告Service
 * @author Administrator
 *
 */
public interface WorkReportService {
	/**
	 * 保存开工报告
	 * @param constructionWork
	 * @throws SerialException
	 * @throws SQLException
	 */
	public String workReportSave(WorkReport workReport) throws Exception;

	/**
	 * 查详述
	 * @param projId
	 * @return
	 */
	public WorkReport workReportDetail(String projId,String dataType) throws ParseException;

	
	/**
	 * 根据工程Id,菜单ID查询报表版本
	 * @author wanghuijun
	 * @createTime 2018年10月9日
	 * @param projId
	 * @param type
	 * @return
	 */
	public String findPrintDataByProjId(String projId,String type);

	/**
	 * 查开工报告
	 * @auther cui on你2017-11-28
	 * @param projId
	 * @return
	 */
    WorkReport findByProjId(String projId);
    
    /**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 注释：查询某个工程某个日期的版本下配置的cpt
	 * @author liaoyq
	 * @createTime 2018年11月30日
	 * @param project
	 * @param menuId
	 * @param signDate
	 * @return
	 *
	 */
	String findCptUrl(Project project, String menuId, String signDate);

    boolean delBackupsWorkReport(String projId,String rollBackReason);
}

