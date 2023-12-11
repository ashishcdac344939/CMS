package in.fridr.modal;

import java.util.List;

import in.fridr.entity.PatientPainAssessments;

public class PatientPainAssessmentsModel {
 private PatientPainAssessments patientPainAssessments;
 private List<Integer> symptomsMasterList;
 private List<CircleData> painMasterList;
public PatientPainAssessmentsModel() {
	super();
	// TODO Auto-generated constructor stub
}
public PatientPainAssessmentsModel(PatientPainAssessments patientPainAssessments, List<CircleData> painMasterList,
		List<Integer> symptomsMasterList) {
	super();
	this.patientPainAssessments = patientPainAssessments;
	this.painMasterList = painMasterList;
	this.symptomsMasterList = symptomsMasterList;
}
public PatientPainAssessments getPatientPainAssessments() {
	return patientPainAssessments;
}
public void setPatientPainAssessments(PatientPainAssessments patientPainAssessments) {
	this.patientPainAssessments = patientPainAssessments;
}

public List<CircleData> getPainMasterList() {
	return painMasterList;
}
public void setPainMasterList(List<CircleData> painMasterList) {
	this.painMasterList = painMasterList;
}
public List<Integer> getSymptomsMasterList() {
	return symptomsMasterList;
}
public void setSymptomsMasterList(List<Integer> symptomsMasterList) {
	this.symptomsMasterList = symptomsMasterList;
}
 
}
