package cc.dfsoft.project.biz.base.constructmanage.service;

import cc.dfsoft.project.biz.base.constructmanage.dto.UploadResult;
import cc.dfsoft.project.biz.base.constructmanage.entity.ConstructionOrganization;
import cc.dfsoft.project.biz.base.project.entity.Project;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 施工组织service
 * @author cui
 * @createTime 2017-3-31
 *
 */
public interface ConstructionOrganizationService {

	/**
	 * 开工过程控制
	 * 辅助查看工程施工中的工程状态
	 * @author liaoyq
	 * @createTime 2019-5-16
	 * @param project
	 * @return  会审交底 、施工组织、 开工报告、施工中有停工
	 */
	Project isAllowWorkStart(Project project);

	/**
	 * 显示出该工程信息显示在施工组织时
	 * @param projId
	 * @return
	 */
	ConstructionOrganization constructionOrganizationDetail(String projId) throws Exception ;

	/**
	 * 保存施工组织
	 * @createTime 2017-04-1
	 * @author cui
	 * @param constructionOrganization
	 * @return
	 */
    String constructionOrganizationSave(ConstructionOrganization constructionOrganization) throws Exception;

	/**
	 * 保存施工组织(附件)
	 * @createTime 2017-04-18
	 * @author cui
	 * @param constructionOrganization
	 * @return
	 */
    String saveConstructionOrganization(HttpServletRequest request, UploadResult constructionOrganization, MultipartFile[] files) throws Exception;

    
    /**
     * 根据工程ID、菜单ID查找报表
     * @author wanghuijun
     * @createTime 2018年10月8日
     * @param projId
     * @param type
     * @return
     */
	public String findPrintDataByProjId(String projId,String type);
	/**
	 * 保存回调
	 * @author fuliwei
	 * @createTime 2018年1月21日
	 * @param 
	 * @return
	 */
	public void saveSignNotice(String cwId);


    ConstructionOrganization findById(String s);

    boolean delBackupsConstructionOrganization(String projId,String rollBackReason);
}
