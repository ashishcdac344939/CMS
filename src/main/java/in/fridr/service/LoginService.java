package in.fridr.service;
import java.util.List;

import javax.validation.Valid;

import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.UserCredential;

public interface LoginService {
	boolean authenticate(String email, String otp);

	boolean saveToLogin(String email);

	Boolean getUserByEmail(String email);

	UserCredential findByEmail(String email);

	boolean saveOtp(UserCredential requesedUserCredential);

	boolean updateCredentialsDetails(UserCredential userCredential);
	

	
}
