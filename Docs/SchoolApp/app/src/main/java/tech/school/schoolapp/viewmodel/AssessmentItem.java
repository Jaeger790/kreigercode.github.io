package tech.school.schoolapp.viewmodel;

public class AssessmentItem {

    private String assessmentType;
    private String assessmentTitle;
    private String endDate;

    public AssessmentItem(String assessmentType, String assessmentTitle, String endDate) {
        this.assessmentType = assessmentType;
        this.assessmentTitle = assessmentTitle;
        this.endDate = endDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public String getEndDate() {
        return endDate;
    }
}
