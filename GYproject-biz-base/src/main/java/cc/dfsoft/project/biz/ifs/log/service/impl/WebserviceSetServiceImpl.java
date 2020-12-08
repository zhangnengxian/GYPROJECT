package cc.dfsoft.project.biz.ifs.log.service.impl;

import cc.dfsoft.project.biz.base.maintain.dto.WebServiceSetReq;
import cc.dfsoft.project.biz.ifs.log.dao.WebServiceSetDao;
import cc.dfsoft.project.biz.ifs.log.entity.WebServiceSet;
import cc.dfsoft.project.biz.ifs.log.service.WebserviceSetService;
import cc.dfsoft.uexpress.biz.sys.dept.dao.DepartmentDao;
import cc.dfsoft.uexpress.biz.sys.dept.entity.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class WebserviceSetServiceImpl implements WebserviceSetService {

    @Resource
    WebServiceSetDao webServiceSetDao;
    @Resource
    DepartmentDao departmentDao;




    @Override
    public Map<String, Object> querywebserviceSetMap(WebServiceSetReq webServiceSetReq) {

        Map<String, Object> map = webServiceSetDao.querywebserviceSetMap(webServiceSetReq);
        List<WebServiceSet> list= (List<WebServiceSet>) map.get("data");
        for (int i = 0; i <list.size() ; i++) {
            Department department = departmentDao.queryDepartment(list.get(i).getCorpId());
            if (department!=null){
                list.get(i).setCorpId(department.getDeptName());
            }else {
                list.get(i).setCorpId("贵州燃气集团(全局)公司");
            }

        }
        return map;
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String switchStatus(String wsId) {
        WebServiceSet webServiceSet = webServiceSetDao.get(wsId);
        if (webServiceSet!=null){
            if ("1".equals(webServiceSet.getWebServiceFlag())){
                webServiceSet.setWebServiceFlag("0");
            }else {
                webServiceSet.setWebServiceFlag("1");
            }
            webServiceSetDao.update(webServiceSet);
            return webServiceSet.getWebServiceFlag();
        }
        return "";
    }
}
