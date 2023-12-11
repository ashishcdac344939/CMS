package in.fridr.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.PatientDetail;
import in.fridr.entity.UserCredential;
import in.fridr.service.DoctorService;
import in.fridr.service.LoginService;
import in.fridr.service.PatientService;

@Controller
@RequestMapping("/doctor")
public class DoctorWebController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/doctorDashboard")
	public ModelAndView getDoctorHomePage(Authentication auth, HttpServletRequest httpRequest,Model model) {
		ModelAndView mav = new ModelAndView("/pages/index");
		 // Get the current date and time
		System.err.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+patientService.getNumberOfMorphinAllocated(Integer.parseInt(httpRequest.getParameter("userId"))).size());
		mav.addObject("userName",httpRequest.getParameter("userName"));
		mav.addObject("userId",httpRequest.getParameter("userId"));
		mav.addObject("totalPatientVisits", patientService.getNumberOfVisitdBydocId(Integer.parseInt(httpRequest.getParameter("userId"))).size());
		mav.addObject("activePatient", patientService.getNumberOfActivePatientdocId(Integer.parseInt(httpRequest.getParameter("userId"))).size());
		mav.addObject("numberOfMorphinAlloted", patientService.getNumberOfMorphinAllocated(Integer.parseInt(httpRequest.getParameter("userId"))).size());
		mav.addObject("totalPatient", patientService.getNumberOfTotalPatientdocId(Integer.parseInt(httpRequest.getParameter("userId"))).size());
		//mav.addObject("userRole",userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
		mav.addObject("token", httpRequest.getParameter("token"));
		return mav;
		
	}
	@RequestMapping(value = "/home")
	public ModelAndView demo(Authentication auth, HttpServletRequest httpRequest,Model model) {
		/*ModelAndView mav = new ModelAndView("/pages/index");
		UserCredential userDetails = loginService.findByEmail(auth.getName());
		mav.addObject("userName",userDetails.getUserDetails().getUserName());
		mav.addObject("userId",userDetails.getId());
		mav.addObject("totalPatientVisits", patientService.getNumberOfVisitdBydocId(userDetails.getId()).size());
		mav.addObject("activePatient", patientService.getNumberOfActivePatientdocId(userDetails.getId()).size());
		//mav.addObject("numberOfMorphinAlloted", patientService.getNumberOfMorphinAllocated(userDetails.getId()).size());
		mav.addObject("userRole", userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
		mav.addObject("totalPatient", patientService.getNumberOfTotalPatientdocId(userDetails.getId()).size());
		//mav.addObject("userRole",userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
		System.err.println("i am in demo");*/
		 return new ModelAndView("redirect:../user/home");
		
	}
	@GetMapping("/AddESA")
	public ModelAndView getESAPage() {
		return new ModelAndView("/pages/addESA");
	}
}
