package in.fridr.service;

import java.util.List;

import in.fridr.entity.BodyCirclePatientESAMapping;
import in.fridr.entity.PatientEdmontonSymptomAssessment;
import in.fridr.entity.SymptomsMaster;
import in.fridr.modal.ESAModel;

public interface SymptomService {

	SymptomsMaster addSymption(SymptomsMaster symptomsMaster);

	List<SymptomsMaster> getAllSymptoms();
}
