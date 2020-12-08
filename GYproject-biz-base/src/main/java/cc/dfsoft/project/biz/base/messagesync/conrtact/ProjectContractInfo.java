package cc.dfsoft.project.biz.base.messagesync.conrtact;

import java.util.List;

public class ProjectContractInfo {

	private ProjectInfo project;
	private ContractInfo contract;
	private List<SupplementalContractInfo> supplementalContract;
	private SubContractInfo subContract;
	public ProjectInfo getProject() {
		return project;
	}
	public void setProject(ProjectInfo project) {
		this.project = project;
	}
	public ContractInfo getContract() {
		return contract;
	}
	public void setContract(ContractInfo contract) {
		this.contract = contract;
	}
	public List<SupplementalContractInfo> getSupplementalContract() {
		return supplementalContract;
	}
	public void setSupplementalContract(
			List<SupplementalContractInfo> supplementalContract) {
		this.supplementalContract = supplementalContract;
	}
	public SubContractInfo getSubContract() {
		return subContract;
	}
	public void setSubContract(SubContractInfo subContract) {
		this.subContract = subContract;
	}
	
	
}
