package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.dao.MaterialListDao;
import cc.dfsoft.project.biz.base.budget.entity.MaterialList;
import cc.dfsoft.project.biz.base.constructmanage.dao.MaterialUseDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.MaterialUse;
import cc.dfsoft.project.biz.base.constructmanage.service.MaterialUseService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class MaterialUseServiceImpl implements MaterialUseService{
	
	
	/**材料使用dao*/
	@Resource
	MaterialUseDao materialUseDao;
	
	/**材料清单dao*/
	@Resource
	MaterialListDao marterialListDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialUses(List<MaterialUse> materialUses) {
		if(materialUses!=null && materialUses.size()>0){
			for(int i=0;i<materialUses.size();i++){
				MaterialUse mu = materialUses.get(i);
				//设置id
				mu.setMuId(IDUtil.getUniqueId(Constants.MODULE_CODE_MATERIAL));
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				mu.setUseStaffId(loginInfo!=null?loginInfo.getStaffId():"");//使用人
				if(mu.getBmPrice()!=null && mu.getMiNum()!=null){
					Double amount = mu.getBmPrice().multiply(mu.getMiNum()).doubleValue();
					mu.setMiAmount(new BigDecimal(amount+""));//合计金额
				}
				MaterialList ml = marterialListDao.get(mu.getMaterialId());
				Double anum = 0.00;
				if(mu.getMiNum()!=null && ml.getUseAnum()!=null){
					anum = mu.getMiNum().doubleValue()+ml.getUseAnum();//计算材料使用数量
				}else if(mu.getMiNum()!=null){
					anum = mu.getMiNum().doubleValue();
				}else if(ml.getUseAnum()!=null){
					anum = ml.getUseAnum();
				}
				ml.setUseAnum(anum);		//更新材料使用总量
				
				BigDecimal aiAnum = new BigDecimal("0.00");
				if(mu.getAiNum()!=null && ml.getAiAnum()!=null){
					aiAnum = new BigDecimal(mu.getAiNum()+"").add(ml.getAiAnum());
				}else if(mu.getAiNum()!=null){
					aiAnum = new BigDecimal(mu.getAiNum()+"");
				}else if(ml.getAiAnum()!=null){
					aiAnum = ml.getAiAnum();
				}
				ml.setAiAnum(aiAnum);       //更新实际安装总量
				
				marterialListDao.update(ml);//更新材料清单
				materialUseDao.save(materialUses.get(i));//新增使用记录
			}
		}
	}

}
