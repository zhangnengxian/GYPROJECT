package cc.dfsoft.project.biz.base.budget.service.impl;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.budget.service.MaterialListService;
import cc.dfsoft.project.biz.base.change.entity.MaterialChange;
import cc.dfsoft.project.biz.base.change.enums.MCTypeEnum;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialCollarDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialCollar;
import cc.dfsoft.project.biz.base.design.entity.DrawingMaterial;
import cc.dfsoft.project.biz.base.design.enums.MaterialFlagEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.ifs.ResultMessage;
import cc.dfsoft.project.biz.ifs.finance.enums.ResultTypeEnum;
import cc.dfsoft.project.biz.ifs.material.dto.MaterialListInfoDTO;
import cc.dfsoft.project.biz.ifs.material.dto.ProjectInfoDTO;
import cc.dfsoft.project.biz.ifs.material.enums.MaterialTableTypeEnum;
import cc.dfsoft.project.biz.ifs.material.enums.UpdateTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.JsonUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialListServiceImpl implements MaterialListService{
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	MaterialListDao materiaListDao;
	@Resource
	MaterialCollarDao materialCollarDao;

	@Override
	public Map<String, Object> queryMaterialList(MaterialListQueryReq materialListQueryReq) {
		return materiaListDao.queryMaterialList(materialListQueryReq);
	}

	@Override
	public List<MaterialList> getExportDataList(String projId) {

		return materiaListDao.getExportDataList(projId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void updateMaterialList(List<MaterialList> list) {
		List<MaterialList> listNew=new ArrayList<MaterialList>();
	    if(null!=list&&list.size()>0){
		   for(MaterialList m :list){
			   MaterialList ml= materiaListDao.get(m.getMaterialId());
			   ml.setMaterialId(m.getMaterialId());
			   ml.setMaterialNum(m.getMaterialNum());
			   ml.setMaterialUnit(m.getMaterialUnit());
			   listNew.add(ml);
			   materiaListDao.batchUpdateObjects(listNew);
		   }
	   }
	}

	@Override
	public List<MaterialChange> getMaterialList(JSONArray jsonArr, String projId) {
		List<DrawingMaterial> list=new ArrayList<DrawingMaterial>();
		List<MaterialList> listml=new ArrayList<MaterialList>();
		List<MaterialChange> mc = new ArrayList<MaterialChange>();
		Project pro = projectDao.get(projId);
		for (int i = 0; i < jsonArr.size(); i++) {
			JSONObject jo = jsonArr.getJSONObject(i);
			if(StringUtils.isBlank(jo.getString("dmName"))){
				continue;
			}
			DrawingMaterial dm=JsonUtils.jsonToBean(jo, DrawingMaterial.class);
			dm.setDmId(IDUtil.getUniqueId(Constants.MODULE_CODE_DESIGN));
			dm.setProjId(projId);
			dm.setProjNo(pro.getProjNo());
			list.add(dm);
			
			MaterialChange m = new MaterialChange();
			m.setProjId(projId); //工程ID
			m.setProjNo(pro.getProjNo());	//工程编号
			m.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));	//材料清单ID
			m.setMaterialName(dm.getDmName());	//材料名称
			m.setMaterialSpec(dm.getDmSpec());	//规格型号
			m.setMaterialUnit(dm.getDmUnit());	//单位
			if(null==dm.getDmNum()||"".equals(dm.getDmNum())){
				m.setAdjustQuantity(new BigDecimal(0));	//调节量
			}else{
				m.setAdjustQuantity(dm.getDmNum());	//调节量
			}
			m.setMcType(MCTypeEnum.MATERIAL_CHANGE.getValue());
			
			/*MaterialList ml=new MaterialList();
			ml.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_BUDGET));
			ml.setProjId(projId);
			ml.setProjName(pro.getProjName());
			ml.setProjNo(pro.getProjNo());
			ml.setMaterialNo(dm.getDmNo());
			ml.setMaterialName(dm.getDmName());
			ml.setMaterialSpec(dm.getDmSpec());
			ml.setMaterialUnit(dm.getDmUnit());
			ml.setMaterialNum(dm.getDmNum());
			ml.setRemark(dm.getRemark());
			listml.add(ml);*/
			mc.add(m);
		}
		return mc;
	}
	
	/**
	 * 列表查询
	 * @author fuliwei
	 * @createTime 2017-02-19
	 * @param projId
	 * @return
	 */
	@Override
	public List<MaterialList> queryMaterialListById(String projId) {
		List<MaterialList> list=materiaListDao.queryMaterialListById(projId);
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				MaterialList mp=list.get(i);
				BigDecimal  materialNum=mp.getMaterialNum();
				if(materialNum==null){
					materialNum=BigDecimal.ZERO;
				}
				BigDecimal  getAnum=mp.getGetAnum();
				if(getAnum==null){
					getAnum=BigDecimal.ZERO;
				}
				mp.setOweNum(materialNum.subtract(getAnum));
			}
		}
		return list;
	}
	
	
	/**
	 * 调用接口处理-弃用
	 * @author fuliwei
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public ResultMessage updateMaterialReceive(ProjectInfoDTO projInfoDto, Map<String,Object> materialInfoList,String operateType) {
		ResultMessage resultMessage = new ResultMessage();
		List<Project> projs=projectDao.findByProjNo(projInfoDto.getProj_no());
		Project proj = new Project();
		if(projs == null || projs.size()<1){
			 resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
			 resultMessage.setRet_message("[proj_no]:"+projInfoDto.getProj_no()+",系统中没有该工程信息!");
			 return resultMessage;
		}else{
			 proj = projs.get(0);
		}
		//材料清单列表
		List<MaterialList> materialList = new ArrayList<MaterialList>();
		List<MaterialList> materialListOld = new ArrayList<MaterialList>();
		List<MaterialCollar> mCollarList = new ArrayList<MaterialCollar>();
		
		//遍历map
		Iterator<Map.Entry<String, Object>> entries = materialInfoList.entrySet().iterator();  
		while (entries.hasNext()) { 
		    Map.Entry<String, Object> entry = entries.next(); 
		    //材料信息
		    Object obj= entry.getValue();
		    //判断是否传递材料信息
		    if(entry.getValue()==null){
				 resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				 resultMessage.setRet_message("["+entry.getKey()+"]:"+entry.getValue()+",传递参数不正确!");
				 return resultMessage;
			}
		    //将对象转为JSONObject
		    net.sf.json.JSONObject jsonObj =  net.sf.json.JSONObject.fromObject(obj);
			//将JSONObject转为bean
		    MaterialListInfoDTO mlid = (MaterialListInfoDTO)net.sf.json.JSONObject.toBean(jsonObj,MaterialListInfoDTO.class);
		    
		    //组装材料清单信息
		    MaterialList ml = new MaterialList();
		    
		    //组装材料领用信息
		    MaterialCollar materialCollar = new MaterialCollar();
		    
		    //材料查询条件-材料名称+规格 
		    MaterialListQueryReq req = new MaterialListQueryReq();
		    
		    //工程ID
		    ml.setProjId(proj.getProjId());
		    materialCollar.setProjId(proj.getProjId());
		    req.setProjId(proj.getProjId());
		    
		    //工程编号
			ml.setProjNo(StringUtil.isNotBlank(proj.getProjNo())?proj.getProjNo():"");
			//物料编码
			if(StringUtil.isBlank(mlid.getMaterial_no())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_no]:"+mlid.getMaterial_name()+",材料编码为空！");
				return resultMessage;
			}
			ml.setMaterialCode(mlid.getMaterial_no());
			materialCollar.setBmNo(mlid.getMaterial_no());
			
			//材料名称
			if(StringUtil.isBlank(mlid.getMaterial_name())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_name]:"+mlid.getMaterial_name()+",材料名称为空！");
				return resultMessage;
			}
			ml.setMaterialName(mlid.getMaterial_name());
			materialCollar.setBmName(mlid.getMaterial_name());
			req.setMaterialName(mlid.getMaterial_name());
			
			//规格型号
			if(StringUtil.isBlank(mlid.getMaterial_spec())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_spec]:"+mlid.getMaterial_spec()+",规格型号为空！");
				return resultMessage;
			}
			ml.setMaterialSpec(mlid.getMaterial_spec());//规格型号
			materialCollar.setBmSpec(mlid.getMaterial_spec());
			req.setMaterialSpec(mlid.getMaterial_spec());
			
			if(StringUtil.isBlank(mlid.getMaterial_unit())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_unit]:"+mlid.getMaterial_unit()+",材料单位为空！");
				return resultMessage;
			}
			ml.setMaterialUnit(mlid.getMaterial_unit());//单位
			materialCollar.setBmUnit(mlid.getMaterial_unit());
			
			if(StringUtil.isBlank(mlid.getMaterial_num())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_num]:"+mlid.getMaterial_num()+",设计数量为空！");
				return resultMessage;
			}
			try{
				ml.setMaterialNum(new BigDecimal(mlid.getMaterial_num()));	//设计数量
			}catch(Exception e){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_num]:"+mlid.getMaterial_num()+",设计数量传递参数不正确！");
				return resultMessage;
			}
			
			//材料类型
			if(StringUtil.isBlank(mlid.getMaterial_table_type())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_table_type]:"+mlid.getMaterial_table_type()+",材料类型为空！");
				return resultMessage;
			}
			ml.setMaterialTableType(mlid.getMaterial_table_type());     
			materialCollar.setMcType(mlid.getMaterial_table_type());
			
			//本次领用量
			if(StringUtil.isBlank(mlid.getMaterial_receive_num())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_receive_num]:"+mlid.getMaterial_receive_num()+",本次领用量为空！");
				return resultMessage;
			}
			try{
				ml.setPlanNum(new BigDecimal(mlid.getMaterial_receive_num()));//本次领用量
				materialCollar.setMcNum(new BigDecimal(mlid.getMaterial_receive_num()));
			}catch(Exception e){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_receive_num]:"+mlid.getMaterial_receive_num()+",本次领用量传递参数不正确！");
				return resultMessage;
			}
			//单据编号
			if(StringUtil.isNotBlank(projInfoDto.getBillno())){
				materialCollar.setBillNo(projInfoDto.getBillno());
			}
			//单据日期
			if(StringUtil.isNotBlank(projInfoDto.getBilldate())){
				materialCollar.setBillDate(projInfoDto.getBilldate());
			}
			//施工单位
			if(StringUtil.isNotBlank(projInfoDto.getConstruction_unit())){
				materialCollar.setCollarUnit(projInfoDto.getConstruction_unit());
			}
			//系统领料日期
			materialCollar.setMgDate(materialCollarDao.getDatabaseDate());
			//材料领用单ID
			materialCollar.setMcId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
			//操作类型
			materialCollar.setOperateType(operateType);
			
			//领料单信息
			mCollarList.add(materialCollar);

			//查询材料清单是否有此材料
			MaterialList mlOld = materiaListDao.queryMaterial(req);
			BigDecimal oldNum = new BigDecimal(0);
			if(mlOld!=null){
				oldNum = oldNum.add(mlOld.getGetAnum()!=null?mlOld.getGetAnum():oldNum);
				//按工程id删除材料目录
				materialListOld.add(mlOld);
			}else{
				
				ml.setStatus("1");//工程系统的材料清单中没有，但是领用了此材料
			}
			ml.setMaterialId(IDUtil.getUniqueId(Constants.MODULE_CODE_CHANGE));
			//领用总量
			if(StringUtil.isBlank(mlid.getGet_anum())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[get_anum]:"+mlid.getMaterial_receive_num()+",领用总量为空！");
				return resultMessage;
			}
			//领用总量为之前的领用总量+当前领用量
			try{
				ml.setGetAnum(new BigDecimal(mlid.getMaterial_receive_num()).add(oldNum));			  //领用总量
			}catch(Exception e){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[get_anum]:"+mlid.getMaterial_receive_num()+",领用总量传递参数不正确！");
				return resultMessage;
			}
			//材料清单
			materialList.add(ml);
		}
		
		//插入领用材料信息
		materialCollarDao.batchInsertObjects(mCollarList);
		//先删除旧材料
		materiaListDao.batchDeleteObjects(materialListOld);
		//插入材料清单
		materiaListDao.batchInsertObjects(materialList);
		resultMessage.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
		resultMessage.setRet_message("已成功更新材料领用信息！");
		return resultMessage;
	}
	
	/**
	 * 调用接口处理，接口传递过来的是材料明细
	 * 1.判断接口传递过的的材料参数是否正确
	 * 2.材料规格作为材料清单的ID,根据ID判断，如果存在此材料，累加此类材料的领用量，如果ID不存在，返回信息，不插入数据
	 * 3.累加后的数据更新到材料清单表，材料明细保存到材料领用表
	 * @author liaoyq
	 * @createTime 2017年11月13日
	 * @param 
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public ResultMessage updateMaterialsReceive(ProjectInfoDTO projInfoDto, Map<String,Object> materialInfoList,String operateType) {
		ResultMessage resultMessage = new ResultMessage();
		List<Project> projs=projectDao.findByProjNo(projInfoDto.getProj_no());
		Project proj = new Project();
		if(projs == null || projs.size()<1){
			 resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
			 resultMessage.setRet_message("[proj_no:"+projInfoDto.getProj_no()+",系统中没有该工程信息!]");
			 return resultMessage;
		}else{
			 proj = projs.get(0);
		}
		//材料清单列表
		List<MaterialList> materialList = new ArrayList<MaterialList>();
		List<MaterialCollar> mCollarList = new ArrayList<MaterialCollar>();
		Map<String ,MaterialList> map = new HashMap<String,MaterialList>();//存储大类的规格-材料领用表的ID
		//遍历map
		Iterator<Map.Entry<String, Object>> entries = materialInfoList.entrySet().iterator();  
		while (entries.hasNext()) { 
		    Map.Entry<String, Object> entry = entries.next(); 
		    //材料信息
		    Object obj= entry.getValue();
		    //判断是否传递材料信息
		    if(entry.getValue()==null){
				 resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				 resultMessage.setRet_message("["+entry.getKey()+":"+entry.getValue()+",传递参数不正确!]");
				 return resultMessage;
			}
		    //将对象转为JSONObject
		    net.sf.json.JSONObject jsonObj =  net.sf.json.JSONObject.fromObject(obj);
			//将JSONObject转为bean
		    MaterialListInfoDTO mlid = (MaterialListInfoDTO)net.sf.json.JSONObject.toBean(jsonObj,MaterialListInfoDTO.class);
		    
		    //组装材料领用信息
		    MaterialCollar materialCollar = new MaterialCollar();
		    
		    //材料查询条件-材料ID
		    MaterialListQueryReq req = new MaterialListQueryReq();
		    
		    //工程ID
		    materialCollar.setProjId(proj.getProjId());
		    req.setProjId(proj.getProjId());
		    //工程编号
			//物料编码
			if(StringUtil.isBlank(mlid.getMaterial_no())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_no:"+mlid.getMaterial_name()+",材料编码为空！]");
				return resultMessage;
			}
			materialCollar.setBmNo(mlid.getMaterial_no());
			//物料编码
			if(StringUtil.isBlank(mlid.getSupname())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[supname:"+mlid.getSupname()+",物料供应商为空！]");
				return resultMessage;
			}
			materialCollar.setSupname(mlid.getSupname());
			
			//材料名称
			if(StringUtil.isBlank(mlid.getMaterial_name())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_name:"+mlid.getMaterial_name()+",材料名称为空！]");
				return resultMessage;
			}
			materialCollar.setBmName(mlid.getMaterial_name());
			req.setMaterialName(mlid.getMaterial_name());
			
			//规格型号
			if(StringUtil.isBlank(mlid.getMaterial_spec())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_spec:"+mlid.getMaterial_spec()+",规格型号为空！]");
				return resultMessage;
			}
			//规格型号-材料清单ID
			materialCollar.setBmSpec(mlid.getMaterial_spec());
			req.setMaterialSpec(mlid.getMaterial_spec());
			
			if(StringUtil.isBlank(mlid.getMaterial_unit())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_unit:"+mlid.getMaterial_unit()+",材料单位为空！]");
				return resultMessage;
			}
			//单位
			materialCollar.setBmUnit(mlid.getMaterial_unit());
			
			if(StringUtil.isBlank(mlid.getMaterial_num())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_num:"+mlid.getMaterial_num()+",设计数量为空！]");
				return resultMessage;
			}

			//材料类型
			if(StringUtil.isBlank(mlid.getMaterial_table_type())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_table_type:"+mlid.getMaterial_table_type()+",材料类型为空！]");
				return resultMessage;
			}     
			materialCollar.setMcType(mlid.getMaterial_table_type());
			//变更材料增加变更ID过滤
			if(MaterialTableTypeEnum.CHANGE_MATERIAL.getValue().equals(mlid.getMaterial_table_type())){
			    req.setCmId(projInfoDto.getChange_id());//变更单ID
			}
			//本次领用量
			if(StringUtil.isBlank(mlid.getMaterial_receive_num())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_receive_num:"+mlid.getMaterial_receive_num()+",本次领用量为空！]");
				return resultMessage;
			}
			try{//本次领用量
				materialCollar.setMcNum(new BigDecimal(mlid.getMaterial_receive_num()));
			}catch(Exception e){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_receive_num:"+mlid.getMaterial_receive_num()+",本次领用量传递参数不正确！]");
				return resultMessage;
			}
			//单据编号
			if(StringUtil.isNotBlank(projInfoDto.getBillno())){
				materialCollar.setBillNo(projInfoDto.getBillno());
			}
			//单据日期
			if(StringUtil.isNotBlank(projInfoDto.getBilldate())){
				materialCollar.setBillDate(projInfoDto.getBilldate());
			}
			//施工单位
			if(StringUtil.isNotBlank(projInfoDto.getConstruction_unit())){
				materialCollar.setCollarUnit(projInfoDto.getConstruction_unit());
			}
			//系统领料日期
			materialCollar.setMgDate(materialCollarDao.getDatabaseDate());
			//材料领用单ID
			materialCollar.setMcId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
			//操作类型
			materialCollar.setOperateType(operateType);
			
			//领料单信息
			mCollarList.add(materialCollar);

			//查询材料清单是否有此材料-没有抛出
			//此材料存在，则更新原材料的领用总量，当前明细材料的领用量加之前的材料领用量
			req.setFlag(MaterialFlagEnum.YES.getValue());//物资购买的材料
			MaterialList mlOld = materiaListDao.queryMaterial(req);
			//原材料的领用量
			BigDecimal oldNum = new BigDecimal(0);
			if(mlOld!=null){
				oldNum = oldNum.add(mlOld.getGetAnum()!=null?mlOld.getGetAnum():oldNum);
				
			}else{
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[material_spec:"+mlid.getMaterial_spec()+"],[material_name:"+mlid.getMaterial_name()+"],领用材料的名称和规格不存在！");
				return resultMessage;
			}
			//领用总量
			if(StringUtil.isBlank(mlid.getGet_anum())){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[get_anum:"+mlid.getMaterial_receive_num()+",领用总量为空！]");
				return resultMessage;
			}
			//领用总量为之前的领用总量+当前细类领用量
			try{
				if(UpdateTypeEnum.BACK_MATERIAL.getValue().equals(operateType)){//退料之前的减去退量
					mlOld.setGetAnum(oldNum.subtract(new BigDecimal(mlid.getMaterial_receive_num())));			  //领用总量
				}else{
					mlOld.setGetAnum(new BigDecimal(mlid.getMaterial_receive_num()).add(oldNum));			  //领用总量
				}
			}catch(Exception e){
				resultMessage.setRet_type(ResultTypeEnum.FAIL.getValue());
				resultMessage.setRet_message("[get_anum:"+mlid.getMaterial_receive_num()+",领用总量传递参数不正确！]");
				return resultMessage;
			}
			//map是否已存在
			map.put(mlid.getMaterial_spec(), mlOld);
		}
		
		//插入领用材料细类信息
		materialCollarDao.batchInsertObjects(mCollarList);
		//批量修改大类信息
		for(String key : map.keySet()){
			materialList.add(map.get(key));
		}
		materiaListDao.batchInsertOrUpdateObjects(materialList);
		resultMessage.setRet_type(ResultTypeEnum.SUCCUSS.getValue());
		resultMessage.setRet_message("已成功更新材料领用信息！");
		return resultMessage;
	}
	
	// Map --> Bean 2: 利用org.apache.commons.beanutils 工具类实现 Map --> Bean  
    public static void transMap2Bean2(Map<String, Object> map, MaterialList ml) {  
        if (map == null || ml == null) {  
            return;  
        }  
        try {  
            BeanUtils.populate(ml, map);  
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }  
}
