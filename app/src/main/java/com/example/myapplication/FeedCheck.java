package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FeedCheck extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    TextView circle;

    private Button sendbt1;
    private Button sendbt2;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_check);

        circle = (TextView) findViewById(R.id.circle);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Feeder").child("count");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("0")||value.equals("1")||value.equals("2")||value.equals("3")){
                    circle.setText("충분");
                    circle.setBackground(getResources().getDrawable(R.drawable.green_circle));
                }

                else if(value.equals("4")||value.equals("5")||value.equals("6")||value.equals("7")||value.equals("8")||value.equals("9")
                        ||value.equals("10")||value.equals("11")||value.equals("12")){
                    circle.setText("보통");
                    circle.setBackground(getResources().getDrawable(R.drawable.orange_circle));
                }
                else {
                    circle.setText("부족");
                    circle.setBackground(getResources().getDrawable(R.drawable.red_circle));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        sendbt1 = (Button) findViewById(R.id.button2);
        sendbt2 = (Button) findViewById(R.id.button5);

        sendbt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Feeder").child("count").setValue("0");
            }
        });

        sendbt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("Feeder").child("count").setValue("3");
            }
        });

    }
}
