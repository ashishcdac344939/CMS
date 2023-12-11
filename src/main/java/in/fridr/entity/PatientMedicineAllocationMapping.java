package in.fridr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "patient_medicine_allocation_record")
public class PatientMedicineAllocationMapping {

	@Id
	@SequenceGenerator(name = "patient_medical_allocation_record_sequence_generator", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_medical_allocation_record_id")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name="patient_medical_visit_record_id")
	private PatientMedicalVisitRecord pmvr;
	
	@ManyToOne()
	@JoinColumn(name="patient_medicine_prescription_id")
	private PatientMedicinePrescription pmpId;
	
	@Column(name="medicine_allocated_count")
	private int medicineAllocatedCount;

	
	
	public PatientMedicineAllocationMapping(PatientMedicalVisitRecord pmvr, PatientMedicinePrescription pmpId,
			int medicineAllocatedCount) {
		super();
		this.pmvr = pmvr;
		this.pmpId = pmpId;
		this.medicineAllocatedCount = medicineAllocatedCount;
	}

	public PatientMedicineAllocationMapping(Integer id, PatientMedicalVisitRecord pmvr, PatientMedicinePrescription pmpId,
			int medicineAllocatedCount) {
		super();
		this.id = id;
		this.pmvr = pmvr;
		this.pmpId = pmpId;
		this.medicineAllocatedCount = medicineAllocatedCount;
	}

	public PatientMedicineAllocationMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PatientMedicalVisitRecord getPmvr() {
		return pmvr;
	}

	public void setPmvr(PatientMedicalVisitRecord pmvr) {
		this.pmvr = pmvr;
	}

	public PatientMedicinePrescription getPmpId() {
		return pmpId;
	}

	public void setPmpId(PatientMedicinePrescription pmpId) {
		this.pmpId = pmpId;
	}

	public int getMedicineAllocatedCount() {
		return medicineAllocatedCount;
	}

	public void setMedicineAllocatedCount(int medicineAllocatedCount) {
		this.medicineAllocatedCount = medicineAllocatedCount;
	}
	
	
}
