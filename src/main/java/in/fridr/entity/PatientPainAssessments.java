package in.fridr.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the "patient_pain_assessments " database table.
 * 
 */
@Entity
@Table(name="patient_pain_assessments")
@NamedQuery(name="PatientPainAssessments.findAll", query="SELECT p FROM PatientPainAssessments p")
public class PatientPainAssessments implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_pain_assessments_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pp_id")
	private Integer ppId;
	
	@Column(name="pp_diagnosis")
	private String ppDiagnosis;

	@Column(name="pp_how_long_relief_retained")
	private String ppHowLongReliefRetained;

	@Column(name="pp_how_much_pain_interfere_daily_activity")
	private String ppHowMuchPainInterfereDailyActivity;


	@Column(name="pp_invistigation_date")
	private Timestamp ppInvistigationDate;

	@Column(name="pp_is_any_posture_gives_relief")
	private String ppIsAnyPostureGivesRelief;

	@Column(name="pp_is_radiatefrom_point_of_origin")
	private String ppIsRadiatefromPointOfOrigin;

	@Column(name="pp_is_received_treatment_for_year")
	private String ppIsReceivedTreatmentForYear;

	@Column(name="pp_is_worsened_by_activity")
	private String ppIsWorsenedByActivity;

	@Column(name="pp_ladder_step")
	private String ppLadderStep;

	@Column(name="pp_location_of_pain")
	private String ppLocationOfPain;

	@Column(name="pp_max_pain_experienced")
	private String ppMaxPainExperienced;

	@Column(name="pp_nrs_representing_least_pain")
	private String ppNrsRepresentingLeastPain;

	@Column(name="pp_pain_maximum_during_day")
	private String ppPainMaximumDuringDay;

	@Column(name="pp_pain_relief_after_medication")
	private String ppPainReliefAfterMedication;

	@Column(name="pp_radiatefrom_point_of_origin_details")
	private String ppRadiatefromPointOfOriginDetails;

	@Column(name="pp_received_treatment_for_year_details")
	private String ppReceivedTreatmentForYearDetails;

	@Column(name="pp_worsened_by_activity_details")
	private String ppWorsenedByActivityDetails;

	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	public PatientPainAssessments(Integer ppId) {
		super();
		this.ppId = ppId;
	}

	//bi-directional many-to-one association to PainMaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pp_type_of_pain")
	private PainMaster painMaster;

	//bi-directional many-to-one association to PatientDetail
	@ManyToOne
	@JoinColumn(name="p_id")
	private PatientDetail patientDetail;

	//bi-directional many-to-one association to SymptomsMaster
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pp_other_symptoms")
	private SymptomsMaster symptomsMaster;
	@OneToOne
	@JoinColumn(name="pv_id")
	private PatientVisitDiagnosisDetail patientVisitDiagnosisDetail;
	
	@OneToMany(mappedBy = "patientPainAssessments")
	private List<PatientPainAssesmentSymtomMasterMapping> patientPainAssesmentSymtomMasterMapping;
	
	@OneToMany(mappedBy = "patientPainAssessments")
	private List<PatientPainAssesmentPainMasterMapping> patientPainAssesmentPainMasterMapping;
	
	@OneToMany(mappedBy="patientPainAssessment")
	private List<BodyCirclePainAssesmentMapping> bodyCirclePainAssesmentMappings;
	
	public PatientPainAssessments() {
	}

	public String getPpDiagnosis() {
		return this.ppDiagnosis;
	}

	public void setPpDiagnosis(String ppDiagnosis) {
		this.ppDiagnosis = ppDiagnosis;
	}

	public List<BodyCirclePainAssesmentMapping> getBodyCirclePainAssesmentMappings() {
		return bodyCirclePainAssesmentMappings;
	}

	public void setBodyCirclePainAssesmentMappings(List<BodyCirclePainAssesmentMapping> bodyCirclePainAssesmentMappings) {
		this.bodyCirclePainAssesmentMappings = bodyCirclePainAssesmentMappings;
	}

	public String getPpHowLongReliefRetained() {
		return this.ppHowLongReliefRetained;
	}

	public void setPpHowLongReliefRetained(String ppHowLongReliefRetained) {
		this.ppHowLongReliefRetained = ppHowLongReliefRetained;
	}

	public String getPpHowMuchPainInterfereDailyActivity() {
		return this.ppHowMuchPainInterfereDailyActivity;
	}

	public void setPpHowMuchPainInterfereDailyActivity(String ppHowMuchPainInterfereDailyActivity) {
		this.ppHowMuchPainInterfereDailyActivity = ppHowMuchPainInterfereDailyActivity;
	}

	public Integer getPpId() {
		return this.ppId;
	}

	public void setPpId(Integer ppId) {
		this.ppId = ppId;
	}

	public Timestamp getPpInvistigationDate() {
		return this.ppInvistigationDate;
	}

	public void setPpInvistigationDate(Timestamp ppInvistigationDate) {
		this.ppInvistigationDate = ppInvistigationDate;
	}

	public String getPpIsAnyPostureGivesRelief() {
		return this.ppIsAnyPostureGivesRelief;
	}

	public void setPpIsAnyPostureGivesRelief(String ppIsAnyPostureGivesRelief) {
		this.ppIsAnyPostureGivesRelief = ppIsAnyPostureGivesRelief;
	}

	public String getPpIsRadiatefromPointOfOrigin() {
		return this.ppIsRadiatefromPointOfOrigin;
	}

	public void setPpIsRadiatefromPointOfOrigin(String ppIsRadiatefromPointOfOrigin) {
		this.ppIsRadiatefromPointOfOrigin = ppIsRadiatefromPointOfOrigin;
	}

	public String getPpIsReceivedTreatmentForYear() {
		return this.ppIsReceivedTreatmentForYear;
	}

	public void setPpIsReceivedTreatmentForYear(String ppIsReceivedTreatmentForYear) {
		this.ppIsReceivedTreatmentForYear = ppIsReceivedTreatmentForYear;
	}

	public String getPpIsWorsenedByActivity() {
		return this.ppIsWorsenedByActivity;
	}

	public void setPpIsWorsenedByActivity(String ppIsWorsenedByActivity) {
		this.ppIsWorsenedByActivity = ppIsWorsenedByActivity;
	}

	public String getPpLadderStep() {
		return this.ppLadderStep;
	}

	public void setPpLadderStep(String ppLadderStep) {
		this.ppLadderStep = ppLadderStep;
	}

	public String getPpLocationOfPain() {
		return this.ppLocationOfPain;
	}

	public void setPpLocationOfPain(String ppLocationOfPain) {
		this.ppLocationOfPain = ppLocationOfPain;
	}

	public String getPpMaxPainExperienced() {
		return this.ppMaxPainExperienced;
	}

	public void setPpMaxPainExperienced(String ppMaxPainExperienced) {
		this.ppMaxPainExperienced = ppMaxPainExperienced;
	}

	public String getPpNrsRepresentingLeastPain() {
		return this.ppNrsRepresentingLeastPain;
	}

	public void setPpNrsRepresentingLeastPain(String ppNrsRepresentingLeastPain) {
		this.ppNrsRepresentingLeastPain = ppNrsRepresentingLeastPain;
	}

	public String getPpPainMaximumDuringDay() {
		return this.ppPainMaximumDuringDay;
	}

	public void setPpPainMaximumDuringDay(String ppPainMaximumDuringDay) {
		this.ppPainMaximumDuringDay = ppPainMaximumDuringDay;
	}

	public String getPpPainReliefAfterMedication() {
		return this.ppPainReliefAfterMedication;
	}

	public void setPpPainReliefAfterMedication(String ppPainReliefAfterMedication) {
		this.ppPainReliefAfterMedication = ppPainReliefAfterMedication;
	}

	public String getPpRadiatefromPointOfOriginDetails() {
		return this.ppRadiatefromPointOfOriginDetails;
	}

	public void setPpRadiatefromPointOfOriginDetails(String ppRadiatefromPointOfOriginDetails) {
		this.ppRadiatefromPointOfOriginDetails = ppRadiatefromPointOfOriginDetails;
	}

	public String getPpReceivedTreatmentForYearDetails() {
		return this.ppReceivedTreatmentForYearDetails;
	}

	public void setPpReceivedTreatmentForYearDetails(String ppReceivedTreatmentForYearDetails) {
		this.ppReceivedTreatmentForYearDetails = ppReceivedTreatmentForYearDetails;
	}

	public String getPpWorsenedByActivityDetails() {
		return this.ppWorsenedByActivityDetails;
	}

	public void setPpWorsenedByActivityDetails(String ppWorsenedByActivityDetails) {
		this.ppWorsenedByActivityDetails = ppWorsenedByActivityDetails;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public PainMaster getPainMaster() {
		return this.painMaster;
	}

	public void setPainMaster(PainMaster painMaster) {
		this.painMaster = painMaster;
	}

	public PatientDetail getPatientDetail() {
		return this.patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}

	public SymptomsMaster getSymptomsMaster() {
		return this.symptomsMaster;
	}

	public void setSymptomsMaster(SymptomsMaster symptomsMaster) {
		this.symptomsMaster = symptomsMaster;
	}

	public List<PatientPainAssesmentSymtomMasterMapping> getPatientPainAssesmentSymtomMasterMapping() {
		return patientPainAssesmentSymtomMasterMapping;
	}

	public void setPatientPainAssesmentSymtomMasterMapping(
			List<PatientPainAssesmentSymtomMasterMapping> patientPainAssesmentSymtomMasterMapping) {
		this.patientPainAssesmentSymtomMasterMapping = patientPainAssesmentSymtomMasterMapping;
	}

	public List<PatientPainAssesmentPainMasterMapping> getPatientPainAssesmentPainMasterMapping() {
		return patientPainAssesmentPainMasterMapping;
	}

	public void setPatientPainAssesmentPainMasterMapping(
			List<PatientPainAssesmentPainMasterMapping> patientPainAssesmentPainMasterMapping) {
		this.patientPainAssesmentPainMasterMapping = patientPainAssesmentPainMasterMapping;
	}

	public PatientVisitDiagnosisDetail getPatientVisitDiagnosisDetail() {
		return patientVisitDiagnosisDetail;
	}

	public void setPatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}

}