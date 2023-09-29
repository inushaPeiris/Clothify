package com.ousl.clothify;

// import packages
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {

    private Context context;
    private ArrayList feedback_topic, feedback_description;

    public FeedbackAdapter(Context context, ArrayList feedback_topic, ArrayList feedback_description) {
        this.context = context;
        this.feedback_topic = feedback_topic;
        this.feedback_description = feedback_description;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.feedbacklist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.feedback_topic.setText(String.valueOf(feedback_topic.get(position)));
        holder.feedback_description.setText(String.valueOf(feedback_description.get(position)));
    }

    @Override
    public int getItemCount() {
        return feedback_topic.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView feedback_topic, feedback_description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            feedback_topic = itemView.findViewById(R.id.editFeedbackTopic);
            feedback_description = itemView.findViewById(R.id.editFeedbackDescription);
        }
    }
}