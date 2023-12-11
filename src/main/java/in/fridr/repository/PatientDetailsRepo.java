package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientDetail;
import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.projection.DistrictPopulationProjection;

/*
 *@author ashish
 */
@Repository
public interface PatientDetailsRepo extends JpaRepository<PatientDetail, Integer> {
	@Query(value = "SELECT dm.district_name AS districtName, dm.latitude AS latitude, dm.longitude AS longitude, COUNT(*) AS population " +
            "FROM patient_details AS pd " +
            "INNER JOIN user_credential AS uc ON uc.id = pd.d_user_cred_id " +
            "INNER JOIN district_master AS dm ON dm.lgd_district_code = pd.district_master_id " +
            "WHERE uc.id = ?1 and DATE(pd.record_tracking) >= CAST(?2 AS DATE) AND DATE(pd.record_tracking) <= (CAST(?3 AS DATE)+ 1) " +
            "GROUP BY dm.district_name, dm.latitude, dm.longitude", nativeQuery = true)
	List<DistrictPopulationProjection> findBydUserCredentialId(int docId, String geoInitialDate, String geoTillDate);

	@Query(value = "select pd from PatientDetail as pd where pd.userDetail=?1")
	PatientDetail getPatientDetailByUserDetilaId(UserDetail ud);
	
	@Query(value = "select pd from PatientDetail as pd where pd.userDetail.userCredential=?1")
	PatientDetail getPatientDetailByUserCredId(UserCredential ud);
}
