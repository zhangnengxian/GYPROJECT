package cc.dfsoft.project.biz.base.baseinfo.dto;
import cc.dfsoft.uexpress.common.dto.PageSortReq;
public class IncrementReg extends PageSortReq{
		private String inId;   //税率主键
         private String increment; //税率值
         private String incrementCode; //税率编码
         public IncrementReg() {
     		super();
     	}
		public String getInId() {
			return inId;
		}
		public void setInId(String inId) {
			this.inId = inId;
		}
		public String getIncrement() {
			return increment;
		}
		public void setIncrement(String increment) {
			this.increment = increment;
		}
		public String getIncrementCode() {
			return incrementCode;
		}
		public void setIncrementCode(String incrementCode) {
			this.incrementCode = incrementCode;
		}
         
}
