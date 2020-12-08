package cc.dfsoft.uexpress.common.sqlConveter;

public interface SqlFunctionConverter {
	/***************************************************************************
	 * len ����ת��
	 * 
	 * @param param1
	 *            ��������������
	 * @return example :param1= col if ( database type is sql server ) return
	 *         len( col ) else if ( database type is oracle ) return length( col )
	 */
	public String funcLenConvert(String param1);
	
	/***************************************************************************
	 * len ����ת��
	 * ֻת��������ƣ����漰�����ת��	
	 */
	public String funcLenConvert();
	
	/***************************************************************************
	 * substring ����ת��
	 * 
	 * @param param1
	 *            ��������������
	 * @param start
	 *            ��һ������ָ���Ӵ��Ŀ�ʼλ�á�
	 * @param length
	 *            ��һ������ָ���Ӵ��ĳ���
	 * @return example :param1= col if ( database type is sql server ) return
	 *         substring( col ,start,length) else if ( database type is oracle )
	 *         return substr( col ,start,length)
	 */
	public String funcSubstringConvert(String param1,int start,int length);
	
	public String funcSubstringConvert(String param1, int start);
	
	/***************************************************************************
	 * substring ����ת��
	 * 
	 * ֻת��������ƣ����漰�����ת��	
	 */
	public String funcSubstringConvert();
	
	
	/***************************************************************************
	 * getdate ����ת��
	 * 
	 * @return example : if ( database type is sql server ) return getdate()
	 *         else if ( database type is oracle ) return sysdate()
	 */
	public String funcGetdateConvert();
	
	/***************************************************************************
	 * getdate ����ת�� ���?��from��sql 
	 * 
	 * @return example : if ( database type is sql server ) return getdate()
	 *         else if ( database type is oracle ) return sysdate()
	 */
	public String funcGetdateFromConvert();

	/***************************************************************************
	 * ��������ת��
	 * 
	 * @param param1
	 * @return * example :param1= '2006-05-10 23:59:59' if ( database type is
	 *         sql server ) return '2006-05-10 23:59:59' else if ( database type
	 *         is oracle ) return to_date('2006-05-10 23:59:59','yyyy-mm-dd
	 *         hh24-mi-ss')
	 */
	public String dataOperate(String param1);
	
	public String dateYearPart(String param1);
	
	public String dateMonthPart(String param1);
	
	public String dateDayPart(String param1);
	
	public String dateHourPart(String param1);
	
	

	/***************************************************************************
	 * convert ����ת��
	 * 
	 * @param param1
	 *            ��������������
	 * @return example :param1= col1,param2=exp if ( database type is sql server )
	 *         return isnull( col1,exp ) else if ( database type is oracle )
	 *         return nvl(col1,exp )
	 */
	public String funcIsnullConvert(String param1, String param2);
	
	/***************************************************************************
	 * convert ����ת��
	 * 	ֻת��������ƣ����漰�����ת��	
	 */
	public String funcIsnullConvert();

	/***************************************************************************
	 * convert ����ת���������������͵ĺ���ת��
	 * 
	 * @param type
	 *            Ҫת��������
	 * @param target
	 *            Ҫת���Ķ���
	 * @param format
	 *            ��ʽ
	 * @return example :target= col if ( database type is sql server ) return
	 *         to_char(target,DD-MON-YYYY HH24:MI:SS) else if ( database type is
	 *         oracle ) return convert(type,target,format)
	 */
	public String funcConvert(String type, String target, int format);

	/***************************************************************************
	 * concat ����ת��,���ڴ���oracle��||��sqlserver��+���͵�ת�����ַ������
	 * 
	 * @param type
	 *            Ҫת��������
	 * @param target
	 *            Ҫת���Ķ���
	 * @return example :target= col if ( database type is sql server ) return
	 *         to_char(target) else if ( database type is oracle ) return
	 *         convert(type,target)
	 */
	public String funcConvert(String type, String target);

	/***************************************************************************
	 * concat ����ת��,����oracle��||��sqlserver��+�����ַ� �ú�����������ԣ�������������ѧ����+�ţ�����ʹ��
	 * 
	 * @param sql
	 *            Ҫת����sql
	 * 
	 * @return example :sql= select 'a'+'b' from mytable; if ( database type is
	 *         sql server ) return sql else if ( database type is oracle )
	 *         return select 'a'||'b' from mytable;
	 */
	public String funcConcat(String sql);
	
	//liuc 2006.10.11
	public String funcConvert(String type, String target, String formate);
	
	//liuc 2006.11.29
	public String funcConvertToNumber(String target);

	public String funcConvertISO(String type, String target, int format);
	
	public String funcConvertISO2(String type, String target, int format);
	
	/***
	 * 
	 * @param type
	 * @param target
	 * @param format
	 * @return
	 */
	public String funcConnactString();
	
	/**
	 * 
	 * TODO:����sqlserver��dateadd()����(�÷���ֻ������type Ϊday,month,year�������)
	 * @param type  :�������ͣ�day,month,year 
	 * @param number:
	 * @param date
	 * @return: 
	 * @date:2007-10-16 ����02:22:30
	 */
	public String funcDateAdd(String type,String number,String date);
	/**
	 * 
	 * TODO:����sqlserver��datediff()����
	 * @param type ʱ�����ͣ�day,month,year,hour,minute,second
	 * @param date1
	 * @param date2
	 * @return: 
	 * @date:2007-10-16 ����04:54:42
	 */
	public String funcDateDiff(String type,String date1,String date2);
	
	public String funcReplaceConvert(String key,int start,int length,String expression);
	
	/**
	 * 
	 * @param param
	 * @return 
	 */
	public String dataOperateYearAndMonth(String param);
	
	public String funcNvl2();
	
	public String funcNvl2(String expr1,String expr2,String expr3);
	
	public String funcAddMonths(String sysDate,int month);
	
	public String rownumberConveter(String str);
	
}
