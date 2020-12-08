package cc.dfsoft.project.biz.base.project.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.project.dao.ProjectScaleDao;
import cc.dfsoft.project.biz.base.project.dao.ScaleDetailDao;
import cc.dfsoft.project.biz.base.project.dto.ScaleDetailQueryReq;
import cc.dfsoft.project.biz.base.project.entity.ProjectScale;
import cc.dfsoft.project.biz.base.project.entity.ScaleDetail;
import cc.dfsoft.project.biz.base.project.service.ScaleDetailService;

/**
 * 工程服务接口实现
 * @author pengtt
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ScaleDetailServiceImpl implements ScaleDetailService{
	
	/**工程规模明细dao*/
	@Resource
	ScaleDetailDao scaleDetailDao;
	@Resource
	ProjectScaleDao projectScaleDao;
	
	@Override
	public Map<String, Object> queryScaleDetail(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException {
		Map<String,Object> result = new HashMap();
		String projLtypeId = scaleDetailQueryReq.getProjLtypeId();
		String[] ltypeId={};
		if(StringUtils.isNotBlank(projLtypeId)){
			ltypeId = projLtypeId.split(",");
		}
		//符合查询条件的所有工程小类的集合
		List<ScaleDetail> newList = new ArrayList();
		//获取工程小类的所有值
		
		//ProjStypeEnum[] stypes = ProjStypeEnum.values();
		List<ProjectScale> stypes=projectScaleDao.getAll();
		for(int i=0;i<stypes.size();i++){
			//若该工程小类所属大类符合查询条件
			for(int j=0;j<ltypeId.length;j++){
				if(StringUtils.isNotBlank(ltypeId[j]) && stypes.get(i).getProjType()!=null&& ltypeId[j].equals(stypes.get(i).getProjType().getProjTypeId())){
					ScaleDetail detail = new ScaleDetail();
					detail.setProjLtypeId(ltypeId[j]);
					detail.setProjStypeDes(stypes.get(i).getPsDes());
					detail.setProjStypeId(stypes.get(i).getPsId());
					newList.add(detail);
				}
			}
		}
		//若存在工程id，则进行数据库查询（查询该工程id的工程规模明细，若不存在id，则无需查库）
		if(StringUtils.isNotBlank(scaleDetailQueryReq.getProjId())){
			scaleDetailQueryReq.setProjLtypeId("");
			result = scaleDetailDao.queryScaleDetail(scaleDetailQueryReq);
			if("-1".equals(scaleDetailQueryReq.getProjId())){
				return result;
			}
			//查询出符合条件的工程规模明细
			List<ScaleDetail> datalist = (List<ScaleDetail>) result.get("data");
			for(int k=0;k<datalist.size();k++){
				ScaleDetail resultSd = datalist.get(k);
				for(int i=0;i<newList.size();i++){
					ScaleDetail sd = newList.get(i);
					if(resultSd.getProjStypeId().equals(sd.getProjStypeId())){
						//移除存在该工程规模明细的工程小类空对象
						newList.remove(i);
						//添加工程规模明细的对象
						newList.add(resultSd);
						break;
					}
				}
			}
		}
		
		result.put("data", newList);
		return result;
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batMaintain(Map<String, Object> result) {
		
		//删除工程原工程规模明细记录；
		scaleDetailDao.deleteByProjId((String)result.get("projId"));
		List<ScaleDetail> listSD = (List<ScaleDetail>) result.get("data");
		//增加工程现工程规模明细记录；
		scaleDetailDao.batchInsertObjects(listSD);
	}

	@Override
	public Map<String, Object> queryScaleDetailCommon(ScaleDetailQueryReq scaleDetailQueryReq) throws ParseException {
		
		return scaleDetailDao.queryScaleDetail(scaleDetailQueryReq);
	}

	@Override
	public List<ScaleDetail> findByprojId(String projId) {
		return scaleDetailDao.findByprojId(projId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void batchInsertObjects(List<ScaleDetail> newList) {
		scaleDetailDao.batchInsertObjects(newList);
	}
}
