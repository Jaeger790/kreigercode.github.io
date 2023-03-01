package tech.school.schoolapp.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.model.AlertReceiver;
import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.AssessmentType;
import tech.school.schoolapp.model.Course;

public class AddAssessmentActivity extends AppCompatActivity {
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
    DatePickerDialog.OnDateSetListener listenDate;
    private Repository repository = new Repository(getApplication());
    Calendar calendar = Calendar.getInstance();
    Button alarmDate;
    EditText goalDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_assessment_activity);

        backButtonHandler();
        courseSpinner();
        fillFormValues();
        pickAlarmDate();

        Button button = new Button(this);
        button.setEnabled(false);

    }
    public void backButtonHandler(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarAlt);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void oaRadioSet(View view){
        RadioButton oa = findViewById(R.id.objectiveRadio);
        RadioButton pa = findViewById(R.id.performanceRadio);
        oa.setSelected(true);
        pa.setSelected(false);
    }
    public void paRadioSet(View view){
        RadioButton oa = findViewById(R.id.objectiveRadio);
        RadioButton pa = findViewById(R.id.performanceRadio);
        oa.setSelected(false);
        pa.setSelected(true);
    }

    public void courseSpinner(){
        Spinner courseSpinner = findViewById(R.id.courseSpinner);
        ArrayList<Course> allCoursesList = new ArrayList<>(repository.mGetAllCourses());
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,allCoursesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(adapter);

    }

    public void pickAlarmDate(){
        alarmDate = findViewById(R.id.alarmPicker);
        goalDate = findViewById(R.id.goalDate);
        alarmDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;

                String info = alarmDate.getText().toString();
                if(info.equals(""))info=goalDate.getText().toString();

                try{
                    calendar.setTime(dateFormat.parse(info));
                }catch (ParseException p){
                    p.printStackTrace();
                }

                new DatePickerDialog(AddAssessmentActivity.this,listenDate,
                        calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        listenDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH,month);
                calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);


                alarmDate.setText(dateFormat.format(calendar.getTime()));
            }

        };
    }

    public void notifyStart(){
        String alarmDateString = alarmDate.getText().toString();
        Date alarmDateD=null;
        try {
            alarmDateD = dateFormat.parse(alarmDateString);
        }catch (ParseException p){
            p.printStackTrace();
        }
        Long trigger = alarmDateD.getTime();
        Intent intent = new Intent(this, AlertReceiver.class);
        intent.putExtra("key",alarmDateD + "is set");
        PendingIntent sender = PendingIntent.getBroadcast(this,++LoginActivity.numAlert,intent,PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,trigger,sender);
    }


    private boolean checkNullValues(){
        EditText title = (EditText) findViewById(R.id.assessmentTitleEdit);
        EditText goalDate = (EditText) findViewById(R.id.goalDate);
        RadioButton oa = (RadioButton) findViewById(R.id.objectiveRadio);
        RadioButton pa = (RadioButton) findViewById(R.id.performanceRadio);

        Spinner courseSpinner = findViewById(R.id.courseSpinner);

        if(title == null || goalDate == null || (!oa.isSelected() && !pa.isSelected()) || courseSpinner.getSelectedItem() == null){
            Context context = getApplicationContext();
            CharSequence text ="Fill out all the fields";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
            return true;
        }
        return false;
    }
    public void addAssessmentClick(View view){
        if(checkNullValues())return;
        notifyStart();

        Assessment newAssessment = new Assessment();
        EditText title = (EditText) findViewById(R.id.assessmentTitleEdit);
        EditText goalDate = (EditText) findViewById(R.id.goalDate);
        RadioButton oa = (RadioButton) findViewById(R.id.objectiveRadio);
        RadioButton pa = (RadioButton) findViewById(R.id.performanceRadio);
        Spinner courseSpinner = findViewById(R.id.courseSpinner);




        String titleString = title.getText().toString();
        String goalDateString = goalDate.getText().toString();

        String typeString = "";
        Course associatedCourse = (Course) courseSpinner.getSelectedItem();

        newAssessment.setTitle(titleString);
        try{
            Date goalD = dateFormat.parse(goalDateString);
            newAssessment.setEndDate(goalD);
        }catch (ParseException p){
            p.printStackTrace();
        }
        if(oa.isSelected()){
            typeString = AssessmentType.assessmentString(AssessmentType.OBJECTIVE);
            newAssessment.setType(typeString);
        }
        if(pa.isSelected()){
            typeString = AssessmentType.assessmentString(AssessmentType.PERFORMANCE);
            newAssessment.setType(typeString);
        }



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            Assessment modifyAssessment = new Assessment();
            String goalString = goalDate.getText().toString();
            modifyAssessment.setId(bundle.getInt("id"));
            modifyAssessment.setTitle(titleString);

            try{
                Date goalD = dateFormat.parse(goalString);
                modifyAssessment.setEndDate(goalD);
            }catch (ParseException p){
                p.printStackTrace();
            }
            if(oa.isSelected()){
                typeString = AssessmentType.assessmentString(AssessmentType.OBJECTIVE);
                modifyAssessment.setType(typeString);
            }
            if(pa.isSelected()){
                typeString = AssessmentType.assessmentString(AssessmentType.PERFORMANCE);
                modifyAssessment.setType(typeString);
            }
            modifyAssessment.setCourseId(associatedCourse.getId());
            repository.mUpdateAssessment(modifyAssessment);
            Intent i = new Intent(this,AssessmentActivity.class);
            startActivity(i);
            return;
        }

        newAssessment.setCourseId(associatedCourse.getId());
        repository.mInsertAssessment(newAssessment);
        Intent i = new Intent(this,AssessmentActivity.class);
        startActivity(i);
    }

    private void fillFormValues(){
        EditText title = (EditText) findViewById(R.id.assessmentTitleEdit);
        EditText goalDate = (EditText) findViewById(R.id.goalDate);
        RadioButton oa = (RadioButton) findViewById(R.id.objectiveRadio);
        RadioButton pa = (RadioButton) findViewById(R.id.performanceRadio);
        Spinner courseSpinner = findViewById(R.id.courseSpinner);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null){
            int id = bundle.getInt("id");
            String titleText = bundle.getString("title");
            String goalDateText = bundle.getString("goal");
            String typeText = bundle.getString("type");
            int courseId = bundle.getInt("course");

            title.setText(titleText);
            goalDate.setText(goalDateText);
            if(typeText.equals("Objective Assessment")){
                oa.setSelected(true);
                oa.setChecked(true);
            }
            else if(typeText.equals("Performance Assessment")){
                pa.setSelected(true);
                pa.setChecked(true);
            }
            courseSpinner.setSelection(courseId-1);
        }

    }


}
