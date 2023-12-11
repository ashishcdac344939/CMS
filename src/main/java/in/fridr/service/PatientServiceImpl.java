package in.fridr.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.PatientLanguage;
import in.fridr.entity.PatientPainAssesmentPainMasterMapping;
import in.fridr.entity.PatientPainAssesmentSymtomMasterMapping;
import in.fridr.entity.PatientPainAssessments;
import in.fridr.entity.PatientTreatmentRecevied;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.StateMaster;
import in.fridr.entity.TreatmentRecevied;
import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.modal.ESAModel;
import in.fridr.modal.PatientModel;
import in.fridr.modal.PatientPainAssessmentResModel;
import in.fridr.modal.PatientVisitDetailsResModel;
import in.fridr.projection.DistrictPopulationProjection;
import in.fridr.projection.PatientDataModel;
import in.fridr.repository.BodyCirclePatientESAMappingRepo;
import in.fridr.repository.PatientDetailsRepo;
import in.fridr.repository.PatientEdmontonSymptomAssessmentRepo;
import in.fridr.repository.PatientLanguageRepo;
import in.fridr.repository.PatientMedicinePrescriptionRepo;
import in.fridr.repository.PatientPainAssesmentPainMasterMappingRepo;
import in.fridr.repository.PatientPainAssesmentSymtomMasterMappingRepo;
import in.fridr.repository.PatientPainAssessmentsRepo;
import in.fridr.repository.PatientTreatmentReceivedRepo;
import in.fridr.repository.PatientVisitDiagnosisDetailRepo;
import in.fridr.repository.StateRepositoryRepo;
import in.fridr.repository.TreatmentReceviedRepo;
import in.fridr.repository.UserCredentialRepo;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

	@Autowired
	private StateRepositoryRepo stateRepositoryRepo;
	
	@Autowired
	private PatientTreatmentReceivedRepo patientTreatmentReceivedRepo;
	@Autowired
	private PatientEdmontonSymptomAssessmentRepo PatientEdmontonSymptomAssessmentRepo;
	@Autowired 
	private BodyCirclePatientESAMappingRepo bodyCirclePatientESAMappingRepo;

	@Autowired
	private CommonService commonService;
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;
	@Autowired
	private PatientPainAssessmentsRepo patientPainAssessmentsRepo;
	@Autowired
	private PatientVisitDiagnosisDetailRepo patientVisitDiagnosisDetailRepo;
	@Autowired
	private PatientPainAssesmentPainMasterMappingRepo patientPainAssesmentPainMasterMappingRepo;
	@Autowired
	private PatientPainAssesmentSymtomMasterMappingRepo patientPainAssesmentSymtomMasterMappingRepo;
	@Autowired
	private PatientEdmontonSymptomAssessmentRepo patientEdmontonSymptomAssessmentRepo; 
	@Autowired
	private PatientLanguageRepo patientLanguageRepo; 
	@Autowired
	private TreatmentReceviedRepo treatmentReceviedRepo; 
	@Autowired
	private PatientMedicinePrescriptionRepo patientMedicinePrescriptionRepo;

	@Override
	public boolean addPatient(PatientModel patientModel) {
		// System.err.println(patientModel);

		try {
			UserCredential userCred = commonService.registerUser(patientModel.getUserModel());
			// System.err.println(userCred);
			// Get the current time in milliseconds
	        long currentTimeMillis = System.currentTimeMillis();
	        
	        // Create a Date object using the current time in milliseconds
	        Date currentDate = new Date(currentTimeMillis);
	        
	        // Convert the Date object to a Timestamp
	        Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
			PatientDetail patientDetail = patientModel.getPatient();
			patientDetail.setRecordTracking(currentTimestamp);
			UserCredential docCred = new UserCredential();
			docCred.setId(patientModel.getDocId());
			patientDetail.setUserDetail(userCred.getUserDetails());
			patientDetail.setdUserCredential(docCred);
			patientDetail = patientDetailsRepo.save(patientDetail);
			List<PatientLanguage> patientLanguage=PatientLanguage.getPatientLanguageList(patientModel.getLanguageknown(),patientDetail);
			patientLanguageRepo.saveAll(patientLanguage);
			List<TreatmentRecevied> treatmentRecevied =TreatmentRecevied.getTreatmentRecevied(patientModel.getTreatmentReceived(),patientDetail);
			treatmentReceviedRepo.saveAll(treatmentRecevied);
			// System.err.println(patientDetail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean savePatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		try {
			PatientVisitDiagnosisDetail savedPatientVisitDiagnosisDetail = patientVisitDiagnosisDetailRepo
					.save(patientVisitDiagnosisDetail);
			List<PatientTreatmentRecevied> treatmentReceived = new ArrayList<PatientTreatmentRecevied>();
			for (String treatment : patientVisitDiagnosisDetail.getTreatmentReceviedAsString()) {
				treatmentReceived.add(new PatientTreatmentRecevied(treatment, savedPatientVisitDiagnosisDetail));
			}
			patientTreatmentReceivedRepo.saveAll(treatmentReceived);
			System.err.println(patientVisitDiagnosisDetail.getPvId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int savePatientPainAssessmentsDetail(PatientPainAssessments patientPainAssessments) {
		try {
			PatientPainAssessments savePatientPainAssessments = patientPainAssessmentsRepo.save(patientPainAssessments);
			System.err.println("XXXXXXXXXXXXXXX: " + savePatientPainAssessments.getPpId());
			return savePatientPainAssessments.getPpId();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<PatientDataModel> getPatientDetails(String emailId, String mobNo, String uniqueId) {
		List<PatientDataModel> data;
		if (emailId != null) {
			data = patientVisitDiagnosisDetailRepo.findByPatientEmailId(emailId);
			System.err.println("1111111: "+data);
			if (data.size() == 0) {
				data = userCredentialRepo.findByUserId(emailId);
			}
		} else if (mobNo != null) {
			data = patientVisitDiagnosisDetailRepo.findByPatientMobNo(mobNo);
			if (data.size() == 0) {
				data = userCredentialRepo.findByUserId(mobNo);
			}

		} else {
			data = patientVisitDiagnosisDetailRepo.findByPatientUniqueId(uniqueId);
			if (data.size() == 0) {
				data = userCredentialRepo.findByUserId(uniqueId);
			}

		}
		return data;
	}

	@Override
	public PatientVisitDetailsResModel getPatientVisitDiagnosiedDetails(int pvdd) {

		PatientVisitDiagnosisDetail visitDiagnosisDetail = patientVisitDiagnosisDetailRepo.findById(pvdd).get();
        List<PatientTreatmentRecevied> treatmentReceived=patientTreatmentReceivedRepo.findbypatientVisitDiagnosisDetail(pvdd);
		List<String> treatmentReceivedasString=new ArrayList<String>();
		treatmentReceived.stream().forEach(e->treatmentReceivedasString.add(e.getTreatmentReceived()));
		
		PatientVisitDetailsResModel ppa = new PatientVisitDetailsResModel();
		ppa.setDoctorName(visitDiagnosisDetail.getUserCredential().getUserDetails().getUserName());
		ppa.setImportantFinding(visitDiagnosisDetail.getImportantFinding());
		ppa.setIndicationOfReferral(visitDiagnosisDetail.getIndicationOfReferral());
		ppa.setInvistigationDate(visitDiagnosisDetail.getInvistigationData());
		ppa.setIsReferralToPcw(visitDiagnosisDetail.getIsReferralToPcw());
		ppa.setPatientName(visitDiagnosisDetail.getpUserCredential().getUserDetails().getUserName());
		ppa.setPdCurrentDiagnosis(visitDiagnosisDetail.getPdCurrentDiagnosis_s_());
		ppa.setPdSecondaries(visitDiagnosisDetail.getPdSecondaries());
		ppa.setPdStageOfCancer(visitDiagnosisDetail.getPdStageOfCancer());
		ppa.setPdTreatmentDetails(visitDiagnosisDetail.getPdTreatmentDetails());
		 ppa.setPdTreatmentReceived(treatmentReceivedasString); 
		ppa.setPresentStatus(visitDiagnosisDetail.getPresentStatus());
		ppa.setTimeOfPresentationToPcwAfterDiagnosis(visitDiagnosisDetail.getTimeOfPresentationToPcwAfterDiagnosis());
		return ppa;
	}

	@Override
	public void savePatientPainAssesmentPainMasterMapping(PatientPainAssesmentPainMasterMapping ppapm) {
		patientPainAssesmentPainMasterMappingRepo.save(ppapm);

	}

	@Override
	public void savePatientPainAssesmentSymtomMasterMapping(PatientPainAssesmentSymtomMasterMapping ppasm) {
		patientPainAssesmentSymtomMasterMappingRepo.save(ppasm);
	}

	@Override
	public Optional<PatientPainAssessments> getByPatientPainAssessmentsId(Integer ppId) {
		Optional<PatientPainAssessments> painAssessments = patientPainAssessmentsRepo.findById(ppId);
		return painAssessments;
	}

	@Override
	public PatientDetail getPatientDetailsById(Integer pId) {
		PatientDetail pd = patientDetailsRepo.findById(pId).get();
		return pd;
	}

	@Override
	public List<PatientDataModel> getNumberOfVisitdBydocId(int docId) {
		return patientVisitDiagnosisDetailRepo.getNumberOfVisitdBydocId(docId);
	}

	@Override
	public List<PatientDataModel> getNumberOfActivePatientdocId(int docId) {
		return patientVisitDiagnosisDetailRepo.getNumberOfActivePatientdocId(docId);
	}

	@Override
	public List<PatientDataModel> getNumberOfMorphinAllocated(int docId) {
		return patientVisitDiagnosisDetailRepo.getNumberOfMorphinAllocated(docId);
	}

	@Override
	public List<PatientDataModel> getNumberOfTotalPatientdocId(int docId) {
		return patientVisitDiagnosisDetailRepo.getNumberOfTotalPatientdocId(docId);
	}

	@Override
	public PatientPainAssessmentResModel getPatientPainAssessmentsDetailByVisitId(Integer pvId) {
		PatientPainAssessments patientPainAssessments = patientPainAssessmentsRepo.findByPatientVisitDiagnosisDetailPvId(pvId);
		
		PatientPainAssessmentResModel patientPainAssessmentResModel = new PatientPainAssessmentResModel();
		patientPainAssessmentResModel.setPatientId(patientPainAssessments.getPatientDetail().getPId());
		patientPainAssessmentResModel.setPatientName(patientPainAssessments.getPatientDetail().getUserDetail().getUserName());
		patientPainAssessmentResModel.setPpDiagnosis(patientPainAssessments.getPpDiagnosis());
		patientPainAssessmentResModel.setPpHowLongReliefRetained(patientPainAssessments.getPpHowLongReliefRetained());
		patientPainAssessmentResModel.setPpHowMuchPainInterfereDailyActivity(patientPainAssessments.getPpHowMuchPainInterfereDailyActivity());
		patientPainAssessmentResModel.setPpInvistigationDate(patientPainAssessments.getPpInvistigationDate());
		patientPainAssessmentResModel.setPpIsRadiatefromPointOfOrigin(patientPainAssessments.getPpIsAnyPostureGivesRelief());
		patientPainAssessmentResModel.setPpIsRadiatefromPointOfOrigin(patientPainAssessments.getPpIsRadiatefromPointOfOrigin());
		patientPainAssessmentResModel.setPpIsReceivedTreatmentForYear(patientPainAssessments.getPpReceivedTreatmentForYearDetails());
		patientPainAssessmentResModel.setPpLadderStep(patientPainAssessments.getPpLadderStep());
		patientPainAssessmentResModel.setPpLocationOfPain(patientPainAssessments.getPpLocationOfPain());
		patientPainAssessmentResModel.setPpMaxPainExperienced(patientPainAssessments.getPpMaxPainExperienced());
		patientPainAssessmentResModel.setPpNrsRepresentingLeastPain(patientPainAssessments.getPpNrsRepresentingLeastPain());
		patientPainAssessmentResModel.setPpPainMaximumDuringDay(patientPainAssessments.getPpNrsRepresentingLeastPain());
		patientPainAssessmentResModel.setPpRadiatefromPointOfOriginDetails(patientPainAssessments.getPpPainReliefAfterMedication());
		patientPainAssessmentResModel.setPatientPainAssesmentPainMasterMapping(patientPainAssessments.getPatientPainAssesmentPainMasterMapping());
		patientPainAssessmentResModel.setPatientPainAssesmentSymtomMasterMapping(patientPainAssessments.getPatientPainAssesmentSymtomMasterMapping());
		patientPainAssessmentResModel.setPpWorsenedByActivityDetails(patientPainAssessments.getPpWorsenedByActivityDetails());
		patientPainAssessmentResModel.setPpIsWorsenedByActivity(patientPainAssessments.getPpIsWorsenedByActivity());
		patientPainAssessmentResModel.setPpIsAnyPostureGivesRelief(patientPainAssessments.getPpIsAnyPostureGivesRelief());
		patientPainAssessmentResModel.setPpIsReceivedTreatmentForYear(patientPainAssessments.getPpIsAnyPostureGivesRelief());
		patientPainAssessmentResModel.setPpPainReliefAfterMedication(patientPainAssessments.getPpPainReliefAfterMedication());
		patientPainAssessmentResModel.setBodyCirclePainAssesmentMapping(patientPainAssessments.getBodyCirclePainAssesmentMappings());
		return patientPainAssessmentResModel;
	}

	@Override
	public List<StateMaster> getAllStates() {
		return stateRepositoryRepo.findAll();
	}

	@Override
	public List<PatientDataModel> getNumberOfVisitsByPatientCredId(String emailId) {
		return patientVisitDiagnosisDetailRepo.findByPatientEmailId(emailId);
	}

	@Override
	public Integer getFirstTimeVisitPatient(Integer id, String poInitialDate, String poTillDate) {
		return patientVisitDiagnosisDetailRepo.getFirstTimeVisitPatient(id, poInitialDate, poTillDate);
	}

	@Override
	public Integer getFollowUpVisitPatient(Integer id, String poInitialDate, String poTillDate) {
		return patientVisitDiagnosisDetailRepo.getFollowUpVisitPatient(id, poInitialDate, poTillDate);
	}

	@Override
	public Integer getInactivePatientbyDocId(Integer id, String poInitialDate, String poTillDate) {
		return patientVisitDiagnosisDetailRepo.getInactivePatientbyDocId(id, poInitialDate, poTillDate);
	}
     @Override	
	public List<DistrictPopulationProjection> getPatientGeoLocationByDocId(int docId,  String geoInitialDate,String geoTillDate) {	
		List<DistrictPopulationProjection> patientDetails = patientDetailsRepo.findBydUserCredentialId(docId,geoInitialDate,geoTillDate);	
		System.err.println("KKKKKKKKKKKKKKKKKKKKKKKKKKK"+patientDetails.size());	
		return patientDetails;	
	}
     @Override
 	public Optional<PatientEdmontonSymptomAssessment> getESAByESAId(int esaId) {
 		return patientEdmontonSymptomAssessmentRepo.findById(esaId);
 	}

	@Override
	public List<PatientEdmontonSymptomAssessment> getESAByPatientcredId(int userId) {
		return PatientEdmontonSymptomAssessmentRepo.findByesaUserCredentialIdOrderByRecordTrackingAsc(userId);
	}
	@Override
	public List<PatientEdmontonSymptomAssessment> getESAByPatientUserId(String userId) {
		return PatientEdmontonSymptomAssessmentRepo.findByesaUserId(userId);
	}

	@Override
	public int saveEsa(ESAModel esaModel) {
		LocalDateTime currentTimestamp = LocalDateTime.now();
		// Convert LocalDateTime to Timestamp
        Timestamp timestamp = Timestamp.valueOf(currentTimestamp);
        // get userCred object using userEmail id
        UserCredential userCred= userCredentialRepo.findByUserName(esaModel.getPatientUserId());
        esaModel.getPatientEdmontonSymptomAssessments().setEsaUserCredential(userCred);
		esaModel.getPatientEdmontonSymptomAssessments().setRecordTracking(timestamp);
		PatientEdmontonSymptomAssessment pp=PatientEdmontonSymptomAssessmentRepo.save(esaModel.getPatientEdmontonSymptomAssessments());
		return pp.getId();
	}

	@Override
	public void saveESABodyCircle(BodyCirclePatientESAMapping ppapm) {
		bodyCirclePatientESAMappingRepo.save(ppapm);
		
	}

	
	@Override
	public List<Object> getPrescriptionByVisitId(int patientVisitId) {
		return patientMedicinePrescriptionRepo.findByPatientMedicinePrescriptionPatientVisitDiagnosisDetailPvId(patientVisitId);
	}

	@Override
	public Object findPatientPainAssessmentsDetailByVisitId(int patientVisitId) {
		return patientPainAssessmentsRepo.findByPatientVisitDiagnosisDetailPvId(patientVisitId);
	}

	@Override
	public PatientDetail getPatientDetailByUserDetailsId(UserDetail userDetailsId) {
		
		return patientDetailsRepo.getPatientDetailByUserDetilaId(userDetailsId);
	}

	
}
