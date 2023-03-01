package tech.school.schoolapp.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.model.Term;

@Database(version = 3,entities = {Course.class, Instructor.class, Assessment.class, Term.class}, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class SchoolAppDatabase extends androidx.room.RoomDatabase {
    public abstract CourseDAO courseDAO();
    public abstract InstructorDAO instructorDAO();
    public abstract  AssessmentDAO assessmentDAO();
    public abstract TermDAO termDAO();

    private static volatile SchoolAppDatabase INSTANCE;

    static SchoolAppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),SchoolAppDatabase.class,"SchoolDatabase.db").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

}