package cc.dfsoft.project.biz.base.constructmanage.service.impl;

import cc.dfsoft.project.biz.base.constructmanage.dao.GraphicProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.dao.ProgressDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.ProgressQueryReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.GraphicProgress;
import cc.dfsoft.project.biz.base.constructmanage.entity.Progress;
import cc.dfsoft.project.biz.base.constructmanage.service.ProgressService;
import cc.dfsoft.project.biz.base.project.dao.ProjectDao;
import cc.dfsoft.project.biz.base.project.entity.Project;
import cc.dfsoft.project.biz.base.subpackage.dao.SubQuantitiesDao;
import cc.dfsoft.project.biz.base.subpackage.entity.SubQuantities;
import cc.dfsoft.uexpress.biz.sys.dept.common.SessionUtil;
import cc.dfsoft.uexpress.biz.sys.dept.dto.LoginInfo;
import cc.dfsoft.uexpress.common.constant.Constants;
import cc.dfsoft.uexpress.common.util.IDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true,propagation=Propagation.REQUIRED)
public class ProgressServiceImpl implements ProgressService{
	
	/**工程进度dao*/
	@Resource
	ProgressDao progressDao;
	
	/**工程量dao*/
	@Resource
	SubQuantitiesDao subQuantitiesDao;

    @Resource
    GraphicProgressDao graphicProgressDao;
	
	@Override
	public Map<String, Object> queryProgress(ProgressQueryReq progressQueryReq) {
        Map<String, Object> map = progressDao.queryProgress(progressQueryReq);
        List<Map<String,Object>> list = (List<Map<String, Object>>) map.get("data");
        for(Map<String,Object> m : list){
            GraphicProgress gp = graphicProgressDao.get((String) m.get("nuitProject"));
            if(gp!=null){
                m.put("nuitProjectDes",gp.getGpName());
            }
        }
        return map;
	}

	/**工程dao*/
	@Resource
	ProjectDao projectDao;
	
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveProgress(List<Progress> progressList) {
		if(progressList!=null && progressList.size()>0){
			for(int i=0;i<progressList.size();i++){
				Progress p = progressList.get(i);
				SubQuantities subQuantities = subQuantitiesDao.get(p.getQuId());//工程量标准
				Double thisnum = 0.0;
				if(p.getHeapProgressNum()!=null&&p.getThisProgressNum()!=null){
					thisnum = p.getHeapProgressNum()+p.getThisProgressNum();//累计完成量=原累计完成量+本次完成量
				}else{
					thisnum = p.getThisProgressNum();
				}
				//subQuantities.setProgressAnum(thisnum); //设置进度总量
				p.setHeapProgressNum(thisnum); 			//累计完成量
				if(p.getAllProgressNum()!=null && thisnum!=null){
					NumberFormat nf = NumberFormat.getInstance();
					nf.setMaximumFractionDigits(2);
					nf.setMinimumFractionDigits(2);
					p.setFinishProgress(nf.format((thisnum/p.getAllProgressNum())*100)+"%");//完成进度
				}
				p.setProgressId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
				p.setRegisterDate(progressDao.getDatabaseDate());//登记时间
				LoginInfo loginInfo = SessionUtil.getLoginInfo();
				p.setRegistePerson(loginInfo.getStaffId());//登记人
				subQuantitiesDao.update(subQuantities);//更新工程量标准
				progressDao.save(p);//新增工程进度记录
				
				/*Project project = projectDao.get(p.getProjId());
				ProgressQueryReq progressQueryReq = new ProgressQueryReq();
				progressQueryReq.setProjId(p.getProjId());
				String pqr = this.queryTotalProgress(progressQueryReq);
				project.setTotalProgress(pqr.substring(0, pqr.length()-1));
				projectDao.update(project);*/
			}
		}
	}

	@Override
	public String queryTotalProgress(ProgressQueryReq progressQueryReq) {
		
		if(StringUtils.isNotBlank(progressQueryReq.getRegisterDate())){
			return progressDao.queryTotalProgress(progressQueryReq);
		}else{
			return progressDao.queryProjectTotalProgress(progressQueryReq.getProjId());
		}
	}

	@Override
	public Date getDatabaseDate() {
		return progressDao.getDatabaseDate();
	}
	
	/**
	 * 工程表总进度保存
	 * @author fulwei
	 * @createTime 2017-01-30
	 * @return
	 */
	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void savePrjectTotalProgress(String projId) {
		Project project = projectDao.get(projId);
		if(project != null){
			String pqr = this.queryProjectTotalProgress(projId);
			project.setTotalProgress(pqr.substring(0, pqr.length()-1));
			projectDao.update(project);
		}
	}
	
	/**
	 * 工程表查询工程总进度 
	 * @author fuliwei
	 * @createTime 2017年2月11日
	 * @param progressQueryReq
	 * @return String
	 */
	@Override
	public String queryProjectTotalProgress(String projId) {
		return progressDao.queryProjectTotalProgress(projId);
	}

	@Override
	public Progress queryGraphicProgress(String projId) {
		return progressDao.queryGraphicProgress(projId);
	}

	@Override
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public String saveGraphicProgress(Progress progress) {
        Progress p = progressDao.queryGraphicProgress(progress.getProjId());
        if(p.getFinishProgress()!=null){
            String finishProgress = p.getFinishProgress();
            //
            if(Integer.parseInt(finishProgress.substring(0,finishProgress.indexOf(".")))>=Integer.parseInt(progress.getFinishProgress().substring(0,progress.getFinishProgress().indexOf(".")))){
                return "rep";
            }
        }
        progress.setProgressId(IDUtil.getUniqueId(Constants.MODULE_CODE_PROCESS));
        progress.setRegisterDate(progressDao.getDatabaseDate());
        //增加记录人ID
        LoginInfo info = SessionUtil.getLoginInfo();
        progress.setRegistePerson(info!=null?info.getStaffId():"");
        progressDao.save(progress);

        //保存到工程表
        Project project = projectDao.get(progress.getProjId());
		String pqr = progress.getFinishProgress();
		project.setTotalProgress(pqr.substring(0, pqr.length()-1));
		projectDao.update(project);
        return progress.getFinishProgress();
	}
}
