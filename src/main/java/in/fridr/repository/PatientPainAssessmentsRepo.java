package in.fridr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientPainAssessments;
@Repository
public interface PatientPainAssessmentsRepo extends JpaRepository<PatientPainAssessments, Integer>{

	PatientPainAssessments findByPatientVisitDiagnosisDetailPvId(int id);


}
