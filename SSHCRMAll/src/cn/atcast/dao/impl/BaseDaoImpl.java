package cn.atcast.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.atcast.dao.BaseDao;

public class BaseDaoImpl<T,PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {
	
	@Autowired
	public void setHT(HibernateTemplate hibernateTemplate){
		//将注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
		this.setHibernateTemplate(hibernateTemplate);
	}
	private Class<T> entityClass;
	
	//通过构造方法获取T的class类型
	//当去实例 化子类时候，调用父类的构造方法
	public BaseDaoImpl(){
		//比如，实例化CustomerDaoImpl extends BaseDaoImpl<CstCustomer, Long>
		//先获取父类的类型，比如：BaseDaoImpl<CstCustomer, Long>
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		if(genericSuperclass instanceof ParameterizedType){
			//将父类类型转为泛型（参数化类型）
			ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
			//获取参数
			Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
			//取出第一个参数类型就是要的实体类的类型，比如中：CstCustomer.class
			Type type = actualTypeArguments[0];
			entityClass = (Class<T>) type;
		}
	}

	@Override
	public T findById(PK id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public void insert(T entity) {
		this.getHibernateTemplate().save(entity);
		
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
		
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void deleteById(PK id) {
		T entity = this.getHibernateTemplate().get(entityClass, id);
		if(entity!=null){
			this.getHibernateTemplate().delete(entity);
		}
	}

	@Override
	public DetachedCriteria createDetachedCriteria() {
		
		return DetachedCriteria.forClass(entityClass);
	}

}
