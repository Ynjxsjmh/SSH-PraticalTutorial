package cn.atcast.crm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.atcast.crm.domain.BaseDict;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.CstCustomerDetail;
import cn.atcast.crm.service.CustomerService;
import cn.atcast.crm.service.SystemService;
import cn.atcast.crm.service.impl.CustomerServiceImpl;
import cn.atcast.crm.service.impl.SystemServiceImpl;

public class CustomerServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求的方法名
		String method = req.getParameter("method");
		if(method == null || method.equals("") || method.equals("add")){
			this.add(req, resp);
			
		}else if(method.equals("addsubmit")){
			this.addsubmit(req, resp);
		}else if(method.equals("list")){
			this.list(req, resp);
		}else if(method.equals("edit")){
			this.edit(req, resp);
		}else if(method.equals("editsubmit")){
			this.editsubmit(req, resp);
		}else if(method.equals("delete")){
			this.delete(req, resp);
		}
	}
	
	//添加客户页面
	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//查询客户来源列表
		SystemService systemService = new SystemServiceImpl();
		List<BaseDict> cusSourceList = systemService.findBaseDictListByType("002");
		req.setAttribute("cusSourceList", cusSourceList);
		//成功
		req.getRequestDispatcher("/jsp/customer/add.jsp").forward(req, resp);
	}
	
	//添加客户提交
	private void addsubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//客户信息
		String custName = req.getParameter("custName");//客户名称
		String custLevel = req.getParameter("custLevel");//客户级别
		
		String custLinkman = req.getParameter("custLinkman");//联系人
		String custPhone = req.getParameter("custPhone");//固定电话
		String custMobile = req.getParameter("custMobile");//移动电话
		
		//客户详细信息
		String custAddress = req.getParameter("custAddress");//联系地址
		String custZip = req.getParameter("custZip");//邮政编码
		
		CustomerService customerService = new CustomerServiceImpl();
		//客户信息
		CstCustomer cstCustomer = new CstCustomer();
		cstCustomer.setCustName(custName);
		//客户来源
		String custSource = req.getParameter("custSource");//信息来源
		BaseDict baseDictBysource = new BaseDict();
		baseDictBysource.setDictId(custSource);
		cstCustomer.setBaseDictBySource(baseDictBysource);
		
		cstCustomer.setCustPhone(custPhone);
		cstCustomer.setCustLinkman(custLinkman);
		cstCustomer.setCustMobile(custMobile);
		
		//客户详细信息
		CstCustomerDetail cstCustomerDetail = new CstCustomerDetail();
		cstCustomerDetail.setCustAddress(custAddress);
		cstCustomerDetail.setCustZip(custZip);
		
		//调用新的 service接口
		try {
			customerService.insertCustomer(cstCustomer,cstCustomerDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
	}
	//修改客户显示
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//接收请求
		Long custId = Long.parseLong(req.getParameter("custId"));
		//调用service查询客户信息
		CustomerService customerService = new CustomerServiceImpl();
		//查询客户基本信息
		CstCustomer cstCustomer = customerService.findCustomerById(custId);
		//查询客户详细信息
		CstCustomerDetail customerDetail = customerService.findCstCustomerDetailById(custId);
		//存储到request域在页面展示
		req.setAttribute("customer", cstCustomer);
		req.setAttribute("customerDetail", customerDetail);
		req.getRequestDispatcher("/jsp/customer/edit.jsp").forward(req, resp);
		
	}
	
	//修改客户提交 
	private void editsubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//客户id
		Long custId = Long.parseLong(req.getParameter("custId"));
		//客户信息
		String custName = req.getParameter("custName");//客户名称
		String custLevel = req.getParameter("custLevel");//客户级别
		String custSource = req.getParameter("custSource");//信息来源
		String custLinkman = req.getParameter("custLinkman");//联系人
		String custPhone = req.getParameter("custPhone");//固定电话
		String custMobile = req.getParameter("custMobile");//移动电话
		
		//客户详细信息
		String custAddress = req.getParameter("custAddress");//联系地址
		String custZip = req.getParameter("custZip");//邮政编码
		
		CustomerService customerService = new CustomerServiceImpl();
		//客户信息
		CstCustomer cstCustomer = new CstCustomer();
		cstCustomer.setCustName(custName);
//		cstCustomer.setCustLevel(custLevel);
//		cstCustomer.setCustSource(custSource);
		cstCustomer.setCustPhone(custPhone);
		cstCustomer.setCustLinkman(custLinkman);
		cstCustomer.setCustMobile(custMobile);
		
		//客户详细信息
		CstCustomerDetail cstCustomerDetail = new CstCustomerDetail();
		cstCustomerDetail.setCustAddress(custAddress);
		cstCustomerDetail.setCustZip(custZip);
		
		//调用新的 service接口
		try {
			customerService.updateCustomer(custId,cstCustomer,cstCustomerDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
		
	}
	
	//删除客户
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//接收请求
		String custId = req.getParameter("custId");
		
		//调用service删除客户信息
		CustomerService customerService = new CustomerServiceImpl();
		customerService.deleteCustomer(Long.parseLong(custId));
		
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
	}
	//查询客户提交
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CustomerService customerService = new CustomerServiceImpl();

		//----------查询条件--------------
		CstCustomer query_cstCustomer = new CstCustomer();
		String custName = req.getParameter("custName");
		query_cstCustomer.setCustName(custName);
		//查询记录总数
		long total = customerService.findCustomerCount(query_cstCustomer);
		
		//-----------分页参数-------------
	
		//每页显示个数
		String pageSizeString = req.getParameter("pageSize");
		int pageSize = Integer.parseInt(pageSizeString == null?"15":pageSizeString);
		//计算总页数
		Double num = Math.ceil(total*1.0/pageSize);
		int totalPage = num.intValue();
		//当前页码
		String pageString = req.getParameter("page");
		int page = Integer.parseInt(pageString == null||pageString.equals("")?"1":pageString);
		if(page<=0){
			page = 1;
		}
		if(page>totalPage){
			page = totalPage;
		}
		//根据分页参数计算出起始记录下标 
		int firstResult = pageSize * (page - 1);

		List<CstCustomer> list = customerService.findCustomerList(query_cstCustomer, firstResult, pageSize);
		//当前页码
		req.setAttribute("page", page);
		//总页数
		req.setAttribute("totalPage", totalPage);
		//每页显示个数
		req.setAttribute("pageSize", pageSize);
		//总数
		req.setAttribute("total", total);
		//列表
		req.setAttribute("list", list);
		//成功
		req.getRequestDispatcher("/jsp/customer/list.jsp").forward(req, resp);
	}

}
