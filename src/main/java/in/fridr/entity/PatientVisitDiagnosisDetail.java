package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the patient_visit_diagnosis_details database table.
 * 
 */
@Entity
@Table(name = "patient_visit_diagnosis_details")
@NamedQuery(name = "PatientVisitDiagnosisDetail.findAll", query = "SELECT p FROM PatientVisitDiagnosisDetail p")
public class PatientVisitDiagnosisDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_visit_diagnosis_details_seq_gen", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pvdd_id")
	private Integer pvId;

	@Column(name = "important_finding")
	private String importantFinding;

	@Column(name = "indication_of_referral")
	private String indicationOfReferral;

	@Column(name = "invistigation_date")
	private Timestamp invistigationDate;
	@Column(name = "nextVisitDate")
	private Timestamp nextVisitDate;

	@Column(name = "is_referral_to_pcw")
	private Boolean isReferralToPcw;

	@Column(name = "pd_current_diagnosis")
	private String pdCurrentDiagnosis;

	@Column(name = "pd_secondaries")
	private String pdSecondaries;

	@Column(name = "pd_stage_of_cancer")
	private String pdStageOfCancer;

	@Column(name = "pd_treatment_details")
	private String pdTreatmentDetails;

	/*
	 * @Column(name="pd_treatment_received") private String pdTreatmentReceived;
	 */

	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	@Column(name = "time_of_presentation_to_pcw_after_diagnosis")
	private String timeOfPresentationToPcwAfterDiagnosis;

	@Column(name = "pd_present_status")
	private String presentStatus;

	@Transient
	List<String> treatmentReceviedAsString;

	// bi-directional many-to-one association to PatientDetail
	@ManyToOne
	@JoinColumn(name = "pd_id")
	private PatientDetail patientDetail;

	@ManyToOne
	@JoinColumn(name = "p_user_cred_id")
	private UserCredential pUserCredential;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "d_user_cred_id")
	private UserCredential dUserCredential;

	@OneToOne(mappedBy = "patientVisitDiagnosisDetail")
	private PatientPainAssessments painPatientPainAssessments;
	
	@OneToMany(mappedBy = "patientVisitDiagnosisDetail")
	private List<PatientMedicinePrescription> patientMedicinePrescriptions;

	@OneToMany(mappedBy = "patientVisitDiagnosisDetail")
	private List<PatientTreatmentRecevied> patientTreatmentRecevied;

	public Timestamp getInvistigationDate() {
		return invistigationDate;
	}

	public List<String> getTreatmentReceviedAsString() {
		return treatmentReceviedAsString;
	}

	public void setTreatmentReceviedAsString(List<String> treatmentReceviedAsString) {
		this.treatmentReceviedAsString = treatmentReceviedAsString;
	}

	public UserCredential getdUserCredential() {
		return dUserCredential;
	}

	public void setdUserCredential(UserCredential dUserCredential) {
		this.dUserCredential = dUserCredential;
	}

	public PatientPainAssessments getPainPatientPainAssessments() {
		return painPatientPainAssessments;
	}

	public void setPainPatientPainAssessments(PatientPainAssessments painPatientPainAssessments) {
		this.painPatientPainAssessments = painPatientPainAssessments;
	}

	public List<PatientTreatmentRecevied> getPatientTreatmentRecevied() {
		return patientTreatmentRecevied;
	}

	public void setPatientTreatmentRecevied(List<PatientTreatmentRecevied> patientTreatmentRecevied) {
		this.patientTreatmentRecevied = patientTreatmentRecevied;
	}

	public void setInvistigationDate(Timestamp invistigationDate) {
		this.invistigationDate = invistigationDate;
	}

	public PatientVisitDiagnosisDetail(Integer pvId) {
		super();
		this.pvId = pvId;
	}

	public List<PatientMedicinePrescription> getPatientMedicinePrescriptions() {
		return patientMedicinePrescriptions;
	}

	public void setPatientMedicinePrescriptions(List<PatientMedicinePrescription> patientMedicinePrescriptions) {
		this.patientMedicinePrescriptions = patientMedicinePrescriptions;
	}

	public UserCredential getUserCredential() {
		return dUserCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.dUserCredential = userCredential;
	}

	public String getPdCurrentDiagnosis() {
		return pdCurrentDiagnosis;
	}

	public void setPdCurrentDiagnosis(String pdCurrentDiagnosis) {
		this.pdCurrentDiagnosis = pdCurrentDiagnosis;
	}

	public PatientVisitDiagnosisDetail() {
	}

	public String getImportantFinding() {
		return this.importantFinding;
	}

	public void setImportantFinding(String importantFinding) {
		this.importantFinding = importantFinding;
	}

	public String getIndicationOfReferral() {
		return this.indicationOfReferral;
	}

	public void setIndicationOfReferral(String indicationOfReferral) {
		this.indicationOfReferral = indicationOfReferral;
	}

	public Timestamp getInvistigationData() {
		return this.invistigationDate;
	}

	public void setInvistigationData(Timestamp invistigationDate) {
		this.invistigationDate = invistigationDate;
	}

	public Boolean getIsReferralToPcw() {
		return this.isReferralToPcw;
	}

	public void setIsReferralToPcw(Boolean isReferralToPcw) {
		this.isReferralToPcw = isReferralToPcw;
	}

	public String getPdCurrentDiagnosis_s_() {
		return this.pdCurrentDiagnosis;
	}

	public void setPdCurrentDiagnosis_s_(String pdCurrentDiagnosis) {
		this.pdCurrentDiagnosis = pdCurrentDiagnosis;
	}

	public String getPdSecondaries() {
		return this.pdSecondaries;
	}

	public void setPdSecondaries(String pdSecondaries) {
		this.pdSecondaries = pdSecondaries;
	}

	public String getPdStageOfCancer() {
		return this.pdStageOfCancer;
	}

	public void setPdStageOfCancer(String pdStageOfCancer) {
		this.pdStageOfCancer = pdStageOfCancer;
	}

	public String getPdTreatmentDetails() {
		return this.pdTreatmentDetails;
	}

	public void setPdTreatmentDetails(String pdTreatmentDetails) {
		this.pdTreatmentDetails = pdTreatmentDetails;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getTimeOfPresentationToPcwAfterDiagnosis() {
		return this.timeOfPresentationToPcwAfterDiagnosis;
	}

	public void setTimeOfPresentationToPcwAfterDiagnosis(String timeOfPresentationToPcwAfterDiagnosis) {
		this.timeOfPresentationToPcwAfterDiagnosis = timeOfPresentationToPcwAfterDiagnosis;
	}

	public PatientDetail getPatientDetail() {
		return this.patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public UserCredential getpUserCredential() {
		return pUserCredential;
	}

	public void setpUserCredential(UserCredential pUserCredential) {
		this.pUserCredential = pUserCredential;
	}

	public Integer getPvId() {
		return pvId;
	}

	public void setPvId(Integer pvId) {
		this.pvId = pvId;
	}

	public Timestamp getNextVisitDate() {
		return nextVisitDate;
	}

	public void setNextVisitDate(Timestamp nextVisitDate) {
		this.nextVisitDate = nextVisitDate;
	}

}