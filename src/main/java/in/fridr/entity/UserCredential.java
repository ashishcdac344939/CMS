package in.fridr.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the user_credential database table.
 * 
 */
@Entity
@Table(name = "user_credential",uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}))
@NamedQuery(name = "UserCredential.findAll", query = "SELECT u FROM UserCredential u")
public class UserCredential implements Serializable {
	public UserCredential(Integer id) {
		super();
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_credential_seq_gen", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "password")
	private String passwrord;

	@Column(name = "otp")
	private String otp;

	@Column(name = "last_login")
	private Timestamp recordTracking;

	@Column(name = "is_login_password")
	private boolean isLoginWithPassword;

	// bi-directional many-to-one association to UserDetail
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userDetailsId")
	private UserDetail userDetails;
	 @OneToOne(mappedBy = "dUserCredential")
	  private PatientDetail dPatientDetail;

	@OneToMany(mappedBy = "userCredential",fetch = FetchType.EAGER)
	private List<UserRolesMapping> userRolesMapping;
	
	// bi-directional many-to-one association to PatientVisitDiagnosisDetail
	@OneToMany(mappedBy = "dUserCredential")
	private List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails;
	@OneToMany(mappedBy = "pUserCredential")
	private List<PatientVisitDiagnosisDetail> pPatientVisitDiagnosisDetails;
	//bi-directional many-to-one association to PatientEdmontonSymptomAssessment\
	@Column(name="reset_password_token")
	private String resetPasswordToken;
	@JsonIgnore
		@OneToMany(mappedBy="esaUserCredential")
		private List<PatientEdmontonSymptomAssessment> patientEdmontonSymptomAssessments;
	public Integer getId() {
		return this.id; 
	} 

	public UserCredential() {
	}

	public PatientDetail getdPatientDetail() {
		return dPatientDetail;
	}

	public void setdPatientDetail(PatientDetail dPatientDetail) {
		this.dPatientDetail = dPatientDetail;
	}

	public List<PatientEdmontonSymptomAssessment> getPatientEdmontonSymptomAssessments() {
		return patientEdmontonSymptomAssessments;
	}

	public void setPatientEdmontonSymptomAssessments(
			List<PatientEdmontonSymptomAssessment> patientEdmontonSymptomAssessments) {
		this.patientEdmontonSymptomAssessments = patientEdmontonSymptomAssessments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getPasswrord() {
		return this.passwrord;
	}

	public void setPasswrord(String passwrord) {
		this.passwrord = passwrord;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserDetail getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetail userDetails) {
		this.userDetails = userDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isLoginWithPassword() {
		return isLoginWithPassword;
	}

	public void setLoginWithPassword(boolean isLoginWithPassword) {
		this.isLoginWithPassword = isLoginWithPassword;
	}

	public UserCredential(String userId, String passwrord) {
		super();
		this.userId = userId;
		this.passwrord = passwrord;
	}

	

	public List<UserRolesMapping> getUserRolesMapping() {
		return userRolesMapping;
	}

	public void setUserRolesMapping(List<UserRolesMapping> userRolesMapping) {
		this.userRolesMapping = userRolesMapping;
	}

	public List<PatientVisitDiagnosisDetail> getPatientVisitDiagnosisDetails() {
		return patientVisitDiagnosisDetails;
	}

	public void setPatientVisitDiagnosisDetails(List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails) {
		this.patientVisitDiagnosisDetails = patientVisitDiagnosisDetails;
	}

	public UserCredential(String userId, String passwrord, Timestamp recordTracking, UserDetail userDetails) {
		super();
		this.userId = userId;
		this.passwrord = passwrord;
		this.recordTracking = recordTracking;
		this.userDetails = userDetails;
	}

	public List<PatientVisitDiagnosisDetail> getpPatientVisitDiagnosisDetails() {
		return pPatientVisitDiagnosisDetails;
	}

	public void setpPatientVisitDiagnosisDetails(List<PatientVisitDiagnosisDetail> pPatientVisitDiagnosisDetails) {
		this.pPatientVisitDiagnosisDetails = pPatientVisitDiagnosisDetails;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

}