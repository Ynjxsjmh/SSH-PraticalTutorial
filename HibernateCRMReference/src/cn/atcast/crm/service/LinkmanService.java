package cn.atcast.crm.service;

import java.util.List;

import cn.atcast.crm.domain.CstLinkman;

public interface LinkmanService {


	//添加联系人，确定所属客户
	/**
	 * 
	 * <p>Title: insertLinkman</p>
	 * <p>Description: </p>
	 * @param custId 所属客户id
	 * @param cstLinkman 联系人信息
	 */
	public void insertLinkman(Long custId,CstLinkman cstLinkman);
	
	//更新联系人
	public void updateLinkman(Long custId,Long lkmId,CstLinkman cstLinkman);
	
	//删除联系人
	public void deleteLinkman(Long lkmId);
	//根据联系人id查询联系人
	public CstLinkman findLinkmanById(Long lkmId);
	//查询联系人列表总记录数
	public long findLinkmanCount(CstLinkman cstLinkman);
	//查询联系人列表
	public List<CstLinkman> findLinkmanList(CstLinkman cstLinkman,int firstResult,int maxResults);
}
