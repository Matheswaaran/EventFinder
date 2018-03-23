package com.example.mat.eventfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mat.eventfinder.Extras.Events;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EventDetailActivity extends AppCompatActivity {
    String eventId;
    TextView eventName, eventOrg, eventStart, eventEnd, eventNumDays, eventAmt, eventLoc, eventDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        eventName = (TextView)findViewById(R.id.event_name);
        eventOrg = (TextView)findViewById(R.id.event_organiser);
        eventStart = (TextView)findViewById(R.id.event_start_datetime);
        eventEnd = (TextView)findViewById(R.id.event_end_datetime);
        eventNumDays = (TextView)findViewById(R.id.event_numdays);
        eventAmt = (TextView)findViewById(R.id.event_amount);
        eventLoc = (TextView)findViewById(R.id.event_location);
        eventDesc = (TextView)findViewById(R.id.event_desc);

        Bundle bundle = getIntent().getExtras();
        eventId = bundle.getString("eventId");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("events");
        Query qry = databaseReference.child(eventId);

        qry.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Events events = dataSnapshot.getValue(Events.class);
                String start = events.getStartDate() + " - " + events.getStartTime() + "   ";
                String end =  "   " + events.getEndDate() + " - " + events.getEndTime();
                eventName.setText(events.getTitle());
                eventOrg.setText(events.getOrganiser());
                eventStart.setText(start);
                eventEnd.setText(end);
                eventNumDays.setText(events.getDays() + " Day(s)");
                eventAmt.setText("₹ " + events.getAmount());
                eventLoc.setText(events.getLocation());
                eventDesc.setText(events.getDesc());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.toException().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
