package cc.dfsoft.uexpress.common.dto;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页排序请求参数
 * @author 1919
 *
 */
public class PageSortReq {

	/** 超始条数 */
	private Integer start;
	/** 每页条数 */
	private Integer length;
	/** 请求次数计数器 */
	private int draw;
	/** 排序的列值 */
	private String sort;
	/** 排序规则 */
	private String sortName;
	
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	public void setSortInfo(HttpServletRequest request){
		String orderc = request.getParameter("order[0][column]");
    	this.sort = request.getParameter("order[0][dir]");
    	this.sortName= request.getParameter("columns["+orderc+"][data]");
	}
	
}
