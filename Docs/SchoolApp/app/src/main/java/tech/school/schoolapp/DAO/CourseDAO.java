package tech.school.schoolapp.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tech.school.schoolapp.model.Course;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM course")
    List<Course> getAllCourses();

    @Query("Select * FROM Course WHERE id = :id")
    Course findCourseWithId(int id);

    @Query("Select * FROM Course WHERE termId = :id")
    List<Course> findCoursesWithTermId(int id);

    @Query("DELETE FROM course")
    void deleteAllCourses();

}
