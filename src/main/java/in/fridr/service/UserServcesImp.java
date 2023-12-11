package in.fridr.service;
import java.util.ArrayList;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.fridr.entity.UserCredential;
import in.fridr.entity.UserDetail;
import in.fridr.repository.UserCredentialRepo;
import in.fridr.repository.UserDetailsRepo;
import in.fridr.shared.userDTO;


@Service
@Transactional
public class UserServcesImp implements UserServices { 
	@Autowired
	private LoginService loginService;

	@Autowired
	private UserDetailsRepo userDetailsRepo;
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	public UserServcesImp() {
	
	}



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserCredential userEntity=loginService.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException(email);
		}
		if(userEntity.isLoginWithPassword())
		return new User(userEntity.getUserId(),userEntity.getPasswrord(),true,true,true,true,new ArrayList<>());
		else {
			return new User(userEntity.getUserId(),userEntity.getOtp(),true,true,true,true,new ArrayList<>());
		}
	}

	@Override
	public userDTO getUserDetailsByEmail(String email) {
		// TODO Auto-generated method stub
		UserCredential userEntity=loginService.findByEmail(email);
		if(userEntity==null) {
			throw new UsernameNotFoundException(email);
		}
		return new ModelMapper().map(userEntity, userDTO.class) ;
	}



	@Override
	public UserDetail getUserDetailbyUserDetailsId(int userDetailsId) {
		UserDetail ud = userDetailsRepo.findById(userDetailsId).get();
		return ud;
	}



	  public void updateResetPasswordToken(String token, String email){
		  UserCredential userCredential = userCredentialRepo.findByUserUserId(email);
	        if (userCredential != null) {
	        	userCredential.setResetPasswordToken(token);
	        	userCredentialRepo.save(userCredential);
	        } else {
	            throw new UsernameNotFoundException("Could not find any customer with the email " + email);
	        }
	    }



	@Override
	public UserCredential getByResetPasswordToken(String token) {
		// TODO Auto-generated method stub
		UserCredential userCredential = userCredentialRepo.findByResetPasswordToken(token);
		return userCredential;
	}



	@Override
	public void updatePassword(UserCredential userCredential, String password) {
		// TODO Auto-generated method stub
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(password);
	        userCredential.setPasswrord(encodedPassword);
	         
	        userCredential.setResetPasswordToken(null);
	        userCredentialRepo.save(userCredential);
		
	}

}
