package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimeSet extends AppCompatActivity {

    private EditText et1;
    private EditText et2;
    private EditText et3;
    private EditText et4;
    private EditText et5;
    private EditText et6;

    public String msg1;
    public String msg2;
    public String msg3;

    FirebaseDatabase database;
    DatabaseReference myRef1;
    DatabaseReference myRef2;
    DatabaseReference myRef3;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    Button btn1;
    Button btn2;
    Button btn3;

    CheckBox cb1;
    CheckBox cb2;
    CheckBox cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_set);

        et1 = (EditText) findViewById(R.id.setTime1);
        et2 = (EditText) findViewById(R.id.setTime2);
        et3 = (EditText) findViewById(R.id.setTime3);
        et4 = (EditText) findViewById(R.id.setTime4);
        et5 = (EditText) findViewById(R.id.setTime5);
        et6 = (EditText) findViewById(R.id.setTime6);

        btn1 = (Button) findViewById(R.id.setButton1);
        btn2 = (Button) findViewById(R.id.setButton2);
        btn3 = (Button) findViewById(R.id.setButton3);

        cb1 = (CheckBox) findViewById(R.id.checkBox2);
        cb2 = (CheckBox) findViewById(R.id.checkBox4);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);


        database = FirebaseDatabase.getInstance();

        myRef1 = database.getReference("Feeder/Time1");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("250000")) {
                    cb1.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        database = FirebaseDatabase.getInstance();

        myRef2 = database.getReference("Feeder/Time2");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("250000")) {
                    cb2.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseDatabase.getInstance();
        myRef3 = database.getReference("Feeder/Time3");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if (value.equals("250000")) {
                    cb3.setChecked(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btn1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg1 = et1.getText().toString() + et2.getText().toString() + "00";

                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if (cb1.isChecked()) {
                            databaseReference.child("Feeder").child("Time1").setValue("250000");
                            Toast.makeText(TimeSet.this, "사용 안함 설정 되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("Feeder").child("Time1").setValue(msg1);
                            Toast.makeText(TimeSet.this, "변경되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        btn2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg2 = et3.getText().toString() + et4.getText().toString() + "00";

                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if (cb2.isChecked()) {
                            databaseReference.child("Feeder").child("Time2").setValue("250000");
                            Toast.makeText(TimeSet.this, "사용 안함 설정 되었습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            databaseReference.child("Feeder").child("Time2").setValue(msg2);
                            Toast.makeText(TimeSet.this, "변경되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btn3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg3 = et5.getText().toString() + et6.getText().toString() + "00";

                myRef3.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String value = dataSnapshot.getValue(String.class);
                        if (cb3.isChecked()) {
                            databaseReference.child("Feeder").child("Time3").setValue("250000");
                            Toast.makeText(TimeSet.this, "사용 안함 설정 되었습니다.", Toast.LENGTH_SHORT).show();

                        } else {

                            databaseReference.child("Feeder").child("Time3").setValue(msg3);
                            Toast.makeText(TimeSet.this, "변경되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        cb1.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    databaseReference.child("Feeder").child("Time1").setValue("250000");
                } else {
                    //databaseReference.child("Feeder").child("Time1").setValue("250000");
                }
            }
        });

        cb2.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    databaseReference.child("Feeder").child("Time2").setValue("250000");
                } else {
                    //databaseReference.child("Feeder").child("Time1").setValue("250000");
                }
            }
        });

        cb3.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {
                    databaseReference.child("Feeder").child("Time3").setValue("250000");
                } else {
                    //databaseReference.child("Feeder").child("Time1").setValue("250000");
                }
            }
        });

    }
}