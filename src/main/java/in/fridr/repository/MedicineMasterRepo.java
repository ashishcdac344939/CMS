package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.fridr.entity.MedicineMaster;
import in.fridr.projection.MedicineQuantityMapProjection;
@Repository
public interface MedicineMasterRepo extends JpaRepository<MedicineMaster, Integer> {

	@Query(value = "SELECT mm.medicine_name AS medicineName, SUM(CAST(pmp.medicine_quantity AS integer)) AS quantity " +
	           "FROM patient_visit_diagnosis_details AS pv " +
	           "INNER JOIN user_credential AS u ON u.id= pv.d_user_cred_id " +
	           "INNER JOIN patient_medicine_prescription pmp on pv.pvdd_id = pmp.pv_id " +
	           "INNER JOIN medicine_master mm on mm.medicine_id = pmp.medicine_master_id " +
	           "WHERE u.id = ?1 and DATE(pv.invistigation_date) >= CAST(?2 AS DATE) AND pv.invistigation_date <= (CAST(?3 AS DATE) + 1) and mm.is_3e_form_required = true " +
	           "GROUP BY mm.medicine_name",  nativeQuery = true)
	List<MedicineQuantityMapProjection> getMedicineQuantitiesForUserWith3EFormRequired(int docId, String barInitialDate, String barTillDate);
	
	@Query(value = "select * from medicine_master where center_details_id=?1",nativeQuery = true)
	List<MedicineMaster> getMedicineByCenter(int centerId);

}
