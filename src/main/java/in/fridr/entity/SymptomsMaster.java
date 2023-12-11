package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the symptoms_master database table.
 * 
 */
@Entity
@Table(name="symptoms_master")
@NamedQuery(name="SymptomsMaster.findAll", query="SELECT s FROM SymptomsMaster s")
public class SymptomsMaster implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "symptoms_master_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="symptom_id")
	private Integer symptomId;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	private String symptom;

	@Column(name="symptom_status")
	private Boolean symptomStatus;

	//bi-directional many-to-one association to PatientPainAssessments_
	@OneToMany(mappedBy="symptomsMaster")
	private List<PatientPainAssessments> patientPainAssessments;
	@JsonIgnore
	@OneToMany(mappedBy = "symptomsMaster")
	private List<SymptomMedicineMapping> symptomMedicineMappings;
	
	public SymptomsMaster(Integer symptomId) {
		super(); 
		this.symptomId = symptomId;
	}

	public SymptomsMaster() {
	}

	public Integer getSymptomId() {
		return this.symptomId;
	}

	public void setSymptomId(Integer symptomId) {
		this.symptomId = symptomId;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getSymptom() {
		return this.symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public Boolean getSymptomStatus() {
		return this.symptomStatus;
	}

	public void setSymptomStatus(Boolean symptomStatus) {
		this.symptomStatus = symptomStatus;
	}

	public List<PatientPainAssessments> getPatientPainAssessments() {
		return this.patientPainAssessments;
	}

	public void setPatientPainAssessments(List<PatientPainAssessments> patientPainAssessments) {
		this.patientPainAssessments = patientPainAssessments;
	}

	public PatientPainAssessments addPatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().add(patientPainAssessment);
		patientPainAssessment.setSymptomsMaster(this);

		return patientPainAssessment;
	}

	public PatientPainAssessments removePatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().remove(patientPainAssessment);
		patientPainAssessment.setSymptomsMaster(null);

		return patientPainAssessment;
	}

	public List<SymptomMedicineMapping> getSymptomMedicineMappings() {
		return symptomMedicineMappings;
	}

	public void setSymptomMedicineMappings(List<SymptomMedicineMapping> symptomMedicineMappings) {
		this.symptomMedicineMappings = symptomMedicineMappings;
	}
	

}