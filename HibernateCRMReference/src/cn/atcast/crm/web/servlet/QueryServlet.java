package cn.atcast.crm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.atcast.crm.domain.BaseDict;
import cn.atcast.crm.domain.CstCustomer;
import cn.atcast.crm.domain.CstLinkman;
import cn.atcast.crm.domain.QueryVo;
import cn.atcast.crm.domain.SaleVisit;
import cn.atcast.crm.service.QueryService;
import cn.atcast.crm.service.SystemService;
import cn.atcast.crm.service.impl.QueryServiceImpl;
import cn.atcast.crm.service.impl.SystemServiceImpl;

public class QueryServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求的方法名
		String method = req.getParameter("method");
		if(method == null || method.equals("") || method.equals("customerlist")){
			this.customerlist(req, resp);
		}else if(method.equals("linkmanlist")){
			this.linkmanlist(req, resp);
		}else if(method.equals("salevisitlist")){
			this.salevisitlist(req, resp);
		}
	}
	
	//客户列表
	private void customerlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
	}
	//客户拜访列表
	private void salevisitlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		QueryService queryService = new QueryServiceImpl();
		//查询客户来源列表
		SystemService systemService = new SystemServiceImpl();
		List<BaseDict> cusSourceList = systemService.findBaseDictListByType("002");
		req.setAttribute("cusSourceList", cusSourceList);
		//----------查询条件--------------
		QueryVo queryVo = new QueryVo();
		//客户名称
		String custName = req.getParameter("custName");
		
		//查询条件-客户信息
		CstCustomer cstCustomer = new CstCustomer();
		//客户名称
		cstCustomer.setCustName(custName);
		//客户来源
		String custSource = req.getParameter("custSource");
		BaseDict baseDictBySource = new BaseDict();
		baseDictBySource.setDictId(custSource);
		cstCustomer.setBaseDictBySource(baseDictBySource);
		
		queryVo.setCstCustomer(cstCustomer);
		//查询记录总数
		long total = queryService.findSaleVisitCount(queryVo);
		
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

		List<SaleVisit> list = queryService.findSaleVisitList(queryVo, firstResult, pageSize);
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
		req.getRequestDispatcher("/jsp/query/salevisitlist.jsp").forward(req, resp);
	}
	//联系人列表
	private void linkmanlist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		QueryService queryService = new QueryServiceImpl();
		
		//----------查询条件--------------
		CstLinkman query_cstLinkman = new CstLinkman();
		String lkmName = req.getParameter("lkmName");
		query_cstLinkman.setLkmName(lkmName);
		//查询记录总数
		long total = queryService.findLinkmanCount(query_cstLinkman);
		
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

		List<CstLinkman> list = queryService.findLinkmanList(query_cstLinkman, firstResult, pageSize);
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
		req.getRequestDispatcher("/jsp/query/linkmanlist.jsp").forward(req, resp);
	}

	
}
