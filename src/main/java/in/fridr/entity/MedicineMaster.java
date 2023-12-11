package in.fridr.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="medicine_master")
@NamedQuery(name="MedicineMaster.findAll", query="SELECT m FROM MedicineMaster m")
public class MedicineMaster {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "medicine_master_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="medicine_id")
	private Integer medicineId;
	
	@Column(name="medicine_name")
	private String medicineName;
	
	@Column(name="medicine_type")
	private String medicineType;
	
	@Column(name="is_3e_form_required")
	private boolean is3ERequired;
	
	
	@Column(name="current_available_count")
	private int currentAvailableCount;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@JsonIgnore
	@OneToMany(mappedBy = "medicineMaster")
	private List<SymptomMedicineMapping> symptomMedicineMappings;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "center_details_id")
	private CenterDetails centerDetails;

	@JsonIgnore
	@OneToMany(mappedBy = "medicineMaster")
	private List<PatientMedicinePrescription> patientMedicinePrescription;
	
	@Transient
	private int userCredId;
	
	public MedicineMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MedicineMaster(Integer medicineId) {
		super();
		this.medicineId = medicineId;
	}

	public MedicineMaster(Integer medicineId, String medicineName, String medicineType) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineType = medicineType;
	}
	
	
	public CenterDetails getCenterDetails() {
		return centerDetails;
	}

	public void setCenterDetails(CenterDetails centerDetails) {
		this.centerDetails = centerDetails;
	}

	public int getUserCredId() {
		return userCredId;
	}

	public void setUserCredId(int userCredId) {
		this.userCredId = userCredId;
	}

	public Integer getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicineType() {
		return medicineType;
	}
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	public Timestamp getRecordTracking() {
		return recordTracking;
	}
	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}
	public List<SymptomMedicineMapping> getSymptomMedicineMappings() {
		return symptomMedicineMappings;
	}
	public void setSymptomMedicineMappings(List<SymptomMedicineMapping> symptomMedicineMappings) {
		this.symptomMedicineMappings = symptomMedicineMappings;
	}

	public boolean isIs3ERequired() {
		return is3ERequired;
	}

	public void setIs3ERequired(boolean is3eRequired) {
		is3ERequired = is3eRequired;
	}

	public int getCurrentAvailableCount() {
		return currentAvailableCount;
	}

	public void setCurrentAvailableCount(int currentAvailableCount) {
		this.currentAvailableCount = currentAvailableCount;
	}

	

	
	
	
}
