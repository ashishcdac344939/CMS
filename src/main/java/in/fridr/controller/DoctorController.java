package in.fridr.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.modal.UserModel;
import in.fridr.service.CommonService;

@RestController
@RequestMapping("/doctorForRest")
public class DoctorController {
//added by ashish
	@Autowired
	private CommonService commonService;
	//this is from kulvant  
	/**
	 * @author ashishy
	 * @param user
	 * @return
	 */
	
	@GetMapping("/doctorHome")
	public ModelAndView getHomePage(){
		return new ModelAndView("/pages/index");
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerDoctor(@Valid @RequestBody UserModel user){
		System.err.println("userdetails: "+ user);
		if(commonService.registerUser(user)!=null) {
			return ResponseEntity.ok("Doctor registered successfully");
		}else {
			return ResponseEntity.badRequest().body("Something went wrong");
		}
	}

}
