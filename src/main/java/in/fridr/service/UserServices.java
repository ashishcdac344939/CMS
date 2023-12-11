package in.fridr.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.shared.userDTO;



public interface UserServices extends UserDetailsService{
	userDTO getUserDetailsByEmail(String email); 
	
	UserDetail getUserDetailbyUserDetailsId(int userDetailsId);

	void updateResetPasswordToken(String token, String email);

	UserCredential getByResetPasswordToken(String token);

	void updatePassword(UserCredential userCredential, String password);

}
