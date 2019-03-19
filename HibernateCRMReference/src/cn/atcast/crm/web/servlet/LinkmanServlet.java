package cn.atcast.crm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.atcast.crm.domain.CstLinkman;
import cn.atcast.crm.service.LinkmanService;
import cn.atcast.crm.service.impl.LinkmanServiceImpl;

public class LinkmanServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//获取请求的方法名
		String method = req.getParameter("method");
		if(method == null || method.equals("") || method.equals("add")){
			//转发到添加客户页面
			req.getRequestDispatcher("/jsp/linkman/add.jsp").forward(req, resp);
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
	//添加联系人提交
	private void addsubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//联系人信息
		String custId = req.getParameter("custId");//所属客户
		String lkmName = req.getParameter("lkmName");
		String lkmPhone = req.getParameter("lkmPhone");
		if(lkmPhone!=null && lkmPhone.trim().equals("")){
			lkmPhone = null;
		}

		//添加联系人
		CstLinkman cstLinkman = new CstLinkman();
		cstLinkman.setLkmName(lkmName);
		cstLinkman.setLkmPhone(lkmPhone);
		//调用新的 service接口
		try {
			LinkmanService linkmanService = new LinkmanServiceImpl();
			linkmanService.insertLinkman(Long.parseLong(custId), cstLinkman);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
	}
	
	//查询客户提交
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		LinkmanService linkmanService = new LinkmanServiceImpl();
		
		//----------查询条件--------------
		CstLinkman query_cstLinkman = new CstLinkman();
		String lkmName = req.getParameter("lkmName");
		query_cstLinkman.setLkmName(lkmName);
		//查询记录总数
		long total = linkmanService.findLinkmanCount(query_cstLinkman);
		
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

		List<CstLinkman> list = linkmanService.findLinkmanList(query_cstLinkman, firstResult, pageSize);
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
		req.getRequestDispatcher("/jsp/linkman/list.jsp").forward(req, resp);
	}
	//修改联系人页面
	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//接收请求
		Long lkmId = Long.parseLong(req.getParameter("lkmId"));
		//调用service查询联系人信息
		LinkmanService linkmanService = new LinkmanServiceImpl();
		//查询联系人基本信息
		CstLinkman cstLinkman = linkmanService.findLinkmanById(lkmId);
		//存储到request域在页面展示
		req.setAttribute("linkman", cstLinkman);
		req.getRequestDispatcher("/jsp/linkman/edit.jsp").forward(req, resp);
		
	}
	
	//修改联系 人提交 
	private void editsubmit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//客户id
		Long custId = Long.parseLong(req.getParameter("custId"));
		//联系 人id
		Long lkmId = Long.parseLong(req.getParameter("lkmId"));
		//联系人信息
		String lkmName = req.getParameter("lkmName");
		String lkmPhone = req.getParameter("lkmPhone");
		
		//添加联系人
		CstLinkman cstLinkman = new CstLinkman();
		cstLinkman.setLkmName(lkmName);
		cstLinkman.setLkmPhone(lkmPhone);
		//调用新的 service接口
		try {
			LinkmanService linkmanService = new LinkmanServiceImpl();
			linkmanService.updateLinkman(custId, lkmId, cstLinkman);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//失败
			req.getRequestDispatcher("/jsp/error.jsp").forward(req, resp);
		}
		
		
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
		
	}
	
	//删除联系人
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//接收请求
		Long lkmId = Long.parseLong(req.getParameter("lkmId"));
		
		//调用service删除联系人信息
		LinkmanService linkmanService = new LinkmanServiceImpl();
		linkmanService.deleteLinkman(lkmId);
		
		//成功
		req.getRequestDispatcher("/jsp/success.jsp").forward(req, resp);
	}
}
