package cn.atcast.e_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

//@Component("userAction")  // 加入IOC容器
//@Component   //更简化的写法
@Controller // 控制层的组件
public class UserAction {

	// @Resource(name = "userService")
	@Autowired
	private UserService userService;

	// public void setUserService(UserService userService) {
	// this.userService = userService;
	// }

	public String execute() {
		userService.save();
		return null;
	}
}
