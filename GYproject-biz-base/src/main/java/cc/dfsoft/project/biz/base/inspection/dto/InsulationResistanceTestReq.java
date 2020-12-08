package cc.dfsoft.project.biz.base.inspection.dto;

import java.util.List;

import cc.dfsoft.project.biz.base.inspection.entity.InsulationResistanceTest;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
/**
 * 电器绝缘电阻
 * @author fuliwei
 *
 */
public class InsulationResistanceTestReq  extends PageSortReq{
	
	private String pcId;
	private  List<InsulationResistanceTest> list;
	
	public String getPcId() {
		return pcId;
	}
	public void setPcId(String pcId) {
		this.pcId = pcId;
	}
	public List<InsulationResistanceTest> getList() {
		return list;
	}
	public void setList(List<InsulationResistanceTest> list) {
		this.list = list;
	}
}
