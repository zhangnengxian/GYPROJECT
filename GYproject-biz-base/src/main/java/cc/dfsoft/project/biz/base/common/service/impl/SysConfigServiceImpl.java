package cc.dfsoft.project.biz.base.common.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cc.dfsoft.project.biz.base.common.dao.SysConfigDao;
import cc.dfsoft.project.biz.base.common.entity.SysConfigBean;
import cc.dfsoft.project.biz.base.common.service.SysConfigService;
import cc.dfsoft.uexpress.common.constant.Constants;
@Service
public class SysConfigServiceImpl implements SysConfigService,InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);
	@Autowired
	private SysConfigDao sysConfigDao;

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			setSysConfigMap();
		}catch (Exception e){
			e.printStackTrace();
		}
		try {
			setConstants();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public String setSysConfigMap() {
		//先清除
		Constants.sysConfigMap.clear();
		List<SysConfigBean> list = sysConfigDao.getSysConfigList();
		if(list !=null && list.size()>0){
			for(SysConfigBean bean :list){
				Constants.sysConfigMap.put(bean.getKeyId(), bean.getCnvalue());
			}
			return "初始化成功,初始化数据量为:"+list.size();
		}
		return "表数据为空，初始化失败!";
	}

	@Override
	public String setConstants() {
		//先清除
		Constants.constantsMap.clear();
		List<Map<String,Object>> map = sysConfigDao.getConstantsList();
		for(Map<String,Object> m :map){
			if(Constants.getConstantsMapByKey(String.valueOf(m.get("PID")))== null){
				String key = String.valueOf(m.get("PID"));
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				for(Map<String,Object> n : map){
					if(String.valueOf(m.get("PID")).equals(String.valueOf(n.get("PID")))){
						list.add(n);
					}
				}
				Constants.constantsMap.put(key,list);
			}
		}
		log.info("=======================初始化常量表成功数据量为:"+Constants.constantsMap.size());

		return null;
	}
}
