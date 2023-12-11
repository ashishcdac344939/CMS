package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientEdmontonSymptomAssessment;

@Repository
public interface PatientEdmontonSymptomAssessmentRepo extends JpaRepository<PatientEdmontonSymptomAssessment,Integer> {

	List<PatientEdmontonSymptomAssessment> findByesaUserCredentialId(int patientCredId);

	List<PatientEdmontonSymptomAssessment> findByesaUserCredentialIdOrderByRecordTrackingAsc(int pCredId); 
	
	@Query(value = "select esa from PatientEdmontonSymptomAssessment as esa where esa.esaUserCredential.userId=?1")
	List<PatientEdmontonSymptomAssessment> findByesaUserId(String pCredId); 

}
