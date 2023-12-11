package in.fridr.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.modal.MessageResponse;
import in.fridr.modal.UserModel;
import in.fridr.service.LoginService;
import in.fridr.service.UserServices;
import in.fridr.sms.MailUtility;
import in.fridr.util.Utility;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/password")
public class PasswordController { 
	
	@Autowired
	private UserServices userServices;
	@Autowired
	private LoginService loginService;
	 @GetMapping("/forgot_password")
	    public ModelAndView showForgotPasswordForm() {
	    	ModelAndView mv = new ModelAndView("/pages/forgotPasswordForm");
	    	return mv;
	    }
	 
	    @PostMapping("/forgot_password")
	    public ResponseEntity<?> processForgotPassword(HttpServletRequest request, Model model) {
	    	//ModelAndView mv =new ModelAndView("/pages/forgotPasswordForm");
	        String email = request.getParameter("email");
	        System.err.println("JJJJJJJJJJJJJJJJJJJJJJJJJ"+email);
	        try {
	        	String tokenRan = RandomString.make(30);
	        	userServices.updateResetPasswordToken(tokenRan, email);
	            String resetPasswordLink = null;
				resetPasswordLink = Utility.getSiteURL(request) + "/password/reset_password?token=" + tokenRan;
				
				 
				MailUtility mu = new MailUtility();
				System.err.println("Reset Password Requested"+resetPasswordLink);
				 // Construct the HTML content as a string
	            String htmlContent = "<!DOCTYPE html>\n" +
	                    "<html>\n" +
	                    "<head>\n" +
	                    "    <title>Password Reset</title>\n" +
	                    "</head>\n" +
	                    "<body>\n" +
	                    "    <table>\n" +
	                    "        <tr>\n" +
	                    "            <td>\n" +
	                    "                <h1>Password Reset Request</h1>\n" +
	                    "                <p>We have received a request to reset your password. To reset your password and regain access to your account, please click the link below:</p>\n" +
	                    "                <p><a href=\"" + resetPasswordLink + "\">Reset Your Password</a></p>\n" +
	                    "                <p>If you did not request a password reset, please ignore this email. Your account's security is important to us, and no changes will be made to your password unless you initiate the process.</p>\n" +
	                    "                <p>If you have any questions or need further assistance, please contact our support team at [cms@gmail.com].</p>\n" +
	        
	                    "            </td>\n" +
	                    "        </tr>\n" +
	                    "    </table>\n" +
	                    "</body>\n" +
	                    "</html>";
	            mu.sendMail(email, htmlContent);
	            return ResponseEntity.ok().body("We have sent a reset password link to your email. Please check");  
	        } catch (Exception ex) {
	        	 return ResponseEntity.ok().body(ex.getMessage());
	        }
	             
	       
	    }
	     
	    public void sendEmail(){
	 
	    }  
	     
	     
	    @GetMapping("/reset_password")
	    public ModelAndView showResetPasswordForm(@Param(value = "token") String token, Model model) {
	      UserCredential userCredential = userServices.getByResetPasswordToken(token);
	      ModelAndView mv =new ModelAndView("/pages/resetPasswordForm");
	        model.addAttribute("token", token);
	         
	        if (userCredential == null) {
	            model.addAttribute("message", "Invalid Token");
	            return mv;
	        }
	         
	        return mv;
	    }
	     
	    @PostMapping("/reset_password")
	    public ResponseEntity<?> processResetPassword(@RequestBody UserModel userModel) {
	        String token = userModel.getToken();
	        String password = userModel.getNewPwd();
	       // ModelAndView mv = new ModelAndView("/pages/resetPasswordMessagePage");
	        System.err.println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGG"+token+" "+password);
	        UserCredential userCredential = userServices.getByResetPasswordToken(token);
	        //model.addAttribute("title", "Reset your password");
	         
	        if (userCredential == null) {
	           return ResponseEntity.ok().body("Invalid Request Please try again");
	        } else {           
	            userServices.updatePassword(userCredential, password);
	             
	            return ResponseEntity.ok().body("You have successfully changed your password.");
	        }
	         
	       
	    }
	    @GetMapping("/changePassword")
	    public ModelAndView showchangePasswordForm() {
	    	ModelAndView mv = new ModelAndView("/pages/changePassword");
	    	return mv;
	    }
	    @PostMapping("/changePassword")
	    public ResponseEntity<MessageResponse> processingChangePasswordForm(Authentication auth,@RequestBody UserModel userModel) {
	    	MessageResponse m =new MessageResponse();
	    	try {
	    		// match password with existing one
	    		
	    		UserCredential uc = loginService.findByEmail(auth.getName());
	    		BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
	    		if(bc.matches(userModel.getOldPwd(), uc.getPasswrord())) {
	    			uc.setPasswrord(bc.encode(userModel.getNewPwd()));
	    			loginService.saveOtp(uc);
	    			m.setMessage("Password has been changed");
	    			m.setStatus(true);
	    			return new ResponseEntity<MessageResponse>(m,HttpStatus.OK);
	    		}else {
	    			m.setMessage("Old password is not mathced with present password");
	    			m.setStatus(false);
	    			return new ResponseEntity<MessageResponse>(m,HttpStatus.OK);
	    		}
	    	}catch(Exception e) {
	    		m.setMessage("Something went wrong");
	    		m.setStatus(false);
		    	 return new ResponseEntity<MessageResponse>(m,HttpStatus.INTERNAL_SERVER_ERROR);
	    	}
	    	
	    }
}
