package cc.dfsoft.project.biz.base.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.dfsoft.project.biz.base.common.dao.DataFilerSetUpDao;
import cc.dfsoft.project.biz.base.common.entity.DataFilerSetUp;
import cc.dfsoft.project.biz.base.common.service.DataFilerSetUpService;
import cc.dfsoft.uexpress.common.constant.ConstantsMap;
import cc.dfsoft.uexpress.common.constant.DataFilerSetUpDto;
import cc.dfsoft.uexpress.common.util.BeanUtil;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class DataFilerSetUpServiceImpl implements DataFilerSetUpService,InitializingBean{
	
	@Autowired
	DataFilerSetUpDao dataFilerSetUpDao;

	@Override
	public void afterPropertiesSet() throws Exception {
		setDataFiler();
	}
	public void setDataFiler(){
		ConstantsMap.dataFilterMap.clear();
		List<DataFilerSetUp> list = dataFilerSetUpDao.getDataFilerSetUpList();
		if(list !=null && list.size()>0){
			for(DataFilerSetUp bean :list){
				//DataFilerSetUpDto使用辅助类转换为实体
				List<DataFilerSetUpDto> datas= new ArrayList<DataFilerSetUpDto>();

				String key=bean.getQueryDeptId()+"_"+bean.getMenuId();
				if(ConstantsMap.dataFilterMap.get(key)!=null && ConstantsMap.dataFilterMap.get(key).size()>0){
					datas = ConstantsMap.dataFilterMap.get(key);
				}
				DataFilerSetUpDto dataFilerSetUpDto = new DataFilerSetUpDto();
				BeanUtil.copyNotNullProperties(dataFilerSetUpDto, bean);
				datas.add(dataFilerSetUpDto);
				ConstantsMap.dataFilterMap.put(key, datas);
			}
		}
	}
}
