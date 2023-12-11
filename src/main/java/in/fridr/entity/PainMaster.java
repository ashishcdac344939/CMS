package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pain_master database table.
 * 
 */
@Entity
@Table(name="pain_master")
@NamedQuery(name="PainMaster.findAll", query="SELECT p FROM PainMaster p")
public class PainMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "pain_master_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pain_master_id")
	private Integer painMasterId;

	@Column(name="pain_name")
	private String painName;

	@Column(name="pain_status")
	private Boolean painStatus;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to PatientPainAssessments_
	@OneToMany(mappedBy="painMaster")
	private List<PatientPainAssessments> patientPainAssessments;

	public PainMaster(Integer painMasterId) {
		super();
		this.painMasterId = painMasterId;
	}

	public PainMaster() {
	}

	public Integer getPainMasterId() {
		return this.painMasterId;
	}

	public void setPainMasterId(Integer painMasterId) {
		this.painMasterId = painMasterId;
	}

	public String getPainName() {
		return this.painName;
	}

	public void setPainName(String painName) {
		this.painName = painName;
	}

	public Boolean getPainStatus() {
		return this.painStatus;
	}

	public void setPainStatus(Boolean painStatus) {
		this.painStatus = painStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public List<PatientPainAssessments> getPatientPainAssessments() {
		return this.patientPainAssessments;
	}

	public void setPatientPainAssessments(List<PatientPainAssessments> patientPainAssessments) {
		this.patientPainAssessments = patientPainAssessments;
	}

	public PatientPainAssessments addPatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().add(patientPainAssessment);
		patientPainAssessment.setPainMaster(this);

		return patientPainAssessment;
	}

	public PatientPainAssessments removePatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().remove(patientPainAssessment);
		patientPainAssessment.setPainMaster(null);

		return patientPainAssessment;
	}

}