package in.fridr.shared;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.fridr.entity.UserCredential;

public class MyUserDetails implements UserDetails{

	private UserCredential userEntity;
	
	
	public MyUserDetails() {
		
	}

	
	
	
	public MyUserDetails(UserCredential userEntity) {
		this.userEntity = userEntity;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		//System.out.println("kkkkkkkkkkkkkkkkkkkk:"+userEntity.isLoginWithPassword());
		// TODO Auto-generated method stub
		if(userEntity.isLoginWithPassword())
		return userEntity.getPasswrord();
		else {
			return userEntity.getOtp();
		}
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEntity.getUserId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
