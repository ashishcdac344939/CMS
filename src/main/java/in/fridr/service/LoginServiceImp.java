package in.fridr.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.fridr.entity.UserCredential;
import in.fridr.repository.LoginRepository;

@Service
@Transactional
public class LoginServiceImp implements LoginService {



	@Autowired
	private LoginRepository loginRepository;

	@Override
	public boolean saveOtp(UserCredential userCredential) {
		
		//return loginDao.saveOtp(email,otp);
		try {
			UserCredential savedUser = loginRepository.save(userCredential);
	 	return true;
		}catch(Exception e) {
		return false;
		}
	}

	@Override
	public boolean authenticate(String email, String otp) {
		// TODO Auto-generated method stub
		UserCredential userCredential = loginRepository.findByUserIdAndOtp(email,otp);
		if(userCredential!=null) {
			return true;
		}
		return false;
	}



	/*
	 * @Override public Login_temp findBymobile_number(String email) { // TODO
	 * Auto-generated method stub return loginRepository.findByEmail(email); }
	 */
	

	@Override
	public UserCredential findByEmail(String email) {
		// TODO Auto-generated method stub
		return loginRepository.findByUserId(email);
	}

	@Override
	public boolean saveToLogin(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCredentialsDetails(UserCredential userCredential) {
		//return loginDao.saveOtp(email,otp);
		System.out.println(userCredential.isLoginWithPassword());
				try {
					UserCredential savedUser = loginRepository.save(userCredential);
			 	return true;
				}catch(Exception e) {
				return false;
				}
	}



}
