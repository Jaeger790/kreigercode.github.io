package tech.school.schoolapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.adapter.CourseAdapter;
import tech.school.schoolapp.viewmodel.CourseItem;
import tech.school.schoolapp.model.Instructor;

public class CourseActivity extends AppCompatActivity {
    ArrayList<CourseItem> courseItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Repository repository = new Repository(getApplication());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        setHeader();
        Thread thread = new Thread(() -> createCourseList());
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        buildRecyclerView();
        pageSelected();
    }

    public void setHeader(){
        TextView appBarHeader = findViewById(R.id.appBarHeader);
        appBarHeader.setText(R.string.courseLabel);

    }

    public void onCourseButtonClick(View view){
        Context context = getApplicationContext();
        CharSequence text ="This page is currently on screen";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    public void onAssessmentsButtonClick(View view){
        ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
        homeButton.setBackgroundResource(R.color.cyan_process);
        Intent intent = new Intent(this,AssessmentActivity.class);
        startActivity(intent);
    }

    public void onTermsButtonClick(View view){
        ImageButton homeButton = (ImageButton) findViewById(R.id.coursesButton);
        homeButton.setBackgroundResource(R.color.clear);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.termsButton);
        coursesButton.setBackgroundResource(R.color.cyan_process);
        Intent intent = new Intent(this, TermActivity.class);
        startActivity(intent);
    }

    public void pageSelected(){
        ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);
        homeButton.setBackgroundResource(R.color.clear);
        coursesButton.setBackgroundResource(R.color.cyan_process);
    }

    public void createCourseList(){
        courseItemList = new ArrayList<>();
        List<Course> allCoursesList = repository.mGetAllCourses();

        String startCourseLabel = "Course Start: ";
        int listSize = allCoursesList.size();

        for(int i=0; i< listSize ;i++){
            int instructorId = allCoursesList.get(i).getInstructorId();
            Instructor instructor = repository.mQueryInstructor(instructorId);

            courseItemList.add(new CourseItem(R.drawable.library_books_48px,
                    allCoursesList.get(i).getTitle(), instructor.getName(),
                    startCourseLabel, DateFormat.getDateInstance().format(allCoursesList.get(i).getStartDate())));

        }
    }
    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.courseListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CourseAdapter(courseItemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<Course> courseList = repository.mGetAllCourses();

                int id = courseList.get(position).getId();
                String title = courseList.get(position).getTitle();
                String startDate = DateFormat.getDateInstance().format(courseList.get(position).getStartDate());
                String endDate = DateFormat.getDateInstance().format(courseList.get(position).getEndDate());
                String courseStatus = courseList.get(position).getStatus();
                int instructorId = courseList.get(position).getInstructorId();
                int termId = courseList.get(position).getTermId();
                String note = courseList.get(position).getNote();

                Intent intent = new Intent(peekAvailableContext(),CourseDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("startDate",startDate);
                intent.putExtra("endDate",endDate);
                intent.putExtra("instructorId",instructorId);
                intent.putExtra("courseStatus",courseStatus);
                intent.putExtra("termId",termId);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });
    }



    public void addCourseButton(View view){
        Intent intent = new Intent(this, AddCourseActivity.class);
        startActivity(intent);
    }



}
