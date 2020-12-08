package cc.dfsoft.project.biz.base.station.service;

import cc.dfsoft.project.biz.base.station.dto.StationProceduresDto;

import java.text.ParseException;

/**
 * 工程服务接口
 * @author cui
 * @createTime 2017-08-21
 *
 */
public interface StationSettlementService {


	/**
	 * 结算保存
	 * @author cui
	 * @createTime 2017-08-22
	 * @param stationProceduresDto
	 */
	void stationSettlementSave(StationProceduresDto stationProceduresDto) throws ParseException;
}
