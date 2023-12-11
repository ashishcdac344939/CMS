package in.fridr.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import in.fridr.entity.UserCredential;
import in.fridr.service.LoginService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private LoginService loginService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication userAuthentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		System.err.println("GGGGGGGGGGGGGGGGGGGGGGGGG"+username+" "+authentication.getCredentials().toString());
		String credentials = authentication.getCredentials().toString().substring(1);

		String authenticationFlag = authentication.getCredentials().toString().substring(0, 1);
		// String password = authentication.getCredentials().toString();
		UserCredential getUserDetails = loginService.findByEmail(username);

		// Replace this with your actual user authentication logic.

		if (authenticationFlag.equals("p")) {
			//getUserDetails.setLoginWithPassword(true);
			if (getUserDetails != null && passwordEncoder.matches(credentials, getUserDetails.getPasswrord())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(getUserDetails.getUserRolesMapping().get(0).getUserRole().getRoleName()));
				loginService.updateCredentialsDetails(getUserDetails);
				UserDetails userDetails = new User(username, credentials, authorities);
				return new UsernamePasswordAuthenticationToken(userDetails, credentials, authorities);
			}
			

		} else {
			//getUserDetails.setLoginWithPassword(false);
			if (getUserDetails != null && passwordEncoder.matches(credentials, getUserDetails.getOtp())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(getUserDetails.getUserRolesMapping().get(0).getUserRole().getRoleName()));
				loginService.updateCredentialsDetails(getUserDetails);
				getUserDetails.setOtp(null);
				loginService.saveOtp(getUserDetails);
				UserDetails userDetails = new User(username, credentials, authorities);
				
				return new UsernamePasswordAuthenticationToken(userDetails, credentials, authorities);
				
			}
			
		}
		return userAuthentication;

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
