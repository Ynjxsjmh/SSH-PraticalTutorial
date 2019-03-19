package cn.atcast.crm.dao;

import java.util.List;

import cn.atcast.crm.domain.CstLinkman;

public interface LinkmanDao {
	// 添加联系人
	public void insert(CstLinkman cstLinkman);
	
	// 更新联系人
	public void update(CstLinkman cstLinkman);
	
	//删除联系人
	public void delete(Long lkmId);
	
	//根据id查询联系人
	public CstLinkman findLinkmanById(Long lkmId);

	// 查询联系人记录总数
	public long findLinkmanCount(CstLinkman cstLinkman);

	// 查询联系人列表
	public List<CstLinkman> findLinkmanList(CstLinkman cstLinkman, int firstResult, int maxResults);

}
