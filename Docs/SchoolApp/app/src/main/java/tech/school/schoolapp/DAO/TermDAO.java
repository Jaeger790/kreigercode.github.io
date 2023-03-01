package tech.school.schoolapp.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Term;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);
    @Update
    void updateTerm(Term term);
    @Delete
    void deleteTerm(Term term);
    @Query("SELECT * FROM term")
    List<Term> getAllTerms();

    @Query("SELECT * FROM term WHERE id = :id")
    Term findTermWIthId(int id);




}
