
        package tech.school.schoolapp.adapter;

        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.ArrayList;

        import tech.school.schoolapp.R;
        import tech.school.schoolapp.viewmodel.CourseItem;
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private ArrayList<CourseItem> courseList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){mListener = listener;}

    public static class CourseViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemIcon;
        public TextView courseTitle;
        public TextView courseStart;
        public TextView courseStartLabel;
        public TextView instructorName;


        public CourseViewHolder(View itemView, OnItemClickListener listener){
            super(itemView);
            itemIcon = itemView.findViewById(R.id.itemIcon);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseStart = itemView.findViewById(R.id.startDate);
            courseStartLabel = itemView.findViewById(R.id.startDateLabel);
            instructorName = itemView.findViewById(R.id.instructorName);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
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

    public CourseAdapter(ArrayList<CourseItem> courseItemsList){
        courseList = courseItemsList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_activity,parent,false);
        return new CourseViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        CourseItem currentItem = courseList.get(position);

        holder.itemIcon.setImageResource(currentItem.getmImage());
        holder.courseTitle.setText(currentItem.getmCourseTitle());
        holder.instructorName.setText(currentItem.getmCourseInstructor());
        holder.courseStartLabel.setText(currentItem.getCourseStartLabel());
        holder.courseStart.setText(currentItem.getCourseStart());
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}
