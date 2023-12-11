package in.fridr.modal;

import java.sql.Timestamp;
import java.util.List;

public class PatientVisitDetailsResModel {
	private Integer pvId;
	private String importantFinding;
	private String indicationOfReferral;
	private Timestamp invistigationDate;
	private Boolean isReferralToPcw;
	private String pdCurrentDiagnosis;
	private String pdSecondaries;
	private String pdStageOfCancer;
	private String pdTreatmentDetails;
	 private List<String> pdTreatmentReceived; 
	private String timeOfPresentationToPcwAfterDiagnosis;
	private String presentStatus;
	private String doctorName;
	private String patientName;
	public Integer getPvId() {
		return pvId;
	}
	public void setPvId(Integer pvId) {
		this.pvId = pvId;
	}
	public String getImportantFinding() {
		return importantFinding;
	}
	public void setImportantFinding(String importantFinding) {
		this.importantFinding = importantFinding;
	}
	public String getIndicationOfReferral() {
		return indicationOfReferral;
	}
	public void setIndicationOfReferral(String indicationOfReferral) {
		this.indicationOfReferral = indicationOfReferral;
	}
	public Timestamp getInvistigationDate() {
		return invistigationDate;
	}
	public void setInvistigationDate(Timestamp invistigationDate) {
		this.invistigationDate = invistigationDate;
	}
	public Boolean getIsReferralToPcw() {
		return isReferralToPcw;
	}
	public void setIsReferralToPcw(Boolean isReferralToPcw) {
		this.isReferralToPcw = isReferralToPcw;
	}
	public String getPdCurrentDiagnosis() {
		return pdCurrentDiagnosis;
	}
	public void setPdCurrentDiagnosis(String pdCurrentDiagnosis) {
		this.pdCurrentDiagnosis = pdCurrentDiagnosis;
	}
	public String getPdSecondaries() {
		return pdSecondaries;
	}
	public void setPdSecondaries(String pdSecondaries) {
		this.pdSecondaries = pdSecondaries;
	}
	public String getPdStageOfCancer() {
		return pdStageOfCancer;
	}
	public void setPdStageOfCancer(String pdStageOfCancer) {
		this.pdStageOfCancer = pdStageOfCancer;
	}
	public String getPdTreatmentDetails() {
		return pdTreatmentDetails;
	}
	public void setPdTreatmentDetails(String pdTreatmentDetails) {
		this.pdTreatmentDetails = pdTreatmentDetails;
	}

	
	public List<String> getPdTreatmentReceived() {
		return pdTreatmentReceived;
	}
	public void setPdTreatmentReceived(List<String> pdTreatmentReceived) {
		this.pdTreatmentReceived = pdTreatmentReceived;
	}
	public String getTimeOfPresentationToPcwAfterDiagnosis() {
		return timeOfPresentationToPcwAfterDiagnosis;
	}
	public void setTimeOfPresentationToPcwAfterDiagnosis(String timeOfPresentationToPcwAfterDiagnosis) {
		this.timeOfPresentationToPcwAfterDiagnosis = timeOfPresentationToPcwAfterDiagnosis;
	}
	public String getPresentStatus() {
		return presentStatus;
	}
	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	
}
