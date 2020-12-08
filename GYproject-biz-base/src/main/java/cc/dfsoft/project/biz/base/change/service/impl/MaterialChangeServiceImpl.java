package cc.dfsoft.project.biz.base.change.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.change.dao.MaterialChangeDao;
import cc.dfsoft.project.biz.base.change.dto.MaterialChangeReq;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.change.service.MaterialChangeService;
import cc.dfsoft.project.biz.base.contract.dao.ContractDao;
import cc.dfsoft.project.biz.base.contract.dao.SupplementalContractDao;
import cc.dfsoft.project.biz.base.contract.dto.SupplementalContractQueryReq;
import cc.dfsoft.project.biz.base.contract.entity.Contract;
import cc.dfsoft.project.biz.base.design.entity.DrawingMaterial;
import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialChangeServiceImpl implements MaterialChangeService{
	
	@Resource
	ProjectDao projectDao;
	/**材料变更记录dao*/
	@Resource
	MaterialChangeDao materialChangeDao;
	/**补充协议dao*/
	@Resource
	SupplementalContractDao supplementalContractDao;
	/**合同dao*/
	@Resource
	ContractDao contractDao;
	
	@Resource
	MaterialListDao materialListDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialChange(List<MaterialChange> list) {
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		List<MaterialChange> newList=new ArrayList<MaterialChange>();
		List<MaterialList> mll=new ArrayList<MaterialList>();
		Project pro = new Project();
		if(list.size()>0){
			pro = projectDao.get(list.get(0).getProjId());
		}
		for(MaterialChange mc:list){
			if(StringUtil.isNotBlank(mc.getMcId())){
				materialChangeDao.updateNotNullProperties(mc, mc.getMcId());
			}else{
				if(StringUtil.isBlank(mc.getCmId())){ //变更记录id为空
					// 总表新增导入保存
					MaterialList ml = new MaterialList();
					ml.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
					ml.setProjId(mc.getProjId());
					ml.setProjNo(mc.getProjNo());
					ml.setProjName(pro.getProjName());
					ml.setMaterialName(mc.getMaterialName());
					ml.setMaterialSpec(mc.getMaterialSpec());
					ml.setMaterialUnit(mc.getMaterialUnit());
					ml.setMaterialNum(mc.getAdjustQuantity());
					
					mll.add(ml);
				}
			mc.setMcId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROJECTAPPROVAL));
			mc.setOptTime(materialChangeDao.getDatabaseDate());
			mc.setMcOperator(loginInfo.getStaffId());
			
			newList.add(mc);
			}
		}
		materialChangeDao.batchInsertObjects(newList);
		if(mll!=null && mll.size()>0){
			materialListDao.batchInsertObjects(mll);
		}
	}
	@Override
	public Map<String,Object> queryMaterialChange(MaterialChangeReq materialChangeReq){
		return 	materialChangeDao.queryMaterialChange(materialChangeReq);
	}
	@Override
	public Object getSupplement(String cmId, String mcType, String projId) throws ParseException {
		SupplementalContractQueryReq scReq=new SupplementalContractQueryReq();

		/*scReq.setCmId(cmId);
		scReq.setMcType(mcType);*/

		Map<String,Object> map=supplementalContractDao.querySupplementalContract(scReq);
		if(null!=map){
			List list=(List) map.get("data");
			if(null!=list && list.size()>0){
				return list.get(0);
			}else{
				Contract contract=contractDao.viewContractByprojId(projId);
				if(null!=contract){
//				contract.setSignDate(contractDao.getDatabaseDate());
//				LoginInfo loginInfo = SessionUtil.getLoginInfo();
//				contract.setConAgent(loginInfo.getStaffName());				
				return contract;
				}
			}
			
		}
		return new Contract();
	
	}
	
	/**
	 * 变更记录导入材料表
	 * @param jsonArr 
	 * @param req  
	 * 
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batInsertMaterialChange(JSONArray jsonArr, MaterialChangeReq req) {
		List<DrawingMaterial> list=new ArrayList<DrawingMaterial>();
		List<MaterialList> listml=new ArrayList<MaterialList>();
		List<MaterialChange> mc = new ArrayList<MaterialChange>();
		LoginInfo login=SessionUtil.getLoginInfo();
		Project pro = projectDao.get(req.getProjId());
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("dmName"))){
				continue;
			}
			DrawingMaterial dm=JsonUtils.jsonToBean(jo, DrawingMaterial.class);
			dm.setDmId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			dm.setProjId(req.getProjId());
			dm.setProjNo(pro.getProjNo());
			list.add(dm);
			
			MaterialChange m = new MaterialChange();
			m.setProjId(req.getProjId()); //工程ID
			m.setProjNo(pro.getProjNo());	//工程编号
			m.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));	//材料清单ID
			m.setMcId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));//主键id
			m.setMaterialName(dm.getDmName());	//材料名称
			m.setMaterialSpec(dm.getDmSpec());	//规格型号
			m.setMaterialUnit(dm.getDmUnit());	//单位
			//m.setMaterialCode(dm.getMaterialNum());//物料编码
			//m.setMaterialStandard(dm.getMaterialStandard());//规格
			m.setCmId(req.getCmId());//变更id
			m.setOptTime(materialChangeDao.getDatabaseDate());
			m.setMcOperator(login.getStaffId());
			if(null==dm.getDmNum()||"".equals(dm.getDmNum())){
				m.setAdjustQuantity(new BigDecimal(0));	//调节量
			}else{
				m.setAdjustQuantity(dm.getDmNum());	//调节量
			}
			m.setMcType(MCTypeEnum.MATERIAL_CHANGE.getValue());
			m.setFlag(StringUtil.isNotBlank(dm.getFlag())?dm.getFlag():MaterialFlagEnum.YES.getValue());//默认为是物资购买
			mc.add(m);
		}
		//查找之前的材料变更并删除
		List<MaterialChange> li =materialChangeDao.queryMaterialChangeList(req);
		if(li!=null&&li.size()>0){
			materialChangeDao.batchDeleteObjects(li);
		}
		materialChangeDao.batchInsertObjects(mc);
	}
	
	
	/**
	 * 根据工程id查询
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	@Override
	public List<MaterialChange> findById(String projId) {
		return materialChangeDao.findByProjId(projId);
	}
}
