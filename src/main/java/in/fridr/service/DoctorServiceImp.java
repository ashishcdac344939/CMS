package in.fridr.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import in.fridr.entity.PatientDetail;
import in.fridr.entity.UserDetail;
import in.fridr.entity.UserRole;
import in.fridr.entity.UserRolesMapping;
import in.fridr.modal.UserModel;
import in.fridr.repository.PatientVisitDiagnosisDetailRepo;
import in.fridr.repository.UserRepository;
import in.fridr.repository.UserRoleMappingRepo;

@Service
@Transactional
public class DoctorServiceImp implements DoctorService {
	@Autowired
	public PatientVisitDiagnosisDetailRepo patientVisitDiagnosisDetailRepo;

	@Override
	public List<Object[]> getNextVisistByDocId(Integer docId,String formattedDate) {
		// TODO Auto-generated method stub
		return patientVisitDiagnosisDetailRepo.getNextVisistByDocId(docId,formattedDate);
	}

}
