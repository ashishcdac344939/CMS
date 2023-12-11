package in.fridr.projection;

import java.sql.Date;

public interface PatientPrescptionProjection {

	public int getDocId();
	public String getAdharno();
	public String getAddress();
	public String getName();
	public String getSymptom();
	public String getMedicineName();
	public String getMedicineQuantity();
	public Date getDob();
	public int getNoOfDays();
	public int getdaysInterval();
	public int getmedicineInADays();
	public boolean getIs3ERequired();
	public int getCurrentAvailableCount();
	public int getPvId();
	public int getPmpId();
	public String getRemark();
	public String getDocName();
	public String getDocRegNo();
	
}
