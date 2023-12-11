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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="symptom_medicine_mapping")
public class SymptomMedicineMapping {

	@Id
	@SequenceGenerator(name = "symptoms_medicine_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="symptom_medicine_mapping_id")
	private int symptomMedicineMappingId;
	
	@ManyToOne
	@JoinColumn(name="medicine_id")
	private MedicineMaster medicineMaster;
	
	@ManyToOne
	@JoinColumn(name="symptom_id")
	private SymptomsMaster symptomsMaster;
	
	@Column(name="recommanded_on_severity")
	private boolean recommandedOnSeverity;
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	

	
	
	public SymptomMedicineMapping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSymptomMedicineMappingId() {
		return symptomMedicineMappingId;
	}
	public void setSymptomMedicineMappingId(int symptomMedicineMappingId) {
		this.symptomMedicineMappingId = symptomMedicineMappingId;
	}
	public MedicineMaster getMedicineMaster() {
		return medicineMaster;
	}
	public void setMedicineMaster(MedicineMaster medicineMaster) {
		this.medicineMaster = medicineMaster;
	}
	public SymptomsMaster getSymptomsMaster() {
		return symptomsMaster;
	}
	public void setSymptomsMaster(SymptomsMaster symptomsMaster) {
		this.symptomsMaster = symptomsMaster;
	}
	public boolean isRecommandedOnSeverity() {
		return recommandedOnSeverity;
	}
	public void setRecommandedOnSeverity(boolean recommandedOnSeverity) {
		this.recommandedOnSeverity = recommandedOnSeverity;
	}
	public Timestamp getRecordTracking() {
		return recordTracking;
	}
	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}
	
	public SymptomMedicineMapping(MedicineMaster medicineMaster) {
		super();
		this.medicineMaster = medicineMaster;
	}
	
	
	
}
