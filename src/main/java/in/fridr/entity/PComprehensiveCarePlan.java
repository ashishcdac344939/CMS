package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;


/**
 * The persistent class for the p_comprehensive_care_plan database table.
 * 
 */
@Entity
@Table(name="p_comprehensive_care_plan")
@NamedQuery(name="PComprehensiveCarePlan.findAll", query="SELECT p FROM PComprehensiveCarePlan p")
public class PComprehensiveCarePlan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "p_comprehensive_care_plan_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="p_ccp_id")
	private Integer pCcpId;

	@Column(name="p_ccp_ccp_date")
	private Timestamp pCcpCcpDate;

	@Column(name="p_ccp_date_intervention_drugsused")
	private String pCcpDateInterventionDrugsused;

	@Column(name="p_ccp_dietary_referral")
	private String pCcpDietaryReferral;

	@Column(name="p_ccp_ecog_performance_status")
	private String pCcpEcogPerformanceStatus;

	@Column(name="p_ccp_intensity_of_symptoms_after_intervention")
	private String pCcpIntensityOfSymptomsAfterIntervention;

	@Column(name="p_ccp_intensity_of_symptoms_before_intervention")
	private String pCcpIntensityOfSymptomsBeforeIntervention;

	@Column(name="p_ccp_others_referral")
	private String pCcpOthersReferral;

	@Column(name="p_ccp_physiotherapy_referral")
	private String pCcpPhysiotherapyReferral;

	@Column(name="p_ccp_referral_to_dept")
	private String pCcpReferralToDept;

	@Column(name="p_ccp_referral_to_dept_reason")
	private String pCcpReferralToDeptReason;

	@Column(name="p_ccp_remarks_if_any")
	private String pCcpRemarksIfAny;

	@Column(name="p_ccp_symptom_severity_nrs_medications")
	private String pCcpSymptomSeverityNrsMedications;

	@Column(name="record_tracking")
	private Timestamp recordTracking;

	//bi-directional many-to-one association to PatientDetail
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="p_id")
	private PatientDetail patientDetail;

	public PComprehensiveCarePlan() {
	}

	public Integer getPCcpId() {
		return this.pCcpId;
	}

	public void setPCcpId(Integer pCcpId) {
		this.pCcpId = pCcpId;
	}

	public Timestamp getPCcpCcpDate() {
		return this.pCcpCcpDate;
	}

	public void setPCcpCcpDate(Timestamp pCcpCcpDate) {
		this.pCcpCcpDate = pCcpCcpDate;
	}

	public String getPCcpDateInterventionDrugsused() {
		return this.pCcpDateInterventionDrugsused;
	}

	public void setPCcpDateInterventionDrugsused(String pCcpDateInterventionDrugsused) {
		this.pCcpDateInterventionDrugsused = pCcpDateInterventionDrugsused;
	}

	public String getPCcpDietaryReferral() {
		return this.pCcpDietaryReferral;
	}

	public void setPCcpDietaryReferral(String pCcpDietaryReferral) {
		this.pCcpDietaryReferral = pCcpDietaryReferral;
	}

	public String getPCcpEcogPerformanceStatus() {
		return this.pCcpEcogPerformanceStatus;
	}

	public void setPCcpEcogPerformanceStatus(String pCcpEcogPerformanceStatus) {
		this.pCcpEcogPerformanceStatus = pCcpEcogPerformanceStatus;
	}

	public String getPCcpIntensityOfSymptomsAfterIntervention() {
		return this.pCcpIntensityOfSymptomsAfterIntervention;
	}

	public void setPCcpIntensityOfSymptomsAfterIntervention(String pCcpIntensityOfSymptomsAfterIntervention) {
		this.pCcpIntensityOfSymptomsAfterIntervention = pCcpIntensityOfSymptomsAfterIntervention;
	}

	public String getPCcpIntensityOfSymptomsBeforeIntervention() {
		return this.pCcpIntensityOfSymptomsBeforeIntervention;
	}

	public void setPCcpIntensityOfSymptomsBeforeIntervention(String pCcpIntensityOfSymptomsBeforeIntervention) {
		this.pCcpIntensityOfSymptomsBeforeIntervention = pCcpIntensityOfSymptomsBeforeIntervention;
	}

	public String getPCcpOthersReferral() {
		return this.pCcpOthersReferral;
	}

	public void setPCcpOthersReferral(String pCcpOthersReferral) {
		this.pCcpOthersReferral = pCcpOthersReferral;
	}

	public String getPCcpPhysiotherapyReferral() {
		return this.pCcpPhysiotherapyReferral;
	}

	public void setPCcpPhysiotherapyReferral(String pCcpPhysiotherapyReferral) {
		this.pCcpPhysiotherapyReferral = pCcpPhysiotherapyReferral;
	}

	public String getPCcpReferralToDept() {
		return this.pCcpReferralToDept;
	}

	public void setPCcpReferralToDept(String pCcpReferralToDept) {
		this.pCcpReferralToDept = pCcpReferralToDept;
	}

	public String getPCcpReferralToDeptReason() {
		return this.pCcpReferralToDeptReason;
	}

	public void setPCcpReferralToDeptReason(String pCcpReferralToDeptReason) {
		this.pCcpReferralToDeptReason = pCcpReferralToDeptReason;
	}

	public String getPCcpRemarksIfAny() {
		return this.pCcpRemarksIfAny;
	}

	public void setPCcpRemarksIfAny(String pCcpRemarksIfAny) {
		this.pCcpRemarksIfAny = pCcpRemarksIfAny;
	}

	public String getPCcpSymptomSeverityNrsMedications() {
		return this.pCcpSymptomSeverityNrsMedications;
	}

	public void setPCcpSymptomSeverityNrsMedications(String pCcpSymptomSeverityNrsMedications) {
		this.pCcpSymptomSeverityNrsMedications = pCcpSymptomSeverityNrsMedications;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public PatientDetail getPatientDetail() {
		return this.patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}

}