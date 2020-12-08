package cc.dfsoft.project.biz.base.baseinfo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.baseinfo.dao.GradeDao;
import cc.dfsoft.project.biz.base.baseinfo.dao.ScoreStandardDao;
import cc.dfsoft.project.biz.base.baseinfo.dto.GradeQueryDReq;
import cc.dfsoft.project.biz.base.baseinfo.dto.ScoreStandardQueryReq;
import cc.dfsoft.project.biz.base.baseinfo.entity.Grade;
import cc.dfsoft.project.biz.base.baseinfo.entity.ScoreStandard;
import cc.dfsoft.project.biz.base.baseinfo.service.ScoreStandardService;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import cc.dfsoft.uexpress.common.util.StringUtil;

/**
 * @author cui
 * @createTime 2016-07-22
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class ScoreStandardServiceImpl implements ScoreStandardService {
	
	/**客户dao*/
	@Resource
	ScoreStandardDao scoreStandardDao;
	
	/**评分dao*/
	@Resource
	GradeDao gradeDao;
	
	@Override
	public Map<String, Object> queryScoreStandard(ScoreStandardQueryReq scoreStandardQueryReq) {
		// TODO Auto-generated method stub
		return scoreStandardDao.queryScoreStandard(scoreStandardQueryReq);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String saveOrUpdateScoreStandard(ScoreStandard scoreStandard) {
		if(StringUtil.isNoneBlank(scoreStandard.getSsId())){
			if(scoreStandardDao.queryScore(scoreStandard)){
				//分值大于100
				return "greater";
			}else{
				scoreStandardDao.update(scoreStandard);
				return Constants.OPERATE_RESULT_SUCCESS;
			}
		}else{
			if((scoreStandardDao.queryBySsDes(scoreStandard.getSsDes())) == null){
				if(scoreStandardDao.queryScore(scoreStandard)){
					//分值大于100
					return "greater";
				}else{
					scoreStandard.setSsId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
					scoreStandardDao.save(scoreStandard);
					return Constants.OPERATE_RESULT_SUCCESS;
				}
			}else{
				if(scoreStandardDao.queryBySsDes(scoreStandard.getSsDes()).getDept().getDeptId().equals(scoreStandard.getDept().getDeptId())){
					return "exist";
				}else if(scoreStandardDao.queryScore(scoreStandard)){
					//分值大于100
					return "greater";
				}else{
					scoreStandard.setSsId(IDUtil.getUniqueId(Constants.FILE_CODE_ACCESS));
					scoreStandardDao.save(scoreStandard);
					return Constants.OPERATE_RESULT_SUCCESS;
				}
				
			}
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteScoreStandard(String id) {
		// TODO Auto-generated method stub
		scoreStandardDao.delete(id);
	}
	
	
	/**
	 * 弹出屏查询评分
	 * @author
	 * @createTime 
	 * @param
	 * @return
	 */
	@Override
	public Map<String, Object> queryGrade(ScoreStandardQueryReq scoreStandardQueryReq) {
		Map<String, Object> map= scoreStandardDao.queryScoreStandard(scoreStandardQueryReq);
	
		List <ScoreStandard> list=(List<ScoreStandard>) map.get("data");
		
		List<Grade> gradeList=new ArrayList<Grade>();
		LoginInfo login=SessionUtil.getLoginInfo();
		//用ssID查grade的值显示
		/*for(int i = 0;i < list.size();i ++){
			gradeList=gradeDao.queryBySsid(list.get(i).getSsId(),scoreStandardQueryReq.getProjId());
			if(gradeList !=null && gradeList.size() > 0){
				for(int j = 0;j<gradeList.size();j ++){
					list.get(i).setSsId(gradeList.get(j).getSsId());
					list.get(i).setSsDes(gradeList.get(j).getGradeDes());
					list.get(i).setSsScore(gradeList.get(j).getGradeScore());
					list.get(i).setUnitType(gradeList.get(j).getUnitType());
					list.get(i).setGrade(gradeList.get(j).getGrade());
					list.get(i).setDepartmentId(gradeList.get(j).getDeptId());
				}
				
			}
		}*/
		ScoreStandard scoreStandard;
		for(int i = 0;i < list.size();i ++){
			gradeList=gradeDao.queryBySsid(scoreStandardQueryReq.getProjId(),login.getStaffId());
			if(gradeList !=null && gradeList.size() > 0){
				for(int j = 0;j<gradeList.size();j ++){
					String ssId1=list.get(i).getSsId();
					String ssId2=gradeList.get(j).getSsId();
					String grade=gradeList.get(j).getGrade();
					if(list.get(i).getSsId().equals(gradeList.get(j).getSsId()) && gradeList.get(j).getGrade()!=null){
						//通过ssid查找score
						scoreStandard=scoreStandardDao.queryBySsId(gradeList.get(j).getSsId());
						list.get(i).setGrade(gradeList.get(j).getGrade());
					}
				}
			}
		}
		Map<String, Object> mapGrade=new HashMap<String, Object>();
		
		mapGrade.put("data", list);
		
		return map;
	}
	
	/**
	 * 批量添加grade
	 * @author
	 * @createTime 2016-12-07
	 * @param
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String batInsertGrade(GradeQueryDReq gqReq) {
		
		List<Grade> gradeList=new ArrayList();
		LoginInfo login=SessionUtil.getLoginInfo();
		Grade grade;
		ScoreStandard scoreStandard;
		String gradeId;
		/*for(Grade grade:gqReq.getGrade()){
			grade.setProjId(gqReq.getProjId());//工程id
			gradeId=IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
			grade.setGradeId(gradeId);         //主键id
			grade.setGrader(login.getStaffName());//评分人
			grade.setGraderId(login.getStaffId());//评分人id
			grade.setGraderDate(gradeDao.getDatabaseDate());//评分时间
			gradeList.add(grade);
		}*/
		if(gqReq.getList()!=null && gqReq.getList().size()>0 ){
			for(int i = 0;i < gqReq.getList().size();i++){
				grade=new Grade();
				scoreStandard=gqReq.getList().get(i);
				grade.setProjId(gqReq.getProjId());				//工程id
				gradeId=IDUtil.getUniqueId(Constants.MODULE_CODE_SUBCONTRACT);
				grade.setGradeId(gradeId);         				  //主键id
				grade.setGrader(login.getStaffName());            //评分人
				grade.setGraderId(login.getStaffId());			  //评分人id
				grade.setGraderDate(gradeDao.getDatabaseDate());  //评分时间
				
				grade.setDeptId(gqReq.getDeptId());					//部门id
				grade.setUnitType(scoreStandard.getDept().getDeptType());//部门类型
				grade.setSsId(scoreStandard.getSsId());
				grade.setGrade(scoreStandard.getGrade());         //打分
				grade.setGradeDes(scoreStandard.getSsDes());      //评分项
				grade.setGradeScore(scoreStandard.getSsScore());  //分值
				gradeList.add(grade);
			}
		}
		
		// 根据unitid和工程id删除grade表相应部门的内容
		gradeDao.deleteByProjIdAndUnitId(gqReq.getProjId(), gqReq.getDeptId());
		
		gradeDao.batchInsertObjects(gradeList);
		return Constants.OPERATE_RESULT_SUCCESS;
	}

	
}
