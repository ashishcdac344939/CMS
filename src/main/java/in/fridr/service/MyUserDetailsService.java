package in.fridr.service;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.fridr.entity.UserCredential;
import in.fridr.shared.MyUserDetails;
import javassist.NotFoundException;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private LoginService loginService;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		UserCredential userEntity=loginService.findByEmail(email);
		
		if(userEntity==null) {
			throw new UsernameNotFoundException("Mobile number is not registered");
		
		}
		
		return new MyUserDetails(userEntity);
		
	}
	

}
