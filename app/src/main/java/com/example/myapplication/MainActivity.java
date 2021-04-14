package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private static final int CALL_FOR_CREATION1 = 0;
    private static final int CALL_FOR_CREATION2 = 1;
    private static final int CALL_FOR_CREATION3 = 2;

    FirebaseDatabase database;
    DatabaseReference myRef;

    private Button sendbt1;     //수동으로 먹이주기
    private Button sendbt2;
    private Button cam;

    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;


    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();   //데이터베이스의 인스턴스 불러옴
    private DatabaseReference databaseReference = firebaseDatabase.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cam = (Button) findViewById(R.id.button3);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("https://www.youtube.com/channel/UC06qLk5YWNGxKcn2p_Pbv4g?view_as=subscriber"));
                // 버튼을 클릭 시 새 창을 열어 유튜브 링크로 이동
                startActivity(intent);
            }
        });




        sendbt1 = (Button) findViewById(R.id.handButton);
        sendbt1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {                                      //수동으로 사료주기 이벤트
                databaseReference.child("Feeder").child("command").setValue("1");   //lockcommand 값에 1으로 세팅
            }
        });

        sendbt2 = (Button) findViewById(R.id.resetButton);
        cb1 = (CheckBox) findViewById(R.id.checkBox2);
        cb2 = (CheckBox) findViewById(R.id.checkBox3);
        cb3 = (CheckBox) findViewById(R.id.checkBox4);

        sendbt2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {                             //초기화 버튼

                databaseReference.child("Feeder").child("command").setValue("0");
                databaseReference.child("Feeder").child("Time1").setValue("090000");
                databaseReference.child("Feeder").child("Time2").setValue("120000");
                databaseReference.child("Feeder").child("Time3").setValue("180000");
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();


        myRef.child("Feeder").child("count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("7")){
                    createNotification1();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myRef.child("Feeder").child("count").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                if(value.equals("14")){
                    createNotification2();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void createNotification1() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Feeder");    // 메시지 제목
        builder.setContentText("사료가 얼마 남지 않았습니다.");   //메시지 내용

        builder.setColor(Color.YELLOW);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(2, builder.build());
    }

    private void createNotification2() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Feeder");        // 메시지 제목
        builder.setContentText("사료가 다 떨어졌습니다.");  // 메시지 내용

        builder.setColor(Color.RED);
        // 사용자가 탭을 클릭하면 자동 제거
        builder.setAutoCancel(true);

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }

    public void TimeClick(View view) {
        Intent intent = new Intent(this, TimeSet.class);
        startActivityForResult(intent, CALL_FOR_CREATION1);
    }

    public void FeedClick(View view) {
        Intent intent = new Intent(this, FeedCheck.class);
        startActivityForResult(intent, CALL_FOR_CREATION3);
    }




/*
    public void TimeClick(View view) {                   //Timeset 이동
        Intent intent = new Intent(this, TimeSet.class);
        startActivityForResult(intent, CALL_FOR_CREATION1);
    }
*/
   /*
    public void CameraClick(View view) {           //카메라 이동
        Intent intent = new Intent(this, Camera.class);
        startActivityForResult(intent, CALL_FOR_CREATION2);
    }
    */
/*
    public void FeedClick(View view) {             //사료양 체크 이동
        Intent intent = new Intent(this, FeedCheck.class);
        startActivityForResult(intent, CALL_FOR_CREATION3);
    }

    */
}
