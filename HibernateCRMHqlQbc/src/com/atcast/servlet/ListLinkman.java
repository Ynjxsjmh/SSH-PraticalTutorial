package com.atcast.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.atcast.domain.Linkman;
import com.atcast.service.LinkmanService;

/**
 * 查询所有的联系人
 * 
 * @author Administrator
 */
public class ListLinkman extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 接收请求的参数
		request.setCharacterEncoding("UTF-8");
		String lkmName = request.getParameter("lkmName");

		// 先创建离线条件查询的对象，脱离Session对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Linkman.class);
		// 添加查询的条件
		if (lkmName != null && !lkmName.trim().isEmpty()) {
			// 拼接查询的条件
			criteria.add(Restrictions.like("lkm_name", "%" + lkmName + "%"));
		}

		// 调用业务层，传递DetachedCriteria对象
		List<Linkman> mans = new LinkmanService().findAll(criteria);
		request.setAttribute("mans", mans);
		request.getRequestDispatcher("/jsp/linkman/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
