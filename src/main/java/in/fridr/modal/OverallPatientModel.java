package in.fridr.modal;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.fridr.entity.PComprehensiveCarePlan;
import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientPainAssessments;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.UserDetail;

public class OverallPatientModel {
		private List<PComprehensiveCarePlan> PComprehensiveCarePlans;
		private UserDetail userDetail;
		private List<PatientPainAssessments> patientPainAssessments;
		private List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails;
		
		public OverallPatientModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		public OverallPatientModel( List<PComprehensiveCarePlan> pComprehensiveCarePlans,
				UserDetail userDetail, List<PatientPainAssessments> patientPainAssessments,
				List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails) {
			super();
			PComprehensiveCarePlans = pComprehensiveCarePlans;
			this.userDetail = userDetail;
			this.patientPainAssessments = patientPainAssessments;
			this.patientVisitDiagnosisDetails = patientVisitDiagnosisDetails;
		}
		
		
		public List<PComprehensiveCarePlan> getPComprehensiveCarePlans() {
			return PComprehensiveCarePlans;
		}
		public void setPComprehensiveCarePlans(List<PComprehensiveCarePlan> pComprehensiveCarePlans) {
			PComprehensiveCarePlans = pComprehensiveCarePlans;
		}
		public UserDetail getUserDetail() {
			return userDetail;
		}
		public void setUserDetail(UserDetail userDetail) {
			this.userDetail = userDetail;
		}
		public List<PatientPainAssessments> getPatientPainAssessments() {
			return patientPainAssessments;
		}
		public void setPatientPainAssessments(List<PatientPainAssessments> patientPainAssessments) {
			this.patientPainAssessments = patientPainAssessments;
		}
		public List<PatientVisitDiagnosisDetail> getPatientVisitDiagnosisDetails() {
			return patientVisitDiagnosisDetails;
		}
		public void setPatientVisitDiagnosisDetails(List<PatientVisitDiagnosisDetail> patientVisitDiagnosisDetails) {
			this.patientVisitDiagnosisDetails = patientVisitDiagnosisDetails;
		}
		
}
