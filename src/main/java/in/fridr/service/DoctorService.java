package in.fridr.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import in.fridr.entity.PatientDetail;
import in.fridr.modal.UserModel;

public interface DoctorService {
	List<Object[]> getNextVisistByDocId(Integer integer, String formattedDate);

	
		
	
}
