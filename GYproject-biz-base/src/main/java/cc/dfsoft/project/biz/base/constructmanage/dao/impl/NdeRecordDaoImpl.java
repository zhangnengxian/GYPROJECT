package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.NdeRecordDao;
import cc.dfsoft.project.biz.base.constructmanage.entity.NdeRecord;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
/**
 * 
 * 描述:无损检测dao实现层
 * @author liaoyq
 * @createTime 2017年9月27日
 */
@Repository
public class NdeRecordDaoImpl extends NewBaseDAO<NdeRecord, String> implements NdeRecordDao {

	/**
	 * 根据通知业务单查询无损检测信息
	 */
	@Override
	public NdeRecord findByBcId(String bcId) {
		StringBuffer hql = new StringBuffer(" from NdeRecord where bcId='").append(bcId).append("'");
		List<NdeRecord> list = this.findByHql(hql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 通过工程id查询探伤委托
	 * @author fuliwei
	 * @createTime 2017年12月10日
	 * @param  projId
	 * @return NdeRecord
	 */
	@Override
	public NdeRecord findBypProjId(String projId) {
		StringBuffer hql = new StringBuffer(" from NdeRecord where projId='").append(projId).append("'");
		List<NdeRecord> list = this.findByHql(hql.toString());
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void updateVersionByNdId(NdeRecord nd) {
		String hql="update NdeRecord set version="+nd.getVersion()+" where nrId='"+nd.getNrId()+"'";
		super.executeHql(hql);
	}

	
	/**
	 * 通过工程ID查找探伤委托
	 * @param projId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<NdeRecord> findNdeBypProjId(String projId){
		StringBuffer hql = new StringBuffer(" from NdeRecord where projId='").append(projId).append("'");
		List<NdeRecord> list = this.findByHql(hql.toString());
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}

}
