package cn.atcast.crm.domain;

import java.util.HashSet;
import java.util.Set;

public class CstCustomer implements java.io.Serializable {

	private Long custId;
	private String custName;
	private String custLevel;
	private BaseDict baseDictBySource;
	private Long custUserId;
	private Long custCreateId;
	private String custIndustry;
	private String custLinkman;
	private String custPhone;
	private String custMobile;

	// 客户拜访记录
	private Set saleVisits = new HashSet(0);

	// 一对多配置，一个客户多个联系人
	private Set cstLinkmans = new HashSet(0);
	// 一对多配置，一个客户多个商机
	// private Set saleChances = new HashSet(0);

	private CstCustomerDetail cstCustomerDetail;

	public String getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(String custLevel) {
		this.custLevel = custLevel;
	}

	public BaseDict getBaseDictBySource() {
		return baseDictBySource;
	}

	public void setBaseDictBySource(BaseDict baseDictBySource) {
		this.baseDictBySource = baseDictBySource;
	}

	public Set getCstLinkmans() {
		return cstLinkmans;
	}

	public Set getSaleVisits() {
		return saleVisits;
	}

	public void setSaleVisits(Set saleVisits) {
		this.saleVisits = saleVisits;
	}

	public void setCstLinkmans(Set cstLinkmans) {
		this.cstLinkmans = cstLinkmans;
	}

	public Long getCustId() {
		return this.custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Long getCustUserId() {
		return this.custUserId;
	}

	public void setCustUserId(Long custUserId) {
		this.custUserId = custUserId;
	}

	public Long getCustCreateId() {
		return this.custCreateId;
	}

	public void setCustCreateId(Long custCreateId) {
		this.custCreateId = custCreateId;
	}

	public String getCustIndustry() {
		return this.custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustLinkman() {
		return custLinkman;
	}

	public void setCustLinkman(String custLinkman) {
		this.custLinkman = custLinkman;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public CstCustomerDetail getCstCustomerDetail() {
		return cstCustomerDetail;
	}

	public void setCstCustomerDetail(CstCustomerDetail cstCustomerDetail) {
		this.cstCustomerDetail = cstCustomerDetail;
	}

}
