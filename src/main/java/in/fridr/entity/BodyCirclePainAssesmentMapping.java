package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the body_circle_pain_assesment_mapping database table.
 * 
 */
@Entity
@Table(name="body_circle_pain_assesment_mapping")
@NamedQuery(name="BodyCirclePainAssesmentMapping.findAll", query="SELECT b FROM BodyCirclePainAssesmentMapping b")
public class BodyCirclePainAssesmentMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_body_circle_assessments_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String x;

	private String y;

	//bi-directional many-to-one association to PatientPainAssessment
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="pain_assesment_id")
	private PatientPainAssessments patientPainAssessment;

	public BodyCirclePainAssesmentMapping() {
	}

	public BodyCirclePainAssesmentMapping(String x2, String y2, PatientPainAssessments patientPainAssessments) {
		// TODO Auto-generated constructor stub
		this.x = x2;
		this.y = y2;
		this.patientPainAssessment = patientPainAssessments;
				
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

	public PatientPainAssessments getPatientPainAssessment() {
		return this.patientPainAssessment;
	}

	public void setPatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		this.patientPainAssessment = patientPainAssessment;
	}

}