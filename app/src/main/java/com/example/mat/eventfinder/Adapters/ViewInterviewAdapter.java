package com.example.mat.eventfinder.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.eventfinder.Extras.Events;
import com.example.mat.eventfinder.Extras.Interviews;
import com.example.mat.eventfinder.R;

import java.util.List;

/**
 * Created by mat on 21/03/18.
 */

public class ViewInterviewAdapter extends RecyclerView.Adapter<ViewInterviewAdapter.MyViewHolder> {
    private List<Interviews> interviewsList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, startTime, endTime, location;

        public MyViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.slot_title);
            startTime = (TextView) view.findViewById(R.id.slot_start_time);
            endTime = (TextView) view.findViewById(R.id.slot_end_time);
            location = (TextView) view.findViewById(R.id.slot_location);
        }
    }

    public ViewInterviewAdapter(List<Interviews> eventsList){
        this.interviewsList = eventsList;
    }

    @Override
    public ViewInterviewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_event, parent, false);

        return new ViewInterviewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewInterviewAdapter.MyViewHolder holder, int position) {
        Interviews events = interviewsList.get(position);
        holder.title.setText(events.getTitle());
        holder.startTime.setText(events.getOrganiser());
        holder.endTime.setText(events.getSalaryPackage());
        holder.location.setText(events.getSalaryPackage());
    }

    @Override
    public int getItemCount(){
        return interviewsList.size();
    }
}
