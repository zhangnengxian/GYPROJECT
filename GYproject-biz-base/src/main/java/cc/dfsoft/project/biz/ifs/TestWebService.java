package cc.dfsoft.project.biz.ifs;

import java.math.BigDecimal;

import nc.bd.itf.customer.service.IContractItfLocator;
import nc.bd.itf.customer.service.IContractItfPortType;
import nc.bd.itf.gzrq.material.MaterielAppLocator;
import nc.bd.itf.gzrq.material.MaterielAppPortType;
import nc.bd.itf.payBill.service.IPayBillLocator;
import nc.bd.itf.payBill.service.IPayBillPortType;
import nc.bd.itf.receiptBill.service.IReceiptBillLocator;
import nc.bd.itf.receiptBill.service.IReceiptBillPortType;
import net.sf.json.JSONObject;
import cc.dfsoft.project.biz.ifs.finance.dto.PayBillInfoDTO;
import cc.dfsoft.project.biz.ifs.finance.dto.PaymentDTO;
import cc.dfsoft.project.biz.ifs.finance.dto.PaymentSuccessInfoDTO;
import cc.dfsoft.project.biz.ifs.finance.enums.UpdateTypeEnum;


public class TestWebService {

	public static void main(String[]args)
	{
		JSONObject object = JSONObject.fromObject("{\"ret_types\":\"0\"}");
		PaymentSuccessInfoDTO dto = (PaymentSuccessInfoDTO) JSONObject.toBean(object, PaymentSuccessInfoDTO.class);
		System.err.println("sss"+dto.getBill_id());
		//JaxWsProxyFactoryBean jwpfb = new JaxWsProxyFactoryBean();  
		//jwpfb.setServiceClass(FinanceService.class);  
		//jwpfb.setAddress("http://192.168.199.111:8081/uapws/service/nc.itf.gzrq.b01.IContractItf?wsdl");
		
		FinanceService();
		
		//ReceiptBill();
		//MaterialService();
		//payBill();
		
		System.err.println(new BigDecimal(12).subtract(new BigDecimal(2)));
	}
	
	private static void FinanceService(){
		IContractItfLocator customerInfo = new IContractItfLocator();
		IContractItfPortType infoPortType;
		try {
		infoPortType = customerInfo.getIContractItfSOAP11port_http();
		//
		String result = infoPortType.doCcontractAdd("username", "pwd", "{\"settleInfo\":null,\"operate_type\":\"0-C\",\"update_type\":\"\",\"cons_constractInfo\":{\"work_remark\":\"\",\"supervision_unit\":\"贵州化兴建设监理有限公司\",\"work_money\":\"11793.57\",\"work_unit\":\"贵阳鸿源燃气建设发展有限公司\",\"supervision_code\":\"5233\",\"work_unit_code\":\"5230\",\"shuilv\":\"B03\",\"work_code\":\"52010818010480\",\"work_date\":\"\"},\"projectInfo\":{\"pro_name\":\"百灵尚品庭院改管工程\",\"legal\":\"\",\"company_code\":\"5201\",\"pro_status\":\"待施工预算\",\"cust_code\":\"5201\",\"area_code\":\"\",\"tel\":\"\",\"design_code\":\"5228\",\"account_bank\":\"\",\"design_unit\":\"贵州燃气热力设计有限责任公司\",\"begin_date\":\"2018-01-16\",\"prj_adds\":\"宝山北路\",\"bank_lb\":\"\",\"project_type\":\"08\",\"pro_no\":\"52010818010480\",\"account_name\":\"\",\"account_no\":\"\",\"cus_tname\":\"贵州燃气集团股份有限公司\",\"pay_model\":\"0\",\"end_date\":\"\",\"cust_class\":\"\",\"company_adds\":\"\",\"area_name\":\"\",\"account_xz\":\"\"},\"constractInfo\":null}");
		System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void MaterialService(){
		MaterielAppLocator customerInfo = new MaterielAppLocator();
		MaterielAppPortType infoPortType;
		try {

			infoPortType = customerInfo.getMaterielAppSOAP11port_http();
			String result;
			result = infoPortType.doMaterAppBillAdd ("username", "pwd", "{\"operate_type\":\"2\",\"materiallistinfo\":{\"materiallist3\":{\"material_name\":\"总线型控制模块\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103079238573\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"块\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist4\":{\"material_name\":\"三通标识块\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103095081976\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist1\":{\"material_name\":\"同心大小头\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103015489499\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"2.00\",\"material_no\":\"\"},\"materiallist2\":{\"material_name\":\"警示带\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103043488374\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"10.00\",\"material_no\":\"\"},\"materiallist25\":{\"material_name\":\"皮膜表\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103491946176\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"块\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist26\":{\"material_name\":\"T型三通盒\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103505510818\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"套\",\"material_num\":\"6.00\",\"material_no\":\"\"},\"materiallist27\":{\"material_name\":\"燃气报警探测器\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103631037593\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"6.00\",\"material_no\":\"\"},\"materiallist28\":{\"material_name\":\"丝接球阀\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103652036323\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"3.00\",\"material_no\":\"\"},\"materiallist29\":{\"material_name\":\"灶具管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103658058892\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"根\",\"material_num\":\"24.00\",\"material_no\":\"\"},\"materiallist9\":{\"material_name\":\"末端标识块\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103191147729\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist20\":{\"material_name\":\"钢制异径三通\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103372629984\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"4.00\",\"material_no\":\"\"},\"materiallist8\":{\"material_name\":\"法兰片\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103188616164\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"片\",\"material_num\":\"3.00\",\"material_no\":\"\"},\"materiallist21\":{\"material_name\":\"法兰球阀\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103428172388\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist7\":{\"material_name\":\"LF-防护板\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103169741312\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"80.00\",\"material_no\":\"\"},\"materiallist22\":{\"material_name\":\"钢制变径\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103430936642\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist6\":{\"material_name\":\"G6表支架（油漆防腐）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103156906028\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist23\":{\"material_name\":\"电缆保护钢管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103444799772\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"50.00\",\"material_no\":\"\"},\"materiallist5\":{\"material_name\":\"支架（油漆防腐）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103096542952\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"19.00\",\"material_no\":\"\"},\"materiallist24\":{\"material_name\":\"90度钢制冲压弯头\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103475014879\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"3.00\",\"material_no\":\"\"},\"materiallist38\":{\"material_name\":\"不锈钢波纹软管(暗埋)\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103988605976\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"80.00\",\"material_no\":\"\"},\"materiallist39\":{\"material_name\":\"哈弗快装接头\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103997021300\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"套\",\"material_num\":\"85.00\",\"material_no\":\"\"},\"materiallist36\":{\"material_name\":\"PE直接头（套管）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103928698035\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"10.00\",\"material_no\":\"\"},\"materiallist18\":{\"material_name\":\"镀锌钢管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103329196164\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"5.00\",\"material_no\":\"\"},\"materiallist37\":{\"material_name\":\"供电电缆\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103972240919\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"50.00\",\"material_no\":\"\"},\"materiallist19\":{\"material_name\":\"通讯电缆\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103355090608\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"50.00\",\"material_no\":\"\"},\"materiallist16\":{\"material_name\":\"无缝钢管（油漆防腐）\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103301534817\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"5.00\",\"material_no\":\"\"},\"materiallist17\":{\"material_name\":\"钢法兰片\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103311572179\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"片\",\"material_num\":\"4.00\",\"material_no\":\"\"},\"materiallist14\":{\"material_name\":\"电磁切断阀\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103282840133\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist15\":{\"material_name\":\"无缝钢管(3PE防腐)\",\"get_anum\":\"\",\"mapping_guize\":\"JEQ-80/1.6\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103283127789\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist30\":{\"material_name\":\"PE专用套管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103695184239\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"米\",\"material_num\":\"80.00\",\"material_no\":\"\"},\"materiallist12\":{\"material_name\":\"G16表支架（油漆防腐）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103240360491\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"2.00\",\"material_no\":\"\"},\"materiallist31\":{\"material_name\":\"支架\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103697779681\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"5.00\",\"material_no\":\"\"},\"materiallist13\":{\"material_name\":\"燃气调压箱\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103280315869\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"套\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist10\":{\"material_name\":\"90°向上弯头开关盒\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103195319988\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"套\",\"material_num\":\"7.00\",\"material_no\":\"\"},\"materiallist11\":{\"material_name\":\"尼龙扎带\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103215772465\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"包\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist34\":{\"material_name\":\"出地套管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103796500454\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"1.00\",\"material_no\":\"\"},\"materiallist35\":{\"material_name\":\"90°冲压弯头（油漆防腐）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103846878156\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"7.00\",\"material_no\":\"\"},\"materiallist32\":{\"material_name\":\"防爆绕性管\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103767629906\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"根\",\"material_num\":\"8.00\",\"material_no\":\"\"},\"materiallist33\":{\"material_name\":\"PE大弯头（套管）\",\"get_anum\":\"\",\"mapping_guize\":\"DN63（D76*4）20#\",\"material_receive_num\":\"\",\"primary_key\":\"201712111108144560103771608771\",\"material_table_type\":\"1\",\"material_spec\":\"\",\"material_unit\":\"个\",\"material_num\":\"13.00\",\"material_no\":\"\"}},\"projinfo\":{\"proj_addr\":\"南明区花果园J区3栋\",\"cust_code\":\"C5201128\",\"org_code\":\"\",\"proj_id\":\"201712051640247100101039146123\",\"opercode\":\"\",\"proj_name\":\"小幺鸡火锅（吴福启）大锅灶公建燃气安装工程\",\"billdate\":\"\",\"custcode\":\"\",\"work_code\":\"52010317120210\",\"billno\":\"\",\"proj_no\":\"52010317120210\",\"unit_code\":\"01\",\"construction_unit\":\"\"}}");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void ReceiptBill(){
		IReceiptBillLocator customerInfo = new IReceiptBillLocator();
		IReceiptBillPortType infoPortType;
		try {

			infoPortType = customerInfo.getIReceiptBillSOAP11port_http();
			String result="";
			String jsonStr ="{\"operate_type\":\"2\",\"receiptInfo\":{\"company_code\":\"5201\",\"oper_name\":\"游勇军\",\"receiptType\":\"01\",\"remark\":\"首付款\",\"cust_code\":\"C5201106\",\"dept_code\":\"52010101\",\"dept_name\":\"市场发展部\",\"project_type\":\"03\",\"oper_code\":\"youyj\",\"money\":\"36489.00\",\"pro_code\":\"52010318020010\",\"shuilv\":\"B03\",\"bill_id\":\"201803071444397940106863774502\",\"bill_date\":\"2018-03-07 08:00:00\",\"contract_code\":\"52010318020010\"}}";
			result = infoPortType.doReceiptBill("username", "pwd", jsonStr);
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
	
	private static void payBill(){
		
		IPayBillLocator customerInfo = new IPayBillLocator();
		IPayBillPortType infoPortType;
		PaymentDTO payInfo = new PaymentDTO();
		payInfo.setPro_no("123");
		payInfo.setPrj_name("12345");
		payInfo.setBill_id("122");
		payInfo.setContract_code("5201");
		payInfo.setContract_money("213456.00");
		payInfo.setDept_code("1101123");
		payInfo.setOper_code("yezq");
		payInfo.setOper_name("也争气");		
		payInfo.setPay_bankno("123456789098789");
		payInfo.setPay_type("01");
		payInfo.setWork_date("2017-11-12");
		payInfo.setWork_money("1234567.90");
		payInfo.setWork_unit("123");
		payInfo.setShuilv("11");
		payInfo.setOrg_code("2334");

		PayBillInfoDTO payBillInfoDTO = new PayBillInfoDTO();
		payBillInfoDTO.setOperate_type(UpdateTypeEnum.INSERT.getValue());
		payBillInfoDTO.setPayInfo(payInfo);
		try {

			infoPortType = customerInfo.getIPayBillSOAP11port_http();
			String result;
			result = infoPortType.doPayBill("username", "pwd", JSONObject.fromObject(payBillInfoDTO).toString());
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
		}
	}
}
