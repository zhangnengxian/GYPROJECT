package cc.dfsoft.project.biz.base.project.service.impl;

import cc.dfsoft.project.biz.base.common.dao.MenuStepDao;
import cc.dfsoft.project.biz.base.common.entity.MenuStep;
import cc.dfsoft.project.biz.base.project.dao.MenuBackSetDao;
import cc.dfsoft.project.biz.base.project.dto.FallbackApplyReq;
import cc.dfsoft.project.biz.base.project.dto.MenuBackSetReq;
import cc.dfsoft.project.biz.base.project.entity.MenuBackSet;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.project.biz.base.project.enums.StepEnum;
import cc.dfsoft.project.biz.base.project.service.MenuBackSetService;
import cc.dfsoft.uexpress.biz.sys.auth.dao.MenuDao;
import cc.dfsoft.uexpress.biz.sys.auth.entity.Menu;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.enums.WorkFlowActionEnum;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 退回菜单配置
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MenuBackSetServiceImpl implements MenuBackSetService{
	
	/**退回菜单Dao*/
	@Resource
	MenuBackSetDao menuBackSetDao;
	@Resource
	MenuStepDao menuStepDao;

	@Resource
	DepartmentDao departmentDao;

	@Resource
	MenuDao menuDao;
	/**
	 * 根据工程类型和当前的id去查返回的list
	 * @author fuliwei
	 * @createTime 2017年11月30日
	 * @param 
	 * @return
	 */
	@Override
	public List<MenuBackSet> queryMenuBackSetByProjType(FallbackApplyReq req) {
		
		//从库中查出一条数据
		List<MenuBackSet> list=menuBackSetDao.queryMenuBackSetByProjType(req);

		List<MenuBackSet> returnList=new ArrayList<MenuBackSet>();
		
		MenuBackSet menuBackSet;

		Boolean flag = true;
		//工程信息修改 且是已竣工
		if(Constants.PROJ_MODIFY_MENUID.equals(req.getMenuId()) && !ProjStatusEnum.ALREADY_COMPLETED.getValue().equals(req.getProjStatusId())){
			flag = false;
		}

		//库中存的是1201,1302
		if(list!=null && list.size()>0){
			MenuBackSet msc=list.get(0);
			String stepIds=msc.getBackStepId();
			String [] stepArray=stepIds.split(","); 
			String [] stepDesArray=msc.getBackStepDes().split(","); 
			if(stepArray.length>0){
				for(int i=0;i<stepArray.length;i++){
					menuBackSet=new MenuBackSet();
					menuBackSet.setMbsId(msc.getMbsId());//回退配置表ID

					if(!flag && (StepEnum.SETTLEMENT_REPORT.getValue().equals(stepArray[i])
							||StepEnum.SETTLEMENT_REPORT_START.getValue().equals(stepArray[i])
					        ||StepEnum.REPORT_DONE_CONFIRM.getValue().equals(stepArray[i]))){
						continue;
					}

					menuBackSet.setBackStepId(stepArray[i]);                                  //回退步骤id
					menuBackSet.setBackStepDes(stepDesArray[i]);  							  //回退步骤
					returnList.add(menuBackSet);
				}
			}
			
		}
		
		
		return returnList;
	}
	
	/**
	 * 通过menuId查询是否有效
	 * @author fuliwei
	 * @createTime 2017年12月1日
	 * @param 
	 * @return
	 */
	@Override
	public String queryMenuBackSetByMenuId(FallbackApplyReq req) {
		
		List<MenuBackSet> list=menuBackSetDao.queryMenuBackSetByProjType(req);
		
		if(list!=null && list.size()>0){
			return Constants.OPERATE_RESULT_SUCCESS;
		}else{
			return Constants.OPERATE_RESULT_FAILURE;
		}
	}

	@Override
	public Map<String, Object> getDataList(MenuBackSetReq req) {
		Map<String, Object> map=menuBackSetDao.getDataList(req);
		List<MenuBackSet> list= (List<MenuBackSet>) map.get("data");
		if(list!=null && list.size()>0){
			for(MenuBackSet mb:list){
				Department department=departmentDao.get(mb.getCorpId());
				if(department!=null){
					mb.setCorpName(department.getDeptName());
				}
			}
		}
		return map;
	}

	@Override
	public MenuBackSet getDataById(String id) {
		MenuBackSet menuBackSet =menuBackSetDao.get(id);
		if(menuBackSet != null){
			MenuStep menuStep = menuStepDao.findByMenuId(menuBackSet.getMenuId());
			if(menuStep != null){
				menuBackSet.setMenuId(menuStep.getStepId());
			}else{
				menuBackSet.setMenuId("");
			}
		}
		return menuBackSet;
	}

	@Override
	public Map<String, Object> getStepDataList(MenuBackSetReq req) {
		MenuBackSet me = menuBackSetDao.get(req.getMbsId());
		List<Map<String,Object>> list = new ArrayList<>();
		int count = 0;
		if(me  == null){
			return ResultUtil.pageResult(count, req.getDraw(),list);
		}
		String stepId = me.getBackStepId();
		String stepName = me.getBackStepDes();
		if(StringUtil.isNotBlank(stepId)){
			String [] stepIds = stepId.split(",");
			String [] stepNames = stepName.split(",");
			count = stepIds.length;
			for(int i=0;i<stepIds.length;i++){
				Map<String,Object> map = new HashMap<>();
				map.put("backStepId",stepIds[i]);
				map.put("backStepDes",stepNames[i]);
				list.add(map);
			}
		}else{
			list = null;
		}
		return ResultUtil.pageResult(count, req.getDraw(),list);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveUpdateData(MenuBackSet menuBackSet) {
		MenuStep menuStep = menuStepDao.findByStepId(menuBackSet.getMenuId());
		if(menuStep != null){
			menuBackSet.setMenuId(menuStep.getMeunId());
			Menu menu=menuDao.get(menuStep.getMeunId());
			menuBackSet.setMenuDes(menu.getMenuName());
		}else{
			menuBackSet.setMenuId("");
		}
		if(StringUtil.isBlank(menuBackSet.getMbsId())){
			//已存在
			if(menuBackSetDao.isExisting(menuBackSet)!=null){
				return Constants.OPERATE_RESULT_FAILURE;
			}
			menuBackSet.setMbsId(IDUtil.getUniqueId(Constants.MODULE_CODE_AUTH));
		}
		menuBackSet.setUpdateTime(menuBackSetDao.getDatabaseDate());
		menuBackSet.setUpdateUser(SessionUtil.getLoginInfo().getLoginAccount());
		menuBackSetDao.saveOrUpdate(menuBackSet);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String deleteData(String id) {
		menuBackSetDao.delete(id);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	@Override
	public String findMenuIdByProjStatusId(String projStatusId) {
		String stepId = WorkFlowActionEnum.getStepCodeByStatusCode(projStatusId);
		if(StringUtil.isNoneBlank(stepId)){
			MenuStep menuStep = menuStepDao.findByStepId(stepId);
			if(menuStep != null){
				return menuStep.getMeunId();
			}
		}
		return null;
	}




	/**
	* @Description: 获取回退节点
	* @author zhangnx
	* @date 2019/8/24 10:05
	*/
	@Override
	public List<MenuBackSet> queryRollBackNode(FallbackApplyReq req) {
		MenuBackSet mbs=menuBackSetDao.quertMenuBackSet(req);
		if (mbs==null) return null;
		if (StringUtil.isBlank(mbs.getBackStepId())) return null;

		List<MenuBackSet> resultList=new ArrayList<>();
		String[] stepIds = mbs.getBackStepId().split(",");
		for (String stepId:stepIds) {
			MenuBackSet m=new MenuBackSet();
			m.setMbsId(mbs.getMbsId());//回退配置表ID
			m.setIsAudit(mbs.getIsAudit());//是否需要审核
			m.setBackStepId(stepId);
			m.setBackStepDes(StepEnum.getNameByCode(stepId));
			resultList.add(m);
		}
		return resultList;
	}
}
