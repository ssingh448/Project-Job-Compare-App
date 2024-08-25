package edu.gatech.seclass.jobcompare6300;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.bean.Job;

public class JobRecyclerViewAdapter extends RecyclerView.Adapter<JobRecyclerViewAdapter.ViewHolder> {

    private List<Job> jobs = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private List<Job> selectedJobs = new ArrayList<>();

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    public void setOnItemClickListener(JobRecyclerViewAdapter.OnItemClickListener listener){
        this.onItemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_container, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // TODO: sort job score to get Rank
        holder.jobInfo.setText(jobs.get(position).getTitle() + "\n" + jobs.get(position).getCompany());
        holder.jobScore.setText("Job Score: " + jobs.get(position).getJobScore().toString());
        if(jobs.get(position).getJobType().equals("Current Job")){
            holder.jobType.setText(jobs.get(position).getJobType());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                holder.jobInfo.setChecked(!holder.jobInfo.isChecked());
                //selectedJobs.add(jobs.get(holder.getAdapterPosition()));
                onItemClickListener.onClick(view, holder.getAdapterPosition());
            }
        });


    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Job> getSelectedJobs(){
        return selectedJobs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private CheckBox jobInfo;
        private TextView jobScore, jobType;
        public ViewHolder(View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            jobInfo = itemView.findViewById(R.id.checkBoxInfoID);
            jobScore = itemView.findViewById(R.id.listViewJobScoreID);
            jobType = itemView.findViewById(R.id.listViewJobTypeID);
        }
    }
}
