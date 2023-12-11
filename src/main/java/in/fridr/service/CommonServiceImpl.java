package in.fridr.service;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.fridr.entity.MedicineInventery;
import in.fridr.entity.MedicineMaster;
import in.fridr.entity.PatientMedicalVisitRecord;
import in.fridr.entity.PatientMedicineAllocationMapping;
import in.fridr.entity.PatientMedicinePrescription;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.UserCredential;
import in.fridr.entity.UserRole;
import in.fridr.entity.UserRolesMapping;
import in.fridr.modal.MedicineAllocationModel;
import in.fridr.modal.PMPAndMedicineAllocatedModel;
import in.fridr.modal.UserModel;
import in.fridr.projection.MedicineNameAndQuantityProjection;
import in.fridr.repository.MedicineInventeryRepository;
import in.fridr.repository.MedicineMasterRepo;
import in.fridr.repository.PatientMedicalVisitRecordRepository;
import in.fridr.repository.PatientMedicineAllocationRepository;
import in.fridr.repository.PatientMedicinePrescriptionRepo;
import in.fridr.repository.UserCredentialRepo;
import in.fridr.repository.UserRepository;
import in.fridr.repository.UserRoleMappingRepo;

@Transactional
@Service
public class CommonServiceImpl implements CommonService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Autowired
	private PatientMedicalVisitRecordRepository patientMedicalVisitRecordRepository;
	
	@Autowired
	private PatientMedicineAllocationRepository patientMedicineAllocationRepository;

	@Autowired
	private UserRoleMappingRepo userRoleMappingRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private MedicineInventeryRepository medicineInventeryRepository;
	
	@Autowired
	private MedicineMasterRepo medicineMasterRepo;
	
	@Autowired
	private PatientMedicinePrescriptionRepo patientMedicinePrescriptionRepo;

	@Override
	public UserCredential registerUser(UserModel model) {
		try {
			model.setPasswrord(passwordEncoder.encode(model.getPasswrord()));
			UserCredential userCredential = userCredentialRepo.save(model.getUserCredentialToSave());
			UserRolesMapping userRolesMapping = userRoleMappingRepo.save(
					new UserRolesMapping(userCredential, new UserRole( model.getUserRoleId())));
			return userCredential;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<MedicineInventery> getAllMedicineInventry() {
		
		return medicineInventeryRepository.findAll();
	}

	@Override
	public List<MedicineNameAndQuantityProjection> getMedicineAllocatedInOneMedicalVisit(int pmvr) {
		return patientMedicineAllocationRepository.getMedicineNameAndQuantity(pmvr);
	}

	@Override
	public void saveMedicineAllocated(MedicineAllocationModel medicineAllocationModel) {
		// save patient medical visit record
		PatientMedicalVisitRecord pmvrsaved=patientMedicalVisitRecordRepository.save(new PatientMedicalVisitRecord(new UserCredential(medicineAllocationModel.getPharmasistCredId()),
				new PatientVisitDiagnosisDetail(medicineAllocationModel.getPvddId()),
				new Timestamp(System.currentTimeMillis())));
		
		// save medicine 
		List<PMPAndMedicineAllocatedModel> pmplist=	 medicineAllocationModel.getPmpANDCount();
		for(PMPAndMedicineAllocatedModel p:pmplist) {
			System.err.println(p.getPatientMedicinePrescriptionId()+" "+p.getAllocatedMedicine()+" mmmmmmmmmmmmm");
             System.err.println("KKKKKKKKKK: "+p.getPatientMedicinePrescriptionId());
			patientMedicineAllocationRepository.save(new PatientMedicineAllocationMapping(pmvrsaved, new PatientMedicinePrescription(p.getPatientMedicinePrescriptionId()), p.getAllocatedMedicine()));
			
			// update inventry by reducing current available minus allocated medicine
			int mmId=patientMedicinePrescriptionRepo.getMedicineMasterByByPatientMedicinePrescription(p.getPatientMedicinePrescriptionId());
			MedicineMaster savedMedicineMaster=medicineMasterRepo.findById(mmId).get();
			if(savedMedicineMaster.getCurrentAvailableCount()<p.getAllocatedMedicine()) {
				throw new RuntimeException();
			}
			savedMedicineMaster.setCurrentAvailableCount(savedMedicineMaster.getCurrentAvailableCount()-p.getAllocatedMedicine());
			medicineMasterRepo.save(savedMedicineMaster);
		}
		
	}
}
