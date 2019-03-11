package com.atcast.domain;

/**
 * 瀹㈡埛鐨凧avaBean
 * 
 * @author Administrator
 */
public class Customer {
	/**
	 * `cust_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '瀹㈡埛缂栧彿(涓婚敭)',
	 * `cust_name` varchar(32) NOT NULL COMMENT '瀹㈡埛鍚嶇О(鍏徃鍚嶇О)', `cust_user_id`
	 * bigint(32) DEFAULT NULL COMMENT '璐熻矗浜篿d', `cust_create_id` bigint(32) DEFAULT
	 * NULL COMMENT '鍒涘缓浜篿d', `cust_source` varchar(32) DEFAULT NULL COMMENT
	 * '瀹㈡埛淇℃伅鏉ユ簮', `cust_industry` varchar(32) DEFAULT NULL COMMENT '瀹㈡埛鎵�灞炶涓�',
	 * `cust_level` varchar(32) DEFAULT NULL COMMENT '瀹㈡埛绾у埆', `cust_linkman`
	 * varchar(64) DEFAULT NULL COMMENT '鑱旂郴浜�', `cust_phone` varchar(64) DEFAULT
	 * NULL COMMENT '鍥哄畾鐢佃瘽', `cust_mobile` varchar(16) DEFAULT NULL COMMENT
	 * '绉诲姩鐢佃瘽',
	 */

	// 浠ュ悗浣跨敤鍖呰绫伙紝榛樿鍊兼槸null
	private Long cust_id;
	private String cust_name;
	private Long cust_user_id;
	private Long cust_create_id;
	private String cust_source;
	private String cust_industry;
	private String cust_level;
	private String cust_linkman;
	private String cust_phone;
	private String cust_mobile;

	public Long getCust_id() {
		return cust_id;
	}

	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}

	public String getCust_name() {
		return cust_name;
	}

	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public Long getCust_user_id() {
		return cust_user_id;
	}

	public void setCust_user_id(Long cust_user_id) {
		this.cust_user_id = cust_user_id;
	}

	public Long getCust_create_id() {
		return cust_create_id;
	}

	public void setCust_create_id(Long cust_create_id) {
		this.cust_create_id = cust_create_id;
	}

	public String getCust_source() {
		return cust_source;
	}

	public void setCust_source(String cust_source) {
		this.cust_source = cust_source;
	}

	public String getCust_industry() {
		return cust_industry;
	}

	public void setCust_industry(String cust_industry) {
		this.cust_industry = cust_industry;
	}

	public String getCust_level() {
		return cust_level;
	}

	public void setCust_level(String cust_level) {
		this.cust_level = cust_level;
	}

	public String getCust_linkman() {
		return cust_linkman;
	}

	public void setCust_linkman(String cust_linkman) {
		this.cust_linkman = cust_linkman;
	}

	public String getCust_phone() {
		return cust_phone;
	}

	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	public String getCust_mobile() {
		return cust_mobile;
	}

	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}

	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_user_id=" + cust_user_id
				+ ", cust_create_id=" + cust_create_id + ", cust_source=" + cust_source + ", cust_industry="
				+ cust_industry + ", cust_level=" + cust_level + ", cust_linkman=" + cust_linkman + ", cust_phone="
				+ cust_phone + ", cust_mobile=" + cust_mobile + "]";
	}

	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result + ((cust_id == null) ? 0 : cust_id.hashCode());
	// result = prime * result
	// + ((cust_name == null) ? 0 : cust_name.hashCode());
	// return result;
	// }
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (cust_id == null) {
			if (other.cust_id != null)
				return false;
		} else if (!cust_id.equals(other.cust_id))
			return false;
		if (cust_name == null) {
			if (other.cust_name != null)
				return false;
		} else if (!cust_name.equals(other.cust_name))
			return false;
		return true;
	}
}