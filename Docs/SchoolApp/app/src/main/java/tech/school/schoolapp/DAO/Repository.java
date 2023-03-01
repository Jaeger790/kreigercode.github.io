package tech.school.schoolapp.DAO;

import android.app.Application;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.model.Term;

public class Repository {

    private CourseDAO mCourseDAO;
    private List<Course> mAllCourses;
    private List<Course> mAllAssociatedCourse;
    private Course selectedCourse;
    private InstructorDAO mInstructorDAO;
    private List<Instructor> mAllInstructors;
    private List<Instructor> mAllInstructorNames;
    private Instructor selectedInstructor;

    private AssessmentDAO mAssessmentDAO;
    private List<Assessment> mAllAssessments;
    private Assessment selectedAssessment;


    private TermDAO mTermDAO;
    private List<Term> mAllTerms;
    private Term selectedTerm;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        SchoolAppDatabase db = SchoolAppDatabase.getDatabase(application);
        mCourseDAO = db.courseDAO();
        mInstructorDAO = db.instructorDAO();
        mAssessmentDAO = db.assessmentDAO();
        mTermDAO = db.termDAO();
    }


    //Course Queries

    public List<Course>mGetAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public Course mQueryCourse(Course course){
        databaseExecutor.execute(()->{
            selectedCourse = mCourseDAO.findCourseWithId(course.getId());

        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
      return  selectedCourse;
    }

    public Course mQueryCourseFK(int id){
        databaseExecutor.execute(()->{
            selectedCourse = mCourseDAO.findCourseWithId(id);

        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return selectedCourse;
    }

    public List<Course> queryAssociatedCourses(int termId){
        databaseExecutor.execute(()->{
            mAllAssociatedCourse = mCourseDAO.findCoursesWithTermId(termId);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssociatedCourse;
    }

    public void insertCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.insertCourse(course);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void deleteCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.deleteCourse(course);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteCourseById(int id){
        databaseExecutor.execute(()->{
            Course c =mCourseDAO.findCourseWithId(id);
            mCourseDAO.deleteCourse(c);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void mUpdateCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.updateCourse(course);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public List<Course>mDeleteAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.getAllCourses();
            for(Course c: mAllCourses) mCourseDAO.deleteCourse(c);
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    //Instructor queries

    public List<Instructor> mGetAllInstructors(){
        databaseExecutor.execute(()->{
            mAllInstructors = mInstructorDAO.getAllInstructors();
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllInstructors;
    }

    public List<Instructor> mGetAllInstructorNames(){
        databaseExecutor.execute(()->{
            mAllInstructorNames = mInstructorDAO.getAllInstructorNames();
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllInstructorNames;
    }



    public Instructor mQueryInstructor(int id){
        databaseExecutor.execute(()->{
            selectedInstructor = mInstructorDAO.findInstructorWithId(id);

        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return selectedInstructor;
    }

    public void mInsertInstructor(Instructor instructor){
        databaseExecutor.execute(()->{
            mInstructorDAO.insertInstructor(instructor);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void mDeleteInstructor(Instructor instructor){
        databaseExecutor.execute(()->{
            mInstructorDAO.deleteInstructor(instructor);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public List<Instructor> mDeleteAllInstructors(){
        databaseExecutor.execute(()->{
            mAllInstructors = mInstructorDAO.getAllInstructors();
            for(Course c: mAllCourses) mCourseDAO.deleteCourse(c);
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllInstructors;
    }


    //Assessment Queries

    public List<Assessment> mGetAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public Assessment queryAssessmentById(int id){
        databaseExecutor.execute(()->{
            selectedAssessment = mAssessmentDAO.findAssessmentWithId(id);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return selectedAssessment;
    }



    public void mDeleteAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(assessment);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void mInsertAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insertAssessment(assessment);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void mUpdateAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    public void deleteAssessmentById(int id){
        databaseExecutor.execute(()->{
            Assessment a =mAssessmentDAO.findAssessmentWithId(id);
            mAssessmentDAO.deleteAssessment(a);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Assessment> mDeleteAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
            for(Assessment a: mAllAssessments) mAssessmentDAO.deleteAssessment(a);
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    //Term Queries

    public List<Term> mGetAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public Term queryTermById(int id){
        databaseExecutor.execute(()->{
            selectedTerm = mTermDAO.findTermWIthId(id);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return selectedTerm;
    }

    public void mInsertTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.insertTerm(term);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void mUpdateTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.updateTerm(term);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void mDeleteTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.deleteTerm(term);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteTermById(int id){
        databaseExecutor.execute(()->{
            selectedTerm = mTermDAO.findTermWIthId(id);
            mTermDAO.deleteTerm(selectedTerm);
        });
        try{
            Thread.sleep(150);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void mDeleteAllAssociatedCourses(int courseId){
        databaseExecutor.execute(()->{
           List<Course> associatedCourses = mCourseDAO.findCoursesWithTermId(courseId);
           for(Course c : associatedCourses) mCourseDAO.deleteCourse(c);
        });
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}