package tech.school.schoolapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Instructor;

@Dao
public interface InstructorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertInstructor(Instructor instructor);

    @Update
    void update(Instructor instructor);

    @Delete
    void deleteInstructor(Instructor instructor);

    @Query("SELECT * FROM Instructor")
    List<Instructor> getAllInstructors();

    @Query("Select * FROM Instructor WHERE id = :id")
    Instructor findInstructorWithId(int id);

    @Query("SELECT id,name FROM Instructor")
    List<Instructor> getAllInstructorNames();

}
