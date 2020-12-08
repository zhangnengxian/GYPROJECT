package cc.dfsoft.project.biz.base.test.controller;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.dfsoft.project.biz.ifs.finance.dto.PaymentSuccessInfoDTO;
import cc.dfsoft.project.biz.ifs.finance.service.FinanceService;

@Controller
@RequestMapping(value="/testServices")
public class TestServicesController {

	/*@RequestMapping(value = "/test")
	@ResponseBody
	public String test(HttpServletRequest request){
		String res="";
        QName qName = org.apache.cxf.financeservice.FinanceService.SERVICE;
		URL wsdlDocumentLocation = org.apache.cxf.financeservice.FinanceService.WSDL_LOCATION;
		
	    Service service = Service.create(wsdlDocumentLocation, qName);
	    FinanceService hiService = service.getPort(org.apache.cxf.financeservice.FinanceService.FinanceServiceImplPort,FinanceService.class);  
	    PaymentSuccessInfoDTO infoDTO = new PaymentSuccessInfoDTO();
	    infoDTO.setBill_id("1122");
	    infoDTO.setPro_no("12345");
	    JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONObject json = JSONObject.fromObject(infoDTO,jsonConfig);
	    res = hiService.paymentDone("userName", "11", json.toString());
		return res;
	}*/
}
