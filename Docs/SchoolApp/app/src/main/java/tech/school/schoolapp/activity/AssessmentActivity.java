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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.adapter.AssessmentListAdapter;
import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.viewmodel.AssessmentItem;

public class AssessmentActivity extends AppCompatActivity {
    private Repository repository = new Repository(getApplication());
    private ArrayList<AssessmentItem> assessmentItemList = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    private RecyclerView recyclerView;
    private AssessmentListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_activity);

        TextView toolbarHeader = findViewById(R.id.appBarHeader);
        toolbarHeader.setText("Assessments");
        createAssessmentList();
        buildRecyclerView();

    }

    public void onAssessmentsButtonClick(View view){
        Context context = getApplicationContext();
        CharSequence text ="This page is currently on screen";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text,duration);
        toast.show();
    }

    public void onCourseButtonClick(View view) {
        ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
        homeButton.setBackgroundResource(R.color.clear);
        ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);
        coursesButton.setBackgroundResource(R.color.cyan_process);
        Intent intent = new Intent(this, CourseActivity.class);
        startActivity(intent);
    }

    public void onTermsButtonClick(View view){
        ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
        homeButton.setBackgroundResource(R.color.clear);
        ImageButton termsButton = (ImageButton) findViewById(R.id.termsButton);
        termsButton.setBackgroundResource(R.color.cyan_process);
        Intent intent = new Intent(this, TermActivity.class);
        startActivity(intent);
    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.assessmentListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new AssessmentListAdapter(assessmentItemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AssessmentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<Assessment> assessmentList = repository.mGetAllAssessments();
                int id = assessmentList.get(position).getId();
                String title = assessmentList.get(position).getTitle();
                String goal = DateFormat.getDateInstance().format(assessmentList.get(position).getEndDate());
                String type = assessmentList.get(position).getType();
                int courseId = assessmentList.get(position).getCourseId();
                Intent intent = new Intent(peekAvailableContext(),AssessmentDetailsActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("goal",goal);
                intent.putExtra("type",type);
                intent.putExtra("course",courseId);
                intent.putExtra("id",id);
                startActivity(intent);

                adapter.notifyItemChanged(position);
            }
        });
    }

    public void createAssessmentList(){
        assessmentItemList = new ArrayList<>();
        List<Assessment> assessmentList = repository.mGetAllAssessments();
        int listSize = assessmentList.size();

        for(int i=0; i < listSize ;i++){
            assessmentItemList.add(new AssessmentItem(assessmentList.get(i).getType(),
                    assessmentList.get(i).getTitle(),
                    DateFormat.getDateInstance().format(assessmentList.get(i).getEndDate())));
        }
    }

    public void addAssessmentButton(View view){
        FloatingActionButton addAssessment = (FloatingActionButton) findViewById(R.id.addAssessmentButton);
        addAssessment.setBackgroundResource(R.color.blue_sapphire);
        Intent intent = new Intent(this,AddAssessmentActivity.class);
        startActivity(intent);

    }
}
