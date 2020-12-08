package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialInspectionDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialInspection;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialInspectionService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialInspectionServiceImpl implements MaterialInspectionService{
	
	
	/**材料报验dao*/
	@Resource
	MaterialInspectionDao materialInspectionDao;
	
	/**材料清单dao*/
	@Resource
	MaterialListDao marterialListDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialInspections(List<MaterialInspection> materialInspections) {

		if(materialInspections!=null && materialInspections.size()>0){
			for(int i=0;i<materialInspections.size();i++){
				MaterialInspection mi = materialInspections.get(i);
				//设置id
				mi.setMiId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				mi.setMaterialInspector(loginInfo!=null?loginInfo.getStaffId():"");//报验人
				if(mi.getBmPrice()!=null && mi.getMiNum()!=null){
					Double amount = mi.getBmPrice().multiply(mi.getMiNum()).doubleValue();
					mi.setMiAmount(new BigDecimal(amount+""));//合计金额
				}
				MaterialList ml = marterialListDao.get(mi.getMaterialId());
				Double anum = 0.00;
				if(mi.getMiNum()!=null && ml.getInspectionAnum()!=null){
					anum = mi.getMiNum().doubleValue()+ml.getInspectionAnum();//计算材料报验数量
				}else if(mi.getMiNum()!=null){
					anum = mi.getMiNum().doubleValue();
				}else if(ml.getInspectionAnum()!=null){
					anum = ml.getInspectionAnum();
				}
				ml.setInspectionAnum(anum);//更新材料报验总量
				marterialListDao.update(ml);//更新材料清单
				materialInspectionDao.save(materialInspections.get(i));//新增报验记录
			}
		}
	
	}

}
