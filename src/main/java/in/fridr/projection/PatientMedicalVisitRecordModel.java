package in.fridr.projection;

import java.sql.Timestamp;

public interface PatientMedicalVisitRecordModel {
	 String getUserCredId();
	 Timestamp getVisitDate();
	 int getPmvrId();
}
