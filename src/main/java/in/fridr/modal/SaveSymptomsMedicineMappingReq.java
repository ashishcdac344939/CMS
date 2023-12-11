package in.fridr.modal;

import java.util.List;

public class SaveSymptomsMedicineMappingReq {
	private int symptomId;
	private List<Integer> medicineIds;
	
	public SaveSymptomsMedicineMappingReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SaveSymptomsMedicineMappingReq(int symptomId, List<Integer> medicineIds) {
		super();
		this.symptomId = symptomId;
		this.medicineIds = medicineIds;
	}
	public int getSymptomId() {
		return symptomId;
	}
	public void setSymptomId(int symptomId) {
		this.symptomId = symptomId;
	}
	public List<Integer> getMedicineIds() {
		return medicineIds;
	}
	public void setMedicineIds(List<Integer> medicineIds) {
		this.medicineIds = medicineIds;
	}
	
}
