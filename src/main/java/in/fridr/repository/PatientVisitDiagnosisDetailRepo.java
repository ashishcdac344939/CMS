package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.projection.PatientDataModel;


@Repository
public interface PatientVisitDiagnosisDetailRepo extends JpaRepository<PatientVisitDiagnosisDetail, Integer>{
	
	// note patient emailid is saved in usercredential table as column name "userId"
	@Query(value = "SELECT pv.pvdd_id AS pvid, pv.d_user_cred_id AS doctorUcId, pv.p_user_cred_id AS patientUcId, "
            + "pv.pd_id AS patientId, pv.invistigation_date AS invistigationdate, pv.next_visit_date AS nextvisitdate, "
            + "pv.pd_current_diagnosis AS pdcurrentdiagnosis, pv.pd_stage_of_cancer AS pdstageOfcancer, pv.pd_present_status AS presentStatus, pv.important_finding AS importantFindings, pv.pd_secondaries AS secondaryFindings, "
            + "ud.user_name AS userName "
            + "FROM patient_visit_diagnosis_details pv "
            + "INNER JOIN user_credential u ON u.id = pv.p_user_cred_id "
            + "INNER JOIN user_details AS ud ON ud.user_details_id = u.user_details_id "
            + "WHERE u.user_id = ?1", nativeQuery = true)
	List<PatientDataModel> findByPatientEmailId(String emailId);
	
	@Query(value = "select pv.pvdd_id as pvid, pv.invistigation_date as invistigationdate ,pv.pd_current_diagnosis as pdcurrentdiagnosis,pv.pd_stage_of_cancer as\r\n"
			+ "pdstageOfcancer,pv.pd_treatment_received as pdtreatmentreceived , ud.user_name as userName \r\n"
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join user_credential u on u.id= pv.p_user_cred_id\r\n"
			+ "inner join user_details ud on ud.user_details_id = u.user_details_id\r\n"
			+ "where ud.user_mobile = ?1",nativeQuery = true)
	List<PatientDataModel> findByPatientMobNo(String mobileNo);
	
	@Query(value = "select pv.pvdd_id as pvid, pv.invistigation_date as invistigationdate ,pv.pd_current_diagnosis as pdcurrentdiagnosis,pv.pd_stage_of_cancer as\r\n"
			+ "pdstageOfcancer,pv.pd_treatment_received as pdtreatmentreceived , ud.user_name as userName  \r\n"
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join user_credential u on u.id= pv.p_user_cred_id\r\n"
			+ "inner join user_details ud on ud.user_details_id = u.user_details_id\r\n"
			+ "where ud.user_unique_id = ?1",nativeQuery = true)
	List<PatientDataModel> findByPatientUniqueId(String uniqueId);

	@Query(value = "select pv.pvdd_id as pvid, pv.invistigation_date as invistigationdate ,pv.pd_current_diagnosis as pdcurrentdiagnosis,pv.pd_stage_of_cancer as\r\n"
			+ "pdstageOfcancer \r\n"
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join user_credential u on u.id= pv.d_user_cred_id\r\n"
			+ "where u.id = ?1",nativeQuery = true)
	List<PatientDataModel> getNumberOfVisitdBydocId(int par);

	@Query(value = "select pv.p_user_cred_id "
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join user_credential u on u.id= pv.d_user_cred_id\r\n"
			+ "inner join user_details ud on ud.user_details_id = u.user_details_id\r\n"
			+ "where u.id = ?1 and ud.user_status = true GROUP BY pv.p_user_cred_id",nativeQuery = true)
	List<PatientDataModel> getNumberOfActivePatientdocId(int docId);

	@Query(value = "select pv.pvdd_id as pvid, pv.invistigation_date as invistigationdate ,pv.pd_current_diagnosis as pdcurrentdiagnosis,pv.pd_stage_of_cancer as pdstageOfcancer\r\n"
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join patient_medicine_prescription px on px.pv_id= pv.pvdd_id\r\n"
			+ "INNER JOIN medicine_master mm on mm.medicine_id = px.medicine_master_id\r\n"
			+ "where pv.d_user_cred_id = ?1 and mm.is_3e_form_required = true",nativeQuery = true)
	List<PatientDataModel> getNumberOfMorphinAllocated(int docId);
	@Query(value = "select pv.p_user_cred_id "
			+ "from patient_visit_diagnosis_details pv\r\n"
			+ "inner join user_credential u on u.id= pv.d_user_cred_id\r\n"
			+ "where u.id = ?1 GROUP BY pv.p_user_cred_id",nativeQuery = true)
	List<PatientDataModel> getNumberOfTotalPatientdocId(int docId);
	
	/*
	 * @Query(value = "SELECT COUNT(*) " +
	 * "FROM patient_visit_diagnosis_details pv " +
	 * "inner join user_credential u on u.id= pv.d_user_cred_id " +
	 * "inner join user_details ud on ud.user_details_id = u.user_details_id " +
	 * "WHERE pv.d_user_cred_id = ?1 and pv.invistigation_date >= CAST(?2 AS TIMESTAMP) AND pv.invistigation_date <= CAST(?3 AS TIMESTAMP) and ud.user_status = true "
	 * + "GROUP BY pv.p_user_cred_id " + "HAVING COUNT(*) = 1", nativeQuery = true)
	 */
	@Query(value = "SELECT COUNT(*) as number_of_patients " +
            "FROM ( " +
            "    SELECT pv.p_user_cred_id, COUNT(*) as visit_count " +
            "    FROM patient_visit_diagnosis_details pv " +
            "    INNER JOIN user_credential u ON u.id = pv.d_user_cred_id " +
            "    INNER JOIN user_details ud ON ud.user_details_id = u.user_details_id " +
            "    WHERE pv.d_user_cred_id = ?1 " +
            "    AND DATE(pv.invistigation_date) >= CAST(?2 AS DATE) " +
            "    AND DATE(pv.invistigation_date) <= (CAST(?3 AS DATE) + 1) " +
            "    AND ud.user_status = true " +
            "    GROUP BY pv.p_user_cred_id " +
            "    HAVING COUNT(*) = 1 " +
            ") AS multiple_visits", nativeQuery = true)
	Integer getFirstTimeVisitPatient(Integer id,String initialDate, String tillDate);

	/*
	 * @Query(value = "SELECT COUNT(*) " +
	 * "FROM patient_visit_diagnosis_details pv " +
	 * "inner join user_credential u on u.id= pv.d_user_cred_id " +
	 * "inner join user_details ud on ud.user_details_id = u.user_details_id " +
	 * "WHERE pv.d_user_cred_id = ?1 and pv.invistigation_date >= CAST(?2 AS TIMESTAMP) AND pv.invistigation_date <= CAST(?3 AS TIMESTAMP) and ud.user_status = true "
	 * + "GROUP BY pv.p_user_cred_id " + "HAVING COUNT(*) > 1", nativeQuery = true)
	 */
	@Query(value = "SELECT COUNT(*) as number_of_patients " +
            "FROM ( " +
            "    SELECT pv.p_user_cred_id, COUNT(*) as visit_count " +
            "    FROM patient_visit_diagnosis_details pv " +
            "    INNER JOIN user_credential u ON u.id = pv.d_user_cred_id " +
            "    INNER JOIN user_details ud ON ud.user_details_id = u.user_details_id " +
            "    WHERE pv.d_user_cred_id = ?1 " +
            "    AND DATE(pv.invistigation_date) >= CAST(?2 AS DATE) " +
            "    AND DATE(pv.invistigation_date) <= (CAST(?3 AS DATE) + 1) " +
            "    AND ud.user_status = true " +
            "    GROUP BY pv.p_user_cred_id " +
            "    HAVING COUNT(*) > 1 " +
            ") AS multiple_visits", nativeQuery = true)
	Integer getFollowUpVisitPatient(Integer id,String initialDate, String tillDate);
	
	@Query(value = "SELECT COUNT(*) as number_of_patients " +
            "FROM ( " +
            "    SELECT pv.p_user_cred_id, COUNT(*) as visit_count " +
            "    FROM patient_visit_diagnosis_details pv " +
            "    INNER JOIN user_credential u ON u.id = pv.d_user_cred_id " +
            "    INNER JOIN user_details ud ON ud.user_details_id = u.user_details_id " +
            "    WHERE pv.d_user_cred_id = ?1 " +
            "    AND DATE(pv.invistigation_date) >= CAST(?2 AS DATE) " +
            "    AND DATE(pv.invistigation_date) <= (CAST(?3 AS DATE) + 1) " +
            "    AND ud.user_status = false " +
            "    GROUP BY pv.p_user_cred_id " +
            ") AS multiple_visits", nativeQuery = true)
	Integer getInactivePatientbyDocId(Integer id,String initialDate, String tillDate);
	
@Query(value = "SELECT uc.user_id AS userId, ud.user_name AS userName, pv.invistigation_date AS investiGationDate " +
        "FROM patient_details AS pd " +
        "INNER JOIN patient_visit_diagnosis_details AS pv ON pv.pd_id = pd.pd_id " +
        "INNER JOIN user_credential AS uc ON uc.id = pv.p_user_cred_id " +
        "INNER JOIN user_details AS ud ON ud.user_details_id = uc.user_details_id " +
        "WHERE pv.d_user_cred_id = ?1 AND DATE(pv.next_visit_date) = DATE(?2)", nativeQuery = true)
	List<Object[]> getNextVisistByDocId(Integer docId, String formattedDate);


}
