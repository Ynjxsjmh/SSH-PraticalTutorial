package cn.atcast.d_myaop;

import org.springframework.stereotype.Component;

@Component  // 加入IOC容器 //关注点代码，程序不想做
public class Aop {
	public void begin(){
		System.out.println("开启事务");
	}
	
	public void commite(){
		System.out.println("提交事务");
	}
}
