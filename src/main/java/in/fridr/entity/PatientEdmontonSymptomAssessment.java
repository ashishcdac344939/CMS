package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent  class for the patient_edmonton_symptom_assessment database
 * table.
 * 
 */
@Entity
@Table(name = "patient_edmonton_symptom_assessment")
@NamedQuery(name = "PatientEdmontonSymptomAssessment.findAll", query = "SELECT p FROM PatientEdmontonSymptomAssessment p")
public class PatientEdmontonSymptomAssessment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_esa_seq_gen", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "completed_by")
	private String completedBy;
	// bi-directional many-to-one association to UserCredential
	
	@Column(name = "pain")
	private String pain;
	@Column(name = "tiredness")
	private String tiredness;
	@Column(name = "nausea")
	private String nausea;
	@Column(name = "depression")
	private String depression;
	@Column(name = "anxiety")
	private String anxiety;
	@Column(name = "drowsiness")
	private String drowsiness;
	@Column(name = "appetite")
	private String appetite;
	@Column(name = "wellbeing")
	private String wellbeing;
	@Column(name = "shortness_of_breath")
	private String sob;
	@Column(name = "others")
	private String others;
	@Column(name = "others_remark")
	private String others_remark;

	@Column(name = "record_tracking")
	private Timestamp recordTracking;


	// bi-directional many-to-one association to UserCredential
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patient_user_cred")
	private UserCredential esaUserCredential;
	
	@OneToMany(mappedBy="patientEdmontonSymptomAssessment")
	private List<BodyCirclePatientESAMapping> bodyCirclePatientESAMappings;

	public PatientEdmontonSymptomAssessment() {
	}

	public PatientEdmontonSymptomAssessment(int id) {
		super();
		this.id = id;
	}

	public String getCompletedBy() {
		return this.completedBy;
	}

	public void setCompletedBy(String completedBy) {
		this.completedBy = completedBy;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}


	public UserCredential getEsaUserCredential() {
		return esaUserCredential;
	}

	public void setEsaUserCredential(UserCredential esaUserCredential) {
		this.esaUserCredential = esaUserCredential;
	}

	public String getTiredness() {
		return tiredness;
	}

	public void setTiredness(String tiredness) {
		this.tiredness = tiredness;
	}

	public String getNausea() {
		return nausea;
	}

	public void setNausea(String nausea) {
		this.nausea = nausea;
	}

	public String getDepression() {
		return depression;
	}

	public void setDepression(String depression) {
		this.depression = depression;
	}

	public String getAnxiety() {
		return anxiety;
	}

	public void setAnxiety(String anxiety) {
		this.anxiety = anxiety;
	}

	public String getDrowsiness() {
		return drowsiness;
	}

	public void setDrowsiness(String drowsiness) {
		this.drowsiness = drowsiness;
	}

	public String getAppetite() {
		return appetite;
	}

	public void setAppetite(String appetite) {
		this.appetite = appetite;
	}

	public String getWellbeing() {
		return wellbeing;
	}

	public void setWellbeing(String wellbeing) {
		this.wellbeing = wellbeing;
	}

	

	public String getSob() {
		return sob;
	}

	public void setSob(String sob) {
		this.sob = sob;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getOthers_remark() {
		return others_remark;
	}

	public void setOthers_remark(String others_remark) {
		this.others_remark = others_remark;
	}

	public List<BodyCirclePatientESAMapping> getBodyCirclePatientESAMappings() {
		return bodyCirclePatientESAMappings;
	}

	public void setBodyCirclePatientESAMappings(List<BodyCirclePatientESAMapping> bodyCirclePatientESAMappings) {
		this.bodyCirclePatientESAMappings = bodyCirclePatientESAMappings;
	}

	public String getPain() {
		return pain;
	}

	public void setPain(String pain) {
		this.pain = pain;
	}

}