package cn.atcast.b_query;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;


public class App_hql {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(Dept.class)   
			.addClass(Employee.class)   // 测试时候使用
			.buildSessionFactory();
	}

	/* 
	 *  1)	Get/load主键查询
		2)	对象导航查询
		3)	HQL查询，  Hibernate Query language  hibernate 提供的面向对象的查询语言。
		4)	Criteria 查询，   完全面向对象的查询（Query By Criteria  ,QBC）
		5)	SQLQuery， 本地SQL查询

	 */
	@Test
	public void all() {
		
		Session session = sf.openSession();
		session.beginTransaction();
		
		//1) 主键查询 (此时不能在hibernate.cfg.xml中开启二级缓存的配置，因为开启后会先在二级缓存中找，而不会去数据库中取)
		 Dept dept=(Dept)session.get(Dept.class,1);
//		 Dept dept=(Dept)session.load(Dept.class,1);
  		 System.out.println(dept.getDeptName());
    	 System.out.println(dept.getEmps());
		//2) 对象导航查询
//		Dept dept =  (Dept) session.get(Dept.class, 1);
//		System.out.println(dept.getDeptName());
//		System.out.println(dept.getEmps());
		
		// 3)	HQL查询
		// 注意：使用hql查询的时候 auto-import="true" 要设置true，默认为true
		//  如果是false，写hql的时候，要指定类的全名  
//		Query q = session.createQuery("from Dept");
//		System.out.println(q.list());
		
		// a. 查询全部列
		//Query q = session.createQuery("from Dept");  //OK
	 	//Query q = session.createQuery("select * from Dept");  //NOK, 错误，不支持*
	//	Query q = session.createQuery("select d from Dept d");  // OK d是Dept的别名
		//System.out.println(q.list());

		// b. 查询指定的列  【返回对象数据Object[] 】
		//加入断点测试，可以选中q.list()后用watch,在Expressions面板中查看。
//		Query q = session.createQuery("select d.deptId,d.deptName from Dept d");  
//		System.out.println(q.list());
		
		// c. 查询指定的列, 自动封装为对象  【必须要提供带参数构造器】
//		Query q = session.createQuery("select new Dept(d.deptId,d.deptName) from Dept d");  
//		System.out.println(q.list());
		
		// d. 条件查询: 一个条件/多个条件and or/between and/模糊查询
		// 条件查询： 占位符
//		Query q = session.createQuery("from Dept d where deptName=?");
//		//两种方法
//		//q.setString(0, "物流部");
//		q.setParameter(0, "物流部");
//		System.out.println(q.list());
		
		// 条件查询： 命名参数
//		Query q = session.createQuery("from Dept d where deptId=:myId or deptName=:name");
//		q.setParameter("myId", 1);
//		q.setParameter("name", "物流部");
//		System.out.println(q.list());
		
//		// 范围
//		Query q = session.createQuery("from Dept d where deptId between ? and ?");
//		q.setParameter(0, 1);
//		q.setParameter(1, 3);
//		System.out.println(q.list());
		
		// 模糊
//		Query q = session.createQuery("from Dept d where deptName like ?");
//		q.setString(0, "%物%");
//		System.out.println(q.list());
		

		// e. 聚合函数统计
		//select count(*) from t_Dept 统计总记录 会统计null
		//select count(1) from t_Dept 统计总记录,效率更高。会统计null
// 		Query q = session.createQuery("select count(*) from Dept");
//		Long num = (Long) q.uniqueResult();
//		System.out.println(num);
		
		// f. 分组查询
		//-- 统计t_employee表中，每个部门的人数
		//数据库写法：SELECT dept_id,COUNT(*) FROM t_employee GROUP BY dept_id;
		// HQL写法
//		Query q = session.createQuery("select e.dept, count(*) from Employee e group by e.dept");
// 		Query q = session.createQuery("select e.dept, count(*) from Employee e group by e.dept having count(*)>1");
		//加入断点测试，可以选中q.list()后用watch,在Expressions面板中查看。
//      System.out.println(q.list());
		
//		session.getTransaction().commit();
//		session.close();
	}
	 
	// g. 连接查询
	@Test
	public void join() {
		
		Session session = sf.openSession();
		session.beginTransaction();
	
		//1) 内连接   【映射已经配置好了关系，关联的时候，直接写对象的属性即可】
		/* 需求:显示员工名称，薪水，部门
		 * sql方法一：
		 *  select e.empName,e.salary,d.deptName from t_dept d, t_employee e where d.deptId=e.dept_id;
			
		 * sql方法二：
			select e.empName,e.salary,d.deptName from t_dept d inner join t_employee e on d.deptId=e.dept_id;
		 */
	
//			Query q = session.createQuery("from Dept d inner join d.emps");
//			q.list();
//		//加入断点，watch-->数组中第一个元素是Dept,第二个元素是Employee
//		List<Object[]> list=q.list();
//		for(int i=0;i<list.size();i++){
//			Object[] obj=list.get(0);
//			Dept dep=(Dept)obj[0];
//			System.out.println(dep.getDeptName());
//		}
		  
		//2) 左外连接(解除主外键约束条件，在部门表加一个没有员工的新部门，在员工表加一个没有部门的新员工进行测试)
		/* 需求:显示部门，以及部门下的员工，如果部门下没有员工用null表示 （始终显示左表t_dept信息，）
		 * select e.empName,e.salary,d.deptName from t_dept d left join t_employee e on d.deptId=e.dept_id;
		   select e.empName,e.salary,d.deptName from t_employee e left join t_dept d on d.deptId=e.dept_id;
		 */
		// 查询出来的每一项为 object[]数组，左表存在object[0]中，右表存在object[1]中
 		//Query q = session.createQuery("from Dept d left join d.emps"); //左表为dept部门表，将部门表中的信息全保留。
//		Query q = session.createQuery("from Employee e left join e.dept"); //左表为employee员工表，将员工表中的信息全保留。
// 		q.list();
		//3) 右外连接
		/* 始终显示right join后面表的数据
		 * select e.empName,e.salary,d.deptName from t_employee e  right join t_dept d 
			on d.deptId=e.dept_id;
		 */
 		Query q = session.createQuery("from Employee e right join e.dept");
 		q.list();
		
		session.getTransaction().commit();
		session.close();
	}
	/*
	 迫切连接,则是表示在做连接的同时，对于关联的表的对象也一并取出，进行初始化。有效减少立即查询方式的select查询
	 内连接和迫切内连接的区别:
		    * 内连接:发送就是内连接的语句,封装的时候将每条记录封装到一个Object[]数组中,最后得到一个List<Object[]>.
		    * 迫切内连接：发送的也是内连接的语句,在join后添加一个fetch关键字,Hibernate会将每条数据封装到对象中,最后List<Customer>. 需要去掉重复值.
	*/
	// g. 连接查询 - 迫切连接
	/* Employee.hbm.xml
	 * <many-to-one name="dept" column="dept_id" class="Dept" fetch="select/join"></many-to-one>
	 * fetch="select",另外发送一条select语句抓取当前对象关联实体或集合
	   fetch="join",hibernate会通过select语句使用外连接来加载其关联实体或集合
		此时lazy会失效
	 */
	@Test
	public void fetch() {
		Session session = sf.openSession();
		session.beginTransaction();
		//1)fetch抓取策略
		Employee employee = (Employee)session.load(Employee.class, 1);
		System.out.println("Employee.empName=" + employee.getEmpName());
		//另外发送一条select语句抓取当前对象关联实体或集合
		System.out.println("Dept.name=" + employee.getDept().getDeptName());
	
		
		//2)  //迫切外连接,则是表示在做连接的同时，对于关联的表的对象也一并取出，进行初始化。 
		//迫切左外连接(使用fetch, 会把右表employee的数据，填充到左表dept对象中)
		//Query q2 = session.createQuery("from Dept d left join fetch d.emps");//左表为dept部门表，将部门表中的信息全保留。
		//q2.list();
		//3) 迫切内连接
//		Query q1 = session.createQuery("from Dept d inner join fetch d.emps");
//		q1.list();
		
		session.getTransaction().commit();
		session.close();
	}
	
	// HQL查询优化
	@Test
	public void hql_other() {
		Session session = sf.openSession();
		session.beginTransaction();
		// HQL写死
 		//Query q = session.createQuery("from Dept d where deptId < 4 ");
		
		// HQL 放到映射文件中（放在那个映射文件都可以，但现在查询的是部门，就放在Dept.hbm.xml中）
		Query q = session.getNamedQuery("getAllDept");
		q.setParameter(0, 4);
		System.out.println(q.list());
		
		session.getTransaction().commit();
		session.close();
	}
	/*
	一般我们进行web开发都会碰到多条件查询。例如根据条件搜索。条件的多少 逻辑关系 是or 还是and等等。我们要根据这些条件来拼写查询语句。
	但是有了离线查询这些都不是问题，我们可以使用DetachedCriteria来构造查询条件，然后将这个DetachedCriteria作为方法调用参数传递给业务层对象。
	而业务层对象获得DetachedCriteria之后，可以在session范围内直接构造Criteria，进行查询。
	就此，查询语句的构造完全被搬离到web层实现，而业务层则只负责完成持久化和查询的封装即可。
	换句话说，业务层代码是不变化的。我们不必为了查询条件的变化而去频繁改动查询语句了。。
	*/
	
	@Test
	/**
	 * 离线条件查询:DetachedCriteria(SSH整合经常使用.).
	 * * 可以脱离session设置参数.
	 */
	public void detached(){
		// 获得一个离线条件查询的对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Employee.class);
		detachedCriteria.add(Restrictions.eq("empName","张三"));
		
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		List<Employee> list = detachedCriteria.getExecutableCriteria(session).list();// 离线条件查询对象与session绑定.
		for (Employee employee : list) {
			System.out.println(employee.getEmpName());
		}
		tx.commit();
		session.close();
	}
	//hql不太灵活，复杂sql还是要用原生态的sql语句
	@Test
	public void sql(){
		Session session=sf.openSession();
		session.beginTransaction();
		String sql="select * from t_dept";
		//通过部门表查询出所有部门记录并封装成部门对象返回
		SQLQuery q=session.createSQLQuery(sql)
				.addEntity(Dept.class);
		Dept dept=(Dept)q.list().get(0);
		session.getTransaction().commit();
		session.close();
	}

}
