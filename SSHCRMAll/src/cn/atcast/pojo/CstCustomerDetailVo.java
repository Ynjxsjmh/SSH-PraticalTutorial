package cn.atcast.pojo;

import java.io.File;

import cn.atcast.domain.CstCustomerDetail;

public class CstCustomerDetailVo extends CstCustomerDetail {
	
	//客户端上传的图片，只要此属性有值说明客户端上传图片成功
	private File picture;
	
	//获取原始文件名称,命名规则：页面上file的名称+FileName
	private String pictureFileName;
	
	//获取原始文件类型，命名规则：页面上file的名称+ContentType
	private String pictureContentType;

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	
}
