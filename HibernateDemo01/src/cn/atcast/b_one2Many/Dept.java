package cn.atcast.b_one2Many;

import java.util.HashSet;
import java.util.Set;

public class Dept {

	private int deptId;
	private String deptName;
	// 【一对多】 部门对应的多个员工
	private Set<Employee> emps = new HashSet<Employee>();

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmps() {
		return emps;
	}

	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}

}
