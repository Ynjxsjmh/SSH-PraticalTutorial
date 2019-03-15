package cn.atcast.d_myaop1;

import org.springframework.stereotype.Component;

@Component  // 加入IOC容器  （切面）
public class Aop {

	// 重复执行的代码
	public void begin(){
		System.out.println("开始事务/异常");
	}
	public void commite(){
		System.out.println("提交事务/关闭");
	}
}
