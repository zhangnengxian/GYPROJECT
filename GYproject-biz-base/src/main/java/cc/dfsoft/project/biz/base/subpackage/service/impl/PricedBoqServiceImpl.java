package cc.dfsoft.project.biz.base.subpackage.service.impl;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import antlr.collections.impl.LList;
import cc.dfsoft.project.biz.base.common.dao.VersionDao;
import cc.dfsoft.project.biz.base.common.entity.Version;
import cc.dfsoft.project.biz.base.common.enums.VersionTypeEnum;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.CostTypeEnum;
import cc.dfsoft.project.biz.base.subpackage.dao.PricedBoqDao;
import cc.dfsoft.project.biz.base.subpackage.dto.PriceVersionDto;
import cc.dfsoft.project.biz.base.subpackage.dto.PricedBoqReq;
import cc.dfsoft.project.biz.base.subpackage.entity.PricedBoq;
import cc.dfsoft.project.biz.base.subpackage.service.PricedBoqService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.DateUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class PricedBoqServiceImpl implements PricedBoqService{	
	@Resource
	PricedBoqDao pricedBoqDao;
	@Resource
	VersionDao versionDao;
	@Resource
	ProjectDao projectDao;
	@Override
	public List<Map<String, Object>> queryQuantStandTree(String projId,String versionOfProj,String incIncraMode) {
		Project pro = projectDao.get(projId);  //根据工程ID查询实体
		List<PricedBoq> list = null;
		if(pro != null){
			list  = pricedBoqDao.findByVeid(pro.getCorpId(),versionOfProj,incIncraMode); //查询工程量
		}
			List<String> costType = new ArrayList<String>();
			List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();

		if(list != null && list.size() > 0){
			for (PricedBoq qt : list) {
				if (!costType.contains(qt.getCostType())) {
					Map<String, Object> parmap = new HashMap<String, Object>();
					// 父级节点
					parmap.put("id", qt.getCostType() + "-type");
					parmap.put("parent", "#");
					parmap.put("text", CostTypeEnum.valueof(qt.getCostType()).getMessage());
					costType.add(qt.getCostType());
					result.add(parmap);

				}
				Map<String, Object> map = new HashMap<String, Object>();
				// 子节点
				map.put("id",
						qt.getQsId() + "@@" + qt.getSubitemName() + "@@" + qt.getUnit() + "@@" + qt.getUnitPrice());
				map.put("parent", qt.getCostType() + "-type");
				map.put("text", qt.getSubitemName());
				result.add(map);
			}
		}
			return result;
//		}
//		return null;
	}
	
	@Override
	public Map<String, Object> queryPricedBoq(PricedBoqReq pricedBoqReq) throws ParseException {
		return pricedBoqDao.queryPricedBoq(pricedBoqReq);
	}

	@Override
	public PricedBoq viewPricedBoq(String id) {
		PricedBoq pricedBoq = pricedBoqDao.viewPricedBoq(id);
		pricedBoq.setSurveyDate(versionDao.get(pricedBoq.getVeId()).getVeStartTime().toString());
		return pricedBoq;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdatePricedBoq(PricedBoq pricedBoq) {
		if(StringUtil.isBlank(pricedBoq.getQsId())){
			pricedBoq.setQsId(IDUtil.getUniqueId(Constants.MODULE_CODE_CONTRACT));
		}
//			PricedBoq pricedBoq1 = pricedBoqDao.get(pricedBoq.getVeId());
//			pricedBoq.setVeId(pricedBoq1.getVeId());
			pricedBoqDao.saveOrUpdate(pricedBoq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deletePricedBoq(String id) {
		pricedBoqDao.delete(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveOrUpdatePricedBoqBat(PriceVersionDto dto) throws ParseException {
		Version vs=null;
		if(dto.getVs()!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    vs=dto.getVs();
		    Date dt=vs.getVeStartTime();
		    Date lastEndDt=DateUtil.addDay(dt, -1);
		    dt=format2.parse(format.format(dt));
		    lastEndDt=format2.parse(format1.format(lastEndDt));
		    String lastId=vs.getLastId();
			vs.setVeId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
			vs.setVeStartTime(dt);
			vs.setVeType(VersionTypeEnum.PRICED_BOQ.getValue());
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(dt);
			int year = cal.get(Calendar.YEAR);
			vs.setVeNo(year+"版");
			vs.setGreatTime(versionDao.getDatabaseDate());
			LoginInfo loginInfo = SessionUtil.getLoginInfo();
			vs.setGreatPerson(loginInfo.getStaffId());
			versionDao.save(vs);
			Version oldve=versionDao.get(lastId);
			if(oldve!=null){
				oldve.setVeEndTime(lastEndDt);
				versionDao.update(oldve);
			}
			
			
		}
		if(dto.getList()!=null){
			List<PricedBoq> list2 = dto.getList();
			for(PricedBoq pb:list2){
				pb.setQsId(IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT));
				pb.setVeId(vs.getVeId());
			}
			pricedBoqDao.batchInsertObjects(list2);
		}
		
	}

	
	
}
