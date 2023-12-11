package in.fridr.modal;

import java.util.List;

public class MedicineAllocationModel {

	int pvddId;
	int pharmasistCredId;
    List<PMPAndMedicineAllocatedModel> pmpANDCount;
    
	public MedicineAllocationModel(int pvddId, int pharmasistCredId, List<PMPAndMedicineAllocatedModel> pmpANDCount) {
		super();
		this.pvddId = pvddId;
		this.pharmasistCredId = pharmasistCredId;
		this.pmpANDCount = pmpANDCount;
	}

	public MedicineAllocationModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPvddId() {
		return pvddId;
	}

	public void setPvddId(int pvddId) {
		this.pvddId = pvddId;
	}

	public int getPharmasistCredId() {
		return pharmasistCredId;
	}

	public void setPharmasistCredId(int pharmasistCredId) {
		this.pharmasistCredId = pharmasistCredId;
	}

	public List<PMPAndMedicineAllocatedModel> getPmpANDCount() {
		return pmpANDCount;
	}

	public void setPmpANDCount(List<PMPAndMedicineAllocatedModel> pmpANDCount) {
		this.pmpANDCount = pmpANDCount;
	}

	@Override
	public String toString() {
		return "MedicineAllocationModel [pvddId=" + pvddId + ", pharmasistCredId=" + pharmasistCredId + ", pmpANDCount="
				+ pmpANDCount + "]";
	}
    
	
    
}
