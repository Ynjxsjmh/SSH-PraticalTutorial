package com.atcast.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.atcast.domain.Customer;
import com.atcast.service.CustomerService;

/**
 * 添加客户的控制器
 * @author Administrator
 */
public class SaveCustomer extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收请求的参数
		request.setCharacterEncoding("UTF-8");
		Map<String, String[]> map = request.getParameterMap();
		// 封装数据，使用BeanUtils工具，到入jar包
		Customer c = new Customer();
		try {
			// 封装数据
			BeanUtils.populate(c, map);
			// 调用业务层
			new CustomerService().saveCustomer(c);
			
			System.out.println("添加客户成功了...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
