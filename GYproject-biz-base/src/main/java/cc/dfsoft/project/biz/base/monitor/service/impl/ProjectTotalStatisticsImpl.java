package cc.dfsoft.project.biz.base.monitor.service.impl;


import cc.dfsoft.project.biz.base.monitor.dao.ProjectTotalStatisticsDao;
import cc.dfsoft.project.biz.base.monitor.service.ProjectTotalStatisticsService;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProjectTotalStatisticsImpl implements ProjectTotalStatisticsService{
	
	/**立项申请单信息Dao*/
	@Resource
	ProjectTotalStatisticsDao projectTotalStatisticsDao;

	/**
	 * 查找按工程类型分组的工程量统计
	 * @createTime	2016-7-9
	 * @param	projId
	 * @return	ProjectApplication 
	 */
	public Map queryProjectTotalStatisticsByType()
	{
		Map map = projectTotalStatisticsDao.queryProjectByType();
		return map;
	}
	
	/**
	 * 能流图
	 * @return
	 */
	public Map queryProjectDataForSankey(){
		Map map = projectTotalStatisticsDao.queryProjectDataForSankey();
		return map;
	}
	
	/**
	 * 收付款统计
	 * @return
	 */
	public Map queryProjectOfPayment()
	{
		Map map = projectTotalStatisticsDao.queryProjectOfPayment();
		return map;
	}

	
	/**
	 * 安全质量统计
	 * @author
	 * @createTime 2016-12-01
	 * @param
	 * @return Map
	 */
	@Override
	public Map querySafetyQualityStatistics() {
		Map map=projectTotalStatisticsDao.querySafetyQualityStatistics();
		return map;
	}
	
	/**
	 * 工程项目总体信息查询-按规模划分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryScaleAmountStatistics() {
		return projectTotalStatisticsDao.queryScaleAmountStatistics();
	}
	
	/**
	 * 工程项目总体信息查询-按区域划分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryAreaMountStatistics() {
		return projectTotalStatisticsDao.queryAreaMountStatistics();
	}
	
	/**
	 * 工程项目总体信息查询-区域划分(饼图查询用)
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String,Object>> queryAreaSumAmountStatistics() {
		return projectTotalStatisticsDao.queryAreaSumAmountStatistics();
	}

	
	/**
	 * 统计各阶段的工程汇总数量
	 * @return
	 */
	public Map queryProjectOfStage()
	{
		Map map = projectTotalStatisticsDao.queryProjectOfState();
		return map;
	}
	/**
	 * 显示选中阶段的工程明细记录
	 * @return
	 */
	public Map queryProjectOfStageOfDetail(ProjectQueryReq p)
	{
		Map map = projectTotalStatisticsDao.queryProjectOfStateDetail(p);
		return map;
	}
	
	/**
	 * 报表屏首页去年结转 本年新增
	 * @author
	 * @createTime 2017-1-1
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryLastYearAndThisYearInfo() {
		List<Map<String,Object>> list=projectTotalStatisticsDao.queryLastYearAndThisYearInfo();
		return list;
	}
	
	/**
	 * 报表屏首页 转天然气和退单
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryAlreadyFinishedSum() {
		List<Map<String,Object>> list=projectTotalStatisticsDao.queryAlreadyFinishedSum();
		return list;
	}
	
	/** 报表屏首页 在建工程
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryConstructingProject() {
		List<Map<String,Object>> list=projectTotalStatisticsDao.queryConstructingProject();
		return list;
	}
	
	/** 报表屏首页 施工处数量
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryManagementOffice() {
		List<Map<String,Object>> list=projectTotalStatisticsDao.queryManagementOffice();
		return list;
	}
	
	/**
	 * 用于近一年每月立项数目
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@Override
	public Map<String, Object> queryLastMonthsNum() {
		// 返回结果集
		Map<String, Object> result = new HashMap();
		List<Map<String, Object>> queryData = projectTotalStatisticsDao.queryLastMonthsNum();
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(projectTotalStatisticsDao.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		String[] months = new String[12];
		for (int i = 0; i < months.length; i++) {
			c.add(Calendar.MONTH, 1);
			String date = f.format(c.getTime());
			months[i] = date;
		}
		// xAxisData
		result.put("xAxisData", months);
		
		// 立项数
		Object[] acceptNum = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		
		for (int n = 0; n < months.length; n++) {
			for (Map<String, Object> qdata : queryData) {
				if (qdata.get("acceptDate").equals(months[n])) {
						acceptNum[n] = qdata.get("num");
				}
			}
		}
		result.put("collectData", acceptNum);
		
		return result;
	}
	
	
	/**
	 * 查询工程的汇总数据，按月进行分类统计
	 * @param startDate 起始日期
	 * @param endDate   终止日期
	 * @param fieldName 查询字段的名称
	 * @return
	 */
	public Map<String,LinkedList> queryProjectNumOfMonthByDate()throws Exception
	{
		//获取立项的结果集
		List<Map>listProjectOfAccept = projectTotalStatisticsDao.queryProjectNumOfMonthByDate("acceptDate");
		
		//获取签约的结果集
		List<Map>listProjectOfSign = projectTotalStatisticsDao.queryProjectNumOfMonthByDate("signDate");
		
		String[]month = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		
		//返回的立项结果集
		LinkedList listRetOfAccept = new LinkedList();
		
		//返回的签约结果集
		LinkedList listRetOfSign = new LinkedList();
		
		boolean bolFlagOfAccept = false;
		boolean bolFlagOfsign = false;
		
		for(int i = 0;i < month.length;i ++)
		{
			bolFlagOfAccept = false;
			bolFlagOfsign = false;
			
			for(int k = 0;k < listProjectOfAccept.size();k ++)
			{
				Map pro = listProjectOfAccept.get(k);
				
				if(pro.get("month").equals(month[i]))
				{
					listRetOfAccept.add(pro.get("num"));
					bolFlagOfAccept = true;
				}
			}
			
			for(int k = 0;k < listProjectOfSign.size();k ++)
			{
				Map pro = listProjectOfSign.get(k);
				if(pro.get("month").equals(month[i]))
				{
					listRetOfSign.add(pro.get("num"));
					bolFlagOfsign = true;
				}
			}
			
			if(!bolFlagOfAccept)
			{
				listRetOfAccept.add("0");
			}
			
			if(!bolFlagOfsign)
			{
				listRetOfSign.add("0");
			}
		}
		
		
		Map<String,LinkedList>mapRet = new HashMap<String,LinkedList>();
		mapRet.put("accept", listRetOfAccept);
		mapRet.put("sign", listRetOfSign);
		return mapRet;
	}
	
	/**
	 * 用于工程项目第1个grid 统计当年和去年的工程数量、金额
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryLastYearAndThisYearNum() {
		return projectTotalStatisticsDao.queryLastYearAndThisYearNum();
	}
	
	/**
	 * 用于工程项目第2个grid --统计当年的民用、车用、公用工程数量
	 * @author fuliwei
	 * @createTime 2017-01-07
	 * @return List
	 */
	@Override
	public List<Map<String, Object>> queryProjectTypeNum() {
		return projectTotalStatisticsDao.queryProjectTypeNum();
	}
	
	/**
	 * 用于工程项目第3个grid-按区域统计当年工程数量和金额
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryProjectAreaNum() {
		return projectTotalStatisticsDao.queryProjectAreaNum();
	}
	
	/**
	 * 用于工程项目第4个grid-按阶段统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryProjectStageNum() {
		return projectTotalStatisticsDao.queryProjectStageNum();
	}
	
	/**
	 * 用于工程项目第5个grid-按立项、签约统计当年工程数量
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryProjectAcceptAndContractNum() {
		return projectTotalStatisticsDao.queryProjectAcceptAndContractNum();
	}
	
	/**
	 * 用于工程项目第6个grid-按结款、未结统计
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> querySignAndAladyCharge() {
		return projectTotalStatisticsDao.querySignAndAladyCharge();
	}
	
	/**
	 * 用于工程施工 各施工处 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryConstructionNumsAndAmount() {
		List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryConstructionNumsAndAmount();
		List<Map<String, Object>> dataAmount = new ArrayList();
		Map<String, Object> result = new HashMap();
		for(int i = 0; i < queryData.size();i ++){
			Map<String, Object> mdata = new HashMap();
			//施工处名字
			String unitName=(String) queryData.get(i).get("NAME");
			mdata.put("value", queryData.get(i).get("AMOUNTVAL"));
			mdata.put("name", unitName);
			dataAmount.add(mdata);
		}
		result.put("dataAmount", dataAmount);

		List<Map<String, Object>> dataNum = new ArrayList();
		for(int i = 0; i < queryData.size();i ++){
			Map<String, Object> mdata = new HashMap();
			//施工处名字
			String unitName=(String) queryData.get(i).get("NAME");
			mdata.put("value", queryData.get(i).get("NUMVAL"));
			mdata.put("name", unitName);
			dataNum.add(mdata);
		}
		result.put("dataNum", dataNum);
		
		return result;
	}
	
	/**
	 * 用于工程施工 各分包单位 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryCuUnitNumsAndAmount() {
		//返回结果集
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryCuUnitNumsAndAmount();
		//处理各分包单位数量
		List<Map<String,Object>> dataNum=new ArrayList();
		for(int i = 0;i < queryData.size();i ++){
			Map<String, Object> mdata =new HashMap();
			String cuName=(String) queryData.get(i).get("name");
			mdata.put("name", cuName);
			mdata.put("value", queryData.get(i).get("numVal"));
			dataNum.add(mdata);
		}
		result.put("dataNum", dataNum);
		
		//处理各分包单位金额
		List<Map<String,Object>> dataAmount=new ArrayList();
		for(int i = 0;i < queryData.size();i ++){
			Map<String, Object> mdata =new HashMap();
			String cuName=(String) queryData.get(i).get("name");
			mdata.put("name", cuName);
			mdata.put("value", queryData.get(i).get("amountVal"));
			dataAmount.add(mdata);
		}
		result.put("dataAmount", dataAmount);
		return result;
	}

    /**
     * 用于工程施工 各分包单位 数量、金额统计
     * @author fuliwei
     * @createTime 2017-1-10
     * @param
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> queryCuUnitNumsAndAmountRight() {
        //返回结果集
        Map<String, Object> result = new HashMap();

        List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryConstructionNumsAndAmountRight();
        //处理各分包单位数量
        List<Map<String,Object>> dataNum=new ArrayList();
        for(int i = 0;i < queryData.size();i ++){
            Map<String, Object> mdata =new HashMap();
            String cuName=(String) queryData.get(i).get("name");
            mdata.put("name", cuName);
            mdata.put("value", queryData.get(i).get("numVal"));
            dataNum.add(mdata);
        }
        result.put("dataNum", dataNum);

        //处理各分包单位金额
        List<Map<String,Object>> dataAmount=new ArrayList();
        for(int i = 0;i < queryData.size();i ++){
            Map<String, Object> mdata =new HashMap();
            String cuName=(String) queryData.get(i).get("name");
            mdata.put("name", cuName);
            mdata.put("value", null!=queryData.get(i).get("amountVal") ?queryData.get(i).get("amountVal"):"0");
            dataAmount.add(mdata);
        }
        result.put("dataAmount", dataAmount);
        return result;

    }


    /**
	 * 用于工程施工 各施工处扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryContructionUnitSafetyNums() {
		//返回结果集
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryContructionUnitSafetyNums();
		//处理各施工处扣分详情
		List<Map<String,Object>> dataNum=new ArrayList();
		
		for(int i=0;i<queryData.size();i++){
			Map<String, Object> map=new HashMap();
			String name=(String) queryData.get(i).get("NAME");
			map.put("name", name);
			map.put("value",queryData.get(i).get("VAL"));
			dataNum.add(map);
		}
		result.put("dataNum", dataNum);
		return result;
		
	}
	
	/**
	 * 用于工程施工 各分包单位扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryCuUnitSafetyNums() {
		//返回结果集
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryCuUnitSafetyNums();
		//处理各分包单位扣分详情
		List<Map<String,Object>> dataNum=new ArrayList();
		
		for(int i=0;i<queryData.size();i++){
			Map<String, Object> map=new HashMap();
			String name=(String) queryData.get(i).get("NAME");
			map.put("name", name);
			map.put("value",queryData.get(i).get("VAL"));
			dataNum.add(map);
		}
		result.put("dataNum", dataNum);
		return result;
	}
	
	/**
	 * 用于工程施工 各施工处按工程规模统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryContructionUnitScaleNums() {
		//返回结果集
		Map<String, Object> result =new HashMap();;
		
		List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryContructionUnitScaleNums();
		//处理各施工处
		List<Map<String,Object>> dataNum=null;
		//民用
		String [] scaleCivilArray={"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};
		for(int i=0;i<queryData.size();i++){
			//Map<String, Object> map=new HashMap();
			List<Map<String, Object>> listCivil=(List<Map<String, Object>>) queryData.get(i).get(scaleCivilArray[i]);
			
			dataNum=new ArrayList();
			if(listCivil!=null && listCivil.size()>0){
				for(int j=0;j<listCivil.size();j++){
					Map<String, Object> map=new HashMap();
					String name=(String) listCivil.get(j).get("NAME");
					map.put("name", name);
					map.put("value",listCivil.get(j).get("VAL"));
					dataNum.add(map);
				}
				result.put("civildataNum"+i, dataNum);
			}
			
		}
		//公服
		String [] scalePulicArray={"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};
		
		List<Map<String, Object>> queryPblicData=projectTotalStatisticsDao.queryContructionUnitScalePublicNums();
		for(int i=0;i<queryPblicData.size();i++){
			//Map<String, Object> map=new HashMap();
			List<Map<String, Object>> listPublic=(List<Map<String, Object>>) queryPblicData.get(i).get(scalePulicArray[i]);
			dataNum=new ArrayList<Map<String, Object>>();
			if(listPublic!=null && listPublic.size()>0){
				for(int j=0;j<listPublic.size();j++){
					Map<String, Object> map=new HashMap<String, Object>();
					String name=(String) listPublic.get(j).get("NAME");
					map.put("name", name);
					map.put("value",listPublic.get(j).get("VAL"));
					dataNum.add(map);
				}
				result.put("publicDataNum"+i, dataNum);
			}
			
		}
		
		
		
		return result;
	}
	
	/**
	 * 用于工程施工 各分包单位按工程规模统计
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> queryCuUnitScaleNums() {
		//返回结果集
				Map<String, Object> result =new HashMap();;
				
				List<Map<String, Object>> queryData=projectTotalStatisticsDao.queryCuUnitScaleNums();
				//处理各施工处
				List<Map<String,Object>> dataNum=null;
				//民用
				String [] scaleCivilArray={"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};
				for(int i=0;i<queryData.size();i++){
					//Map<String, Object> map=new HashMap();
					List<Map<String, Object>> listCivil=(List<Map<String, Object>>) queryData.get(i).get(scaleCivilArray[i]);
					
					dataNum=new ArrayList();
					if(listCivil!=null && listCivil.size()>0){
						for(int j=0;j<listCivil.size();j++){
							Map<String, Object> map=new HashMap();
							String name=(String) listCivil.get(j).get("NAME");
							map.put("name", name);
							map.put("value",listCivil.get(j).get("VAL"));
							dataNum.add(map);
						}
						result.put("civildataNum"+i, dataNum);
					}
					
				}
				//公服
				String [] scalePulicArray={"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};
				
				List<Map<String, Object>> queryPblicData=projectTotalStatisticsDao.queryCuUnitScalePublicNums();
				for(int i=0;i<queryPblicData.size();i++){
					//Map<String, Object> map=new HashMap();
					List<Map<String, Object>> listPublic=(List<Map<String, Object>>) queryPblicData.get(i).get(scalePulicArray[i]);
					dataNum=new ArrayList<Map<String, Object>>();
					if(listPublic!=null && listPublic.size()>0){
						for(int j=0;j<listPublic.size();j++){
							Map<String, Object> map=new HashMap<String, Object>();
							String name=(String) listPublic.get(j).get("NAME");
							map.put("name", name);
							map.put("value",listPublic.get(j).get("VAL"));
							dataNum.add(map);
						}
						result.put("publicDataNum"+i, dataNum);
					}
					
				}
				return result;
	}
	
	/**
	 * 用于近一年每月施工处扣分数
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@Override
	public Map<String, Object> queryContructionUnitScore() {
		// 返回结果集
		Map<String, Object> result = new HashMap();
		
		List<Map<String, Object>> queryData = projectTotalStatisticsDao.queryContructionUnitScore();
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.setTime(projectTotalStatisticsDao.getDatabaseDate());
		c.add(Calendar.YEAR, -1);
		String[] months = new String[12];
		for (int i = 0; i < months.length; i++) {
			c.add(Calendar.MONTH, 1);
			String date = f.format(c.getTime());
			months[i] = date;
		}
		// xAxisData
		result.put("xAxisData", months);
		
		
		
		String [] constructionArray={"施工管理一处","施工管理二处","施工管理三处","施工管理四处","施工管理五处"};
		
		// 
		
		//处理各施工处
		List<Map<String,Object>> dataNum=null;
		
		for(int j=0;j<queryData.size();j++){
				Map mapp=queryData.get(j);
			for(int i=0;i<constructionArray.length;i++){
				Object[] acceptNum = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
				String unitName=constructionArray[i];
				List<Map<String, Object>> listPublic=(List<Map<String, Object>>) mapp.get(unitName);
				dataNum=new ArrayList<Map<String, Object>>();
				if(listPublic!=null && listPublic.size()>0){
					for (int n = 0; n < months.length; n++) {
						for (Map<String, Object> qdata : listPublic) {
							if (qdata.get("CHECK_DATE").equals(months[n])) {
									acceptNum[n] = qdata.get("SCORE");
							}
						}
					}
					result.put("constructionNum"+i, acceptNum);
				}
			}
		}
		return result;
	}
	
	/**
	 * 用于当年工程进度
	 * @author fuliwei
	 * @createTime 2017-01-11
	 * @return
	 */
	@Override
	public Map<String, Object> queryProjectSchedule() {
		List<Map<String, Object>> quertData= projectTotalStatisticsDao.queryProjectSchedule();
        Map schedule = new HashMap();
		Map initdata = new HashMap();
		String[] xdata = { "A", "B", "C", "D", "E", "F" };
		String[] ydata = { "居民户工程", "公建户工程", "改管工程", "干线工程", "智能表工程", "场站工程" };
		for (String d : xdata) {
			Object[] sdata = { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
			initdata.put(d, sdata);
		}
		if (quertData != null && quertData.size() > 0) {
			for (Map m : quertData) {
				for (int j = 0; j < ydata.length; j++) {
					if (m.get("name").equals(ydata[j])) {
						if (initdata.get("" + m.get("progress")) != null) {
							((Object[]) initdata.get("" + m.get("progress")))[j] = m.get("num");
						}
					}
				}
			}
		}
        schedule.put("initdata",initdata);
        schedule.put("departments",ydata);
        return schedule;
	}
}
