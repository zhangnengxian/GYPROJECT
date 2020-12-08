package cc.dfsoft.uexpress.biz.sys.dept.dto;

import cc.dfsoft.uexpress.common.dto.PageSortReq;

public class DepartmentQueryDto extends PageSortReq{
	private String businessType;//业务类型
	private String proContructType;//工程建设类型
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getProContructType() {
		return proContructType;
	}
	public void setProContructType(String proContructType) {
		this.proContructType = proContructType;
	}
	
}
