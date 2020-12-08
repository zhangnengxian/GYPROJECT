package cc.dfsoft.uexpress.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.uexpress.common.dto.PageSortReq;
import cc.dfsoft.uexpress.common.sqlConveter.MysqlSqlConveter;
import cc.dfsoft.uexpress.common.util.BeanUtil;
import cc.dfsoft.uexpress.common.util.HQLUtils;
import cc.dfsoft.uexpress.common.util.StringUtil;


/**
 * Dao基类
 * 
 * @author wx
 * @param <T>
 *            实体泛型
 * @param <PK>
 *            实体主键泛型
 */
@Transactional
public class NewBaseDAO<T, PK extends Serializable> implements CommonDao<T, PK> {
	private static final Logger logger = LoggerFactory
			.getLogger(NewBaseDAO.class);
	
	/**
	 * 注入mysqlSqlConveter类
	 */
	@Autowired(required=true)
	@Qualifier("mysqlSqlConveter")
	protected MysqlSqlConveter  mysqlSqlConveter;
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> entityClass;

	protected Session session() {
		logger.debug("sessionFactory getCUrrentSession"
				+ this.getClass().toString());
		return sessionFactory.getCurrentSession();
	}

	protected Session openSession() {
		logger.debug("sessionFactory openNew Session"
				+ this.getClass().toString());
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	public NewBaseDAO() {
		this.entityClass = null;
		@SuppressWarnings("rawtypes")
		Class c = this.getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();
			this.entityClass = (Class<T>) parameterizedType[0];
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#save(T)
	 */
	@Override
	public void save(T t) {
		session().save(t);
	}
	/**
	 * 从一级缓存中删除对象
	 * @param t 
	 */
	@Override
    public void evict(T t){
    	session().evict(t);
    }
	/**
	 * 拷贝对象
	 */
	@Override
	public void merge(T t){
		session().merge(t);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#delete(PK)
	 */
	@Override
	public void delete(PK id) {
		session().delete(this.get(id));
	}
	
	@Override
	public void delete(T t) {
		session().delete(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#update(T)
	 */
	@Override
	public void update(T t) {
		session().update(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#saveOrUpdate(T)
	 */
	@Override
	public void saveOrUpdate(T t) {
		session().saveOrUpdate(t);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#get(PK)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		if(id==null){
			return null;
		}
		return (T) session().get(this.entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#get(java.lang.String, java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(String propName, Object value) {
		List<T> l = getCriteria().add(Restrictions.eq(propName, value)).list();
		return l != null && l.size() > 0 ? l.get(0) : null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return session().createCriteria(this.entityClass).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#getCriteria()
	 */
	@Override
	public Criteria getCriteria() {
		return session().createCriteria(this.entityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#findByCriteria(org.hibernate.Criteria)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criteria criteria) {
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#countByCriteria(org.hibernate.Criteria)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Integer countByCriteria(Criteria criteria) {
		Integer total = 0;
		criteria.setProjection(Projections.rowCount());
		List<Object> list = criteria.list();
		if (list != null && list.size() > 0) {
			total = Integer.parseInt(list.get(0).toString());
		}
		criteria.setProjection(null);
		return total;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#findByExample(T)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByExample(T t) {
		Example example = Example.create(t);
		Criteria criteria = session().createCriteria(this.entityClass);
		criteria.add(example);
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#findByHql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findByHql(final String hql, final Object... params) {
		Query query = session().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	/**
	 * 根据条件返回结果对象
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findClassByHql(String hql) {
		return (T) session().createQuery(hql).uniqueResult();
	}
	
	/**
	 * 根据条件返回结果对象
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T findClassByHql(final String hql, final Object... params) {
		Query query = session().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (T) query.uniqueResult();
	}
	
	/**
	 * 分页返回结果
	 * @param criteria
	 * @param pageSortReq
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBySortCriteria(Criteria criteria, PageSortReq pageSortReq) {
		if(pageSortReq.getStart()!=null){
			criteria.setFirstResult(pageSortReq.getStart());
		}
		if(pageSortReq.getLength()!=null){
			criteria.setMaxResults(pageSortReq.getLength());
		}
		//排序
		String sort = pageSortReq.getSort();
		if(StringUtil.isNotBlank(sort)) {
			if(sort.equals("asc")) {
				criteria.addOrder(Order.asc(pageSortReq.getSortName()));
			}else {
				criteria.addOrder(Order.desc(pageSortReq.getSortName()));
			}
		}
		
		return criteria.list();
	}
	
	/**
	 * 以map作为参数分页查询
	 * @param criteria
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> findBySortCriteria(Criteria criteria, Map<String,String> map) {
		//分页参数
		criteria.setFirstResult(Integer.parseInt((String)map.get("start")));
		criteria.setMaxResults(Integer.parseInt((String)map.get("length")));
		//排序
		String orderc = (String)map.get("order[0][column]");
    	String sort = (String)map.get("order[0][dir]");
    	String sortName=(String)map.get("columns["+orderc+"][data]");
		if(StringUtil.isNotBlank(sort) && sort.equals("asc")){
    		criteria.addOrder(Order.asc(sortName));
    	}else{
    		criteria.addOrder(Order.desc(sortName));
    	}
		return criteria.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#executeHql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public boolean executeHql(final String hql, final Object... params) {
		Query query = session().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate() > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#findBySql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object[]> findBySql(final String sql, final Object... params) {
		Query query = session().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.list();
	}
	
	/**
	 * 返回多条对象结果
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> findListBySql(final String sql, final Object... params) {
		Query query = session().createSQLQuery(sql);
		if(params!=null){
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}}
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	/**
	 * 返回单一对象结果
	 * @param sql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> findObjectBySql(final String sql, final Object... params) {
		Query query = session().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP); 
		return ((Map<String, Object>) query.uniqueResult());
	}
	
	/**
	 * 根据sql返回总数量
	 * @param sql
	 * @param params
	 * @return
	 */
	public int getCountBySql(final String sql, final Object... params) {
		Query query = session().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return Integer.parseInt(query.uniqueResult().toString());
	} 

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#executeSql(java.lang.String,
	 * java.lang.Object)
	 */
	@Override
	public boolean executeSql(final String sql, final Object... params) {
		Query query = session().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0;  i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate() > 0 ? true : false;
	}
	
	@Override
	public boolean executeSqlList(final String sql, final List<Object> params) {
		Query query = session().createSQLQuery(sql);
		if (params != null) {
			for (int i = 0;  i < params.size(); i++) {
				query.setParameter(i, params.get(i));
			}
		}
		return query.executeUpdate() > 0 ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#findByHql(java.lang.String)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List findByHql(String hql) {
		return session().createQuery(hql).list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cc.dfsoft.base.dao.CommonDao#updateNotNullProperties(java.lang.Class,
	 * java.lang.Object, java.io.Serializable)
	 */
	@Override
	public boolean updateNotNullProperties(
			@SuppressWarnings("rawtypes") Class clazz, Object orig,
			Serializable objId) throws IllegalAccessException,
			InvocationTargetException {
		Object dest = this.session().get(clazz, objId);
		if (dest == null) {
			return false;
		}
		BeanUtil.copyNotNullProperties(dest, orig);
		this.session().update(dest);
		return true;
	}

	public boolean updateNotNullProperties(Object orig, Serializable objId) {
		Object dest = this.session().get(orig.getClass(), objId);
		if (dest == null) {
			return false;
		}
		BeanUtil.copyNotNullProperties(dest, orig);
		this.session().update(dest);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#batchInsertObjects(java.util.List)
	 */
	@Override
	public void batchInsertObjects(@SuppressWarnings("rawtypes") List objs) {
		Session session = session();
		for (int i = 0; i < objs.size(); i++) {
			Object obj = objs.get(i);
			if (obj != null)
				session.save(obj);
			if ((i + 1) % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#batchUpdateObjects(java.util.List)
	 */
	@Override
	public void batchUpdateObjects(@SuppressWarnings("rawtypes") List objs) {
		Session session = session();
		for (int i = 0; i < objs.size(); i++) {
			Object obj = objs.get(i);
			session.update(obj);
			if ((i + 1) % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cc.dfsoft.base.dao.CommonDao#batchInsertOrUpdateObjects(java.util.List)
	 */
	@Override
	public void batchInsertOrUpdateObjects(
			@SuppressWarnings("rawtypes") List objs) {
		Session session = session();
		for (int i = 0; i < objs.size(); i++) {
			Object obj = objs.get(i);
			if (obj != null)
				session.saveOrUpdate(obj);
			if ((i + 1) % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#batchDeleteObjects(java.util.List)
	 */
	@Override
	public void batchDeleteObjects(@SuppressWarnings("rawtypes") List objs) {
		Session session = session();
		for (int i = 0; i < objs.size(); i++) {
			Object obj = objs.get(i);
			session.delete(obj);
			if ((i + 1) % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#batchUpdateHql(java.util.List)
	 */
	@Override
	public void batchUpdateHql(@SuppressWarnings("rawtypes") List hqls) {
		Session session = session();
		for (int i = 0; i < hqls.size(); i++) {
			String aHql = (String) hqls.get(i);
			session.createQuery(aHql).executeUpdate();
			if ((i + 1) % 200 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cc.dfsoft.base.dao.CommonDao#getDatabaseDate()
	 */
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public Date getDatabaseDate() {
		Session session = session();
		@SuppressWarnings("rawtypes")
//		session.createQuery("SELECT CURRENT_DATE() from Person p").list();//获取数据库服务器日期 
//		session.createQuery("SELECT CURRENT_TIME() from Person p").list();//获取数据库服务器time 
//		session.createQuery("SELECT CURRENT_TIMESTAMP() from Person p").list();//timestamp 
//		List l = session.createQuery("select CURRENT_TIMESTAMP()  from Menu  e")
//				.list();
		List l = session.createSQLQuery("select CURRENT_TIMESTAMP()  from menu  e limit 1").list();
		if (l.size() <= 0) {
			return null;
		}
		return (Date) l.get(0);
	}

	/**
	 * 去除hql语句中的select *, left join, right join, inner join, order by, group
	 * by等，以期能在hql上使用select count(*)。
	 * 
	 * @param hql
	 * @return
	 */
	public String hqlFilterForCount(String hql) {
		if (!hql.startsWith("from"))
			hql = HQLUtils.removeHeader(hql);
		if (hql.indexOf("left join") != -1)
			hql = HQLUtils.removeJoin(hql, "left join");
		if (hql.indexOf("right join") != -1)
			hql = HQLUtils.removeJoin(hql, "right join");
		if (hql.indexOf("inner join") != -1)
			hql = HQLUtils.removeJoin(hql, "inner join");

		if (hql.indexOf("order by") != -1)
			hql = HQLUtils.removeSort(hql, "order by");

		if (hql.indexOf("group by") != -1)
			hql = HQLUtils.removeSort(hql, "group by");
		return hql;
	}

	/**
	 * 根据hql语句，返回符合条件的记录数量
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int getCount(String hql) {
		Session session = this.sessionFactory.openSession();
		String hqlC = "select count(*) "+hql;
		/*hqlC = hqlFilterForCount(hqlC);*/
		try {
			Query query = session.createQuery(hqlC);
			Iterator it = query.iterate();
			if(it!=null && it.hasNext()){
				return Integer.parseInt(it.next().toString());
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public String generateMaxProjNo(String corpId) throws SQLException {
		Connection connection = SessionFactoryUtils.getDataSource(sessionFactory).getConnection();
		CallableStatement proc = null;
		try {
				 String sp = "SP_GETPROJNO";
				 proc = connection.prepareCall("{ call "+sp+"(?) }"); 
				 proc.setString(1,corpId);
			     // proc.registerOutParameter(1, Types.VARCHAR); 
			      proc.execute(); 
			      ResultSet  result1 = proc.getResultSet();
			      String result;
			      if(result1.next()){
			    	  result =  result1.getString("result");
			      }else{
			    	  result ="";
			      }
			      
			      //System.out.println(" max proj NO = "+result);
			      return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HibernateException("存储过程  SP_GETPROJNO 执行失败！");
		} finally {
			try {
				proc.close();
				connection.close();
			} catch (SQLException e) {
				proc = null;
			}
		}
	}

}
