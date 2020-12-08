package cc.dfsoft.project.biz.base.station.service;

import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;

import java.text.ParseException;
import java.util.Map;


/**
 * 工程服务接口
 * @author cui
 * @createTime 2017-08-21
 *
 */
public interface StationAcceptService {
	
	/**
	 * 场站保存
	 * @author cui
	 * @createTime 2017-08-22
	 * @param project
	 */
	String acceptSave(Project project);

	/**
	 * 场站立项查询
	 * @author cui
	 * @createTime 2017-08-22
	 * @param projectQueryReq
	 */
    Map<String,Object> queryAcceptProject(ProjectQueryReq projectQueryReq) throws ParseException;

	/**
	 * 场站工程详述（通用）
	 * @author cui
	 * @createTime 2017-08-22
	 * @param projId
	 */
    StationProceduresDto viewStationProject(String projId) throws ParseException;
}
