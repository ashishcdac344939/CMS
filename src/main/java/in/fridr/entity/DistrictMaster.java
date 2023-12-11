package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the district_master database table.
 * 
 */
@Entity
@Table(name = "district_master")
@NamedQuery(name = "DistrictMaster.findAll", query = "SELECT d FROM DistrictMaster d")
public class DistrictMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "lgd_district_code")
	private Integer lgdDistrictCode;

	@Column(name = "district_name")
	private String districtName;

	@Column(name = "district_status")
	private Boolean districtStatus;

	@Column(name = "record_tracking")
	private Timestamp recordTracking;

	// bi-directional many-to-one association to StateMaster
	// bi-directional many-to-one association to StateMaster

	// bi-directional many-to-one association to PatientDetail
	@JsonIgnore
	@OneToMany(mappedBy = "districtMaster")
	private List<PatientDetail> patientDetails;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "lgd_state_code")
	private StateMaster stateMaster;
	private String latitude;
    private String longitude;
	public DistrictMaster() {
	}

	public DistrictMaster(Integer lgdDistrictCode) {
		super();
		this.lgdDistrictCode = lgdDistrictCode;
	}

	public Integer getLgdDistrictCode() {
		return this.lgdDistrictCode;
	}

	public void setLgdDistrictCode(Integer lgdDistrictCode) {
		this.lgdDistrictCode = lgdDistrictCode;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public Boolean getDistrictStatus() {
		return this.districtStatus;
	}

	public void setDistrictStatus(Boolean districtStatus) {
		this.districtStatus = districtStatus;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public StateMaster getStateMaster() {
		return this.stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public List<PatientDetail> getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(List<PatientDetail> patientDetails) {
		this.patientDetails = patientDetails;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}