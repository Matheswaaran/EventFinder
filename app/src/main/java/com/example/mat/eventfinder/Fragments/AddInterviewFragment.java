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
import com.example.mat.eventfinder.Extras.Interviews;
import com.example.mat.eventfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class AddInterviewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseReference interviewDatabaseRef;
    private EditText title, postion, salary, organiser, date, startTime, endTime, location, desc;
    private Button addInterviews;
    private FirebaseUser currentFirebaseUser;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddInterviewFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_add_interview, container, false);

        title = (EditText)view.findViewById(R.id.interview_name);
        postion = (EditText)view.findViewById(R.id.interview_pos);
        salary = (EditText)view.findViewById(R.id.interview_salary);
        organiser = (EditText)view.findViewById(R.id.interview_organiser);
        date = (EditText)view.findViewById(R.id.interview_date);
        startTime = (EditText)view.findViewById(R.id.interview_start_time);
        endTime = (EditText)view.findViewById(R.id.interview_end_time);
        location = (EditText)view.findViewById(R.id.interview_location);
        desc = (EditText)view.findViewById(R.id.interview_desc);
        addInterviews = (Button)view.findViewById(R.id.interview_btn_add);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        interviewDatabaseRef = FirebaseDatabase.getInstance().getReference("interviews");

        addInterviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertInterview();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void clearData(){
        title.setText("");
        postion.setText("");
        salary.setText("");
        organiser.setText("");
        date.setText("");
        startTime.setText("");
        endTime.setText("");
        location.setText("");
        desc.setText("");
    }

    private void insertInterview(){
        String name = title.getText().toString().trim();
        String pos = postion.getText().toString().trim();
        String salaryPackage = salary.getText().toString().trim();
        String org = organiser.getText().toString().trim();
        String intdate = date.getText().toString().trim();
        String stime = startTime.getText().toString().trim();
        String etime = endTime.getText().toString().trim();
        String loc = location.getText().toString().trim();
        String description = desc.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(pos) && !TextUtils.isEmpty(pos) && !TextUtils.isEmpty(salaryPackage) && !TextUtils.isEmpty(org) && !TextUtils.isEmpty(intdate) && !TextUtils.isEmpty(stime) && !TextUtils.isEmpty(etime) && !TextUtils.isEmpty(loc) && !TextUtils.isEmpty(description)){
            String id = interviewDatabaseRef.push().getKey();
            Interviews interviews = new Interviews(id,name,pos,org,salaryPackage,intdate,stime,etime,loc,description,currentFirebaseUser.getUid());
            interviewDatabaseRef.child(id).setValue(interviews);
            Toast.makeText(getContext(), "Interview Added!", Toast.LENGTH_LONG).show();
            clearData();
        }else{
            Toast.makeText(getContext(), "Adding Error!", Toast.LENGTH_LONG).show();
        }
    }
}
