package cc.dfsoft.project.biz.ifs.material.dto;

import java.util.Map;


/**
 * 工程信息汇总
 * @author fuliwei
 *
 */
public class ProjectWholeInoDTO {
	
	/**操作类型*/
	private String operate_type;
	
	//工程基本信息
	private ProjectInfoDTO projinfo;
		
	//材料信息
	private Map<String,Object> materiallistinfo;
	
	

	public String getOperate_type() {
		return operate_type;
	}

	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}

	public ProjectInfoDTO getProjinfo() {
		return projinfo;
	}

	public void setProjinfo(ProjectInfoDTO projinfo) {
		this.projinfo = projinfo;
	}

	public Map<String,Object> getMateriallistinfo() {
		return materiallistinfo;
	}

	public void setMateriallistinfo(Map<String,Object> materiallistinfo) {
		this.materiallistinfo = materiallistinfo;
	}

	



	
	
	
	
	
}
