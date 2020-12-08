package cc.dfsoft.uexpress.common.sqlConveter;

import org.springframework.stereotype.Repository;

/**
 * @author Dfsoft
 *
 */
@Repository
public class SqlServerSqlConverter implements SqlFunctionConverter {

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#dataOperate(java.lang.String)
	 */
	public String dataOperate(String param1) {
		return "'" + param1 + "'";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#dateDayPart(java.lang.String)
	 */
	public String dateDayPart(String param1) {
		return "DATEPART(day, " + param1 + ")";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#dateMonthPart(java.lang.String)
	 */
	public String dateMonthPart(String param1) {
		return "DATEPART(month, " + param1 + ")";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#dateYearPart(java.lang.String)
	 */
	public String dateYearPart(String param1) {
		return "DATEPART(year, " + param1 + ")";
	}

	public String dateHourPart(String param1){
		return "DATEPART(hour, " + param1 + ")";
	}
	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConcat(java.lang.String)
	 */
	public String funcConcat(String sql) {
		return sql;
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConnactString()
	 */
	public String funcConnactString() {
		return "+";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvert(java.lang.String, java.lang.String, int)
	 */
	public String funcConvert(String type, String target, int format) {
		return " convert(" + type + "," + target + "," + format + ") ";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvert(java.lang.String, java.lang.String)
	 */
	public String funcConvert(String type, String target) {
		return " convert(" + type + "," + target + ") ";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvert(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String funcConvert(String type, String target, String formate) {
		return " convert(" + type + "," + target + ") ";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvertISO(java.lang.String, java.lang.String, int)
	 */
	public String funcConvertISO(String type, String target, int format) {
		return " convert(" + type + "," + target + "," + format + ") ";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvertISO2(java.lang.String, java.lang.String, int)
	 */
	public String funcConvertISO2(String type, String target, int format) {
		return " convert(" + type + "," + target + "," + format + ") ";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcConvertToNumber(java.lang.String)
	 */
	public String funcConvertToNumber(String target) {
		return target;
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcGetdateConvert()
	 */
	public String funcGetdateConvert() {
		return " GETDATE() ";
	}

	public String funcGetdateFromConvert(){
		
		return " GETDATE() ";
	}
	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcIsnullConvert(java.lang.String, java.lang.String)
	 */
	public String funcIsnullConvert(String param1, String param2) {
		return " ISNULL ( " + param1 + "," + param2 + " )";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcIsnullConvert()
	 */
	public String funcIsnullConvert() {
		return " ISNULL";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcLenConvert(java.lang.String)
	 */
	public String funcLenConvert(String param1) {
		if (param1 == null || param1.equals(""))
			return null;
		return " LEN ( " + param1 + " )";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcLenConvert()
	 */
	public String funcLenConvert() {
		return " LEN";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcSubstringConvert(java.lang.String, int, int)
	 */
	public String funcSubstringConvert(String param1, int start, int length) {
		return " SUBSTRING ( " + param1 + "," + start + "," + length + " )";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcSubstringConvert(java.lang.String, int)
	 */
	public String funcSubstringConvert(String param1, int start) {
		return " SUBSTRING ( " + param1 + "," + start +  "," + param1.length() +" )";
	}

	/* (non-Javadoc)
	 * @see cc.dfsoft.j2ee.dao.SqlFunctionConverter#funcSubstringConvert()
	 */
	public String funcSubstringConvert() {
		return " SUBSTRING";
	}

	public String funcDateAdd(String type,String number,String date){
		
		return " dateadd("+type+","+number+","+date+") ";
	}
	
	public String funcDateDiff(String type,String date1,String date2){
		
		return " datediff("+type+","+date1+","+date2+") ";
	}
	
	public String funcReplaceConvert(String key,int start,int length,String expression){
		
		return " STUFF("+key+","+start+","+length+", "+expression+") ";
	}

	@Override
	public String dataOperateYearAndMonth(String param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String funcNvl2(String expr1, String expr2, String expr3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String funcNvl2() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String funcAddMonths(String sysDate, int month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String rownumberConveter(String str) {
		// TODO Auto-generated method stub
		return null;
	}
}
