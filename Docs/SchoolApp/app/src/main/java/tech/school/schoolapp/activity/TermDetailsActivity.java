package tech.school.schoolapp.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.DAO.TermDAO;
import tech.school.schoolapp.R;
import tech.school.schoolapp.adapter.CourseAdapter;
import tech.school.schoolapp.model.Assessment;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Instructor;
import tech.school.schoolapp.model.Term;
import tech.school.schoolapp.viewmodel.CourseItem;

public class TermDetailsActivity extends AppCompatActivity {

    ArrayList<CourseItem> courseItemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Repository repository = new Repository(getApplication());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_details_activity);
        backButtonHandler();
        getTermDetails();
        buildRecyclerView();

    }

    protected void backButtonHandler(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void getTermDetails(){
        TextView title = (TextView) findViewById(R.id.termTitleDetail);
        TextView startDate = (TextView) findViewById(R.id.termStart);
        TextView endDate = (TextView) findViewById(R.id.termEnd);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //term id for course lookup
        if(bundle!=null) {
            int id = bundle.getInt("id");
            String titleText = bundle.getString("title");
            String startText = bundle.getString("startDate");
            String endText = bundle.getString("endDate");

            title.setText(titleText);
            startDate.setText(startText);
            endDate.setText(endText);

            //get list of courses using selected term id.
            List<Course> associatedCourses = repository.queryAssociatedCourses(id);
            List<Instructor> allInstructorList = repository.mGetAllInstructors();
            int listSize = associatedCourses.size();
            String startCourseLabel = "Course Start: ";


            for(int i = 0; i < listSize; i++){
                courseItemList.add(new CourseItem(R.drawable.library_books_48px,
                        associatedCourses.get(i).getTitle(), allInstructorList.get(i).toString(),
                        startCourseLabel, dateFormat.format(associatedCourses.get(i).getStartDate())));
            }
        }

    }

    public void buildRecyclerView(){
        recyclerView = findViewById(R.id.courseListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CourseAdapter(courseItemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void onEditClick(View view){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int lookupId = bundle.getInt("id");

        Term editAssessment = repository.queryTermById(lookupId);
        int id = editAssessment.getId();
        String title = editAssessment.getTermTitle();
        String startDate = dateFormat.format(editAssessment.getEndDate());
        String endDate = dateFormat.format(editAssessment.getEndDate());

        Intent i = new Intent(this,AddTermActivity.class);
        i.putExtra("id",id);
        i.putExtra("title",title);
        i.putExtra("startDate",startDate);
        i.putExtra("endDate",endDate);
        startActivity(i);
    }

    public void onDeleteClick(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(TermDetailsActivity.this);

        builder.setMessage("Deleting a term will also delete all courses associated with it, Do you wish to continue?");

        builder.setTitle("Warning!");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            int id = bundle.getInt("id");
            Repository repository = new Repository(getApplication());
            repository.mDeleteAllAssociatedCourses(id);
            repository.deleteTermById(id);
            Intent i = new Intent(this,TermActivity.class);
            startActivity(i);
            finish();
        });

        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            recreate();
            dialog.cancel();
        });

        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();


    }

}
