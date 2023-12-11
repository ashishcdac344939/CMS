package in.fridr.controller;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.UserCredential;
import in.fridr.modal.MessageResponse;
import in.fridr.response.AuthenticationResponse;
import in.fridr.service.LoginService;
import in.fridr.sms.MailUtility;
import in.fridr.sms.TwilioUtility;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author kulvantk
 *
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(TwilioUtility.class);
	@Autowired
	private Environment environment;
	@Autowired
	private LoginService loginService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	private String otp;

	/**
	 * 
	 * @param userCredential
	 * @return
	 */
	@PostMapping("/getOtp")
	public ResponseEntity<MessageResponse> createOtp(@RequestBody UserCredential userCredential) {
		logger.info("Otp request received");
		TwilioUtility twilloUtility = new TwilioUtility(environment);
		MailUtility mu = new MailUtility();
		MessageResponse otp_response = new MessageResponse();
		HttpStatus httpStatus = null;
		// generate four digit numeric otp
		otp = twilloUtility.generateOTP(4);
		try {
			// get the user by email id
			UserCredential requesedUserCredential = loginService.findByEmail(userCredential.getUserId());
			if (requesedUserCredential != null) {
				// Construct the HTML content as a string
	            String htmlContent = "<!DOCTYPE html>\n" +
	                    "<html>\n" +
	                    "<head>\n" +
	                    "    <title>One-Time Password</title>\n" +
	                    "</head>\n" +
	                    "<body>\n" +
	                    "    <table>\n" +
	                    "        <tr>\n" +
	                    "            <td>\n" +
	                    "                <h1>Your One-Time Password (OTP)</h1>\n" +
	                    "                <p>Your one-time password (OTP) for authentication is:</p>\n" +
	                    "                <p>" + otp + "\"</p>\n" +
	                    " <p>This OTP is valid for a short period and is used to verify your identity. Do not share this OTP with anyone.</p>\n" +
	                   
	                    "                <p>If you did not request a OTP, please ignore this email. Your account's security is important to us, and no changes will be made.</p>\n" +
	                    "                <p>If you have any questions or need further assistance, please contact our support team at [cms@gmail.com].</p>\n" +
	        
	                    "            </td>\n" +
	                    "        </tr>\n" +
	                    "    </table>\n" +
	                    "</body>\n" +
	                    "</html>";
				if (mu.sendMail(userCredential.getUserId(), htmlContent)) {
					String encodedPassword = passwordEncoder.encode(otp);
					requesedUserCredential.setOtp(encodedPassword);
					boolean idCreated = loginService.saveOtp(requesedUserCredential);
					otp_response.setMessage("otp sent successfully");
					httpStatus = HttpStatus.OK;
				}
			} else {
				otp_response.setMessage("Email Id does not exist!!");
				httpStatus = HttpStatus.BAD_REQUEST;
			}
			return new ResponseEntity<MessageResponse>(otp_response, httpStatus);
		} catch (Exception e) {
			e.printStackTrace();
			otp_response.setMessage("Something went wrong,Please try again!!");
			return new ResponseEntity<MessageResponse>(otp_response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * 
	 * @param userCredential
	 */
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate1(@Valid @RequestBody UserCredential userCredential,
			HttpServletRequest request, HttpServletResponse response, BindingResult br) throws Exception {

		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		try {

			logger.info("Authentication request received by: " + userCredential.getUserId());

			if (br.hasErrors()) {
				authenticationResponse.setAuthenticated(false);
				return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.UNAUTHORIZED);

			}
			UserCredential userDetails = loginService.findByEmail(userCredential.getUserId());
			// Authenticate the username and password
			if (userCredential.getPasswrord() != null) {
				userDetails.setLoginWithPassword(true);
				loginService.updateCredentialsDetails(userDetails);
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUserId(),
						userCredential.getPasswrord()));
			} else {
				userDetails.setLoginWithPassword(false);
				loginService.updateCredentialsDetails(userDetails);
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userCredential.getUserId(), userCredential.getOtp()));
			}

			@SuppressWarnings("deprecation")
			String token = Jwts.builder().setSubject(userDetails.getUserId())
					.setExpiration(new Date(System.currentTimeMillis()
							+ Long.parseLong(environment.getProperty("token.expiration_time"))))
					.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();
			response.addHeader("token", token);
			response.addHeader("userId", userDetails.getUserId());

			authenticationResponse.setAuthenticated(true);
			
			authenticationResponse.setRole(userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
			authenticationResponse.setUserDetail(userDetails.getUserDetails());
			authenticationResponse.setUserId(userDetails.getUserId());
			// delete otp from user_credentials table
			userDetails.setOtp(null);
			loginService.saveOtp(userDetails);
			logger.info("user authenticated:" + userDetails.getUserId());

			return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);

		}

		catch (BadCredentialsException e) {
			e.printStackTrace();
			authenticationResponse.setAuthenticated(false);
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.UNAUTHORIZED);
		}

		catch (Exception e) {
			e.printStackTrace();
			authenticationResponse.setAuthenticated(false);
			return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/**
	 * 
	 * @param userCredential
	 */
	@PostMapping("/authenticateByWeb")
	public ModelAndView authenticate1ByWeb( @ModelAttribute UserCredential userCredential,
			HttpServletRequest request, HttpServletResponse response, BindingResult br) throws Exception {
		System.err.println(userCredential.getUserId());
		logger.info("Authentication request received by: " + userCredential.getUserId()+" "+userCredential.getPasswrord());
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();
		try {

			

			if (br.hasErrors()) {
				System.err.println("ggggggggggggggggggggggggggggggggggggggggggggggggggggg");
				authenticationResponse.setAuthenticated(false);
				return new ModelAndView("error");

			}
			UserCredential userDetails = loginService.findByEmail(userCredential.getUserId());
			// Authenticate the username and password
			if (userCredential.getPasswrord() != null && !userCredential.getPasswrord().equals(" ")) {
				userDetails.setLoginWithPassword(true);
				loginService.updateCredentialsDetails(userDetails);
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredential.getUserId(),
						userCredential.getPasswrord()));
			} else {
				userDetails.setLoginWithPassword(false);
				loginService.updateCredentialsDetails(userDetails);
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(userCredential.getUserId(), userCredential.getOtp()));
			}

			@SuppressWarnings("deprecation")
			String token = Jwts.builder().setSubject(userDetails.getUserId())
					.setExpiration(new Date(System.currentTimeMillis()
							+ Long.parseLong(environment.getProperty("token.expiration_time"))))
					.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret")).compact();
			response.addHeader("Authorization", token);
			response.addHeader("userId", userDetails.getUserId());

			authenticationResponse.setAuthenticated(true);
			
			authenticationResponse.setRole(userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
			authenticationResponse.setUserDetail(userDetails.getUserDetails());
			authenticationResponse.setUserId(userDetails.getUserId());
			// delete otp from user_credentials table
			userDetails.setOtp(null);
			loginService.saveOtp(userDetails);
			logger.info("user authenticated:" + userDetails.getUserId());
			//after login model
			ModelAndView mav = new ModelAndView();
			mav.addObject("userName",userDetails.getUserDetails().getUserName());
			mav.addObject("userId",userDetails.getId());
			mav.addObject("userRole",userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
			mav.addObject("token", token);
			switch (userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName().toString()) {
			case "ROLE_DOCTOR":
				mav.setViewName("redirect:../doctor/doctorDashboard");
				break;
			}
			
			System.out.println(userDetails.getUserRolesMapping().get(0).getUserRole().getRoleName());
			//
			return mav;

		}

		catch (BadCredentialsException e) {
			e.printStackTrace();
			authenticationResponse.setAuthenticated(false);
			return new ModelAndView("error");
		}

		catch (Exception e) {
			e.printStackTrace();
			authenticationResponse.setAuthenticated(false);
			return new ModelAndView("error");
		}

	}
	@GetMapping(value={"/","/loginForm"})
	public ModelAndView authenticate1(Authentication auth) throws Exception {
		if(auth!=null) {
			return new ModelAndView("redirect:../doctor/home");
		}else {
		return new ModelAndView("/authentication/login");
		}
		

	}
	@GetMapping("/loginError")
	public ModelAndView loginError(Authentication auth) throws Exception {
		ModelAndView mav = new ModelAndView("/authentication/login");
		mav.addObject("errorMsg", "Authentication Error-Please Enter Correct UserName/Password/OTP");
		return mav;

	}
	

}
