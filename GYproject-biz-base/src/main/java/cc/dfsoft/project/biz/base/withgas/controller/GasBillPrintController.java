package cc.dfsoft.project.biz.base.withgas.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cc.dfsoft.project.biz.base.contract.enums.ContractIsPrintEnum;
import cc.dfsoft.uexpress.common.constant.Constants;


/**
 * 勘察单打印
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/gasBillPrint")
public class GasBillPrintController {
	
	 private static Logger logger= LoggerFactory.getLogger(GasBillPrintController.class);
	
	 
	@RequestMapping(value="/main")
    public ModelAndView main(){
        ModelAndView modelView=new ModelAndView();
        modelView.addObject("alreadyPrint", ContractIsPrintEnum.ALREADY_PRINT.getValue());//已打印
        modelView.addObject("haveNotPrint", ContractIsPrintEnum.HAVE_NOT_PRINT.getValue());//未打印
        modelView.addObject("url", String.valueOf(Constants.getSysConfigByKey("anShun_surveyResultPrint")));//报表地址
        modelView.setViewName("design/surveyResultPrint");
        return modelView;
    }
 
	 
	 
}
