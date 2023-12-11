package in.fridr.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.SymptomsMaster;
import in.fridr.modal.CircleData;
import in.fridr.modal.ESAModel;
import in.fridr.service.SymptomService;

@RestController
@RequestMapping("/symptom")
public class SysmptomController {

	@Autowired
	private SymptomService symptomService;

	@GetMapping("/add")
	public ModelAndView addSymptomPage(Authentication auth) {

		ModelAndView mv = new ModelAndView("/pages/addSymptom"); 
		mv.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		return mv;
	}
	@PostMapping("/add")
	public ResponseEntity<?> addSymptom(@RequestBody SymptomsMaster symptomMaster) {

		symptomMaster.setRecordTracking(new Timestamp(System.currentTimeMillis()));
		symptomMaster.setSymptomStatus(true);
		return ResponseEntity.ok(symptomService.addSymption(symptomMaster));
	}
	@GetMapping("/getAllSymptoms")
	public ResponseEntity<?> getAllSymptoms() {

		return ResponseEntity.ok(symptomService.getAllSymptoms());
	}

}
