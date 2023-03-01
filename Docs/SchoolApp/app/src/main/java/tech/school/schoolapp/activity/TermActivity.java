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
import java.util.ArrayList;
import java.util.List;

import tech.school.schoolapp.DAO.Repository;
import tech.school.schoolapp.R;
import tech.school.schoolapp.adapter.TermAdapter;
import tech.school.schoolapp.model.Course;
import tech.school.schoolapp.model.Term;
import tech.school.schoolapp.viewmodel.TermItem;

public class TermActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    private TermAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TermItem> termItemList = new ArrayList<>();
    private Repository repository = new Repository(getApplication());
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.term_activity);
        pageSelected();
        TextView appHeaderBar = (TextView) findViewById(R.id.appBarHeader);
        appHeaderBar.setText(R.string.terms_header);
        createTermList();
        buildRecyclerView();
    }

    protected void onResume() {
        super.onResume();
        createTermList();
    }

    public void buildRecyclerView() {
        recyclerView = findViewById(R.id.termListView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new TermAdapter(termItemList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListenerTerm(new TermAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<Term> termList = repository.mGetAllTerms();
                int id = termList.get(position).getId();
                String title = termList.get(position).getTermTitle();
                String startDate = DateFormat.getDateInstance().format(termList.get(position).getStartDate());
                String endDate = DateFormat.getDateInstance().format(termList.get(position).getEndDate());

                Intent intent = new Intent(peekAvailableContext(),TermDetailsActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("startDate",startDate);
                intent.putExtra("endDate",endDate);
                startActivity(intent);
            }
        });


        }
        public void pageSelected () {
            ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
            ImageButton termsButton = (ImageButton) findViewById(R.id.termsButton);
            homeButton.setBackgroundResource(R.color.clear);
            termsButton.setBackgroundResource(R.color.cyan_process);
        }
        public void onTermsButtonClick (View view){
            Context context = getApplicationContext();
            CharSequence text = "This page is currently displaying";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        public void onAssessmentsButtonClick (View view){
            ImageButton homeButton = (ImageButton) findViewById(R.id.assessmentButton);
            homeButton.setBackgroundResource(R.color.cyan_process);
            Intent intent = new Intent(this, AssessmentActivity.class);
            startActivity(intent);
        }

        public void onCourseButtonClick (View view){
            ImageButton termsButton = (ImageButton) findViewById(R.id.termsButton);
            termsButton.setBackgroundResource(R.color.clear);
            ImageButton coursesButton = (ImageButton) findViewById(R.id.coursesButton);
            coursesButton.setBackgroundResource(R.color.cyan_process);
            Intent intent = new Intent(this, CourseActivity.class);
            startActivity(intent);
        }
        public void addTermButtonClick (View view){
            Intent intent = new Intent(this, AddTermActivity.class);
            startActivity(intent);
        }

        public void createTermList () {
            termItemList = new ArrayList<>();
            List<Term> allTermList = repository.mGetAllTerms();
            String startLabel = "Start Date: ";
            String endLabel = "End Date: ";

            int listSize = allTermList.size();

            for (int i = 0; i < listSize; i++) {
                termItemList.add(new TermItem(allTermList.get(i).getTermTitle(), startLabel, endLabel, DateFormat.getDateInstance().format(allTermList.get(i).getStartDate()), DateFormat.getDateInstance().format(allTermList.get(i).getStartDate())));

            }
        }


    }

