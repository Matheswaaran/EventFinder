package com.example.mat.eventfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mat.eventfinder.Extras.Interviews;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InterviewDetailActivity extends AppCompatActivity {
    private String interviewId;
    private TextView title, position, organiser, salaryPackage, date, startTime, endTIme, location, description;
    private DatabaseReference databaseReference;
    private Query qry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_detail);

        title = (TextView)findViewById(R.id.interview_name);
        position = (TextView)findViewById(R.id.interview_position);
        organiser = (TextView)findViewById(R.id.interview_organiser);
        salaryPackage = (TextView)findViewById(R.id.interview_salary);
        date = (TextView)findViewById(R.id.interview_date);
        startTime = (TextView)findViewById(R.id.interview_start_time);
        endTIme = (TextView)findViewById(R.id.interview_end_time);
        location = (TextView)findViewById(R.id.interview_location);
        description = (TextView)findViewById(R.id.interview_desc);

        Bundle bundle = getIntent().getExtras();
        interviewId = bundle.getString("interviewId");

        databaseReference = FirebaseDatabase.getInstance().getReference("interviews");
        qry = databaseReference.child(interviewId);

        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Interviews interviews = dataSnapshot.getValue(Interviews.class);
                title.setText(interviews.getTitle());
                position.setText(interviews.getPosition());
                organiser.setText(interviews.getOrganiser());
                salaryPackage.setText(interviews.getSalaryPackage());
                date.setText(interviews.getInterviewDate());
                startTime.setText(interviews.getStartTime() + " ");
                endTIme.setText(" " + interviews.getEndTIme());
                location.setText(interviews.getLocation());
                description.setText(interviews.getDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.toException().toString(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
