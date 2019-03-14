package cn.atcast.b_create_obj;

// 工厂，创建对象
public class ObjectFactory {
	// ObjectFactory f1=new ObjectFactory(); f1.getInstance();->user
	// 实例方法创建对象
	public User getInstance() {
		return new User(100, "工厂：调用实例方法");
	}

	// 静态方法创建对象
	// ObjectFactory.getStaticInstance()->user
	public static User getStaticInstance() {
		return new User(101, "工厂：调用静态方法");
	}
}
