package com.example.mat.eventfinder.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mat.eventfinder.Extras.Events;
import com.example.mat.eventfinder.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mat on 13/03/18.
 */

public class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.MyViewHolder> {
    private List<Events> eventsList;

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

    public ViewEventAdapter(List<Events> eventsList){
        this.eventsList = eventsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_event, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Events events = eventsList.get(position);
        holder.title.setText(events.getTitle());
        holder.startTime.setText(events.getStartTime());
        holder.endTime.setText(events.getEndTime());
        holder.location.setText(events.getLocation());
    }

    @Override
    public int getItemCount(){
        return eventsList.size();
    }
}
