package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.ConstructionWorkReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionWork;

import javax.sql.rowset.serial.SerialException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 工程交底Service
 * @author Administrator
 *
 */
public interface ConstructionWorkService {
	/**
	 * 保存交底记录
	 * @param constructionWork
	 * @throws SerialException
	 * @throws SQLException
	 */
	public String constructionWorkSave(ConstructionWork constructionWork) throws Exception;
	/**
	 * 查详述
	 * @param projId
	 * @return
	 */
	public ConstructionWork constructionWorkDetail(String projId,String dataType);
	/**
	 * 根据工程Id查交底记录
	 * @param projId
	 * @return
	 */
	public List<ConstructionWork> findByProjId(String projId);
	/**
	 * @author liaoyq
	 * @createTime 2017年7月28日
	 * @param request
	 * @param constructionWorkReq 会审交底查询辅助类
	 * @return  Map<String, Object> 会审交底记录及分页信息
	 */
	public Map<String, Object> queryList(ConstructionWorkReq constructionWorkReq);
	/**
	 * @author liaoyq
	 * @createTime 2017年7月28日
	 * @param id 会审交底记录ID
	 * @return
	 */
	public ConstructionWork findDetailById(String id);
	
	/**
	 * 根据工程ID、菜单ID查询报表
	 * @author wanghuijun
	 * @createTime 2018年10月8日
	 * @param projId
	 * @param type
	 * @return
	 */
	public List<Object> findPrintDataByProjId(String projId,String type);
	/**
	 * 获取服务器时间
	 * @return
	 */
	public Date getDataBaseDate();
	
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);

	/**
	 * 删除会审交底
	 * @param cwId
	 * @return
	 */
    boolean updateConstrctWorkById(String cwId) throws Exception;
    /**
     * 查询已完成签字的交底记录数量
     * @param projId
     * @param signStatus
     * @return
     */
	public Integer countSignedByProjId(String projId, String signStatus);

    String firstSignStatusByProjId(String projId);

    boolean delBackupsConstructionWork(String projId,String rollBackReason);
}

