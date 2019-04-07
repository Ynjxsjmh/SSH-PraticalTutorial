package cn.atcast.pojo;

import java.util.Date;

import cn.atcast.domain.SaleVisit;

public class SaleVisitVo extends SaleVisit {

	// 分页参数
	private int page;
	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	// 拜访时间起
	private Date visitTime_start;
	// 拜访时间止
	private Date visitTime_end;

	public Date getVisitTime_start() {
		return visitTime_start;
	}

	public void setVisitTime_start(Date visitTime_start) {
		this.visitTime_start = visitTime_start;
	}

	public Date getVisitTime_end() {
		return visitTime_end;
	}

	public void setVisitTime_end(Date visitTime_end) {
		this.visitTime_end = visitTime_end;
	}

}
