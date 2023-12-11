package in.fridr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.MedicineMaster;
import in.fridr.modal.MedicalModel;
import in.fridr.modal.MedicineAllocationModel;
import in.fridr.modal.UserModel;
import in.fridr.repository.CenterDetailsRepository;
import in.fridr.service.CommonService;
import in.fridr.service.MedicineService;

@Controller
@RequestMapping("/pharmasist")
public class PharmacistController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private CenterDetailsRepository centerDetailsRepository;
	
	@GetMapping("/add")
	public ModelAndView addPharmacist(Authentication auth) {
		ModelAndView mv=new ModelAndView("/pages/addPharmacist");
		mv.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		  mv.addObject("centerDetails", centerDetailsRepository.findAll());
		return mv;
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> registerDoctor(@Valid @RequestBody UserModel user){
		System.err.println("userdetails: "+ user);
		if(commonService.registerUser(user)!=null) {
			return ResponseEntity.ok("pharmacist registered successfully");
		}else {
			return ResponseEntity.badRequest().body("Something went wrong");
		}
	}
	
	
	@GetMapping("/updateInventery")
	public ModelAndView enventeryUpdatePage(Authentication auth) {
		ModelAndView mv=new ModelAndView("/pages/InventeryUpdate");
		// get the list of medicine and their count
		mv.addObject("userRole", auth.getAuthorities().toArray()[0].toString());
		return mv;
	}
	@GetMapping("/getMecineAllocatedDetailsByPmvrId")
	public ResponseEntity<?> getMecineAllocatedDetailsByPmvrId(@RequestParam int pmvrId) {
		//System.err.println("LLLLLLLLLLLLLLLL: "+ pmvrId);
		return  ResponseEntity.ok(commonService.getMedicineAllocatedInOneMedicalVisit(pmvrId));
	}
	
	
	@PostMapping("/updateMedicineCount")
	public ResponseEntity<?> updateInventry(@RequestBody MedicineMaster medicineMaster){
		return ResponseEntity.ok(medicineService.updateInventery(medicineMaster));
	}
	
	@GetMapping("/getAllMedicineInventry")
	public ResponseEntity<?> getAllInventry(){
		return ResponseEntity.ok(commonService.getAllMedicineInventry());
	}
	
	@GetMapping("/getMedicineByPrescriptonId")
	public ResponseEntity<?> getMedicineByPrescriptionId(@RequestParam int pvid){
		return medicineService.getPatientMedicineRecord(pvid);
	}
	
	
	@PostMapping("/saveAllocatedMedicine")
	public ResponseEntity<?> updateInventry(@RequestBody MedicineAllocationModel medicineAllocationModel){
		System.err.println("reqqqqqqqqqqqqq: "+medicineAllocationModel);
		commonService.saveMedicineAllocated(medicineAllocationModel);
		return ResponseEntity.ok("medicine allocation saved successfully");
	}
}
