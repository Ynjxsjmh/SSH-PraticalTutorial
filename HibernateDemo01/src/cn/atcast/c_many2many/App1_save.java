package cn.atcast.c_many2many;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

public class App1_save {
	
	private static SessionFactory sf;
	static {
		sf = new Configuration()
			.configure()
			.addClass(Project.class)   
			.addClass(Developer.class)   // 测试时候使用
			.buildSessionFactory();
	}

	// 1. 多对多，保存  【只能通过一方维护另外一方，不能重复维护！】
	@Test
	public void save() {
		Session session = sf.openSession();
		session.beginTransaction();
		
		/*
		 * 模拟数据： 
			电商系统（曹吉，王春）
			OA系统（王春，老张）
		 */
		// 创建项目对象
		Project prj_ds = new Project();
		prj_ds.setPrj_name("电商系统");
		Project prj_oa = new Project();
		prj_oa.setPrj_name("OA系统");
		
		// 创建员工对象
		Developer dev_cj = new Developer();
		dev_cj.setD_name("曹吉");
		Developer dev_wc = new Developer();
		dev_wc.setD_name("王春");
		Developer dev_lz = new Developer();
		dev_lz.setD_name("老张");
		
		// 关系 【项目方】通过项目维护员工
		prj_ds.getDevelopers().add(dev_cj);
		prj_ds.getDevelopers().add(dev_wc); // 电商系统（曹吉，王春）
		prj_oa.getDevelopers().add(dev_wc);
		prj_oa.getDevelopers().add(dev_lz); // OA系统（王春，老张）
		
		//通过员工维护项目，不能重复维护！因为通过项目维护员工时会在中间表中插入一条记录，而再通过员工去维护项目，相当于在中间表中又插入一条记录。
		//dev_cj.getProjects().add(prj_ds);
		
		//方法一：直接保存员工和项目
		/*
		// 保存
		session.save(dev_cj);
		session.save(dev_wc);
		session.save(dev_lz);
	
		session.save(prj_ds);
		session.save(prj_oa);   // 必须要设置级联保存 
		*/
		
		//方法二：使用级联操作
		session.save(prj_ds);
		session.save(prj_oa);  
		
		session.getTransaction().commit();
		session.close();
	}

}
