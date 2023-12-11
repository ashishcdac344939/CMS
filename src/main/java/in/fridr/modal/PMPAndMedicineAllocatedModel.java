package in.fridr.modal;

public class PMPAndMedicineAllocatedModel {
   int patientMedicinePrescriptionId;
   int allocatedMedicine;
   
public PMPAndMedicineAllocatedModel(int patientMedicinePrescription, int allocatedMedicine) {
	super();
	this.patientMedicinePrescriptionId = patientMedicinePrescription;
	this.allocatedMedicine = allocatedMedicine;
}
public PMPAndMedicineAllocatedModel() {
	super();
	// TODO Auto-generated constructor stub
}
public int getPatientMedicinePrescriptionId() {
	return patientMedicinePrescriptionId;
}
public void setPatientMedicinePrescriptionId(int patientMedicinePrescriptionId) {
	this.patientMedicinePrescriptionId = patientMedicinePrescriptionId;
}
public int getAllocatedMedicine() {
	return allocatedMedicine;
}
public void setAllocatedMedicine(int allocatedMedicine) {
	this.allocatedMedicine = allocatedMedicine;
}

   
}
