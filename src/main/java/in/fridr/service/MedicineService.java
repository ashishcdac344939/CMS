package in.fridr.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;

import in.fridr.entity.MedicineMaster;
import in.fridr.entity.SymptomMedicineMapping;
import in.fridr.modal.PatientPrescriptionModel;
import in.fridr.modal.PatientPrescriptionResponseModel;
import in.fridr.projection.MedicineQuantityMapProjection;
import in.fridr.projection.PatientPrescptionProjection;

public interface MedicineService {

	List<MedicineMaster> getMedicineBySymptomId(Integer symptomId);

	boolean savePatientPrescription(PatientPrescriptionModel patientPrescriptionModel);

	ResponseEntity<?> addMedicine(MedicineMaster medicineMaster);

	List<PatientPrescriptionResponseModel> getPatientPrescption(int patientVisitDetailsId);
	
	List<MedicineMaster>  getAllMedicine(Integer userCredId);

	List<SymptomMedicineMapping> getAllSymptomMedicineMapping();
	
	int  getMedicineCountByMedicineId(int medicineId);
	
	MedicineMaster updateInventery(MedicineMaster medicineMaster);
	
    ResponseEntity<?> getPatientMedicineRecord(int pvid);
    List<MedicineQuantityMapProjection> getMedicineQuantitiesForUserWith3EFormRequired(int docId, String barInitialDate, String barTillDate);

}
