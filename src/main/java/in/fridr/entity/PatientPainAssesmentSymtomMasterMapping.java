package in.fridr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "patient_pain_assesment_symptom_master_mapping")
public class PatientPainAssesmentSymtomMasterMapping {
	
	@Id
	@SequenceGenerator(name = "patient_pain_assesment_symptom_master_mapping_sequence_generator",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name="symtom_master_id")
	private SymptomsMaster symptomsMaster;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="patient_pain_assesment_id")
	private PatientPainAssessments patientPainAssessments;

	public PatientPainAssesmentSymtomMasterMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientPainAssesmentSymtomMasterMapping(SymptomsMaster symptomsMaster,
			PatientPainAssessments patientPainAssessments) {
		super();
		this.symptomsMaster = symptomsMaster;
		this.patientPainAssessments = patientPainAssessments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SymptomsMaster getSymptomsMaster() {
		return symptomsMaster;
	}

	public void setSymptomsMaster(SymptomsMaster symptomsMaster) {
		this.symptomsMaster = symptomsMaster;
	}

	public PatientPainAssessments getPatientPainAssessments() {
		return patientPainAssessments;
	}

	public void setPatientPainAssessments(PatientPainAssessments patientPainAssessments) {
		this.patientPainAssessments = patientPainAssessments;
	}
	
	

}
