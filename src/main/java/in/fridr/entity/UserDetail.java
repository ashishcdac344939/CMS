package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user_details database table.
 * 
 */
@Entity
@Table(name="user_details")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "user_details_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_details_id")
	private Integer userDetailsId;

	@Column(name="signup_date")
	private Timestamp signUpDate;

	@Column(name="user_description")
	private String userDescription;

	@Column(name="user_mobile")
	private String userMobile;

	@Column(name="user_name")
	private String userName;

	@Column(name="user_status")
	private Boolean userStatus;

	@Column(name="user_unique_id")
	private String userUniqueId;

	// added newlay
	@Column(name = "address")
	private String address;
	
	//bi-directional many-to-one association to PatientDetail
	@OneToOne(mappedBy="userDetail")
	private PatientDetail patientDetails;

	//bi-directional many-to-one association to UserCredential
	@JsonIgnore
	@OneToOne(mappedBy = "userDetails",cascade = CascadeType.ALL)
	private UserCredential userCredential;

	@ManyToOne
	@JoinColumn(name = "center_details_id")
	private CenterDetails centerDetails;
	
	
	public UserDetail() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PatientDetail getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(PatientDetail patientDetails) {
		this.patientDetails = patientDetails;
	}

	
	

	

	public Integer getUserDetailsId() {
		return userDetailsId;
	}

	public void setUserDetailsId(Integer userDetailsId) {
		this.userDetailsId = userDetailsId;
	}

	public Timestamp getSignUpDate() {
		return signUpDate;
	}

	public void setSignUpDate(Timestamp signUpDate) {
		this.signUpDate = signUpDate;
	}

	public String getUserDescription() {
		return this.userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	

	public String getUserMobile() {
		return this.userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserUniqueId() {
		return this.userUniqueId;
	}

	public void setUserUniqueId(String userUniqueId) {
		this.userUniqueId = userUniqueId;
	}


	public UserCredential getUserCredential() {
		return this.userCredential;
	}

	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	
	
	public CenterDetails getCenterDetails() {
		return centerDetails;
	}

	public void setCenterDetails(CenterDetails centerDetails) {
		this.centerDetails = centerDetails;
	}

	// this constructor is going to be used to register the User other than patient
	public UserDetail(Timestamp signUpDate, String userDescription, String userMobile, String userName,
			Boolean userStatus, String userUniqueId,String address,CenterDetails centerDetailsId) {
		this.signUpDate = signUpDate;
		this.userDescription = userDescription;
		this.userMobile = userMobile;
		this.userName = userName;
		this.userStatus = userStatus;
		this.userUniqueId = userUniqueId;
		this.address=address;
		this.centerDetails=centerDetailsId;
	}

	@Override
	public String toString() {
		return "UserDetail [userDetailsId=" + userDetailsId + ", signUpDate=" + signUpDate + ", userDescription="
				+ userDescription + ", userMobile=" + userMobile + ", userName=" + userName + ", userStatus="
				+ userStatus + ", userUniqueId=" + userUniqueId + ", address=" + address + "]";
	}

	

	
}