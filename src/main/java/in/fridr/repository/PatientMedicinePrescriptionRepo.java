package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import in.fridr.entity.MedicineMaster;
import in.fridr.entity.PatientMedicinePrescription;

public interface PatientMedicinePrescriptionRepo extends JpaRepository<PatientMedicinePrescription, Integer>{
	
	
	@Query(value = "select mm.medicine_id from patient_medicine_prescription pmp\r\n"
			+ " inner join symptom_medicine_mapping smp on pmp.symptom_medicine_mapping_id = smp.symptom_medicine_mapping_id\r\n"
			+ " inner join medicine_master mm on mm.medicine_id = smp.medicine_id\r\n"
			+ " where pmp.patient_medicine_prescription_id=?1",nativeQuery = true)
	int getMedicineMasterByByPatientMedicinePrescription(int p);

	Object findByPatientVisitDiagnosisDetailPvId(int patientVisitId);
	@Query(value = "select * from patient_medicine_prescription as pmp "
			+ " where pmp.pv_id = ?1 ",nativeQuery = true)
	List<Object> findByPatientMedicinePrescriptionPatientVisitDiagnosisDetailPvId(int patientVisitId);

}
