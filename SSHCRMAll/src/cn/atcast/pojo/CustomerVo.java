package cn.atcast.pojo;

import cn.atcast.domain.CstCustomer;

/**
 * 包装对象，将它作为模型对象
 * 
 * @author Administrator
 *
 */
public class CustomerVo extends CstCustomer {

	// 客户统计分类
	private String customerClass;

	// 分页参数
	private int page;// 页码
	private int rows;// 每页记录

	private CstCustomer customer;

	// 搜索关键字名称
	private String q;

	public String getCustomerClass() {
		return customerClass;
	}

	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	// 通过属性接收详细信息
	private CstCustomerDetailVo customerDetailVo;

	public CstCustomerDetailVo getCustomerDetailVo() {
		return customerDetailVo;
	}

	public void setCustomerDetailVo(CstCustomerDetailVo customerDetailVo) {
		this.customerDetailVo = customerDetailVo;
	}

	public CstCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CstCustomer customer) {
		this.customer = customer;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// 客户id
	private Long custId;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

}
