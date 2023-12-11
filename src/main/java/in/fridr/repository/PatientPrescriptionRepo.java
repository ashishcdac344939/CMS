package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.PatientMedicinePrescription;
import in.fridr.projection.PatientPrescptionProjection;

@Repository
public interface PatientPrescriptionRepo extends JpaRepository<PatientMedicinePrescription, Integer> {

	
	@Query(value = "select  pd.d_user_cred_id as docId, ud.address ,ud.user_unique_id as adharno,ud.user_name as name ,pd.p_dob as dob, mm.medicine_name as\r\n"
			+ "medicineName,pmp.no_of_days as noOfDays,pmp.days_interval as daysInterval,pmp.medicine_in_a_day as medicineInADays, pmp.medicine_quantity as medicineQuantity,\r\n"
			+ "pmp.medicine_remark as remark,\r\n"
			+ "mm.is_3e_form_required as is3ERequired , mm.current_available_count as currentAvailableCount, pmp.pv_id as pvId,pmp.patient_medicine_prescription_id as pmpId\r\n"
			+ "from user_details ud\r\n"
			+ "inner join patient_details pd on ud.user_details_id = pd.user_details_id\r\n"
			+ "inner join patient_medicine_prescription pmp on pmp.pd_id= pd.pd_id\r\n"
			+ "inner join medicine_master mm on mm.medicine_id = pmp.medicine_master_id\r\n"
			+ "where pmp.pv_id=?1", nativeQuery = true)
	List<PatientPrescptionProjection> getPatientPrescptioon(int patientVisitDetails);
}
