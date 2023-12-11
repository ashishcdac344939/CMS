package in.fridr.projection;

import java.sql.Timestamp;

public interface PatientDataModel {
	
	public int getPvid();
	public Timestamp getInvistigationdate();
	public Timestamp getNextvisitdate();
	public String getPdcurrentdiagnosis();
	public String getImportantFindings();
	public String getSecondaryFindings();
	public String getPdstageOfcancer();
	public String getPdtreatmentreceived();
	public String getDoctorUcId();
	public String getPresentStatus();
	public int getPatientUcId();
	public int getPatientId();
	public String getUserName();
	
	 void setPatientUcId(Integer patientUcId);
	 void setPatientId(Integer integer);
	 
	 
	 
	
	
}
