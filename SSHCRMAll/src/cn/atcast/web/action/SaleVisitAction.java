package cn.atcast.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.atcast.domain.SaleVisit;
import cn.atcast.pojo.SaleVisitVo;
import cn.atcast.service.CustomerService;
import cn.atcast.service.SaleVisitService;
import cn.atcast.util.FastJsonUtil;

@Controller("saleVisitAction")
@Scope("prototype")
public class SaleVisitAction extends BaseAction<SaleVisitVo> {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SaleVisitService saleVisitService;

	// 查询客户拜访
	public void list() {

		// 获取模型对象
		SaleVisitVo saleVisitVo = this.getModel();

		// 获取分页参数
		int page = saleVisitVo.getPage();
		int rows = saleVisitVo.getRows();
		// 开始记录下标
		int firstResult = (page - 1) * rows;
		// 查询记录总数
		Long total = customerService.findSaleVisitCount(saleVisitVo);

		// 查询记录列表
		List<SaleVisit> list = customerService.findSaleVisitList(saleVisitVo, firstResult, rows);

		// 将数据生成json串
		Map<String, Object> datagrid_result = new HashMap<String, Object>();
		datagrid_result.put("total", total);
		datagrid_result.put("rows", list);

		// 生成json串
		String jsonString = FastJsonUtil.toJSONString(datagrid_result);
		// 输出json
		HttpServletResponse response = ServletActionContext.getResponse();
		FastJsonUtil.write_json(response, jsonString);
	}

	// 新增提交
	public void addsubmit() {
		HttpServletResponse response = ServletActionContext.getResponse();
		// 获取模型对象
		SaleVisitVo saleVisitVo = this.getModel();
		try {
			saleVisitService.insertSaleVisit(saleVisitVo);
		} catch (Exception e) {

			e.printStackTrace();
			// 生成异常信息
			String ajaxResult = FastJsonUtil.ajaxResult(false, "添加客户拜访异常");
			// 输出json
			FastJsonUtil.write_json(response, ajaxResult);
			return;

		}
		// 生成成功信息
		String ajaxResult = FastJsonUtil.ajaxResult(true, "添加客户拜访成功");
		// 输出json
		FastJsonUtil.write_json(response, ajaxResult);

	}
}
