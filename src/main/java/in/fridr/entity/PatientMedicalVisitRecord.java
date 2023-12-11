package in.fridr.entity;

import java.sql.Timestamp;

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
@Table(name = "patient_medical_visit_record")
public class PatientMedicalVisitRecord {
	
	@Id
	@SequenceGenerator(name = "patient_medical_visit_record_sequence_generator", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_medical_visit_record_id")
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name="pharmasist_id")
	private UserCredential pharmacistCred;
	
	@ManyToOne()
	@JoinColumn(name="patient_vistit_id")
	private PatientVisitDiagnosisDetail pvId;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;

	public PatientMedicalVisitRecord(Integer id, UserCredential pharmacistCred, PatientVisitDiagnosisDetail pvId,
			Timestamp recordTracking) {
		super();
		this.id = id;
		this.pharmacistCred = pharmacistCred;
		this.pvId = pvId;
		this.recordTracking = recordTracking;
	}

	public PatientMedicalVisitRecord() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserCredential getPharmacistCred() {
		return pharmacistCred;
	}

	public void setPharmacistCred(UserCredential pharmacistCred) {
		this.pharmacistCred = pharmacistCred;
	}

	public PatientVisitDiagnosisDetail getPvId() {
		return pvId;
	}

	public void setPvId(PatientVisitDiagnosisDetail pvId) {
		this.pvId = pvId;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	
	public PatientMedicalVisitRecord(UserCredential pharmacistCred, PatientVisitDiagnosisDetail pvId,
			Timestamp recordTracking) {
		super();
		this.pharmacistCred = pharmacistCred;
		this.pvId = pvId;
		this.recordTracking = recordTracking;
	}
	
	
	

}
