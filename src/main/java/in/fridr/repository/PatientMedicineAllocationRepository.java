package in.fridr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.fridr.entity.PatientMedicineAllocationMapping;
import in.fridr.projection.KeyValueModel;
import in.fridr.projection.MedicineNameAndQuantityProjection;

public interface PatientMedicineAllocationRepository extends JpaRepository<PatientMedicineAllocationMapping, Integer> {

	
	@Query(value = "select pmar.patient_medicine_prescription_id as pmpId, sum(pmar.medicine_allocated_count) as medicineAllocatedCount from patient_medicine_allocation_record pmar\r\n"
			+ " inner join patient_medical_visit_record pmvr on pmvr.patient_medical_visit_record_id = pmar.patient_medical_visit_record_id\r\n"
			+ " where pmvr.patient_vistit_id=?1\r\n"
			+ " group by pmar.patient_medicine_prescription_id ",nativeQuery = true)
	List<KeyValueModel> getMedicineAllocationCount(int pvid);
	
	
	@Query(value = "select mm.medicine_name as medicineName,pmar.medicine_allocated_count as medicineAllocatedCount from patient_medicine_allocation_record pmar \r\n"
			+ " inner join patient_medical_visit_record pmvr on pmvr.patient_medical_visit_record_id = pmar.patient_medical_visit_record_id\r\n"
			+ " inner join patient_medicine_prescription pmp on pmar.patient_medicine_prescription_id = pmp.patient_medicine_prescription_id\r\n"
			+ " inner join symptom_medicine_mapping smm on smm.symptom_medicine_mapping_id = pmp.symptom_medicine_mapping_id\r\n"
			+ " inner join medicine_master mm on mm.medicine_id = smm.medicine_id \r\n"
			+ " where pmvr.patient_medical_visit_record_id=?1",nativeQuery = true)
	List<MedicineNameAndQuantityProjection> getMedicineNameAndQuantity(int pmvrid);
}
