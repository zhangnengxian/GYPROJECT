package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.FestivalDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.FestivalReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Festival;
import cc.dfsoft.project.biz.base.baseinfo.enums.FestivalTypeEnum;
import cc.dfsoft.project.biz.base.baseinfo.enums.IsValidEnum;
import cc.dfsoft.project.biz.base.baseinfo.service.FestivalService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.FestivalUtil;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;
/**
 * 节假日、补班service实现
 * @author liaoyq
 * @createTime 2018年5月7日
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class FestivalServiceImpl implements FestivalService,InitializingBean {
	//Map缓存节假日、补班日
	public static Map<String, List<Date>> festivalCashMap = new HashMap<String, List<Date>>();
	
	@Resource
	FestivalDao festivalDao;
	
	/**
	 * 查询节假日列表
	 */
	@Override
	public Map<String, Object> queryList(FestivalReq festivalReq) {
		return festivalDao.queryList(festivalReq);
	}

	/**
	 * 保存更新节假日信息
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveFestival(Festival festival) {
		if(StringUtil.isBlank(festival.getId())){
			festival.setId(IDUtil.getUniqueId(Constants.STANDARD));
			festival.setIsDel(IsValidEnum.UN_USED.getValue());//未删除
		}
		festival.setOperateTime(festivalDao.getDatabaseDate());
		LoginInfo loginInfo = SessionUtil.getLoginInfo();
		festival.setOperator(loginInfo.getStaffId());
		festivalDao.saveOrUpdate(festival);
		this.afterPropertiesSet();
		return festival.getId();
	}

	/**
	 * 根据id查询节假日信息
	 */
	@Override
	public Festival findById(String id) {
		return festivalDao.get(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delFestival(String id) {
		festivalDao.delete(id);
		this.afterPropertiesSet();
	}

	/**
	 * 查询出所有节假日中的日期列表
	 */
	@Override
	public List<Date> queryHolidays() {
		List<Date> holidays = new ArrayList<Date>();
		FestivalReq festivalReq = new FestivalReq();
		festivalReq.setDayType(FestivalTypeEnum.HOLIDAY.getValue());
		List<Festival> list = festivalDao.queryFestivals(festivalReq);
		if(list!=null && list.size()>0){
			//遍历节假日设置
			for(Festival festival : list){
				//获取每个节假日中的日期
				holidays.addAll(FestivalUtil.displayDate(festival.getFestivalStartDate(), festival.getFestivalEndDate()));
			}
		}
		//System.err.println("查询出所有节假日中的日期列表");
		return holidays;
	}

	/**
	 * 查询所有补班日列表
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @param dayType
	 * @return
	 */
	@Override
	public List<Date> queryWorkdays() {
		List<Date> workdays = new ArrayList<Date>();
		FestivalReq festivalReq = new FestivalReq();
		festivalReq.setDayType(FestivalTypeEnum.SUP_CLASS.getValue());
		List<Festival> list = festivalDao.queryFestivals(festivalReq);
		if(list!=null && list.size()>0){
			//遍历节假日设置
			for(Festival festival : list){
				//获取每个补班日中的日期
				workdays.addAll(FestivalUtil.displayDate(festival.getFestivalStartDate(), festival.getFestivalEndDate()));
			}
		}
		//System.err.println("查询所有补班日列表");
		return workdays;
	}

	/**
	 * 初始化缓存数据
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() {
		try {
			//初始化数据
			List<Date> workdays = this.queryWorkdays();
			List<Date> holidays = this.queryHolidays();
			if(workdays!=null && workdays.size()>0){
				festivalCashMap.put(Constants.WORKDAYS, workdays);
			}
			if(holidays!=null && holidays.size()>0){
				festivalCashMap.put(Constants.HOLIDAYS, holidays);
			}
		} catch (Exception e) {
		}
		
	}
	/**
	 * 清除缓存数据
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 */
	@Override
	public void clearCacheMap(){
		festivalCashMap.clear();
	}
	
	/**
	 * 根据key值获取数据
	 * 若当前数据不存在，重新初始化缓存
	 * @author liaoyq
	 * @createTime 2018年5月10日
	 * @param key
	 * @return
	 * @throws Exception 
	 */
	@Override
	public List<Date> getCacheMap(String key){
		if(festivalCashMap.get(key)==null ||festivalCashMap.get(key).size()<=0){
			this.afterPropertiesSet();
		}
		return festivalCashMap.get(key);
	}
}
