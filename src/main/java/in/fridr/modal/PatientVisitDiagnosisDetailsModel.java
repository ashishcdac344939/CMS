package in.fridr.modal;

import java.sql.Timestamp;

public class PatientVisitDiagnosisDetailsModel {
	
	 private int   patientId;
	  private String  presentStatus;
	  private String   userName;
	  private String importantFindings;
	  private String  secondaryFindings;
	  private Timestamp   nextvisitdate;
	    private int   pvid;
	    private String  pdstageOfcancer;
	    private String  doctorUcId;
	    private int   patientUcId;
	 private Timestamp   invistigationdate;
	private String    pdcurrentdiagnosis;
	  private String  pdtreatmentreceived;
	private  boolean isPainAssessmentFilled ;
	 private   boolean isPrescriptionFilled;
	  
	public PatientVisitDiagnosisDetailsModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public PatientVisitDiagnosisDetailsModel(int patientId, int patientUcId) {
		super();
		this.patientId = patientId;
		this.patientUcId = patientUcId;
	}



	
	public PatientVisitDiagnosisDetailsModel(int patientId, String presentStatus, String userName,
			String importantFindings, String secondaryFindings, Timestamp nextvisitdate, int pvid, String pdstageOfcancer,
			String doctorUcId, int patientUcId, Timestamp invistigationdate, String pdcurrentdiagnosis,
			String pdtreatmentreceived, boolean isPainAssessmentFilled, boolean isPrescriptionFilled) {
		super();
		this.patientId = patientId;
		this.presentStatus = presentStatus;
		this.userName = userName;
		this.importantFindings = importantFindings;
		this.secondaryFindings = secondaryFindings;
		this.nextvisitdate = nextvisitdate;
		this.pvid = pvid;
		this.pdstageOfcancer = pdstageOfcancer;
		this.doctorUcId = doctorUcId;
		this.patientUcId = patientUcId;
		this.invistigationdate = invistigationdate;
		this.pdcurrentdiagnosis = pdcurrentdiagnosis;
		this.pdtreatmentreceived = pdtreatmentreceived;
		this.isPainAssessmentFilled = isPainAssessmentFilled;
		this.isPrescriptionFilled = isPrescriptionFilled;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPresentStatus() {
		return presentStatus;
	}

	public void setPresentStatus(String presentStatus) {
		this.presentStatus = presentStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImportantFindings() {
		return importantFindings;
	}

	public void setImportantFindings(String importantFindings) {
		this.importantFindings = importantFindings;
	}

	public String getSecondaryFindings() {
		return secondaryFindings;
	}

	public void setSecondaryFindings(String secondaryFindings) {
		this.secondaryFindings = secondaryFindings;
	}

	public Timestamp getNextvisitdate() {
		return nextvisitdate;
	}

	public void setNextvisitdate(Timestamp nextvisitdate) {
		this.nextvisitdate = nextvisitdate;
	}

	public int getPvid() {
		return pvid;
	}

	public void setPvid(int pvid) {
		this.pvid = pvid;
	}

	public String getPdstageOfcancer() {
		return pdstageOfcancer;
	}

	public void setPdstageOfcancer(String pdstageOfcancer) {
		this.pdstageOfcancer = pdstageOfcancer;
	}

	public String getDoctorUcId() {
		return doctorUcId;
	}

	public void setDoctorUcId(String doctorUcId) {
		this.doctorUcId = doctorUcId;
	}

	public int getPatientUcId() {
		return patientUcId;
	}

	public void setPatientUcId(int patientUcId) {
		this.patientUcId = patientUcId;
	}

	public Timestamp getInvistigationdate() {
		return invistigationdate;
	}

	public void setInvistigationdate(Timestamp invistigationdate) {
		this.invistigationdate = invistigationdate;
	}

	public String getPdcurrentdiagnosis() {
		return pdcurrentdiagnosis;
	}

	public void setPdcurrentdiagnosis(String pdcurrentdiagnosis) {
		this.pdcurrentdiagnosis = pdcurrentdiagnosis;
	}

	public String getPdtreatmentreceived() {
		return pdtreatmentreceived;
	}

	public void setPdtreatmentreceived(String pdtreatmentreceived) {
		this.pdtreatmentreceived = pdtreatmentreceived;
	}

	public boolean isPainAssessmentFilled() {
		return isPainAssessmentFilled;
	}

	public void setPainAssessmentFilled(boolean isPainAssessmentFilled) {
		this.isPainAssessmentFilled = isPainAssessmentFilled;
	}

	public boolean isPrescriptionFilled() {
		return isPrescriptionFilled;
	}

	public void setPrescriptionFilled(boolean isPrescriptionFilled) {
		this.isPrescriptionFilled = isPrescriptionFilled;
	}
	
}
