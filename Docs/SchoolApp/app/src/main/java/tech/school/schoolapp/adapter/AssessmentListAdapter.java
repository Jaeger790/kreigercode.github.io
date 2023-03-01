package tech.school.schoolapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.school.schoolapp.R;
import tech.school.schoolapp.viewmodel.AssessmentItem;

public class AssessmentListAdapter extends RecyclerView.Adapter<AssessmentListAdapter.AssessmentViewHolder> {
        private ArrayList<AssessmentItem> assessmentList;
        private OnItemClickListener mListener;


        public interface  OnItemClickListener{
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener){
            mListener = listener;
        }

        public static class AssessmentViewHolder extends RecyclerView.ViewHolder{
            public TextView goalDate;
            public TextView assessmentTitle;
            public TextView assessmentType;



            public AssessmentViewHolder(View itemView, OnItemClickListener listener){
                super(itemView);

                goalDate = itemView.findViewById(R.id.goalDate);
                assessmentTitle = itemView.findViewById(R.id.assessmentTitle);
                assessmentType = itemView.findViewById(R.id.assessmentType);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener!= null){
                            int position = getAdapterPosition();
                            if(position != RecyclerView.NO_POSITION){
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
            }
        }

    public AssessmentListAdapter(ArrayList<AssessmentItem> assessmentItemList){
            assessmentList = assessmentItemList;
        }

        @NonNull
        @Override
        public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assessment_item_activity,parent,false);
            return new AssessmentViewHolder(v,mListener);
        }

        @Override
        public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
            AssessmentItem currentItem = assessmentList.get(position);

            holder.goalDate.setText(currentItem.getEndDate());
            holder.assessmentTitle.setText(currentItem.getAssessmentTitle());
            holder.assessmentType.setText(currentItem.getAssessmentType());
        }

        @Override
        public int getItemCount() {
            return assessmentList.size();
        }
}
