package cn.atcast.crm.domain;

// Generated 2016-2-19 14:33:20 by Hibernate Tools 4.0.0

import java.util.Date;

/**
 * SaleVisit generated by hbm2java
 */
public class SaleVisit implements java.io.Serializable {

	private String visitId;
	
	//客户
	private CstCustomer cstCustomer;
	//用户
	private SysUser sysUser;
	//被拜访人
	private String  visitInterviewee;
	
	private Date visitTime;
	private String visitAddr;
	private String visitDetail;
	private Date visitNexttime;

	public SaleVisit() {
	}

	
	public String getVisitId() {
		return this.visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}

	
	public String getVisitInterviewee() {
		return visitInterviewee;
	}


	public void setVisitInterviewee(String visitInterviewee) {
		this.visitInterviewee = visitInterviewee;
	}


	public CstCustomer getCstCustomer() {
		return this.cstCustomer;
	}

	public void setCstCustomer(CstCustomer cstCustomer) {
		this.cstCustomer = cstCustomer;
	}

	public SysUser getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public Date getVisitTime() {
		return this.visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getVisitAddr() {
		return this.visitAddr;
	}

	public void setVisitAddr(String visitAddr) {
		this.visitAddr = visitAddr;
	}

	public String getVisitDetail() {
		return this.visitDetail;
	}

	public void setVisitDetail(String visitDetail) {
		this.visitDetail = visitDetail;
	}

	public Date getVisitNexttime() {
		return this.visitNexttime;
	}

	public void setVisitNexttime(Date visitNexttime) {
		this.visitNexttime = visitNexttime;
	}

}