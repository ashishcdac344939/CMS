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
@Table(name="patient_treatment_received")
public class PatientTreatmentRecevied {

	
	@Id
	@SequenceGenerator(name = "patient_treatment_received_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ptr_id")
	private Integer ptrId;
	
	@Column(name = "received_treatment_name")
	private String TreatmentReceived;
	
	@ManyToOne()
	@JoinColumn(name = "patient_visti_diagnosis_details_id")
	private PatientVisitDiagnosisDetail patientVisitDiagnosisDetail;

	public PatientTreatmentRecevied(Integer ptrId, String treatmentReceived,
			PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		super();
		this.ptrId = ptrId;
		TreatmentReceived = treatmentReceived;
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}

	
	public PatientTreatmentRecevied(String treatmentReceived, PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		super();
		TreatmentReceived = treatmentReceived;
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}


	public PatientTreatmentRecevied() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPtrId() {
		return ptrId;
	}

	public void setPtrId(Integer ptrId) {
		this.ptrId = ptrId;
	}

	public String getTreatmentReceived() {
		return TreatmentReceived;
	}

	public void setTreatmentReceived(String treatmentReceived) {
		TreatmentReceived = treatmentReceived;
	}

	public PatientVisitDiagnosisDetail getPatientVisitDiagnosisDetail() {
		return patientVisitDiagnosisDetail;
	}

	public void setPatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}
	
	
	
}
