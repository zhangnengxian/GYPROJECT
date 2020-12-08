package cc.dfsoft.uexpress.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 判断日期是否为工作日（排除节假日和调整周末上班）测试
 * @author liaoyq
 * @createTime 2018年4月10日
 */
public class FestivalUtil {
		/** 
		 * @param args
		 * return void    返回类型 
		 * throws 
		 */
		public static void main(String[] args) {
			try {
				List<Date> workDays = new ArrayList<Date>();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				//workDays.add(dateFormat.parse("2017-12-30"));
				//workDays.add(dateFormat.parse("2017-12-31"));
				//workDays = displayDate("2017-12-30","2017-12-31");
				List<Date> holidays = new ArrayList<Date>();
				//holidays.add(dateFormat.parse("2018-10-18"));
				//holidays.add(dateFormat.parse("2018-10-17"));
				//holidays.add(dateFormat.parse("2018-10-19"));
				//holidays.add(dateFormat.parse("2018-01-04"));
				//holidays.add(dateFormat.parse("2018-01-05"));
				//holidays = displayDate("2018-01-01", "2018-01-05");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date startDate = df.parse("2018-10-17");
				Date endDate = df.parse("2018-05-10");
				//System.err.println("工作日："+calLeaveDays(startDate, endDate, null, null));
				System.err.println("工作日后的日期："+calLeaveDate(startDate, 2, null, holidays));
				DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//System.err.println(getChangeDate(df1.parse("2018-05-10 19:00:00")));
				//System.err.println(displayDate(null,null));
			} catch ( Exception e) {
				System.out.println(e.getClass());
				e.printStackTrace();
			}
			
		}
		/**
		 * 计算两个日期之间的每一天
		 * 包含两个日期
		 * @author liaoyq
		 * @createTime 2018年5月10日
		 * @param startTime
		 * @param endTime
		 * @return
		 */
		 public static List<Date> displayDate(Date startTime, Date endTime){
			 List<Date> list = new ArrayList<Date>();
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        try{
		        	startTime = dateFormat.parse(dateFormat.format(startTime));//设置循环开始日期  
					endTime = dateFormat.parse(dateFormat.format(endTime));
		            Calendar calendar = Calendar.getInstance();
		            calendar.setTime(startTime);
		            while(calendar.getTime().compareTo(endTime)!=1){ 
		            	 System.err.println(dateFormat.format(calendar.getTime()));
			             list.add(dateFormat.parse(dateFormat.format(calendar.getTime())));
		                 calendar.add(Calendar.DAY_OF_MONTH, 1); 
		            }
		        }catch(Exception e){
		            e.printStackTrace();
		            System.err.println("传递日期不正确");
		        }
		        return list;
		    }

		
		/**
		 * 计算两个日期之间的工作日除去周末、节假日、加上补班
		 * 包含两个日期
		 * @author liaoyq
		 * @createTime 2018年5月10日
		 * @param startTime 开始日期
		 * @param endTime  结束日期
		 * @param workDays
		 * @param holidays
		 * @return
		 */
		public static long calLeaveDays(Date startTime,Date endTime,List<Date> workDays,List<Date> holidays){ 
		    long leaveDays = 0;  
			//从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1  
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(); 
			try{
				Date flag = dateFormat.parse(dateFormat.format(startTime));//设置循环开始日期  
				endTime = dateFormat.parse(dateFormat.format(endTime));
				//循环遍历每个日期  
				while(flag.before(endTime)){ 
				    cal.setTime(flag);  
				//判断是否为周六日 、除去补班
				    int week = cal.get(Calendar.DAY_OF_WEEK) - 1;  
				    if((week == 0 || week == 6) && (workDays ==null || !workDays.contains(cal.getTime()))){//0为周日，6为周六  
				        //跳出循环进入下一个日期  
				        cal.add(Calendar.DAY_OF_MONTH, +1);  
				        flag = cal.getTime();  
				        continue;  
				    } 
			        if(holidays!=null&&holidays.contains(cal.getTime())){  
			            //跳出循环进入下一个日期  
			        	cal.add(Calendar.DAY_OF_MONTH, +1);  
			        	flag = cal.getTime();  
			        	continue; 
			        }
				    //不是节假日或者周末，天数+1  
				    leaveDays = leaveDays + 1;  
				    //日期往后加一天  
				    cal.add(Calendar.DAY_OF_MONTH, +1); 
				    flag = cal.getTime();
				}
			}catch(Exception e){
	            e.printStackTrace();
	            System.err.println("传递日期、参数不正确");
	        }
			return leaveDays; 
		}
		/**
		 * 判断日期是否超过14点，超过，返回+1天的日期
		 * @author liaoyq
		 * @createTime 2018年5月10日
		 * @param date
		 * @return
		 */
		 public static Date getChangeDate(Date date){
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		        try{
		        	String changeDate = dateFormat.format(date)+" 14:00:00";
		        	SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		        	if(date.after(sDateFormat.parse(changeDate))){
		        		Calendar calendar = Calendar.getInstance();
			            calendar.add(Calendar.DAY_OF_MONTH, 1); 
			            return calendar.getTime();
		        	}
		        }
		        catch(Exception e){
		            e.printStackTrace();
		        }
		        return date;
		    }
		 /**
		  * 计算某个日期之后加几个的工作日（除去周末、节假日、加上补班）的日期
		  * @author liaoyq
		  * @createTime 2018年10月17日
		  * @param startTime
		  * @param days
		  * @param cacheMap
		  * @param cacheMap2
		  * @return
		  */
		public static Date calLeaveDate(Date startTime, Integer days,
				List<Date> workDays, List<Date> holidays) {
			long leaveDays = 0;  
			//从startTime开始循环，若该日期不是节假日或者不是周六日则请假天数+1  
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance(); 
			try{
				Date flag = dateFormat.parse(dateFormat.format(startTime));//设置循环开始日期  
			    cal.setTime(flag);
				//循环遍历每个日期  
			    while(leaveDays<days){  
				//判断是否为周六日 、除去补班
				    int week = cal.get(Calendar.DAY_OF_WEEK) - 1;  
				    if((week == 0 || week == 6) && (workDays ==null || !workDays.contains(cal.getTime()))){//0为周日，6为周六  
				        //跳出循环进入下一个日期  
				        cal.add(Calendar.DAY_OF_MONTH, +1);  
				        flag = cal.getTime();  
				        continue;  
				    } 
			        if(holidays!=null&&holidays.contains(cal.getTime())){  
			            //跳出循环进入下一个日期  
			        	cal.add(Calendar.DAY_OF_MONTH, +1);  
			        	flag = cal.getTime();  
			        	continue; 
			        }
				    //不是节假日或者周末，天数+1  
				    leaveDays = leaveDays + 1; 
				    //日期往后加一天  
				    if(leaveDays!=days){
				    	cal.add(Calendar.DAY_OF_MONTH, +1);
				    }
				    flag = cal.getTime();
				}
				return flag;
			}catch(Exception e){
	            e.printStackTrace();
	            System.err.println("传递日期、参数不正确");
	        }
			return null;
		}
}
