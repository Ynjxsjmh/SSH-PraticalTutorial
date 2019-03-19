package cn.atcast.crm.domain;

/**
 * 
 * <p>Title: QueryVo</p>
 * <p>Description:查询对象，用于封装查询条件 </p>
 */
public class QueryVo {
	
	//客户信息
	private CstCustomer cstCustomer;
	//用户信息
	private SysUser sysUser;
	public CstCustomer getCstCustomer() {
		return cstCustomer;
	}
	public void setCstCustomer(CstCustomer cstCustomer) {
		this.cstCustomer = cstCustomer;
	}
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	
	

}
