package in.fridr.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.BodyCirclePainAssesmentMapping;
import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.DistrictMaster;
import in.fridr.entity.MedicineMaster;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.PatientPainAssesmentSymtomMasterMapping;
import in.fridr.entity.PatientPainAssessments;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.StateMaster;
import in.fridr.entity.SymptomMedicineMapping;
import in.fridr.entity.SymptomsMaster;
import in.fridr.entity.UserCredential;
import in.fridr.modal.AssessmentPrescriptionStatus;
import in.fridr.modal.CircleData;
import in.fridr.modal.ESAModel;
import in.fridr.modal.GeoMapResponseModel;
import in.fridr.modal.OverallPatientModel;
import in.fridr.modal.PatientModel;
import in.fridr.modal.PatientPainAssessmentResModel;
import in.fridr.modal.PatientPainAssessmentsModel;
import in.fridr.modal.PatientVisitDiagnosisDetailsModel;
import in.fridr.projection.DistrictPopulationProjection;
import in.fridr.projection.PatientDataModel;
import in.fridr.repository.BodyCirclePainAssesmentMappingRepo;
import in.fridr.repository.BodyCirclePatientESAMappingRepo;
import in.fridr.repository.CenterDetailsRepository;
import in.fridr.repository.DistrictMasterRepo;
import in.fridr.repository.PatientDetailsRepo;
import in.fridr.repository.PatientPainAssessmentsRepo;
import in.fridr.repository.SymptomMedicineMappingRepo;
import in.fridr.repository.UserCredentialRepo;
import in.fridr.service.LoginService;
import in.fridr.service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {
	// ADDING NEW COMMENT
	// adding new changes
	@Autowired
	private PatientService patientService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private PatientPainAssessmentsRepo patientPainAssessmentsRepo;
	@Autowired
	private SymptomMedicineMappingRepo symptomMedicineMappingRepo;
	@Autowired
	private BodyCirclePainAssesmentMappingRepo bodyCirclePainAssesmentMappingRepo;
	@Autowired
	private DistrictMasterRepo districtMasterRepo;
	@Autowired
	private PatientDetailsRepo patientDetailsRepo;

	@Autowired
	private CenterDetailsRepository centerDetailsRepository;

	@Autowired
	private UserCredentialRepo userCredentialRepo;

	@Autowired 
	private BodyCirclePatientESAMappingRepo bodyCirclePatientESAMappingRepo;
	
	@PostMapping("/getDistrictByStateId")
	public ResponseEntity<List<DistrictMaster>> getDistrictByStateId(@RequestBody StateMaster stateMaster) {
		try {
			List<DistrictMaster> districtMasters = districtMasterRepo
					.findByStateMasterLgdStateCode(stateMaster.getLgdStateCode());
			return ResponseEntity.ok(districtMasters);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<>());
		}

	}

	/**
	 * @author ashishy
	 * @return
	 */
	@GetMapping("/viewVisitPatient")
	public ModelAndView viewVisitPatientPage(Authentication auth) {
		ModelAndView mav = new ModelAndView("/pages/viewVisitPatient");
		mav.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		return mav;
	}

	@GetMapping("/viewPatientESA")
	public ModelAndView viewPatientESAPage(Authentication auth) {
		UserCredential userDetails = loginService.findByEmail(auth.getName());
		ModelAndView mav = new ModelAndView("/pages/viewPatientESAPatient");
		mav.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		mav.addObject("userName", userDetails.getUserDetails().getUserName());
		mav.addObject("userId", userDetails.getId());
		mav.addObject("emailId",auth.getName());
		mav.addObject("patientCredId", userDetails.getId());
		/*
		 * List<PatientEdmontonSymptomAssessment> edmontonSymptomAssessments =
		 * patientService .getESAByPatientUserId(userDetails.getUserId());
		 */
		return mav;
	}

	/**
	 * @author ashishy
	 * @return
	 */
	@GetMapping("/add")
	public ModelAndView addpatientPage(Authentication auth) {
		ModelAndView mav = new ModelAndView("/pages/addPatientPage");
		mav.addObject("centerDetails", centerDetailsRepository.findAll());
		mav.addObject("stateDetails", patientService.getAllStates());
		return mav;
	}

	@PostMapping("/add")
	private ResponseEntity<?> addPatient(@Valid @RequestBody PatientModel patient) {
		if (patientService.addPatient(patient)) {
			return ResponseEntity.ok("patient registered successfully");
		} else {
			return ResponseEntity.badRequest().body("Something went wrong");
		}
	}

	/**
	 * 
	 * @param patientVisitDiagnosisDetail
	 * @return
	 */
	@RequestMapping("/savePatientVisitDiagnosisDetail")
	private ResponseEntity<?> savePatientVisitDiagnosisDetail(
			@RequestBody PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		if (patientService.savePatientVisitDiagnosisDetail(patientVisitDiagnosisDetail)) {
			return ResponseEntity.ok("value saved");
		} else {
			System.err.println("hello");
			return ResponseEntity.badRequest().body("Something went wrong");
		}
	}

	/**
	 * 
	 * @param patientPainAssessmentsModel
	 * @return
	 */
	@RequestMapping("/savePatientPainAssessmentsDetail")
	private ResponseEntity<?> savePatientPainAssessmentsDetail(
			@RequestBody PatientPainAssessmentsModel patientPainAssessmentsModel) {

		System.err.println("000000000000000000"+patientPainAssessmentsModel);
		int savePatientPainAssessmentsDetailId = patientService
				.savePatientPainAssessmentsDetail(patientPainAssessmentsModel.getPatientPainAssessments());
		System.err.println("-1-1-1-1-1-1");
		if (savePatientPainAssessmentsDetailId != 0) {
			// SAVE THE DETAILS IN Circle Mappin table

			for (CircleData data : patientPainAssessmentsModel.getPainMasterList()) {
				String axisX = data.getX().toString();
				String axisY = data.getY().toString();
				BodyCirclePainAssesmentMapping ppapm = new BodyCirclePainAssesmentMapping(axisX, axisY,
						new PatientPainAssessments(savePatientPainAssessmentsDetailId));

				bodyCirclePainAssesmentMappingRepo.save(ppapm);
			}

			System.err.println("2222222222222222");
			// SAVE THE DETAILS IN SYMTOM MASTER MAPPING TABLE
			for (Integer data : patientPainAssessmentsModel.getSymptomsMasterList()) {
				PatientPainAssesmentSymtomMasterMapping ppasm = new PatientPainAssesmentSymtomMasterMapping(
						new SymptomsMaster(data), new PatientPainAssessments(savePatientPainAssessmentsDetailId));
				patientService.savePatientPainAssesmentSymtomMasterMapping(ppasm);
			}
			System.err.println("33333333333333333");
			return ResponseEntity.ok("value saved");
		} else {
			return ResponseEntity.badRequest().body("Something went wrong");
		}
	}

	@RequestMapping("/getPatientPainAssessmentsDetailByVisitId")
	private ResponseEntity<PatientPainAssessmentResModel> getPatientPainAssessmentsDetailByVisitId(
			@RequestBody PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		PatientPainAssessmentResModel painAssessments = patientService
				.getPatientPainAssessmentsDetailByVisitId(patientVisitDiagnosisDetail.getPvId());

		return ResponseEntity.ok(painAssessments);

	}

	/**
	 * 
	 * @param patientPainAssessments
	 * @return
	 */
	@RequestMapping("/getPatientPainAssessmentsDetailById")
	private ResponseEntity<PatientPainAssessments> getPatientPainAssessmentsDetail(
			@RequestBody PatientPainAssessments patientPainAssessments) {

		return ResponseEntity.ok(patientService.getByPatientPainAssessmentsId(patientPainAssessments.getPpId()).get());
	}

	/**
	 * 
	 * @param emailId
	 * @param mobNo
	 * @param uniqueId
	 * @return
	 */
	@GetMapping("/getPatientDetails")
	private ResponseEntity<?> getPatientDetails(@RequestParam(required = false) String emailId,
			@RequestParam(required = false) String mobNo, @RequestParam(required = false) String uniqueId) {

		List<PatientVisitDiagnosisDetailsModel> PatientVisitDiagnosisDetailsModelList = new ArrayList<>();

		List<PatientDataModel> pvdd = patientService.getPatientDetails(emailId, mobNo, uniqueId);
		System.err.println(pvdd);
		if(pvdd.size()>0) {
		if (pvdd.get(0).getImportantFindings() != null) {
			for (PatientDataModel data : pvdd) {

				boolean isPainAssessmentFilled = (patientService
						.findPatientPainAssessmentsDetailByVisitId(data.getPvid()) != null);
				boolean isPrescriptionFilled = (patientService.getPrescriptionByVisitId(data.getPvid()).size() != 0);

				PatientVisitDiagnosisDetailsModel PatientVisitDiagnosisDetailsModel = new PatientVisitDiagnosisDetailsModel(
						data.getPatientId(), data.getPresentStatus(), data.getUserName(), data.getImportantFindings(),
						data.getSecondaryFindings(), data.getNextvisitdate(), data.getPvid(), data.getPdstageOfcancer(),
						data.getDoctorUcId(), data.getPatientUcId(), data.getInvistigationdate(),
						data.getPdcurrentdiagnosis(), data.getPdtreatmentreceived(), isPainAssessmentFilled,
						isPrescriptionFilled);

				PatientVisitDiagnosisDetailsModelList.add(PatientVisitDiagnosisDetailsModel);
			}
		} else {
			for (PatientDataModel data : pvdd) {
				PatientVisitDiagnosisDetailsModelList
						.add(new PatientVisitDiagnosisDetailsModel(data.getPatientId(), data.getPatientUcId()));
			}
		}
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User does not exist");
		}
		return ResponseEntity.ok(PatientVisitDiagnosisDetailsModelList);
	}

	/**
	 * 
	 * @param pvdd
	 * @return
	 */
	@GetMapping("/getPatientVisitDetails/{pvdd}")
	private ResponseEntity<?> getPatientVisitDetails(@PathVariable int pvdd) {
		return ResponseEntity.ok(patientService.getPatientVisitDiagnosiedDetails(pvdd));
	}

	/**
	 * 
	 * @param patientDetail
	 * @return
	 */
	@PostMapping("/getPatientByPatientId")
	private ResponseEntity<OverallPatientModel> getPatientByPatientId(@RequestBody PatientDetail patientDetail) {
		PatientDetail pd = patientService.getPatientDetailsById(patientDetail.getPId());
		OverallPatientModel overallPatientModel = new OverallPatientModel(pd.getPComprehensiveCarePlans(),
				pd.getUserDetail(), pd.getPatientPainAssessments(), pd.getPatientVisitDiagnosisDetails());
		return ResponseEntity.ok(overallPatientModel);
	}

	@PostMapping("/getSymptomsAndMedicinesByPatientVisitId")
	private ResponseEntity<List<List<SymptomMedicineMapping>>> getSymptomsAndMedicinesByPatientVisitId(
			@RequestBody PatientPainAssessments patientPainAssessmentsReq) {

		List<Map<SymptomsMaster, List<MedicineMaster>>> symptomsAndMedicineMap = new ArrayList<>();
		PatientPainAssessments patientPainAssessments1 = patientPainAssessmentsRepo
				.findByPatientVisitDiagnosisDetailPvId(patientPainAssessmentsReq.getPpId());
		Optional<PatientPainAssessments> patientPainAssessments = patientPainAssessmentsRepo
				.findById(patientPainAssessments1.getPpId());
		List<PatientPainAssesmentSymtomMasterMapping> symptomsList = patientPainAssessments.get()
				.getPatientPainAssesmentSymtomMasterMapping();

		List<List<SymptomMedicineMapping>> listlListOfSymptomMasterMappin = new ArrayList<>();
		for (PatientPainAssesmentSymtomMasterMapping data : symptomsList) {
			List<SymptomMedicineMapping> medicineMastersList = symptomMedicineMappingRepo
					.findBySymptomsMasterSymptomId(data.getSymptomsMaster().getSymptomId());
			listlListOfSymptomMasterMappin.add(medicineMastersList);
		}

		return ResponseEntity.ok(listlListOfSymptomMasterMappin);
	}

	@GetMapping("/isPrescriptionAndPainAssessmentFilled")
	public ResponseEntity<AssessmentPrescriptionStatus> isPrescriptionAndPainAssessmentFilled(
			@RequestParam(name = "patientVisitId") int patientVisitId) {
		boolean isPainAssessmentFilled = (patientService
				.findPatientPainAssessmentsDetailByVisitId(patientVisitId) != null);
		boolean isPrescriptionFilled = (patientService.getPrescriptionByVisitId(patientVisitId) != null);

		AssessmentPrescriptionStatus status = new AssessmentPrescriptionStatus(isPainAssessmentFilled,
				isPrescriptionFilled);

		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/getPatientOverViewChart/{docId}")
	private ResponseEntity<?> getPatientOverViewChart(@PathVariable int docId,
			@RequestParam(name = "poInitialDate") String poInitialDate,
			@RequestParam(name = "poTillDate") String poTillDate) {
		System.out.println("getPatientOverViewChart" + poInitialDate + ' ' + poTillDate);
		int followUpPatient = 0;
		int firstTimeVisit = 0;
		int inActivePatient = 0;
		if (patientService.getFirstTimeVisitPatient(docId, poInitialDate, poTillDate) != null) {
			System.err.println("I am in getFirstTimeVisitPatient ");
			firstTimeVisit = patientService.getFirstTimeVisitPatient(docId, poInitialDate, poTillDate);
			System.err.println("I am in getFirstTimeVisitPatient " + firstTimeVisit);
		}
		if (patientService.getFollowUpVisitPatient(docId, poInitialDate, poTillDate) != null) {

			followUpPatient = patientService.getFollowUpVisitPatient(docId, poInitialDate, poTillDate);
		}
		if (patientService.getInactivePatientbyDocId(docId, poInitialDate, poTillDate) != null)
			inActivePatient = patientService.getInactivePatientbyDocId(docId, poInitialDate, poTillDate);
		String type = "pie";
		Map<String, Object> response = new HashMap<>();
		List<Integer> patientData = new ArrayList<>();
		List<String> patientDataMarker = new ArrayList<>();
		System.err.println("I am in getFollowUpVisitPatient" + followUpPatient);
		patientData.add(0, firstTimeVisit);
		patientData.add(1, followUpPatient);
		patientData.add(2, inActivePatient);
		patientDataMarker.add(0, "1st Time Visit");
		patientDataMarker.add(1, "Follow Up Visits");
		patientDataMarker.add(2, "Inactive Patients");

		response.put("values", patientData);
		response.put("labels", patientDataMarker);
		response.put("type", type);
		return ResponseEntity.ok(response);

	}

	@GetMapping("/getPatientGeoLocationByDocId/{docId}")
	private ResponseEntity<GeoMapResponseModel> getPatientGeoLocationByDocId(@PathVariable int docId,
			@RequestParam(name = "geoInitialDate") String geoInitialDate,
			@RequestParam(name = "geoTillDate") String geoTillDate) {
		List<DistrictPopulationProjection> pd = patientService.getPatientGeoLocationByDocId(docId, geoInitialDate,
				geoTillDate);
		List<String> latitude = new ArrayList<>();
		List<String> longitude = new ArrayList<>();
		List<String> cityName = new ArrayList<>();
		List<Long> pop = new ArrayList<>();
		GeoMapResponseModel gmr = new GeoMapResponseModel();
		for (DistrictPopulationProjection data : pd) {
			latitude.add(data.getLatitude());
			longitude.add(data.getLongitude());
			cityName.add(data.getDistrictName());
			pop.add(data.getPopulation());
		}
		gmr.setCityName(cityName);
		gmr.setLatitude(latitude);
		gmr.setLongitude(longitude);
		gmr.setPop(pop);
		return ResponseEntity.ok(gmr);

	}

	@GetMapping("/getESADataByESAId")
	private ResponseEntity<?> getESAByESAId(@RequestParam("esaId") int esaId) {
		System.err.println("************: " + esaId);
		Optional<PatientEdmontonSymptomAssessment> pes = patientService.getESAByESAId(esaId);
		System.err.println(pes.get().getAnxiety());
		return ResponseEntity.ok(pes);

	}

	@GetMapping("/getESAByPatientcredId/{patientCredId}")
	public ResponseEntity<?> getESAByPatientcredId(@PathVariable String patientCredId) {
		System.err.println("aaaaaaaaaaaaaaaa: " + patientCredId);
		// find userCred
		List<PatientEdmontonSymptomAssessment> edmontonSymptomAssessments = patientService
				.getESAByPatientUserId(patientCredId);
		return ResponseEntity.ok(edmontonSymptomAssessments);
		// return null;

	}

	@PostMapping("/addEdmSymptomAssessment")
	public ResponseEntity<?> addEdmSymptomAssessment(@RequestBody ESAModel esaModel) {

		int esaId = patientService.saveEsa(esaModel);
		if (esaId != 0) {
			// SAVE THE DETAILS IN Circle Mappin table
        ArrayList<BodyCirclePatientESAMapping> allBodyCirclePatientESAMapping=new ArrayList<BodyCirclePatientESAMapping>();
			for (CircleData data : esaModel.getCircleData()) {
				String axisX = data.getX().toString();
				String axisY = data.getY().toString();
				
				BodyCirclePatientESAMapping ppapm = new BodyCirclePatientESAMapping(axisX, axisY,
						new PatientEdmontonSymptomAssessment(esaId));
				
				allBodyCirclePatientESAMapping.add(ppapm);
				
			}
			bodyCirclePatientESAMappingRepo.saveAll(allBodyCirclePatientESAMapping);
		} else {
			return ResponseEntity.badRequest().body("Something went wrong");
		}
		return ResponseEntity.ok("EDA Added Successfully");
	}

	@GetMapping("/getESAMapDataByPatientCredId")
	private ModelAndView getESAMapDataByPatientCredId(Authentication auth,
			@RequestParam(name = "patientEmailId", required = false) String patientEmailId) {

		ModelAndView mav = new ModelAndView("/pages/viewPatientESAMaps");

		UserCredential patientUserDetails = loginService.findByEmail(patientEmailId);
		mav.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		mav.addObject("userName", patientUserDetails.getUserDetails().getUserName());
		mav.addObject("userId", patientUserDetails.getId());
		mav.addObject("patientCredId", patientUserDetails.getId());
		mav.addObject("patientName", userCredentialRepo.getUserNameByUserId(patientEmailId));
		System.err.println("userDetails.getId(): " + patientUserDetails.getId());
		List<PatientEdmontonSymptomAssessment> edmontonSymptomAssessments = patientService
				.getESAByPatientcredId(patientUserDetails.getId());
		System.err.println("edmontonSymptomAssessments: " + edmontonSymptomAssessments.size());
		List<String> symptomList = Arrays.asList("pain", "tiredness", "nausea", "depression", "anxiety", "drowsiness",
				"appetite", "wellbeing", "sob", "others");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (String symptom : symptomList) {
			Map<List<String>, List<Integer>> dataPoint = new HashMap<>();
			List<String> dateList = new ArrayList<>();
			List<String> completedBy = new ArrayList<>();
			List<Integer> valueList = new ArrayList<>();

			switch (symptom) {
			case "pain":
				List<java.sql.Timestamp> timeStamp = new ArrayList<>();
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {

					String formattedDate = dateFormat.format(data.getRecordTracking());
					// timeStamp.add(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getPain()));
					completedBy.add(data.getCompletedBy());
					// dataPoint.put(data.getRecordTracking().toString(),data.getPain());
				}
				dataPoint.put(dateList, valueList);
				/*
				 * List<String> timestampStrings = timeStamp.stream() .map(Timestamp::toString)
				 * .collect(Collectors.toList());
				 */
				mav.addObject("painValueList", valueList);
				System.err.println("painValueList: " + valueList.size());
				mav.addObject("painDates", dateList);
				mav.addObject("paincompletedBy", completedBy);
				break;
			case "tiredness":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getTiredness()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("tirednessValueList", valueList);
				mav.addObject("tirednessDates", dateList);
				mav.addObject("tirednesscompletedBy", completedBy);
				System.err.println("tirednessValueList: " + valueList.size());
				break;
			case "nausea":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getNausea()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("nauseaValueList", valueList);
				mav.addObject("nauseaDates", dateList);
				mav.addObject("nauseacompletedBy", completedBy);
				System.err.println("nauseaValueList: " + valueList.size());
				break;
			case "depression":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getDepression()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("depressionValueList", valueList);
				mav.addObject("depressionDates", dateList);
				mav.addObject("depressioncompletedBy", completedBy);
				System.err.println("depressionValueList: " + valueList.size());
				break;
			case "anxiety":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getAnxiety()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("anxietyValueList", valueList);
				mav.addObject("anxietyDates", dateList);
				mav.addObject("anxietycompletedBy", completedBy);
				break;
			case "drowsiness":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getDrowsiness()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("drowsinessValueList", valueList);
				mav.addObject("drowsinessDates", dateList);
				mav.addObject("drowsinesscompletedBy", completedBy);
				break;
			case "appetite":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getAppetite()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("appetiteValueList", valueList);
				mav.addObject("appetiteDates", dateList);
				mav.addObject("appetitecompletedBy", completedBy);
				break;
			case "wellbeing":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getWellbeing()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("wellbeingValueList", valueList);
				mav.addObject("wellbeingDates", dateList);
				mav.addObject("wellbeingcompletedBy", completedBy);
				break;
			case "sob":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getSob()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("sobValueList", valueList);
				mav.addObject("sobDates", dateList);
				mav.addObject("sobcompletedBy", completedBy);
				break;
			case "others":
				for (PatientEdmontonSymptomAssessment data : edmontonSymptomAssessments) {
					String formattedDate = dateFormat.format(data.getRecordTracking());
					dateList.add(formattedDate);
					valueList.add(Integer.parseInt(data.getOthers()));
					completedBy.add(data.getCompletedBy());
				}
				mav.addObject("othersValueList", valueList);
				mav.addObject("othersDates", dateList);
				mav.addObject("otherscompletedBy", completedBy);
				break;
			default:
				// Handle the case where the symptomName is not recognized
				// dataPoint.put("value", null);
				break;
			}

		}

		return mav;

	}

}
