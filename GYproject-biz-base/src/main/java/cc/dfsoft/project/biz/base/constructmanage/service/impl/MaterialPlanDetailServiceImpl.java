package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.dto.MaterialListQueryReq;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialCollarDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialPlanDetailDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialPlanDetail;
import cc.dfsoft.project.biz.base.constructmanage.enums.CertificateCompleteEnum;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialPlanDetailService;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialPlanDetailServiceImpl implements MaterialPlanDetailService{
	
	/**材料计划明细*/
	@Resource
	MaterialPlanDetailDao materialPlanDetailDao;
	
	/**材料清单Dao*/
	@Resource
	MaterialListDao materialListDao;
	
	/**材料领用Dao*/
	@Resource
	MaterialCollarDao materialCollarDao;
	
	/**
	 * 材料计划明细列表查询
	 * @author fuliwei
	 * @createTime 2017-1-30
	 * @param materialListQueryReq
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryMaterialPlanList(MaterialListQueryReq materialListQueryReq) {
		Map<String, Object>  map=materialPlanDetailDao.queryMaterialPlanList(materialListQueryReq);
		List<MaterialPlanDetail> detailList=(List<MaterialPlanDetail>) map.get("data");
		List<MaterialPlanDetail> resultList=new ArrayList();
		if(detailList!=null && detailList.size()>0){
			for(int i=0;i<detailList.size();i++){
				MaterialPlanDetail mpd=detailList.get(i);
				MaterialList material=materialListDao.get(mpd.getMaterialId());
				mpd.setGetAnum(material.getGetAnum());
				mpd.setPlanTotalNum(material.getPlanTotalNum());
				resultList.add(mpd);
			}
		}
		map.put("data", resultList);
		return  map;
	}
	
	/**
	 * 材料计划导出Excel文件
	 * @author fuliwei
	 * @createTime 2017-2-11
	 * @param String  mpId
	 * @return List<MaterialPlan>
	 */
	@Override
	public List<MaterialPlanDetail> exprotExcel(String mpId) {
		List<MaterialPlanDetail> list=materialPlanDetailDao.exprotExcel(mpId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		List<MaterialPlanDetail>  listResult=new ArrayList<MaterialPlanDetail>();
		for(MaterialPlanDetail mp:list){
			if(mp.getCertificateComplete()!=null){
				mp.setCertificateComplete(CertificateCompleteEnum.valueof(mp.getCertificateComplete()).getMessage());
			}
			if(mp.getGetGoodsTime()!=null){
				mp.setGoodTime(sdf.format(mp.getGetGoodsTime()));
			}
			//欠量=设计总量-领用总量
			BigDecimal materialNum=mp.getMaterialNum();//设计总量
			if(materialNum==null){
				materialNum=BigDecimal.ZERO;
			}
			BigDecimal getAnum=mp.getGetAnum();
			if(getAnum==null){
				getAnum=BigDecimal.ZERO;
			}
			mp.setOweNum(materialNum.subtract(getAnum));
			listResult.add(mp);
		}
		return listResult;
	}
	
	/**
	 * 材料计划明细列表查询-修改后
	 * @author fuliwei
	 * @createTime 2017-2-16
	 * @param String projId,Date createDate
	 * @return Map<String,Object>
	 */
	@Override
	public Map<String, Object> queryMaterialPlanDetailList(MaterialListQueryReq materialListQueryReq) {
		
		Map<String, Object>  map=materialPlanDetailDao.queryMaterialPlanList(materialListQueryReq);
		List<MaterialPlanDetail> detailList=(List<MaterialPlanDetail>) map.get("data");
		if(detailList!=null && detailList.size()>0){
			return map;
		}else{
			if(StringUtils.isNotBlank(materialListQueryReq.getCreateDate())){
				List list=materialCollarDao.queryMaterialPlanDeatilList(materialListQueryReq.getProjId(), materialListQueryReq.getCreateDate());
				List<MaterialPlanDetail> returnList=new ArrayList<MaterialPlanDetail>();
				MaterialList ml=null;
				
				Map<String, Object> reusltMap=new HashMap<String, Object>();
				if(list!=null && list.size()>0){
					for(int i=0;i<list.size();i++){
						MaterialPlanDetail mpd=new MaterialPlanDetail();
						Object[]obj = (Object[])list.get(i);
						BigDecimal receiveSum=(BigDecimal) obj[0];
						//BigDecimal sums=new BigDecimal(receiveSum);//当天累计领用量
						String materialName=(String) obj[1];//材料名称
						//根据材料名称查找
						ml=materialListDao.queryMaterialList(materialName, materialListQueryReq.getProjId());
						if(ml!=null){
							//mpd.setMpId(materialListQueryReq.getMpId());	//材料计划id
							mpd.setProjId(materialListQueryReq.getProjId());//工程id
							mpd.setMaterialId(ml.getMaterialId());			//材料主键id
							mpd.setMaterialName(materialName);				//材料名称
							mpd.setMaterialSpec(ml.getMaterialSpec());		//规格
							mpd.setMaterialUnit(ml.getMaterialUnit());		//单位
							mpd.setMaterialNum(ml.getMaterialNum());		//设计总量
							mpd.setGetAnum(ml.getGetAnum());				//领用总量
							mpd.setThisDayReceiveSum(receiveSum);			//当天领用合计
							returnList.add(mpd);
						}
					}
				}
				reusltMap.put("data", returnList);
				return reusltMap;
			}
		}
		return map;
	}
	
}
