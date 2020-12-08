package cc.dfsoft.project.biz.base.plan.dao;

import cc.dfsoft.project.biz.base.plan.entity.InstTasks;
import cc.dfsoft.uexpress.common.dao.CommonDao;

import java.util.List;

public  interface InstTasksDao extends CommonDao<InstTasks, String>{

	/**
	 * 根据工程id查询安装任务
	 * @param
	 * @return
	 */
	List<InstTasks> findByProjId(String projId);


	
}