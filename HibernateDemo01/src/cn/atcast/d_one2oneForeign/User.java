package cn.atcast.d_one2oneForeign;

// 用户
public class User {

	private int userId;
	private String userName;
	// 用户与身份证信息， 一对一关系
	private IdCard idCard;

	public IdCard getIdCard() {
		return idCard;
	}

	public void setIdCard(IdCard idCard) {
		this.idCard = idCard;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
