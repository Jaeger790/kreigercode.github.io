package tech.school.schoolapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.adapter.CourseAdapter;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.model.Term;
import tech.school.schoolapp.viewmodel.CourseItem;

public class CourseDetailsActivity extends AppCompatActivity {

    ArrayList<CourseItem> courseItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Repository repository = new Repository(getApplication());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details_activity);
        backButtonHandler();
        getCourseDetails();


    }



    public void shareNote(View view){
        EditText noteBox = (EditText)findViewById(R.id.note);
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,noteBox.getText().toString());
        sendIntent.putExtra(Intent.EXTRA_TITLE,"d");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }

    protected void backButtonHandler(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void getCourseDetails(){
        TextView titleBox = (TextView) findViewById(R.id.courseTitle);
        TextView startBox = (TextView) findViewById(R.id.courseStart);
        TextView endBox = (TextView) findViewById(R.id.courseEnd);
        TextView statusBox = (TextView) findViewById(R.id.courseStatus);
        TextView instructorNameBox = (TextView) findViewById(R.id.iName);
        TextView instructorPhoneBox = (TextView) findViewById(R.id.iPhone);
        TextView instructorEmailBox = (TextView) findViewById(R.id.iEmail);
        TextView termBox = (TextView) findViewById(R.id.tTitle);
        TextView noteBox = (EditText) findViewById(R.id.note);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            int id = bundle.getInt("id");
            String courseTitle = bundle.getString("title");
            String startDate = bundle.getString("startDate");
            String endDate = bundle.getString("endDate");
            String courseStatus = bundle.getString("courseStatus");
            int instructorId = bundle.getInt("instructorId");
            int termId = bundle.getInt("termId");
            Instructor courseInstructor = repository.mQueryInstructor(instructorId);
            String instructorName = courseInstructor.getName();
            String instructorPhone = courseInstructor.getPhoneNumber();
            String instructorEmail = courseInstructor.getEmail();
            Term associatedTerm = repository.queryTermById(termId);
            String termTitle = associatedTerm.getTermTitle();
            String noteText = bundle.getString("note");

            titleBox.setText(courseTitle);
            startBox.setText(startDate);
            endBox.setText(endDate);
            statusBox.setText(courseStatus);
            instructorNameBox.setText(instructorName);
            instructorPhoneBox.setText(instructorPhone);
            instructorEmailBox.setText(instructorEmail);
            termBox.setText(termTitle);
            noteBox.setText(noteText);
        }

    }



    public void saveNoteClick(View view){

        Intent intent = getIntent();


        Bundle bundle = intent.getExtras();
            int id = bundle.getInt("id");
            Course course = repository.mQueryCourseFK(id);
            EditText noteBox = (EditText) findViewById(R.id.note);
            String note = noteBox.getText().toString();
            course.setNote(note);
            repository.mUpdateCourse(course);
            Button saveNote = (Button) findViewById(R.id.saveNote);
            saveNote.setEnabled(false);
    }


    public void onEditClick(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int lookupId = bundle.getInt("id");
        Course editCourse = repository.mQueryCourseFK(lookupId);
        int id = editCourse.getId();
        String title = editCourse.getTitle();
        String startDate = dateFormat.format(editCourse.getStartDate());
        String endDate = dateFormat.format(editCourse.getEndDate());
        String status = editCourse.getStatus();
        int termId = editCourse.getTermId();
        int instructorId = editCourse.getInstructorId();

        Intent i = new Intent(this,AddCourseActivity.class);
        i.putExtra("id",id);
        i.putExtra("title",title);
        i.putExtra("startDate",startDate);
        i.putExtra("endDate",endDate);
        i.putExtra("status",status);
        i.putExtra("termId",termId);
        i.putExtra("instructorId",instructorId);
        startActivity(i);


    }

    public void onDeleteClick(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int id = bundle.getInt("id");
        Repository repository = new Repository(getApplication());
        repository.deleteCourseById(id);
        Intent i = new Intent(this,CourseActivity.class);
        startActivity(i);
    }
}
