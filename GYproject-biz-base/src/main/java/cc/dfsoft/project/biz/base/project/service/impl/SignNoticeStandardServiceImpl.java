package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.SignNoticeStandardDao;
import cc.dfsoft.project.biz.base.project.entity.SignNoticeStandard;
import cc.dfsoft.project.biz.base.project.service.SignNoticeStandardService;
import cc.dfsoft.uexpress.common.constant.Constants;

/**
 * 签字通知标准
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class SignNoticeStandardServiceImpl implements SignNoticeStandardService,InitializingBean {

	@Resource
	ProjectDao projectDao;
	
	/**
	 * Map缓存签字配置标准
	 *key-menuId
	 *value-dataType
	 */
	public static Map<String, String> sNsMenuIdCashMap = new HashMap<String, String>();
	/**
	 * Map存储签字标准配置信息
	 * key-sNsCashMap
	 * value-List<>
	 */
	public static Map<String, List<SignNoticeStandard>> signNoticeStandardCashMap = new HashMap<String, List<SignNoticeStandard>>();
	
	@Resource
	SignNoticeStandardDao signNoticeStandardDao;
	/**
	 * 将签字标准配置存入Map
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		List<SignNoticeStandard> list = signNoticeStandardDao.queryByPostAndDateType(null,null,null,null,null);
		if(list!=null){
			for(SignNoticeStandard sds : list){
				sNsMenuIdCashMap.put(sds.getMenuId(), sds.getDataType());
			}
			signNoticeStandardCashMap.put("sNsCashMap", list);
		}
	}

	/**
	 * 通过菜单ID查询签字标准配置的dataType
	 * @author liaoyq
	 * @createTime 2018年5月18日
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	@Override
	public String getSNsMenuIdCashMapByKey(String key) throws Exception{
		if(sNsMenuIdCashMap.get(key)==null){
			this.afterPropertiesSet();
		}
		return sNsMenuIdCashMap.get(key);
	}

	
	/**
	 * 查询签字标准通过以下参数
	 * @param postType
	 * @param dataTyep
	 * @param corpId
	 * @param projectType
	 * @param contributionMode
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<SignNoticeStandard> querySignNoticeStandard(String postType, String dataType, String corpId,
			String projectType, String contributionMode) throws Exception {
		// TODO Auto-generated method stub
		List<SignNoticeStandard> projectLeaderStandardList = null;
		// 按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
	    projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(postType,dataType,corpId,projectType,contributionMode);
		
	    // 按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
		if (projectLeaderStandardList.size() < 1) {
			 projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(postType,dataType,corpId,projectType,Constants.CONTRIBUTION_MODE);
			
		}
		// 按职务ID、签字类型、公司ID、工程类型、出资方式查询签字标准表
		if (projectLeaderStandardList.size() < 1) {
			 projectLeaderStandardList=signNoticeStandardDao.queryByPostAndDateType(postType,dataType,corpId,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		    
				}
		// 查找默认签字通知表
		if (projectLeaderStandardList.size() < 1) {
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(postType,dataType,corpId,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
			
		}
		
		// 查找默认签字通知表
		if (projectLeaderStandardList.size() < 1) {
			projectLeaderStandardList = signNoticeStandardDao.queryByPostAndDateType(postType,dataType,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
					
		}
		
		return projectLeaderStandardList;
	}



	@Override
	public List<SignNoticeStandard> findSignNoticeStandardList(String menuId, String projId) {

		Project project = projectDao.get(projId);if (project==null)return null;

		List<SignNoticeStandard> list = signNoticeStandardDao.findSignNoticeStandardList(menuId,project.getCorpId(),project.getProjectType(),project.getContributionMode());

		if (list!=null && list.size()>0) return list;

		list = signNoticeStandardDao.findSignNoticeStandardList(menuId,project.getCorpId(),project.getCorpId(),Constants.CONTRIBUTION_MODE);
		if (list!=null && list.size()>0) return list;

		list = signNoticeStandardDao.findSignNoticeStandardList(menuId,project.getCorpId(),Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		if (list!=null && list.size()>0) return list;

		list = signNoticeStandardDao.findSignNoticeStandardList(menuId,Constants.CORP_ID,Constants.PROJECT_TYPE,Constants.CONTRIBUTION_MODE);
		if (list!=null && list.size()>0) return list;

		return null;
	}
}
