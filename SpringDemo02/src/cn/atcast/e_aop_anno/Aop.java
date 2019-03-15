package cn.atcast.e_aop_anno;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Component
@Aspect  // 指定当前类为切面类
public class Aop {
	 /*
	//方法一:
	@Before("execution(* cn.atcast.e_aop_anno.*.*(..))")
	public void begin(){
		System.out.println("开始事务/异常");
	}
	
	// 后置/最终通知：在执行目标方法之后执行  【无论是否出现异常最终都会执行】
	@After("execution(* cn.atcast.e_aop_anno.*.*(..))")
	public void after(){
		System.out.println("提交事务/关闭");
	}
 	*/
	 
	 //方法二：由于方法一要在所有方法都要写拦截的逻辑execution(* cn.atcast.e_aop_anno.*.*(..))，所以抽取出来。
	// 指定切入点表单式： 拦截哪些方法； 即为哪些类生成代理对象 （现在拦截的是此包下的所有类的所有方法。）
	@Pointcut("execution(* cn.atcast.e_aop_anno.*.*(..))")
	public void pointCut_(){  //方法名随意指定
	}
	
	// 前置通知 : 在执行目标方法之前执行
	@Before("pointCut_()")
	public void begin(){
		System.out.println("开始事务/异常");
	}
	
	// 后置/最终通知：在执行目标方法之后执行  【无论是否出现异常最终都会执行】
	@After("pointCut_()")
	public void after(){
		System.out.println("提交事务/关闭");
	}
	
	
	// 返回后通知： 在调用目标方法结束后执行 【出现异常不执行】
	@AfterReturning("pointCut_()")
	public void afterReturning() {
		System.out.println("afterReturning()");
	}
	
	// 异常通知： 当目标方法执行异常时候执行此关注点代码
	@AfterThrowing("pointCut_()")
	public void afterThrowing(){
		System.out.println("afterThrowing()");
	}
	
	// 环绕通知：环绕目标方式执行
	@Around("pointCut_()")
	public void around(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("环绕前....");
		pjp.proceed();  // 执行目标方法
		System.out.println("环绕后....");
	}
	 
}
