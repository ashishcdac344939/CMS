package in.fridr.modal;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.OneToMany;

import in.fridr.entity.BodyCirclePainAssesmentMapping;
import in.fridr.entity.PatientPainAssesmentPainMasterMapping;
import in.fridr.entity.PatientPainAssesmentSymtomMasterMapping;


public class PatientPainAssessmentResModel {
   
    private Integer ppId;
	private String ppDiagnosis;
	private String ppHowLongReliefRetained;
	private String ppHowMuchPainInterfereDailyActivity;
	private Timestamp ppInvistigationDate;
	private String ppIsAnyPostureGivesRelief;
	private String ppIsRadiatefromPointOfOrigin;
	private String ppIsReceivedTreatmentForYear;
	private String ppIsWorsenedByActivity;
	private String ppLadderStep;
	private String ppLocationOfPain;
	private String ppMaxPainExperienced;
	private String ppNrsRepresentingLeastPain;
	private String ppPainMaximumDuringDay;
	private String ppPainReliefAfterMedication;
	private String ppRadiatefromPointOfOriginDetails;
	private String ppReceivedTreatmentForYearDetails;
	private String ppWorsenedByActivityDetails;
	private String patientName;
	private List<PatientPainAssesmentSymtomMasterMapping> patientPainAssesmentSymtomMasterMapping;
	private List<PatientPainAssesmentPainMasterMapping> patientPainAssesmentPainMasterMapping;
	private List<BodyCirclePainAssesmentMapping> bodyCirclePainAssesmentMapping;
	private int patientId;
	public Integer getPpId() {
		return ppId;
	}
	


	public List<BodyCirclePainAssesmentMapping> getBodyCirclePainAssesmentMapping() {
		return bodyCirclePainAssesmentMapping;
	}



	public void setBodyCirclePainAssesmentMapping(List<BodyCirclePainAssesmentMapping> bodyCirclePainAssesmentMapping) {
		this.bodyCirclePainAssesmentMapping = bodyCirclePainAssesmentMapping;
	}



	public void setPatientId(int patientId) {
		this.patientId = patientId;
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
	public void setPpId(Integer ppId) {
		this.ppId = ppId;
	}
	public String getPpDiagnosis() {
		return ppDiagnosis;
	}
	public void setPpDiagnosis(String ppDiagnosis) {
		this.ppDiagnosis = ppDiagnosis;
	}
	public String getPpHowLongReliefRetained() {
		return ppHowLongReliefRetained;
	}
	public void setPpHowLongReliefRetained(String ppHowLongReliefRetained) {
		this.ppHowLongReliefRetained = ppHowLongReliefRetained;
	}
	public String getPpHowMuchPainInterfereDailyActivity() {
		return ppHowMuchPainInterfereDailyActivity;
	}
	public void setPpHowMuchPainInterfereDailyActivity(String ppHowMuchPainInterfereDailyActivity) {
		this.ppHowMuchPainInterfereDailyActivity = ppHowMuchPainInterfereDailyActivity;
	}
	public Timestamp getPpInvistigationDate() {
		return ppInvistigationDate;
	}
	public void setPpInvistigationDate(Timestamp ppInvistigationDate) {
		this.ppInvistigationDate = ppInvistigationDate;
	}
	public String getPpIsAnyPostureGivesRelief() {
		return ppIsAnyPostureGivesRelief;
	}
	public void setPpIsAnyPostureGivesRelief(String ppIsAnyPostureGivesRelief) {
		this.ppIsAnyPostureGivesRelief = ppIsAnyPostureGivesRelief;
	}
	public String getPpIsRadiatefromPointOfOrigin() {
		return ppIsRadiatefromPointOfOrigin;
	}
	public void setPpIsRadiatefromPointOfOrigin(String ppIsRadiatefromPointOfOrigin) {
		this.ppIsRadiatefromPointOfOrigin = ppIsRadiatefromPointOfOrigin;
	}
	public String getPpIsReceivedTreatmentForYear() {
		return ppIsReceivedTreatmentForYear;
	}
	public void setPpIsReceivedTreatmentForYear(String ppIsReceivedTreatmentForYear) {
		this.ppIsReceivedTreatmentForYear = ppIsReceivedTreatmentForYear;
	}
	public String getPpIsWorsenedByActivity() {
		return ppIsWorsenedByActivity;
	}
	public void setPpIsWorsenedByActivity(String ppIsWorsenedByActivity) {
		this.ppIsWorsenedByActivity = ppIsWorsenedByActivity;
	}
	public String getPpLadderStep() {
		return ppLadderStep;
	}
	public void setPpLadderStep(String ppLadderStep) {
		this.ppLadderStep = ppLadderStep;
	}
	public String getPpLocationOfPain() {
		return ppLocationOfPain;
	}
	public void setPpLocationOfPain(String ppLocationOfPain) {
		this.ppLocationOfPain = ppLocationOfPain;
	}
	public String getPpMaxPainExperienced() {
		return ppMaxPainExperienced;
	}
	public void setPpMaxPainExperienced(String ppMaxPainExperienced) {
		this.ppMaxPainExperienced = ppMaxPainExperienced;
	}
	public String getPpNrsRepresentingLeastPain() {
		return ppNrsRepresentingLeastPain;
	}
	public void setPpNrsRepresentingLeastPain(String ppNrsRepresentingLeastPain) {
		this.ppNrsRepresentingLeastPain = ppNrsRepresentingLeastPain;
	}
	public String getPpPainMaximumDuringDay() {
		return ppPainMaximumDuringDay;
	}
	public void setPpPainMaximumDuringDay(String ppPainMaximumDuringDay) {
		this.ppPainMaximumDuringDay = ppPainMaximumDuringDay;
	}
	public String getPpPainReliefAfterMedication() {
		return ppPainReliefAfterMedication;
	}
	public void setPpPainReliefAfterMedication(String ppPainReliefAfterMedication) {
		this.ppPainReliefAfterMedication = ppPainReliefAfterMedication;
	}
	public String getPpRadiatefromPointOfOriginDetails() {
		return ppRadiatefromPointOfOriginDetails;
	}
	public void setPpRadiatefromPointOfOriginDetails(String ppRadiatefromPointOfOriginDetails) {
		this.ppRadiatefromPointOfOriginDetails = ppRadiatefromPointOfOriginDetails;
	}
	public String getPpReceivedTreatmentForYearDetails() {
		return ppReceivedTreatmentForYearDetails;
	}
	public void setPpReceivedTreatmentForYearDetails(String ppReceivedTreatmentForYearDetails) {
		this.ppReceivedTreatmentForYearDetails = ppReceivedTreatmentForYearDetails;
	}
	public String getPpWorsenedByActivityDetails() {
		return ppWorsenedByActivityDetails;
	}
	public void setPpWorsenedByActivityDetails(String ppWorsenedByActivityDetails) {
		this.ppWorsenedByActivityDetails = ppWorsenedByActivityDetails;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	

}
