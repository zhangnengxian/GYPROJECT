package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.text.ParseException;
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

import cc.dfsoft.project.biz.base.baseinfo.dao.BusinessPartnersDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.BusinessPartnersReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.BusinessPartners;
import cc.dfsoft.project.biz.base.baseinfo.service.BusinessPartnersService;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.uexpress.biz.sys.dept.dao.StaffDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Staff;
import cc.dfsoft.uexpress.biz.sys.dept.enums.UnitTypeEnum;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class BusinessPartnersServiceImpl implements BusinessPartnersService{
	
	/**业务合作伙伴dao*/
	@Resource
	BusinessPartnersDao businessPartnersDao;
	@Resource
	StaffDao staffDao;
	
	@Override
	public Map<String, Object> queryBusinessPartners(BusinessPartnersReq businessPartnersReq) throws ParseException {
		

		//用分公司id去查库
		//不满足，可选择多个施工单位、多个监理单位
		/*Object obj = Constants.getSysConfigByKey(businessPartnersReq.getCorpId()+"_"+businessPartnersReq.getUnitType()+"_BusinessPartners");
		if(obj==null){
			//默认返回业务合作伙伴,走原来的方法
			return businessPartnersDao.queryBusinessPartners(businessPartnersReq);
		}*/
		//六盘水情况特殊，监理负责与集团不一样
		Map<String, Object> map=businessPartnersDao.queryBusinessPartners(businessPartnersReq);
		List<BusinessPartners> list= (List<BusinessPartners>) map.get("data");
		Staff staff = new Staff();
		if(list!=null && list.size()>0 && staff!=null){
			for(BusinessPartners bp:list){
				//配置规则：分公司ID_施工监理单位ID_BusinessPartners
				Object obj = Constants.getSysConfigByKey(businessPartnersReq.getCorpId()+"_"+bp.getUnitId()+"_BusinessPartners");
				if(obj!=null && StringUtils.isNotBlank(obj.toString())){
					staff=staffDao.get(obj.toString());
				}
				if(staff != null && bp.getUnitId().equals(staff.getDeptId())){
					//便利合作伙伴，如果是配置的六盘水监理负责人，则进行替换
					bp.setUnitDirector(staff.getStaffName());//负责人
					bp.setUnitMobile(staff.getMobile()); //电话
				}
			}
		}
		return map;
	}

	@Override
	public BusinessPartners viewBusinessPartnersById(String id) {
		return businessPartnersDao.get(id);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveBusinessPartners(BusinessPartners businessPartners) {
		//BusinessPartners businessPartnersResult=businessPartners;
		if(StringUtils.isBlank(businessPartners.getUnitId())){
			businessPartners.setUnitId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));//生成主键ID 先用分包ID
			
		}/*else{ 
			businessPartnersResult = businessPartnersDao.get(businessPartners.getUnitId());
			businessPartnersResult.setUnitName(businessPartners.getUnitName());
			businessPartnersResult.setShortName(businessPartners.getShortName());
			businessPartnersResult.setUnitDirector(businessPartners.getUnitDirector());
			businessPartnersResult.setUnitPhone(businessPartners.getUnitPhone());
			businessPartnersResult.setUnitMobile(businessPartners.getUnitMobile());
			businessPartnersResult.setUnitFoundDate(businessPartners.getUnitFoundDate());
			businessPartnersResult.setUnitQualification(businessPartners.getUnitQualification());
			businessPartnersResult.setUnitType(businessPartners.getUnitType());
		}*/
		if(StringUtils.isBlank(businessPartners.getUnitCode())){
			businessPartners.setUnitCode(this.getUnitCode(AreaEnum.GUIYANG.getValue(),businessPartners.getUnitType()));
		}
		businessPartnersDao.saveOrUpdate(businessPartners);
	}
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void deleteBusinessPartners(String id) {
		businessPartnersDao.delete(id);
	}

	@Override
	public List<Map<String, Object>> getDataTree(String type) {
		BusinessPartners req=new BusinessPartners();
		req.setUnitType(type);
		List<BusinessPartners> list= businessPartnersDao.findByExample(req);
		List<String> types=new ArrayList<String>();
		List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
		for(BusinessPartners qt:list){			
            if(!types.contains(qt.getUnitType())){
            	Map<String, Object> parmap=new HashMap<String, Object>();
            	//父级节点
            	parmap.put("id",qt.getUnitType());
            	parmap.put("parent", "#");
            	parmap.put("text", UnitTypeEnum.valueof(qt.getUnitType()).getMessage());
            	types.add(qt.getUnitType());
				result.add(parmap);
            	
			}	
                Map<String, Object> map=new HashMap<String, Object>();
				//子节点
				map.put("id",qt.getUnitId());
				map.put("parent", qt.getUnitType());
				map.put("text", qt.getUnitName());				
				result.add(map);
		}
		 return result;
	}

	@Override
	public List getCuUnit() {
		return businessPartnersDao.getCuUnit();
	}
	
	private String getUnitCode(String areaCode,String unitType){
		if(areaCode == null){
			return null;
		}
		//客户编码前缀
		String strPrex = areaCode+unitType;
		//获取当前的日期
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String unitCode = strPrex+format.format(businessPartnersDao.getDatabaseDate());
		return unitCode;
	}

}
