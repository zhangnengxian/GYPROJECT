package cc.dfsoft.uexpress.biz.sys.auth.dto;

/**
 * 菜单信息
 * @author 1919
 *
 */
public class MenuDto {

	/** 菜单id */
	private String menuId;
	/** 菜单名称 */
	private String menuName;
	/** 菜单标题 */
	private String headTitle;
	/** 次序 */
	private int sortNo;
	/** 链接 */
	private String url;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getHeadTitle() {
		return headTitle;
	}
	public void setHeadTitle(String headTitle) {
		this.headTitle = headTitle;
	}
	public int getSortNo() {
		return sortNo;
	}
	public void setSortNo(int sortNo) {
		this.sortNo = sortNo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
 