package tech.school.schoolapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.AssessmentType;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.CourseStatus;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.model.Term;

public class AddCourseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Repository repository = new Repository(getApplication());
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course_activity);
        backButtonHandler();
        statusSpinner();
        instructorSpinner();
        termSpinner();
        fillFormValues();
    }
    protected void onResume() {
        super.onResume();

    }


    protected void backButtonHandler(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void statusSpinner(){
        Spinner statusSpinner = (Spinner) findViewById(R.id.statusSpinner);
        ArrayList<CourseStatus> statusList = new ArrayList<>(Arrays.asList(CourseStatus.values()));
        ArrayAdapter<CourseStatus> adapter = new ArrayAdapter<>( this,android.R.layout.simple_spinner_item,statusList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

    }
    public void instructorSpinner(){
        Spinner instructorSpinner = (Spinner) findViewById(R.id.instructorSpinner);
        ArrayList<Instructor> instructorList = new ArrayList<>(repository.mGetAllInstructors());
        ArrayAdapter<Instructor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,instructorList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instructorSpinner.setAdapter(adapter);
    }

    public void termSpinner(){
        Spinner termSpinner = (Spinner) findViewById(R.id.termSpinner);
        ArrayList<Term> termList = new ArrayList<>(repository.mGetAllTerms());
        ArrayAdapter<Term> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,termList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        termSpinner.setAdapter(adapter);
    }

    private boolean checkNullValues(){
        EditText courseTitle = (EditText) findViewById(R.id.courseTitleEdit);
        EditText startDateBox = (EditText) findViewById(R.id.termStartDateEdit);
        EditText endDateBox = (EditText) findViewById(R.id.termEndDateEdit);
        Spinner courseStatus = (Spinner) findViewById(R.id.statusSpinner);
        Spinner termSpinner = (Spinner) findViewById(R.id.termSpinner);
        Spinner instructor = (Spinner) findViewById(R.id.instructorSpinner);

        if(courseTitle == null || startDateBox == null || endDateBox == null || courseStatus == null || termSpinner.getSelectedItem() == null || instructor.getSelectedItem() == null){
            Context context = getApplicationContext();
            CharSequence text ="Fill out all the fields";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
            return true;
        }
        return false;
    }


    public void saveButtonHandler(View view) {
        if(checkNullValues())return;

        Course course = new Course();
        EditText courseTitle = (EditText) findViewById(R.id.courseTitleEdit);
        EditText startDateBox = (EditText) findViewById(R.id.termStartDateEdit);
        EditText endDateBox = (EditText) findViewById(R.id.termEndDateEdit);
        Spinner courseStatus = (Spinner) findViewById(R.id.statusSpinner);
        Spinner termSpinner = (Spinner) findViewById(R.id.termSpinner);
        Spinner instructor = (Spinner) findViewById(R.id.instructorSpinner);

        String startDateString = startDateBox.getText().toString();
        String endDateString = endDateBox.getText().toString();
        Date endDate = null;
        Date startDate = null;
        try {
            startDate = dateFormat.parse(startDateString);
            endDate = dateFormat.parse(endDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        course.setStartDate(startDate);
        course.setEndDate(endDate);
        course.setTitle(String.valueOf(courseTitle.getText()));
        course.setStatus(courseStatus.getSelectedItem().toString());

        Instructor courseInstructor = (Instructor) instructor.getSelectedItem();
        course.setInstructorId(courseInstructor.getId());

        Term courseTerm = (Term) termSpinner.getSelectedItem();
        course.setTermId(courseTerm.getId());



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Course modifyCourse = new Course();
            int courseId = bundle.getInt("id");
            String title = bundle.getString("title");
            String startDString = startDateBox.getText().toString();
            String endDString = endDateBox.getText().toString();

            try{
                Date cStart = dateFormat.parse(startDString);
                modifyCourse.setStartDate(cStart);
                Date cEnd = dateFormat.parse(endDString);
                modifyCourse.setEndDate(cEnd);
            }catch (ParseException p){
                p.printStackTrace();
            }

            String modCourseStatus = courseStatus.getSelectedItem().toString();
            int termId = ((Term) termSpinner.getSelectedItem()).getId();
            int instructorId = ((Instructor) instructor.getSelectedItem()).getId();
            modifyCourse.setId(courseId);
            modifyCourse.setTitle(title);
            modifyCourse.setStatus(modCourseStatus);
            modifyCourse.setTermId(termId);
            modifyCourse.setInstructorId(instructorId);

            repository.mUpdateCourse(modifyCourse);
            Intent i = new Intent(this,CourseActivity.class);
            startActivity(i);
            return;
        }

        repository.insertCourse(course);

        Intent i = new Intent(this,CourseActivity.class);
        startActivity(i);
    }

    private void fillFormValues(){
        EditText courseTitle = (EditText) findViewById(R.id.courseTitleEdit);
        EditText startDateBox = (EditText) findViewById(R.id.termStartDateEdit);
        EditText endDateBox = (EditText) findViewById(R.id.termEndDateEdit);
        Spinner courseStatus = (Spinner) findViewById(R.id.statusSpinner);
        Spinner termSpinner = (Spinner) findViewById(R.id.termSpinner);
        Spinner instructor = (Spinner) findViewById(R.id.instructorSpinner);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            int id = bundle.getInt("id");
            String titleText = bundle.getString("title");
            String startDateText = bundle.getString("startDate");
            String endDateText = bundle.getString("endDate");
            String courseStatusText = bundle.getString("status");


            ArrayList<CourseStatus> statusList = new ArrayList<>(Arrays.asList(CourseStatus.values()));
            ArrayAdapter<CourseStatus> adapter = new ArrayAdapter<>( this,android.R.layout.simple_spinner_item,statusList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            courseStatus.setAdapter(adapter);
            courseStatus.setSelection(adapter.getPosition(CourseStatus.valueOf(courseStatusText)));



            courseTitle.setText(titleText);
            startDateBox.setText(startDateText);
            endDateBox.setText(endDateText);

        }

    }

    public void cancelButton(View view){
        Intent intent = new Intent(this,CourseActivity.class);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
