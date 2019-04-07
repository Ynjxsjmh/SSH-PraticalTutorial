package cn.atcast.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.atcast.dao.SaleVisitDao;
import cn.atcast.domain.BaseDict;
import cn.atcast.domain.CstCustomer;
import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.SaleVisitVo;

@Repository("saleVisitDao")
public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit, String> implements SaleVisitDao {

	//提取一个方法拼装 查询条件，同时拼装 参数
	private void findSaleVisitCondition(SaleVisitVo saleVisitVo,StringBuffer queryString,List<Object> params){
		//拼装 查询条件，拼装 查询条件同时拼装 参数
		if(saleVisitVo!=null){
			CstCustomer cstCustomer = saleVisitVo.getCstCustomer();
			if(cstCustomer!=null){
				//客户名称
				if(StringUtils.isNotEmpty(cstCustomer.getCustName())){
					queryString.append(" and  s.cstCustomer.custName like  ? ");
					params.add("%"+cstCustomer.getCustName()+"%");
				}
				//客户级别
				if(cstCustomer.getBaseDictByCustLevel()!=null){
					BaseDict baseDictByCustLevel = cstCustomer.getBaseDictByCustLevel();
					if(StringUtils.isNotEmpty(baseDictByCustLevel.getDictId())){
						queryString.append(" and s.cstCustomer.baseDictByCustLevel.dictId = ? ");
						params.add(baseDictByCustLevel.getDictId());
					}
				}
				//拜访时间(起)
				if(saleVisitVo.getVisitTime_start()!=null){
					queryString.append(" and s.visitTime >= ? ");
					params.add(saleVisitVo.getVisitTime_start());
				}
				//拜访时间(止)
				if(saleVisitVo.getVisitTime_end()!=null){
					queryString.append(" and s.visitTime <= ? ");
					params.add(saleVisitVo.getVisitTime_end());
				}
				
			}
		}		
	}
	
	@Override
	public List<SaleVisit> findSaleVisitList(SaleVisitVo saleVisitVo, int firstResult, int maxResults) {
		//如果在这个方法中得到Hibernate的session，通过session执行hql的查询（不使用HibernateTemplate）
		Session session = this.getSessionFactory().getCurrentSession();
		//使用hql查询
		StringBuffer queryString = new StringBuffer();
		//使用fetch将关联对象抓取过来存储到SaleVisit的cstCustomer属性中
		queryString.append("from SaleVisit s inner join fetch s.cstCustomer inner join fetch s.sysUser where 1=1 ");
		//定义List存放参数
		List<Object> params = new ArrayList<Object>();
		
		//拼装 查询条件
		findSaleVisitCondition(saleVisitVo, queryString, params);
		
		Query query = session.createQuery(queryString.toString());
		
		//参数绑定
		//遍历params，进行每个参数绑定
		for(int i=0;i<params.size();i++){
			query.setParameter(i, params.get(i));
		}
		//设置分页参数
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		//直接使用原始的query对象查询
		
		return query.list();
		//参数：1：hql语句，2：传入的参数，数组类型
		//return (List<SaleVisit>) this.getHibernateTemplate().find(queryString.toString(), params.toArray());

	}

	@Override
	public Long findSaleVisitCount(SaleVisitVo saleVisitVo) {
		//使用hql查询
		StringBuffer queryString = new StringBuffer();
		queryString.append("select count(*) from SaleVisit s inner join  s.cstCustomer inner join  s.sysUser where 1=1 ");
		//定义List存放参数
		List<Object> params = new ArrayList<Object>();

		//拼装 查询条件
		findSaleVisitCondition(saleVisitVo, queryString, params);
		List list = this.getHibernateTemplate().find(queryString.toString(), params.toArray());
		Long total  = (Long) list.get(0);
		return total;
	}

}
