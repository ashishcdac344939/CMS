package in.fridr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="body_circle_pain_esa_mapping")
@NamedQuery(name="BodyCirclePatientESAMapping.findAll", query="SELECT b FROM BodyCirclePatientESAMapping b")
public class BodyCirclePatientESAMapping {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_body_circle_ESA_assessments_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String x;

	private String y;

	//bi-directional many-to-one association to PatientPainAssessment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pain_esa_assesment_id")
	private PatientEdmontonSymptomAssessment patientEdmontonSymptomAssessment;

	public BodyCirclePatientESAMapping() {
	}

	public BodyCirclePatientESAMapping(String x2, String y2, PatientEdmontonSymptomAssessment patientEdmontonSymptomAssessment) {
		// TODO Auto-generated constructor stub
		this.x = x2;
		this.y = y2;
		this.patientEdmontonSymptomAssessment = patientEdmontonSymptomAssessment;
				
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id; 
	}

	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return this.y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public PatientEdmontonSymptomAssessment getPatientEdmontonSymptomAssessment() {
		return patientEdmontonSymptomAssessment;
	}

	public void setPatientEdmontonSymptomAssessment(PatientEdmontonSymptomAssessment patientEdmontonSymptomAssessment) {
		this.patientEdmontonSymptomAssessment = patientEdmontonSymptomAssessment;
	}

}
