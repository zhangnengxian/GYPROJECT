package cc.dfsoft.project.biz.base.project.service;

import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.dto.MenuBackSetReq;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;

import java.util.List;
import java.util.Map;

/**
 * 退回菜单配置
 * @author fuliwei
 *
 */
public interface MenuBackSetService {
	
	/**
	 * 根据工程类型和当前的id去查返回的list
	 * @author fuliwei
	 * @createTime 2017年11月30日
	 * @param 
	 * @return
	 */
	public List<MenuBackSet> queryMenuBackSetByProjType(FallbackApplyReq req); 
	
	/**
	 * 通过menuId查询是否有效
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	public String queryMenuBackSetByMenuId(FallbackApplyReq req);

	/**
	 * 查询回退表数据
	 * @return
	 */
	Map<String, Object> getDataList(MenuBackSetReq req);

	public MenuBackSet getDataById(String id);

	public Map<String, Object> getStepDataList(MenuBackSetReq req );

	public String saveUpdateData(MenuBackSet menuBackSet);

	public String deleteData(String id);

	/**
	 * 注释：根据工程状态得到相应步骤下的菜单ID
	 * @author liaoyq
	 * @createTime 2019年8月9日
	 * @param projStatusId
	 * @return
	 *
	 */
	public String findMenuIdByProjStatusId(String projStatusId);

    List<MenuBackSet> queryRollBackNode(FallbackApplyReq req);
}
