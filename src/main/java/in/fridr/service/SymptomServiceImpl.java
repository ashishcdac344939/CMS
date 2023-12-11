package in.fridr.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.SymptomsMaster;
import in.fridr.entity.UserCredential;
import in.fridr.modal.ESAModel;
import in.fridr.repository.BodyCirclePatientESAMappingRepo;
import in.fridr.repository.PatientEdmontonSymptomAssessmentRepo;
import in.fridr.repository.SymptionMasterRepo;
@Service
@Transactional
public class SymptomServiceImpl implements SymptomService {

	@Autowired
	private SymptionMasterRepo symptionMasterRepo;
	@Override
	public SymptomsMaster addSymption(SymptomsMaster symptomsMaster) {	
		return symptionMasterRepo.save(symptomsMaster);
	}

	@Override
	public List<SymptomsMaster> getAllSymptoms() {
		// TODO Auto-generated method stub
		return symptionMasterRepo.findBySymptomStatus(true);
	}

}
