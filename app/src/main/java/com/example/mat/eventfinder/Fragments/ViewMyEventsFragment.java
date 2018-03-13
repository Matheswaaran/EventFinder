package com.example.mat.eventfinder.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mat.eventfinder.Adapters.ViewEventAdapter;
import com.example.mat.eventfinder.Extras.Events;
import com.example.mat.eventfinder.Extras.RecyclerViewDecoration;
import com.example.mat.eventfinder.Extras.RecyclerViewTouchListener;
import com.example.mat.eventfinder.R;

import java.util.ArrayList;
import java.util.List;

public class ViewMyEventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Events> eventsList = new ArrayList<>();
    private ViewEventAdapter viewEventAdapter;
    private RecyclerView recyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewMyEventsFragment() {
        // Required empty public constructor
    }

    public static ViewMyEventsFragment newInstance(String param1, String param2) {
        ViewMyEventsFragment fragment = new ViewMyEventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_events, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        viewEventAdapter = new ViewEventAdapter(eventsList);
        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(eLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new RecyclerViewDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));
        recyclerView.setAdapter(viewEventAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Events events = eventsList.get(position);
                Toast.makeText(getContext(), events.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareEventsData();

        return view;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void prepareEventsData(){

        Events events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);
        events = new Events("Ji","12","23","Mdu");
        eventsList.add(events);

        viewEventAdapter.notifyDataSetChanged();
    }
}
