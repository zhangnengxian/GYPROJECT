package cc.dfsoft.uexpress.common.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;



public interface CommonDao<T, PK extends Serializable> {
	/**
	 * 从一级缓存中删除实体
	 * 使其成为托管状态
	 * @param t
	 */
	 public void evict(T t);
	 /**
	  * 拷贝对象
	  */
	 public void merge(T t);
	/**
	 * 保存实体
	 * 
	 * @param t
	 */
	public abstract void save(T t);

	/**
	 * 删除实体
	 * 
	 * @param id
	 */
	public abstract void delete(T t);

	/**
	 * 通过主键删除实体
	 * 
	 * @param id
	 */
	public abstract void delete(PK id);

	/**
	 * 更新实体
	 * 
	 * @param t
	 */
	public abstract void update(T t);

	/**
	 * 保存或更新实�?
	 * 
	 * @param t
	 */
	public abstract void saveOrUpdate(T t);

	/**
	 * 根据主键获取实体
	 * 
	 * @param id
	 * @return
	 */
	public abstract T get(PK id);

	/**
	 * 获取全部实体
	 * 
	 * @return
	 */
	public abstract T get(String propName, Object value);

	/**
	 * 获取全部实体
	 * 
	 * @return
	 */
	public abstract List<T> getAll();

	/**
	 * 获取Criteria
	 * 
	 * @return
	 */
	public abstract Criteria getCriteria();

	/**
	 * QBC查询
	 * 
	 * @param criteria
	 * @return
	 */
	public abstract List<T> findByCriteria(Criteria criteria);

	/**
	 * QBC查询总数
	 * @param criteria
	 * @return
	 */
	public abstract Integer countByCriteria(Criteria criteria);

	/**
	 * QBE查询
	 * 
	 * @param t
	 * @return
	 */
	public abstract List<T> findByExample(T t);

	/**
	 * HQL查询
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public abstract List<T> findByHql(String hql, Object... params);

	/**
	 * 执行HQL
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public abstract boolean executeHql(String hql, Object... params);

	/**
	 * SQL查询
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public abstract List<Object[]> findBySql(String sql, Object... params);

	/**
	 * 执行HQL
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public abstract boolean executeSql(String sql, Object... params);
    /**
     * 执行sql
     * @param sql
     * @param params
     * @return
     */
	public abstract boolean executeSqlList(String sql, List<Object> params);
	/**
	 * 执行hql语句
	 * 
	 * @param hql
	 * @return
	 */
	public abstract List findByHql(String hql);

	/**
	 * 把orig中非空属性更新到数据库
	 * 
	 * @param clazz
	 * @param orig
	 * @param objId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public abstract boolean updateNotNullProperties(Class clazz, Object orig,
			Serializable objId) throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * 把orig中非空属性更新到数据库
	 * 
	 * @param orig
	 * @param objId
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public boolean updateNotNullProperties(Object orig, Serializable objId);

	/**
	 * 批量插入对象
	 * 
	 * @param objs
	 */
	public abstract void batchInsertObjects(List objs);

	/**
	 * 批量更新对象
	 * 
	 * @param objs
	 */
	public abstract void batchUpdateObjects(List objs);

	/**
	 * 批量新增、更新对象
	 * 
	 * @param objs
	 */
	public abstract void batchInsertOrUpdateObjects(List objs);

	/**
	 * 批量删除对象
	 * 
	 * @param objs
	 */
	public abstract void batchDeleteObjects(List objs);

	/**
	 * 批量执行hql语句
	 * 
	 * @param hqls
	 */
	public abstract void batchUpdateHql(List hqls);

	/**
	 * 获得数据库时间
	 * 
	 * @return
	 */
	public abstract Date getDatabaseDate();
	
	/**
	 * 获取工程编号
	 * @return
	 */
	public String generateMaxProjNo(String corpId) throws SQLException ;

	
}