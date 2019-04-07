package cn.atcast.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import cn.atcast.dao.CustomerDao;
import cn.atcast.domain.CstCustomer;
import cn.atcast.pojo.CustomerVo;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<CstCustomer, Long> implements CustomerDao {
		
/*	@Autowired
	public void setHT(HibernateTemplate hibernateTemplate){
		//将CustomerDao 中注入进来 的hibernateTemplate给父类的setHibernateTemplate方法传入
		this.setHibernateTemplate(hibernateTemplate);
	}*/

	@Override
	public void insert(CstCustomer customer) {
		this.getHibernateTemplate().save(customer);
		
	}
	
	//
	
	@Override
	public DetachedCriteria createDetachedCriteria(){
		return DetachedCriteria.forClass(CstCustomer.class);
	}

//	@Override
//	public CstCustomer findCustomerById(Long custId) {
//		
//		return this.getHibernateTemplate().get(CstCustomer.class, custId);
//	}

	//拼装 查询条件
	private void findCustomerCondition(CustomerVo customerVo,DetachedCriteria detachedCriteria){
		
		if(customerVo!=null){
			//拼装 客户名称
			if(StringUtils.isNotEmpty(customerVo.getCustName())){
				//通过MatchMode.ANYWHERE自动前后加"%"
				detachedCriteria.add(Restrictions.like("custName", customerVo.getCustName(), MatchMode.ANYWHERE));
			}
			//客户来源
			if(customerVo.getBaseDictByCustSource()!=null){
				if(StringUtils.isNotEmpty(customerVo.getBaseDictByCustSource().getDictId())){
					detachedCriteria.add(Restrictions.eq("baseDictByCustSource.dictId", customerVo.getBaseDictByCustSource().getDictId()));
				}
			}
			//客户级别
			if(customerVo.getBaseDictByCustLevel()!=null){
				if(StringUtils.isNotEmpty(customerVo.getBaseDictByCustLevel().getDictId())){
					detachedCriteria.add(Restrictions.eq("baseDictByCustLevel.dictId", customerVo.getBaseDictByCustLevel().getDictId()));
				}
			}
		}
	}
	@Override
	public Long findCustomerCount(CustomerVo customerVo) {
		//根据customerVo中数据拼接查询条件，使用detachedCriteria
		//创建detachedCriteria
		DetachedCriteria detachedCriteria = this.createDetachedCriteria();
		//拼装 查询条件
		findCustomerCondition(customerVo, detachedCriteria);
		
		//设置投影列
		detachedCriteria.setProjection(Projections.rowCount());
		//查询
		List list = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		Long total  = (Long) list.get(0);
		return total;
	}

	@Override
	public List<CstCustomer> findCustomerList(CustomerVo customerVo, int firstResult, int maxResults) {
		//根据customerVo中数据拼接查询条件，使用detachedCriteria
		//创建detachedCriteria
		DetachedCriteria detachedCriteria = this.createDetachedCriteria();
		//拼装 查询条件
		findCustomerCondition(customerVo, detachedCriteria);
		
		return (List<CstCustomer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);
	}

	@Override
	public List<CstCustomer> findCustomerList(CustomerVo customerVo) {
		//根据customerVo中数据拼接查询条件，使用detachedCriteria
		//创建detachedCriteria
		DetachedCriteria detachedCriteria = this.createDetachedCriteria();
		//拼装 查询条件
		findCustomerCondition(customerVo, detachedCriteria);
		
		return (List<CstCustomer>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List findCustomerStatList(CustomerVo customerVo, int firstResult, int maxResults) {
		//获取hibernate的session
		Session session = this.getSessionFactory().getCurrentSession();
		//定义sql语句
		StringBuffer queryString = new StringBuffer();
		//获 取页面传来的统计分类
		String customerClass = customerVo.getCustomerClass();
		if(customerClass == null){//没有统计分类不进行统计
			return null;
		}
		//拼装  sql语句
		queryString.append("SELECT "
				+ " t.*,base_dict.dict_item_name "
				+ " FROM"
				+ " (SELECT COUNT(*) COUNT,"+customerClass
				+ " FROM cst_customer "
				+ " GROUP BY "+customerClass+") t,base_dict "
				+ "	WHERE t."+customerClass+" = base_dict.dict_id ");
		
		
		//创建原生 sql 的查询
		SQLQuery sqlQuery = session.createSQLQuery(queryString.toString());
		
		//设置分页参数
		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResults);
		
		//不进行结果集绑定，将结果类型作为List<Object[]>
		//在实际开发需要进行结果集绑定,绑定map，map的key就是字段名，key对应的value字段的值
		sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		
		return sqlQuery.list();
	}

	@Override
	public Long findCustomerStatCount(CustomerVo customerVo) {
		
		//获取hibernate的session
		Session session = this.getSessionFactory().getCurrentSession();
		//定义sql语句
		StringBuffer queryString = new StringBuffer();
		//获 取页面传来的统计分类
		String customerClass = customerVo.getCustomerClass();
		if(customerClass == null){//没有统计分类不进行统计
			return null;
		}
		//拼装  sql语句
		queryString.append("SELECT "
				+ " count(*) "
				+ " FROM"
				+ " (SELECT COUNT(*) COUNT,"+customerClass
				+ " FROM cst_customer "
				+ " GROUP BY "+customerClass+") t,base_dict "
				+ "	WHERE t."+customerClass+" = base_dict.dict_id ");
		
		
		//创建原生 sql 的查询
		SQLQuery sqlQuery = session.createSQLQuery(queryString.toString());
		
		List list = sqlQuery.list();
		
		//Long total = (Long) list.get(0); ，原生 查询的结果，只有一个数值自动转成类型为BigInteger类型
		//解决上边的问题，将list.get(0)转成字符串，转成long
		Long total =Long.parseLong(list.get(0)+"") ;
		
		return total;
	}

//	@Override
//	public void delete(Long custId) {
//		CstCustomer customer = this.getHibernateTemplate().get(CstCustomer.class, custId);
//		if(customer!=null){
//			//如果对象在数据库中不存在，hibernate执行删除抛出异常
//			this.getHibernateTemplate().delete(customer);
//		}
//		
//		
//	}

//	@Override
//	public void update(CstCustomer customer) {
//		this.getHibernateTemplate().update(customer);
//		
//	}
}
