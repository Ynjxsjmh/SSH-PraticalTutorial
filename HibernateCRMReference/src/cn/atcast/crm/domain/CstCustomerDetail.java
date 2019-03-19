package cn.atcast.crm.domain;

 
public class CstCustomerDetail implements java.io.Serializable {

	private Long custId;
	private String custRegion;
	private String custZip;
	private String custAddress;
	private String custFax;
	private String custWebsite;
	private String custLicence;
	private String custCorporation;
	private Long custCapital;
	private String custBank;
	private String custPic;
	private String custMemo;

	public CstCustomerDetail() {
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustRegion() {
		return this.custRegion;
	}

	public void setCustRegion(String custRegion) {
		this.custRegion = custRegion;
	}

	public String getCustZip() {
		return this.custZip;
	}

	public void setCustZip(String custZip) {
		this.custZip = custZip;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustFax() {
		return this.custFax;
	}

	public void setCustFax(String custFax) {
		this.custFax = custFax;
	}

	public String getCustWebsite() {
		return this.custWebsite;
	}

	public void setCustWebsite(String custWebsite) {
		this.custWebsite = custWebsite;
	}

	public String getCustLicence() {
		return this.custLicence;
	}

	public void setCustLicence(String custLicence) {
		this.custLicence = custLicence;
	}

	public String getCustCorporation() {
		return this.custCorporation;
	}

	public void setCustCorporation(String custCorporation) {
		this.custCorporation = custCorporation;
	}

	public Long getCustCapital() {
		return this.custCapital;
	}

	public void setCustCapital(Long custCapital) {
		this.custCapital = custCapital;
	}

	public String getCustBank() {
		return this.custBank;
	}

	public void setCustBank(String custBank) {
		this.custBank = custBank;
	}

	public String getCustPic() {
		return custPic;
	}

	public void setCustPic(String custPic) {
		this.custPic = custPic;
	}

	public String getCustMemo() {
		return this.custMemo;
	}

	public void setCustMemo(String custMemo) {
		this.custMemo = custMemo;
	}

}
