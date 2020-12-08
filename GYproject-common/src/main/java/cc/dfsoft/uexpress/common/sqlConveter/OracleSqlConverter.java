package cc.dfsoft.uexpress.common.sqlConveter;

import org.springframework.stereotype.Repository;

@Repository
public class OracleSqlConverter implements SqlFunctionConverter {

	public String dataOperate(String param1) {
		return " to_date('" + param1 + "','yyyy-mm-dd hh24-mi-ss') ";
	}

	public String dateDayPart(String param1) {
		return "extract(day from " + param1 + ")";
	}

	public String dateMonthPart(String param1) {
		return "extract(month from " + param1 + ")";
	}

	public String dateYearPart(String param1) {
		return "extract(year from " + param1 + ")";
	}
	
	public String dateHourPart(String param1) {
		return " to_char(" + param1 + ",'HH24') ";
	}

	public String funcConcat(String sql) {
		return sql.replaceAll("\\+", "||");
	}

	public String funcConnactString() {
		return "||";
	}

	public String funcConvert(String type, String target, int format) {
		return " to_char(" + target + ",'YYYY-MM-DD HH24:MI:SS') ";
	}

	public String funcConvert(String type, String target) {
		if (type != null && !type.equals("")
				&& type.toLowerCase().indexOf("decimal") >= 0)
			return " to_char(" + target + ",'99999999D99') ";
		else {
			return " to_char(" + target + ") ";
		}
	}

	public String funcConvert(String type, String target, String formate) {
		return " to_char(" + target + ",'" + formate + "') ";
	}

	public String funcConvertISO(String type, String target, int format) {
		return " to_char(" + target + ",'YYYY-MM-DD') ";
	}

	public String funcConvertISO2(String type, String target, int format) {
		return " to_char(" + target + ",'YYYYMMDD') ";
	}

	public String funcConvertToNumber(String target) {
		return " to_number(" + target + ") ";
	}

	public String funcGetdateConvert() {
		return " SYSDATE ";
	}

	public String funcGetdateFromConvert() {
		return " SYSDATE from dual ";
	}
	
	public String funcIsnullConvert(String param1, String param2) {
		return " NVL ( " + param1 + "," + param2 + " )";
	}

	public String funcIsnullConvert() {
		return " NVL";
	}

	public String funcLenConvert(String param1) {
		if (param1 == null || param1.equals(""))
			return null;
		return " LENGTH ( " + param1 + " )";
	}

	public String funcLenConvert() {
		return " LENGTH";
	}

	public String funcSubstringConvert(String param1, int start, int length) {
		return " SUBSTRING ( " + param1 + "," + start + "," + length + " )";
	}

	public String funcSubstringConvert(String param1, int start) {
		return " SUBSTRING ( " + param1 + "," + start +  " )";
	}

	public String funcSubstringConvert() {
		return " SUBSTRING";
	}

	public String funcDateAdd(String type,String number,String date){
		
		if(type.equalsIgnoreCase("year")||type.equalsIgnoreCase("yyyy")||type.equalsIgnoreCase("yy")){
			return " sysdate+"+number+"*365 ";
		}else if(type.equalsIgnoreCase("month")||type.equalsIgnoreCase("mm")||type.equalsIgnoreCase("m")){
			return " sysdate+"+number+"*31 ";
		}else if(type.equalsIgnoreCase("day")||type.equalsIgnoreCase("dd")||type.equalsIgnoreCase("d")){
			return " sysdate+"+number+" ";
		}else if(type.equalsIgnoreCase("hour")||type.equalsIgnoreCase("hh")){
			return " sysdate+"+number+"/24 ";
		}else if(type.equalsIgnoreCase("minute")||type.equalsIgnoreCase("mi")||type.equalsIgnoreCase("n")){
			return " sysdate+"+number+"/(24*60) ";
		}else if(type.equalsIgnoreCase("second")||type.equalsIgnoreCase("ss")||type.equalsIgnoreCase("s")){
			return " sysdate+"+number+"/(24*60*60) ";
		}else if(type.equalsIgnoreCase("millisecond")||type.equalsIgnoreCase("ms")){
			return " sysdate+"+number+"/(24*60*60*1000) ";
		}
		return "";
	}
	
	public String funcDateDiff(String type,String date1,String date2){
		if(type.equalsIgnoreCase("year")||type.equalsIgnoreCase("yyyy")||type.equalsIgnoreCase("yy")){
			return " ("+date2+"-"+date1+")/365 ";
		}else if(type.equalsIgnoreCase("month")||type.equalsIgnoreCase("mm")||type.equalsIgnoreCase("m")){
			return " ("+date2+"-"+date1+")/31 ";
		}else if(type.equalsIgnoreCase("day")||type.equalsIgnoreCase("dd")||type.equalsIgnoreCase("d")){
			return " ("+date2+"-"+date1+") ";
		}else if(type.equalsIgnoreCase("hour")||type.equalsIgnoreCase("hh")){
			return " ("+date2+"-"+date1+")*24 ";
		}else if(type.equalsIgnoreCase("minute")||type.equalsIgnoreCase("mi")||type.equalsIgnoreCase("n")){
			return " ("+date2+"-"+date1+")*24*60 ";
		}else if(type.equalsIgnoreCase("second")||type.equalsIgnoreCase("ss")||type.equalsIgnoreCase("s")){
			return " ("+date2+"-"+date1+")*24*60*60 ";
		}else if(type.equalsIgnoreCase("millisecond")||type.equalsIgnoreCase("ms")){
			return " ("+date2+"-"+date1+")*24*60*60*1000 ";
		}
		
		return " ";
	}
	
	public String funcReplaceConvert(String key,int start,int length,String expression){
		String keyTemp = key.substring(start-1,start+length-1);
		
		return " Replace("+key+",'"+keyTemp+"', "+expression+") ";
	}

	@Override
	public String dataOperateYearAndMonth(String param) {
		return "to_char(" + param + ",'yyyy-mm')";
	}

	@Override
	public String funcNvl2(String expr1,String expr2,String expr3) {
		return "NVL2("+expr1+","+expr2+","+expr3+")";
	}

	@Override
	public String funcNvl2() {
		return "NVL2";
	}

	@Override
	public String funcAddMonths(String sysDate, int month) {
		return "ADD_MONTHS("+sysDate+","+month+")";
	}

	@Override
	public String rownumberConveter(String str) {
		return "rownum "+str;
	}
	
	//日期
		public String funcTruncConveter(String type,String date,String formate){
			return "trunc(" +date + "," + formate + ")";
		}
		public String funcTruncConveter(String type,String date){
			return "trunc(" +date + ")";
		}
		public String funcTruncConveter(String type){
			return "trunc(sysdate)";
		}
		public String funcTruncConveter(){
			return "trunc";
		}
}
