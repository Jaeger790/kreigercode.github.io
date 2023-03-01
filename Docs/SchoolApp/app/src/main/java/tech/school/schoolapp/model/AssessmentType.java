package tech.school.schoolapp.model;

public enum AssessmentType {
    OBJECTIVE,
    PERFORMANCE;

    public static String assessmentString(AssessmentType assessmentType){

        switch(assessmentType){
            case OBJECTIVE:
                return "Objective Assessment";
            case PERFORMANCE:
                return "Performance Assessment";
            default:
                return null;
        }
    }
    }
