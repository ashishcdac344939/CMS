package in.fridr.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="medicine_inventery")
public class MedicineInventery  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "medicine_inventery_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="mi_id")
	private Integer miId;
	
	@Column(name="medicine_count")
	private int medicineCount;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	@ManyToOne()
	@JoinColumn(name="medicine_id")
	private MedicineMaster medicineMaster;

	public MedicineInventery(int medicineCount, Timestamp recordTracking, MedicineMaster medicineMaster) {
		super();
		this.medicineCount = medicineCount;
		this.recordTracking = recordTracking;
		this.medicineMaster = medicineMaster;
	}

	public MedicineInventery() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getMiId() {
		return miId;
	}

	public void setMiId(Integer miId) {
		this.miId = miId;
	}

	public int getMedicineCount() {
		return medicineCount;
	}

	public void setMedicineCount(int medicineCount) {
		this.medicineCount = medicineCount;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public MedicineMaster getMedicineMaster() {
		return medicineMaster;
	}

	public void setMedicineMaster(MedicineMaster medicineMaster) {
		this.medicineMaster = medicineMaster;
	}
	
	
	
	
}
