package tech.school.schoolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.model.Instructor;

public class LoginActivity extends AppCompatActivity {
    public static int numAlert=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        buildInstructorList();


    }

    public void onEnterClick(View view){
            Intent intent = new Intent(this, TermActivity.class);
            startActivity(intent);
    }
    private void buildInstructorList(){
        Repository repository = new Repository(getApplication());
        if(repository.mGetAllInstructors().isEmpty()) {
            Instructor rambo = new Instructor("John Rambo", "123-456-789", "firstblood@longroad.edu");
            repository.mInsertInstructor(rambo);
            Instructor survivorman = new Instructor("Survivorman", "987-654-321", "survivealone@anywhere.edu");
            repository.mInsertInstructor(survivorman);
            Instructor docHoliday = new Instructor("Doc Holiday", "564-879-213", "okcorral@huckleberry.edu");
            repository.mInsertInstructor(docHoliday);
            Instructor bigfoot = new Instructor("Bigfoot", "798-645-312", "neverfindme@amireal.edu");
            repository.mInsertInstructor(bigfoot);
            Instructor freya = new Instructor("Freya", "312-465-798", "gardenofodin@midgard.edu");
            repository.mInsertInstructor(freya);
        }
    }



}
