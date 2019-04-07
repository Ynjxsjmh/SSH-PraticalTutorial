package cn.atcast.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.loader.plan.build.spi.ReturnGraphTreePrinter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.atcast.dao.CustomerDao;
import cn.atcast.dao.CustomerDetailDao;
import cn.atcast.dao.SaleVisitDao;
import cn.atcast.domain.CstCustomer;
import cn.atcast.domain.CstCustomerDetail;
import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.CstCustomerDetailVo;
import cn.atcast.pojo.CustomerVo;
import cn.atcast.pojo.SaleVisitVo;
import cn.atcast.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	// 注入dao
	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private CustomerDetailDao customerDetailDao;
	
	@Autowired
	private SaleVisitDao saleVisitDao;

	@Override
	public CstCustomer findCustomerById(Long custId) {

		return customerDao.findById(custId);
	}

	@Override
	public Long findCustomerCount(CustomerVo customerVo) {
		
		return customerDao.findCustomerCount(customerVo);
	}

	@Override
	public List<CstCustomer> findCustomerList(CustomerVo customerVo, int firstResult, int maxResults) {
		return customerDao.findCustomerList(customerVo, firstResult, maxResults);
	}

	@Override
	public void deleteCustomer(Long custId) {
		customerDao.deleteById(custId);
		customerDetailDao.deleteById(custId);
		
	}

	@Override
	public void insertCustomer(CustomerVo customerVo, CstCustomerDetailVo customerDetailVo) {
		if(customerVo == null){
			//参数不合法
			//抛出异常
			throw new RuntimeException("参数不合法");
		}	
		//添加save基本信息，生成主键
		CstCustomer customer = new CstCustomer();
		//将customerVo中属性值拷贝到customer中
//		customer.setCustName(customerVo.getCustName());
//		customer.setCustLinkman(customerVo.getCustLinkman());
		//上边拷贝属性的方式修改为工具类实现
		//第一个为源对象，第二个为目标对象，将源对象中属性值拷贝到目标对象中，源和目标对象不能为空，属性名称一样方可拷贝
		BeanUtils.copyProperties(customerVo, customer);
		//主键为自增，将custId设置为空
		customer.setCustId(null);
		//针对客户来源、客户级别、客户所属行业作非空校验处理
		if(customer.getBaseDictByCustSource()!=null){
			if(StringUtils.isEmpty(customer.getBaseDictByCustSource().getDictId())){
				//将客户来源设置为空
				customer.setBaseDictByCustSource(null);
			}
		}
		if(customer.getBaseDictByCustLevel()!=null){
			if(StringUtils.isEmpty(customer.getBaseDictByCustLevel().getDictId())){
				//将客户级别设置为空
				customer.setBaseDictByCustLevel(null);
			}
		}
		if(customer.getBaseDictByCustIndustry()!=null){
			if(StringUtils.isEmpty(customer.getBaseDictByCustIndustry().getDictId())){
				//将客户所属行业设置为空
				customer.setBaseDictByCustIndustry(null);
			}
		}
		customerDao.insert(customer);
		if(customerDetailVo == null){
			customerDetailVo = new CstCustomerDetailVo();
		}
		//获取新增客户的id
		Long custId = customer.getCustId();
		//客户详细信息对象
		CstCustomerDetail cstCustomerDetail = new CstCustomerDetail();
		
		//将action传进来的customerDetailVo属性值拷贝到cstCustomerDetail中
		BeanUtils.copyProperties(customerDetailVo, cstCustomerDetail);
		//设置主键值
		cstCustomerDetail.setCustId(custId);
		//将主键设置到详细信息对象customerDetailVo中
		customerDetailDao.insert(cstCustomerDetail);
	}

	@Override
	public void updateCustomer(Long custId, CustomerVo customerVo, CstCustomerDetailVo customerDetailVo) {
		if(customerVo == null){
			//参数不合法
			//抛出异常
			throw new RuntimeException("参数不合法");
		}	
		//针对客户来源、客户级别、客户所属行业作非空校验处理
		if(customerVo.getBaseDictByCustSource()!=null){
			if(StringUtils.isEmpty(customerVo.getBaseDictByCustSource().getDictId())){
				//将客户来源设置为空
				customerVo.setBaseDictByCustSource(null);
			}
		}
		if(customerVo.getBaseDictByCustLevel()!=null){
			if(StringUtils.isEmpty(customerVo.getBaseDictByCustLevel().getDictId())){
				//将客户级别设置为空
				customerVo.setBaseDictByCustLevel(null);
			}
		}
		if(customerVo.getBaseDictByCustIndustry()!=null){
			if(StringUtils.isEmpty(customerVo.getBaseDictByCustIndustry().getDictId())){
				//将客户所属行业设置为空
				customerVo.setBaseDictByCustIndustry(null);
			}
		}
		//先从数据查询持久对象
		CstCustomer customer = customerDao.findById(custId);
		//对关键参数校验
		if(customer == null){
			throw new RuntimeException("缺少修改客户id");
		}
		
		//根据页面需求手工设置修改的值
		customer.setCustName(customerVo.getCustName());
		customer.setBaseDictByCustIndustry(customerVo.getBaseDictByCustIndustry());
		customer.setBaseDictByCustLevel(customerVo.getBaseDictByCustLevel());
		customer.setBaseDictByCustSource(customerVo.getBaseDictByCustSource());
		//...
		//对托管态对象，由于此方法受事务控制，这里不用手动执行update，当事务提交后自动执行更新操作
		customerDao.update(customer);
		
		//详细信息修改
		CstCustomerDetail customerDetail = customerDetailDao.findById(custId);
		//当页面提交了详细信息才执行更新操作
		if(customerDetail!=null && customerDetailVo!=null){
			//更新详细信息
			customerDetail.setCustAddress(customerDetailVo.getCustAddress());
			//更新图片
			customerDetail.setCustPic(customerDetailVo.getCustPic());
			//.....
			//对托管态对象，由于此方法受事务控制，这里不用手动执行update，当事务提交后自动执行更新操作
			customerDetailDao.update(customerDetail);
			
		}
		
	}

	@Override
	public CstCustomerDetail findCustomerDetailById(Long custId) {
		return customerDetailDao.findById(custId);
	}

	@Override
	public List<SaleVisit> findSaleVisitList(SaleVisitVo saleVisitVo, int firstResult, int maxResults) {
		// TODO Auto-generated method stub
		return saleVisitDao.findSaleVisitList(saleVisitVo, firstResult, maxResults);
	}

	@Override
	public Long findSaleVisitCount(SaleVisitVo saleVisitVo) {
		// TODO Auto-generated method stub
		return saleVisitDao.findSaleVisitCount(saleVisitVo);
	}

	@Override
	public List<CstCustomer> findCustomerByName(String custName) {
		CustomerVo customerVo = new CustomerVo();
		customerVo.setCustName(custName);
		return customerDao.findCustomerList(customerVo);
	}

	@Override
	public List findCustomerStatList(CustomerVo customerVo, int firstResult, int maxResults) {
		
		return customerDao.findCustomerStatList(customerVo, firstResult, maxResults);
	}

	@Override
	public Long findCustomerStatCount(CustomerVo customerVo) {
		// TODO Auto-generated method stub
		return customerDao.findCustomerStatCount(customerVo);
	}

}
