package tech.school.schoolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Course;

public class AssessmentDetailsActivity extends AppCompatActivity {
   private  Repository repository = new Repository(getApplication());
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_details_activity);
        getAssessmentDetails();
        backButtonHandler();

    }

    protected void backButtonHandler(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void getAssessmentDetails(){
        TextView title = (TextView) findViewById(R.id.assessmentTitle);
        TextView goalDate = (TextView) findViewById(R.id.goalDate);
        TextView type = (TextView) findViewById(R.id.assessmentType);
        TextView course = (TextView) findViewById(R.id.courseName);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle!=null){
            String titleText = bundle.getString("title");
            String goalDateText = bundle.getString("goal");
            String typeText = bundle.getString("type");
            int courseId = bundle.getInt("course");


            Course associatedCourse = repository.mQueryCourseFK(courseId);
            String courseName = associatedCourse.getTitle();
            title.setText(titleText);
            goalDate.setText(goalDateText);
            type.setText(typeText);
            course.setText(courseName);
        }
    }

    public void onDeleteClick(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        Repository repository = new Repository(getApplication());
        repository.deleteAssessmentById(id);
        Intent i = new Intent(this,AssessmentActivity.class);
        startActivity(i);
    }

    public void onEditClick(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int lookupId = bundle.getInt("id");

        Assessment editAssessment = repository.queryAssessmentById(lookupId);
        int id = editAssessment.getId();
        String title = editAssessment.getTitle();
        String goal = dateFormat.format(editAssessment.getEndDate());
        String type = editAssessment.getType();
        int courseId = editAssessment.getCourseId();

        Intent i = new Intent(this,AddAssessmentActivity.class);
        i.putExtra("id",id);
        i.putExtra("title",title);
        i.putExtra("goal",goal);
        i.putExtra("type",type);
        i.putExtra("course",courseId);
        i.putExtra("id",id);
        startActivity(i);
    }


}
