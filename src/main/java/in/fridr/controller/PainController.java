package in.fridr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.fridr.entity.PainMaster;
import in.fridr.service.PainService;
@RestController
@RequestMapping("/pain")
public class PainController {
	
	@Autowired
	private PainService painService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addPain(@RequestBody PainMaster painMaster){		
		return ResponseEntity.ok(painService.addPainMaster(painMaster));
		
	}
	@GetMapping("/getAllPainDetails")
	public ResponseEntity<?> getAllPainDetails(){		
		return ResponseEntity.ok(painService.getAllPainDetails());
		
	}

}
