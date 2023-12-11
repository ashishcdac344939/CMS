package in.fridr.entity;

import java.util.List;
import java.util.stream.Collectors;

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
@Table(name="treatment_received")
public class TreatmentRecevied {

	@Id
	@SequenceGenerator(name = "treatment_received_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="treatment_received_id")
	private Integer treatmentReceivedId;
	
	@Column(name="center_name")
	private String treatmentName;
	
	@ManyToOne
	@JoinColumn(name = "patient_details_id")
	private PatientDetail patientDetail;

	public TreatmentRecevied(Integer treatmentReceivedId, String treatmentName, PatientDetail patientDetail) {
		super();
		this.treatmentReceivedId = treatmentReceivedId;
		this.treatmentName = treatmentName;
		this.patientDetail = patientDetail;
	}

	
	
	public TreatmentRecevied(String treatmentName,PatientDetail pd) {
		super();
		this.treatmentName = treatmentName;
		this.patientDetail=pd;
	}



	public static List<TreatmentRecevied> getTreatmentRecevied(List<String> treatmentRecevied,PatientDetail pd){
		  return 	treatmentRecevied.stream().map(e->new TreatmentRecevied(e,pd)).collect(Collectors.toList());
		}
	
	public TreatmentRecevied() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getTreatmentReceivedId() {
		return treatmentReceivedId;
	}

	public void setTreatmentReceivedId(Integer treatmentReceivedId) {
		this.treatmentReceivedId = treatmentReceivedId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public PatientDetail getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}
	
	
}
