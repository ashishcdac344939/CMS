package in.fridr.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the state_master database table.
 * 
 */
@Entity
@Table(name="state_master")
@NamedQuery(name="StateMaster.findAll", query="SELECT s FROM StateMaster s")
public class StateMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lgd_state_code")
	private Integer lgdStateCode;

	@Column(name="state_name")
	private String stateName;

	@Column(name="state_status")
	private Boolean stateStatus;
	
	@Column(name="record_tracking")
	private Timestamp recordTracking;
	
	//bi-directional many-to-one association to PatientDetail
	@OneToMany(mappedBy="stateMaster")
	private List<PatientDetail> patientDetails;
	
	@JsonIgnore
	//bi-directional many-to-one association to DistrictMaster
	@OneToMany(mappedBy="stateMaster")
	private List<DistrictMaster> districtMasters;

	public StateMaster() {
	}

	
	
	public StateMaster(Integer lgdStateCode) {
		super();
		this.lgdStateCode = lgdStateCode;
	}



	public List<PatientDetail> getPatientDetails() {
		return patientDetails;
	}

	public void setPatientDetails(List<PatientDetail> patientDetails) {
		this.patientDetails = patientDetails;
	}
	
	public Integer getLgdStateCode() {
		return this.lgdStateCode;
	}

	public void setLgdStateCode(Integer lgdStateCode) {
		this.lgdStateCode = lgdStateCode;
	}

	public Timestamp getRecordTracking() {
		return this.recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}

	public String getStateName() {
		return this.stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Boolean getStateStatus() {
		return this.stateStatus;
	}

	public void setStateStatus(Boolean stateStatus) {
		this.stateStatus = stateStatus;
	}

	public List<DistrictMaster> getDistrictMasters() {
		return this.districtMasters;
	}

	public void setDistrictMasters(List<DistrictMaster> districtMasters) {
		this.districtMasters = districtMasters;
	}

	public DistrictMaster addDistrictMaster(DistrictMaster districtMaster) {
		getDistrictMasters().add(districtMaster);
		districtMaster.setStateMaster(this);

		return districtMaster;
	}

	public DistrictMaster removeDistrictMaster(DistrictMaster districtMaster) {
		getDistrictMasters().remove(districtMaster);
		districtMaster.setStateMaster(null);

		return districtMaster;
	}

}