package tech.school.schoolapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.school.schoolapp.R;
import tech.school.schoolapp.viewmodel.TermItem;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private ArrayList<TermItem> termList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListenerTerm(OnItemClickListener listener){mListener = listener;}

    public static class TermViewHolder extends RecyclerView.ViewHolder{
        public TextView termTitle;
        public TextView dateLabelStart;
        public TextView dateLabelEnd;
        public TextView termStart;
        public TextView termEnd;

        public TermViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            termTitle = itemView.findViewById(R.id.termTitle);
            dateLabelStart = itemView.findViewById(R.id.tStart);
            dateLabelEnd = itemView.findViewById(R.id.dateLabelEnd);
            termStart = itemView.findViewById(R.id.termStart);
            termEnd = itemView.findViewById(R.id.termEnd);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public TermAdapter(ArrayList<TermItem> termItemsList){termList = termItemsList;}

    @NonNull
    @Override
    public  TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.term_item_template,parent,false);
        return new TermViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        TermItem currentItem = termList.get(position);

        holder.termTitle.setText(currentItem.getTermTitle());
        holder.dateLabelStart.setText(currentItem.getDateLabelStart());
        holder.dateLabelEnd.setText(currentItem.getDateLabelEnd());
        holder.termStart.setText(currentItem.getTermStart());
        holder.termEnd.setText(currentItem.getTermEnd());
    }

    @Override
    public int getItemCount() {
        return termList.size();
    }
}
