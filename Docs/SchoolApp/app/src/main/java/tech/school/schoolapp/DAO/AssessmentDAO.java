package tech.school.schoolapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Course;

@Dao
public interface AssessmentDAO {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void insertAssessment(Assessment assessment);
        @Update
        void update(Assessment assessment);
        @Delete
        void deleteAssessment(Assessment assessment);
        @Query("SELECT * FROM assessment")
        List<Assessment> getAllAssessments();

        @Query("DELETE FROM assessment")
        void deleteAllAssessments();

        @Query("Select * FROM assessment WHERE id = :id")
        Assessment findAssessmentWithId(int id);
}
