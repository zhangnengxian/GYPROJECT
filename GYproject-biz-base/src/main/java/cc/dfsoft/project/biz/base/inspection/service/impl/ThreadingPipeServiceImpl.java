package cc.dfsoft.project.biz.base.inspection.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.inspection.dao.BasicProjectCheckItemDao;
import cc.dfsoft.project.biz.base.inspection.dao.BasicProjectContentDao;
import cc.dfsoft.project.biz.base.inspection.dao.GuaranteeItemsDao;
import cc.dfsoft.project.biz.base.inspection.dao.GuaranteeItemsListDao;
import cc.dfsoft.project.biz.base.inspection.dto.ThreadingPipeReq;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectCheckItem;
import cc.dfsoft.project.biz.base.inspection.entity.BasicProjectContent;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItems;
import cc.dfsoft.project.biz.base.inspection.entity.GuaranteeItemsList;
import cc.dfsoft.project.biz.base.inspection.service.ThreadingPipeService;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
/**
 * 管内穿线
 * @author fuliwei
 *
 */
@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ThreadingPipeServiceImpl  implements ThreadingPipeService{
	
	/**保证项目Dao*/
	@Resource
	GuaranteeItemsListDao guaranteeItemsListDao;
	
	/**保证项目初始值Dao*/
	@Resource
	GuaranteeItemsDao guaranteeItemsDao;
	
	/**基本项目Dao*/
	@Resource
	BasicProjectCheckItemDao basicProjectCheckItemDao;
	
	/**基本项目初始值Dao*/
	@Resource
	BasicProjectContentDao basicProjectContentDao;
	
	/**
	 * 管内穿线-保证项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryGuaranteeItemsList(ThreadingPipeReq req) {
		
		//判断是否有值
		Map<String, Object> map=guaranteeItemsListDao.queryGuaranteeItemsList(req);
		List<GuaranteeItemsList> list=(List) map.get("data");
		if(list != null && list.size()>0){
			return map;
		}else{
			//没有的话初始化值
			Map<String, Object> guaranteeItemsMap=guaranteeItemsDao.queryGuaranteeItems(req);
			List<GuaranteeItems> guaranteeItemsList=(List) guaranteeItemsMap.get("data");
			List<GuaranteeItemsList> listResult=new ArrayList<GuaranteeItemsList>();
			GuaranteeItemsList gil=null;
			for(int i = 0;i < guaranteeItemsList.size();i ++){
				gil=new GuaranteeItemsList();
				gil.setGiId(guaranteeItemsList.get(i).getGiId());//保证项目id
				gil.setGiDes(guaranteeItemsList.get(i).getGiDes());//保证项目描述
				gil.setTbType(req.getItemType());//类型 1管内穿线 2低压安装
				listResult.add(gil);
			}
			Map<String, Object> mapResult=new HashMap<String, Object>();
			mapResult.put("data", listResult);
			return mapResult;
		}
	}
	
	/**
	 * 管内穿线-基本项目列表查询
	 * @author fuliwei
	 * @createTime 2017-2-7
	 * @param ThreadingPipeReq 查询条件dto
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryThreadingPipeBasic(ThreadingPipeReq req) {
		//判断是否有值
		Map<String, Object> map=basicProjectCheckItemDao.queryBasicProjectCheckItem(req);
		List<BasicProjectCheckItem> list=(List) map.get("data");
		if(list != null && list.size()>0){
			return map;
		}else{
			//没有的话初始化值
			Map<String, Object> basicProjectContentMap=basicProjectContentDao.queryBasicProjectContent(req);
			List<BasicProjectContent> basicProjectContentList=(List) basicProjectContentMap.get("data");
			BasicProjectCheckItem bpc=null;
			List<BasicProjectCheckItem> listResult=new ArrayList<BasicProjectCheckItem>();
			for(int i = 0;i < basicProjectContentList.size();i ++){
				bpc=new BasicProjectCheckItem();
				bpc.setBpcId(basicProjectContentList.get(i).getBpcId());    //基本项目检查内容主键id
				bpc.setItem(basicProjectContentList.get(i).getItem());		//项目内容
				bpc.setItemType(req.getItemType());							//类型
				listResult.add(bpc);
			}
			Map<String, Object> mapResult=new HashMap<String, Object>();
			mapResult.put("data", listResult);
			return mapResult;
		}
	}
	
	/**
	 * 保存记录页面数据
	 * @author fuliwei
	 * @createTime 2017年2月8日
	 * @param req
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveThreadingPipeRecord(ThreadingPipeReq req) {
		//保证项目
		List<GuaranteeItemsList> firstList=req.getFirstList();
		//删除原来的保证项目
		
		if(firstList!=null && firstList.size()>0){
			guaranteeItemsListDao.deleteById(req.getPcId(),firstList.get(0).getTbType());
			for(int i = 0;i<firstList.size();i++){
				firstList.get(i).setGilId(IDUtil.getUniqueId(Constants.THREADING_PIPE));
			}
			guaranteeItemsListDao.batchInsertObjects(firstList);
		}
		
		//基本项目
		List<BasicProjectCheckItem> secondList=req.getSecondList();
		
		if(secondList!=null && secondList.size()>0){
			basicProjectCheckItemDao.deleteById(req.getPcId(), secondList.get(0).getItemType());
			for(int i = 0;i<secondList.size();i++){
				secondList.get(i).setBpciId(IDUtil.getUniqueId(Constants.THREADING_PIPE));
			}
			basicProjectCheckItemDao.batchInsertObjects(secondList);
		}
		
		//允许偏差项目
		List<BasicProjectCheckItem> thirdList=req.getThirdList();
		
		if(thirdList!=null && thirdList.size()>0){
			basicProjectCheckItemDao.deleteById(req.getPcId(), thirdList.get(0).getItemType());
			for(int i = 0;i<thirdList.size();i++){
				thirdList.get(i).setBpciId(IDUtil.getUniqueId(Constants.THREADING_PIPE));
			}
			basicProjectCheckItemDao.batchInsertObjects(thirdList);
		}
		
	}

}
