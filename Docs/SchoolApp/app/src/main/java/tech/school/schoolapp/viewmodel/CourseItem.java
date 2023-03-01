package tech.school.schoolapp.viewmodel;

import java.util.Date;

public class CourseItem {
    private int mImage;
    private String mCourseTitle;
    private String mCourseInstructor;
    private String courseStartLabel;
    private String mCourseStart;

    public CourseItem(int mImage, String mCourseTitle, String mCourseInstructor,String courseStartLabel, String mCourseStart) {
        this.mImage = mImage;
        this.mCourseTitle = mCourseTitle;
        this.mCourseInstructor = mCourseInstructor;
        this.mCourseStart = mCourseStart;
        this.courseStartLabel = courseStartLabel;
    }


    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getmCourseTitle() {
        return mCourseTitle;
    }

    public void setmCourseTitle(String mCourseTitle) {
        this.mCourseTitle = mCourseTitle;
    }

    public String getmCourseInstructor() {
        return mCourseInstructor;
    }

    public void setmCourseInstructor(String mCourseInstructor) {
        this.mCourseInstructor = mCourseInstructor;
    }

    public String getCourseStart() {
        return mCourseStart;
    }

    public void setCourseStart(String mCourseStart) {
        this.mCourseStart = mCourseStart;
    }

    public String getCourseStartLabel() {
        return courseStartLabel;
    }

    public void setCourseStartLabel(String courseStartLabel) {
        this.courseStartLabel = courseStartLabel;
    }




}
