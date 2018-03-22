package com.example.mat.eventfinder.Fragments;

import android.app.ProgressDialog;
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
import com.example.mat.eventfinder.Adapters.ViewInterviewAdapter;
import com.example.mat.eventfinder.Extras.Events;
import com.example.mat.eventfinder.Extras.Interviews;
import com.example.mat.eventfinder.Extras.RecyclerViewDecoration;
import com.example.mat.eventfinder.Extras.RecyclerViewTouchListener;
import com.example.mat.eventfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewMyInterviewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Interviews> interviewsList;
    private ViewInterviewAdapter viewInterviewAdapter;
    private RecyclerView recyclerView;
    private DatabaseReference interviewsDataRef;
    private FirebaseUser currentFirebaseUser;
    private ProgressDialog progressDialog;
    private RecyclerView.LayoutManager eLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ViewMyInterviewsFragment() {
        // Required empty public constructor
    }

    public static ViewMyInterviewsFragment newInstance(String param1, String param2) {
        ViewMyInterviewsFragment fragment = new ViewMyInterviewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_view_my_interviews, container, false);

        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        interviewsList = new ArrayList<Interviews>();

        interviewsDataRef = FirebaseDatabase.getInstance().getReference("interviews");
        progressDialog = new ProgressDialog(getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.my_interviews_recycler_view);

        eLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(eLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerViewDecoration(getContext(), LinearLayoutManager.VERTICAL, 16));

        prepareInterviewsData();

        recyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getContext(), recyclerView, new RecyclerViewTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Interviews interviews = interviewsList.get(position);
                Toast.makeText(getContext(), interviews.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }

    private void prepareInterviewsData() {
        progressDialog.setMessage("Loading Data");
        progressDialog.show();

        interviewsDataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Interviews value = dataSnapshot1.getValue(Interviews.class);
                    if (value.getUid().equals(currentFirebaseUser.getUid())){
                        interviewsList.add(value);
                    }
                }

                viewInterviewAdapter = new ViewInterviewAdapter(interviewsList);
                recyclerView.setAdapter(viewInterviewAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(),databaseError.toException().toString(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
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
}
