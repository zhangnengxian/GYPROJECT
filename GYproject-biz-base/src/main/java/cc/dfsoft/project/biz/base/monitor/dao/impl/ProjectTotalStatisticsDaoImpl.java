package cc.dfsoft.project.biz.base.monitor.dao.impl;

import cc.dfsoft.project.biz.base.monitor.dao.ProjectTotalStatisticsDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectTypeDao;
import cc.dfsoft.project.biz.base.project.dto.ProjectQueryReq;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.project.enums.AreaEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjLtypeEnum;
import cc.dfsoft.project.biz.base.project.enums.ProjStatusEnum;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class ProjectTotalStatisticsDaoImpl extends NewBaseDAO<Project, String> implements ProjectTotalStatisticsDao{

	@Resource
	ProjectTypeDao projectTypeDao;

	//测试标志，如果为1，表示在测试，如果为0，表示是正式环境，通过修改这个标志，就可以将一些测试数据屏蔽掉；
	private int isTest = 0;
	/**
     * 查询实时的工程总量统计数据(按照区域和规模），统计对象为本年新签的合同
     * @return
     */
	public Map queryProjectByType(){
		//获取当前日期
		Date currentDate = this.getDatabaseDate();

		//规模的分布数组
		String[][]arrayScale = {{"2万以下","0","20000"},{"2万至5万","20000","50000"},{"5万至20万","50000","200000"},{"20万至50万","200000","500000"},{"50万至200万","500000","2000000"},{"200万以上","2000000","90000000"}};

		//规模结果集
		Map<String,String> mapRetOfScale;

		List<Map> listRetOfScale = new ArrayList();

		//区域结果集
		Map<String,String> mapRetOfArea = new LinkedHashMap<String,String>();

		List<Map> listRetOfArea = new ArrayList();

		//标题结果集
		List<String> listRetOfTitle = new ArrayList<String>();

		//考虑到标题的排序，做成两个结果集，最后再进行合并
		List<String> listRetOfTitleSub = new ArrayList<String>();

		Map mapRet = new HashMap();

		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////


		try{
			//查询当前新签合同的数量（按大类和区域进行分类统计）
			StringBuilder strBuild = new StringBuilder();
			strBuild.append("select contribution_mode_des,scale,sum(num) from (");

			for(int i = 0;i < arrayScale.length;i ++){
				if(i > 0)strBuild.append(" union ");
				//updateto_date
				//strBuild.append("(select '").append(arrayScale[i][0]).append("' scale,").append(i).append(" indexStr,p.contribution_mode_des,count(*) num from Project p where p.SIGN_DATE >= to_date('").append(simple.format(currentDate)).append("-01-01 00:00:00','yyyy-MM-dd hh24:mi:ss') ");
				strBuild.append("(select '").append(arrayScale[i][0]).append("' scale,").append(i).append(" indexStr,p.contribution_mode_des,count(*) num from Project p where p.SIGN_DATE >=").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
				strBuild.append(" and p.contract_amount > ").append(arrayScale[i][1]).append(" and p.contract_amount <= ").append(arrayScale[i][2]);
				strBuild.append(" group by p.contribution_mode_des)");
			}

			strBuild.append(") S group by contribution_mode_des,scale,indexStr order by contribution_mode_des,indexStr ");

			List<Object[]>listProjectNew = this.findBySql(strBuild.toString());
			if(listProjectNew == null)listProjectNew = new ArrayList();
			String strTemp = "";

			for(Object[]obj:listProjectNew){
				//获取title数据
				if(!listRetOfTitle.contains((String)obj[0])){
					listRetOfTitle.add((String)obj[0]);
				}

				if(!listRetOfTitleSub.contains((String)obj[1])){
					listRetOfTitleSub.add((String)obj[1]);
				}

				//组合区域结果集数据
				if(mapRetOfArea.get((String)obj[0]) != null){
					strTemp = (String)mapRetOfArea.get((String)obj[0]);
					if(strTemp == null || strTemp.equals(""))strTemp = "0";
					mapRetOfArea.put((String)obj[0],String.valueOf(Integer.parseInt(strTemp) + ((BigDecimal)obj[2]).intValue()));
				}else{
					mapRetOfArea.put((String)obj[0],String.valueOf(obj[2]));
				}

				//组合工程规模结果集数据
				mapRetOfScale = new HashMap();
				mapRetOfScale.put("name",(String)obj[1]);
				mapRetOfScale.put("value",String.valueOf(obj[2]));
				listRetOfScale.add(mapRetOfScale);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}

		//汇总区域数据
		Iterator iterator = mapRetOfArea.keySet().iterator();
		while(iterator.hasNext()){
			Map map = new HashMap();
			String strTemp = (String)iterator.next();
			map.put("name",strTemp);
			map.put("value",mapRetOfArea.get(strTemp));
			listRetOfArea.add(map);
		}

		//listRetOfTitle.addAll(listRetOfTitleSub);

		listRetOfTitleSub.addAll(listRetOfTitle);
		mapRet.put("scale", listRetOfScale);
		mapRet.put("area", listRetOfArea);
		mapRet.put("title", listRetOfTitleSub);
		return mapRet;
	}
	
	/**
     * 查询能流图数据(工程类型-区域-工程规模）
     * @return
     */
	public Map queryProjectDataForSankey(){
		//获取工程类型与规模类型的结果集
		List listTypeToArea = queryProjectOfTypeToScaleType();
		//获取规模类型与工程规模的结果集
		List listAreaToScale = queryProjectOfScaleTypeToScale();
		List listRet = new ArrayList();
		List listRetSub = new LinkedList();
		
		//临时变量，用于保存记录的名称，防止重复添加
		List listTemp = new ArrayList();
		
		//对结果集进行整合，获取需要的数据集合
		for(int i = 0;i < listTypeToArea.size();i ++){
			Object[]obj = (Object[])listTypeToArea.get(i);
			//类型
			String strType = (String)obj[0];
			//规模类型
			String strArea = (String)obj[1];
			//数量
			String bigNum = obj[2].toString();
			Map mapTemp = new HashMap();
			mapTemp.put("source",strType);
			mapTemp.put("target",strArea);
			mapTemp.put("value",bigNum);
			
			listRetSub.add(mapTemp);
			
			if(!listTemp.contains(strType))
			{
				mapTemp = new HashMap();
				mapTemp.put("name",strType);
				listRet.add(mapTemp);
				
				listTemp.add(strType);
			}
			
			for(int j = 0;j < listAreaToScale.size();j ++)
			{
				Object[] objSub = (Object[])listAreaToScale.get(j);
				
				//规模类型
				String strSubArea = (String)objSub[0];
				
				//工程规模
				String strScale = (String)objSub[1];
				
				//数量
				BigDecimal bigSubNum = (BigDecimal)objSub[2];
				
				if(strSubArea.equals(strArea))
				{
					if(!listTemp.contains(strSubArea + "-" + strScale))
					{
						mapTemp = new HashMap();
						mapTemp.put("source",strSubArea);
						mapTemp.put("target",strScale);
						mapTemp.put("value", bigSubNum);
						listRetSub.add(mapTemp);
						
						listTemp.add(strSubArea + "-" + strScale);
					}
					
					if(!listTemp.contains(strSubArea))
					{
						mapTemp = new HashMap();
						mapTemp.put("name",strSubArea);
						listRet.add(mapTemp);
						
						listTemp.add(strSubArea);
					}
					
					if(!listTemp.contains(strScale))
					{
						mapTemp = new HashMap();
						mapTemp.put("name",strScale);
						listRet.add(mapTemp);
						
						listTemp.add(strScale);
					}
				}
			}
		}
		
		Map mapRet = new HashMap();
		mapRet.put("nodes", listRet);
		mapRet.put("links", listRetSub);
		return mapRet;
	}
	
	/**
	 * 查询区域与工程规模之间的关联关系,针对于当年新签合同数量
	 * @return
	 */
	private List queryProjectOfAreaToScale()
	{
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");
		//规模的分布数组
		String[][]arrayScale = {{"2万以下","0","20000"},{"2万至5万","20000","50000"},{"5万至20万","50000","200000"},{"20万至50万","200000","500000"},{"50万至200万","500000","2000000"},{"200万以上","2000000","90000000"}};

		//查询当前新签合同的数量（按大类和区域进行分类统计）
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select area,scale,sum(num) from (");

		for(int i = 0;i < arrayScale.length;i ++)
		{
			if(i > 0)strBuild.append(" union ");
			strBuild.append("(select '").append(arrayScale[i][0]).append("' scale,").append(i).append(" indexStr,p.area,count(*) num from Project p where p.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			strBuild.append(" and p.contract_amount > ").append(arrayScale[i][1]).append(" and p.contract_amount <= ").append(arrayScale[i][2]);
			strBuild.append(" group by p.area)");
		}

		strBuild.append(") group by area,scale,indexStr order by area,indexStr ");

		List<Object[]>listProjectNew = this.findBySql(strBuild.toString());
		if(listProjectNew == null)listProjectNew = new ArrayList();

		return listProjectNew;
	}
	
	/**
	 * 查询工程规模细类与工程规模之间的关联关系,针对于当年新签合同数量
	 * @return
	 */
	private List queryProjectOfScaleTypeToScale(){
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		//规模的分布数组
		String[][]arrayScale = {{"2万以下","0","20000"},{"2万至5万","20000","50000"},{"5万至20万","50000","200000"},{"20万至50万","200000","500000"},{"50万至200万","500000","2000000"},{"200万以上","2000000","90000000"}};

		//查询当前新签合同的数量（按大类和区域进行分类统计）
		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select proj_stype_des,scale,sum(num) from (");

		for(int i = 0;i < arrayScale.length;i ++){
			if(i > 0)strBuild.append(" union ");
			strBuild.append("(select '").append(arrayScale[i][0]).append("' scale,").append(i).append(" indexStr,A.proj_stype_des,count(*) num from Project p ");
			//oracle
			//strBuild.append("inner join (select sd.proj_id,sd.proj_stype_des, row_number() over(partition by sd.proj_id order by sd.scale_id) num from scale_detail sd) A ");
			//mysql
			strBuild.append("inner join (select heyf_tmp.proj_stype_des,if(@pdept=heyf_tmp.proj_id,@rank\\:=@rank+1,@rank\\:=1) as num,@pdept\\:=heyf_tmp.proj_id AS proj_id from "
					+ "(select sd.proj_id,sd.scale_id ,sd.proj_stype_des from scale_detail sd order by proj_id asc ,scale_id desc ) heyf_tmp ,(select @rownum\\:=0,@pdept\\:=null,@rank\\:=0) rn ) A ");
			strBuild.append("on p.proj_id = A.proj_id and A.num = 1 ");
			strBuild.append("where p.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			strBuild.append(" and p.contract_amount > ").append(arrayScale[i][1]).append(" and p.contract_amount <= ").append(arrayScale[i][2]);
			strBuild.append(" group by proj_stype_des)");
		}

		strBuild.append(") S group by proj_stype_des,scale,indexStr order by proj_stype_des,indexStr ");

		List<Object[]> listProjectNew = this.findBySql(strBuild.toString());
		if(listProjectNew == null)listProjectNew = new ArrayList();

		return listProjectNew;
	}
	
	/**
	 * 查询当年新签合同数量（工程类型与区域之间的关系）
	 * @return
	 */
	private List queryProjectOfTypeToArea()
	{
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end type,area,count(*) num ");
		strBuild.append("from project p ");
		strBuild.append("where p.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		strBuild.append(" group by case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end,area ");
		strBuild.append("order by case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end,area ");

		List<Object[]>listProject = this.findBySql(strBuild.toString());
		if(listProject == null)listProject = new ArrayList();

		return listProject;
	}
	
	/**
	 * 查询当年新签合同数量（工程类型与工程规模类型之间的关系）
	 * @return
	 */
	private List queryProjectOfTypeToScaleType(){
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");
		
		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		StringBuilder strBuild1 = new StringBuilder();
		strBuild1.append("select project_type_des,proj_stype_des,count(*) num ");
		strBuild1.append("from project p ");
		//oracle
		//strBuild.append("inner join (select sd.proj_id,sd.proj_stype_des, row_number() over(partition by sd.proj_id order by sd.scale_id) num from scale_detail sd) A ");
		//mysql
		strBuild1.append("inner join (select heyf_tmp.proj_stype_des,if(@pdept=heyf_tmp.proj_id,@rank\\:=@rank+1,@rank\\:=1) as num,@pdept\\:=heyf_tmp.proj_id AS proj_id from "
				+ "(select sd.proj_id,sd.scale_id ,sd.proj_stype_des from scale_detail sd order by proj_id asc ,scale_id desc ) heyf_tmp ,(select @rownum\\:=0,@pdept\\:=null,@rank\\:=0) rn ) A ");
		strBuild1.append("on p.proj_id = A.proj_id and A.num = 1 ");
		strBuild1.append("where p.SIGN_DATE >= ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		strBuild1.append(" group by ").append("project_type_des,proj_stype_des ");
		strBuild1.append("order by ").append("project_type_des,proj_stype_des ");

		List<Object[]> listProject = this.findBySql(strBuild1.toString());
		if(listProject == null)listProject = new ArrayList();		
		
		return listProject;
	}
	
	/**
     * 查询实际收付款的统计数据
     * @return
     */
	public Map queryProjectOfPayment(){
		String[] arrayMonth = {"01","02","03","04","05","06","07","08","09","10","11","12"};

		//获取实收实付的统计数据明细
		List listRet = queryProjectOfPaymentDetail();

		//获取累计数据明细
		List listRetSum = queryProjectOfSumPaymentDetail();

		//收款结果集
		List listReceive = new LinkedList();

		//付款结果集
		List listPayment = new LinkedList();

		//累计金额结果集
		List listSumReceive = new LinkedList();
		List listSumPayment = new LinkedList();

		//付款标志
		boolean bolPayment = false;

		//收款标志
		boolean bolReceive = false;

		boolean bolPaymentSum = false;
		boolean bolReceiveSum = false;

		for(int i = 0;i < arrayMonth.length;i ++){
			String strMonth = arrayMonth[i];

			//收付款标志初始化
			bolPayment = false;
			bolReceive = false;
			bolPaymentSum = false;
			bolReceiveSum = false;

			for(int j = 0;j < listRet.size();j ++){
				Object[]obj = (Object[])listRet.get(j);

				//收款
				if(((BigDecimal)obj[1]).intValue() == 1){
					if(((String)obj[0]).substring(5,7).equals(strMonth)){
						bolReceive = true;

						listReceive.add(((BigDecimal)obj[2]).setScale(2));
					}
				}else{//付款
					if(((String)obj[0]).substring(5,7).equals(strMonth)){
						bolPayment = true;

						listPayment.add(((BigDecimal)obj[2]).setScale(2));
					}
				}
			}

			if(!bolPayment){
				listPayment.add("0.00");
			}

			if(!bolReceive){
				listReceive.add("0.00");
			}

			//对累计金额的数据进行处理
			for(int j = 0;j < listRetSum.size();j ++){
				Object[]obj = (Object[])listRetSum.get(j);

				//收款
				if(((BigDecimal)obj[1]).intValue() == 1){
					if(((String)obj[0]).equals(strMonth)){
						bolReceiveSum = true;

						listSumReceive.add(((BigDecimal)obj[2]).setScale(2));
					}
				}
				//付款
				else{
					if(((String)obj[0]).equals(strMonth)){
						bolPaymentSum = true;

						listSumPayment.add(((BigDecimal)obj[2]).setScale(2));
					}
				}
			}

			if(!bolPaymentSum){
				listSumPayment.add("0.00");
			}

			if(!bolReceiveSum){
				listSumReceive.add("0.00");
			}

		}

		Map mapRet = new HashMap();
		mapRet.put("receive", listReceive);
		mapRet.put("payement", listPayment);
		mapRet.put("payementSum", listSumPayment);
		mapRet.put("receiveSum", listSumReceive);

		return mapRet;
	}

	/**
	 * 查询实收实付的统计数据明细
	 * @return
	 */
	private List queryProjectOfPaymentDetail(){
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		StringBuilder strBuild = new StringBuilder();

		strBuild.append("select ").append(mysqlSqlConveter.dataOperateYearAndMonth("cf_date")).append(",cf_flag,sum(cf_amount) from CASH_FLOW cf ");
		strBuild.append("where cf_date > ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		strBuild.append(" and cf_date < ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-12-31 23:59:59"));
		strBuild.append(" group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("cf_date")).append(",cf_flag ");
		strBuild.append("order by cf_flag,").append(mysqlSqlConveter.dataOperateYearAndMonth("cf_date")).append(" ");

		List listRet = this.findBySql(strBuild.toString());
		return listRet;
	}

	/**
	 * 查询实收实付的累计统计数据明细
	 * @return
	 */
	private List queryProjectOfSumPaymentDetail()
	{
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		String[]strMonth = {"01","02","03","04","05","06","07","08","09","10","11","12","13"};

		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select monthStr,cf_flag,sum(sum) from (");

		for(int i = 0;i < strMonth.length;i ++)
		{
			//月份列表中显示01的作用就是为了在sql语句中拼接前一个月份
			if(strMonth[i].equals("01"))continue;

			if(i > 1)strBuild.append(" union ");
			strBuild.append("select '").append(strMonth[i-1]).append("' monthStr,cf_flag,sum(cf_amount) sum from CASH_FLOW cf ");
			strBuild.append("where cf_date > ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00")).append(" ");

			if(strMonth[i].equals("13"))
			{
				//strBuild.append("and cf_date < to_date('").append(simple.format(currentDate)).append("-12-31 23:59:59','yyyy-MM-dd hh24;mi:ss') ");
				strBuild.append("and cf_date < ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-12-31 23:59:59")).append(" ");
			}
			else
			{
				strBuild.append("and cf_date < ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-"+strMonth[i]+"-01 00:00:00")).append(" ");
			}
			strBuild.append("group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("cf_date")).append(",cf_flag ");

		}

		strBuild.append(") A group by monthStr,cf_flag ");

		strBuild.append("order by cf_flag,monthStr ");

		List listRet = this.findBySql(strBuild.toString());
		return listRet;
	}


	@Override
	public Map querySafetyQualityStatistics() {
		return null;
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
		List<Object> params = new ArrayList<Object>();
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		StringBuffer sql=new StringBuffer();
//		sql.append("select * from ( ");
//		sql.append("select 'lastYear' name,case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end type,count(*) num,sum(contract_amount) sum");
//		sql.append(" from project p");
//		sql.append(" where p.sign_date <").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
//		sql.append(" and p.proj_status_id not in('2001','2002','1029') group by case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end");
//
//		sql.append(" union ");
//		sql.append("select 'thisYear' name,case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end type,count(*) num,sum(contract_amount) sum");
//		sql.append(" from project p");
//		sql.append(" where p.sign_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
//		sql.append(" group by case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end");
//
//		sql.append(") order by type,name desc");
		List sqlCurrentList = this.findBySql(sql.toString());



		List listType=new ArrayList();

		Map map;

		String [] typeArray={"11","12","13"};
		for(int i = 0;i < typeArray.length;i ++){
			map = new HashMap();
			String strType=typeArray[i];
			map.put("type", ProjLtypeEnum.valueof(strType).getMessage());
			for(int j = 0;j < sqlCurrentList.size();j ++){
				Object[]obj = (Object[])sqlCurrentList.get(j);
				String subType=(String)obj[1];

				if(strType.equals(subType)){
					if(obj[0].equals("lastYear")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", obj[3]);
					}
					if(obj[0].equals("thisYear")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", obj[3]);
					}
				}
			}
			listType.add(map);
		}

		//List<Map<String,Object>> list=this.findListBySql(sql.toString(), params.toArray());



		return listType;
	}

	@Override
	public List<Map<String, Object>> queryAreaMountStatistics() {
		List<Object> params=new ArrayList<Object>();
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();

		sql.append("select * from ( ");
		sql.append("select 'lastYear' name,area area,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.sign_date <").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" and p.proj_status_id not in('2001','2002','1029') group by area");

		sql.append(" union ");
		sql.append("select 'thisYear' name,area area,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.sign_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" group by area");
		sql.append(") order by area,name desc");
		List sqlCurrentList = this.findBySql(sql.toString());

		List listArea=new ArrayList();

		Map map;

		String [] areaArray={"110101","110102","110103","110104","110105"};
		for(int i = 0;i < areaArray.length;i ++){
			map = new HashMap();
			String areaAry=areaArray[i];
			map.put("area", AreaEnum.valueof(areaAry).getMessage());

			for(int j = 0;j < sqlCurrentList.size();j ++){
				Object [] obj=(Object[]) sqlCurrentList.get(j);
				//list中的area值与数组中的一致
				String subArea=(String)obj[1];
				if(areaAry.equals(subArea)){
					if(obj[0].equals("lastYear")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", ((BigDecimal)obj[3]).setScale(2));
					}
					if(obj[0].equals("thisYear")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", ((BigDecimal)obj[3]).setScale(2));
					}
				}

			}

			listArea.add(map);
		}
		return listArea;
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
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		Map map;
		String [] areaArray={"110101","110102","110103","110104","110105"};
		List sqlCurrentList=new ArrayList();
		List<Map<String,Object>> returnList=new ArrayList<Map<String, Object>>();
		StringBuffer sql;
		//查询各区金额
		for(int i = 0;i < areaArray.length;i ++){
			map=new HashMap();
			sql=new StringBuffer();
			sql.append("select sum(sum) from ( ");
			sql.append("select area,count(*) num,sum(contract_amount) sum");
			sql.append(" from project p");
			sql.append(" where p.sign_date <").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append(" and p.proj_status_id not in('2001','2002','1029') group by area");
			sql.append(" union ");
			sql.append("select area,count(*) num,sum(contract_amount) sum");
			sql.append(" from project p");
			sql.append(" where p.sign_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append("  group by area)");
			sql.append(" where area=").append(areaArray[i]);
			sqlCurrentList= this.findBySql(sql.toString());
			if(sqlCurrentList.get(0)==null){
				BigDecimal money=BigDecimal.ZERO;
				map.put("area"+i, money);
			}else{
				map.put("area"+i, ((BigDecimal)sqlCurrentList.get(0)).setScale(2));
			}

			returnList.add(map);
		}
		return returnList;
	}


	/**
     * 查询各阶段工程数量的汇总数据
     * @return
     */
	public Map queryProjectOfState()
	{
		//首先定义工程项目各阶段
		String[][]projectStage = {{"0","立项","1001,1002,1003,1004"},{"1","设计","1005,1006,1007"},{"2","预算","1008,1009,1010,10101"},{"3","合同","1011,10111,1012,1013,1014"},{"4","计划","1015,1016"},{"5","分包","1017,1018,1019,1020"},{"6","施工","1021,1022"},{"7","结算","1028,1029,1030,1031,10311,1032,1033,1034,1035,7003"},{"8","竣工","1023,1024,1025,1026,1027,10271,10221,10231,10232,10241"}};

		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select * from (");

		for(int i = 0;i < projectStage.length;i ++)
		{
			if(i > 0)strBuild.append(" union ");

			strBuild.append("(");
			strBuild.append("select '").append(projectStage[i][0]).append("' stageId,'").append(projectStage[i][1]).append("' stageDes, count(*) num from project p ");
			strBuild.append("WHERE p.proj_status_id in (").append(projectStage[i][2]).append(") ");

			strBuild.append(")");
		}

		strBuild.append(") A order by A.stageId ");

		List listRet = this.findBySql(strBuild.toString());

		Map map = new HashMap();
		for(int i = 0;i < projectStage.length;i ++)
		{
			map.put("name_" + projectStage[i][0],projectStage[i][1]);
			map.put("num_" + projectStage[i][0],"0");
			map.put("where_" + projectStage[i][0],projectStage[i][2]);
		}

		//对结果集进行处理
		for(int i = 0;i < listRet.size();i ++)
		{
			Object[]obj = (Object[])listRet.get(i);

			//map.put("num_" + String.valueOf(obj[0]),String.valueOf(((BigDecimal)obj[2]).intValue()));
			map.put("num_" + String.valueOf(obj[0]),obj[2].toString());
		}

		return map;
	}

	/**
     * 查询各阶段工程数量的明细数据
     * @return
     */
	public Map queryProjectOfStateDetail(ProjectQueryReq p)
	{
//		StringBuilder strBuild = new StringBuilder();
//		strBuild.append("from Project p where p.projStatusId in(").append(status).append(") order by p.projNo ");
//
//		List list = this.findByHql(strBuild.toString());

		String strStatus = p.getProjStatusId();
		if(strStatus == null) strStatus = " ";

		Criteria c = super.getCriteria();

		String[]statusArray = strStatus.split(",");
		List listArray = new ArrayList();
		for(int i = 0;i < statusArray.length;i ++)
		{
			listArray.add(statusArray[i]);
		}

		c.add(Restrictions.in("projStatusId",listArray));

		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<Project> projectList = this.findBySortCriteria(c,p);
		// 返回结果
		return ResultUtil.pageResult( filterCount, p.getDraw(),projectList);
	}

	/**
	 * 报表屏首页 去年结转 、本年新增
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryLastYearAndThisYearInfo() {
		List<Object> params=new ArrayList<Object>();
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		//simple.format(currentDate) 为演示 写死为2016
		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		//去年结转
		sql.append("select 'lastYear' name,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.accept_date <").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" and p.proj_status_id not in('2001','4001')");
		//本年新增
		sql.append(" union ");
		sql.append("select 'thisYear' name,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		List sqlCurrentList = this.findBySql(sql.toString());

		Map map=new HashMap();
		for(int j = 0;j < sqlCurrentList.size();j ++){
			Object [] obj=(Object[]) sqlCurrentList.get(j);
			if(obj[0].equals("lastYear")){
				map.put(obj[0]+"Num", obj[1]);
				if(obj[2]==null){
					BigDecimal money=BigDecimal.ZERO;
					map.put(obj[0]+"Money", money);
				}else{
					map.put(obj[0]+"Money", ((BigDecimal)obj[2]).setScale(2));
				}
			}
			if(obj[0].equals("thisYear")){
				map.put(obj[0]+"Num", obj[1]);
				if(obj[2]==null){
					BigDecimal money=BigDecimal.ZERO;
					map.put(obj[0]+"Money", money);
				}else{
					map.put(obj[0]+"Money", ((BigDecimal)obj[2]).setScale(2));
				}
			}
		}
		List thihisYearAndLsatYearList=new ArrayList();
		thihisYearAndLsatYearList.add(map);
		return thihisYearAndLsatYearList;
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
		List<Object> params=new ArrayList<Object>();
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();

		//结单
		sql.append("select 'alreadyFinish' name,count(*)");
		sql.append(" from project p");
		//sql.append(" where  p.proj_status_id ='").append(ProjStatusEnum.ALREADY_FINISH.getValue()).append("'");
		sql.append(" where  p.proj_status_id ='").append(ProjStatusEnum.ALREADY_COMPLETED.getValue()).append("'");
		//退单
		sql.append(" union ");
		sql.append("select 'termination' name,count(*)");
		sql.append(" from project p");
		sql.append(" where  p.proj_status_id ='").append(ProjStatusEnum.TERMINATION.getValue()).append("'");
		List sqlCurrentList = this.findBySql(sql.toString());

		Map map=new HashMap();
		for(int j = 0;j < sqlCurrentList.size();j ++){
			Object [] obj=(Object[]) sqlCurrentList.get(j);
			if(obj[0].equals("alreadyFinish")){
				map.put(obj[0]+"Num", obj[1]);
			}
			if(obj[0].equals("termination")){
				map.put(obj[0]+"Num", obj[1]);
			}
		}
		List alreadyFinishedSumList=new ArrayList();
		alreadyFinishedSumList.add(map);
		return alreadyFinishedSumList;
	}

	/** 报表屏首页 在建工程
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryConstructingProject() {
		StringBuffer sql=new StringBuffer();
		//施工各处数量
		sql.append(" select 'conProject' name, count(*),sum(contract_amount)");
		sql.append(" from project ");
		sql.append(" where proj_status_id in('1022','1023','1024','1025','1026','1027','10271','1028','1029','1030','1031','10311','1033','1034','1035','10221','10231','10232','10241','7701','7702','7703')");
		List sqlCurrentList = this.findBySql(sql.toString());
		Map map=new HashMap();

		for(int j = 0;j < sqlCurrentList.size();j ++){
			Object [] obj=(Object[]) sqlCurrentList.get(j);
			if(obj[0].equals("conProject")){
				map.put(obj[0]+"Num", obj[1]);
				map.put(obj[0]+"Amount", obj[2]);
			}
		}
		List constructingProjectNumList=new ArrayList();
		constructingProjectNumList.add(map);
		return constructingProjectNumList;
	}

	/** 报表屏首页 在建工程
	 * @author
	 * @createTime 2017-1-4
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryManagementOffice() {
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(*),project_type_des");
		sql.append(" from project");
		sql.append(" where proj_status_id in('1022','1023','1024','1025','1026','1027','10271','1028','1029','1030','1031','10311','1033','1034','1035','10221','10231','10232','10241','7701','7702','7703')");
		sql.append(" group by project_type_des");
		List sqlCurrentList = this.findBySql(sql.toString());
		Map map=new HashMap();
		for(int j = 0;j < sqlCurrentList.size();j ++){
			Object [] obj=(Object[]) sqlCurrentList.get(j);
				if(obj[1]!=null&&obj[1].equals("居民户工程")){
					map.put("fitstNum", obj[0]);
				}
				if(obj[1]!=null&&obj[1].equals("公建户工程")){
					map.put("secondNum", obj[0]);
				}
				if(obj[1]!=null&&obj[1].equals("改管工程")){
					map.put("thirdNum", obj[0]);
				}
				if(obj[1]!=null&&obj[1].equals("干线工程")){
					map.put("fourthNum", obj[0]);
				}
				if(obj[1]!=null&&obj[1].equals("智能表工程")){
					map.put("fifthNum", obj[0]);
				}
				if(obj[1]!=null&&obj[1].equals("场站工程")){
					map.put("sixthNum", obj[0]);
				}
		}

		List list=new ArrayList();
		list.add(map);
		return list;
	}

	/**
	 * 报表系统首屏最近12个月
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryLastMonthsNum() {
		/**一年前日期*/
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
        c.setTime(super.getDatabaseDate());
        c.add(Calendar.YEAR, -1);
        c.add(Calendar.MONTH, 1);
        String date = f.format(c.getTime());
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(mysqlSqlConveter.dataOperateYearAndMonth("pro.accept_date")).append(" acceptDate,count(*) num from project pro where pro.accept_date>=");
		sql.append(mysqlSqlConveter.dataOperate(date)).append(" group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("pro.accept_date")).append("");
		List<Map<String,Object>> result = super.findListBySql(sql.toString());
		return result;
	}

	/**
	 * 查询工程的汇总数据，按月进行分类统计
	 * @param fieldName 查询字段的名称
	 * @return
	 */
	public List<Map> queryProjectNumOfMonthByDate(String fieldName){
		List listRet = new ArrayList();
		if(fieldName == null || fieldName.equals("")){
			return listRet;
		}

		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1){
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		/////////////////由于演示，将日期限制在2016年///////////////////////////

		StringBuilder strBuild = new StringBuilder();
		strBuild.append("select ").append(mysqlSqlConveter.dataOperateYearAndMonth(fieldName)).append(",count(*) from Project p where ").append(fieldName).append(" > ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00")).append(" ");
		strBuild.append("and ").append(fieldName).append(" < ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-12-31 23:59:59")).append(" ");
		strBuild.append("group by ").append(mysqlSqlConveter.dataOperateYearAndMonth(fieldName)).append(" ");

		listRet = this.findByHql(strBuild.toString());

		if(listRet == null)listRet = new ArrayList();

		List listRetReal = new ArrayList();

		for(int i = 0;i < listRet.size();i ++){
			Object[] obj = (Object[])listRet.get(i);

			Map map = new HashMap();
			map.put("month", ((String)obj[0]).substring(5));
			map.put("num", String.valueOf(((Long)obj[1]).longValue()));
			listRetReal.add(map);
		}
		return listRetReal;
	}

	/**
	 * 用于工程项目第一个grid 统计当年和去年的工程数量、金额
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryLastYearAndThisYearNum() {

		List<Object> params=new ArrayList<Object>();
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		StringBuffer sql=new StringBuffer();
		//simple.format(currentDate)
		//去年项目-过滤掉退单的
		sql.append("select 'lastYear' name,count(*),sum(contract_amount) sum");
		sql.append(" from project pro ");
		sql.append(" where pro.accept_date <").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01"));
		sql.append(" and  pro.proj_status_id !='").append(ProjStatusEnum.TERMINATION.getValue()).append("'");
		sql.append(" union ");
		//今年项目-过滤掉退单的
		sql.append("select 'thisYear' name,count(*),sum(contract_amount) sum");
		sql.append(" from project pro ");
		sql.append(" where pro.accept_date >=").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01"));
		sql.append(" and  pro.proj_status_id !='").append(ProjStatusEnum.TERMINATION.getValue()).append("'");

		//统计当年竣工和退单
		StringBuffer hql=new StringBuffer();
		//竣工
		hql.append("select 'alreadyFinish' name,count(*)");
		hql.append(" from project p");
		hql.append(" where  p.proj_status_id ='").append(ProjStatusEnum.ALREADY_COMPLETED.getValue()).append("'");
		hql.append(" and p.accept_date> ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01"));
		//退单
		hql.append(" union ");
		hql.append("select 'termination' name,count(*)");
		hql.append(" from project p");
		hql.append(" where  p.proj_status_id ='").append(ProjStatusEnum.TERMINATION.getValue()).append("'");
		hql.append(" and p.accept_date> ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01"));

		//统计结转退单
		List hqlCurrentList = this.findBySql(hql.toString());

		Map map=new HashMap();
		for(int j = 0;j < hqlCurrentList.size();j ++){
			Object [] obj=(Object[]) hqlCurrentList.get(j);
			if(obj[0].equals("alreadyFinish")){
				map.put(obj[0]+"Num", obj[1]);
			}
			if(obj[0].equals("termination")){
				map.put(obj[0]+"Num", obj[1]);
			}
		}

		//统计
		List sqlCurrentList = this.findBySql(sql.toString());

		for(int j = 0;j < sqlCurrentList.size();j ++){
			Object [] obj=(Object[]) sqlCurrentList.get(j);
			if(obj[0].equals("lastYear")){
				map.put(obj[0]+"Num", obj[1]);
				map.put(obj[0]+"Money", null!=obj[2] ? ((BigDecimal)obj[2]).setScale(2) : BigDecimal.ZERO);
			}
			if(obj[0].equals("thisYear")){
				map.put(obj[0]+"Num", obj[1]);
				map.put(obj[0]+"Money", null!=obj[2] ? ((BigDecimal)obj[2]).setScale(2) : 0);
			}
		}
		List thihisYearAndLsatYearList=new ArrayList();
		thihisYearAndLsatYearList.add(map);
		return thihisYearAndLsatYearList;
	}

	/**
	 * 按工程规模统计 --统计当年的民用、车用、公用工程数量
	 * @author fuliwei
	 * @createTime 2017-01-07
	 * @return List
	 */
	@Override
	public List<Map<String, Object>> queryProjectTypeNum() {
		//获取当前日期
		Date currentDate = this.getDatabaseDate();
		SimpleDateFormat simple = new SimpleDateFormat("yyyy");

		/////////////////由于演示，将日期限制在2016年///////////////////////////
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		StringBuffer sql=new StringBuffer();

		sql.append("select 'thisYear' name,case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1)  else proj_Ltype_id end type,count(*) num,sum(contract_amount) sum ");
		sql.append(" from project p where p.accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append("group by case when instr(proj_Ltype_id,',',1) > 0 then ").append(mysqlSqlConveter.funcSubstringConvert()).append("(proj_Ltype_id,1,instr(proj_Ltype_id,',',1) -1) else proj_Ltype_id end");
		List sqlCurrentList = this.findBySql(sql.toString());

		List listType=new ArrayList();

		Map map;

		String [] typeArray={"11","12","13"};
		for(int i = 0;i < typeArray.length;i ++){
			map = new HashMap();
			String strType=typeArray[i];
			map.put("type", ProjLtypeEnum.valueof(strType).getMessage());
			for(int j = 0;j < sqlCurrentList.size();j ++){
				Object[]obj = (Object[])sqlCurrentList.get(j);
				String subType=(String)obj[1];
				if(strType.equals(subType)){
					map.put(obj[0]+"Num", obj[2]);
					map.put(obj[0]+"Money", obj[3]);
				}
			}
			listType.add(map);
		}
		return listType;
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
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}

		sql.append("select 'accept' name,area area,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" group by area");
		sql.append(" union ");
		sql.append("select 'sign' name,area area,count(*) num,sum(contract_amount) sum");
		sql.append(" from project p");
		sql.append(" where p.sign_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" group by area");



		List sqlCurrentList = this.findBySql(sql.toString());

		List listArea=new ArrayList();

		Map map;

		String [] areaArray={"110101","110102","110103","110104","110105"};
		for(int i = 0;i < areaArray.length;i ++){
			map = new HashMap();
			String areaAry=areaArray[i];
			map.put("area", AreaEnum.valueof(areaAry).getMessage());

			for(int j = 0;j < sqlCurrentList.size();j ++){
				Object [] obj=(Object[]) sqlCurrentList.get(j);
				//list中的area值与数组中的一致
				String subArea=(String)obj[1];
				if(areaAry.equals(subArea)){
					if(obj[0].equals("accept")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", ((BigDecimal)obj[3]).setScale(2));
					}
					if(obj[0].equals("sign")){
						map.put(obj[0]+"Num", obj[2]);
						map.put(obj[0]+"Money", ((BigDecimal)obj[3]).setScale(2));
					}
				}

			}

			listArea.add(map);
		}
		return listArea;
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
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		//立项阶段
		sql.append("select 'accept' status,count(*) from project where proj_status_id in('").append(ProjStatusEnum.TO_SURVEY.getValue()).append("','").append(ProjStatusEnum.TO_PROJECT_CONFIRM.getValue()).append("')").append(" and accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union ");
		//在建阶段
		sql.append(" select 'constructing' status,count(*) from project where proj_status_id in('").append(ProjStatusEnum.DURING_CONSTRUCTION.getValue()).append("','").append(ProjStatusEnum.TO_SELF_CHECK.getValue()).append("','");
		sql.append(ProjStatusEnum.TO_PRE_INSPECTION.getValue()).append("','").append(ProjStatusEnum.UNION_PRE_INSPECTION.getValue()).append("','").append(ProjStatusEnum.SETTLEMENT_REPORT.getValue()).append("','");
		sql.append(ProjStatusEnum.SETTLEMENT_REPORT_START.getValue()).append("','");
		sql.append(ProjStatusEnum.AUDIT_DONE_DISPATCH.getValue()).append("','");
		sql.append(ProjStatusEnum.REPORT_DONE_CONFIRM.getValue()).append("','").append(ProjStatusEnum.CONNECT_CONFIRM.getValue()).append("')");
		sql.append(" and accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union ");
		//结单
		//sql.append(" select 'trunGas' status,count(*) from project where proj_status_id ='").append(ProjStatusEnum.ALREADY_FINISH.getValue()).append("'");
		sql.append(" select 'trunGas' status,count(*) from project where proj_status_id ='").append(ProjStatusEnum.ALREADY_COMPLETED.getValue()).append("'");
		sql.append(" and accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union ");
		//退单
		sql.append(" select 'chargeback' status,count(*) from project where proj_status_id ='").append(ProjStatusEnum.TERMINATION.getValue()).append("'");
		sql.append(" and accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		List list=this.findBySql(sql.toString());
		Map map=new HashMap();
		for(int i =0;i<list.size();i++){
			Object [] obj=(Object[]) list.get(i);
			if(obj[0].equals("accept")){
				map.put(obj[0]+"Num", obj[1]);
			}
			if(obj[0].equals("constructing")){
				map.put(obj[0]+"Num", obj[1]);
			}
			if(obj[0].equals("trunGas")){
				map.put(obj[0]+"Num", obj[1]);
			}
			if(obj[0].equals("chargeback")){
				map.put(obj[0]+"Num", obj[1]);
			}
		}
		List areaList=new ArrayList();

		areaList.add(map);
		return areaList;
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
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		sql.append("select 'accept' status,count(*),sum(contract_amount) from project where  accept_date >").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union ");
		sql.append("select 'contract' status,count(*),sum(contract_amount) from project where sign_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		List list=this.findBySql(sql.toString());
		Map map=new HashMap();

		for(int i =0;i<list.size();i++){
			Object [] obj=(Object[]) list.get(i);
			if(obj[0].equals("accept")){
				map.put(obj[0]+"Num", obj[1]);
				map.put(obj[0]+"Money", ((BigDecimal)obj[2]).setScale(2));
			}
			if(obj[0].equals("contract")){
				map.put(obj[0]+"Num", obj[1]);
			}
		}
		List acceptAndContractList=new ArrayList();
		acceptAndContractList.add(map);
		return acceptAndContractList;

	}

	/**
	 * 用于工程项目第6个grid-应收应付统计
	 * @author
	 * @createTime 2017-1-7
	 * @param
	 * @return
	 */
	@Override
	public List<Map<String, Object>> querySignAndAladyCharge() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		//签约金额
		sql.append("select 'sign' name,sum(p.contract_amount) from project p  where p.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union");
		//应收
		sql.append(" select 'shouldCharge' name,sum(ar.ar_cost) from accruals_record ar where   ar.ar_flag='1' and ar.ar_type='13'and ar.ar_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union");
		//实收
		sql.append(" select 'alreadyCharge' name,sum(cf.cf_amount) from cash_flow cf where cf.cf_flag='1' and cf.cf_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union");
		//应付
		sql.append(" select 'shouldPay' name,sum(ar.ar_cost) from accruals_record ar where   ar.ar_flag='-1' and ar.ar_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
		sql.append(" union");
		//实付
		sql.append(" select 'alreadyPay' name,sum(cf.cf_amount) from cash_flow cf where cf.cf_flag='-1' and cf.cf_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));;
		List list=this.findBySql(sql.toString());
		Map map=new HashMap();

		for(int i =0;i<list.size();i++){
			Object [] obj=(Object[]) list.get(i);
			if(obj[0].equals("sign")){
				map.put(obj[0]+"Money", ((BigDecimal)obj[1]).setScale(2));
			}
			if(obj[0].equals("shouldCharge")){
				map.put(obj[0]+"Money", ((BigDecimal)obj[1]).setScale(2));
			}
			if(obj[0].equals("alreadyCharge")){
				map.put(obj[0]+"Money", ((BigDecimal)obj[1]).setScale(2));
			}
			if(obj[0].equals("shouldPay")){
				map.put(obj[0]+"Money", ((BigDecimal)obj[1]).setScale(2));
			}
			if(obj[0].equals("alreadyPay")){
				map.put(obj[0]+"Money", ((BigDecimal)obj[1]).setScale(2));
			}
		}
		List signAndAladyChargeList=new ArrayList();
		signAndAladyChargeList.add(map);
		return signAndAladyChargeList;
	}

	/**
	 * 用于工程施工 各施工处 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryConstructionNumsAndAmount() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		sql.append("select count(*) numVal,").append(mysqlSqlConveter.funcSubstringConvert()).append("(pl.MANAGEMENT_OFFICE,5,6) name,sum(pro.contract_amount) amountVal");
		sql.append(" from construction_plan pl,project pro where pro.proj_id =pl.proj_id ");
		sql.append(" and pro.proj_status_id in('1019','1020','1021','1022','1023','10231','10232','1024','10241','10242','1025','10251','1026','1027','1028')");
		sql.append(" and pro.accept_date>  ").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));;
		sql.append(" group by MANAGEMENT_OFFICE");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;
	}

	/**
	 * 用于工程施工 各分包单位 数量、金额统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryCuUnitNumsAndAmount() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List<ProjStatusEnum> projStuList = ProjStatusEnum.getThanValueNew(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
		sql.append(" select project_type_des name, count(*) numVal, sum(contract_amount) amountVal");
		sql.append(" from project where ");
		sql.append("proj_status_id in('");
		for(int i=1;i<projStuList.size();i++){
			sql.append(projStuList.get(i).getValue()).append("',").append("'");
		}
		sql.append(projStuList.get(0).getValue()).append("')");
		sql.append(" and accept_date> str_to_date('").append(simple.format(currentDate)).append("-01-01 00:00:00','%Y-%m-%d %H:%i:%s')");
		sql.append(" group by name");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;

	}

	@Override
	public List<Map<String, Object>> queryConstructionNumsAndAmountRight() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List<ProjStatusEnum> projStuList = ProjStatusEnum.getThanValueNew(ProjStatusEnum.DURING_CONSTRUCTION.getValue(),ProjStatusEnum.CONNECT_CONFIRM.getValue());
		sql.append(" select project_type_des name, count(*) numVal, sum(sc.SC_AMOUNT) amountVal");
		sql.append(" from project p,sub_contract sc where p.proj_id = sc.proj_id");
		sql.append(" and p.proj_status_id in('");
		for(int i=1;i<projStuList.size();i++){
			sql.append(projStuList.get(i).getValue()).append("',").append("'");
		}
		sql.append(projStuList.get(0).getValue()).append("')");
		sql.append(" and p.accept_date> str_to_date('").append(simple.format(currentDate)).append("-01-01 00:00:00','%Y-%m-%d %H:%i:%s')");
		sql.append(" group by name");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;
	}

	/**
	 * 用于工程施工 各施工处扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryContructionUnitSafetyNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		sql.append("select ").append(mysqlSqlConveter.funcSubstringConvert()).append("(cp.MANAGEMENT_OFFICE,5,6) name,sum(FRACTION) val");
		sql.append(" from INSPECTION_RECORD ir,construction_plan cp,project pro");
		sql.append(" where ir.proj_id=cp.proj_id and cp.proj_id=pro.proj_id");
		sql.append(" and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));;;
		sql.append("  group by management_office");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;
	}

	/**
	 * 用于工程施工 各分包单位扣分统计
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryCuUnitSafetyNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");
		StringBuffer sql=new StringBuffer();
		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		sql.append("select bp.short_name name,sum(FRACTION) val");
		sql.append(" from INSPECTION_RECORD ir,construction_plan cp,project pro,BUSINESS_PARTNERS bp");
		sql.append(" where ir.proj_id=cp.proj_id and cp.proj_id=pro.proj_id and cp.cu_id=bp.unit_id");
		sql.append(" and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));;;
		sql.append("  group by bp.short_name");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;
	}

	/**
	 * 用于工程施工 各施工处按工程类型统计-民用
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryContructionUnitScaleNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");

		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List scalelist=new ArrayList();
		String [] scaleCivilArray={"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};
		for(int i=0;i<scaleCivilArray.length;i++){
			StringBuffer sql=new StringBuffer();
			sql.append("select sum(sd.HOUSE_NUM) val,").append(mysqlSqlConveter.funcSubstringConvert()).append("(cp.MANAGEMENT_OFFICE,5,6) name");
			sql.append("  from scale_detail sd,construction_plan cp,project pro");
			sql.append("  where sd.proj_id=cp.proj_id and  pro.proj_id= cp.proj_id and sd.PROJ_STYPE_DES='").append(scaleCivilArray[i]).append("'");
			sql.append("  and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append("  group by cp.management_office");
			List<Map<String, Object>> list=this.findListBySql(sql.toString());
			Map map=new HashMap();
			map.put(scaleCivilArray[i], list);
			scalelist.add(map);
		}
		return scalelist;
	}

	/**
	 * 用于工程施工 各施工处按工程规模统计-公用
	 * @author fuliwei
	 * @createTime 2017-1-10
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryContructionUnitScalePublicNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");

		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List scalelist=new ArrayList();
		String [] scalePulicArray={"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};
		for(int i=0;i<scalePulicArray.length;i++){
			StringBuffer sql=new StringBuffer();
			sql.append("select sum(sd.sear_num) val,").append(mysqlSqlConveter.funcSubstringConvert()).append("(cp.MANAGEMENT_OFFICE,5,6) name");
			sql.append("  from scale_detail sd,construction_plan cp,project pro");
			sql.append("  where sd.proj_id=cp.proj_id and  pro.proj_id= cp.proj_id and sd.PROJ_STYPE_DES='").append(scalePulicArray[i]).append("'");
			sql.append("  and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append("  group by cp.management_office");
			List<Map<String, Object>> list=this.findListBySql(sql.toString());
			Map map=new HashMap();
			map.put(scalePulicArray[i], list);
			scalelist.add(map);
		}
		return scalelist;
	}

	/**
	 * 用于工程施工 各分包单位按工程规模统计
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryCuUnitScaleNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");

		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List scalelist=new ArrayList();
		String [] scaleCivilArray={"高层壁挂(户)","多层壁挂(户)","别墅(户)","多层(户)","私宅(户)","高层(户)"};
		for(int i=0;i<scaleCivilArray.length;i++){
			StringBuffer sql=new StringBuffer();
			sql.append("select sum(sd.HOUSE_NUM) val,bp.short_name name");
			sql.append("  from scale_detail sd,construction_plan cp,project pro,business_partners bp");
			sql.append("  where sd.proj_id=cp.proj_id and  pro.proj_id= cp.proj_id and cp.cu_id=bp.unit_id and sd.PROJ_STYPE_DES='").append(scaleCivilArray[i]).append("'");
			sql.append("  and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append("  group by bp.short_name");
			List<Map<String, Object>> list=this.findListBySql(sql.toString());
			Map map=new HashMap();
			map.put(scaleCivilArray[i], list);
			scalelist.add(map);
		}
		return scalelist;
	}

	/**
	 * 用于工程施工 各分包单位按工程规模统计-公用
	 * @author fuliwei
	 * @createTime 2017-1-11
	 * @param
	 * @return Map<String, Object>
	 */
	@Override
	public List<Map<String, Object>> queryCuUnitScalePublicNums() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");

		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}
		List scalelist=new ArrayList();
		String [] scalePulicArray={"餐厅(座)","车用","其他","采暖锅炉(座)","工业(座)","门面房(座)"};
		for(int i=0;i<scalePulicArray.length;i++){
			StringBuffer sql=new StringBuffer();
			sql.append("select sum(sd.sear_num) val,bp.short_name name");
			sql.append("  from scale_detail sd,construction_plan cp,project pro,business_partners bp");
			sql.append("  where sd.proj_id=cp.proj_id and  pro.proj_id= cp.proj_id and cp.cu_id=bp.unit_id and sd.PROJ_STYPE_DES='").append(scalePulicArray[i]).append("'");
			sql.append("  and pro.accept_date>").append(mysqlSqlConveter.dataOperate(simple.format(currentDate)+"-01-01 00:00:00"));
			sql.append("  group by bp.short_name");
			List<Map<String, Object>> list=this.findListBySql(sql.toString());
			Map map=new HashMap();
			map.put(scalePulicArray[i], list);
			scalelist.add(map);
		}
		return scalelist;
	}

	/**
	 * 用于近一年每月施工处扣分数
	 * @author fuliwei
	 * @createTime 2017-01-03
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryContructionUnitScore() {
		/**一年前日期*/
		Format f = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
        c.setTime(super.getDatabaseDate());
        c.add(Calendar.YEAR, -1);
        c.add(Calendar.MONTH, 1);
        String date = f.format(c.getTime());


		StringBuffer constructionSql = new StringBuffer();
		constructionSql.append("select cp.management_office");
		constructionSql.append(" from construction_plan cp group by cp.management_office");
		List constructionList = this.findBySql(constructionSql.toString());
		List result=new ArrayList();
		for(int i=0;i<constructionList.size();i++){
			StringBuffer sql = new StringBuffer();
			sql.append("select sum(il.TOTAL) score,").append(mysqlSqlConveter.dataOperateYearAndMonth("il.CHECK_DATE")).append(" CHECK_DATE");
			sql.append(" from INSPECTION_LIST il,project pro");
			sql.append(" where pro.proj_id=il.proj_id and il.Check_Date is not null and pro.accept_date>=").append(mysqlSqlConveter.dataOperate(date));
			sql.append(" and il.CONSTRUCTION_DEPARTMENT ='").append(constructionList.get(i)).append("'");
			sql.append(" group by ").append(mysqlSqlConveter.dataOperateYearAndMonth("il.CHECK_DATE"));
			List<Map<String, Object>> list=this.findListBySql(sql.toString());
			Map map=new HashMap();
			map.put(constructionList.get(i), list);
			result.add(map);
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
	public List<Map<String, Object>> queryProjectSchedule() {
		Date currentDate=this.getDatabaseDate();
		SimpleDateFormat simple=new SimpleDateFormat("yyyy");

		if(isTest == 1)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, 2016);
			currentDate = calendar.getTime();
		}

		StringBuffer sql = new StringBuffer();
		sql.append(" select project_type_des name,count(*) num,pt. progress progress from (");
		sql.append(" select case when p.total_progress<=30 then 'A'");
		sql.append(" when p.total_progress<=50 and p.total_progress>30 then 'B'");
		sql.append(" when p.total_progress<=60 and p.total_progress>50 then 'C'");
		sql.append(" when p.total_progress<=80 and p.total_progress>60 then 'D'");
		sql.append(" when p.total_progress<=90 and p.total_progress>80 then 'E'");
		sql.append(" when p.total_progress<=100 and p.total_progress>90 then 'F' else null End ");
		sql.append(" progress,p.proj_id projId,p.project_type_des,p.total_progress tprogress ");
		sql.append(" from project p where p.total_progress is not null and p.accept_date>=").append(mysqlSqlConveter.dataOperate(simple.format(currentDate))).append(")");
		sql.append(" pt group by pt. progress");
		List<Map<String, Object>> list=this.findListBySql(sql.toString());
		return list;

	}

}
