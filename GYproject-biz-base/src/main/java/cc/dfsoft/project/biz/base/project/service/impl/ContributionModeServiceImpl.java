package cc.dfsoft.project.biz.base.project.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.ContributionModeDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectScaleReq;
import cc.dfsoft.project.biz.base.project.entity.ContributionMode;
import cc.dfsoft.project.biz.base.project.entity.ProjectType;
import cc.dfsoft.project.biz.base.project.service.ContributionModeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ContributionModeServiceImpl implements ContributionModeService{
	
	/**出资方式dao*/
	@Resource
	ContributionModeDao contributionModeDao;
	
	/**工程类型*/
	@Resource
	ProjectTypeDao projecTypeDao;
	
	/**
	 * 查询出资方式
	 * @author fuliwei
	 * @createTime 2017年7月19日
	 * @param 
	 * @return
	 */
	@Override
	public List<ContributionMode> findById(String typeId) {
		return contributionModeDao.findById(typeId);
	}
	
	/**
	 * 查询出资方式列表
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@Override
	public Map<String, Object> queryContributionMode(ProjectScaleReq req) {
		
		Map<String, Object> map=contributionModeDao.queryContributionMode(req);
		List<ContributionMode> list=(List<ContributionMode>) map.get("data");
		
		/*if(list!=null && list.size()>0){
			for(ContributionMode cm:list){
				ProjectType pr=projecTypeDao.get(cm.getProjTypeId());
				if(pr != null){
					cm.setProjTypeDes(pr.getProjConstructDes());
				}
			}
		}*/
		map.put("data", list);
		
		return map;
	}
	
	/**
	 * 保存出资方式
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveContributionMode(ContributionMode contributionMode) {
		if(StringUtils.isBlank(contributionMode.getId())){
			contributionMode.setId(IDUtil.getUniqueId(Constants.STANDARD));
		}
		contributionModeDao.saveOrUpdate(contributionMode);
		return Constants.OPERATE_RESULT_SUCCESS;
	}
	
	/**
	 * 根据id删除
	 * @author fuliwei
	 * @createTime 2017年7月28日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteById(String id) {
		contributionModeDao.delete(id);
	}

	@Override
	public List<ContributionMode> queryAllList() {
		return contributionModeDao.queryAllList();
	}

}
