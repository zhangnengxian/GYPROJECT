package cc.dfsoft.project.biz.base.budget.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.budget.service.DrawingDirectoryService;
import cc.dfsoft.project.biz.base.project.dao.ManageRecordDao;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class DrawingDirectoryServiceImpl implements DrawingDirectoryService{
	
	@Resource
	ProjectDao projectDao;
	
	@Resource
	ManageRecordDao managerecorddao;
	
	/**
	 * 查询工程列表
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 *//*
	@Override
	public Map<String, Object> queryAuditProject(ProjectQueryReq projectQueryReq) throws ParseException {
		return projectDao.queryProject(projectQueryReq);
	}
	
	
	
	*//**
	 * 图纸审核页面工程列表查询 
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 *//*
	@Override
	public Map<String, Object> queryProject(ProjectQueryReq projectQueryReq) throws ParseException {
		String a="111";
		
		Map<String,Object> result = this.queryAuditProject(projectQueryReq);
		List<Project> data = (List<Project>) result.get("data");
		if(data!=null && data.size()>0){
			String id=data.get(0).getProjId();
			//ToDo通过id查图纸目录的list
		}
		
		if(data!=null && data.size()>0){
			*//**
			 * -1 未审核  0 审核未通过  1 审核通过  2待审核
			 * 若该 审核为三级审核，且一级审核通过、二级审核未通过，三级审核待审核，则键值对：{"level1":"1","level2":"0","level3":"2"};
			 * *//*
			//遍历循环  设置审核级别
			for(int i = 0;i<data.size();i++){
				//data.get(i).setLevel(DocTypeEnum.SURVEY_INFO.getGrade());	//设置审核总级数（勘察信息3级审核）
				Map<String,String> levelBtn = new HashMap();
				//以下循环，遍历出默认应该几级审核，审核状态分别为什么，例如三级审核，则一级待审、二级未审、三级未审{"level1":"2","level2":"-1","level3":"-1"}；
				for(int n=1;n<Integer.parseInt(DocTypeEnum.SURVEY_INFO.getGrade())+1;n++){
					if(n==1){
						levelBtn.put("level"+n, "2");// 待审
					}else{
						levelBtn.put("level"+n, "-1");//未审
					}
				}
				List<ManageRecord> mrls = managerecorddao.findByProjId(data.get(i).getProjId());
				if(mrls!=null && mrls.size()>0){
					//遍历循环，获取审核是否通过
					for(ManageRecord mr:mrls){
						levelBtn.put("level"+mr.getMrAuditLevel(),mr.getMrResult());
					}
					if(mrls.size()<Integer.parseInt(DocTypeEnum.SURVEY_INFO.getGrade())){
						levelBtn.put("level"+(mrls.size()+1), "2");
					}
				}
				//data.get(i).setMrAuditLevel(JSONSerializer.toJSON(levelBtn).toString());
			}
			result.put("data", data);
		}
		return result;
	}*/

}
