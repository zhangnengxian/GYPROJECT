package cc.dfsoft.project.biz.ifs;
/**
 * webservice接口访问路径配置
 * @author liaoyq
 * @createTime 2017-11-08
 *
 */
public class WebServiceProperty {

	/**用友-工程信息接口*/
	//public final static String SYS_PROJECTINFO = "http://127.0.0.1/uapws/service/nc.itf.gzrq.b01.contract?wsdl";//工程信息同步webservice接口-用友
	public final static String SYS_PROJECTINFO = "http://192.168.199.111:8081/uapws/service/nc.itf.gzrq.b01.IContractItf?wsdl";//工程信息同步webservice接口-用友
	/**用友-收款单接口*/
	//public final static String GATHER_CLIENT = "http://127.0.0.1/uapws/service/nc.itf.gzrq.b02.receiptBill?wsdl";//收款单同步webservice接口-用友
	public final static String GATHER_CLIENT = "http://127.0.0.1:8081/services/financeService?wsdl";//收款单同步webservice接口-用友
	/**用友-付款单接口*/
	//public final static String PAYMENT_CLEINT = "http://127.0.0.1/uapws/service/nc.itf.gzrq.b03.payBill?wsdl";//付款单同步webservice接口-用友
	public final static String PAYMENT_CLEINT = "http://127.0.0.1:8081/services/financeService?wsdl";//付款单同步webservice接口-用友
	/**工程系统提供-付款成功的接口*/
	public final static String PAYMENT_SUCCESSFUL_CLEINT = "http://192.168.199.106:8081/services/financeService?wsdl";//提供给用友的
	
	public final static String MATERIAL_SUCCESSFUL = "https://192.168.199.101/services/materialService?wsdl";//提供给用友的
}
