package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.fridr.entity.PatientTreatmentRecevied;

public interface PatientTreatmentReceivedRepo extends JpaRepository<PatientTreatmentRecevied, Integer>{

	
	@Query(value = "select ptr from PatientTreatmentRecevied ptr where ptr.patientVisitDiagnosisDetail.pvId=?1")
	List<PatientTreatmentRecevied> findbypatientVisitDiagnosisDetail(int  pvdd);
}
