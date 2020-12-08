package cc.dfsoft.project.biz.base.station.service.impl;

import cc.dfsoft.project.biz.base.station.dao.StationDataDao;
import cc.dfsoft.project.biz.base.station.entity.StationData;
import cc.dfsoft.project.biz.base.station.service.StationDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cui on 2017/8/28.
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class StationDataServiceImpl implements StationDataService {

    @Resource
    StationDataDao stationDataDao;

    @Override
    public List<StationData> findByType(String value) {
        return stationDataDao.findByType(value);
    }
}
