package in.fridr.modal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import in.fridr.entity.PatientDetail;
import in.fridr.entity.PatientVisitDiagnosisDetail;
import in.fridr.entity.SymptomMedicineMapping;

public class PatientPrescriptionResModal {
	private Integer id;
	
	private boolean morphinFormApplicable; 
	private String medicineQuantity;
	
	private PatientDetail patientDetail;
	

	private PatientVisitDiagnosisDetail patientVisitDiagnosisDetail;
	

	private SymptomMedicineMapping symptomMedicineMapping;

}
