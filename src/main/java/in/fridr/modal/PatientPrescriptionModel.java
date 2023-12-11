package in.fridr.modal;

import java.util.List;

import javax.persistence.Column;

import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientVisitDiagnosisDetail;

public class PatientPrescriptionModel {

	private PatientDetail patientDetail;	
	private PatientVisitDiagnosisDetail patientVisitDiagnosisDetail;
	private List<MedicineAndQuantityModel> medicineAndQuantity;
	
	public PatientPrescriptionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public PatientPrescriptionModel(PatientDetail patientDetail, Boolean morphinApplicable,
			List<MedicineAndQuantityModel> medicineAndQuantity) {
		super();
		this.patientDetail = patientDetail;
		this.medicineAndQuantity = medicineAndQuantity;
	}


	public List<MedicineAndQuantityModel> getMedicineAndQuantity() {
		return medicineAndQuantity;
	}



	public void setMedicineAndQuantity(List<MedicineAndQuantityModel> medicineAndQuantity) {
		this.medicineAndQuantity = medicineAndQuantity;
	}



	public PatientDetail getPatientDetail() {
		return patientDetail;
	}
	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}



	public PatientVisitDiagnosisDetail getPatientVisitDiagnosisDetail() {
		return patientVisitDiagnosisDetail;
	}



	public void setPatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}


	
}
