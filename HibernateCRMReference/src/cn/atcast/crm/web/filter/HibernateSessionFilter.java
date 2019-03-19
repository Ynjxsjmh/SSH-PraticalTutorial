package cn.atcast.crm.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.Session;

import cn.atcast.crm.util.HibernateUtil;


public class HibernateSessionFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		Session session = HibernateUtil.getCurrentSession();
		session.close();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}

