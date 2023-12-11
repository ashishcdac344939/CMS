package in.fridr.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; 

import in.fridr.entity.PatientDetail;
import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.modal.UserModel;
import in.fridr.repository.CenterDetailsRepository;
import in.fridr.repository.UserCredentialRepo;
import in.fridr.service.DoctorService;
import in.fridr.service.LoginService;
import in.fridr.service.MedicineService;
import in.fridr.service.PatientService;
import in.fridr.service.UserServices;
import in.fridr.sms.MailUtility;
import in.fridr.util.Utility;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private MedicineService medicineService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private CenterDetailsRepository centerDetailsRepository;
	
	@Autowired
	private UserServices userServices;

	@GetMapping("/login")
	public ModelAndView login() {

		return new ModelAndView("/authentication/login");
	}

	@GetMapping("/register")
	public ModelAndView register() {
		System.err.println("docotr registration page");
		// get all center and send for registration
		ModelAndView mv = new ModelAndView("/authentication/register");
		mv.addObject("centerDetails", centerDetailsRepository.findAll());
		return mv;
	}

	// this is home page of all contorlller
	@RequestMapping(value = "/home")
	public ModelAndView demo(Authentication auth, HttpServletRequest httpRequest, Model model) {
		if (auth != null) {

			ModelAndView mav = new ModelAndView();
			UserCredential userDetails = loginService.findByEmail(auth.getName());

			/**List<MedicineQuantityMapProjection> getMedicineAllocatedByDoctorByDocId = medicineService
					.getMedicineQuantitiesForUserWith3EFormRequired(userDetails.getId(), "0001-01-1",
							new java.util.Date().toString());
			int medicineCount = 0;
			for (int i = 0; i < getMedicineAllocatedByDoctorByDocId.size(); i++) {
				medicineCount = (int) (medicineCount + getMedicineAllocatedByDoctorByDocId.get(i).getQuantity());
			}*/

			java.util.Date currentDate = new java.util.Date();

			// You can format the date as a string if needed
			SimpleDateFormat dateFormatForNewVisit = new SimpleDateFormat("yyyy-MM-dd");
			String formattedDate = dateFormatForNewVisit.format(currentDate);
			List<UserModel> userModelList = new ArrayList<>();
			List<Object[]> pd = new ArrayList<>();
			pd = doctorService.getNextVisistByDocId(userDetails.getId(), formattedDate);
			System.err.println(
					"LLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLKK  " + pd + " on date " + formattedDate);
			for (Object[] result : pd) {
				String userId = (String) result[0];
				String userName = (String) result[1];
				String investiGationDate = (String) dateFormatForNewVisit.format(result[2]);
				UserModel userModel = new UserModel(userId, userName, investiGationDate);
				userModelList.add(userModel);
			}
			mav.addObject("userName", userDetails.getUserDetails().getUserName());
			mav.addObject("userId", userDetails.getId());
			mav.addObject("userModelList", userModelList);
			mav.addObject("totalPatientVisits", patientService.getNumberOfVisitdBydocId(userDetails.getId()).size());
			mav.addObject("activePatient", patientService.getNumberOfActivePatientdocId(userDetails.getId()).size());
			mav.addObject("userRole", userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
			mav.addObject("totalPatient", patientService.getNumberOfTotalPatientdocId(userDetails.getId()).size());
			 mav.addObject("noOfMedicineWith3eForm",patientService.getNumberOfMorphinAllocated(userDetails.getId()).size());
			System.err.println("i am in demo");
			if (auth.getAuthorities().toArray()[0].toString().equals("ROLE_PATIENT")) {
				System.err.println("Patient login received");
				mav.addObject("totalNumberOfVisits",
						patientService.getNumberOfVisitsByPatientCredId(userDetails.getUserId()).size());
				if (userDetails.getpPatientVisitDiagnosisDetails().size() > 0) {
					Date date = new Date(
							userDetails.getpPatientVisitDiagnosisDetails().get(0).getInvistigationData().getTime());
					int numberOfDays = userDetails.getpPatientVisitDiagnosisDetails().get(0)
							.getPatientMedicinePrescriptions().get(0).getNoOfDays();
					// Convert java.sql.Date to LocalDate
					LocalDate localGivenDate = date.toLocalDate();

					// Calculate the date that is 15 days after the given date
					LocalDate fifteenDaysLater = localGivenDate.plusDays(numberOfDays);

					// Convert the LocalDate back to java.sql.Date if needed
					Date nextVisitDate = Date.valueOf(fifteenDaysLater);
					// Print the result
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					mav.addObject("nextVisitDate", dateFormat.format(nextVisitDate));
					mav.addObject("patientCredId", userDetails.getId());
					mav.addObject("lastVisitDate", date);
				} else {
					mav.addObject("nextVisitDate", "NA");
					mav.addObject("lastVisitDate", "NA");
				}
				if (userDetails.getpPatientVisitDiagnosisDetails().size() > 0)
					mav.addObject("prescrivedBy", userDetails.getpPatientVisitDiagnosisDetails().get(0)
							.getUserCredential().getUserDetails().getUserName());
				else {
					mav.addObject("prescrivedBy", "NA");
				}
				mav.addObject("visitDetails", userDetails.getpPatientVisitDiagnosisDetails());
				mav.setViewName("/pages/patientDashboard");
				return mav;
			} else if (auth.getAuthorities().toArray()[0].toString().equals("ROLE_PHARMACIST")) {
				mav.setViewName("pages/pharmacistDashboard");
				return mav;
			}
			mav.setViewName("/pages/doctorDashboard");
			return mav;
		} else {
			return new ModelAndView("/authentication/login");
		}

	}

	@GetMapping("/profile")
	public ModelAndView profile(@RequestParam Integer userDetailsId, Authentication auth) {
		System.err.println("docotr registration page");
		// get all center and send for registration
		ModelAndView mv = new ModelAndView("/pages/profile");
		boolean hasRoleDoctor = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DOCTOR"));
		boolean hasRolePatient = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PATIENT"));
		boolean hasRolePharmasist = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_PHARMACIST"));
		if (hasRoleDoctor) {
			// get the doctor details from the user details
			UserDetail ud = userServices.getUserDetailbyUserDetailsId(userDetailsId);
			mv.addObject("userId", ud.getUserCredential().getUserId());
			mv.addObject("userDetails", ud);
			mv.addObject("centerName", ud.getCenterDetails().getCenterName());
		}
		else if(hasRolePatient) {
			UserDetail ud = userServices.getUserDetailbyUserDetailsId(userDetailsId);
			mv.addObject("userId", ud.getUserCredential().getUserId());
			mv.addObject("userDetails", ud);
			mv.addObject("centerName", ud.getCenterDetails().getCenterName());
			
			PatientDetail pd=patientService.getPatientDetailByUserDetailsId(ud);
			mv.addObject("patientDetails", pd);
			mv.addObject("stateName", pd.getStateMaster().getStateName());
			mv.addObject("DistrictName", pd.getDistrictMaster().getDistrictName());
			mv.addObject("languageKnown", pd.getPatientLanguage());
			mv.addObject("TreatmentReceived", pd.getTreatmentRecevied());
		}else if(hasRolePharmasist) {
			UserDetail ud = userServices.getUserDetailbyUserDetailsId(userDetailsId);
			mv.addObject("userId", ud.getUserCredential().getUserId());
			mv.addObject("userDetails", ud);
			mv.addObject("centerName", ud.getCenterDetails().getCenterName());
		}

		return mv;
	}
	
   

}
