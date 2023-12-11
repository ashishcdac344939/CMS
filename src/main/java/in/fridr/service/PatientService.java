package in.fridr.service;

import java.util.List;
import java.util.Optional;

import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.PatientPainAssesmentPainMasterMapping;
import in.fridr.entity.PatientPainAssesmentSymtomMasterMapping;
import in.fridr.entity.PatientPainAssessments;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.StateMaster;
import in.fridr.entity.UserDetail;
import in.fridr.modal.ESAModel;
import in.fridr.modal.PatientModel;
import in.fridr.modal.PatientPainAssessmentResModel;
import in.fridr.modal.PatientVisitDetailsResModel;
import in.fridr.projection.DistrictPopulationProjection;
import in.fridr.projection.PatientDataModel;

public interface PatientService {
	
	public  boolean addPatient(PatientModel patientModel);

	public boolean savePatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail);
	
	public List<PatientDataModel> getPatientDetails(String email,String mobNo,String uniqueId);
	
	public PatientVisitDetailsResModel getPatientVisitDiagnosiedDetails(int pvdd);

	public int savePatientPainAssessmentsDetail(PatientPainAssessments patientPainAssessments);

	public void savePatientPainAssesmentPainMasterMapping(PatientPainAssesmentPainMasterMapping ppapm);

	public void savePatientPainAssesmentSymtomMasterMapping(PatientPainAssesmentSymtomMasterMapping ppasm);

	public Optional<PatientPainAssessments> getByPatientPainAssessmentsId(Integer ppId);

	public PatientDetail getPatientDetailsById(Integer pId);

	public List<PatientDataModel> getNumberOfVisitdBydocId(int i);

	public List<PatientDataModel> getNumberOfActivePatientdocId(int parseInt);

	public List<PatientDataModel> getNumberOfMorphinAllocated(int parseInt);

	public List<PatientDataModel> getNumberOfTotalPatientdocId(int parseInt);

	public PatientPainAssessmentResModel getPatientPainAssessmentsDetailByVisitId(Integer pvId);

	public List<StateMaster> getAllStates();

	public List<PatientDataModel> getNumberOfVisitsByPatientCredId(String string);

	public Integer getFirstTimeVisitPatient(Integer id, String poInitialDate, String poTillDate);

	public Integer getFollowUpVisitPatient(Integer id, String poInitialDate, String poTillDate);

	public Integer getInactivePatientbyDocId(Integer id, String poInitialDate, String poTillDate);

	public List<DistrictPopulationProjection> getPatientGeoLocationByDocId(int docId, String geoInitialDate, String geoTillDate);

	public Optional<PatientEdmontonSymptomAssessment> getESAByESAId(int esaId);

	public List<PatientEdmontonSymptomAssessment> getESAByPatientcredId(int userId);
	public List<PatientEdmontonSymptomAssessment> getESAByPatientUserId(String userId);

	public int saveEsa(ESAModel esaModel);

	public void saveESABodyCircle(BodyCirclePatientESAMapping ppapm);

	public List<Object> getPrescriptionByVisitId(int patientVisitId);

	public Object findPatientPainAssessmentsDetailByVisitId(int long1);


	 public PatientDetail getPatientDetailByUserDetailsId(UserDetail userDetailsId);

	

}
