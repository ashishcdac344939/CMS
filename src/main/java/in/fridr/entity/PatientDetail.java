package in.fridr.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.fridr.modal.PatientModel;


/**
 * The persistent class for the patient_details database table.
 * 
 */
@Entity
@Table(name="patient_details")
@NamedQuery(name="PatientDetail.findAll", query="SELECT p FROM PatientDetail p")
public class PatientDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "patient_details_seq_gen",allocationSize = 1,initialValue = 1)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pd_id")
	private Integer pdId;

	@Column(name="p_current_socio_economic_status")
	private String pCurrentSocioEconomicStatus;

	@Column(name="p_daughter")
	private String pDaughter;

	@Column(name="p_dependent_children")
	private String pDependentChildren;

	@Column(name="p_distance_to_nci_inkm")
	private BigDecimal pDistanceToNciInkm;

	@Temporal(TemporalType.DATE)
	@Column(name="p_dob")
	private Date pDob;

	@Column(name="p_edu_qualification")
	private String pEduQualification;

	@Column(name="p_family_earning_member")
	private String pFamilyEarningMember;

	@Column(name="p_gender")
	private String pGender;

	@Column(name="p_marrigeble_daughters")
	private String pMarrigebleDaughters;

	@Column(name="p_mode_of_transport")
	private String pModeOfTransport;

	@Column(name="p_primary_care_giver")
	private String pPrimaryCareGiver;

	@Column(name="p_primary_care_giver_relation_with_patient")
	private String pPrimaryCareGiverRelationWithPatient;
	
	@Column(name="p_sons")
	private String pSons;

	@Temporal(TemporalType.DATE)
	@Column(name="p_when_cancer_diagnosed")
	private Date pWhenCancerDiagnosed;

	@Column(name="opdipd")
	private String opdipd;
	
	@Column(name="patientRegNo")
	private String patientRegNo;
	
	@Column(name="maritalStatus")
	private String maritalStatus;
	
	@Column(name="housingStatus")
	private String housingStatus;
	
	@Column(name="occupationOfPatient")
	private String occupationOfPatient;
	
	
	@Column(name="familyHeadEducation")
	private String familyHeadEducation;
	
	@Column(name="familyHeadOccupation")
	private String familyHeadOccupation;
	
	@Column(name="familyHeadIncome")
	private String familyHeadIncome;
	
	@Column(name="stateOfCancer")
	private String stateOfCancer;
	
	@Column(name="currentDiagnosis")
	private String currentDiagnosis;
	
	
	@Column(name="treatmentDetails")
	private String treatmentDetails;
	
	
	//bi-directional many-to-one association to PComprehensiveCarePlan
	@OneToMany(mappedBy="patientDetail")
	private List<PComprehensiveCarePlan> PComprehensiveCarePlans;

	//bi-directional many-to-one association to UserDetail
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="user_details_id")
	private UserDetail userDetail;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="d_user_cred_id")
	private UserCredential dUserCredential;
	
	@JsonIgnore
	//bi-directional many-to-one association to PatientPainAssessments_
	@OneToMany(mappedBy="patientDetail")
	private List<PatientPainAssessments> patientPainAssessments;

	//bi-directional many-to-one association to PatientVisitDiagnosisDetail
	@OneToMany(mappedBy="patientDetail")
	private List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails;
	
	@OneToMany(mappedBy = "patientDetail")
	private List<PatientMedicinePrescription> patientMedicinePrescriptions;
	

	//bi-directional many-to-one association to DistrictMaster
		@ManyToOne
		@JoinColumn(name="district_master_id")
		private DistrictMaster districtMaster;

		//bi-directional many-to-one association to StateMaster
		@ManyToOne
		@JoinColumn(name="state_master_id")
		private StateMaster stateMaster;
		
		@Column(name="record_tracking")
		private Timestamp recordTracking;

		@OneToMany(mappedBy = "patientDetail",cascade = CascadeType.ALL)
		private List<TreatmentRecevied> treatmentRecevied;
		
		@OneToMany(mappedBy = "patientDetail",cascade = CascadeType.ALL ,orphanRemoval = true)
		private List<PatientLanguage> patientLanguage;

		

		
		@Override
		public String toString() {
			return "PatientDetail [pdId=" + pdId + ", pCurrentSocioEconomicStatus=" + pCurrentSocioEconomicStatus
					+ ", pDaughter=" + pDaughter + ", pDependentChildren=" + pDependentChildren
					+ ", pDistanceToNciInkm=" + pDistanceToNciInkm + ", pDob=" + pDob + ", pEduQualification="
					+ pEduQualification + ", pFamilyEarningMember=" + pFamilyEarningMember + ", pGender=" + pGender
					+ ", pMarrigebleDaughters=" + pMarrigebleDaughters + ", pModeOfTransport=" + pModeOfTransport
					+ ", pPrimaryCareGiver=" + pPrimaryCareGiver + ", pPrimaryCareGiverRelationWithPatient="
					+ pPrimaryCareGiverRelationWithPatient + ", pSons=" + pSons + ", pWhenCancerDiagnosed="
					+ pWhenCancerDiagnosed + ", opdipd=" + opdipd + ", patientRegNo=" + patientRegNo
					+ ", maritalStatus=" + maritalStatus + ", housingStatus=" + housingStatus + ", occupationOfPatient="
					+ occupationOfPatient + ", familyHeadEducation=" + familyHeadEducation + ", familyHeadOccupation="
					+ familyHeadOccupation + ", familyHeadIncome=" + familyHeadIncome + ", stateOfCancer="
					+ stateOfCancer + ", currentDiagnosis=" + currentDiagnosis + ", treatmentDetails="
					+ treatmentDetails + "]";
		}

		// this constuctor is used to get the patientDetails from patientModel
		public PatientDetail(PatientModel pm) {
			this.dUserCredential=new UserCredential(pm.getDocId());
			this.opdipd=pm.getOpdipd();
			this.patientRegNo=pm.getPatientRegNo();
			this.pGender=pm.getpGender();
			this.pDob=pm.getpDob();
			this.pEduQualification=pm.getpEduQualification();
			this.occupationOfPatient=pm.getOccupationOfPatient();
			this.maritalStatus=pm.getMaritalStatus();
			this.housingStatus=pm.getHousingStatus();
			this.familyHeadEducation=pm.getFamilyHeadEducation();
			this.familyHeadOccupation=pm.getFamilyHeadOccupation();
			this.familyHeadIncome=pm.getFamilyHeadIncome();
			this.stateOfCancer=pm.getStateOfCancer();
			this.pPrimaryCareGiver=pm.getpPrimaryCareGiver();
			this.currentDiagnosis=pm.getCurrentDiagnosis();
			this.treatmentDetails=pm.getTreatmentDetails();
			this.pPrimaryCareGiverRelationWithPatient=pm.getPrimaryCareGiverRelationWithPatient();
			this.pFamilyEarningMember=pm.getpFamilyEarningMember();
			this.pSons=pm.getpSons();
			this.pDaughter=pm.getpDaughter();
			this.pDependentChildren=pm.getpDependentChildren();
			this.pMarrigebleDaughters=pm.getpMarrigebleDaughters();
			this.pDistanceToNciInkm=pm.getpDistanceToNciInkm();
			this.pModeOfTransport=pm.getpModeOfTransport();
			this.pWhenCancerDiagnosed=pm.getpWhenCancerDiagnosed();
			this.stateMaster=new StateMaster(pm.getStateId());
			this.districtMaster=new DistrictMaster(pm.getDistrictId());
			
			//this.patientLanguage=PatientLanguage.getPatientLanguageList(pm.getLanguageknown());
			//this.treatmentRecevied=TreatmentRecevied.getTreatmentRecevied(pm.getTreatmentReceived());
			
		}

		public List<PatientMedicinePrescription> getPatientMedicinePrescriptions() {
			return patientMedicinePrescriptions;
		}

		public void setPatientMedicinePrescriptions(List<PatientMedicinePrescription> patientMedicinePrescriptions) {
			this.patientMedicinePrescriptions = patientMedicinePrescriptions;
		}

		public PatientDetail() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PatientDetail(Integer pdId) {
			super();
			this.pdId = pdId;
		} 
		
		
	public DistrictMaster getDistrictMaster() {
			return districtMaster;
		}

		public void setDistrictMaster(DistrictMaster districtMaster) {
			this.districtMaster = districtMaster;
		}

		public StateMaster getStateMaster() {
			return stateMaster;
		}

		public void setStateMaster(StateMaster stateMaster) {
			this.stateMaster = stateMaster;
		}

		
	
		
	public String getpPrimaryCareGiverRelationWithPatient() {
			return pPrimaryCareGiverRelationWithPatient;
		}

		public void setpPrimaryCareGiverRelationWithPatient(String pPrimaryCareGiverRelationWithPatient) {
			this.pPrimaryCareGiverRelationWithPatient = pPrimaryCareGiverRelationWithPatient;
		}

	public Integer getPId() {
		return this.pdId;
	}

	public void setPId(Integer pId) {
		this.pdId = pId;
	}

	public String getPCurrentSocioEconomicStatus() {
		return this.pCurrentSocioEconomicStatus;
	}

	public void setPCurrentSocioEconomicStatus(String pCurrentSocioEconomicStatus) {
		this.pCurrentSocioEconomicStatus = pCurrentSocioEconomicStatus;
	}

	public String getPDaughter() {
		return this.pDaughter;
	}

	public void setPDaughter(String pDaughter) {
		this.pDaughter = pDaughter;
	}

	public String getPDependentChildren() {
		return this.pDependentChildren;
	}

	public void setPDependentChildren(String pDependentChildren) {
		this.pDependentChildren = pDependentChildren;
	}

	public BigDecimal getPDistanceToNciInkm() {
		return this.pDistanceToNciInkm;
	}

	public void setPDistanceToNciInkm(BigDecimal pDistanceToNciInkm) {
		this.pDistanceToNciInkm = pDistanceToNciInkm;
	}

	public Date getPDob() {
		return this.pDob;
	}

	public void setPDob(Date pDob) {
		this.pDob = pDob;
	}

	
	public String getOccupationOfPatient() {
		return occupationOfPatient;
	}


	public void setOccupationOfPatient(String occupationOfPatient) {
		this.occupationOfPatient = occupationOfPatient;
	}


	public String getPEduQualification() {
		return this.pEduQualification;
	}

	public void setPEduQualification(String pEduQualification) {
		this.pEduQualification = pEduQualification;
	}

	public String getPFamilyEarningMember() {
		return this.pFamilyEarningMember;
	}

	public void setPFamilyEarningMember(String pFamilyEarningMember) {
		this.pFamilyEarningMember = pFamilyEarningMember;
	}

	public String getPGender() {
		return this.pGender;
	}
    
	public void setPGender(String pGender) {
		this.pGender = pGender;
	}

	public String getPMarrigebleDaughters() {
		return this.pMarrigebleDaughters;
	}

	public void setPMarrigebleDaughters(String pMarrigebleDaughters) {
		this.pMarrigebleDaughters = pMarrigebleDaughters;
	}

	public String getPModeOfTransport() {
		return this.pModeOfTransport;
	}

	public void setPModeOfTransport(String pModeOfTransport) {
		this.pModeOfTransport = pModeOfTransport;
	}

	public String getPPrimaryCareGiver() {
		return this.pPrimaryCareGiver;
	}

	public void setPPrimaryCareGiver(String pPrimaryCareGiver) {
		this.pPrimaryCareGiver = pPrimaryCareGiver;
	}

	public String getPSons() {
		return this.pSons;
	}

	public void setPSons(String pSons) {
		this.pSons = pSons;
	}

	public Date getPWhenCancerDiagnosed() {
		return this.pWhenCancerDiagnosed;
	}

	public void setPWhenCancerDiagnosed(Date pWhenCancerDiagnosed) {
		this.pWhenCancerDiagnosed = pWhenCancerDiagnosed;
	}

	public List<PComprehensiveCarePlan> getPComprehensiveCarePlans() {
		return this.PComprehensiveCarePlans;
	}

	public void setPComprehensiveCarePlans(List<PComprehensiveCarePlan> PComprehensiveCarePlans) {
		this.PComprehensiveCarePlans = PComprehensiveCarePlans;
	}

	public PComprehensiveCarePlan addPComprehensiveCarePlan(PComprehensiveCarePlan PComprehensiveCarePlan) {
		getPComprehensiveCarePlans().add(PComprehensiveCarePlan);
		PComprehensiveCarePlan.setPatientDetail(this);

		return PComprehensiveCarePlan;
	}

	public PComprehensiveCarePlan removePComprehensiveCarePlan(PComprehensiveCarePlan PComprehensiveCarePlan) {
		getPComprehensiveCarePlans().remove(PComprehensiveCarePlan);
		PComprehensiveCarePlan.setPatientDetail(null);

		return PComprehensiveCarePlan;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public List<PatientPainAssessments> getPatientPainAssessments() {
		return this.patientPainAssessments;
	}

	public void setPatientPainAssessments(List<PatientPainAssessments> patientPainAssessments) {
		this.patientPainAssessments = patientPainAssessments;
	}

	public PatientPainAssessments addPatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().add(patientPainAssessment);
		patientPainAssessment.setPatientDetail(this);

		return patientPainAssessment;
	}

	public PatientPainAssessments removePatientPainAssessment(PatientPainAssessments patientPainAssessment) {
		getPatientPainAssessments().remove(patientPainAssessment);
		patientPainAssessment.setPatientDetail(null);

		return patientPainAssessment;
	}

	public List<PatientVisitDiagnosisDetail> getPatientVisitDiagnosisDetails() {
		return this.patientVisitDiagnosisDetails;
	}

	public void setPatientVisitDiagnosisDetails(List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails) {
		this.patientVisitDiagnosisDetails = patientVisitDiagnosisDetails;
	}

	public PatientVisitDiagnosisDetail addPatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		getPatientVisitDiagnosisDetails().add(patientVisitDiagnosisDetail);
		patientVisitDiagnosisDetail.setPatientDetail(this);

		return patientVisitDiagnosisDetail;
	}

	public UserCredential getdUserCredential() {
		return dUserCredential;
	}

	public void setdUserCredential(UserCredential dUserCredential) {
		this.dUserCredential = dUserCredential;
	}

	public PatientVisitDiagnosisDetail removePatientVisitDiagnosisDetail(PatientVisitDiagnosisDetail patientVisitDiagnosisDetail) {
		getPatientVisitDiagnosisDetails().remove(patientVisitDiagnosisDetail);
		patientVisitDiagnosisDetail.setPatientDetail(null);

		return patientVisitDiagnosisDetail;
	}

	public Timestamp getRecordTracking() {
		return recordTracking;
	}

	public void setRecordTracking(Timestamp recordTracking) {
		this.recordTracking = recordTracking;
	}



	public Integer getPdId() {
		return pdId;
	}


	public void setPdId(Integer pdId) {
		this.pdId = pdId;
	}


	public String getpCurrentSocioEconomicStatus() {
		return pCurrentSocioEconomicStatus;
	}


	public void setpCurrentSocioEconomicStatus(String pCurrentSocioEconomicStatus) {
		this.pCurrentSocioEconomicStatus = pCurrentSocioEconomicStatus;
	}


	public String getpDaughter() {
		return pDaughter;
	}


	public void setpDaughter(String pDaughter) {
		this.pDaughter = pDaughter;
	}


	public String getpDependentChildren() {
		return pDependentChildren;
	}


	public void setpDependentChildren(String pDependentChildren) {
		this.pDependentChildren = pDependentChildren;
	}


	public BigDecimal getpDistanceToNciInkm() {
		return pDistanceToNciInkm;
	}


	public void setpDistanceToNciInkm(BigDecimal pDistanceToNciInkm) {
		this.pDistanceToNciInkm = pDistanceToNciInkm;
	}


	public Date getpDob() {
		return pDob;
	}


	public void setpDob(Date pDob) {
		this.pDob = pDob;
	}


	public String getpEduQualification() {
		return pEduQualification;
	}


	public void setpEduQualification(String pEduQualification) {
		this.pEduQualification = pEduQualification;
	}


	public String getpFamilyEarningMember() {
		return pFamilyEarningMember;
	}


	public void setpFamilyEarningMember(String pFamilyEarningMember) {
		this.pFamilyEarningMember = pFamilyEarningMember;
	}


	public String getpGender() {
		return pGender;
	}


	public void setpGender(String pGender) {
		this.pGender = pGender;
	}


	public String getpMarrigebleDaughters() {
		return pMarrigebleDaughters;
	}


	public void setpMarrigebleDaughters(String pMarrigebleDaughters) {
		this.pMarrigebleDaughters = pMarrigebleDaughters;
	}


	public String getpModeOfTransport() {
		return pModeOfTransport;
	}


	public void setpModeOfTransport(String pModeOfTransport) {
		this.pModeOfTransport = pModeOfTransport;
	}


	public String getpPrimaryCareGiver() {
		return pPrimaryCareGiver;
	}


	public void setpPrimaryCareGiver(String pPrimaryCareGiver) {
		this.pPrimaryCareGiver = pPrimaryCareGiver;
	}


	public String getpSons() {
		return pSons;
	}


	public void setpSons(String pSons) {
		this.pSons = pSons;
	}


	public Date getpWhenCancerDiagnosed() {
		return pWhenCancerDiagnosed;
	}


	public void setpWhenCancerDiagnosed(Date pWhenCancerDiagnosed) {
		this.pWhenCancerDiagnosed = pWhenCancerDiagnosed;
	}


	public String getOpdipd() {
		return opdipd;
	}


	public void setOpdipd(String opdipd) {
		this.opdipd = opdipd;
	}


	public String getPatientRegNo() {
		return patientRegNo;
	}


	public void setPatientRegNo(String patientRegNo) {
		this.patientRegNo = patientRegNo;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}


	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getHousingStatus() {
		return housingStatus;
	}


	public void setHousingStatus(String housingStatus) {
		this.housingStatus = housingStatus;
	}



	public String getFamilyHeadEducation() {
		return familyHeadEducation;
	}


	public void setFamilyHeadEducation(String familyHeadEducation) {
		this.familyHeadEducation = familyHeadEducation;
	}


	public String getFamilyHeadOccupation() {
		return familyHeadOccupation;
	}


	public void setFamilyHeadOccupation(String familyHeadOccupation) {
		this.familyHeadOccupation = familyHeadOccupation;
	}


	public String getFamilyHeadIncome() {
		return familyHeadIncome;
	}


	public void setFamilyHeadIncome(String familyHeadIncome) {
		this.familyHeadIncome = familyHeadIncome;
	}


	public String getStateOfCancer() {
		return stateOfCancer;
	}


	public void setStateOfCancer(String stateOfCancer) {
		this.stateOfCancer = stateOfCancer;
	}


	public String getCurrentDiagnosis() {
		return currentDiagnosis;
	}


	public void setCurrentDiagnosis(String currentDiagnosis) {
		this.currentDiagnosis = currentDiagnosis;
	}



	public String getTreatmentDetails() {
		return treatmentDetails;
	}


	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}


	public List<TreatmentRecevied> getTreatmentRecevied() {
		return treatmentRecevied;
	}


	public void setTreatmentRecevied(List<TreatmentRecevied> treatmentRecevied) {
		this.treatmentRecevied = treatmentRecevied;
	}


	public List<PatientLanguage> getPatientLanguage() {
		return patientLanguage;
	}


	public void setPatientLanguage(List<PatientLanguage> patientLanguage) {
		this.patientLanguage = patientLanguage;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	
	
}