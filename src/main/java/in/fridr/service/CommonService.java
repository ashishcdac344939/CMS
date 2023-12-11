package in.fridr.service;

import java.util.List;

import in.fridr.entity.MedicineInventery;
import in.fridr.entity.UserCredential;
import in.fridr.modal.MedicineAllocationModel;
import in.fridr.modal.UserModel;
import in.fridr.projection.MedicineNameAndQuantityProjection;

public interface CommonService {

	public UserCredential registerUser(UserModel model); 
	
	public List<MedicineInventery> getAllMedicineInventry();
	
	public List<MedicineNameAndQuantityProjection> getMedicineAllocatedInOneMedicalVisit(int pmvr);

	public void saveMedicineAllocated(MedicineAllocationModel medicineAllocationModel);
}
