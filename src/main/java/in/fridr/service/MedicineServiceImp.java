package in.fridr.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.fridr.entity.CenterDetails;
import in.fridr.entity.MedicineInventery;
import in.fridr.entity.MedicineMaster;
import in.fridr.entity.PatientMedicinePrescription;
import in.fridr.entity.SymptomMedicineMapping;
import in.fridr.modal.MedicalModel;
import in.fridr.modal.PatientPrescriptionModel;
import in.fridr.modal.PatientPrescriptionResponseModel;
import in.fridr.projection.KeyValueModel;
import in.fridr.projection.MedicineQuantityMapProjection;
import in.fridr.projection.PatientMedicalVisitRecordModel;
import in.fridr.projection.PatientPrescptionProjection;
import in.fridr.repository.MedicineInventeryRepository;
import in.fridr.repository.MedicineMasterRepo;
import in.fridr.repository.PatientMedicalVisitRecordRepository;
import in.fridr.repository.PatientMedicineAllocationRepository;
import in.fridr.repository.PatientPrescriptionRepo;
import in.fridr.repository.SymptomMedicineMappingRepo;
import in.fridr.repository.UserCredentialRepo;

@Service
@Transactional
public class MedicineServiceImp implements MedicineService {

	@Autowired
	private MedicineMasterRepo medicineMasterRepo;

	@Autowired
	SymptomMedicineMappingRepo symptomMedicineMappingRepo;

	@Autowired
	PatientPrescriptionRepo patientPrescriptionRepo;

	@Autowired
	MedicineInventeryRepository inventeryRepository;

	@Autowired
	PatientMedicalVisitRecordRepository patientMedicalVisitRecordRepository;

	@Autowired
	PatientMedicineAllocationRepository patientMedicineAllocationRepository;

	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Override
	public List<MedicineMaster> getMedicineBySymptomId(Integer symptomId) {
		List<MedicineMaster> medicineMasterList = new ArrayList<>();
		// TODO Auto-generated method stub
		List<SymptomMedicineMapping> symptomMedicineMappingList = symptomMedicineMappingRepo
				.findBySymptomsMasterSymptomId(symptomId);
		for (SymptomMedicineMapping data : symptomMedicineMappingList) {
			MedicineMaster medicineMaster = new MedicineMaster(data.getMedicineMaster().getMedicineId(),
					data.getMedicineMaster().getMedicineName(), data.getMedicineMaster().getMedicineType());
			medicineMasterList.add(medicineMaster);
		}
		return medicineMasterList;
	}

	@Override
	public boolean savePatientPrescription(PatientPrescriptionModel patientPrescriptionModel) {
		// TODO Auto-generated method stub
		System.err.println("from service method : "+patientPrescriptionModel.getMedicineAndQuantity());
		try {
			for (int i = 0; i < patientPrescriptionModel.getMedicineAndQuantity().size(); i++) {
				PatientMedicinePrescription patientPrescription = new PatientMedicinePrescription(
						patientPrescriptionModel.getMedicineAndQuantity().get(i).getNoOfDays(),
						patientPrescriptionModel.getPatientDetail(),
						patientPrescriptionModel.getPatientVisitDiagnosisDetail(),
						new MedicineMaster(patientPrescriptionModel.getMedicineAndQuantity().get(i).getMedicineId()),
						patientPrescriptionModel.getMedicineAndQuantity().get(i).getDaysInterval(),
						patientPrescriptionModel.getMedicineAndQuantity().get(i).getMedicineInaDays(),
						patientPrescriptionModel.getMedicineAndQuantity().get(i).getMedicineQuantity(),
						patientPrescriptionModel.getMedicineAndQuantity().get(i).getRemark(),
						new Timestamp(System.currentTimeMillis()));
				patientPrescriptionRepo.save(patientPrescription);

			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ResponseEntity<?> addMedicine(MedicineMaster medicineMaster) {
		int userCredId = medicineMaster.getUserCredId();
		System.err.println(":::::::::::::: " + userCredId);
		int centerId=userCredentialRepo.findCenterIdbyUserCredId(userCredId);
		System.err.println(centerId);
		medicineMaster.setCenterDetails(new CenterDetails(centerId));
		try {
			MedicineMaster mm = medicineMasterRepo.save(medicineMaster);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(mm);
		} catch (Exception e) {
			System.err.println("aaaaaaaaaaaaaaaaaaaa");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error in saving Medicine");
		}

	}

	@Override
	public List<MedicineMaster> getAllMedicine(Integer userCredId) {
		if(userCredId==null)
			return medicineMasterRepo.findAll();
		else{
			int centerId=userCredentialRepo.findCenterIdbyUserCredId(userCredId);
			return medicineMasterRepo.getMedicineByCenter(centerId);
		}
		
	}

	@Override
	public List<SymptomMedicineMapping> getAllSymptomMedicineMapping() {

		return symptomMedicineMappingRepo.findAll();
	}

	@Override
	public int getMedicineCountByMedicineId(int medicineId) {
		return medicineMasterRepo.findById(medicineId).get().getCurrentAvailableCount();
	}

	@Override
	public MedicineMaster updateInventery(MedicineMaster medicineMaster) {
		MedicineMaster medicineMasterFromDB = medicineMasterRepo.findById(medicineMaster.getMedicineId()).get();
		// insert into medicineInventry
		medicineMasterFromDB.setCurrentAvailableCount(
				medicineMasterFromDB.getCurrentAvailableCount() + medicineMaster.getCurrentAvailableCount());
		// update count of medicine in medicine master
		inventeryRepository.save(new MedicineInventery(medicineMaster.getCurrentAvailableCount(),
				new Timestamp(System.currentTimeMillis()), medicineMasterFromDB));
		return medicineMasterRepo.save(medicineMasterFromDB);
	}

	@Override
	public List<PatientPrescriptionResponseModel> getPatientPrescption(int patientVisitDetailsId) {
		
		List<PatientPrescptionProjection> medicineData = patientPrescriptionRepo
				.getPatientPrescptioon(patientVisitDetailsId);
		List<PatientPrescriptionResponseModel> response = new ArrayList<>();
		if (medicineData.size() != 0) {
			List<PatientPrescptionProjection> docData = userCredentialRepo
					.getUserByUSerCredId(medicineData.get(0).getDocId());
			for (PatientPrescptionProjection p : medicineData) {
				response.add(new in.fridr.modal.PatientPrescriptionResponseModel(p.getDocId(), p.getAdharno(),
						p.getAddress(), p.getName(), p.getMedicineName(), p.getMedicineQuantity(), p.getDob(),
						p.getNoOfDays(), p.getdaysInterval(), p.getmedicineInADays(), p.getIs3ERequired(), p.getPmpId(),
						p.getRemark(), docData.get(0).getDocRegNo(), docData.get(0).getDocName(),p.getPvId()));
			}
		}
		return response;
	}

	@Override
	public ResponseEntity<?> getPatientMedicineRecord(int pvid) {

		MedicalModel mm = new MedicalModel();

		List<PatientPrescptionProjection> ppp = patientPrescriptionRepo.getPatientPrescptioon(pvid);
		System.err.println("1.  ppp: " + ppp.size());
		mm.setPrescription(ppp);
		if (ppp.size() == 0) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Record not found");
		}
		List<PatientMedicalVisitRecordModel> pmvr = patientMedicalVisitRecordRepository.findByPvId(pvid);
		mm.setMedicalVisitRecords(pmvr);
		System.err.println("2. pmvr: " + pmvr.size());

		List<KeyValueModel> kvm = patientMedicineAllocationRepository.getMedicineAllocationCount(pvid);
		System.err.println("3 kvm: " + kvm.size());
		mm.setPmpAndAllocatedMedicineCount(kvm);
		return ResponseEntity.ok(mm);
	}

	@Override
	public List<MedicineQuantityMapProjection> getMedicineQuantitiesForUserWith3EFormRequired(int docId,
			String barInitialDate, String barTillDate) {
		// TODO Auto-generated method stub
		return medicineMasterRepo.getMedicineQuantitiesForUserWith3EFormRequired(docId, barInitialDate, barTillDate);
	}

}
