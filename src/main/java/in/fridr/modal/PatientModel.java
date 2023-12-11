package in.fridr.modal;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import in.fridr.entity.DistrictMaster;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.StateMaster;

public class PatientModel {

	public UserModel userModel;
	
	private int docId;
	private String opdipd;
	private String patientRegNo;
	private String maritalStatus;
	private String housingStatus;
	private List<String> languageknown;
	private String familyHeadEducation;
	private String familyHeadOccupation;
	private String familyHeadIncome;
	private String stateOfCancer;
	private String currentDiagnosis;
	private List<String> treatmentReceived;
	private String treatmentDetails;
	private String occupationOfPatient;
	
	private String pGender;
	private Date pDob;
	private String pEduQualification;
	private String pPrimaryCareGiver;
	private String primaryCareGiverRelationWithPatient;
	private String pFamilyEarningMember;
	private String pSons;
	private String pDaughter;
	private String pDependentChildren;
	private String pMarrigebleDaughters;
	private String pCurrentSocioEconomicStatus;
	private BigDecimal pDistanceToNciInkm;
	private String pModeOfTransport;
	private Date pWhenCancerDiagnosed;
	private int stateId;
	private int districtId;
	
	public UserModel getUserModel() {
		return userModel;
	}
	
	
	@Override
	public String toString() {
		return "PatientModel [userModel=" + userModel + ", pGender=" + pGender + ", pDob=" + pDob
				+ ", pEduQualification=" + pEduQualification + ", pPrimaryCareGiver=" + pPrimaryCareGiver
				+ ", pFamilyEarningMember=" + pFamilyEarningMember + ", pSons=" + pSons + ", pDaughter=" + pDaughter
				+ ", pDependentChildren=" + pDependentChildren + ", pMarrigebleDaughters=" + pMarrigebleDaughters
				+ ", pCurrentSocioEconomicStatus=" + pCurrentSocioEconomicStatus + ", pDistanceToNciInkm="
				+ pDistanceToNciInkm + ", pModeOfTransport=" + pModeOfTransport + ", pWhenCancerDiagnosed="
				+ pWhenCancerDiagnosed + "]";
	}
	
	public PatientDetail getPatient() {
		return new PatientDetail(this);
	}
	
	public String getPrimaryCareGiverRelationWithPatient() {
		return primaryCareGiverRelationWithPatient;
	}


	public void setPrimaryCareGiverRelationWithPatient(String primaryCareGiverRelationWithPatient) {
		this.primaryCareGiverRelationWithPatient = primaryCareGiverRelationWithPatient;
	}


	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public String getpGender() {
		return  pGender;
	}
	public void setpGender(String pGender) {
		this.pGender = pGender;
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
	public String getpPrimaryCareGiver() {
		return pPrimaryCareGiver;
	}
	public void setpPrimaryCareGiver(String pPrimaryCareGiver) {
		this.pPrimaryCareGiver = pPrimaryCareGiver;
	}
	public String getpFamilyEarningMember() {
		return pFamilyEarningMember;
	}
	public void setpFamilyEarningMember(String pFamilyEarningMember) {
		this.pFamilyEarningMember = pFamilyEarningMember;
	}
	public String getpSons() {
		return pSons;
	}
	public void setpSons(String pSons) {
		this.pSons = pSons;
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
	public String getpMarrigebleDaughters() {
		return pMarrigebleDaughters;
	}
	public void setpMarrigebleDaughters(String pMarrigebleDaughters) {
		this.pMarrigebleDaughters = pMarrigebleDaughters;
	}
	public String getpCurrentSocioEconomicStatus() {
		return pCurrentSocioEconomicStatus;
	}
	public void setpCurrentSocioEconomicStatus(String pCurrentSocioEconomicStatus) {
		this.pCurrentSocioEconomicStatus = pCurrentSocioEconomicStatus;
	}
	public BigDecimal getpDistanceToNciInkm() {
		return pDistanceToNciInkm;
	}
	public void setpDistanceToNciInkm(BigDecimal pDistanceToNciInkm) {
		this.pDistanceToNciInkm = pDistanceToNciInkm;
	}
	public String getpModeOfTransport() {
		return pModeOfTransport;
	}
	public void setpModeOfTransport(String pModeOfTransport) {
		this.pModeOfTransport = pModeOfTransport;
	}
	public Date getpWhenCancerDiagnosed() {
		return pWhenCancerDiagnosed;
	}
	public void setpWhenCancerDiagnosed(Date pWhenCancerDiagnosed) {
		this.pWhenCancerDiagnosed = pWhenCancerDiagnosed;
	}
	
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public int getDistrictId() {
		return districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
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


	public List<String> getLanguageknown() {
		return languageknown;
	}


	public void setLanguageknown(List<String> languageknown) {
		this.languageknown = languageknown;
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


	public List<String> getTreatmentReceived() {
		return treatmentReceived;
	}


	public void setTreatmentReceived(List<String> treatmentReceived) {
		this.treatmentReceived = treatmentReceived;
	}


	public String getTreatmentDetails() {
		return treatmentDetails;
	}


	public void setTreatmentDetails(String treatmentDetails) {
		this.treatmentDetails = treatmentDetails;
	}


	public String getOccupationOfPatient() {
		return occupationOfPatient;
	}


	public void setOccupationOfPatient(String occupationOfPatient) {
		this.occupationOfPatient = occupationOfPatient;
	}

	
	
}
