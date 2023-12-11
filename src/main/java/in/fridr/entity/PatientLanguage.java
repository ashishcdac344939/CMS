package in.fridr.entity;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="patient_language")
public class PatientLanguage {

	@Id
	@SequenceGenerator(name = "patient_language_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="patient_language_id")
	private Integer patientLanguageId;
	
	@Column(name="language_name")
	private String languageName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_details_id")
	private PatientDetail patientDetail;

	
	public PatientLanguage(String languageName, PatientDetail pd) {
		super();
		this.languageName = languageName;
		this.patientDetail=pd;
	}

	public PatientLanguage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<PatientLanguage> getPatientLanguageList(List<String> patientLanguage,PatientDetail pd){
	  return 	patientLanguage.stream().map(e->new PatientLanguage(e,pd)).collect(Collectors.toList());
	}
	
	public PatientLanguage(Integer patientLanguageId, String languageName, PatientDetail patientDetail) {
		super();
		this.patientLanguageId = patientLanguageId;
		this.languageName = languageName;
		this.patientDetail = patientDetail;
	}

	public Integer getPatientLanguageId() {
		return patientLanguageId;
	}

	public void setPatientLanguageId(Integer patientLanguageId) {
		this.patientLanguageId = patientLanguageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	public PatientDetail getPatientDetail() {
		return patientDetail;
	}

	public void setPatientDetail(PatientDetail patientDetail) {
		this.patientDetail = patientDetail;
	}
	
	
	

}
