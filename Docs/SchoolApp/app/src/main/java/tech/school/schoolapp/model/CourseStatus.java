package tech.school.schoolapp.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CourseStatus {
    PlanToTake,
    InProgress,
    Completed,
    Dropped;

    private static final List<CourseStatus> courseStatusList = Arrays.asList(CourseStatus.values());

    public static List<CourseStatus> getCourseStatusList(){
        return courseStatusList;
    }



    public static String courseStatusString(CourseStatus courseStatus){

        switch(courseStatus){
            case PlanToTake:
                return "Plan To Take";
            case InProgress:
                return "In Progress";
            case Completed:
                return "Completed";
            case Dropped:
                return "Dropped";
            default:
                return null;
        }
    }
}
