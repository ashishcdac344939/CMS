package in.fridr.modal;

public class AssessmentPrescriptionStatus {
    private boolean isPainAssessment;
    private boolean isPrescribed;

    public AssessmentPrescriptionStatus(boolean isPainAssessment, boolean isPrescribed) {
        this.isPainAssessment = isPainAssessment;
        this.isPrescribed = isPrescribed;
    }

    public boolean isPainAssessment() {
        return isPainAssessment;
    }

    public boolean isPrescribed() {
        return isPrescribed;
    }
}

