package in.fridr.modal;

import java.sql.Date;

public class PatientPrescriptionResponseModel {

	public int docId;
	public String adharno;
	public String address;
	public String name;
	public String symptom;
	public String medicineName;
	public String medicineQuantity;
	public Date dob;
	public int noOfDays;
	public int daysInterval;
	public int medicineInADays;
	public boolean is3ERequired;
	public int currentAvailableCount;
	public int pvId;
	public int pmpId;
	public String remark;
	public String docName;
	public String docRegNo;
	
	
	
	
	public PatientPrescriptionResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public PatientPrescriptionResponseModel(int docId, String adharno, String address, String name, String medicineName,
			String medicineQuantity, Date dob, int noOfDays, int daysInterval, int medicineInADays,
			boolean is3eRequired, int pmpId, String remark,String docRegNo,String docName,int pvid) {
		super();
		this.docId = docId;
		this.adharno = adharno;
		this.address = address;
		this.name = name;
		this.medicineName = medicineName;
		this.medicineQuantity = medicineQuantity;
		this.dob = dob;
		this.noOfDays = noOfDays;
		this.daysInterval = daysInterval;
		this.medicineInADays = medicineInADays;
		is3ERequired = is3eRequired;
		this.pmpId = pmpId;
		this.remark = remark;
		this.docRegNo=docRegNo;
		this.docName=docName;
		this.pvId=pvid;
	}
	
	
	
}
