package cn.atcast.crm.domain;


import java.util.Date;

/**
 * SaleChance generated by hbm2java
 */
public class SaleChance implements java.io.Serializable {

	private String chanId;
	private SysUser sysUser;
	private CstCustomer cstCustomer;
	private String chanName;
	private Integer chanMoney;
	private String chanType;
	private String chanSource;
	private String chanState;
	private Long chanLkmId;
	private Date chanLinktime;
	private String chanDesc;

	public SaleChance() {
	}

	public SaleChance(String chanId, CstCustomer cstCustomer, String chanName) {
		this.chanId = chanId;
		this.cstCustomer = cstCustomer;
		this.chanName = chanName;
	}

	public SaleChance(String chanId, SysUser sysUser, CstCustomer cstCustomer,
			String chanName, Integer chanMoney, String chanType,
			String chanSource, String chanState, Long chanLkmId,
			Date chanLinktime, String chanDesc) {
		this.chanId = chanId;
		this.sysUser = sysUser;
		this.cstCustomer = cstCustomer;
		this.chanName = chanName;
		this.chanMoney = chanMoney;
		this.chanType = chanType;
		this.chanSource = chanSource;
		this.chanState = chanState;
		this.chanLkmId = chanLkmId;
		this.chanLinktime = chanLinktime;
		this.chanDesc = chanDesc;
	}

	public String getChanId() {
		return this.chanId;
	}

	public void setChanId(String chanId) {
		this.chanId = chanId;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public CstCustomer getCstCustomer() {
		return this.cstCustomer;
	}

	public void setCstCustomer(CstCustomer cstCustomer) {
		this.cstCustomer = cstCustomer;
	}

	public String getChanName() {
		return this.chanName;
	}

	public void setChanName(String chanName) {
		this.chanName = chanName;
	}

	public Integer getChanMoney() {
		return this.chanMoney;
	}

	public void setChanMoney(Integer chanMoney) {
		this.chanMoney = chanMoney;
	}

	public String getChanType() {
		return this.chanType;
	}

	public void setChanType(String chanType) {
		this.chanType = chanType;
	}

	public String getChanSource() {
		return this.chanSource;
	}

	public void setChanSource(String chanSource) {
		this.chanSource = chanSource;
	}

	public String getChanState() {
		return this.chanState;
	}

	public void setChanState(String chanState) {
		this.chanState = chanState;
	}

	public Long getChanLkmId() {
		return this.chanLkmId;
	}

	public void setChanLkmId(Long chanLkmId) {
		this.chanLkmId = chanLkmId;
	}

	public Date getChanLinktime() {
		return this.chanLinktime;
	}

	public void setChanLinktime(Date chanLinktime) {
		this.chanLinktime = chanLinktime;
	}

	public String getChanDesc() {
		return this.chanDesc;
	}

	public void setChanDesc(String chanDesc) {
		this.chanDesc = chanDesc;
	}

}
