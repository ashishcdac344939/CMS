package in.fridr.modal;

import java.util.List;

import in.fridr.entity.PatientEdmontonSymptomAssessment;

public class ESAModel {
private PatientEdmontonSymptomAssessment patientEdmontonSymptomAssessments;
private List<CircleData> circleData;
private int pUserCredId;
private String patientUserId;
public ESAModel() {
	super();
	// TODO Auto-generated constructor stub
}

public List<CircleData> getCircleData() {
	return circleData;
}

public void setCircleData(List<CircleData> circleData) {
	this.circleData = circleData;
}

public PatientEdmontonSymptomAssessment getPatientEdmontonSymptomAssessments() {
	return patientEdmontonSymptomAssessments;
}

public void setPatientEdmontonSymptomAssessments(PatientEdmontonSymptomAssessment patientEdmontonSymptomAssessments) {
	this.patientEdmontonSymptomAssessments = patientEdmontonSymptomAssessments;
}

public int getpUserCredId() {
	return pUserCredId; 
}

public void setpUserCredId(int pUserCredId) {
	this.pUserCredId = pUserCredId;
}

public String getPatientUserId() {
	return patientUserId;
}

public void setPatientUserId(String patientUserId) {
	this.patientUserId = patientUserId;
}





}
