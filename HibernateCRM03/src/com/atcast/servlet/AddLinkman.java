package com.atcast.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atcast.domain.Linkman;
import com.atcast.service.LinkmanService;

/**
 * 添加联系人
 * 
 * @author Administrator
 */
public class AddLinkman extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先解决中文乱码
		request.setCharacterEncoding("UTF-8");
		// 接收数据
		Map<String, String[]> map = request.getParameterMap();
		// 先把客户的id获取到
		String scust_id = map.get("cust_id")[0];
		// 转换
		Long cust_id = Long.parseLong(scust_id);

		// 可以封装数据了
		Linkman man = new Linkman();
		try {
			// 封装数据
			BeanUtils.populate(man, map);

			// 调用业务层，保存联系人
			new LinkmanService().save(man, cust_id);

			System.out.println("保存联系人成功了...");
		} catch (Exception e) {
			e.printStackTrace();
		}

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
