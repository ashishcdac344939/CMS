package in.fridr.controller;

import java.sql.Timestamp;
import java.util.List;

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

import in.fridr.entity.MedicineMaster;
import in.fridr.entity.SymptomMedicineMapping;
import in.fridr.entity.SymptomsMaster;
import in.fridr.modal.PatientPrescriptionModel;
import in.fridr.modal.SaveSymptomsMedicineMappingReq;
import in.fridr.projection.MedicineQuantityMapProjection;
import in.fridr.projection.PatientPrescptionProjection;
import in.fridr.repository.SymptomMedicineMappingRepo;
import in.fridr.service.MedicineService;

@RestController
@RequestMapping("/medicine")
public class MedicineController {
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private SymptomMedicineMappingRepo symptomMedicineMappingRepol;

	// get all symptommapping with medicine
	@GetMapping("/getAllSymptomsMedicineMapping")
	public ResponseEntity<?> getAllSymptomMedicineMapping() {
		return ResponseEntity.ok(medicineService.getAllSymptomMedicineMapping());
	}

	@GetMapping("/getMedicineCountByMedicineId")
	public ResponseEntity<?> getMedicineCountByMedicineId(@RequestParam int mId) {
		return ResponseEntity.ok(medicineService.getMedicineCountByMedicineId(mId));
	}

	@GetMapping("/mapSymtomWithMedicine")
	public ModelAndView mapSymtomWithMedicine(Authentication auth) {

		ModelAndView mv = new ModelAndView("/pages/mapSymptomWithMedicine");
		mv.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		return mv;
	}

	@GetMapping("/add")
	public ModelAndView addMedicinePage(Authentication auth) {

		ModelAndView mv = new ModelAndView("/pages/addMedicine");
		mv.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		return mv;
	}

	@PostMapping("/add")
	public ResponseEntity<?> addMedicine(@RequestBody MedicineMaster medicineMaster) {
		medicineMaster.setRecordTracking(new Timestamp(System.currentTimeMillis()));
		return ResponseEntity.ok(medicineService.addMedicine(medicineMaster));
	}

	@GetMapping("/getAllMedicine")
	public ResponseEntity<?> getAllMedicine(@RequestParam(required = false) Integer userCredId) {
		
		return ResponseEntity.ok(medicineService.getAllMedicine(userCredId));
	}

	@PostMapping("/getMedicineBySymptomId")
	public ResponseEntity<List<MedicineMaster>> getMedicineBySymptomId(@RequestBody SymptomsMaster symptomsMaster) {

		return ResponseEntity.ok(medicineService.getMedicineBySymptomId(symptomsMaster.getSymptomId()));

	}

	/**
	 * 
	 * @param patientPrescriptionModel
	 * @return
	 */
	@PostMapping("/savePatientPrescription")
	public ResponseEntity<?> savePatientPrescription(@RequestBody PatientPrescriptionModel patientPrescriptionModel) {
		try {
		System.err.println("1111111111111111: "+patientPrescriptionModel.getMedicineAndQuantity());
		boolean isDataSaved = medicineService.savePatientPrescription(patientPrescriptionModel);
			if (isDataSaved) {
				return ResponseEntity.ok("Details Saved");
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/getPatientPrescription")
	public ResponseEntity<?> getPatientDescription(@RequestParam int patientVisitId) {
		return ResponseEntity.ok(medicineService.getPatientPrescption(patientVisitId));

	}

	@PostMapping("/mapSymptomWithMedicines")
	public ResponseEntity<?> mapSymptomWithMedicines(
			@RequestBody SaveSymptomsMedicineMappingReq symptomsMedicineMappingReq) {
		for (int i = 0; i < symptomsMedicineMappingReq.getMedicineIds().size(); i++) {
			SymptomMedicineMapping symptomMedicineMapping = new SymptomMedicineMapping();
			symptomMedicineMapping.setSymptomsMaster(new SymptomsMaster(symptomsMedicineMappingReq.getSymptomId()));
			symptomMedicineMapping
					.setMedicineMaster(new MedicineMaster(symptomsMedicineMappingReq.getMedicineIds().get(i)));
			symptomMedicineMapping.setRecordTracking(new Timestamp(System.currentTimeMillis()));
			symptomMedicineMappingRepol.save(symptomMedicineMapping);
		}

		return ResponseEntity.ok("Mapping Saved Successfully");

	}
	@GetMapping("/3eFormMedicineCount/{docId}")
    public List<MedicineQuantityMapProjection> getUser5MedicinesQuantitiesWith3EFormRequired(@PathVariable int docId,@RequestParam(name = "barInitialDate") String barInitialDate,
            @RequestParam(name = "barTillDate") String barTillDate) {
		System.out.println("filtered dates are "+barInitialDate +" "+barTillDate);
        return medicineService.getMedicineQuantitiesForUserWith3EFormRequired(docId,barInitialDate,barTillDate);
    }

}
