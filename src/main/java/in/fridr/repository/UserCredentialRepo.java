package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.UserCredential;
import in.fridr.projection.PatientDataModel;
import in.fridr.projection.PatientPrescptionProjection;

@Repository
public interface UserCredentialRepo extends JpaRepository<UserCredential, Integer> {

	@Query(value = "select  u.user_details_id as patientUcId,pd.pd_id as patientId\r\n"
			+ "		 from user_credential uc \r\n"
			+ "			inner join user_details u on u.user_details_id= uc.user_details_id\r\n"
			+ "			inner join patient_details pd on pd.user_details_id = u.user_details_id\r\n"
			+ " 			where uc.user_id=?1",nativeQuery = true)
	List<PatientDataModel> findByUserId(String emailId);
	
	@Query(value = "select  u.user_details_id as patientUcId,pd.pd_id as patientId\r\n"
			+ "		 from user_credential uc \r\n"
			+ "			inner join user_details u on u.user_details_id= uc.user_details_id\r\n"
			+ "			inner join patient_details pd on pd.user_details_id = u.user_details_id\r\n"
			+ " 			where u.user_mobile=?1",nativeQuery = true)
	List<PatientDataModel> findBymobNo(String mobNo);

	@Query(value = "select  u.user_details_id as patientUcId,pd.pd_id as patientId\r\n"
			+ "		 from user_credential uc \r\n"
			+ "			inner join user_details u on u.user_details_id= uc.user_details_id\r\n"
			+ "			inner join patient_details pd on pd.user_details_id = u.user_details_id\r\n"
			+ " 			where u.user_unique_id=?1",nativeQuery = true)
	List<PatientDataModel> findByuniqueId(String uniqueId);
	
	@Query(value  = "select cd.center_details_id from user_credential uc \r\n"
			+ " inner join user_details  ud on uc.user_details_id = ud.user_details_id \r\n"
			+ " inner join center_details cd on cd.center_details_id = ud.center_details_id\r\n"
			+ " where uc.id=?1",nativeQuery = true)
	int findCenterIdbyUserCredId(int userCredId);
	
	@Query(value = "select uc from UserCredential as uc where uc.userId=?1")
	UserCredential findByUserName(String userId);
	
	@Query(value = "select ud.user_name from user_details ud \r\n"
			+ "inner join user_credential uc on uc.user_details_id = ud.user_details_id\r\n"
			+ "where uc.user_id=?1",nativeQuery = true)
	String getUserNameByUserId(String userId);
	@Query(value = "select * from user_credential as uc "
			+ "where uc.reset_password_token=?1",nativeQuery = true)
	UserCredential findByResetPasswordToken(String token);

	@Query(value = "select * from user_credential as uc "
			+ "where uc.user_id=?1",nativeQuery = true)
	UserCredential findByUserUserId(String email); 

	
	@Query(value = "select ud.user_name as docName, ud.user_unique_id as docRegNo from user_details ud \r\n"
			+ "INNER join user_credential uc on uc.user_details_id = ud.user_details_id \r\n"
			 + "where uc.id=?1",nativeQuery = true)
	 List<PatientPrescptionProjection> getUserByUSerCredId(int userCredId);
}
