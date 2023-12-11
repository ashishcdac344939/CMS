package in.fridr.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="center_details")
public class CenterDetails {

	@Id
	@SequenceGenerator(name = "center_details_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="center_details_id")
	private Integer centerId;
	
	@Column(name="center_code")
	private int centerCode;
	
	@Column(name="center_name")
	private String centerName;
	
	@OneToMany(mappedBy = "centerDetails")
	private List<UserDetail>  userDetails;
	
	@OneToMany(mappedBy = "centerDetails")
	private List<MedicineMaster>  medicineMaster;

	public CenterDetails(Integer centerId, int centerCode, String centerName, List<UserDetail> userDetails) {
		super();
		this.centerId = centerId;
		this.centerCode = centerCode;
		this.centerName = centerName;
		this.userDetails = userDetails;
	}

	public CenterDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public CenterDetails(Integer centerId) {
		super();
		this.centerId = centerId;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public int getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(int centerCode) {
		this.centerCode = centerCode;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public List<UserDetail> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetail> userDetails) {
		this.userDetails = userDetails;
	}
	
	
}
