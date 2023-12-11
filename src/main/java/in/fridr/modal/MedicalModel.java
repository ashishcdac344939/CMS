package in.fridr.modal;

import java.util.List;

import in.fridr.projection.KeyValueModel;
import in.fridr.projection.PatientMedicalVisitRecordModel;
import in.fridr.projection.PatientPrescptionProjection;

public class MedicalModel {

	
	List<PatientPrescptionProjection> prescription;
	
	List<PatientMedicalVisitRecordModel> medicalVisitRecords;
	
	List<KeyValueModel> pmpAndAllocatedMedicineCount;

	public MedicalModel(List<PatientPrescptionProjection> prescription,
			List<PatientMedicalVisitRecordModel> medicalVisitRecords, List<KeyValueModel> pmpAndAllocatedMedicineCount) {
		super();
		this.prescription = prescription;
		this.medicalVisitRecords = medicalVisitRecords;
		this.pmpAndAllocatedMedicineCount = pmpAndAllocatedMedicineCount;
	}

	public MedicalModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<PatientPrescptionProjection> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<PatientPrescptionProjection> prescription) {
		this.prescription = prescription;
	}

	public List<PatientMedicalVisitRecordModel> getMedicalVisitRecords() {
		return medicalVisitRecords;
	}

	public void setMedicalVisitRecords(List<PatientMedicalVisitRecordModel> medicalVisitRecords) {
		this.medicalVisitRecords = medicalVisitRecords;
	}

	public List<KeyValueModel> getPmpAndAllocatedMedicineCount() {
		return pmpAndAllocatedMedicineCount;
	}

	public void setPmpAndAllocatedMedicineCount(List<KeyValueModel> pmpAndAllocatedMedicineCount) {
		this.pmpAndAllocatedMedicineCount = pmpAndAllocatedMedicineCount;
	}
	
	
}
