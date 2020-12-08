package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.ControlDebugging;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 控制系统调试
 * @author fuliwei
 *
 */
public class ControlDebuggingReq  extends PageSortReq{
	
	private String pcId;
	private  List<ControlDebugging> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<ControlDebugging> getList() {
		return list;
	}
	public void setList(List<ControlDebugging> list) {
		this.list = list;
	}
}
