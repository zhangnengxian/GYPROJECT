package cc.dfsoft.uexpress.common.util;

public class HQLUtils {
	/**
	 * 去除hql语句中from之前的部分
	 * @param hql
	 * @return
	 */
	public static String removeHeader(String hql) {
		int from = hql.indexOf("from");
		if(from != -1) {
			hql = hql.substring(from);
		}
		
		return hql;
	}

	/**
	 * 去除hql语句中where后面的部分
	 * @param hql
	 * @return
	 */
	public static String removeWhere(String hql) {
		int where = hql.indexOf("where");
		if(where != -1) {
			hql = hql.substring(0,where);
		}
		
		return hql;
	}
	
	
	/**
	 * 去除hql中的left join, right join和inner join
	 * @param hql
	 * @param joinType
	 * @return
	 */
	public static String removeJoin(String hql, String joinType) {
		int firstJoin = hql.indexOf(joinType);
		int where = hql.indexOf("where");
		if(firstJoin != -1) {
			if(where == -1 ) where = hql.length();
			hql = hql.substring(0, firstJoin) + " " +hql.substring(where);
		}
		return hql;
	}
	
	
	/**
	 * 去除hql中的order by, group by
	 * @param hql
	 * @param sortType
	 * @return
	 */
	public static String removeSort(String hql, String sortType) {
		int sort = hql.indexOf(sortType);
		
		if(sort != -1) {
			hql = hql.substring(0, sort);
		}
		
		return hql;
	}
	
	
	
}
