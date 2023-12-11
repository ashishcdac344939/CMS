package in.fridr.util;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import in.fridr.entity.PatientEdmontonSymptomAssessment;

import java.util.HashMap;

public class EdmontonSymptomAssessmentConverterForMap {
    public static List<Map<String, Object>> convertToSymptomsData(List<PatientEdmontonSymptomAssessment> assessments, String symptomName) {
        List<Map<String, Object>> symptomsData = new ArrayList<>();

        // Create a map to group assessments by symptom name
        Map<String, List<PatientEdmontonSymptomAssessment>> groupedAssessments = new HashMap<>();

        for (PatientEdmontonSymptomAssessment assessment : assessments) {
            String currentSymptomName = assessment.getPain(); // Change to the appropriate default getter method
            if (!groupedAssessments.containsKey(currentSymptomName)) {
                groupedAssessments.put(currentSymptomName, new ArrayList<>());
            }
            groupedAssessments.get(currentSymptomName).add(assessment); 
        }

        // Convert grouped assessments into the desired format
        for (Map.Entry<String, List<PatientEdmontonSymptomAssessment>> entry : groupedAssessments.entrySet()) {
            String currentSymptomName = entry.getKey();
            List<PatientEdmontonSymptomAssessment> symptomAssessments = entry.getValue();

            List<Map<String, Object>> symptomData = new ArrayList<>();
            for (PatientEdmontonSymptomAssessment assessment : symptomAssessments) {
                Map<String, Object> dataPoint = new HashMap<>();
                dataPoint.put("date", assessment.getRecordTracking()); // Change to the appropriate getter method for date

                // Dynamically choose the appropriate getter method based on symptomName
                switch (symptomName) {
                    case "Pain":
                        dataPoint.put("value", assessment.getPain());
                        break;
                    case "Nausea":
                        dataPoint.put("value", assessment.getNausea());
                        break;
                    // Add more cases for other symptoms as needed
                    default:
                        // Handle the case where the symptomName is not recognized
                        dataPoint.put("value", null);
                        break;
                }

                symptomData.add(dataPoint);
            }

            Map<String, Object> symptomEntry = new HashMap<>();
            symptomEntry.put("symptom", currentSymptomName);
            symptomEntry.put("data", symptomData);
            symptomsData.add(symptomEntry);
        }

        return symptomsData;
    }
}
