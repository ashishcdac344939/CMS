package in.fridr.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "patient_medicine_prescription")
public class PatientMedicinePrescription {
	@Id
	@SequenceGenerator(name = "patient_medicine_prescription_sequence_generator", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patient_medicine_prescription_id")
	private Integer id;

	// for how many days the medicine is allocated
	@Column(name = "no_of_days")
	private int noOfDays;

	@Column(name = "days_interval")
	private int daysInterval;
	
	@Column(name = "medicine_in_a_day")
	private int medicineInaDays;
	
	@Column(name = "medicine_quantity")
	private int medicineQuantity;

	@Column(name = "medicine_remark")
	private String remark;
	
	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	@ManyToOne
	@JoinColumn(name = "pdId")
	private PatientDetail patientDetail;

	@ManyToOne
	@JoinColumn(name = "pvId")
	private PatientVisitDiagnosisDetail patientVisitDiagnosisDetail;

	@ManyToOne
	@JoinColumn(name = "medicine_master_id")
	private MedicineMaster medicineMaster;

	public PatientMedicinePrescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public PatientMedicinePrescription(Integer id) {
		super();
		this.id = id;
	}


	public PatientMedicinePrescription(int noOfDays,PatientDetail patientDetail,
			PatientVisitDiagnosisDetail patientVisitDiagnosisDetail,MedicineMaster medicineMaster,
			int daysInterval,int medicineInaDays,int medicineQuantity,String remark,Timestamp recordTracking) {
		super();
		this.noOfDays=noOfDays;
		this.daysInterval=daysInterval;
		this.medicineInaDays=medicineInaDays;
		this.medicineQuantity = medicineQuantity;
		this.recordTracking=recordTracking;
		this.patientDetail = patientDetail;
		this.medicineMaster = medicineMaster;
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
		this.remark=remark;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	

	public PatientDetail getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}

	public PatientVisitDiagnosisDetail getPatientVisitDiagnosisDetail() {
		return patientVisitDiagnosisDetail;
	}

	public void setPatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		this.patientVisitDiagnosisDetail = patientVisitDiagnosisDetail;
	}

	

	public int getDaysInterval() {
		return daysInterval;
	}


	public void setDaysInterval(int daysInterval) {
		this.daysInterval = daysInterval;
	}


	public int getMedicineInaDays() {
		return medicineInaDays;
	}


	public void setMedicineInaDays(int medicineInaDays) {
		this.medicineInaDays = medicineInaDays;
	}


	public MedicineMaster getMedicineMaster() {
		return medicineMaster;
	}


	public void setMedicineMaster(MedicineMaster medicineMaster) {
		this.medicineMaster = medicineMaster;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}

	public int getMedicineQuantity() {
		return medicineQuantity;
	}

	public void setMedicineQuantity(int medicineQuantity) {
		this.medicineQuantity = medicineQuantity;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
