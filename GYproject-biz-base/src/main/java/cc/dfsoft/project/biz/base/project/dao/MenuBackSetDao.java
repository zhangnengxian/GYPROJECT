package cc.dfsoft.project.biz.base.project.dao;

import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.dto.MenuBackSetReq;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;
import java.util.Map;
/**
 * 退回菜单配置
 * @author fuliwei
 *
 */
public interface MenuBackSetDao extends CommonDao<MenuBackSet, String>{
	
	/**
	 * 根据工程类型和当前的id去查返回的list
	 * @author fuliwei
	 * @createTime 2017年11月30日
	 * @param 
	 * @return
	 */
	public List<MenuBackSet> queryMenuBackSetByProjType(FallbackApplyReq req);

	public Map<String, Object> getDataList(MenuBackSetReq req);

	public MenuBackSet isExisting(MenuBackSet menuBackSet);

    MenuBackSet quertMenuBackSet(FallbackApplyReq req);
}
