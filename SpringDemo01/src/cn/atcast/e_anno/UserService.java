package cn.atcast.e_anno;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Component("userService")  // userService加入ioc容器
//@Component  //在控制层、持久层、业务层都使用@Component,不容易区分。
@Service // 表示业务逻辑层的组件
public class UserService {

	// 会从IOC容器中找userDao对象，注入到当前字段
	/*
	 * <bean id="" class=""> 
	 * 	<property name="userDao" ref="userDao"/>  @Resource相当于这里的配置 
	 * </bean>
	 */

	// @Resource(name = "userDao") //根据名称查找（优先使用）
	@Autowired // 根据类型查找
	private UserDao userDao;

	// public void setUserDao(UserDao userDao) {
	// 	   this.userDao = userDao;
	// }

	public void save() {
		userDao.save();
	}
}
