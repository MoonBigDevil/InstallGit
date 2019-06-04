package cn.yao.bookStore.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable{
	private int currentPage;//当前页
	private int currentCount;//每页显示条数
	private String category;//分类
	
	private int totalPage;//总页数
	private int totalCount;//总条数
	private List<Product> psList; //获取到的数据
	
	private String searchfield;//模糊搜索的图书名
	
	
	
	public String getSearchfield() {
		return searchfield;
	}
	public void setSearchfield(String searchfield) {
		this.searchfield = searchfield;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<Product> getPsList() {
		return psList;
	}
	public void setPsList(List<Product> psList) {
		this.psList = psList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getCurrentCount() {
		return currentCount;
	}
	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "PageBean [currentPage=" + currentPage + ", currentCount=" + currentCount + ", category=" + category
				+ ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", psList=" + psList + ", searchfield="
				+ searchfield + "]";
	}
	
	
}
