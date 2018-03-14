package com.example.mat.eventfinder.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mat.eventfinder.Extras.Events;
import com.example.mat.eventfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddEventFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseReference eventDatabaseRef;
    private FirebaseAuth firebaseAuth;
    List<Events> events;
    private EditText title, startTime, endTime, location, desc, organiser;
    private Button addEvent;
    FirebaseUser currentFirebaseUser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddEventFragment() {
        // Required empty public constructor
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
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);

        title = (EditText)view.findViewById(R.id.event_name);
        startTime = (EditText)view.findViewById(R.id.event_start_time);
        endTime = (EditText)view.findViewById(R.id.event_end_time);
        location = (EditText)view.findViewById(R.id.event_location);
        desc = (EditText)view.findViewById(R.id.event_desc);
        organiser = (EditText)view.findViewById(R.id.event_organiser);
        addEvent = (Button) view.findViewById(R.id.event_btn_add);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertEvent();
            }
        });

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        eventDatabaseRef = FirebaseDatabase.getInstance().getReference("events");

        return view;


    }

    private void clearData(){
        title.setText("");
        startTime.setText("");
        endTime.setText("");
        location.setText("");
        desc.setText("");
        organiser.setText("");
    }

    private void insertEvent() {
        String event_name = title.getText().toString().trim();
        String sTime = startTime.getText().toString().trim();
        String eTime = endTime.getText().toString().trim();
        String loc = location.getText().toString().trim();
        String description = desc.getText().toString().trim();
        String organ = organiser.getText().toString().trim();
        String uid = currentFirebaseUser.getUid().toString();

        if (!TextUtils.isEmpty(event_name) && !TextUtils.isEmpty(sTime) && !TextUtils.isEmpty(eTime) && !TextUtils.isEmpty(loc) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(organ)){
            String id = eventDatabaseRef.push().getKey();
            Events events = new Events(id,event_name,sTime,eTime,loc,description,organ,uid);
            eventDatabaseRef.child(id).setValue(events);
            Toast.makeText(getContext(), "Event Added!", Toast.LENGTH_LONG).show();
            clearData();
        } else{
            Toast.makeText(getContext(), "Adding Error!", Toast.LENGTH_LONG).show();
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
