package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientMedicalVisitRecord;
import in.fridr.projection.PatientMedicalVisitRecordModel;

@Repository
public interface PatientMedicalVisitRecordRepository  extends JpaRepository<PatientMedicalVisitRecord, Integer>{

	@Query(value = "select uc.user_id as userCredId,pmvr.record_tracking as visitDate,pmvr.patient_medical_visit_record_id as pmvrId from  patient_medical_visit_record pmvr \r\n"
			+ " inner join user_credential  uc on pmvr.pharmasist_id = uc.id where pmvr.patient_vistit_id=?1",nativeQuery = true)
	List<PatientMedicalVisitRecordModel> findByPvId(int pvid);
}
