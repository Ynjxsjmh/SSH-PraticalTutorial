package cn.atcast.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.atcast.domain.CstCustomer;
import cn.atcast.domain.CstCustomerDetail;
import cn.atcast.pojo.CstCustomerDetailVo;
import cn.atcast.pojo.CustomerVo;
import cn.atcast.service.CustomerService;
import cn.atcast.util.FastJsonUtil;

//指定模型对象类型
@Controller("customerAction")
@Scope("prototype")//多例
public class CustomerAction extends BaseAction<CustomerVo> {

	//注入service
	@Autowired
	private CustomerService customerService;

	public String test(){
		CustomerVo customerVo = this.getModel();
		CstCustomer customer = customerService.findCustomerById(customerVo.getCustId());
		//将customer放入值栈，页面获取
		customerVo.setCustomer(customer);
		return "test";
	}
	
	//客户列表
	public void list(){
		CustomerVo customerVo = this.getModel();
		//获取分页参数
		int page = customerVo.getPage();
		int rows = customerVo.getRows();
		
		//计算开始记录下标 
		int firstResult = (page-1)*rows;
		
		//通过 service查询客户信息列表
		//查询总数
		Long total = customerService.findCustomerCount(customerVo);
		
		
		//查询列表
		List<CstCustomer> list = customerService.findCustomerList(customerVo, firstResult, rows);
		//定义一个map
		Map<String, Object> datagrid_result = new HashMap<String, Object>();
		//将map数据转json，datagrid需要这个json，要求包括total和rows
		datagrid_result.put("total", total);
		datagrid_result.put("rows", list);
		//使用工具类
		//使用fastjson将map数据转json串
		String jsonString = FastJsonUtil.toJSONString(datagrid_result);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
		
	}
	
	//客户统计列表
	public void statlist(){
		CustomerVo customerVo = this.getModel();
		//获取分页参数
		int page = customerVo.getPage();
		int rows = customerVo.getRows();
		
		//计算开始记录下标 
		int firstResult = (page-1)*rows;
		//记录总数
		Long total = customerService.findCustomerStatCount(customerVo);
		//查询列表
		List list = customerService.findCustomerStatList(customerVo, firstResult, rows);
		
		if(total == null || list== null){
			total = 0l;
			list = new ArrayList();
		}
		//定义一个map
		Map<String, Object> datagrid_result = new HashMap<String, Object>();
		//将map数据转json，datagrid需要这个json，要求包括total和rows
		datagrid_result.put("total", total);
		datagrid_result.put("rows", list);
		//使用工具类
		//使用fastjson将map数据转json串
		String jsonString = FastJsonUtil.toJSONString(datagrid_result);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
	}
	
	//删除客户
	public void deleteCustomer(){
		CustomerVo customerVo = this.getModel();
		HttpServletResponse response = ServletActionContext.getResponse();
		//获取请求客户id
		Long custId = customerVo.getCustId();
		
		try {
			customerService.deleteCustomer(custId);
		} catch (Exception e) {
			e.printStackTrace();
			//如果存在异常，向客户输出json信息中包括异常信息
			String ajaxResult = FastJsonUtil.ajaxResult(false, "删除客户失败！");
			//输出json
			FastJsonUtil.write_json(response, ajaxResult);
			return ;
		}
		//执行到这里，执行成功
		//向客户输出json信息中包括成功提示信息
		String ajaxResult = FastJsonUtil.ajaxResult(true, "删除客户成功！");
		//输出json
		FastJsonUtil.write_json(response, ajaxResult);
		
	}
	
	//添加提交
	public void addsubmit(){
		CustomerVo customerVo = this.getModel();
		HttpServletResponse response = ServletActionContext.getResponse();
		CstCustomerDetailVo customerDetailVo = customerVo.getCustomerDetailVo();
		try {
			
			//判断是否上传成功
			//上传成功的图片，文件默认在tomcat的临时目录 中
			File picture = customerDetailVo.getPicture();
			//上传文件的原始名称
			String pictureFileName = customerDetailVo.getPictureFileName();
			//上传文件的类型
			String pictureContentType = customerDetailVo.getPictureContentType();
			if(picture!=null && pictureFileName!=null && !pictureFileName.equals("")){
				//服务器图片存储路径
				String filePath = "d:\\crmsshall\\upload\\";
				
				//扩展名，从原始名称中截取
				String fileName_extension = pictureFileName.substring(pictureFileName.lastIndexOf("."));
				
				//为了保证服务器上图片目录中图片名称不重复将每个上传的图片重定义一个名称
				//新文件名称
				String fileNameNew=UUID.randomUUID().toString()+fileName_extension;
				//定义一个File
				File fileNew  = new File(filePath+fileNameNew);
				
				//将tomcat下临时目录中的文件picture拷贝或移动到fileNew
				//使用拷贝，拷贝完成tomcat下临时目录中的文件自动删除了
				FileUtils.copyFile(picture, fileNew);
				
				//在数据库中保存图片路径
				customerDetailVo.setCustPic(fileNameNew);
				
			}
			
			
			customerService.insertCustomer(customerVo, customerDetailVo);
		} catch (Exception e) {
			e.printStackTrace();
			
			String ajaxResult = FastJsonUtil.ajaxResult(false, "添加客户失败！");
			//输出json
			FastJsonUtil.write_json(response, ajaxResult);
			return ;
		}
		
		
		//向客户返回成功提示
		String ajaxResult = FastJsonUtil.ajaxResult(true, "添加客户成功！");
		//输出json
		FastJsonUtil.write_json(response, ajaxResult);
	}
	
	//根据id查询客户基本信息
	public void findCustomerById(){
		CustomerVo customerVo = this.getModel();
		HttpServletResponse response = ServletActionContext.getResponse();
		CstCustomer customer = customerService.findCustomerById(customerVo.getCustId());
		//将上边的对象转json输出
		String jsonString = FastJsonUtil.toJSONString(customer);
		//使用JsonFormatterAddPrefix工具方法将嵌套的json转成单层结构
		jsonString= FastJsonUtil.JsonFormatterAddPrefix(jsonString, "", null);
		FastJsonUtil.write_json(response, jsonString);
	}
	//根据id查询客户详细信息
	public void findCustomerDetailById(){
		CustomerVo customerVo = this.getModel();
		HttpServletResponse response = ServletActionContext.getResponse();
		//查询详细信息
		CstCustomerDetail customerDetail = customerService.findCustomerDetailById(customerVo.getCustId());
		//将对象转json输出
		//将上边的对象转json输出
		String jsonString = FastJsonUtil.toJSONString(customerDetail);
		//使用JsonFormatterAddPrefix工具方法将嵌套的json转成单层结构
		//由于页面的input的name有前缀：customerDetailVo.，对json数据添加前缀
		jsonString= FastJsonUtil.JsonFormatterAddPrefix(jsonString, "customerDetailVo.", null);
		FastJsonUtil.write_json(response, jsonString);
	}
	
	//修改提交
	public void editsubmit(){
		CustomerVo customerVo = this.getModel();
		HttpServletResponse response = ServletActionContext.getResponse();
		CstCustomerDetailVo customerDetailVo = customerVo.getCustomerDetailVo();
		try {
			//判断是否上传成功
			//上传成功的图片，文件默认在tomcat的临时目录 中
			File picture = customerDetailVo.getPicture();
			//上传文件的原始名称
			String pictureFileName = customerDetailVo.getPictureFileName();
			//上传文件的类型
			String pictureContentType = customerDetailVo.getPictureContentType();
			if(picture!=null && pictureFileName!=null && !pictureFileName.equals("")){
				//服务器图片存储路径
				String filePath = "F:\\develop\\upload\\";
				
				//扩展名，从原始名称中截取
				String fileName_extension = pictureFileName.substring(pictureFileName.lastIndexOf("."));
				
				//为了保证服务器上图片目录中图片名称不重复将每个上传的图片重定义一个名称
				//新文件名称
				String fileNameNew=UUID.randomUUID().toString()+fileName_extension;
				//定义一个File
				File fileNew  = new File(filePath+fileNameNew);
				
				//将tomcat下临时目录中的文件picture拷贝或移动到fileNew
				//使用拷贝，拷贝完成tomcat下临时目录中的文件自动删除了
				FileUtils.copyFile(picture, fileNew);
				
				//在数据库中保存图片路径
				customerDetailVo.setCustPic(fileNameNew);
				
			}
			customerService.updateCustomer(customerVo.getCustId(), customerVo, customerVo.getCustomerDetailVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String ajaxResult = FastJsonUtil.ajaxResult(false, "更新客户失败！");
			//输出json
			FastJsonUtil.write_json(response, ajaxResult);
			return ;
			
		}
		//向客户返回成功提示
		String ajaxResult = FastJsonUtil.ajaxResult(true, "更新客户成功！");
		//输出json
		FastJsonUtil.write_json(response, ajaxResult);
	}
	
	//根据客户名称搜索客户信息
	public void searchCustomerByName(){
		
		//获取搜索的关键字，客户名称
		CustomerVo customerVo = this.getModel();
		String q = customerVo.getQ();
		List<CstCustomer> list = null;
		
		if(q!=null && !q.equals("")){
			//调用service去搜索
			list = customerService.findCustomerByName(q);
		}else{
			list = new ArrayList<CstCustomer>();
		}
		//返回datagrid需要的json
		//定义一个map
		Map<String, Object> datagrid_result = new HashMap<String, Object>();
		//将map数据转json，datagrid需要这个json，要求包括total和rows
		datagrid_result.put("total", list.size());
		datagrid_result.put("rows", list);
		//使用工具类
		//使用fastjson将map数据转json串
		String jsonString = FastJsonUtil.toJSONString(datagrid_result);
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
		
	}
	
//	//客户列表
//	public void list(){
//		
//		List<CstCustomer> list = new ArrayList<CstCustomer>();
//		//这里使用静态数据填充客户信息
//		CstCustomer cstCustomer1 = new CstCustomer();
//		cstCustomer1.setCustName("黑马程序员");
//		cstCustomer1.setCustId(101l);
//		list.add(cstCustomer1);
//		
//		CstCustomer cstCustomer2 = new CstCustomer();
//		cstCustomer2.setCustName("传智播客");
//		cstCustomer2.setCustId(102l);
//		list.add(cstCustomer2);
//
//		//定义一个map
//		Map<String, Object> datagrid_result = new HashMap<String, Object>();
//		//将map数据转json，datagrid需要这个json，要求包括total和rows
//		datagrid_result.put("total", 2);
//		datagrid_result.put("rows", list);
//		//使用工具类
//		//使用fastjson将map数据转json串
//		String jsonString = FastJsonUtil.toJSONString(datagrid_result);
//		HttpServletResponse response = ServletActionContext.getResponse();
//		FastJsonUtil.write_json(response, jsonString);
////		String jsonString = JSON.toJSONString(list);
//		//DisableCircularReferenceDetect禁止循环引用检测
////		String jsonString = JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
//		
////		//手动通过response输出json
////		HttpServletResponse response = ServletActionContext.getResponse();
////		//设置内容类型
////		response.setContentType("application/json;charset=utf-8");
////		//将上边jsonString串输出到客户端
////		try {
////			response.getWriter().write(jsonString);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		
//	}
	

}
