package cn.atcast.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.atcast.domain.BaseDict;
import cn.atcast.domain.SysUser;
import cn.atcast.pojo.BaseDictVo;
import cn.atcast.service.SystemService;
import cn.atcast.util.FastJsonUtil;

//指定模型对象类型
@Controller("systemAction")
@Scope("prototype")//多例
//public class SystemAction extends ActionSupport implements ModelDriven<BaseDictVo> {
public class SystemAction extends BaseAction<BaseDictVo>{
	
//	//定义一个成员变量，作为模型对象
//	private BaseDictVo baseDictVo = new BaseDictVo();
	
	//注入service
	@Autowired
	private SystemService systemService;

	//由ModelDriven拦截器调用此方法，此方法是在action实例化后调用，获取一个模型对象
//	@Override
//	public BaseDictVo getModel() {
//		
//		return baseDictVo;
//	}
	
	//根据typecode查询数据字典
	public void findBaseDictByTypecode(){
		//获取模型对象实例
		BaseDictVo baseDictVo = this.getModel();
		//获取页面请求的typecode
		String dictTypeCode = baseDictVo.getDictTypeCode();
		//查询数据字典信息
		List<BaseDict> list = systemService.findBaseDictByTypeCode(dictTypeCode);
		//创建“请选择”空对象
		BaseDict baseDict = new BaseDict();
		baseDict.setDictItemName("请选择");
		baseDict.setDictId("");
		//重新构造一个listAll
		List<BaseDict> listAll = new ArrayList<BaseDict>();
		listAll.add(baseDict);
		listAll.addAll(list);
		
		//将list转成json串
		String jsonString = FastJsonUtil.toJSONString(listAll);
		//输出json
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
	}
	
	//根据用户名称搜索用户信息
	public void searchSysUserByName(){
		
		//获取搜索的关键字，用户名称
		BaseDictVo baseDictVo = this.getModel();
		String q = baseDictVo.getQ();
		List<SysUser> list = null;
		
		if(q!=null && !q.equals("")){
			//调用service去搜索
			list = systemService.findSysUserByUserName(q);
		}else{
			list = new ArrayList<SysUser>();
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

}
