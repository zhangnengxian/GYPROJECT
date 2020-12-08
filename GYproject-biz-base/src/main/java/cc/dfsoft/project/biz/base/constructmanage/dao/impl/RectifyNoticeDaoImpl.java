package cc.dfsoft.project.biz.base.constructmanage.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cc.dfsoft.project.biz.base.constructmanage.dao.RectifyNoticeDao;
import cc.dfsoft.project.biz.base.constructmanage.dto.RectifyNoticeReq;
import cc.dfsoft.project.biz.base.constructmanage.entity.RectifyNotice;
import cc.dfsoft.uexpress.common.dao.NewBaseDAO;
import cc.dfsoft.uexpress.common.util.ResultUtil;
/**
 * 
 * 描述:整改通知dao实现
 * @author liaoyq
 * @createTime 2017年8月4日
 */
@Repository
public class RectifyNoticeDaoImpl extends NewBaseDAO<RectifyNotice, String> implements
		RectifyNoticeDao {
	@Override
	public Map<String, Object> queryRectifyNotice(
		RectifyNoticeReq rectifyNoticeReq) throws ParseException {
		Criteria c = super.getCriteria();
		//整改编号
		if(StringUtils.isNotBlank(rectifyNoticeReq.getRnNo())){
			c.add(Restrictions.eq("rnNo",rectifyNoticeReq.getRnNo()));
		}
		//工程id
		if(StringUtils.isNotBlank(rectifyNoticeReq.getProjId())){
			c.add(Restrictions.eq("projId",rectifyNoticeReq.getProjId()));
		}
		 //用于字符串日期格式转换
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//通知开始日期
		if(StringUtils.isNotBlank(rectifyNoticeReq.getRnDateStart())){
			 c.add(Restrictions.ge("rnDate", sdf.parse(rectifyNoticeReq.getRnDateStart())));
		}
		//通知结束日期
		if(StringUtils.isNotBlank(rectifyNoticeReq.getRnDateEnd())){
			c.add(Restrictions.le("rnDate", sdf.parse(rectifyNoticeReq.getRnDateEnd())));
		}
		//通知状态
		if(rectifyNoticeReq.getRnStatus()!=null&&rectifyNoticeReq.getRnStatus().size()>0){
			c.add(Restrictions.in("rnStatus", rectifyNoticeReq.getRnStatus()));
		}
		
		c.addOrder(Order.desc("rnDate"));  // 通知日期
		// 数据库根据条件过滤记录数
		int filterCount = this.countByCriteria(c);

		// 根据条件获取查询信息
		List<RectifyNotice> engineeringVisaList = this.findBySortCriteria(c, rectifyNoticeReq);
		
		// 返回结果
		return ResultUtil.pageResult( filterCount, rectifyNoticeReq.getDraw(),engineeringVisaList);
	}

}
